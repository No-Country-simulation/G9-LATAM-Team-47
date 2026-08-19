import requests
from flask import current_app, session
from .exceptions import (
    AuthenticationError, AuthorizationError, BackendError,
    BackendUnavailableError, ConflictError, ResourceNotFoundError,
    ValidationError,
)


class FinanceAIAPI:
    def _headers(self, authenticated=True):
        headers = {"Accept": "application/json", "Content-Type": "application/json"}
        if authenticated:
            token = session.get("access_token")
            if not token:
                raise AuthenticationError("Tu sesión no está activa.", 401)
            headers["Authorization"] = f"Bearer {token}"
        return headers

    @staticmethod
    def _message(response):
        try:
            body = response.json()
        except ValueError:
            return "El servicio devolvió una respuesta no válida."
        if isinstance(body, dict):
            return body.get("message") or body.get("detail") or body.get("error") or "La solicitud no pudo completarse."
        return "La solicitud no pudo completarse."

    def request(self, method, endpoint, *, authenticated=True, json=None, params=None):
        try:
            response = requests.request(
                method,
                f'{current_app.config["BACKEND_API_URL"]}{endpoint}',
                headers=self._headers(authenticated),
                json=json,
                params=params,
                timeout=current_app.config["REQUEST_TIMEOUT"],
            )
        except (requests.Timeout, requests.ConnectionError) as exc:
            raise BackendUnavailableError("FinanceAI no está disponible temporalmente.", 503) from exc
        except requests.RequestException as exc:
            raise BackendError("No fue posible completar la solicitud.", 502) from exc

        message = self._message(response)
        if response.status_code == 401:
            session.clear()
            raise AuthenticationError("Tu sesión expiró o el token no es válido.", 401)
        if response.status_code == 403:
            raise AuthorizationError("No tienes permisos para realizar esta operación.", 403)
        if response.status_code == 400:
            raise ValidationError(message, 400)
        if response.status_code == 404:
            raise ResourceNotFoundError(message, 404)
        if response.status_code == 409:
            raise ConflictError(message, 409)
        if response.status_code >= 500:
            raise BackendError(message, response.status_code)
        if not response.ok:
            raise BackendError(message, response.status_code)
        if response.status_code == 204 or not response.content:
            return None
        try:
            return response.json()
        except ValueError as exc:
            raise BackendError("El backend devolvió JSON no válido.", 502) from exc

    def register(self, payload):
        return self.request("POST", "/auth/register", authenticated=False, json=payload)

    def login(self, payload):
        return self.request("POST", "/auth/login", authenticated=False, json=payload)

    def create_profile(self, payload):
        return self.request("POST", "/perfil", json=payload)

    def get_my_profile(self):
        return self.request("GET", "/usuarios/miPerfil")

    def list_transactions(self):
        data = self.request("GET", "/transacciones/usuario/transacciones")
        return [normalize_transaction(x) for x in (data or [])]

    def create_transaction(self, payload):
        return normalize_transaction(self.request("POST", "/transacciones/usuario/transacciones", json=payload))

    def request_analysis(self):
        return normalize_analysis(self.request("POST", "/analisis/predict"))

    def list_history(self):
        data = self.request("GET", "/analisis/usuario/historial")
        return [normalize_analysis(x) for x in (data or [])]

    def get_latest_analysis(self):
            return normalize_analysis(self.request("GET", "/analisis/usuario/ultimo"))

def _pick(data, *keys, default=None):
    if not isinstance(data, dict):
        return default
    for key in keys:
        if key in data:
            return data[key]
    return default


def normalize_transaction(data):
    return {
        "nombre_comercio": _pick(data, "nombre_comercio", "nombreComercio", default=""),
        "monto_transaccion": _pick(data, "monto_transaccion", "montoTransaccion", default=0),
        "medio_pago": _pick(data, "medio_pago", "medioPago", default=""),
        "fecha": _pick(data, "fecha", default=""),
    }


def normalize_analysis(data):
    data = data or {}
    return {
        "id": _pick(data, "id"),
        "perfil_financiero": _pick(data, "perfil_financiero", "perfilFinanciero", default="SIN_DATOS"),
        "probabilidad": _pick(data, "probabilidad"),
        "nivel_endeudamiento": _pick(data, "nivel_endeudamiento", "nivelEndeudamiento"),
        "rango_ahorro": _pick(data, "rango_ahorro", "rangoAhorro", "porcentaje_ahorro", default=""),
        "resumen_gastos": _pick(data, "resumen_gastos", "resumenGastos", default={}) or {},
        "recomendaciones": _pick(data, "recomendaciones", default=[]) or [],
    }


api = FinanceAIAPI()
