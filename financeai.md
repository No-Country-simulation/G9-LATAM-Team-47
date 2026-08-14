This file is a merged representation of the entire codebase, combined into a single document by Repomix.

<file_summary>
This section contains a summary of this file.

<purpose>
This file contains a packed representation of the entire repository's contents.
It is designed to be easily consumable by AI systems for analysis, code review,
or other automated processes.
</purpose>

<file_format>
The content is organized as follows:
1. This summary section
2. Repository information
3. Directory structure
4. Repository files (if enabled)
5. Multiple file entries, each consisting of:
  - File path as an attribute
  - Full contents of the file
</file_format>

<usage_guidelines>
- This file should be treated as read-only. Any changes should be made to the
  original repository files, not this packed version.
- When processing this file, use the file path to distinguish
  between different files in the repository.
- Be aware that this file may contain sensitive information. Handle it with
  the same level of security as you would the original repository.
</usage_guidelines>

<notes>
- Some files may have been excluded based on .gitignore rules and Repomix's configuration
- Binary files are not included in this packed representation. Please refer to the Repository Structure section for a complete list of file paths, including binary files
- Files matching patterns in .gitignore are excluded
- Files matching default ignore patterns are excluded
- Files are sorted by Git change count (files with more changes are at the bottom)
</notes>

</file_summary>

<directory_structure>
backend/
  .mvn/
    wrapper/
      maven-wrapper.properties
  src/
    main/
      java/
        com/
          nocountry/
            financeai/
              client/
                IAClient.java
              config/
                .gitkeep
                CorsConfig.java
                IniciarAdmin.java
                JwtConfig.java
                OpenApiConfig.java
                OrdenOpenApi.java
                RestClientConfig.java
              controller/
                .gitkeep
                AdminController.java
                AnalisisController.java
                AuthController.java
                HistorialAnalisisController.java
                PerfilFinancieroController.java
                TestSecurityController.java
                TransactionController.java
                UserController.java
              dto/
                request/
                  AnalisisRequest.java
                  ChangePasswdRequest.java
                  LoginRequest.java
                  PerfilFinancieroRequest.java
                  RegisterRequest.java
                  TransactionRequest.java
                  UserRequest.java
                response/
                  AnalisisResponse.java
                  AuthResponse.java
                  ErrorResponse.java
                  HistorialAnalisisResponse.java
                  PerfilFinancieroResponse.java
                  TransaccionResponse.java
                  UserResponse.java
                .gitkeep
              entity/
                enums/
                  EstadoCivil.java
                  MedioPago.java
                  PerfilFinanciero.java
                  RangoAhorro.java
                  Rol.java
                  Sexo.java
                .gitkeep
                HistorialAnalisisEntity.java
                PerfilFinancieroEntity.java
                TransactionEntity.java
                UserEntity.java
              exception/
                .gitkeep
                ApiExceptionHandler.java
                ResourceNotFoundException.java
                UserAlreadyExistsException.java
              repository/
                .gitkeep
                HistorialAnalisisRepository.java
                PerfilFinancieroRepository.java
                TransactionRepository.java
                UserRepository.java
              security/
                CustomUserDetailsService.java
                JwtAuthFilter.java
                JwtUtil.java
                SecurityConfig.java
              service/
                impl/
                  AnalisisIAServiceImpl.java
                  AuthServiceImpl.java
                  HistorialAnalisisServiceImpl.java
                  PerfilFinancieroServiceImpl.java
                  TransaccionServiceImpl.java
                  UserServiceImpl.java
                .gitkeep
                AnalisisIAService.java
                AuthService.java
                HistorialAnalisisService.java
                PerfilFinancieroService.java
                TransaccionService.java
                UserService.java
              FinanceaiApplication.java
      resources/
        db/
          migration/
            V1__create_users_table.sql
            V2__create_transactions_table.sql
            V3__create_analysis_table.sql
            V4__create_perfil_Financiero_table.sql
            V5__fix_historial_analisis_schema.sql
            V6__add_documento_to_usuarios.sql
        application.yml
    test/
      java/
        com/
          nocountry/
            financeai/
              FinanceaiApplicationTests.java
  Dockerfile
  HELP.md
  mvnw
  mvnw.cmd
  pom.xml
  README.md
data-science/
  modeloFinanceAI/
    Dockerfile
    main.py
    modelo_clasificacion_transacciones.pkl
    modelo_perfil_financiero.pkl
    requirements.txt
  main.py
  modelo_clasificacion_transacciones.pkl
  modelo_perfil_financiero.pkl
  README.md
  requirements.txt
frontend/
  css/
    style.css
  js/
    api.js
    auth.js
    dashboard.js
  dashboard.html
  index.html
frontend-flask/
  app/
    routes/
      __init__.py
      analisis.py
      auth.py
      dashboard.py
      historial.py
      transacciones.py
    services/
      __init__.py
      exceptions.py
      financeai_api.py
    static/
      css/
        style.css
      js/
        main.js
    templates/
      analisis/
        resultado.html
      auth/
        login.html
        registro.html
      dashboard/
        index.html
      errors/
        error.html
      historial/
        index.html
      transacciones/
        _tabla.html
        formulario.html
        lista.html
      base.html
    __init__.py
    config.py
    decorators.py
    errors.py
  tests/
    conftest.py
    test_normalizers.py
    test_session.py
  .env.example
  .gitignore
  Dockerfile
  README.md
  requirements.txt
  run.py
.gitattributes
.gitignore
docker-compose.yml
notamaestra_financeai_v4.md
Protocolo de colaboracion.md
README.md
</directory_structure>

<files>
This section contains the contents of the repository's files.

<file path="backend/src/main/java/com/nocountry/financeai/entity/.gitkeep">

</file>

<file path="frontend-flask/app/routes/__init__.py">

</file>

<file path="frontend-flask/app/routes/analisis.py">
from flask import Blueprint, flash, redirect, render_template, url_for
from ..decorators import login_required
from ..services.exceptions import FinanceAIError
from ..services.financeai_api import api

analisis_bp = Blueprint("analisis", __name__, url_prefix="/analisis")


@analisis_bp.post("/generar")
@login_required
def generate():
    try:
        result = api.request_analysis()
        return render_template("analisis/resultado.html", result=result)
    except FinanceAIError as error:
        flash(f"El análisis no pudo completarse: {error}", "danger")
        return redirect(url_for("dashboard.index"))
</file>

<file path="frontend-flask/app/routes/auth.py">
from urllib.parse import urlparse
from flask import Blueprint, flash, redirect, render_template, request, session, url_for
from ..services.exceptions import ConflictError, FinanceAIError, ValidationError
from ..services.financeai_api import api

auth_bp = Blueprint("auth", __name__)


def _safe_next(target):
    parsed = urlparse(target or "")
    return bool(target and target.startswith("/") and not parsed.scheme and not parsed.netloc)


@auth_bp.route("/", methods=["GET", "POST"])
@auth_bp.route("/login", methods=["GET", "POST"])
def login():
    if session.get("access_token"):
        return redirect(url_for("dashboard.index"))
    if request.method == "POST":
        try:
            data = api.login({"email": request.form.get("email", "").strip(), "password": request.form.get("password", "")})
            token = (data or {}).get("token")
            if not token:
                raise ValidationError("El backend no devolvió un token válido.", 502)
            session.clear()
            session["access_token"] = token
            session.permanent = True
            flash((data or {}).get("message", "Sesión iniciada correctamente."), "success")
            target = request.args.get("next")
            return redirect(target if _safe_next(target) else url_for("dashboard.index"))
        except FinanceAIError as error:
            flash(str(error), "danger")
    return render_template("auth/login.html")


@auth_bp.route("/registro", methods=["GET", "POST"])
def register():
    if request.method == "POST":
        user_payload = {
            "nombre": request.form.get("nombre", "").strip(),
            "apellido": request.form.get("apellido", "").strip(),
            "documento": request.form.get("documento", "").strip(),
            "email": request.form.get("email", "").strip(),
            "password": request.form.get("password", ""),
            "fecha_nacimiento": request.form.get("fecha_nacimiento", ""),
            "sexo": request.form.get("sexo", ""),
            "estado_civil": request.form.get("estado_civil", ""),
            "numero_hijos": int(request.form.get("numero_hijos") or 0),
        }
        profile_payload = {
            "empleo_formal": int(request.form.get("empleo_formal") or 0),
            "ingreso_mensual": request.form.get("ingreso_mensual", ""),
            "linea_credito": request.form.get("linea_credito", ""),
        }
        try:
            data = api.register(user_payload)
            token = (data or {}).get("token")
            if not token:
                raise ValidationError("El registro no devolvió un token válido.", 502)
            session.clear()
            session["access_token"] = token
            session.permanent = True
            try:
                api.create_profile(profile_payload)
            except ConflictError:
                pass
            flash("Cuenta y perfil creados correctamente.", "success")
            return redirect(url_for("dashboard.index"))
        except FinanceAIError as error:
            flash(str(error), "danger")
    return render_template("auth/registro.html")


@auth_bp.post("/logout")
def logout():
    session.clear()
    flash("Sesión cerrada correctamente.", "success")
    return redirect(url_for("auth.login"))
</file>

<file path="frontend-flask/app/routes/dashboard.py">
from flask import Blueprint, render_template
from ..decorators import login_required
from ..services.exceptions import FinanceAIError
from ..services.financeai_api import api

dashboard_bp = Blueprint("dashboard", __name__, url_prefix="/dashboard")


@dashboard_bp.get("/")
@login_required
def index():
    transactions, warning = [], None
    try:
        transactions = api.list_transactions()
    except FinanceAIError as error:
        warning = str(error)
    total = sum(float(item.get("monto_transaccion") or 0) for item in transactions)
    return render_template("dashboard/index.html", transactions=transactions[:5], total=total, warning=warning)
</file>

<file path="frontend-flask/app/routes/historial.py">
from flask import Blueprint, render_template
from ..decorators import login_required
from ..services.financeai_api import api

historial_bp = Blueprint("historial", __name__, url_prefix="/historial")


@historial_bp.get("/")
@login_required
def index():
    return render_template("historial/index.html", items=api.list_history())
</file>

<file path="frontend-flask/app/routes/transacciones.py">
from flask import Blueprint, flash, redirect, render_template, request, url_for
from ..decorators import login_required
from ..services.exceptions import FinanceAIError
from ..services.financeai_api import api

transacciones_bp = Blueprint("transacciones", __name__, url_prefix="/transacciones")


@transacciones_bp.get("/")
@login_required
def index():
    return render_template("transacciones/lista.html", transactions=api.list_transactions())


@transacciones_bp.route("/nueva", methods=["GET", "POST"])
@login_required
def create():
    if request.method == "POST":
        payload = {
            "nombre_comercio": request.form.get("nombre_comercio", "").strip(),
            "monto_transaccion": request.form.get("monto_transaccion", ""),
            "medio_pago": request.form.get("medio_pago", ""),
        }
        try:
            api.create_transaction(payload)
            flash("Transacción registrada correctamente.", "success")
            return redirect(url_for("transacciones.index"))
        except FinanceAIError as error:
            flash(str(error), "danger")
    return render_template("transacciones/formulario.html")
</file>

<file path="frontend-flask/app/services/__init__.py">

</file>

<file path="frontend-flask/app/services/exceptions.py">
class FinanceAIError(Exception):
    def __init__(self, message, status_code=None, details=None):
        super().__init__(message)
        self.status_code = status_code
        self.details = details


class AuthenticationError(FinanceAIError):
    pass


class AuthorizationError(FinanceAIError):
    pass


class ValidationError(FinanceAIError):
    pass


class ConflictError(FinanceAIError):
    pass


class ResourceNotFoundError(FinanceAIError):
    pass


class BackendUnavailableError(FinanceAIError):
    pass


class BackendError(FinanceAIError):
    pass
</file>

<file path="frontend-flask/app/services/financeai_api.py">
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
</file>

<file path="frontend-flask/app/static/css/style.css">
body{background:#f5f7fb}.card{border:0;box-shadow:0 .25rem 1rem rgba(28,39,49,.08);border-radius:1rem}.auth-card,.form-card{max-width:34rem}.navbar{background:linear-gradient(90deg,#0d6efd,#1746a2)!important}
</file>

<file path="frontend-flask/app/static/js/main.js">
document.querySelectorAll('form').forEach(form => form.addEventListener('submit', () => { const button=form.querySelector('button[type="submit"],button:not([type])'); if(button){button.disabled=true;} }));
</file>

<file path="frontend-flask/app/templates/analisis/resultado.html">
{% extends 'base.html' %}{% block content %}<h1>Resultado del análisis</h1><div class="row g-3"><div class="col-md-4"><div class="card p-3"><span>Perfil</span><strong>{{ result.perfil_financiero }}</strong></div></div><div class="col-md-4"><div class="card p-3"><span>Probabilidad</span><strong>{{ result.probabilidad or 'N/D' }}</strong></div></div><div class="col-md-4"><div class="card p-3"><span>Ahorro</span><strong>{{ result.rango_ahorro or 'N/D' }}</strong></div></div></div><div class="card p-3 mt-3"><h2 class="h5">Resumen de gastos</h2><ul>{% for category, amount in result.resumen_gastos.items() %}<li>{{ category|title }}: ${{ amount }}</li>{% else %}<li>Sin datos</li>{% endfor %}</ul><h2 class="h5">Recomendaciones</h2><ul>{% for item in result.recomendaciones %}<li>{{ item }}</li>{% else %}<li>Sin recomendaciones</li>{% endfor %}</ul></div>{% endblock %}
</file>

<file path="frontend-flask/app/templates/auth/login.html">
{% extends 'base.html' %}{% block title %}Iniciar sesión | FinanceAI{% endblock %}{% block content %}
<div class="auth-card card p-4 mx-auto"><h1 class="h3 mb-3">Iniciar sesión</h1>
<form method="post"><div class="mb-3"><label class="form-label">Correo</label><input class="form-control" type="email" name="email" required></div>
<div class="mb-3"><label class="form-label">Contraseña</label><input class="form-control" type="password" name="password" required></div>
<button class="btn btn-primary w-100">Entrar</button></form><p class="mt-3 mb-0">¿No tienes cuenta? <a href="{{ url_for('auth.register') }}">Regístrate</a></p></div>
{% endblock %}
</file>

<file path="frontend-flask/app/templates/auth/registro.html">
{% extends 'base.html' %}{% block title %}Registro | FinanceAI{% endblock %}{% block content %}
<div class="card p-4"><h1 class="h3">Crear cuenta y perfil</h1><form method="post" class="row g-3">
{% for name,label,type in [('nombre','Nombre','text'),('apellido','Apellido','text'),('documento','Documento','text'),('email','Correo','email'),('password','Contraseña','password'),('fecha_nacimiento','Fecha de nacimiento','date')] %}
<div class="col-md-6"><label class="form-label">{{ label }}</label><input class="form-control" name="{{ name }}" type="{{ type }}" required></div>{% endfor %}
<div class="col-md-4"><label class="form-label">Sexo</label><select class="form-select" name="sexo" required><option value="M">Masculino</option><option value="F">Femenino</option></select></div>
<div class="col-md-4"><label class="form-label">Estado civil</label><select class="form-select" name="estado_civil" required>{% for x in ['SOLTERO','CASADO','DIVORCIADO','VIUDO'] %}<option>{{ x }}</option>{% endfor %}</select></div>
<div class="col-md-4"><label class="form-label">Número de hijos</label><input class="form-control" type="number" min="0" name="numero_hijos" value="0" required></div>
<div class="col-md-4"><label class="form-label">Empleo formal</label><select class="form-select" name="empleo_formal"><option value="1">Sí</option><option value="0">No</option></select></div>
<div class="col-md-4"><label class="form-label">Ingreso mensual</label><input class="form-control" type="number" min="0.01" step="0.01" name="ingreso_mensual" required></div>
<div class="col-md-4"><label class="form-label">Línea de crédito</label><input class="form-control" type="number" min="0" step="0.01" name="linea_credito" required></div>
<div class="col-12"><button class="btn btn-primary">Crear cuenta</button></div></form></div>{% endblock %}
</file>

<file path="frontend-flask/app/templates/dashboard/index.html">
{% extends 'base.html' %}{% block content %}<div class="d-flex justify-content-between align-items-center mb-4"><div><h1>Dashboard</h1><p class="text-muted mb-0">Resumen de tu actividad financiera</p></div><form method="post" action="{{ url_for('analisis.generate') }}"><button class="btn btn-success">Generar análisis</button></form></div>
{% if warning %}<div class="alert alert-warning">{{ warning }}</div>{% endif %}<div class="row g-3 mb-4"><div class="col-md-6"><div class="card p-3"><span class="text-muted">Transacciones</span><strong class="display-6">{{ transactions|length }}</strong></div></div><div class="col-md-6"><div class="card p-3"><span class="text-muted">Monto mostrado</span><strong class="display-6">${{ '%.2f'|format(total) }}</strong></div></div></div>
<div class="card p-3"><div class="d-flex justify-content-between"><h2 class="h5">Últimas transacciones</h2><a href="{{ url_for('transacciones.create') }}">Nueva</a></div>{% include 'transacciones/_tabla.html' %}</div>{% endblock %}
</file>

<file path="frontend-flask/app/templates/errors/error.html">
{% extends 'base.html' %}{% block content %}<div class="text-center py-5"><div class="display-1 fw-bold text-primary">{{ code }}</div><h1>{{ title }}</h1><p class="text-muted">{{ message }}</p><a class="btn btn-primary" href="{{ url_for('dashboard.index') if session.get('access_token') else url_for('auth.login') }}">Continuar</a></div>{% endblock %}
</file>

<file path="frontend-flask/app/templates/historial/index.html">
{% extends 'base.html' %}{% block content %}<h1>Historial de análisis</h1>{% for item in items %}<div class="card p-3 mb-3"><div class="d-flex justify-content-between"><strong>{{ item.perfil_financiero }}</strong><span>{{ item.probabilidad or 'N/D' }}</span></div><ul class="mt-2 mb-0">{% for recommendation in item.recomendaciones %}<li>{{ recommendation }}</li>{% endfor %}</ul></div>{% else %}<div class="alert alert-info">Todavía no hay análisis guardados.</div>{% endfor %}{% endblock %}
</file>

<file path="frontend-flask/app/templates/transacciones/_tabla.html">
{% if transactions %}<div class="table-responsive"><table class="table"><thead><tr><th>Comercio</th><th>Monto</th><th>Medio</th><th>Fecha</th></tr></thead><tbody>{% for tx in transactions %}<tr><td>{{ tx.nombre_comercio }}</td><td>${{ tx.monto_transaccion }}</td><td>{{ tx.medio_pago }}</td><td>{{ tx.fecha }}</td></tr>{% endfor %}</tbody></table></div>{% else %}<p class="text-muted my-3">No hay transacciones registradas.</p>{% endif %}
</file>

<file path="frontend-flask/app/templates/transacciones/formulario.html">
{% extends 'base.html' %}{% block content %}<div class="card p-4 mx-auto form-card"><h1 class="h3">Nueva transacción</h1><form method="post"><div class="mb-3"><label class="form-label">Comercio</label><input class="form-control" name="nombre_comercio" required></div><div class="mb-3"><label class="form-label">Monto</label><input class="form-control" type="number" min="0.01" step="0.01" name="monto_transaccion" required></div><div class="mb-3"><label class="form-label">Medio de pago</label><select class="form-select" name="medio_pago">{% for x in ['EFECTIVO','DEBITO','CREDITO','TRANSFERENCIA'] %}<option>{{ x }}</option>{% endfor %}</select></div><button class="btn btn-primary">Guardar</button></form></div>{% endblock %}
</file>

<file path="frontend-flask/app/templates/transacciones/lista.html">
{% extends 'base.html' %}{% block content %}<div class="d-flex justify-content-between"><h1>Transacciones</h1><a class="btn btn-primary" href="{{ url_for('transacciones.create') }}">Nueva transacción</a></div><div class="card p-3 mt-3">{% include 'transacciones/_tabla.html' %}</div>{% endblock %}
</file>

<file path="frontend-flask/app/templates/base.html">
<!doctype html>
<html lang="es">
<head>
  <meta charset="utf-8"><meta name="viewport" content="width=device-width,initial-scale=1">
  <title>{% block title %}FinanceAI{% endblock %}</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="{{ url_for('static', filename='css/style.css') }}" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary"><div class="container">
  <a class="navbar-brand fw-bold" href="{{ url_for('dashboard.index') }}">FinanceAI</a>
  {% if session.get('access_token') %}<div class="d-flex gap-2">
    <a class="btn btn-sm btn-outline-light" href="{{ url_for('transacciones.index') }}">Transacciones</a>
    <a class="btn btn-sm btn-outline-light" href="{{ url_for('historial.index') }}">Historial</a>
    <form method="post" action="{{ url_for('auth.logout') }}"><button class="btn btn-sm btn-light">Salir</button></form>
  </div>{% endif %}
</div></nav>
<main class="container py-4">
{% for category, message in get_flashed_messages(with_categories=true) %}
<div class="alert alert-{{ category }} alert-dismissible fade show">{{ message }}<button class="btn-close" data-bs-dismiss="alert"></button></div>
{% endfor %}
{% block content %}{% endblock %}
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body></html>
</file>

<file path="frontend-flask/app/__init__.py">
from flask import Flask
from .config import Config
from .errors import register_error_handlers


def create_app(config_object=Config):
    app = Flask(__name__)
    app.config.from_object(config_object)

    if not app.config.get("SECRET_KEY"):
        raise RuntimeError("FLASK_SECRET_KEY es obligatoria")

    from .routes.auth import auth_bp
    from .routes.dashboard import dashboard_bp
    from .routes.transacciones import transacciones_bp
    from .routes.analisis import analisis_bp
    from .routes.historial import historial_bp

    app.register_blueprint(auth_bp)
    app.register_blueprint(dashboard_bp)
    app.register_blueprint(transacciones_bp)
    app.register_blueprint(analisis_bp)
    app.register_blueprint(historial_bp)
    register_error_handlers(app)
    return app
</file>

<file path="frontend-flask/app/config.py">
import os
from datetime import timedelta


def _as_bool(value: str | None, default=False):
    if value is None:
        return default
    return value.strip().lower() in {"1", "true", "yes", "on"}


class Config:
    SECRET_KEY = os.getenv("FLASK_SECRET_KEY")
    BACKEND_API_URL = os.getenv("BACKEND_API_URL", "http://localhost:8080/api/v1").rstrip("/")
    REQUEST_TIMEOUT = float(os.getenv("REQUEST_TIMEOUT", "10"))
    SESSION_COOKIE_HTTPONLY = True
    SESSION_COOKIE_SAMESITE = "Lax"
    SESSION_COOKIE_SECURE = _as_bool(os.getenv("SESSION_COOKIE_SECURE"), False)
    PERMANENT_SESSION_LIFETIME = timedelta(hours=8)
    WTF_CSRF_TIME_LIMIT = timedelta(hours=8)
    DEBUG = _as_bool(os.getenv("FLASK_DEBUG"), False)
</file>

<file path="frontend-flask/app/decorators.py">
from functools import wraps
from flask import flash, redirect, request, session, url_for


def login_required(view):
    @wraps(view)
    def wrapped(*args, **kwargs):
        if not session.get("access_token"):
            flash("Tu sesión no está activa. Inicia sesión para continuar.", "warning")
            return redirect(url_for("auth.login", next=request.path))
        return view(*args, **kwargs)
    return wrapped
</file>

<file path="frontend-flask/app/errors.py">
from flask import flash, redirect, render_template, request, session, url_for
from .services.exceptions import AuthenticationError, AuthorizationError, BackendError, BackendUnavailableError


def register_error_handlers(app):
    @app.errorhandler(AuthenticationError)
    def authentication_error(error):
        session.clear()
        flash(str(error), "warning")
        return redirect(url_for("auth.login", next=request.path))

    @app.errorhandler(AuthorizationError)
    def authorization_error(error):
        return render_template("errors/error.html", code=403, title="Acceso denegado", message=str(error)), 403

    @app.errorhandler(BackendUnavailableError)
    def unavailable_error(error):
        return render_template("errors/error.html", code=503, title="Servicio no disponible", message=str(error)), 503

    @app.errorhandler(BackendError)
    def backend_error(error):
        code = error.status_code if error.status_code and 500 <= error.status_code <= 599 else 502
        return render_template("errors/error.html", code=code, title="Error del servicio", message="No fue posible completar la operación."), code

    @app.errorhandler(404)
    def not_found(_error):
        return render_template("errors/error.html", code=404, title="Página no encontrada", message="La página solicitada no existe."), 404

    @app.errorhandler(500)
    def internal_error(_error):
        return render_template("errors/error.html", code=500, title="Error interno", message="Ocurrió un error inesperado."), 500
</file>

<file path="frontend-flask/tests/conftest.py">
import pytest
from app import create_app


class TestConfig:
    TESTING = True
    SECRET_KEY = "test-secret"
    BACKEND_API_URL = "http://backend.test/api/v1"
    REQUEST_TIMEOUT = 1
    SESSION_COOKIE_HTTPONLY = True
    SESSION_COOKIE_SAMESITE = "Lax"
    SESSION_COOKIE_SECURE = False
    WTF_CSRF_ENABLED = False


@pytest.fixture
def app():
    return create_app(TestConfig)


@pytest.fixture
def client(app):
    return app.test_client()
</file>

<file path="frontend-flask/tests/test_normalizers.py">
from app.services.financeai_api import normalize_analysis, normalize_transaction


def test_transaction_accepts_camel_case():
    value = normalize_transaction({"nombreComercio": "Tienda", "montoTransaccion": 10, "medioPago": "EFECTIVO"})
    assert value["nombre_comercio"] == "Tienda"
    assert value["monto_transaccion"] == 10


def test_analysis_accepts_both_contracts():
    value = normalize_analysis({"perfilFinanciero": "SALUDABLE", "resumenGastos": {"salud": 20}})
    assert value["perfil_financiero"] == "SALUDABLE"
    assert value["resumen_gastos"]["salud"] == 20
</file>

<file path="frontend-flask/tests/test_session.py">
from unittest.mock import patch
from app.services.exceptions import AuthenticationError


def test_protected_page_redirects_without_session(client):
    response = client.get("/dashboard/")
    assert response.status_code == 302
    assert "/login" in response.headers["Location"]


def test_login_saves_token(client):
    with patch("app.routes.auth.api.login", return_value={"token": "jwt-test", "message": "ok"}):
        response = client.post("/login", data={"email": "user@example.com", "password": "secret"})
    assert response.status_code == 302
    with client.session_transaction() as session:
        assert session["access_token"] == "jwt-test"


def test_logout_clears_session(client):
    with client.session_transaction() as session:
        session["access_token"] = "jwt-test"
    response = client.post("/logout")
    assert response.status_code == 302
    with client.session_transaction() as session:
        assert "access_token" not in session


def test_401_handler_clears_session(client, app):
    with client.session_transaction() as session:
        session["access_token"] = "expired"
    @app.get("/_test_auth_error")
    def test_error():
        raise AuthenticationError("expirada", 401)
    response = client.get("/_test_auth_error")
    assert response.status_code == 302
    with client.session_transaction() as session:
        assert "access_token" not in session
</file>

<file path="frontend-flask/.env.example">
FLASK_APP=run.py
FLASK_ENV=development
FLASK_SECRET_KEY=replace-with-a-long-random-secret
BACKEND_API_URL=http://localhost:8080/api/v1
REQUEST_TIMEOUT=10
SESSION_COOKIE_SECURE=false
</file>

<file path="frontend-flask/.gitignore">
.env
.venv/
__pycache__/
.pytest_cache/
*.py[cod]
instance/
</file>

<file path="frontend-flask/Dockerfile">
FROM python:3.12-slim
WORKDIR /app
ENV PYTHONDONTWRITEBYTECODE=1 PYTHONUNBUFFERED=1
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt
COPY . .
EXPOSE 5000
CMD ["flask", "--app", "run.py", "run", "--host=0.0.0.0", "--port=5000"]
</file>

<file path="frontend-flask/README.md">
# FinanceAI Frontend Flask

Capa de presentación Flask para consumir el backend Spring Boot de FinanceAI. No accede directamente a PostgreSQL ni a FastAPI.

## Funciones incluidas

- Registro y login contra Spring Boot.
- JWT guardado en la sesión de Flask.
- Creación de perfil financiero después del registro.
- Dashboard, alta y consulta de transacciones.
- Vistas preparadas para análisis e historial.
- Normalización temporal de respuestas camelCase/snake_case.
- Manejo centralizado de 400, 401, 403, 404, 409, 5xx y timeouts.

## Ejecución local

```bash
python -m venv .venv
```

Windows:

```powershell
.venv\Scripts\Activate.ps1
pip install -r requirements.txt
Copy-Item .env.example .env
flask --app run.py run --debug
```

Define `FLASK_SECRET_KEY` y confirma que `BACKEND_API_URL` apunte a Spring Boot.

## Pruebas

```bash
pytest -q
```

## Pendientes externos

El análisis y el historial requieren que Spring Boot y el motor de IA resuelvan la ruta y el contrato de respuesta. La ausencia de `GET /api/v1/perfil` impide consultar el perfil existente, pero no bloquea su creación.
</file>

<file path="frontend-flask/requirements.txt">
Flask==3.1.1
requests==2.32.4
python-dotenv==1.1.1
Flask-WTF==1.2.2
pytest==8.4.1
</file>

<file path="frontend-flask/run.py">
from app import create_app

app = create_app()

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug=app.config["DEBUG"])
</file>

<file path="backend/.mvn/wrapper/maven-wrapper.properties">
wrapperVersion=3.3.4
distributionType=only-script
distributionUrl=https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.9/apache-maven-3.9.9-bin.zip
</file>

<file path="backend/src/main/java/com/nocountry/financeai/config/.gitkeep">

</file>

<file path="backend/src/main/java/com/nocountry/financeai/config/CorsConfig.java">
package com.nocountry.financeai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/config/IniciarAdmin.java">
package com.nocountry.financeai.config;

import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.entity.enums.Rol;
import com.nocountry.financeai.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Configuration
@RequiredArgsConstructor
public class IniciarAdmin {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner crearAdministrador(){
        return args ->{
            String emailAdmin = "admin@financeai.com";
            if ( userRepository.existsByEmail(emailAdmin)){
                return;
            }
            UserEntity admin = UserEntity.builder()
                    .nombre("Administrador")
                    .apellido("FinanceAI")
                    .documento("ADMIN_001")
                    .email(emailAdmin)
                    .password(passwordEncoder.encode("FinanceAdmin2026*"))
                    .fechaNacimiento(LocalDate.of(1990, 1, 1))
                    .rol(Rol.ADMIN)
                    .activo(true)
                    .build();

            userRepository.save(admin);
        };
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/config/JwtConfig.java">
package com.nocountry.financeai.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {

    private String secret;
    private long expiration;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/config/RestClientConfig.java">
package com.nocountry.financeai.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {
    @Value("${ia.api.url}")
    private String iaApiUrl;
    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl(iaApiUrl)
                .build();
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/controller/.gitkeep">

</file>

<file path="backend/src/main/java/com/nocountry/financeai/controller/UserController.java">
package com.nocountry.financeai.controller;
import com.nocountry.financeai.dto.request.ChangePasswdRequest;
import com.nocountry.financeai.dto.request.UserRequest;
import com.nocountry.financeai.dto.response.UserResponse;
import com.nocountry.financeai.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
@Tag(
        name = "Usuarios",
        description = "Gestion del perfil de usuario"
)
public class UserController {
    private final UserService userService;

    @GetMapping("/miPerfil")
    public UserResponse obtenerMiPerfil(Authentication authentication) {

        return userService.obtenerMiPerfil(authentication.getName());
    }

    @PatchMapping("/miPerfil")
    public UserResponse actualizarMiPerfil(Authentication authentication, @Valid @RequestBody UserRequest userRequest) {
        return userService.actualizarMiPerfil(authentication.getName(), userRequest);
    }

    @PutMapping("/miPerfil/passwd")
    public ResponseEntity<Map<String, String>> cambiarPasswd(Authentication autenticacion, @Valid @RequestBody ChangePasswdRequest request){
        userService.cambiarPasswd(autenticacion.getName(), request);
        return ResponseEntity.ok(Map.of("message", "Se actualizor correctamente la contraseña"));
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/request/ChangePasswdRequest.java">
package com.nocountry.financeai.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record ChangePasswdRequest (
        @Schema(
                description = "Solicita contraseña actual",
                example = "abc123456"
        )
        @JsonProperty("current_password")
        @NotBlank(message = "Contranseña actual puede estar vacio")
        String currentPasswd,
        @Schema(
                description = "Solicita nueva clave",
                example = "ABC123*"
        )
        @NotBlank(message = "Nueva contraseña no puede estar vacio")
        String newPasswd,
        @Schema(
                description = "Solitia confirmar la nueva clave",
                example = "ABC123*"
        )
        @NotBlank(message = "Confirmar contraseña no puede estar vacio")
        String confirmPasswd
) {
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/request/PerfilFinancieroRequest.java">
package com.nocountry.financeai.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PerfilFinancieroRequest(
        @Schema(
                description = "Indica si el usuario tiene empleo formal (1) o no (0)",
                example = "1")
        @JsonProperty("empleo_formal")
        @NotNull(message = "Debe indicar si tiene empleo formal")
        @Min(value = 0, message = "El valor debe ser 0 o 1")
        Integer empleoFormal,

        @Schema(
                description = "Ingreso mensual del usuario",
                example = "3500.00")
        @JsonProperty("ingreso_mensual")
        @NotNull(message = "El ingreso mensual es obligatorio")
        @Positive(message = "El ingreso mensual debe ser mayor a cero")
        BigDecimal ingresoMensual,

        @Schema(
                description = "Línea de crédito disponible del usuario",
                example = "1000.00")
        @JsonProperty("linea_credito")
        @NotNull(message = "La línea de crédito es obligatoria")
        @DecimalMin(value = "0.0", inclusive = true, message = "La línea de crédito no puede ser negativa")
        BigDecimal lineaCredito
) {
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/response/UserResponse.java">
package com.nocountry.financeai.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.EstadoCivil;
import com.nocountry.financeai.entity.enums.Sexo;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record UserResponse(
        @Schema(
                description = "Nombre del usuario",
                example = "carlos"
        )
        String nombre,

        @Schema(
                description = "Apellido del usuario",
                example = "gomez"
        )
        String apellido,

        @Schema(
                description = "Documento de identificacion del usuario",
                example = "1022332456"
        )
        String documento,

        @Schema(
                description = "Email registrado por el usuario",
                example = "carlosgomez@nocountry.com"
        )
        String email,

        @Schema(
                description = "Fecha de nacimiento del usuario",
                example = "1996-05-31"
        )
        @JsonProperty("fecha_nacimiento")
        LocalDate fechaNacimiento,

        @Schema(
                description = "Estado civil del usuario",
                example = "viudo"
        )
        @JsonProperty("estado_civil")
        EstadoCivil estadoCivil,

        @Schema(
                description = "Genero del usuario",
                example = "masculino"
        )
        Sexo sexo,

        @Schema(
                description = "Cantidad de hijos que tiene el usuario",
                example = "2"
        )
        @JsonProperty("numero_hijos")
        Integer numeroHijos


) {
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/.gitkeep">

</file>

<file path="backend/src/main/java/com/nocountry/financeai/entity/enums/PerfilFinanciero.java">
package com.nocountry.financeai.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Resultado del perfil financiero despues del analisis",
        example = "SALUDABLE"
)
public enum PerfilFinanciero {
    SALUDABLE,
    EN_OBSERVACION,
    RIESGO;

    @JsonCreator
    public static PerfilFinanciero forString(String value) {
        return PerfilFinanciero.valueOf(value.trim().toUpperCase());
    }

    @JsonValue
    public String toValue(){
        return this.name().toLowerCase();
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/exception/.gitkeep">

</file>

<file path="backend/src/main/java/com/nocountry/financeai/exception/ResourceNotFoundException.java">
package com.nocountry.financeai.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/exception/UserAlreadyExistsException.java">
package com.nocountry.financeai.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/repository/.gitkeep">

</file>

<file path="backend/src/main/java/com/nocountry/financeai/repository/HistorialAnalisisRepository.java">
package com.nocountry.financeai.repository;

import com.nocountry.financeai.entity.HistorialAnalisisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialAnalisisRepository extends JpaRepository<HistorialAnalisisEntity, Long> {
    List<HistorialAnalisisEntity> findByUsuarioId(Long id);
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/repository/PerfilFinancieroRepository.java">
package com.nocountry.financeai.repository;

import com.nocountry.financeai.entity.PerfilFinancieroEntity;
import com.nocountry.financeai.entity.enums.PerfilFinanciero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilFinancieroRepository extends JpaRepository<PerfilFinancieroEntity, Long> {
    Optional<PerfilFinancieroEntity> findByUsuarioId(Long usuarioId);

}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/impl/PerfilFinancieroServiceImpl.java">
package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.dto.request.PerfilFinancieroRequest;
import com.nocountry.financeai.dto.response.PerfilFinancieroResponse;
import com.nocountry.financeai.entity.PerfilFinancieroEntity;
import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.exception.ResourceNotFoundException;
import com.nocountry.financeai.repository.PerfilFinancieroRepository;
import com.nocountry.financeai.repository.UserRepository;
import com.nocountry.financeai.service.PerfilFinancieroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfilFinancieroServiceImpl implements PerfilFinancieroService {
    private final PerfilFinancieroRepository perfilFinancieroRepository;
    private final UserRepository userRepository;

    @Override
    public PerfilFinancieroEntity obtenerPerfilPorUsuarioId(Long usuarioId) {
        return perfilFinancieroRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "El usuario no tiene perfil financiero"
                ));
    }

    @Override
    public PerfilFinancieroResponse crearPerfil(String email, PerfilFinancieroRequest request) {
        UserEntity usuario = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        if (perfilFinancieroRepository.findByUsuarioId(usuario.getId()).isPresent()) {
            throw new IllegalStateException("El usuario ya tiene un perfil financiero registrado");
        }

        PerfilFinancieroEntity perfil = PerfilFinancieroEntity.builder()
                .usuario(usuario)
                .empleoFormal(request.empleoFormal())
                .ingresoMensual(request.ingresoMensual())
                .lineaCredito(request.lineaCredito())
                .build();
        PerfilFinancieroEntity perfilGuardado = perfilFinancieroRepository.save(perfil);

        return new PerfilFinancieroResponse(
                perfilGuardado.getEmpleoFormal(),
                perfilGuardado.getIngresoMensual(),
                perfilGuardado.getLineaCredito()
        );
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/impl/UserServiceImpl.java">
package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.dto.request.ChangePasswdRequest;
import com.nocountry.financeai.dto.request.UserRequest;
import com.nocountry.financeai.dto.response.UserResponse;
import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.exception.ResourceNotFoundException;
import com.nocountry.financeai.repository.UserRepository;
import com.nocountry.financeai.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // metodos definidos en la interfaz

    @Override
    public List<UserResponse> obtenerUsuarios() {
        return userRepository.findAll()
                .stream()
                .map(this::convertirRespuesta)
                .toList();
    }

    @Override
    public UserResponse obtenerUsuarioPorDocumento(String documento) {
        UserEntity usuario = buscarUsuarioPorDocumento(documento);
        return convertirRespuesta(usuario);
    }

    @Override
    public UserResponse obtenerMiPerfil(String email) {
        UserEntity usuario = buscarUsuarioPorEmail(email);
        return convertirRespuesta(usuario);

    }

    @Override
    public void cambiarPasswd(String email, ChangePasswdRequest request) {
        UserEntity usuario = buscarUsuarioPorEmail(email);

        if(!passwordEncoder.matches(
                request.currentPasswd(),
                usuario.getPassword()
        )){
            throw new IllegalArgumentException("La contraseña actual es incorrecta");
        }
        if(!request.newPasswd().equals(request.confirmPasswd())){
            throw new IllegalArgumentException("La nueva contraseña no coincide");
        }

        usuario.setPassword(passwordEncoder.encode(request.newPasswd()));
        userRepository.save(usuario);
    }

    @Override
    public UserResponse actualizarMiPerfil(String email, UserRequest request) {
        UserEntity usuario = buscarUsuarioPorEmail(email);

        System.out.println(request.nombre());

        if(request.nombre() != null){
            usuario.setNombre(request.nombre());
        }
        if(request.apellido() != null){
            usuario.setApellido(request.apellido());
        }
        if(request.email() != null){
            usuario.setEmail(request.email());
        }
        if(request.estadoCivil() != null){
            usuario.setEstadoCivil(request.estadoCivil());
        }
        if(request.sexo() != null){
            usuario.setSexo(request.sexo());
        }
        if(request.numeroHijos() != null){
            usuario.setNumeroHijos(request.numeroHijos());
        }

        UserEntity usuarioActualizado = userRepository.save(usuario);

        return convertirRespuesta(usuarioActualizado);
    }

    // metodos privados de la clase

    private UserEntity buscarUsuarioPorEmail(String email) {
        return  userRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException(
                        "Usuario no encontrado"
                ));
    }

    private UserEntity buscarUsuarioPorDocumento(String documento){
        UserEntity usuario = userRepository.findByDocumento(documento)
                .orElseThrow(()-> new ResourceNotFoundException("Usuario no encontrado")
                );
        return usuario;
    }

    private UserResponse convertirRespuesta(UserEntity usuario) {
        return new UserResponse(
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getDocumento(),
                usuario.getEmail(),
                usuario.getFechaNacimiento(),
                usuario.getEstadoCivil(),
                usuario.getSexo(),
                usuario.getNumeroHijos()
        );
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/.gitkeep">

</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/PerfilFinancieroService.java">
package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.request.PerfilFinancieroRequest;
import com.nocountry.financeai.dto.response.PerfilFinancieroResponse;
import com.nocountry.financeai.entity.PerfilFinancieroEntity;

public interface PerfilFinancieroService {
    PerfilFinancieroEntity obtenerPerfilPorUsuarioId(Long usuarioId);

    PerfilFinancieroResponse crearPerfil(String email, PerfilFinancieroRequest request);
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/UserService.java">
package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.request.ChangePasswdRequest;
import com.nocountry.financeai.dto.request.UserRequest;
import com.nocountry.financeai.dto.response.UserResponse;

import java.util.List;

public interface UserService  {
    // Lista los todos los usuarios
    List<UserResponse> obtenerUsuarios();

    //  Obtiene usuario por documento
    UserResponse obtenerUsuarioPorDocumento(String documento);

    //obtiene el perlfil del usuario autenticado
    UserResponse obtenerMiPerfil(String email);

    // Actualiza datos del usuario
    UserResponse actualizarMiPerfil(String email, UserRequest userRequest);

    // Actuliza contraseña de usuaria
    void cambiarPasswd(String email, ChangePasswdRequest changePasswdRequest);


}
</file>

<file path="backend/src/main/resources/db/migration/V4__create_perfil_Financiero_table.sql">
CREATE TABLE perfil_financiero (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL UNIQUE,
    empleo_formal INTEGER,
    ingreso_mensual DECIMAL(12,2),
    linea_credito DECIMAL(12,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_perfil_usuario
    FOREIGN KEY (usuario_id)
    REFERENCES usuarios(id)
);
</file>

<file path="backend/src/main/resources/db/migration/V6__add_documento_to_usuarios.sql">
-- V6: Agrega la columna 'documento' a la tabla usuarios.
-- Requerida por UserEntity.documento, RegisterRequest.documento,
-- UserRepository.findByDocumento/existsByDocumento e IniciarAdmin.
-- No se toca V1 (ya pudo haberse aplicado en otros ambientes).
--
-- Se agrega primero sin restricciones para no romper filas ya existentes
-- (ej. si el admin de IniciarAdmin llegó a crearse antes de este fix),
-- se rellena con un valor temporal único por fila, y recién ahí se
-- aplican NOT NULL y UNIQUE.

ALTER TABLE usuarios
    ADD COLUMN documento VARCHAR(30);

UPDATE usuarios
SET documento = 'TEMP_' || id
WHERE documento IS NULL;

ALTER TABLE usuarios
    ALTER COLUMN documento SET NOT NULL;

ALTER TABLE usuarios
    ADD CONSTRAINT uk_usuarios_documento UNIQUE (documento);
</file>

<file path="backend/src/test/java/com/nocountry/financeai/FinanceaiApplicationTests.java">
package com.nocountry.financeai;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FinanceaiApplicationTests {

    @Test
    void contextLoads() {
        // Intencionadamente vacío: Este test sirve exclusivamente para verificar
        // que el contexto de Spring Boot se inicialice correctamente.
    }

}
</file>

<file path="backend/HELP.md">
# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/4.1.0/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/4.1.0/maven-plugin/build-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/4.1.0/reference/web/servlet.html)
* [Validation](https://docs.spring.io/spring-boot/4.1.0/reference/io/validation.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/4.1.0/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Flyway Migration](https://docs.spring.io/spring-boot/4.1.0/how-to/data-initialization.html#howto.data-initialization.migration-tool.flyway)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.
</file>

<file path="backend/mvnw">
#!/bin/sh
# ----------------------------------------------------------------------------
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
# ----------------------------------------------------------------------------

# ----------------------------------------------------------------------------
# Apache Maven Wrapper startup batch script, version 3.3.4
#
# Optional ENV vars
# -----------------
#   JAVA_HOME - location of a JDK home dir, required when download maven via java source
#   MVNW_REPOURL - repo url base for downloading maven distribution
#   MVNW_USERNAME/MVNW_PASSWORD - user and password for downloading maven
#   MVNW_VERBOSE - true: enable verbose log; debug: trace the mvnw script; others: silence the output
# ----------------------------------------------------------------------------

set -euf
[ "${MVNW_VERBOSE-}" != debug ] || set -x

# OS specific support.
native_path() { printf %s\\n "$1"; }
case "$(uname)" in
CYGWIN* | MINGW*)
  [ -z "${JAVA_HOME-}" ] || JAVA_HOME="$(cygpath --unix "$JAVA_HOME")"
  native_path() { cygpath --path --windows "$1"; }
  ;;
esac

# set JAVACMD and JAVACCMD
set_java_home() {
  # For Cygwin and MinGW, ensure paths are in Unix format before anything is touched
  if [ -n "${JAVA_HOME-}" ]; then
    if [ -x "$JAVA_HOME/jre/sh/java" ]; then
      # IBM's JDK on AIX uses strange locations for the executables
      JAVACMD="$JAVA_HOME/jre/sh/java"
      JAVACCMD="$JAVA_HOME/jre/sh/javac"
    else
      JAVACMD="$JAVA_HOME/bin/java"
      JAVACCMD="$JAVA_HOME/bin/javac"

      if [ ! -x "$JAVACMD" ] || [ ! -x "$JAVACCMD" ]; then
        echo "The JAVA_HOME environment variable is not defined correctly, so mvnw cannot run." >&2
        echo "JAVA_HOME is set to \"$JAVA_HOME\", but \"\$JAVA_HOME/bin/java\" or \"\$JAVA_HOME/bin/javac\" does not exist." >&2
        return 1
      fi
    fi
  else
    JAVACMD="$(
      'set' +e
      'unset' -f command 2>/dev/null
      'command' -v java
    )" || :
    JAVACCMD="$(
      'set' +e
      'unset' -f command 2>/dev/null
      'command' -v javac
    )" || :

    if [ ! -x "${JAVACMD-}" ] || [ ! -x "${JAVACCMD-}" ]; then
      echo "The java/javac command does not exist in PATH nor is JAVA_HOME set, so mvnw cannot run." >&2
      return 1
    fi
  fi
}

# hash string like Java String::hashCode
hash_string() {
  str="${1:-}" h=0
  while [ -n "$str" ]; do
    char="${str%"${str#?}"}"
    h=$(((h * 31 + $(LC_CTYPE=C printf %d "'$char")) % 4294967296))
    str="${str#?}"
  done
  printf %x\\n $h
}

verbose() { :; }
[ "${MVNW_VERBOSE-}" != true ] || verbose() { printf %s\\n "${1-}"; }

die() {
  printf %s\\n "$1" >&2
  exit 1
}

trim() {
  # MWRAPPER-139:
  #   Trims trailing and leading whitespace, carriage returns, tabs, and linefeeds.
  #   Needed for removing poorly interpreted newline sequences when running in more
  #   exotic environments such as mingw bash on Windows.
  printf "%s" "${1}" | tr -d '[:space:]'
}

scriptDir="$(dirname "$0")"
scriptName="$(basename "$0")"

# parse distributionUrl and optional distributionSha256Sum, requires .mvn/wrapper/maven-wrapper.properties
while IFS="=" read -r key value; do
  case "${key-}" in
  distributionUrl) distributionUrl=$(trim "${value-}") ;;
  distributionSha256Sum) distributionSha256Sum=$(trim "${value-}") ;;
  esac
done <"$scriptDir/.mvn/wrapper/maven-wrapper.properties"
[ -n "${distributionUrl-}" ] || die "cannot read distributionUrl property in $scriptDir/.mvn/wrapper/maven-wrapper.properties"

case "${distributionUrl##*/}" in
maven-mvnd-*bin.*)
  MVN_CMD=mvnd.sh _MVNW_REPO_PATTERN=/maven/mvnd/
  case "${PROCESSOR_ARCHITECTURE-}${PROCESSOR_ARCHITEW6432-}:$(uname -a)" in
  *AMD64:CYGWIN* | *AMD64:MINGW*) distributionPlatform=windows-amd64 ;;
  :Darwin*x86_64) distributionPlatform=darwin-amd64 ;;
  :Darwin*arm64) distributionPlatform=darwin-aarch64 ;;
  :Linux*x86_64*) distributionPlatform=linux-amd64 ;;
  *)
    echo "Cannot detect native platform for mvnd on $(uname)-$(uname -m), use pure java version" >&2
    distributionPlatform=linux-amd64
    ;;
  esac
  distributionUrl="${distributionUrl%-bin.*}-$distributionPlatform.zip"
  ;;
maven-mvnd-*) MVN_CMD=mvnd.sh _MVNW_REPO_PATTERN=/maven/mvnd/ ;;
*) MVN_CMD="mvn${scriptName#mvnw}" _MVNW_REPO_PATTERN=/org/apache/maven/ ;;
esac

# apply MVNW_REPOURL and calculate MAVEN_HOME
# maven home pattern: ~/.m2/wrapper/dists/{apache-maven-<version>,maven-mvnd-<version>-<platform>}/<hash>
[ -z "${MVNW_REPOURL-}" ] || distributionUrl="$MVNW_REPOURL$_MVNW_REPO_PATTERN${distributionUrl#*"$_MVNW_REPO_PATTERN"}"
distributionUrlName="${distributionUrl##*/}"
distributionUrlNameMain="${distributionUrlName%.*}"
distributionUrlNameMain="${distributionUrlNameMain%-bin}"
MAVEN_USER_HOME="${MAVEN_USER_HOME:-${HOME}/.m2}"
MAVEN_HOME="${MAVEN_USER_HOME}/wrapper/dists/${distributionUrlNameMain-}/$(hash_string "$distributionUrl")"

exec_maven() {
  unset MVNW_VERBOSE MVNW_USERNAME MVNW_PASSWORD MVNW_REPOURL || :
  exec "$MAVEN_HOME/bin/$MVN_CMD" "$@" || die "cannot exec $MAVEN_HOME/bin/$MVN_CMD"
}

if [ -d "$MAVEN_HOME" ]; then
  verbose "found existing MAVEN_HOME at $MAVEN_HOME"
  exec_maven "$@"
fi

case "${distributionUrl-}" in
*?-bin.zip | *?maven-mvnd-?*-?*.zip) ;;
*) die "distributionUrl is not valid, must match *-bin.zip or maven-mvnd-*.zip, but found '${distributionUrl-}'" ;;
esac

# prepare tmp dir
if TMP_DOWNLOAD_DIR="$(mktemp -d)" && [ -d "$TMP_DOWNLOAD_DIR" ]; then
  clean() { rm -rf -- "$TMP_DOWNLOAD_DIR"; }
  trap clean HUP INT TERM EXIT
else
  die "cannot create temp dir"
fi

mkdir -p -- "${MAVEN_HOME%/*}"

# Download and Install Apache Maven
verbose "Couldn't find MAVEN_HOME, downloading and installing it ..."
verbose "Downloading from: $distributionUrl"
verbose "Downloading to: $TMP_DOWNLOAD_DIR/$distributionUrlName"

# select .zip or .tar.gz
if ! command -v unzip >/dev/null; then
  distributionUrl="${distributionUrl%.zip}.tar.gz"
  distributionUrlName="${distributionUrl##*/}"
fi

# verbose opt
__MVNW_QUIET_WGET=--quiet __MVNW_QUIET_CURL=--silent __MVNW_QUIET_UNZIP=-q __MVNW_QUIET_TAR=''
[ "${MVNW_VERBOSE-}" != true ] || __MVNW_QUIET_WGET='' __MVNW_QUIET_CURL='' __MVNW_QUIET_UNZIP='' __MVNW_QUIET_TAR=v

# normalize http auth
case "${MVNW_PASSWORD:+has-password}" in
'') MVNW_USERNAME='' MVNW_PASSWORD='' ;;
has-password) [ -n "${MVNW_USERNAME-}" ] || MVNW_USERNAME='' MVNW_PASSWORD='' ;;
esac

if [ -z "${MVNW_USERNAME-}" ] && command -v wget >/dev/null; then
  verbose "Found wget ... using wget"
  wget ${__MVNW_QUIET_WGET:+"$__MVNW_QUIET_WGET"} "$distributionUrl" -O "$TMP_DOWNLOAD_DIR/$distributionUrlName" || die "wget: Failed to fetch $distributionUrl"
elif [ -z "${MVNW_USERNAME-}" ] && command -v curl >/dev/null; then
  verbose "Found curl ... using curl"
  curl ${__MVNW_QUIET_CURL:+"$__MVNW_QUIET_CURL"} -f -L -o "$TMP_DOWNLOAD_DIR/$distributionUrlName" "$distributionUrl" || die "curl: Failed to fetch $distributionUrl"
elif set_java_home; then
  verbose "Falling back to use Java to download"
  javaSource="$TMP_DOWNLOAD_DIR/Downloader.java"
  targetZip="$TMP_DOWNLOAD_DIR/$distributionUrlName"
  cat >"$javaSource" <<-END
	public class Downloader extends java.net.Authenticator
	{
	  protected java.net.PasswordAuthentication getPasswordAuthentication()
	  {
	    return new java.net.PasswordAuthentication( System.getenv( "MVNW_USERNAME" ), System.getenv( "MVNW_PASSWORD" ).toCharArray() );
	  }
	  public static void main( String[] args ) throws Exception
	  {
	    setDefault( new Downloader() );
	    java.nio.file.Files.copy( java.net.URI.create( args[0] ).toURL().openStream(), java.nio.file.Paths.get( args[1] ).toAbsolutePath().normalize() );
	  }
	}
	END
  # For Cygwin/MinGW, switch paths to Windows format before running javac and java
  verbose " - Compiling Downloader.java ..."
  "$(native_path "$JAVACCMD")" "$(native_path "$javaSource")" || die "Failed to compile Downloader.java"
  verbose " - Running Downloader.java ..."
  "$(native_path "$JAVACMD")" -cp "$(native_path "$TMP_DOWNLOAD_DIR")" Downloader "$distributionUrl" "$(native_path "$targetZip")"
fi

# If specified, validate the SHA-256 sum of the Maven distribution zip file
if [ -n "${distributionSha256Sum-}" ]; then
  distributionSha256Result=false
  if [ "$MVN_CMD" = mvnd.sh ]; then
    echo "Checksum validation is not supported for maven-mvnd." >&2
    echo "Please disable validation by removing 'distributionSha256Sum' from your maven-wrapper.properties." >&2
    exit 1
  elif command -v sha256sum >/dev/null; then
    if echo "$distributionSha256Sum  $TMP_DOWNLOAD_DIR/$distributionUrlName" | sha256sum -c - >/dev/null 2>&1; then
      distributionSha256Result=true
    fi
  elif command -v shasum >/dev/null; then
    if echo "$distributionSha256Sum  $TMP_DOWNLOAD_DIR/$distributionUrlName" | shasum -a 256 -c >/dev/null 2>&1; then
      distributionSha256Result=true
    fi
  else
    echo "Checksum validation was requested but neither 'sha256sum' or 'shasum' are available." >&2
    echo "Please install either command, or disable validation by removing 'distributionSha256Sum' from your maven-wrapper.properties." >&2
    exit 1
  fi
  if [ $distributionSha256Result = false ]; then
    echo "Error: Failed to validate Maven distribution SHA-256, your Maven distribution might be compromised." >&2
    echo "If you updated your Maven version, you need to update the specified distributionSha256Sum property." >&2
    exit 1
  fi
fi

# unzip and move
if command -v unzip >/dev/null; then
  unzip ${__MVNW_QUIET_UNZIP:+"$__MVNW_QUIET_UNZIP"} "$TMP_DOWNLOAD_DIR/$distributionUrlName" -d "$TMP_DOWNLOAD_DIR" || die "failed to unzip"
else
  tar xzf${__MVNW_QUIET_TAR:+"$__MVNW_QUIET_TAR"} "$TMP_DOWNLOAD_DIR/$distributionUrlName" -C "$TMP_DOWNLOAD_DIR" || die "failed to untar"
fi

# Find the actual extracted directory name (handles snapshots where filename != directory name)
actualDistributionDir=""

# First try the expected directory name (for regular distributions)
if [ -d "$TMP_DOWNLOAD_DIR/$distributionUrlNameMain" ]; then
  if [ -f "$TMP_DOWNLOAD_DIR/$distributionUrlNameMain/bin/$MVN_CMD" ]; then
    actualDistributionDir="$distributionUrlNameMain"
  fi
fi

# If not found, search for any directory with the Maven executable (for snapshots)
if [ -z "$actualDistributionDir" ]; then
  # enable globbing to iterate over items
  set +f
  for dir in "$TMP_DOWNLOAD_DIR"/*; do
    if [ -d "$dir" ]; then
      if [ -f "$dir/bin/$MVN_CMD" ]; then
        actualDistributionDir="$(basename "$dir")"
        break
      fi
    fi
  done
  set -f
fi

if [ -z "$actualDistributionDir" ]; then
  verbose "Contents of $TMP_DOWNLOAD_DIR:"
  verbose "$(ls -la "$TMP_DOWNLOAD_DIR")"
  die "Could not find Maven distribution directory in extracted archive"
fi

verbose "Found extracted Maven distribution directory: $actualDistributionDir"
printf %s\\n "$distributionUrl" >"$TMP_DOWNLOAD_DIR/$actualDistributionDir/mvnw.url"
mv -- "$TMP_DOWNLOAD_DIR/$actualDistributionDir" "$MAVEN_HOME" || [ -d "$MAVEN_HOME" ] || die "fail to move MAVEN_HOME"

clean || :
exec_maven "$@"
</file>

<file path="data-science/modeloFinanceAI/Dockerfile">
FROM python:3.11-slim

WORKDIR /app

COPY . .

RUN pip install --no-cache-dir -r requirements.txt

EXPOSE 8000

CMD ["uvicorn","main:app","--host","0.0.0.0","--port","8000"]
</file>

<file path="data-science/main.py">
from fastapi import FastAPI, HTTPException, status
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel, Field
from typing import List, Dict
from contextlib import asynccontextmanager
import pandas as pd
import numpy as np
import joblib
import sklearn
import sklearn.compose._column_transformer

# ==============================================================================
# 1. PARCHE DE COMPATIBILIDAD SKLEARN
# ==============================================================================
if not hasattr(sklearn.compose._column_transformer, '_RemainderColsList'):
    class _RemainderColsList(list):
        pass
    sklearn.compose._column_transformer._RemainderColsList = _RemainderColsList

# ==============================================================================
# 2. CARGA SEGURA DE MODELOS (LIFESPAN)
# ==============================================================================
modelos = {}

@asynccontextmanager
async def lifespan(app: FastAPI):
    # Proceso de arranque (Startup)
    try:
        modelos['transacciones'] = joblib.load('modelo_clasificacion_transacciones.pkl')
        modelos['perfil'] = joblib.load('modelo_perfil_financiero.pkl')
        print("✅ [PROD] Modelos ML cargados exitosamente.")
    except Exception as e:
        print(f"❌ [ERROR CRÍTICO] Fallo al cargar modelos .pkl: {e}")
        raise RuntimeError(f"No se pudieron cargar los modelos en producción: {e}")
    yield
    # Proceso de apagado (Shutdown)
    modelos.clear()

# ==============================================================================
# 3. CREAR LA APLICACIÓN FASTAPI
# ==============================================================================
app = FastAPI(
    title="API Analítica Financiera",
    version="1.0.0",
    lifespan=lifespan
)

# Configuración de CORS para producción / Oracle Cloud
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # En prod estricto, reemplaza "*" por la IP/Dominio de tu Frontend
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# ==============================================================================
# 4. ESTRUCTURA DE DATOS DE ENTRADA (Pydantic Models)
# ==============================================================================
class TransaccionInput(BaseModel):
    nombre_comercio: str = Field(
        ..., 
        example="Uber", 
        description="Nombre del establecimiento o comercio"
    )
    monto_transaccion: float = Field(
        ..., 
        gt=0, 
        example=250.0, 
        description="Monto de la transacción (debe ser mayor a 0)"
    )
    medio_pago: str = Field(
        ..., 
        example="credito", 
        description="Medios aceptados: credito, debito, transaccion, efectivo"
    )

class EntradaUsuario(BaseModel):
    edad: int = Field(..., ge=18, le=120)
    sexo: str
    estado_civil: str
    numero_hijos: int = Field(..., ge=0)
    empleo_formal: int = Field(..., ge=0, le=1)
    ingreso_mensual: float = Field(..., ge=0)
    linea_credito: float = Field(..., ge=0)
    transacciones: List[TransaccionInput] = []

# ==============================================================================
# 5. ENDPOINTS DE PRODUCCIÓN
# ==============================================================================

@app.get("/health", status_code=status.HTTP_200_OK)
def health_check():
    """Endpoint para que Oracle Cloud / Docker verifique si la API está viva"""
    if 'transacciones' not in modelos or 'perfil' not in modelos:
        raise HTTPException(status_code=500, detail="Modelos no inicializados")
    return {"status": "ok", "models_loaded": True}

@app.post("/analisis-financiero")
def analizar_usuario(datos: EntradaUsuario):
    try:
        modelo_perfil = modelos.get('perfil')
        modelo_transacciones = modelos.get('transacciones')

        # ----------------------------------------------------------------------
        # A) CÁLCULO DE GASTOS Y MÉTRICAS FINANCIERAS
        # ----------------------------------------------------------------------
        gasto_total = 0.0
        if datos.transacciones:
            gasto_total = sum([float(tx.monto_transaccion) for tx in datos.transacciones])

        # 1. Nivel de Endeudamiento (escala float 0.0 a 1.0)
        denom_endeudamiento = datos.ingreso_mensual + datos.linea_credito
        if denom_endeudamiento > 0:
            nivel_endeudamiento = round(float(gasto_total / denom_endeudamiento), 2)
        else:
            nivel_endeudamiento = 0.0

        # 2. Rango de Ahorro (String)
        if datos.ingreso_mensual > 0:
            ahorro_bruto = max(datos.ingreso_mensual - gasto_total, 0.0)
            pct_ahorro = ahorro_bruto / datos.ingreso_mensual
        else:
            pct_ahorro = 0.0

        if pct_ahorro >= 0.40:
            rango_ahorro_str = "Alta"
        elif pct_ahorro >= 0.20:
            rango_ahorro_str = "Media"
        elif pct_ahorro > 0:
            rango_ahorro_str = "Baja"
        else:
            rango_ahorro_str = "Ninguna"

# ----------------------------------------------------------------------
        # B) PREDICCIÓN CON MODELO DE PERFIL (.pkl)
        # ----------------------------------------------------------------------
        df_cliente = pd.DataFrame([{
            'edad': int(datos.edad),
            'sexo': str(datos.sexo).lower().strip(),
            'estado_civil': str(datos.estado_civil).lower().strip(),
            'numero_hijos': int(datos.numero_hijos),
            'empleo_formal': int(datos.empleo_formal),
            'ingreso_mensual': float(datos.ingreso_mensual),
            'linea_credito': float(datos.linea_credito),
            'nivel_endeudamiento': float(nivel_endeudamiento),
            'rango_ahorro': float(pct_ahorro)  # Valor decimal menor a 1
        }])

        perfil_pred = modelo_perfil.predict(df_cliente)[0]
        perfil_str = str(perfil_pred).upper().replace(" ", "_")

        # Inicializamos la probabilidad por defecto por seguridad
        probabilidad = 0.85
        try:
            if hasattr(modelo_perfil, "predict_proba"):
                probs = modelo_perfil.predict_proba(df_cliente)[0]
                probabilidad = round(float(np.max(probs)), 2)
        except Exception:
            probabilidad = 0.85

        # ----------------------------------------------------------------------
        # C) CLASIFICACIÓN NLP DE TRANSACCIONES
        # ----------------------------------------------------------------------
        resumen_gastos: Dict[str, float] = {}
        
        if datos.transacciones and len(datos.transacciones) > 0:
            df_tx = pd.DataFrame([
                {
                    'nombre_comercio': str(t.nombre_comercio).lower().strip(),
                    'monto_transaccion': float(t.monto_transaccion)
                }
                for t in datos.transacciones
            ])
            
            # Evaluación defensiva de probabilidades o predicción directa
            try:
                probs_matriz = modelo_transacciones.predict_proba(df_tx)
                clases = modelo_transacciones.classes_
                categorias_finales = []

                for probs in probs_matriz:
                    prob_max = float(np.max(probs))
                    idx_max = int(np.argmax(probs))
                    
                    # Umbral de confianza al 60%
                    if prob_max <= 0.60:
                        categorias_finales.append("otros servicios")
                    else:
                        categorias_finales.append(str(clases[idx_max]))
                
                df_tx['categoria'] = categorias_finales
            except Exception:
                # Si el modelo no soporta predict_proba, realiza la predicción directa
                preds = modelo_transacciones.predict(df_tx)
                df_tx['categoria'] = [str(p) for p in preds]
            
            # Agrupar montos por categoría
            agrupar = df_tx.groupby('categoria')['monto_transaccion'].sum().to_dict()
            resumen_gastos = {str(k).lower(): round(float(v), 2) for k, v in agrupar.items()}

        # ----------------------------------------------------------------------
        # D) GENERACIÓN DE RECOMENDACIONES
        # ----------------------------------------------------------------------
        recomendaciones = []

        if perfil_str == "RIESGOSO" and datos.linea_credito > datos.ingreso_mensual:
            recomendaciones.append(
                "Para aumentar el score del perfil financiero, se recomienda reducir el gasto o incrementar el ingreso mensual"
            )

        if "entretenimiento" in resumen_gastos and resumen_gastos["entretenimiento"] > (datos.ingreso_mensual * 0.15):
            recomendaciones.append("Monitorear los gastos recurrentes de entretenimiento.")

        if nivel_endeudamiento > 0.50:
            recomendaciones.append("Reducir las gastos para bajar el nivel de endeudamiento.")

        if not recomendaciones:
            recomendaciones.append("Mantener los hábitos de gasto actuales y continuar monitoreando el presupuesto.")

        # ----------------------------------------------------------------------
        # E) SALIDA EN FORMATO ESTRICTO
        # ----------------------------------------------------------------------
        return {
            "perfilFinanciero": perfil_str,
            "probabilidad": probabilidad,
            "nivel_endeudamiento": nivel_endeudamiento,
            "rango_ahorro": rango_ahorro_str,
            "resumenGastos": resumen_gastos,
            "recomendaciones": recomendaciones
        }

    except Exception as e:
        raise HTTPException(
            status_code=status.HTTP_500_INTERNAL_SERVER_ERROR,
            detail=f"Error interno en la inferencia del modelo: {str(e)}"
        )

####http://localhost:8000/docs####
</file>

<file path="data-science/README.md">
# Data Science
</file>

<file path="frontend/css/style.css">
body {
    background-color: #f8f9fa;
}

.card {
    border: none;
    box-shadow: 0 4px 6px rgba(0,0,0,0.1);
}

.badge-Saludable {
    background-color: #198754;
}

.badge-Observacion {
    background-color: #ffc107;
    color: black;
}

.badge-Riesgo {
    background-color: #dc3545;
}
</file>

<file path="notamaestra_financeai_v4.md">
# FinanceAI
## Nota Maestra del Proyecto
*Documentación Técnica, Auditoría de Código y Hoja de Ruta — Documento único de referencia para el equipo y para asistentes de IA*

**Versión 4 — Actualizado: 08 de agosto de 2026**
Stack objetivo: Java 21 + Spring Boot 4.1.0
Basado en re-auditoría **verificada línea por línea con herramientas** (no por lectura narrativa) contra `financeai.md`
*Proyecto Hackathon No Country / ONE (Oracle Next Education – Alura)*
*Este documento reemplaza a la v3 (08 de agosto de 2026) como fuente única de verdad. La v3 contenía dos errores de auditoría que se corrigen aquí — ver Sección 0.1.*

---

## 0. Cómo Usar Este Documento
Fuente única de verdad del proyecto FinanceAI. Convención de identificadores sin cambios: **AUD-XX** para hallazgos técnicos (Sección 6), **TASK-XXX** para tareas de backlog (Sección 10). Se conservan AUD-01 a AUD-22 y TASK-001 a TASK-037 de versiones anteriores; los hallazgos y tareas nuevos de esta re-auditoría continúan desde **AUD-23 / TASK-038**.

**Estados:** 🟢/✅ Completado · 🟡 Parcial / con deuda técnica · 🔴 Pendiente · ⚠️ Bloqueado o riesgoso.

### 0.1 ⚠️ Corrección de Método (léase antes que el resto del documento)
La v3 de este documento se generó leyendo el snapshot `financeai.md` de forma narrativa. Ese documento mezcla, en un solo archivo, el **código real** con una **copia incrustada del `notamaestra_financeai.md` anterior** (líneas 3308–3764 del snapshot), que contiene prosa describiendo versiones *pasadas* del código. La v3 confundió dos afirmaciones de esa prosa incrustada con el estado real del código:

| Afirmación de la v3 | Realidad verificada con `grep`/`sed` sobre el archivo real |
| :--- | :--- |
| "`frontend/dashboard.html` sigue sin existir" | **Falso.** El archivo existe (`<file path="frontend/dashboard.html">` en el snapshot) y ya no da 404. |
| "El formulario de registro ya no captura los campos de perfil financiero; `auth.js` no llama a `/perfil`" | **Falso.** `index.html` conserva la sección "Perfil Financiero Inicial", y `auth.js` sí encadena `POST /perfil` tras un registro exitoso. |

Esta v4 se construyó extrayendo cada archivo relevante por su ruta exacta (`sed -n '/<file path="...">/,/<\/file>/p'`) y comparando el contenido real, no descripciones sobre él. El resultado es una imagen más precisa: **algunos hallazgos que la v3 daba por abiertos están en realidad casi resueltos, y aparecen 4 hallazgos nuevos, muy concretos, que la lectura narrativa no había detectado** — todos con el mismo patrón de causa raíz (ver AUD-23).

### 0.2 Qué Cambió Desde la v3 (Resumen Ejecutivo, corregido)
* **AUD-18 se reclasifica de "🟡 archivo faltante" a "🟡 archivo huérfano".** `dashboard.html` existe, pero es un stub aislado: no carga `js/api.js` ni `js/dashboard.js` (confirmado: en todo el repositorio sólo hay dos `<script src>`, ambos en `index.html` — Bootstrap y `auth.js`). La funcionalidad real (tabla de transacciones, botón de análisis, modal de perfil) vive en `dashboard.js`, que ningún HTML del repo carga.
* **AUD-19 se reclasifica de "🔴 no se intenta" a "🟡 se intenta, pero muy probablemente falla antes de llegar al perfil".** El flujo está correctamente encadenado en `auth.js` (registro → `POST /perfil`), pero `RegisterRequest.java` exige las claves JSON `fecha_nacimiento`, `estado_civil`, `numero_hijos` (vía `@JsonProperty`) y `auth.js` envía `fechaNacimiento`, `estadoCivil`, `numeroHijos` (camelCase). Sin una estrategia de *naming* global, Jackson no completa esos campos, `@NotNull` los rechaza, y el registro devuelve 400 **antes** de que el flujo llegue siquiera a crear el perfil.
* **AUD-03 se reclasifica de "🔴 desalineado" a "🟡 rutas correctas, casing roto en dos puntos".** Verificado: `dashboard.js` ya llama a `/transacciones/usuario/transacciones`, `/analisis/predict` y `/perfil` — las rutas correctas del backend real. El payload de alta de transacción también es correcto (`nombre_comercio`, `monto_transaccion`, `medio_pago`, snake_case). Pero aparecen dos bugs puntuales nuevos: la tabla de transacciones nunca muestra datos reales (AUD-24) y el modal de perfil de `dashboard.js` tiene el mismo problema de casing que el registro (parte de AUD-23).
* **4 hallazgos nuevos** (AUD-23 a AUD-26), todos en el frontend, todos de bajo costo de arreglo individual pero de alto impacto acumulado porque bloquean el camino crítico completo.
* **Sin cambios:** AUD-02, AUD-05, AUD-13, AUD-15, AUD-16, AUD-17, AUD-22 — re-verificados, se mantiene su estado de la v3.

**Balance v4:** 26 hallazgos totales (AUD-01 a AUD-26). **12 resueltos · 6 parciales · 8 abiertos.**

**Conclusión para el PM:** el proyecto está genuinamente más cerca de una demo que lo que decía la v3. Los 4 hallazgos nuevos (AUD-23 a AUD-26) son, en conjunto, **menos trabajo que un solo `TASK-018` de la v2** — son correcciones de una o dos líneas cada una, concentradas casi todas en `dashboard.js`. Recomiendo tratarlas como el verdadero P0 de esta versión: son la diferencia entre "casi funciona" y "funciona".

---

## 1. Visión General del Proyecto
Sin cambios respecto a versiones anteriores (descripción, objetivos del MVP, equipo, stack). Ver v3/v2, Sección 1.

---

## 2. Arquitectura Real del Sistema
### 2.1 Módulos del Monorepo (actualizado v4)
| Módulo | Estado v4 | Nota |
| :--- | :--- | :--- |
| `backend/` | 🟡 Backend sólido, deuda puntual | `AuthResponse`, `application.yml`, `V5`, `Sexo`, `pom.xml` resueltos. `JwtUtil` y `IAClient` con deuda puntual (AUD-13, AUD-15). |
| `mock-api/` | ⚠️ Obsoleto, no conectado | Sin cambios. |
| `data-science/modeloFinanceAI/` | 🟡 Motor canónico, aún inalcanzable | `docker-compose` ya lo declara canónico (`depends_on`), pero `IAClient` sigue apuntando a `/predict` (AUD-15) y el contrato de salida sigue incompatible (AUD-16). |
| `data-science/` (raíz) | 🟡 Copia duplicada | Sin cambios (AUD-17). |
| `frontend/` | 🟡 Todas las piezas existen, pero están desconectadas entre sí | `index.html`, `dashboard.html`, `auth.js`, `dashboard.js`, `api.js` existen y en su mayoría apuntan a las rutas correctas del backend — pero `dashboard.html` no carga los dos scripts que implementan la funcionalidad real (AUD-18), y persisten mismatches de *casing* en 3 payloads y 1 respuesta (AUD-23, AUD-24). |

### 2.2 Flujo de Comunicación
Sin cambios respecto a v3 (ver v3, Sección 2.2): `IA_API_URL` ya está externalizada y `depends_on` apunta a `modelo-financeai`, pero `IAClient.java` sigue con `.uri("/predict")` hardcodeado. AUD-15 sigue parcial.

### 2.3 Estructura de Paquetes del Backend
Sin cambios estructurales. Ver v2, Sección 2.3.

---

## 3. Contrato de API: Objetivo vs. Estado Actual (corregido)
La tabla de la v2/v3 quedó desactualizada — se reconstruye aquí a partir del código real verificado:

| Aspecto | Backend real (Java) | Frontend real (`dashboard.js` / `auth.js`) | Estado |
| :--- | :--- | :--- | :--- |
| Ruta transacciones (GET/POST) | `/api/v1/transacciones/usuario/transacciones` | `/transacciones/usuario/transacciones` (`BASE_URL` = `.../api/v1`) | ✅ Coincide |
| Ruta análisis | `POST /api/v1/analisis/predict` | `POST /analisis/predict` | ✅ Coincide |
| Ruta perfil | `POST /api/v1/perfil` | `POST /perfil` (desde `auth.js` y desde el modal de `dashboard.js`) | ✅ Coincide |
| Payload alta transacción | `{ nombre_comercio, monto_transaccion, medio_pago }` | `{ nombre_comercio, monto_transaccion, medio_pago }` | ✅ Coincide |
| Payload registro | `{ nombre, apellido, email, password, fecha_nacimiento, sexo, estado_civil, numero_hijos }` | `{ nombre, apellido, email, password, fechaNacimiento, sexo, estadoCivil, numeroHijos }` | 🔴 **3 claves en camelCase, backend espera snake_case (AUD-23)** |
| Payload perfil financiero | `{ empleo_formal, ingreso_mensual, linea_credito }` | `{ ingresoMensual, lineaCredito, empleoFormal }` (en ambos orígenes: `auth.js` y `dashboard.js`) | 🔴 **3 claves en camelCase, backend espera snake_case (AUD-23)** |
| Respuesta transacción (GET) | `{ nombreComercio, montoTransaccion, medioPago, fecha }` (camelCase, sin `@JsonProperty`) | Lee `t.nombre_comercio`, `t.medio_pago`, `t.monto_transaccion` (snake_case) | 🔴 **Mismatch inverso: la tabla nunca muestra datos reales (AUD-24)** |
| Respuesta análisis | `{ perfil_financiero, resumen_gastos, recomendaciones, ... }` (snake_case vía `@JsonProperty`) | Lee `data.perfil_financiero` ✅, pero usa `data.resumen_gastos` en vez de `data.recomendaciones` para la lista de recomendaciones | 🔴 **Bug de variable, no de casing (AUD-25)** |

**Recomendación del PM (actualizada):** dado que el mismatch de casing se repite en 3 payloads distintos (registro, perfil x2), la solución de mayor apalancamiento no es corregir cada uno a mano, sino declarar `spring.jackson.property-naming-strategy: SNAKE_CASE` en `application.yml` (opción A) — lo que además permitiría **quitar** todas las anotaciones `@JsonProperty` manuales de `RegisterRequest`, `PerfilFinancieroRequest`, `AnalisisResponse`, etc., reduciendo superficie de error futuro. La alternativa (opción B: corregir cada payload JS uno por uno) es más rápida hoy pero no previene que el próximo formulario nuevo reintroduzca el mismo bug. Ver TASK-038.

---

## 4. Configuración de Entorno
Sin cambios respecto a v3 (ver v3, Sección 4). `application.yml` confirmado restaurado (AUD-09 ✅), `open-in-view: false` confirmado presente (AUD-11 ✅), `JWT_SECRET`/`IA_API_URL` confirmados externalizados sin default inseguro a nivel de `application.yml` (AUD-13 sigue parcial únicamente por el fallback remanente en `JwtUtil.java`).

---

## 5. Dependencias del Backend (pom.xml)
Sin cambios. `<configuration>` confirmado correcto en ambos bloques (`spring-boot-maven-plugin` y `maven-compiler-plugin`) — AUD-21 ✅.

---

## 6. Auditoría Técnica v4
Metodología: cada archivo citado se extrajo por ruta exacta del snapshot y se comparó carácter por carácter contra lo que su contraparte (otro archivo, o el propio comentario del código) declara esperar. No se usó la prosa de la Nota Maestra incrustada (líneas 3308–3764 del snapshot) como fuente de verdad para ningún hallazgo.

### 6.1 Índice de Hallazgos (v4)
| ID | Severidad | Título | Estado v4 |
| :--- | :--- | :--- | :--- |
| AUD-01 | Alta | AuthResponse: campos invertidos | ✅ Resuelto |
| AUD-02 | Alta | Enum de perfil financiero inconsistente | 🔴 Sin cambios |
| AUD-03 | Alta | Contrato de API desalineado | 🟡 **Rutas resueltas; casing roto (ver AUD-23/24)** |
| AUD-04 | Media | Campo `transactions` vs `transacciones` | ✅ Resuelto |
| AUD-05 | Alta | `/predict` del mock ignora el body | 🟡 Sin cambios |
| AUD-06 | Alta | `usuarioId` nunca se asigna en historial | ✅ Resuelto |
| AUD-07 | Alta (seg.) | Transacciones sin autorización/DTO | ✅ Resuelto |
| AUD-08 | Alta (seg.) | IDOR en historial | ✅ Resuelto |
| AUD-09 | Alta | `application.yml` casi vacío | ✅ Resuelto |
| AUD-10 | Baja | `UserEntity.apellido` no se puebla | ✅ Resuelto |
| AUD-11 | Baja | `open-in-view=false` pendiente | ✅ Resuelto |
| AUD-12 | Media | `TransactionRequest` mal aprovechado | ✅ Resuelto |
| AUD-13 | Alta (seg.) | `jwt.secret` hardcodeado | ✅ Resuelto |
| AUD-14 | Alta | Esquema `historial_analisis` no coincide con JPA | ✅ Resuelto |
| AUD-15 | Alta | Motor de IA inalcanzable (ruta) | 🟡 Parcial |
| AUD-16 | Alta | Contrato de respuesta del motor no coincide | 🔴 Sin cambios |
| AUD-17 | Media | Motor de IA duplicado | 🔴 Sin cambios |
| AUD-18 | Media | `dashboard.html` existe pero está huérfano | 🟡 **Reclasificado (existe, desconectado)** |
| AUD-19 | Media | Registro→perfil encadenado, pero bloqueado por casing | 🟡 **Reclasificado (código correcto, dato de entrada roto)** |
| AUD-20 | Baja | Typo `Sexo.FEMININO` | ✅ Resuelto |
| AUD-21 | Baja | Typo `pom.xml` `<coniguration>` | ✅ Resuelto |
| AUD-22 | Baja | `Sexo` serializado como código de una letra hacia la IA | 🔴 Sin cambios |
| **AUD-23** | Alta — nuevo | Mismatch sistemático camelCase/snake_case en 3 payloads del frontend | 🔴 Nuevo |
| **AUD-24** | Media — nuevo | `TransaccionResponse` camelCase vs. lectura snake_case en `dashboard.js` | 🔴 Nuevo |
| **AUD-25** | Media — nuevo | `mostrarResultadosIA` lee `resumen_gastos` en vez de `recomendaciones` | 🔴 Nuevo |
| **AUD-26** | Baja — nuevo | No existe `GET /api/v1/perfil`; verificación de perfil depende sólo de `localStorage` | 🔴 Nuevo |

*Balance v4: 12 resueltos · 6 parciales · 8 abiertos, de 26 hallazgos totales.*

### 6.2 Hallazgos Reclasificados (evidencia de código, corrige la v3)

#### AUD-18 — dashboard.html existe, pero es un archivo huérfano — 🟡 Reclasificado
Verificado: `frontend/dashboard.html` existe en el repositorio (ya no hay 404 tras el login). Pero su único `<script>` es un bloque inline de ~15 líneas que sólo valida la presencia de un token en `localStorage` y pinta un mensaje de bienvenida estático. **En todo el repositorio sólo existen dos `<script src="...">`: el CDN de Bootstrap y `js/auth.js`, ambos dentro de `index.html`.** `js/api.js` y `js/dashboard.js` —donde vive toda la lógica real de transacciones, perfil y análisis— no se cargan desde ningún HTML del proyecto.
**Impacto:** aunque el login funcione (AUD-01 resuelto) y la redirección llegue a `dashboard.html` sin 404, el usuario ve una tarjeta de bienvenida vacía. Ningún botón, tabla ni modal de `dashboard.js` existe en el DOM.
**Acción recomendada (TASK-035, redefinida):** en `dashboard.html`, agregar `<script src="js/api.js"></script>` y `<script src="js/dashboard.js"></script>` antes de `</body>`, y añadir al HTML los elementos que `dashboard.js` espera encontrar por `id` (`tablaTransaccionesBody`, `formTransaccion`, `btnAnalizar`, `resultadoContenedor`, `iaPerfil`, `iaRecomendaciones`, `modalPerfilIncompleto`, `formPerfilFinanciero`, etc. — ninguno de estos existe hoy en `dashboard.html`).

#### AUD-19 — Registro→perfil correctamente encadenado en código, pero bloqueado aguas arriba — 🟡 Reclasificado
Verificado: `index.html` conserva la sección "Perfil Financiero Inicial" con los campos `regIngresoMensual`, `regLineaCredito`, `regEmpleoFormal`. `auth.js` los captura en `perfilPayload` y, tras un `POST /auth/register` exitoso, encadena `POST /perfil` con ese payload antes de redirigir a `dashboard.html`. El diseño del flujo es correcto.
**El problema real** es que el primer paso (`POST /auth/register`) muy probablemente nunca llega a "exitoso": `RegisterRequest.java` exige `fecha_nacimiento`, `estado_civil`, `numero_hijos` (vía `@JsonProperty`, todos `@NotNull`), pero `auth.js` envía `fechaNacimiento`, `estadoCivil`, `numeroHijos`. Jackson no completa esos tres campos con esas claves, y la validación los rechaza con 400 antes de crear el usuario — por lo que el paso B (crear perfil) nunca se alcanza en la práctica. Ver AUD-23 para la causa raíz común.
**Acción recomendada:** resolver AUD-23 (TASK-038); una vez corregido el casing, este flujo debería funcionar sin cambios adicionales de lógica.

#### AUD-03 — Contrato de API: rutas resueltas, casing roto en dos puntos — 🟡 Reclasificado
Verificado con las tres llamadas de `dashboard.js`: `/transacciones/usuario/transacciones` (GET y POST), `/analisis/predict` (POST) y `/perfil` (POST desde el modal) — **las tres coinciden exactamente con los `@RequestMapping` reales del backend.** El payload de alta de transacción (`nombre_comercio`, `monto_transaccion`, `medio_pago`) también es correcto. El desalineamiento de rutas descrito en v1/v2/v3 **ya no existe en el código actual.**
Lo que sí sigue roto, y es nuevo en esta auditoría: el payload del modal de perfil de `dashboard.js` (mismo problema que AUD-19/AUD-23) y la lectura de la respuesta de transacciones (AUD-24).
**Acción recomendada:** cerrar AUD-23, AUD-24 y AUD-25 (Sección 6.3). TASK-003 y TASK-018 de versiones anteriores quedan mayormente resueltos por el propio avance del código; sólo falta reconciliar el backlog.

### 6.3 Hallazgos Nuevos (AUD-23 a AUD-26)

#### AUD-23 — Mismatch sistemático de casing en 3 payloads del frontend
**Severidad:** Alta · **Componente:** `auth.js`, `dashboard.js` → `RegisterRequest.java`, `PerfilFinancieroRequest.java`
Hallazgo: tres payloads distintos, en dos archivos JS distintos, repiten el mismo patrón: se construyen con claves camelCase (`fechaNacimiento`, `estadoCivil`, `numeroHijos`, `ingresoMensual`, `lineaCredito`, `empleoFormal`), mientras los DTOs Java correspondientes exigen snake_case vía `@JsonProperty` (`fecha_nacimiento`, `estado_civil`, `numero_hijos`, `ingreso_mensual`, `linea_credito`, `empleo_formal`). No hay `spring.jackson.property-naming-strategy` configurado, así que Jackson no hace ningún mapeo automático entre ambas convenciones.
Impacto: bloquea el registro completo (AUD-19) y ambos caminos de creación de perfil financiero (el de `auth.js` tras registro, y el del modal de `dashboard.js`), con un error 400 genérico que no indica la causa real al usuario.
**Acción recomendada (TASK-038):** declarar `spring.jackson.property-naming-strategy: SNAKE_CASE` en `application.yml` y retirar las anotaciones `@JsonProperty` manuales redundantes en los DTOs afectados (`RegisterRequest`, `PerfilFinancieroRequest`, `AnalisisRequest`, `AnalisisResponse`, `TransactionRequest`). Alternativa más rápida pero menos robusta: corregir manualmente los tres payloads en `auth.js`/`dashboard.js` a snake_case, sin tocar el backend.

#### AUD-24 — TransaccionResponse en camelCase vs. lectura snake_case en dashboard.js
**Severidad:** Media · **Componente:** `TransaccionResponse.java`, `dashboard.js::renderizarTablaTransacciones`
Hallazgo: `TransaccionResponse` no tiene ninguna anotación `@JsonProperty`, así que Jackson la serializa con sus nombres de campo tal cual (`nombreComercio`, `montoTransaccion`, `medioPago`, `fecha` — camelCase). `renderizarTablaTransacciones` en `dashboard.js` lee `t.nombre_comercio`, `t.medio_pago`, `t.monto_transaccion` (snake_case).
Impacto: el `GET` de transacciones puede tener éxito (200 OK) con datos reales, pero la tabla siempre mostrará "Desconocido" / "N/A" / "$0.00" para cada fila, dando la falsa impresión de que no hay transacciones o que el guardado falló.
**Acción recomendada (TASK-039):** si se adopta TASK-038 (snake_case global), este hallazgo se resuelve solo. Si no, corregir `renderizarTablaTransacciones` para leer `t.nombreComercio`, `t.medioPago`, `t.montoTransaccion`.

#### AUD-25 — mostrarResultadosIA lee el campo equivocado para las recomendaciones
**Severidad:** Media · **Componente:** `dashboard.js::mostrarResultadosIA`
Hallazgo: la función construye la lista de "recomendaciones" iterando sobre `data.resumen_gastos` (un objeto/mapa de categoría→monto) en vez de `data.recomendaciones` (la lista real de strings). Como `resumen_gastos` es un objeto plano, `.length` es `undefined`, la condición `data.resumen_gastos.length > 0` es siempre falsa, y la rama que ejecuta es siempre la de "No hay datos suficientes para recomendaciones" — sin importar la respuesta real del backend. Además, `resumen_gastos` (el desglose de gastos por categoría, que sí tiene valor para el usuario) nunca se renderiza en ningún punto de la UI.
**Acción recomendada (TASK-040):** cambiar la fuente de datos de la lista a `data.recomendaciones`, y agregar un bloque separado que sí renderice `data.resumen_gastos` (p. ej. una lista o mini-tabla de categoría → monto).

#### AUD-26 — No existe GET /api/v1/perfil; la verificación de perfil depende sólo de localStorage
**Severidad:** Baja · **Componente:** `PerfilFinancieroController.java`, `dashboard.js::verificarPerfilFinanciero`
Hallazgo: `PerfilFinancieroController` sólo expone `@PostMapping` — no hay ningún `GET`. El propio comentario de `dashboard.js` lo reconoce: *"Asumiendo que existe un endpoint GET /perfil... si el backend aún no lo tiene, esto fallará"*. En la práctica, `verificarPerfilFinanciero()` no llama a ningún endpoint: sólo revisa la bandera local `perfilCompletado` en `localStorage`.
Impacto: cualquier usuario que ya tenga un perfil financiero creado pero pierda esa bandera local (nueva sesión, otro dispositivo, `localStorage` limpiado) volverá a ver el modal de "completar tu perfil". Si lo reenvía, chocará con la restricción de unicidad del backend (`PerfilFinancieroServiceImpl` lanza `IllegalStateException` → 409 Conflict) y verá un mensaje genérico de error sin explicación real.
**Acción recomendada (TASK-041):** agregar `GET /api/v1/perfil` (del usuario autenticado, reutilizando `PerfilFinancieroService.obtenerPerfilPorUsuarioId`, que ya existe) y hacer que `verificarPerfilFinanciero()` consulte ese endpoint real en vez de sólo el flag local.

### 6.4 Hallazgos Sin Cambios (re-verificados en esta pasada)
* **AUD-02** — `data-science/modeloFinanceAI/main.py` y `data-science/main.py` siguen comparando `perfil_str == "RIESGOSO"`; el enum Java sigue en `RIESGO`; `mock-api` sigue en `EN_RIESGO`. Nota: el comentario en `dashboard.js` ("Corregido de EN_RIESGO a RIESGO") sólo alineó el *frontend* con el enum Java — el motor de IA real sigue emitiendo un tercer valor incompatible. Sigue bloqueante para cuando se conecte el motor real.
* **AUD-05, AUD-16, AUD-17** — re-verificados contra el código, sin cambios respecto a v3.
* **AUD-13, AUD-15** — re-verificados, mismo estado parcial que v3 (ver Sección 4).
* **AUD-22** — re-verificado: `Sexo.getCodigo()` sigue devolviendo `"M"`/`"F"` hacia el motor de IA.

---

## 7. Estado Real por Vertical Slice (v4)

### 7.1 Slice 1 — Autenticación
| Capa | Estado v4 |
| :--- | :--- |
| Backend: seguridad base, register/login | ✅ / 🟡 (AUD-13 parcial) |
| Frontend: login | ✅ Funcional end-to-end (token correcto, redirección a un archivo que existe) |
| Frontend: registro | 🔴 Muy probablemente falla en el primer paso (AUD-23) |

### 7.2 Slice 2 — Transacciones
| Capa | Estado v4 |
| :--- | :--- |
| Backend | ✅ Completo y seguro, sin cambios |
| Frontend: alta de transacción | 🟢 Payload correcto — funcional si `dashboard.html` se conecta (AUD-18) |
| Frontend: listado de transacciones | 🔴 Se muestra vacío/incorrecto por AUD-24, aun si el backend responde bien |

### 7.3 Slice 3 — Análisis Financiero e IA
| Capa | Estado v4 |
| :--- | :--- |
| Backend: persistencia del historial | ✅ Desbloqueado (AUD-14) |
| Backend: perfil financiero requerido | 🟡 Requiere que AUD-23 se resuelva para recibir datos reales |
| Motor de IA | 🟡 Infraestructura lista, código y contrato rotos (AUD-15, AUD-16) |
| Frontend: resultado del análisis | 🟡 Badge de perfil correcto; recomendaciones nunca se muestran (AUD-25) |

### 7.4 Camino Crítico para una Demo End-to-End (re-evaluado v4)
| # | Bloqueador | Estado v4 | Esfuerzo estimado para cerrar |
| :---: | :--- | :--- | :--- |
| 1 | AUD-01 (token de login) | ✅ Resuelto | — |
| 2 | AUD-18 (dashboard.html desconectado) | 🟡 Bloquea | Bajo — 2 `<script>` + IDs faltantes en el HTML |
| 3 | AUD-23 (casing registro/perfil) | 🔴 Bloquea | Bajo — 1 propiedad en `application.yml` (o 3 payloads JS) |
| 4 | AUD-24 (tabla de transacciones) | 🔴 Bloquea la demo visual, no el dato | Muy bajo — 3 nombres de campo en `dashboard.js` |
| 5 | AUD-25 (recomendaciones) | 🔴 Bloquea la demo visual, no el dato | Muy bajo — 1 variable en `dashboard.js` |
| 6 | AUD-15 + AUD-16 (motor de IA) | 🟡 Bloquea | Medio — 1 línea de ruta + contrato de campos/tipos con Data Science |
| 7 | AUD-14 (esquema historial) | ✅ Resuelto | — |

**Lectura para el PM:** de los 7 puntos del camino crítico, 2 ya están resueltos y los otros 5 son, individualmente, correcciones pequeñas y bien localizadas — nada que requiera diseño nuevo. La secuencia de menor esfuerzo para llegar a una demo end-to-end es: **AUD-23 → AUD-18 → AUD-24 → AUD-25 → (AUD-15 + AUD-16 en paralelo con Data Science).**

---

## 8. Sprint de Estabilización v4

### Grupo A — Los 4 hallazgos nuevos (mayor apalancamiento, menor esfuerzo)
* 🔴 **TASK-038** — `spring.jackson.property-naming-strategy: SNAKE_CASE` en `application.yml` (o corrección manual de los 3 payloads en JS). Cierra AUD-23, desbloquea AUD-19 sin tocar su lógica.
* 🔴 **TASK-039** — Alinear `renderizarTablaTransacciones` con el casing real de `TransaccionResponse` (se resuelve solo si se adopta TASK-038). Cierra AUD-24.
* 🔴 **TASK-040** — Corregir `mostrarResultadosIA` para usar `data.recomendaciones`, agregar render de `data.resumen_gastos`. Cierra AUD-25.
* 🔴 **TASK-041** — Agregar `GET /api/v1/perfil` y usarlo en `verificarPerfilFinanciero()`. Cierra AUD-26.

### Grupo B — Conectar dashboard.html a la funcionalidad real
* 🔴 **TASK-035** (redefinida) — Agregar `<script src="js/api.js">` y `<script src="js/dashboard.js">` a `dashboard.html`, y los elementos HTML con los `id` que `dashboard.js` espera (tabla, formularios, modal, contenedores de resultado). Cierra AUD-18.

### Grupo C — Deuda de seguridad remanente
* 🔴 **TASK-034** — Eliminar el fallback hardcodeado de `JwtUtil.java`. Cierra AUD-13.

### Grupo D — Motor de IA real
* 🔴 **TASK-036** — Corregir `IAClient.java` de `/predict` a `/analisis-financiero`; confirmar `IA_API_URL` en `.env`. Cierra AUD-15.
* 🔴 **TASK-028** — Alinear contrato de respuesta del motor (nombres de campo + tipos numéricos sin `%`). Cierra AUD-16.
* 🔴 **TASK-002** — Unificar el enum de perfil de riesgo con Data Science (`RIESGOSO` → `RIESGO`). Cierra AUD-02.
* 🔴 **TASK-037** — Verificar con Data Science el vocabulario esperado para `sexo` ("M"/"F" vs. palabra completa). Cierra AUD-22.

### Grupo E — Limpieza y siguiente ola (sin cambios de fondo respecto a v3)
* 🔴 **TASK-029** — Retirar/documentar el motor de IA duplicado y `mock-api`. Cierra AUD-17 + AUD-05.
* 🔴 **TASK-021** — Vista de historial de diagnósticos (ya desbloqueada por AUD-14).
* 🔴 **TASK-016** — Paginación de transacciones.
* 🔴 **TASK-013** — Suite de tests de integración de Auth.
* 🔴 **TASK-020** — Tests de resiliencia ante caída del motor de IA.

### Grupo F — Semana 5 / Infraestructura (sin cambios)
* 🔴 **TASK-023, TASK-024, TASK-025** — `docker-compose.prod.yml`, despliegue OCI, QA final.

---

## 9. Convenciones y Definition of Done
Sin cambios respecto a v3 (incluida la regla 9.5 de disciplina de checklist). Se agrega:

**9.6 Regla nueva — Verificación por extracción, no por lectura narrativa:** al auditar un snapshot Repomix que incluye una copia incrustada de una Nota Maestra anterior (como ocurre en este proyecto), cualquier hallazgo debe verificarse extrayendo el bloque `<file path="...">` exacto del archivo de código en cuestión — nunca inferirse de la prosa descriptiva de la copia incrustada, que puede describir una versión pasada del código.

---

## 10. Backlog Priorizado v4
Se conservan los IDs TASK-001 a TASK-037; los nuevos continúan desde TASK-038.

### P0 — Sprint de Estabilización v4 (los 4 hallazgos nuevos + dashboard.html)
| ID | Título | Ref. |
| :--- | :--- | :--- |
| TASK-038 | Resolver mismatch de casing (snake_case global o payloads corregidos) | AUD-23 |
| TASK-039 | Corregir lectura de `TransaccionResponse` en `dashboard.js` | AUD-24 |
| TASK-040 | Corregir fuente de datos de recomendaciones en `mostrarResultadosIA` | AUD-25 |
| TASK-035 | Conectar `dashboard.html` con `api.js`/`dashboard.js` + IDs faltantes | AUD-18 |

### P1 — Motor de IA y deuda de seguridad remanente
| ID | Título | Ref. |
| :--- | :--- | :--- |
| TASK-034 | Eliminar fallback hardcodeado en `JwtUtil.java` | AUD-13 |
| TASK-036 | Corregir ruta de `IAClient` a `/analisis-financiero` | AUD-15 |
| TASK-028 | Alinear contrato de respuesta del motor de IA | AUD-16 |
| TASK-002 | Unificar enum de perfil de riesgo | AUD-02 |

### P2 — Robustez y limpieza
| ID | Título | Ref. |
| :--- | :--- | :--- |
| TASK-041 | Agregar `GET /api/v1/perfil` | AUD-26 |
| TASK-029 | Retirar/documentar motor de IA duplicado y mock-api | AUD-17 + AUD-05 |
| TASK-021 | Vista de historial de diagnósticos (ya desbloqueada) | Slice 3 |
| TASK-016 | Paginación de transacciones | Slice 2 |

### P3 — Verificación y tests
| ID | Título | Ref. |
| :--- | :--- | :--- |
| TASK-013 | Suite de tests de integración de Auth | Slice 1 |
| TASK-020 | Tests de resiliencia ante caída de IA | Slice 3 |
| TASK-037 | Verificar vocabulario de `sexo` con Data Science | AUD-22 |

### P4 — Infraestructura y cierre (Semana 5, sin cambios)
| ID | Título | Ref. |
| :--- | :--- | :--- |
| TASK-023 | `docker-compose.prod.yml` | Semana 5 |
| TASK-024 | Despliegue en OCI | Semana 5 |
| TASK-025 | QA end-to-end + revisión final | Semana 5 |

**Ya completadas y confirmadas dos veces (v3 y v4):** TASK-001, TASK-004, TASK-006, TASK-007, TASK-008, TASK-009 (parte de `application.yml`), TASK-010, TASK-011, TASK-012, TASK-026, TASK-032, TASK-033.

---

## 11. Anexo: Prompts Guía para Sesiones de IA
Sin cambios respecto a v3, actualizar referencias a "v4". Se agrega:

### Prompt de verificación anti-alucinación para snapshots con documentación incrustada
*Antes de afirmar el estado de cualquier hallazgo AUD-XX, extrae el archivo de código exacto citado (por su ruta `<file path="...">`) y compáralo carácter por carácter con lo que otro archivo espera de él. Si el snapshot incluye una copia de una Nota Maestra anterior dentro de sí mismo, ignórala como fuente de verdad sobre el estado actual del código — úsala sólo para saber qué se dijo en el pasado.*
</file>

<file path="Protocolo de colaboracion.md">
# 📌 Protocolo de Colaboración, Verificación y Control de Versionado (Actualizado)

**Proyecto:** FinanceAI - Backend  
**Propósito:** Definir el flujo de interacción estricto para la entrega de código optimizado, validación de compilación local, generación de comandos Git y actualización de la Nota Maestra.

---

### 🎯 Objetivo Principal
Garantizar que ningún commit de Git y ninguna actualización en la Nota Maestra se registren con código no probado. Todo cambio debe estar alineado con la arquitectura real del proyecto (paquete base `com.nocountry.financeai`) y ser compilado localmente antes de pasar a la fase de versionado y documentación.

---

### 🔄 Flujo de Trabajo en 5 Pasos (Paso 0 al Paso 4)

#### **Paso 0: Análisis Estricto de Contexto (Asistente IA)**
* Antes de generar cualquier fragmento de código o sugerencia, la IA **debe revisar obligatoriamente** las fuentes adjuntas en el cuaderno (como `financeai.md`, `pom.xml` o notas previas).
* Tiene prohibido inventar rutas, nombres de paquetes genéricos (*placeholders*) o versiones. Debe extraer el paquete base real (`com.nocountry.financeai`) para entregar una solución 100% *plug and play*.

#### **Paso 1: Entrega de Código (Asistente IA)**
* Se proporciona el código fuente completo en Java 21 / Spring Boot 3 (DTOs, Servicios, Controladores, etc.) con sus anotaciones (Lombok, Jakarta Validation, Spring Security) adaptado a la estructura del proyecto.
* **Restricción:** En este paso **no se generan** comandos Git ni bloques de actualización de la nota.

#### **Paso 2: Verificación Local (Desarrollador)**
* Se copia el código al IDE (IntelliJ / VS Code).
* Se ejecuta la compilación (`mvn clean compile` o build del IDE) y se verifica que no existan errores de sintaxis, dependencias o conflictos de contexto.

#### **Paso 3: Trigger de Confirmación (Desarrollador)**
* El usuario envía un mensaje en el chat confirmando que el módulo/código ha sido integrado y compilado exitosamente (ej. *"Listo, ya compiló correctamente"*).

#### **Paso 4: Artefactos Finales (Asistente IA)**
* Tras recibir el trigger, la IA genera inmediatamente:
  1. **Comando Git:** Formateado bajo el estándar *Conventional Commits* (ej. `feat(auth): ...`, `fix(security): ...`).
  2. **Snippet de Nota Maestra:** Fragmento Markdown listo para copiar y pegar en la documentación general del proyecto.

---

### 🏷️ Convención de Commits (Conventional Commits)

| Tipo | Uso | Ejemplo |
| :--- | :--- | :--- |
| `feat` | Nueva funcionalidad agregada | `feat(auth): implement RegisterRequest and LoginRequest DTOs` |
| `fix` | Corrección de un error o bug | `fix(security): resolve circular dependency in JwtAuthFilter` |
| `refactor` | Reestructuración de código sin alterar comportamiento | `refactor(config): update SecurityConfig to handle specific exceptions` |
| `docs` | Cambios exclusivos en documentación | `docs(readme): update backend technical notes` |
</file>

<file path="backend/src/main/java/com/nocountry/financeai/client/IAClient.java">
package com.nocountry.financeai.client;

import com.nocountry.financeai.dto.request.AnalisisRequest;
import com.nocountry.financeai.dto.response.AnalisisResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class IAClient {
    private final RestClient restClient;

    public AnalisisResponse analizar(AnalisisRequest request) {

        return restClient.post()
                .uri("/analisis-financiero")
                .body(request)
                .retrieve()
                .body(AnalisisResponse.class);
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/config/OpenApiConfig.java">
package com.nocountry.financeai.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {
    @Value("${app.openapi.server-url}")
    private String serverUrl;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .servers(List.of(
                        new Server().url(serverUrl)
                ))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));

    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/config/OrdenOpenApi.java">
package com.nocountry.financeai.config;

import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Clase para dar un orden a los endpoint en OpenApi-swagger
@Configuration
public class OrdenOpenApi {
    @Bean
    public OpenApiCustomizer ordenarTags() {
        return openApi -> {
            List<String> ordenDeseado = List.of(
                    "Analisis",
                    "Autenticacion",
                    "Perfil Financiero",
                    "Transacciones",
                    "Historial Resultado Analisis",
                    "Usuarios",
                    "Administradores",
                    "Test"
            );

            List<Tag> tagsOrdenados = new ArrayList<>(openApi.getTags());
            tagsOrdenados.sort(Comparator.comparingInt(tag -> {
                int idx = ordenDeseado.indexOf(tag.getName());
                return idx == -1 ? Integer.MAX_VALUE : idx;
            }));

            openApi.setTags(tagsOrdenados);
        };
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/controller/AdminController.java">
package com.nocountry.financeai.controller;

import com.nocountry.financeai.dto.request.TransactionRequest;
import com.nocountry.financeai.dto.response.AnalisisResponse;
import com.nocountry.financeai.dto.response.TransaccionResponse;
import com.nocountry.financeai.dto.response.UserResponse;
import com.nocountry.financeai.service.AnalisisIAService;
import com.nocountry.financeai.service.TransaccionService;
import com.nocountry.financeai.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@AllArgsConstructor
@Tag(
        name = "Administradores",
        description = "Administra el sistema"
)
public class AdminController {
    private final UserService userService;
    private final TransaccionService transaccionService;
    private final AnalisisIAService analisisIAService;

    @GetMapping("/usuarios")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> obtenerUsuarios() {
        return userService.obtenerUsuarios();
    }

    @GetMapping("/usuarios/documento/{documento}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse obtenerUsuario(@PathVariable String documento) {
        return userService.obtenerUsuarioPorDocumento(documento);
    }

    @GetMapping("/transacciones")
    @PreAuthorize("hasRole('ADMIN')")
    public List<TransaccionResponse> listarTransacciones(){
        return transaccionService.obtenerTransacciones();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/transacciones/usuario/{usuarioId}")
    public List<TransaccionResponse> listarTransaccionesPorUsuario(@PathVariable Long usuarioId) {
        return transaccionService.obtenerTransaccionesPorUsuario(usuarioId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("transacciones/usuario/{usuarioId}")
    public TransaccionResponse crearTransaccion(
            @PathVariable Long usuarioId,
            @Valid @RequestBody TransactionRequest transactionRequest) {
        return transaccionService.crearTransaccion(usuarioId, transactionRequest);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/usuario/{documento}/analizar")
    public AnalisisResponse analisisPorUsuario(
            @PathVariable String documento
    ) {
        return analisisIAService.analizarPorDocumento(documento);
    }


}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/controller/PerfilFinancieroController.java">
package com.nocountry.financeai.controller;

import com.nocountry.financeai.dto.request.PerfilFinancieroRequest;
import com.nocountry.financeai.dto.response.PerfilFinancieroResponse;
import com.nocountry.financeai.service.PerfilFinancieroService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/perfil")
@RequiredArgsConstructor
@Tag(
        name = "Perfil Financiero",
        description = "Gestión del perfil financiero del usuario")
public class PerfilFinancieroController {
    private final PerfilFinancieroService perfilFinancieroService;

    @PostMapping
    public PerfilFinancieroResponse crearPerfil(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody PerfilFinancieroRequest request
    ) {
        System.out.println("Request recibido: " + request);
        return perfilFinancieroService.crearPerfil(userDetails.getUsername(), request);

    }

}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/request/UserRequest.java">
package com.nocountry.financeai.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.EstadoCivil;
import com.nocountry.financeai.entity.enums.Sexo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;


public record UserRequest(
        @Schema(
                description = "Nombre del usuario" ,
                example = "carlos"
        )
        @Size(max = 100, message = "Nombre no puede superar los 100 caracteres")
        String nombre,

        @Schema(
                description = "apellido del usuario" ,
                example = "gomez"
        )
        @Size(max = 100, message = "Apellido no puede superar los 100 caracteres")
        String apellido,
        @Schema(
                description = "Documento de identificacion del usuario" ,
                example = "PEMJ920323HJCZNN0"
        )
        String documento,

        @Schema(
                description = "Correo electronico del usuario" ,
                example = "carlosgomez@alura.com"
        )
        @Email(message = "El formato del correo no es valido")
        String email,

        @Schema(
                description = "Fecha de naciemiento del usuario" ,
                example = "1999-03-24"
        )
        @JsonProperty("fecha_nacimiento")
        LocalDate fechaNacimiento,

        @Schema(
                description = "Estado civil del usuario" ,
                example = "soltero"
        )

        @JsonProperty("estado_civil")
        EstadoCivil estadoCivil,

        @Schema(
                description = "Sexo de nacimiento del usuario" ,
                example = "masculino"
        )
        Sexo sexo,

        @Schema(
                description = "Cantidad de hijos del usuario" ,
                example = "1"
        )
        @JsonProperty("numero_hijos")
        @Min(value = 0, message = "El numero de hijos no puede ser negativo")
        Integer numeroHijos
) {
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/response/HistorialAnalisisResponse.java">
package com.nocountry.financeai.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.PerfilFinanciero;
import com.nocountry.financeai.entity.enums.RangoAhorro;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public record HistorialAnalisisResponse (
        @JsonProperty("perfil_financiero")
        PerfilFinanciero perfilFinanciero,

        BigDecimal probabilidad,
        @JsonProperty("nivel_endeudamiento")
        BigDecimal nivelEndeudamiento,
        @JsonProperty("rango_ahorro")
        RangoAhorro rangoAhorro,
        @JsonProperty("resumen_gastos")
        Map<String, BigDecimal> resumenGastos,
        @JsonProperty
        List<String> recomendaciones
){}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/response/PerfilFinancieroResponse.java">
package com.nocountry.financeai.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record PerfilFinancieroResponse (
        @Schema(
                description = "Cantidad de empleos que tiene un usuario",
                example = "1"
        )
        @JsonProperty("empleo_formal")
        Integer empleoFormal,

        @Schema(
                description = "Cantidad de ingresos que persibe un usuario",
                example = "5500"
        )
        @JsonProperty("ingreso_mensual")
        BigDecimal ingresoMensual,

        @Schema(
                description = "Monto de credito que tiene un usuario",
                example = "10000"
        )
        @JsonProperty("linea_credito")
        BigDecimal lineaCredito
) {
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/response/TransaccionResponse.java">
package com.nocountry.financeai.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.MedioPago;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransaccionResponse(
        @Schema(
                description = "Nombre del comercio que aparece en la factura",
                example = "telcel"
        )
        @JsonProperty("nombre_comercio")
        String nombreComercio,

        @Schema(
                description = "Valor de la transaccion",
                example = "365"
        )
        @JsonProperty("monto_transaccion")
        BigDecimal montoTransaccion,

        @Schema(
                description = "Medio de pago en el que se pago/cancelo la transaccion",
                example = "EFECTIVO"
        )
        @JsonProperty("medio_pago")
        MedioPago medioPago,
        @Schema(
                description = "Fecha de la transaccion",
                example = "2026-08-06T20:06:38.692Z"
        )
        LocalDateTime fecha
) {}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/entity/enums/MedioPago.java">
package com.nocountry.financeai.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Medio de pago que usa un usuario para cancelar una transaccion",
        example = "TRANSFERENCIA"
)
public enum MedioPago {
    EFECTIVO,
    DEBITO,
    CREDITO,
    TRANSFERENCIA;

    @JsonValue
    public String toValue(){
        return this.name().toLowerCase();
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/entity/enums/Rol.java">
package com.nocountry.financeai.entity.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Permisos que tiene un usuario en el sistema",
        example = "USER"
)
public enum Rol {
    USER,
    ADMIN,
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/entity/PerfilFinancieroEntity.java">
package com.nocountry.financeai.entity;

import com.nocountry.financeai.entity.enums.RangoAhorro;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "perfil_financiero")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PerfilFinancieroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idPerfilFinanciero;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private UserEntity usuario;

    @Column(name = "empleo_formal")
    private Integer empleoFormal;

    @Column(name = "ingreso_mensual", precision = 12, scale = 2)
    private BigDecimal ingresoMensual;

    @Column(name = "linea_credito",  precision = 12, scale = 2)
    private BigDecimal lineaCredito;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/repository/TransactionRepository.java">
package com.nocountry.financeai.repository;

import com.nocountry.financeai.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByUsuarioId(Long usuarioId);
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/security/CustomUserDetailsService.java">
package com.nocountry.financeai.security;

import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el email: " + email));

        return new User(
                user.getEmail(),
                user.getPassword(),
                List.of(
                        new SimpleGrantedAuthority(
                                "ROLE_" + user.getRol().name()
                        )
                )
        );
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/security/JwtAuthFilter.java">
package com.nocountry.financeai.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Slf4j
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    // Se usa @Lazy en UserDetailsService para romper la referencia circular en tiempo de inicio
    public JwtAuthFilter(JwtUtil jwtUtil, @Lazy UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        log.info("JWT filter ejecutando{}", request.getRequestURI());
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        userEmail = jwtUtil.extractUsername(jwt);
        System.out.println("JWT recibido para: " + userEmail);

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            if (jwtUtil.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println("JWT válido: autenticando usuario");
            }
        }

        filterChain.doFilter(request, response);
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/security/JwtUtil.java">
package com.nocountry.financeai.security;

import com.nocountry.financeai.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.HexFormat;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private final JwtConfig jwtConfig;

    public JwtUtil(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
                .signWith(getSignInKey())
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * 🔑 IMPORTANTE: usando HEX (no Base64)
     */
    private SecretKey getSignInKey() {
        byte[] keyBytes = HexFormat.of().parseHex(jwtConfig.getSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/impl/TransaccionServiceImpl.java">
package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.dto.request.TransactionRequest;
import com.nocountry.financeai.dto.response.TransaccionResponse;
import com.nocountry.financeai.entity.TransactionEntity;
import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.exception.ResourceNotFoundException;
import com.nocountry.financeai.repository.TransactionRepository;
import com.nocountry.financeai.repository.UserRepository;
import com.nocountry.financeai.service.TransaccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransaccionServiceImpl implements TransaccionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Override
    public TransaccionResponse crearTransaccionAutenticado(String email, TransactionRequest transactionRequest) {
        UserEntity usuario = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado"
                        )
                );

        TransactionEntity transaccion = TransactionEntity.builder()
                        .nombreComercio(transactionRequest.nombreComercio())
                        .montoTransaccion(transactionRequest.montoTransaccion())
                        .medioPago(transactionRequest.medioPago())
                        .usuario(usuario)
                        .fecha(LocalDateTime.now())
                        .build();

        TransactionEntity transaccionGuardada = transactionRepository.save(transaccion);

        return convertirRespuesta(
                transaccionGuardada
        );
    }

    @Override
    public List<TransaccionResponse> obtenerTransaccionesAutenticado(String email) {
        UserEntity usuario = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        return transactionRepository.findByUsuarioId(usuario.getId())
                .stream()
                .map(this::convertirRespuesta)
                .toList();
    }

    @Override
    public TransaccionResponse crearTransaccion(Long usuarioId, TransactionRequest request) {
        UserEntity usuario = userRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        TransactionEntity transaccion = TransactionEntity.builder()
                .nombreComercio(request.nombreComercio())
                .montoTransaccion(request.montoTransaccion())
                .medioPago(request.medioPago())
                .usuario(usuario)
                .fecha(LocalDateTime.now())
                .build();

        TransactionEntity transaccionGuardada = transactionRepository.save(transaccion);

        return new TransaccionResponse(
                transaccionGuardada.getNombreComercio(),
                transaccionGuardada.getMontoTransaccion(),
                transaccionGuardada.getMedioPago(),
                transaccionGuardada.getFecha()
        );
    }

    @Override
    public List<TransaccionResponse> obtenerTransaccionesPorUsuario(Long idUsuario) {
        return transactionRepository.findByUsuarioId(idUsuario)
                .stream()
                .map(this::convertirRespuesta)
                .toList();
    }

    @Override
    public TransaccionResponse actualizarTransaccion(String email, Long idTransaccion, TransactionRequest request) {

        TransactionEntity transaccion = transactionRepository.findById(idTransaccion)
                .orElseThrow(()-> new ResourceNotFoundException("Transaccion no encontrada"));

        if(!transaccion.getUsuario().getEmail().equals(email)) {
            throw new AccessDeniedException("No tienes permiso para modificar esta transaccion");
        }

        if(request.nombreComercio() != null){
            transaccion.setNombreComercio(request.nombreComercio());
        }

        if(request.montoTransaccion() != null){
            transaccion.setMontoTransaccion(request.montoTransaccion());
        }

        if(request.medioPago() != null){
            transaccion.setMedioPago(request.medioPago());
        }

        TransactionEntity transaccionActualizada = transactionRepository.save(transaccion);
        return convertirRespuesta(transaccionActualizada);
    }

    @Override
    public void eliminarTransaccion(String email, Long idTransaccion) {
        TransactionEntity transaccion = transactionRepository.findById(idTransaccion)
                .orElseThrow(()-> new ResourceNotFoundException("Transaccion no encontrada"));

        if(!transaccion.getUsuario().getEmail().equals(email)) {
            throw new  AccessDeniedException("Transaccion no pertenece al usuario");
        }
        transactionRepository.delete(transaccion);
    }

    public List<TransaccionResponse> obtenerTransacciones() {
        return transactionRepository.findAll()
                .stream()
                .map(this::convertirRespuesta)
                .toList();
    }

    private TransaccionResponse convertirRespuesta(TransactionEntity transactionEntity) {
        return new TransaccionResponse(
                transactionEntity.getNombreComercio(),
                transactionEntity.getMontoTransaccion(),
                transactionEntity.getMedioPago(),
                transactionEntity.getFecha()
        );
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/AuthService.java">
package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.request.LoginRequest;
import com.nocountry.financeai.dto.request.RegisterRequest;
import com.nocountry.financeai.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/HistorialAnalisisService.java">
package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.response.HistorialAnalisisResponse;

import java.util.List;

public interface HistorialAnalisisService {
    List<HistorialAnalisisResponse> obtenerHistorial();
    List<HistorialAnalisisResponse> obtenerHistorialPorId(Long id);
    List<HistorialAnalisisResponse> obtenerHistorialAutenticado(String email);
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/TransaccionService.java">
package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.request.TransactionRequest;
import com.nocountry.financeai.dto.response.TransaccionResponse;
import com.nocountry.financeai.entity.TransactionEntity;
import jakarta.validation.Valid;

import java.util.List;

public interface TransaccionService {
    //crea transacciones de un usuario autenticado
    TransaccionResponse crearTransaccionAutenticado(String email,TransactionRequest transactionRequest);
    // Obtiene las transacciones de un usuario registrado
    List<TransaccionResponse> obtenerTransaccionesAutenticado(String email);
    // Crea transaccion por Id
    TransaccionResponse crearTransaccion(Long usuarioId, TransactionRequest transactionRequest);
    // Obtiene todas las transacciones de todos los usuarios
    List<TransaccionResponse> obtenerTransacciones();
    // Obtiene todas las transacciones de un usuario
    List<TransaccionResponse> obtenerTransaccionesPorUsuario(Long idUsuario);

    TransaccionResponse actualizarTransaccion(String email, Long idTransaccion, @Valid TransactionRequest transactionRequest);

    void eliminarTransaccion(String email, Long idTransaccion);
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/FinanceaiApplication.java">
package com.nocountry.financeai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinanceaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceaiApplication.class, args);
	}

}
</file>

<file path="backend/src/main/resources/db/migration/V2__create_transactions_table.sql">
CREATE TABLE transacciones (
id BIGSERIAL PRIMARY KEY,
usuario_id BIGINT NOT NULL,
monto_transaccion NUMERIC(12, 2) NOT NULL,
tipo VARCHAR(10),
categoria VARCHAR(50),
nombre_comercio VARCHAR(255),
medio_pago VARCHAR(20) NOT NULL,
fecha TIMESTAMP NOT NULL,
CONSTRAINT fk_transacciones_usuario
FOREIGN KEY (usuario_id)
REFERENCES usuarios(id)
ON DELETE CASCADE
);
</file>

<file path="backend/src/main/resources/db/migration/V5__fix_historial_analisis_schema.sql">
-- Renombrar la columna frecuencia_ahorro a rango_ahorro para coincidir con la entidad JPA
ALTER TABLE historial_analisis
RENAME COLUMN frecuencia_ahorro TO rango_ahorro;

-- Cambiar el tipo de dato de INTEGER a NUMERIC(4,2) para soportar BigDecimal
ALTER TABLE historial_analisis
ALTER COLUMN nivel_endeudamiento TYPE NUMERIC(4,2);
</file>

<file path="backend/mvnw.cmd">
<# : batch portion
@REM ----------------------------------------------------------------------------
@REM Licensed to the Apache Software Foundation (ASF) under one
@REM or more contributor license agreements.  See the NOTICE file
@REM distributed with this work for additional information
@REM regarding copyright ownership.  The ASF licenses this file
@REM to you under the Apache License, Version 2.0 (the
@REM "License"); you may not use this file except in compliance
@REM with the License.  You may obtain a copy of the License at
@REM
@REM    http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing,
@REM software distributed under the License is distributed on an
@REM "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
@REM KIND, either express or implied.  See the License for the
@REM specific language governing permissions and limitations
@REM under the License.
@REM ----------------------------------------------------------------------------

@REM ----------------------------------------------------------------------------
@REM Apache Maven Wrapper startup batch script, version 3.3.4
@REM
@REM Optional ENV vars
@REM   MVNW_REPOURL - repo url base for downloading maven distribution
@REM   MVNW_USERNAME/MVNW_PASSWORD - user and password for downloading maven
@REM   MVNW_VERBOSE - true: enable verbose log; others: silence the output
@REM ----------------------------------------------------------------------------

@IF "%__MVNW_ARG0_NAME__%"=="" (SET __MVNW_ARG0_NAME__=%~nx0)
@SET __MVNW_CMD__=
@SET __MVNW_ERROR__=
@SET __MVNW_PSMODULEP_SAVE=%PSModulePath%
@SET PSModulePath=
@FOR /F "usebackq tokens=1* delims==" %%A IN (`powershell -noprofile "& {$scriptDir='%~dp0'; $script='%__MVNW_ARG0_NAME__%'; icm -ScriptBlock ([Scriptblock]::Create((Get-Content -Raw '%~f0'))) -NoNewScope}"`) DO @(
  IF "%%A"=="MVN_CMD" (set __MVNW_CMD__=%%B) ELSE IF "%%B"=="" (echo %%A) ELSE (echo %%A=%%B)
)
@SET PSModulePath=%__MVNW_PSMODULEP_SAVE%
@SET __MVNW_PSMODULEP_SAVE=
@SET __MVNW_ARG0_NAME__=
@SET MVNW_USERNAME=
@SET MVNW_PASSWORD=
@IF NOT "%__MVNW_CMD__%"=="" ("%__MVNW_CMD__%" %*)
@echo Cannot start maven from wrapper >&2 && exit /b 1
@GOTO :EOF
: end batch / begin powershell #>

$ErrorActionPreference = "Stop"
if ($env:MVNW_VERBOSE -eq "true") {
  $VerbosePreference = "Continue"
}

# calculate distributionUrl, requires .mvn/wrapper/maven-wrapper.properties
$distributionUrl = (Get-Content -Raw "$scriptDir/.mvn/wrapper/maven-wrapper.properties" | ConvertFrom-StringData).distributionUrl
if (!$distributionUrl) {
  Write-Error "cannot read distributionUrl property in $scriptDir/.mvn/wrapper/maven-wrapper.properties"
}

switch -wildcard -casesensitive ( $($distributionUrl -replace '^.*/','') ) {
  "maven-mvnd-*" {
    $USE_MVND = $true
    $distributionUrl = $distributionUrl -replace '-bin\.[^.]*$',"-windows-amd64.zip"
    $MVN_CMD = "mvnd.cmd"
    break
  }
  default {
    $USE_MVND = $false
    $MVN_CMD = $script -replace '^mvnw','mvn'
    break
  }
}

# apply MVNW_REPOURL and calculate MAVEN_HOME
# maven home pattern: ~/.m2/wrapper/dists/{apache-maven-<version>,maven-mvnd-<version>-<platform>}/<hash>
if ($env:MVNW_REPOURL) {
  $MVNW_REPO_PATTERN = if ($USE_MVND -eq $False) { "/org/apache/maven/" } else { "/maven/mvnd/" }
  $distributionUrl = "$env:MVNW_REPOURL$MVNW_REPO_PATTERN$($distributionUrl -replace "^.*$MVNW_REPO_PATTERN",'')"
}
$distributionUrlName = $distributionUrl -replace '^.*/',''
$distributionUrlNameMain = $distributionUrlName -replace '\.[^.]*$','' -replace '-bin$',''

$MAVEN_M2_PATH = "$HOME/.m2"
if ($env:MAVEN_USER_HOME) {
  $MAVEN_M2_PATH = "$env:MAVEN_USER_HOME"
}

if (-not (Test-Path -Path $MAVEN_M2_PATH)) {
    New-Item -Path $MAVEN_M2_PATH -ItemType Directory | Out-Null
}

$MAVEN_WRAPPER_DISTS = $null
if ((Get-Item $MAVEN_M2_PATH).Target[0] -eq $null) {
  $MAVEN_WRAPPER_DISTS = "$MAVEN_M2_PATH/wrapper/dists"
} else {
  $MAVEN_WRAPPER_DISTS = (Get-Item $MAVEN_M2_PATH).Target[0] + "/wrapper/dists"
}

$MAVEN_HOME_PARENT = "$MAVEN_WRAPPER_DISTS/$distributionUrlNameMain"
$MAVEN_HOME_NAME = ([System.Security.Cryptography.SHA256]::Create().ComputeHash([byte[]][char[]]$distributionUrl) | ForEach-Object {$_.ToString("x2")}) -join ''
$MAVEN_HOME = "$MAVEN_HOME_PARENT/$MAVEN_HOME_NAME"

if (Test-Path -Path "$MAVEN_HOME" -PathType Container) {
  Write-Verbose "found existing MAVEN_HOME at $MAVEN_HOME"
  Write-Output "MVN_CMD=$MAVEN_HOME/bin/$MVN_CMD"
  exit $?
}

if (! $distributionUrlNameMain -or ($distributionUrlName -eq $distributionUrlNameMain)) {
  Write-Error "distributionUrl is not valid, must end with *-bin.zip, but found $distributionUrl"
}

# prepare tmp dir
$TMP_DOWNLOAD_DIR_HOLDER = New-TemporaryFile
$TMP_DOWNLOAD_DIR = New-Item -Itemtype Directory -Path "$TMP_DOWNLOAD_DIR_HOLDER.dir"
$TMP_DOWNLOAD_DIR_HOLDER.Delete() | Out-Null
trap {
  if ($TMP_DOWNLOAD_DIR.Exists) {
    try { Remove-Item $TMP_DOWNLOAD_DIR -Recurse -Force | Out-Null }
    catch { Write-Warning "Cannot remove $TMP_DOWNLOAD_DIR" }
  }
}

New-Item -Itemtype Directory -Path "$MAVEN_HOME_PARENT" -Force | Out-Null

# Download and Install Apache Maven
Write-Verbose "Couldn't find MAVEN_HOME, downloading and installing it ..."
Write-Verbose "Downloading from: $distributionUrl"
Write-Verbose "Downloading to: $TMP_DOWNLOAD_DIR/$distributionUrlName"

$webclient = New-Object System.Net.WebClient
if ($env:MVNW_USERNAME -and $env:MVNW_PASSWORD) {
  $webclient.Credentials = New-Object System.Net.NetworkCredential($env:MVNW_USERNAME, $env:MVNW_PASSWORD)
}
[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12
$webclient.DownloadFile($distributionUrl, "$TMP_DOWNLOAD_DIR/$distributionUrlName") | Out-Null

# If specified, validate the SHA-256 sum of the Maven distribution zip file
$distributionSha256Sum = (Get-Content -Raw "$scriptDir/.mvn/wrapper/maven-wrapper.properties" | ConvertFrom-StringData).distributionSha256Sum
if ($distributionSha256Sum) {
  if ($USE_MVND) {
    Write-Error "Checksum validation is not supported for maven-mvnd. `nPlease disable validation by removing 'distributionSha256Sum' from your maven-wrapper.properties."
  }
  Import-Module $PSHOME\Modules\Microsoft.PowerShell.Utility -Function Get-FileHash
  if ((Get-FileHash "$TMP_DOWNLOAD_DIR/$distributionUrlName" -Algorithm SHA256).Hash.ToLower() -ne $distributionSha256Sum) {
    Write-Error "Error: Failed to validate Maven distribution SHA-256, your Maven distribution might be compromised. If you updated your Maven version, you need to update the specified distributionSha256Sum property."
  }
}

# unzip and move
Expand-Archive "$TMP_DOWNLOAD_DIR/$distributionUrlName" -DestinationPath "$TMP_DOWNLOAD_DIR" | Out-Null

# Find the actual extracted directory name (handles snapshots where filename != directory name)
$actualDistributionDir = ""

# First try the expected directory name (for regular distributions)
$expectedPath = Join-Path "$TMP_DOWNLOAD_DIR" "$distributionUrlNameMain"
$expectedMvnPath = Join-Path "$expectedPath" "bin/$MVN_CMD"
if ((Test-Path -Path $expectedPath -PathType Container) -and (Test-Path -Path $expectedMvnPath -PathType Leaf)) {
  $actualDistributionDir = $distributionUrlNameMain
}

# If not found, search for any directory with the Maven executable (for snapshots)
if (!$actualDistributionDir) {
  Get-ChildItem -Path "$TMP_DOWNLOAD_DIR" -Directory | ForEach-Object {
    $testPath = Join-Path $_.FullName "bin/$MVN_CMD"
    if (Test-Path -Path $testPath -PathType Leaf) {
      $actualDistributionDir = $_.Name
    }
  }
}

if (!$actualDistributionDir) {
  Write-Error "Could not find Maven distribution directory in extracted archive"
}

Write-Verbose "Found extracted Maven distribution directory: $actualDistributionDir"
Rename-Item -Path "$TMP_DOWNLOAD_DIR/$actualDistributionDir" -NewName $MAVEN_HOME_NAME | Out-Null
try {
  Move-Item -Path "$TMP_DOWNLOAD_DIR/$MAVEN_HOME_NAME" -Destination $MAVEN_HOME_PARENT | Out-Null
} catch {
  if (! (Test-Path -Path "$MAVEN_HOME" -PathType Container)) {
    Write-Error "fail to move MAVEN_HOME"
  }
} finally {
  try { Remove-Item $TMP_DOWNLOAD_DIR -Recurse -Force | Out-Null }
  catch { Write-Warning "Cannot remove $TMP_DOWNLOAD_DIR" }
}

Write-Output "MVN_CMD=$MAVEN_HOME/bin/$MVN_CMD"
</file>

<file path="backend/README.md">
## 💻 Módulo Backend & Infraestructura

El backend de **FinanceAI** está estructurado bajo una arquitectura limpia, desacoplada y orientada a capas utilizando **Java 21** y **Spring Boot 3.x/4.x**. El sistema ha sido diseñado bajo un enfoque "camaleónico", permitiendo un desarrollo local ágil pero completamente preparado para un despliegue seguro y transparente en **Oracle Cloud Infrastructure (OCI)**.

### 🛠️ Stack Tecnológico
* **Lenguaje:** Java 21 (LTS) - Implementación de *Records* inmutables para DTOs y compatibilidad nativa con *Virtual Threads*.
* **Framework:** Spring Boot (Spring Web, Spring Data JPA, Jakarta Validation).
* **Base de Datos:** PostgreSQL - Elegido por su estricta precisión matemática (`NUMERIC`) en transacciones financieras y madurez analítica.
* **Evolución de Datos:** Flyway - Control de versiones y migraciones automatizadas del esquema de base de datos.
* **Virtualización Local:** Docker Compose - Para la réplica exacta y aislada del entorno de base de datos en el equipo.
* **Calidad de Código:** Configurado bajo estándares estrictos de **SonarQube** (Clean Code y prevención de código muerto).

---

### 📂 Estructura de Arquitectura (Capas)
Dentro del directorio `/backend/src/main/java/com/nocountry/financeai/`, el código se organiza bajo el principio de responsabilidad única:

* **`controller/`**: Expone los endpoints REST públicos. Administra las validaciones automáticas de payloads (`@Valid`) y el manejo de políticas CORS para la integración fluida con el frontend.
* **`service/` & `service.impl/`**: Capa pura de lógica de negocio. Utiliza abstracción por interfaces para aislar los procesos internos, dejando el esqueleto preparado para orquestar las llamadas HTTP externas hacia la API de FastAPI del equipo de Data Science.
* **`dto/`**: Objetos de Transferencia de Datos desarrollados mediante *Java 21 Records*, reduciendo el código basura (*boilerplate*) y asegurando la inmutabilidad de los datos transferidos.
* **`model/`**: Aloja las entidades JPA de base de datos y Enums tipados (ej: `CategoriaGasto`, `MedioPago`) mapeados estrictamente en minúsculas mediante Jackson (`@JsonValue`), garantizando una sintonía del 100% con los requerimientos del dataset limpio de Data Science.
* **`repository/`**: Interfaces de persistencia segura que heredan de `JpaRepository`.

---

### 🐳 Réplica de Entorno Local (Docker Compose)
Para eliminar el problema de *"en mi máquina no funciona"*, la infraestructura local de base de datos está completamente automatizada.

**Instrucciones para el equipo de desarrollo:**
1. Asegúrate de tener Docker instalado en tu sistema operativo Linux.
2. Abre una terminal en la raíz del monorepo (donde se ubica el archivo `docker-compose.yml`).
3. Ejecuta el siguiente comando para levantar el entorno en segundo plano:
   ```bash
   docker compose up -d
</file>

<file path="data-science/requirements.txt">
fastapi
uvicorn
pandas
scikit-learn==1.3.2
joblib
pydantic
</file>

<file path="frontend/js/api.js">
// ==========================================
// Configuración y Utilidades Base de la API
// ==========================================
const BASE_URL = 'http://localhost:8080/api/v1';

/**
 * Función genérica (fetch wrapper) para consumir endpoints protegidos.
 * Inyecta automáticamente el token JWT en las cabeceras.
 */
async function fetchProtected(endpoint, options = {}) {
    const token = localStorage.getItem('jwtToken');

    if (!token) {
        console.warn("No hay sesión activa");
        return null;
    }

    const defaultHeaders = {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
    };

    const config = {
        ...options,
        headers: {
            ...defaultHeaders,
            ...options.headers
        }
    };

    try {
        const response = await fetch(`${BASE_URL}${endpoint}`, config);

        // Si el token expiró o es inválido, Spring Boot devolverá 401 o 403
        if (response.status === 401 || response.status === 403) {
            localStorage.removeItem('jwtToken');
            window.location.href = 'index.html';
            throw new Error('Sesión expirada o no autorizada');
        }

        return response;
    } catch (error) {
        console.error('Error en fetchProtected:', error);
        throw error;
    }
}
</file>

<file path="frontend/js/dashboard.js">
// ==========================================
// Configuración e Inicio
// ==========================================
// Asumiendo que `fetchProtected` está en api.js. Si no, asegúrate de que agregue la URL base '/api/v1' y el Header de Autorización.

document.addEventListener('DOMContentLoaded', async () => {
    const token = localStorage.getItem('jwtToken');
    // AUD-01: Validamos que haya token
    if (!token || token === 'undefined') {
        window.location.href = 'index.html';
        return;
    }

    // AUD-19: Validar si el usuario ya tiene perfil financiero
    // Verificamos intentando consultar el perfil. (Asumiendo que existe un endpoint GET /perfil)
    // Si el backend aún no tiene GET /perfil, esto fallará y forzará a llenarlo.
    await verificarPerfilFinanciero();

    cargarTransacciones();
});

const btnLogout = document.getElementById('btnLogout');
if (btnLogout) {
    btnLogout.addEventListener('click', () => {
        localStorage.removeItem('jwtToken');
        // También limpiamos banderas locales
        localStorage.removeItem('perfilCompletado');
        window.location.href = 'index.html';
    });
}

// ==========================================
// Módulo de Perfil Financiero (AUD-19)
// ==========================================
async function verificarPerfilFinanciero() {
    // Si ya lo completó en esta sesión localmente, lo dejamos pasar
    if (localStorage.getItem('perfilCompletado') === 'true') return;

    // Aquí llamarías a tu API para validar. Por ahora, mostramos el modal directamente 
    // si no tenemos constancia local de que lo haya llenado.
    const modal = new bootstrap.Modal(document.getElementById('modalPerfilIncompleto'));
    modal.show();

    const formPerfil = document.getElementById('formPerfilFinanciero');
    formPerfil.addEventListener('submit', async (e) => {
        e.preventDefault();
        const btnGuardar = document.getElementById('btnGuardarPerfil');
        btnGuardar.disabled = true;
        btnGuardar.innerText = 'Guardando...';

        const payload = {
            ingresoMensual: parseFloat(document.getElementById('perfilIngreso').value),
            lineaCredito: parseFloat(document.getElementById('perfilCredito').value),
            empleoFormal: document.getElementById('perfilEmpleoFormal').checked
        };

        try {
            // Requisito: Endpoint para crear perfil
            const response = await fetchProtected('/perfil', {
                method: 'POST',
                body: JSON.stringify(payload)
            });

            if (response.ok) {
                localStorage.setItem('perfilCompletado', 'true');
                modal.hide();
            } else {
                alert('Hubo un error al guardar tu perfil. Inténtalo de nuevo.');
                btnGuardar.disabled = false;
                btnGuardar.innerText = 'Guardar y Continuar';
            }
        } catch (error) {
            console.error('Error al guardar perfil:', error);
            btnGuardar.disabled = false;
        }
    });
}

// ==========================================
// Módulo de Transacciones (Slice 2)
// ==========================================
async function cargarTransacciones() {
    try {
        // AUD-03: Ruta correcta hacia el backend Java
        const response = await fetchProtected('/transacciones/usuario/transacciones', { method: 'GET' });
        if (response.ok) {
            const transacciones = await response.json();
            renderizarTablaTransacciones(transacciones);
        }
    } catch (error) {
        console.error('Error al cargar transacciones:', error);
    }
}

const formTransaccion = document.getElementById('formTransaccion');
if (formTransaccion) {
    formTransaccion.addEventListener('submit', async (e) => {
        e.preventDefault();

        // AUD-03: Contrato de payload exacto
        const payload = {
            nombre_comercio: document.getElementById('transComercio').value,
            monto_transaccion: parseFloat(document.getElementById('transMonto').value),
            medio_pago: document.getElementById('transMedioPago').value
        };

        try {
            const response = await fetchProtected('/transacciones/usuario/transacciones', {
                method: 'POST',
                body: JSON.stringify(payload)
            });

            if (response.ok) {
                formTransaccion.reset();
                cargarTransacciones(); // Recargar la tabla
            } else {
                alert('Error al guardar la transacción');
            }
        } catch (error) {
            console.error('Error en el registro:', error);
        }
    });
}

function renderizarTablaTransacciones(transacciones) {
    const tbody = document.getElementById('tablaTransaccionesBody');
    if (!tbody) return;
    tbody.innerHTML = '';

    if (!transacciones || transacciones.length === 0) {
        tbody.innerHTML = '<tr><td colspan="3" class="text-center text-muted">Aún no hay transacciones registradas</td></tr>';
        return;
    }

    transacciones.forEach(t => {
        const tr = document.createElement('tr');
        // Usamos los nombres correctos del backend (monto_transaccion, nombre_comercio)
        tr.innerHTML = `
            <td>${t.nombre_comercio || 'Desconocido'}</td>
            <td><span class="badge bg-secondary">${t.medio_pago || 'N/A'}</span></td>
            <td class="text-end fw-bold">$${t.monto_transaccion ? t.monto_transaccion.toFixed(2) : '0.00'}</td>
        `;
        tbody.appendChild(tr);
    });
}

// ==========================================
// Módulo de Análisis IA (Slice 3)
// ==========================================
const btnAnalizar = document.getElementById('btnAnalizar');
if (btnAnalizar) {
    btnAnalizar.addEventListener('click', async () => {
        btnAnalizar.disabled = true;
        btnAnalizar.innerText = 'Consultando a la IA...';

        try {
            // AUD-03: Ajustado al endpoint correcto del backend
            const response = await fetchProtected('/analisis/predict', { method: 'POST' });

            if (response.ok) {
                const resultado = await response.json();
                mostrarResultadosIA(resultado);
            } else {
                alert('No se pudo completar el análisis. Verifica que tengas transacciones registradas.');
            }
        } catch (error) {
            console.error('Error al solicitar análisis:', error);
        } finally {
            btnAnalizar.disabled = false;
            btnAnalizar.innerText = 'Generar Análisis Inteligente';
        }
    });
}

function mostrarResultadosIA(data) {
    const contenedor = document.getElementById('resultadoContenedor');
    if (!contenedor) return;

    // AUD-02: Corregido de 'EN_RIESGO' a 'RIESGO' para alinear con el enum de Java
    let badgeClass = 'bg-secondary';
    if (data.perfil_financiero === 'SALUDABLE') badgeClass = 'bg-success';
    else if (data.perfil_financiero === 'EN_OBSERVACION') badgeClass = 'bg-warning text-dark';
    else if (data.perfil_financiero === 'RIESGO') badgeClass = 'bg-danger';

    contenedor.classList.remove('d-none');
    document.getElementById('iaPerfil').innerHTML = `<span class="badge ${badgeClass} p-2">${data.perfil_financiero || 'DESCONOCIDO'}</span>`;

    if (data.resumen_gastos && data.resumen_gastos.length > 0) {
        const listaRecomendaciones = data.resumen_gastos.map(r => `<li class="list-group-item bg-transparent text-start small">${r}</li>`).join('');
        document.getElementById('iaRecomendaciones').innerHTML = `<ul class="list-group list-group-flush">${listaRecomendaciones}</ul>`;
    } else {
        document.getElementById('iaRecomendaciones').innerHTML = '<p class="text-muted small">No hay datos suficientes para recomendaciones.</p>';
    }
}
</file>

<file path="frontend/dashboard.html">
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FinanceAI - Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

    <nav class="navbar navbar-dark bg-primary shadow-sm">
        <div class="container-fluid">
            <span class="navbar-brand mb-0 h1 fw-bold">FinanceAI - Panel Principal</span>
            <button class="btn btn-outline-light btn-sm" id="btnLogout">Cerrar Sesión</button>
        </div>
    </nav>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8 text-center">
                <div class="card shadow border-0 p-4">
                    <h2 class="text-success mb-3">¡Bienvenido al Dashboard!</h2>
                    <p class="text-muted">La interfaz ha cargado correctamente y la sesión está activa.</p>
                    <hr>
                    <div id="estadoConexion" class="alert alert-info">
                        Verificando conexión con el backend...
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        console.log("¡El dashboard.html se cargó y ejecutó correctamente!");
        
        // Validar si el token existe
        const token = localStorage.getItem('jwtToken');
        const estadoDiv = document.getElementById('estadoConexion');
        
        if (!token || token === 'undefined') {
            estadoDiv.className = "alert alert-danger";
            estadoDiv.innerText = "Advertencia: No se encontró un token JWT válido en el almacenamiento local.";
        } else {
            estadoDiv.className = "alert alert-success";
            estadoDiv.innerText = "Token JWT detectado con éxito. Listo para consumir la API.";
        }

        // Botón de salida
        document.getElementById('btnLogout').addEventListener('click', () => {
            localStorage.removeItem('jwtToken');
            window.location.href = 'index.html';
        });
    </script>
</body>
</html>
</file>

<file path=".gitattributes">
text=auto eol=lf
backend/mvnw text eol=lf
</file>

<file path="README.md">
# FinanceAI
# 🚀 FinanceAI – Asistente Inteligente de Salud Financiera

📋 ## Índice
- [Estado del proyecto](#-estado-del-proyecto)
- [Descripción del proyecto](#-descripción-del-proyecto)
- [Objetivos](#-objetivos)
- [Sector empresarial](#-sector-empresarial)
- [Tecnologías](#%EF%B8%8F-tecnologías)
- [Arquitectura](#-arquitectura)
- [Ejemplo de uso](#-ejemplo-de-uso)
- [Equipo](#-equipo)

---

## 🚧 Estado del proyecto
Actualmente el proyecto se encuentra en fase de planificación y diseño de arquitectura. La implementación se desarrollará durante el Hackathon ONE.

## 📖 Descripción del proyecto
FinanceAI es una solución inteligente orientada a mejorar la salud financiera de los usuarios mediante el análisis automático de sus transacciones y hábitos financieros.
A partir de la información proporcionada por el usuario, el sistema será capaz de analizar su comportamiento financiero y generar información útil que facilite una mejor toma de decisiones.

Entre la información procesada se encuentran:
* Ingreso mensual.
* Nivel de endeudamiento.
* Frecuencia de ahorro.
* Historial de transacciones.
* Descripción y monto de cada gasto.

## 🎯 Objetivos
El proyecto busca desarrollar un MVP capaz de:
* Clasificar automáticamente las transacciones financieras.
* Identificar patrones de consumo.
* Analizar el perfil financiero del usuario.
* Generar recomendaciones personalizadas.
* Exponer los resultados mediante una API REST.
* Integrar al menos un servicio de Oracle Cloud Infrastructure (OCI).

## 🏢 Sector Empresarial
**Fintech · Educación Financiera · Carteras Digitales**  
FinanceAI está dirigido a personas que desean comprender mejor sus hábitos financieros, organizar sus gastos y tomar decisiones más informadas sobre el manejo de su dinero.

## 🛠️ Tecnologías
Actualmente el proyecto contempla el uso de las siguientes tecnologías:

### Backend
* Java 21
* Spring Boot
* Spring Data JPA
* Maven
* Flyway
* Lombok
* Swagger / OpenAPI

### Ciencia de Datos
* Python
* Pandas
* Scikit-Learn
* Jupyter Notebook

### Frontend
* Vue.js

### Infraestructura
La infraestructura del proyecto se encuentra actualmente en definición. Durante el desarrollo del hackathon se seleccionarán los servicios de Oracle Cloud Infrastructure (OCI) que mejor se adapten a las necesidades del proyecto.

## 🏗️ Arquitectura
La solución estará organizada en cuatro módulos principales:
1. **Frontend**, encargado de la interacción con el usuario.
2. **Backend**, responsable de la lógica de negocio y la API REST.
3. **Ciencia de Datos**, donde se desarrollarán y entrenarán los modelos de clasificación y análisis financiero.
4. **Oracle Cloud Infrastructure (OCI)**, utilizado para el almacenamiento, procesamiento o despliegue de la solución.

La arquitectura podrá evolucionar conforme avance el desarrollo del proyecto.

## 💻 Ejemplo de uso

### Endpoint
`POST /api/analisis-financiero`

### Solicitud
```json
{
  "ingreso_mensual": 4500,
  "nivel_endeudamiento": 25,
  "frecuencia_ahorro": "Media",
  "transacciones": [
    {
      "descripcion": "Supermercado",
      "valor": 420
    },
    {
      "descripcion": "Combustible",
      "valor": 300
    },
    {
      "descripcion": "Streaming",
      "valor": 40
    }
  ]
}
Respuesta
JSON
{
  "perfil_financiero": "En observación",
  "probabilidad": 0.82,
  "resumen_gastos": {
    "alimentacion": 420,
    "transporte": 300,
    "entretenimiento": 40
  },
  "recomendaciones": [
    "Monitorear gastos recurrentes de entretenimiento.",
    "Aumentar la reserva financiera mensual."
  ]
}
👥 Equipo
Proyecto desarrollado por el equipo G9-LATAM-Team 47 FinanceAI durante el Hackathon Oracle Next Education (ONE).
</file>

<file path="backend/src/main/java/com/nocountry/financeai/controller/HistorialAnalisisController.java">
package com.nocountry.financeai.controller;

import com.nocountry.financeai.dto.response.HistorialAnalisisResponse;
import com.nocountry.financeai.service.HistorialAnalisisService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/analisis")
@RequiredArgsConstructor
@Tag(
        name = "Historial Resultado Analisis",
        description = "Listado de historiales realizados de un usuario"
)
public class HistorialAnalisisController {
    private final HistorialAnalisisService historialAnalisisService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/usuario/{userId}")
    public List<HistorialAnalisisResponse> obtenerHistorialPorId(@PathVariable Long userId) {
        return historialAnalisisService.obtenerHistorialPorId(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<HistorialAnalisisResponse> obtenerHistorial() {
        return historialAnalisisService.obtenerHistorial();
    }

    @GetMapping("/usuario/historial")
    public List<HistorialAnalisisResponse> obtenerMiHistorial(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return historialAnalisisService.obtenerHistorialAutenticado(userDetails.getUsername());
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/controller/TestSecurityController.java">
package com.nocountry.financeai.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Tag(
        name = "Test",
        description = "Prebas preeliminares del sistema "
)
public class TestSecurityController {

    // Ruta pública (dentro de /auth/**)
    @GetMapping("/auth/ping")
    public ResponseEntity<String> publicPing() {
        return ResponseEntity.ok("Ruta pública OK - Acceso permitido sin token");
    }

    // Ruta protegida
    @GetMapping("/test/protected")
    public ResponseEntity<String> protectedPing() {
        return ResponseEntity.ok("Ruta protegida OK - Requiere token JWT válido");
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/response/AuthResponse.java">
package com.nocountry.financeai.dto.response;

public record AuthResponse(
        String token,
        String message
) {
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/entity/enums/EstadoCivil.java">
package com.nocountry.financeai.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EstadoCivil {
    SOLTERO("SOLTERO"),
    CASADO("CASADO"),
    DIVORCIADO("DIVORCIADO"),
    VIUDO("VIUDO");

    private final String valor;

    EstadoCivil(String valor) {
        this.valor = valor;
    }

    @JsonValue
    public String getValor() {
        return valor;
    }

    @JsonCreator
    public static EstadoCivil fromValor(String valor) {
        if (valor == null || valor.isBlank()) {
            return null;
        }
        String normalized = valor.trim().toUpperCase();
        for (EstadoCivil ec : EstadoCivil.values()) {
            if (ec.name().equalsIgnoreCase(normalized) || ec.valor.equalsIgnoreCase(normalized)) {
                return ec;
            }
        }
        // Fallback flexible para evitar errores 400 por tildes o variaciones
        if (normalized.contains("SOLTERO")) return SOLTERO;
        if (normalized.contains("CASADO")) return CASADO;
        if (normalized.contains("DIVORCIADO")) return DIVORCIADO;
        if (normalized.contains("VIUDO")) return VIUDO;

        throw new IllegalArgumentException("Valor no aceptado para EstadoCivil: " + valor);
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/entity/enums/RangoAhorro.java">
package com.nocountry.financeai.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Rango de ahorro del usuario",
        example = "Alta"
)
public enum RangoAhorro {
    ALTA,
    MEDIA,
    BAJA,
    NINGUNA;

    @JsonCreator
    public static RangoAhorro forString(String value) {
        return RangoAhorro.valueOf(value.trim().toUpperCase());
    }

    @JsonValue
    public String toValue(){
        return this.name().toLowerCase();
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/entity/enums/Sexo.java">
package com.nocountry.financeai.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Sexo {
    MASCULINO("M"),
    FEMENINO("F"); // Corregido el typo AUD-20 (antes FEMININO)

    private final String codigo;

    Sexo(String codigo) {
        this.codigo = codigo;
    }

    // @JsonValue indica que al convertir este Enum a JSON,
    // se debe usar el valor de este metodo ("M" o "F")
    @JsonValue
    public String getCodigo() {
        return codigo;
    }

    // @JsonCreator intercepta el JSON entrante y lo convierte al Enum correcto
    @JsonCreator
    public static Sexo fromCodigo(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (Sexo sexo : Sexo.values()) {
            if (sexo.codigo.equalsIgnoreCase(codigo.trim())) {
                return sexo;
            }
        }
        throw new IllegalArgumentException("Valor no aceptado para Sexo. Se esperaba 'M' o 'F', pero se recibió: " + codigo);
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/entity/HistorialAnalisisEntity.java">
package com.nocountry.financeai.entity;

import com.nocountry.financeai.entity.enums.RangoAhorro;
import com.nocountry.financeai.entity.enums.PerfilFinanciero;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "historial_analisis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class HistorialAnalisisEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UserEntity usuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "perfil_financiero", nullable = false)
    private PerfilFinanciero perfilFinanciero;

    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal probabilidad;

    @Column(name = "nivel_endeudamiento", nullable = false, precision = 4, scale = 2)
    private BigDecimal nivelEndeudamiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "rango_ahorro", nullable = false, length = 20)
    private RangoAhorro rangoAhorro;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "resumen_gastos")
    private Map<String, BigDecimal> resumenGastos;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(nullable = false)
    private List<String> recomendaciones;

    @Column(name = "fecha_analisis", nullable = false, updatable = false)
    private LocalDateTime fechaAnalisis;

    @PrePersist
    protected void onCreate(){
        this.fechaAnalisis = LocalDateTime.now();
    }

}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/entity/TransactionEntity.java">
package com.nocountry.financeai.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.MedioPago;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.*;


@Entity
@Table(name = "transacciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String tipo;

    @Column(length = 50)
    private String categoria;

    @Column(name = "nombre_comercio", nullable = false, length = 255)
    private String nombreComercio;

    @Column(name ="monto_transaccion", nullable = false)
    private BigDecimal montoTransaccion;

    @Enumerated(EnumType.STRING)
    @Column(name = "medio_pago", nullable = false, length = 20)
    private MedioPago medioPago;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UserEntity usuario;
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/impl/HistorialAnalisisServiceImpl.java">
package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.dto.response.HistorialAnalisisResponse;
import com.nocountry.financeai.entity.HistorialAnalisisEntity;
import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.exception.ResourceNotFoundException;
import com.nocountry.financeai.repository.HistorialAnalisisRepository;
import com.nocountry.financeai.repository.UserRepository;
import com.nocountry.financeai.service.HistorialAnalisisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistorialAnalisisServiceImpl implements HistorialAnalisisService {
    private final HistorialAnalisisRepository historialAnalisisRepository;
    private final UserRepository userRepository;
    @Override
    public List<HistorialAnalisisResponse> obtenerHistorialPorId(Long id) {
        return historialAnalisisRepository.findByUsuarioId(id)
                .stream()
                .map(this::convertirRespuesta)
                .toList();
    }

    @Override
    public List<HistorialAnalisisResponse> obtenerHistorial() {

        return historialAnalisisRepository.findAll()
                .stream()
                .map(this::convertirRespuesta)
                .toList();

    }

    @Override
    public List<HistorialAnalisisResponse> obtenerHistorialAutenticado(String email) {
        UserEntity usuario = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        return historialAnalisisRepository.findByUsuarioId(usuario.getId())
                .stream()
                .map(this::convertirRespuesta)
                .toList();
    }

    public HistorialAnalisisResponse convertirRespuesta(HistorialAnalisisEntity historial) {
        return new HistorialAnalisisResponse(
                historial.getPerfilFinanciero(),
                historial.getProbabilidad(),
                historial.getNivelEndeudamiento(),
                historial.getRangoAhorro(),
                historial.getResumenGastos(),
                historial.getRecomendaciones()
        );
    }
}
</file>

<file path="data-science/modeloFinanceAI/main.py">
from fastapi import FastAPI, HTTPException, status
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel, Field
from typing import List, Dict
from contextlib import asynccontextmanager
import pandas as pd
import numpy as np
import joblib
import sklearn
import sklearn.compose._column_transformer

# ==============================================================================
# 1. PARCHE DE COMPATIBILIDAD SKLEARN
# ==============================================================================
if not hasattr(sklearn.compose._column_transformer, '_RemainderColsList'):
    class _RemainderColsList(list):
        pass
    sklearn.compose._column_transformer._RemainderColsList = _RemainderColsList

# ==============================================================================
# 2. CARGA SEGURA DE MODELOS (LIFESPAN)
# ==============================================================================
modelos = {}

@asynccontextmanager
async def lifespan(app: FastAPI):
    # Proceso de arranque (Startup)
    try:
        modelos['transacciones'] = joblib.load('modelo_clasificacion_transacciones.pkl')
        modelos['perfil'] = joblib.load('modelo_perfil_financiero.pkl')
        print("✅ [PROD] Modelos ML cargados exitosamente.")
    except Exception as e:
        print(f"❌ [ERROR CRÍTICO] Fallo al cargar modelos .pkl: {e}")
        raise RuntimeError(f"No se pudieron cargar los modelos en producción: {e}")
    yield
    # Proceso de apagado (Shutdown)
    modelos.clear()

# ==============================================================================
# 3. CREAR LA APLICACIÓN FASTAPI
# ==============================================================================
app = FastAPI(
    title="API Analítica Financiera",
    version="1.0.0",
    lifespan=lifespan
)

# Configuración de CORS para producción / Oracle Cloud
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # En prod estricto, reemplaza "*" por la IP/Dominio de tu Frontend
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# ==============================================================================
# 4. ESTRUCTURA DE DATOS DE ENTRADA (Pydantic Models)
# ==============================================================================
class TransaccionInput(BaseModel):
    nombre_comercio: str = Field(
        ..., 
        example="Uber", 
        description="Nombre del establecimiento o comercio"
    )
    monto_transaccion: float = Field(
        ..., 
        gt=0, 
        example=250.0, 
        description="Monto de la transacción (debe ser mayor a 0)"
    )
    medio_pago: str = Field(
        ..., 
        example="credito", 
        description="Medios aceptados: credito, debito, transaccion, efectivo"
    )

class EntradaUsuario(BaseModel):
    edad: int = Field(..., ge=18, le=120)
    sexo: str
    estado_civil: str
    numero_hijos: int = Field(..., ge=0)
    empleo_formal: int = Field(..., ge=0, le=1)
    ingreso_mensual: float = Field(..., ge=0)
    linea_credito: float = Field(..., ge=0)
    transacciones: List[TransaccionInput] = []

# ==============================================================================
# 5. ENDPOINTS DE PRODUCCIÓN
# ==============================================================================

@app.get("/health", status_code=status.HTTP_200_OK)
def health_check():
    """Endpoint para que Oracle Cloud / Docker verifique si la API está viva"""
    if 'transacciones' not in modelos or 'perfil' not in modelos:
        raise HTTPException(status_code=500, detail="Modelos no inicializados")
    return {"status": "ok", "models_loaded": True}

@app.post("/analisis-financiero")
def analizar_usuario(datos: EntradaUsuario):
    try:
        modelo_perfil = modelos.get('perfil')
        modelo_transacciones = modelos.get('transacciones')

        # ----------------------------------------------------------------------
        # A) CÁLCULO DE GASTOS Y MÉTRICAS FINANCIERAS
        # ----------------------------------------------------------------------
        gasto_total = 0.0
        if datos.transacciones:
            gasto_total = sum([float(tx.monto_transaccion) for tx in datos.transacciones])

        # 1. Nivel de Endeudamiento (escala float 0.0 a 1.0)
        denom_endeudamiento = datos.ingreso_mensual + datos.linea_credito
        if denom_endeudamiento > 0:
            nivel_endeudamiento = round(float(gasto_total / denom_endeudamiento), 2)
        else:
            nivel_endeudamiento = 0.0

        # 2. Rango de Ahorro (String)
        if datos.ingreso_mensual > 0:
            ahorro_bruto = max(datos.ingreso_mensual - gasto_total, 0.0)
            pct_ahorro = ahorro_bruto / datos.ingreso_mensual
        else:
            pct_ahorro = 0.0

        if pct_ahorro >= 0.40:
            rango_ahorro_str = "Alta"
        elif pct_ahorro >= 0.20:
            rango_ahorro_str = "Media"
        elif pct_ahorro > 0:
            rango_ahorro_str = "Baja"
        else:
            rango_ahorro_str = "Ninguna"

# ----------------------------------------------------------------------
        # B) PREDICCIÓN CON MODELO DE PERFIL (.pkl)
        # ----------------------------------------------------------------------
        df_cliente = pd.DataFrame([{
            'edad': int(datos.edad),
            'sexo': str(datos.sexo).lower().strip(),
            'estado_civil': str(datos.estado_civil).lower().strip(),
            'numero_hijos': int(datos.numero_hijos),
            'empleo_formal': int(datos.empleo_formal),
            'ingreso_mensual': float(datos.ingreso_mensual),
            'linea_credito': float(datos.linea_credito),
            'nivel_endeudamiento': float(nivel_endeudamiento),
            'rango_ahorro': float(pct_ahorro)  # Valor decimal menor a 1
        }])

        perfil_pred = modelo_perfil.predict(df_cliente)[0]
        perfil_str = str(perfil_pred).upper().replace(" ", "_")

        # Inicializamos la probabilidad por defecto por seguridad
        probabilidad = 0.85
        try:
            if hasattr(modelo_perfil, "predict_proba"):
                probs = modelo_perfil.predict_proba(df_cliente)[0]
                probabilidad = round(float(np.max(probs)), 2)
        except Exception:
            probabilidad = 0.85

        # ----------------------------------------------------------------------
        # C) CLASIFICACIÓN NLP DE TRANSACCIONES
        # ----------------------------------------------------------------------
        resumen_gastos: Dict[str, float] = {}
        
        if datos.transacciones and len(datos.transacciones) > 0:
            df_tx = pd.DataFrame([
                {
                    'nombre_comercio': str(t.nombre_comercio).lower().strip(),
                    'monto_transaccion': float(t.monto_transaccion)
                }
                for t in datos.transacciones
            ])
            
            # Evaluación defensiva de probabilidades o predicción directa
            try:
                probs_matriz = modelo_transacciones.predict_proba(df_tx)
                clases = modelo_transacciones.classes_
                categorias_finales = []

                for probs in probs_matriz:
                    prob_max = float(np.max(probs))
                    idx_max = int(np.argmax(probs))
                    
                    # Umbral de confianza al 60%
                    if prob_max <= 0.60:
                        categorias_finales.append("otros servicios")
                    else:
                        categorias_finales.append(str(clases[idx_max]))
                
                df_tx['categoria'] = categorias_finales
            except Exception:
                # Si el modelo no soporta predict_proba, realiza la predicción directa
                preds = modelo_transacciones.predict(df_tx)
                df_tx['categoria'] = [str(p) for p in preds]
            
            # Agrupar montos por categoría
            agrupar = df_tx.groupby('categoria')['monto_transaccion'].sum().to_dict()
            resumen_gastos = {str(k).lower(): round(float(v), 2) for k, v in agrupar.items()}

        # ----------------------------------------------------------------------
        # D) GENERACIÓN DE RECOMENDACIONES
        # ----------------------------------------------------------------------
        recomendaciones = []

        if perfil_str == "RIESGOSO" and datos.linea_credito > datos.ingreso_mensual:
            recomendaciones.append(
                "Para aumentar el score del perfil financiero, se recomienda reducir el gasto o incrementar el ingreso mensual"
            )

        if "entretenimiento" in resumen_gastos and resumen_gastos["entretenimiento"] > (datos.ingreso_mensual * 0.15):
            recomendaciones.append("Monitorear los gastos recurrentes de entretenimiento.")

        if nivel_endeudamiento > 0.50:
            recomendaciones.append("Reducir las gastos para bajar el nivel de endeudamiento.")

        if not recomendaciones:
            recomendaciones.append("Mantener los hábitos de gasto actuales y continuar monitoreando el presupuesto.")

        # ----------------------------------------------------------------------
        # E) SALIDA EN FORMATO ESTRICTO
        # ----------------------------------------------------------------------
        return {
            "perfil_financiero": perfil_str,
            "probabilidad": probabilidad,
            "nivel_endeudamiento": nivel_endeudamiento,
            "rango_ahorro": rango_ahorro_str,
            "resumen_gastos": resumen_gastos,
            "recomendaciones": recomendaciones
        }

    except Exception as e:
        raise HTTPException(
            status_code=status.HTTP_500_INTERNAL_SERVER_ERROR,
            detail=f"Error interno en la inferencia del modelo: {str(e)}"
        )

####http://localhost:8000/docs####
</file>

<file path="data-science/modeloFinanceAI/requirements.txt">
fastapi
uvicorn
pandas
scikit-learn==1.6.1
joblib
pydantic
</file>

<file path="backend/src/main/java/com/nocountry/financeai/controller/AuthController.java">
package com.nocountry.financeai.controller;

import com.nocountry.financeai.dto.request.LoginRequest;
import com.nocountry.financeai.dto.request.RegisterRequest;
import com.nocountry.financeai.dto.response.AuthResponse;
import com.nocountry.financeai.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(
        name = "Autenticacion",
        description = "Registro y login de usuarios")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/request/LoginRequest.java">
package com.nocountry.financeai.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @Schema(
                description = "email del usuario",
                example = "carlosgomez@gmail.com"
        )
        @NotBlank(message = "El correo electrónico es obligatorio")
        @Email(message = "El formato del correo es inválido")
        String email,

        @Schema(
                description = "clave del usuario",
                example = "Passwd123*"
        )
        @NotBlank(message = "La contraseña es obligatoria")
       String password
) {
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/response/ErrorResponse.java">
package com.nocountry.financeai.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        int status,
        String error,
        List<String> message,
        LocalDateTime timestamp
) {
    // Constructor secundario inteligente para asignar la fecha y hora automáticamente
    public ErrorResponse(
            int status,
            String error,
            List<String> message
    ) {
        this(status, error, message, LocalDateTime.now());
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/entity/UserEntity.java">
package com.nocountry.financeai.entity;

import com.nocountry.financeai.entity.enums.EstadoCivil;
import com.nocountry.financeai.entity.enums.Rol;
import com.nocountry.financeai.entity.enums.Sexo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"relationLazy"})
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 100)
    private String apellido;

    @Column(name = "documento", nullable = false, unique = true, length = 30)
    private String documento;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_civil", length = 20)
    private EstadoCivil estadoCivil;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Sexo sexo;

    @Column(name = "numero_hijos")
    private Integer numeroHijos;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Rol rol = Rol.USER;

    @Builder.Default
    @Column(nullable = false)
    private Boolean activo = true;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PerfilFinancieroEntity perfilFinanciero;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TransactionEntity> transacciones;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HistorialAnalisisEntity> historialAnalisis;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/repository/UserRepository.java">
package com.nocountry.financeai.repository;

import com.nocountry.financeai.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // Utilizado en el Login para buscar al usuario
    Optional<UserEntity> findByEmail(String email);

    // Busca usuario por documeneto de identidad
    Optional<UserEntity> findByDocumento(String documento);

    // Utilizado en el Registro para evitar correos duplicados
    boolean existsByEmail(String email);

    // verifica si existe un documento
    boolean existsByDocumento(String documento);
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/impl/AnalisisIAServiceImpl.java">
package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.client.IAClient;
import com.nocountry.financeai.dto.request.AnalisisRequest;
import com.nocountry.financeai.dto.request.TransactionRequest;
import com.nocountry.financeai.dto.response.AnalisisResponse;
import com.nocountry.financeai.entity.HistorialAnalisisEntity;
import com.nocountry.financeai.entity.PerfilFinancieroEntity;
import com.nocountry.financeai.entity.TransactionEntity;
import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.entity.enums.PerfilFinanciero;
import com.nocountry.financeai.exception.ResourceNotFoundException;
import com.nocountry.financeai.repository.HistorialAnalisisRepository;
import com.nocountry.financeai.repository.PerfilFinancieroRepository;
import com.nocountry.financeai.repository.TransactionRepository;
import com.nocountry.financeai.repository.UserRepository;
import com.nocountry.financeai.service.AnalisisIAService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalisisIAServiceImpl implements AnalisisIAService {

    private final IAClient iaClient;
    private final UserRepository userRepository;
    private final PerfilFinancieroRepository perfilFinancieroRepository;
    private final TransactionRepository transactionRepository;
    private final HistorialAnalisisRepository historialAnalisisRepository;

    @Override
    public AnalisisResponse analizar(String email) {
        // Su busca el usuario por email, se usa el Id para hacer el analisis
        UserEntity usuario = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuario no encontrado"
                        ));
        return analizarPorUsuarioId(usuario.getId());
    }

    @Override
    public AnalisisResponse analizarPorDocumento(String documento) {
        UserEntity usuario = userRepository
                .findByDocumento(documento)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuario no encontrado"));
        return analizarPorUsuarioId(usuario.getId());
    }

    @Override
    public AnalisisResponse analizarPorUsuarioId(Long usuarioId) {
        // Busca el usuario por el id y se guarda
        UserEntity usuario = userRepository
                .findById(usuarioId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuario no encontrado"));

        // Calcula la edad del usuario
        Integer edad = Period.between(usuario.getFechaNacimiento(), LocalDate.now()).getYears();

        // Busca el perfil financiero asociado al usuario, si no tiene envia exepcion
        PerfilFinancieroEntity perfil = perfilFinancieroRepository
                .findByUsuarioId(usuarioId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "El usuario no tiene un perfil financiero registrado"));

        // Guarda las transacciones de un usuario en una lista
        List<TransactionRequest> transaccionesRequest = transactionRepository
                .findByUsuarioId(usuarioId)
                .stream()
                .map(this::convertirTransaccion)
                .toList();

        if (transaccionesRequest.isEmpty()) {
            throw new IllegalStateException("El usuario debe tener al menos una transacción registrada para generar un análisis");
        }

        // Teniendo tadas las variable para el analisis crea el request
        AnalisisRequest request = convertirAnalisis(edad, usuario, perfil, transaccionesRequest);

        // Envia la peticion para hacer el analisis y guarda la respuesta
        AnalisisResponse response = iaClient.analizar(request);

        // Guarda el analisis al usuario
        guardarHistorial(usuario, response);

        return response;
    }


    // metodos privados para convertir entidad en request

    private TransactionRequest convertirTransaccion(TransactionEntity entity) {
        return new TransactionRequest(
                entity.getNombreComercio(),
                entity.getMontoTransaccion(),
                entity.getMedioPago()
        );
    }

    private AnalisisRequest convertirAnalisis(Integer edad, UserEntity usuario, PerfilFinancieroEntity perfil, List<TransactionRequest> transaccionRequest) {
        return new AnalisisRequest(edad,
                usuario.getSexo(),
                usuario.getEstadoCivil(),
                usuario.getNumeroHijos(),
                perfil.getEmpleoFormal(),
                perfil.getIngresoMensual(),
                perfil.getLineaCredito(),
                transaccionRequest
        );
    }


    // metodo privado de la clase para guarda el historial en la base de datos
    private void guardarHistorial(UserEntity usuario, AnalisisResponse response) {
        HistorialAnalisisEntity historial = HistorialAnalisisEntity.builder()
                .usuario(usuario)
                .perfilFinanciero(response.perfilFinanciero())
                .probabilidad(response.probabilidad())
                .nivelEndeudamiento(response.nivelEndeudamiento())
                .rangoAhorro(response.rangoAhorro())
                .resumenGastos(response.resumenGastos())
                .recomendaciones(response.recomendaciones())
                .build();

        historialAnalisisRepository.save(historial);
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/AnalisisIAService.java">
package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.request.AnalisisRequest;
import com.nocountry.financeai.dto.response.AnalisisResponse;


public interface AnalisisIAService {
    // metodo para hacer el analisis del usuario autenticado
    AnalisisResponse analizar(String email);

    // medodo para hacer el analisis por documento de identificacion
    AnalisisResponse analizarPorDocumento(String documento);

    // metodo para hacer el analisi de un usuario por Id
    AnalisisResponse analizarPorUsuarioId(Long usuarioId);
}
</file>

<file path="backend/src/main/resources/db/migration/V1__create_users_table.sql">
CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100),
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    fecha_nacimiento DATE,
    sexo VARCHAR(20),
    estado_civil VARCHAR(20),
    numero_hijos INTEGER,
    rol VARCHAR(20) DEFAULT 'USER',
    activo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
</file>

<file path="backend/src/main/resources/application.yml">
spring:
  application:
    name: financeai

  datasource:
    url: ${SPRING_DATASOURCE_URL}
    username: ${SPRING_DATASOURCE_USERNAME}
    password: ${SPRING_DATASOURCE_PASSWORD}
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: validate
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
    show-sql: true
    open-in-view: false # Resuelve el hallazgo AUD-11

  flyway:
    enabled: true
    baseline-on-migrate: true

jwt:
  secret: ${JWT_SECRET}
  expiration: ${JWT_EXPIRATION}

ia:
  api:
    url: ${IA_API_URL}

# Configuración de Logs para depuración local
logging:
  level:
    org.springframework.web: INFO
    com.nocountry.financeai: DEBUG
    # Muestra los valores reales inyectados en las sentencias SQL de Hibernate
    org.hibernate.orm.jdbc.bind: TRACE
    org.hibernate.orm.jdbc.extract: TRACE
</file>

<file path="backend/Dockerfile">
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .

# NUEVA LÍNEA: Limpieza de saltos de línea de Windows (CRLF a LF)
RUN sed -i 's/\r$//' mvnw

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests
FROM eclipse-temurin:21-jre
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
</file>

<file path="frontend/js/auth.js">
// ==========================================
// Módulo de Autenticación (Login y Registro)
// ==========================================
const BASE_URL = 'http://localhost:8080/api/v1';

// ==========================================
// 1. Manejo de Inicio de Sesión (Login)
// ==========================================
const formLogin = document.getElementById('formLogin');
if (formLogin) {
    formLogin.addEventListener('submit', async (e) => {
        e.preventDefault();

        const email = document.getElementById('loginEmail').value;
        const password = document.getElementById('loginPassword').value;

        try {
            const response = await fetch(`${BASE_URL}/auth/login`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ email, password })
            });

            if (response.ok) {
                const data = await response.json();

                // El backend devuelve el JWT real en el campo 'token' (AuthResponse.token)
                const token = data.token;

                if (token) {
                    localStorage.setItem('jwtToken', token);
                    // AUD-18: Redirigir al dashboard unificado
                    window.location.href = 'dashboard.html';
                } else {
                    alert('Error crítico: No se encontró el token de acceso en la respuesta.');
                }
            } else {
                alert('Credenciales inválidas o error en el servidor.');
            }
        } catch (error) {
            console.error('Error de red en login:', error);
            alert('No se pudo conectar con el servidor backend.');
        }
    });
}

// ==========================================
// 2. Manejo de Registro y Perfil (AUD-19)
// ==========================================
const formRegister = document.getElementById('formRegister');
if (formRegister) {
    formRegister.addEventListener('submit', async (e) => {
        e.preventDefault();

        // Estructura exigida por RegisterRequest en el backend Java
        const registerPayload = {
            nombre: document.getElementById('regNombre').value,
            apellido: document.getElementById('regApellido').value,
            email: document.getElementById('regEmail').value,
            password: document.getElementById('regPassword').value,
            fechaNacimiento: document.getElementById('regFechaNacimiento').value,
            sexo: document.getElementById('regSexo').value,
            estadoCivil: document.getElementById('regEstadoCivil').value,
            numeroHijos: parseInt(document.getElementById('regNumeroHijos').value || 0)
        };

        // Datos del perfil financiero capturados en el mismo formulario (AUD-19)
        const perfilPayload = {
            ingresoMensual: parseFloat(document.getElementById('regIngresoMensual').value || 0),
            lineaCredito: parseFloat(document.getElementById('regLineaCredito').value || 0),
            empleoFormal: document.getElementById('regEmpleoFormal').checked
        };

        try {
            // Paso A: Registrar usuario en el backend
            const responseReg = await fetch(`${BASE_URL}/auth/register`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(registerPayload)
            });

            if (!responseReg.ok) {
                alert('Error al registrar el usuario. Es posible que el correo ya esté en uso.');
                return;
            }

            const dataReg = await responseReg.json();

            // El backend devuelve el JWT real en el campo 'token' (AuthResponse.token)
            const token = dataReg.token;

            if (token) {
                // Guardar token temporalmente para autenticar la petición de perfil
                localStorage.setItem('jwtToken', token);

                // Paso B: Crear automáticamente el perfil financiero (Solución a AUD-19)
                try {
                    const responsePerfil = await fetch(`${BASE_URL}/perfil`, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                            'Authorization': `Bearer ${token}`
                        },
                        body: JSON.stringify(perfilPayload)
                    });

                    if (responsePerfil.ok) {
                        localStorage.setItem('perfilCompletado', 'true');
                    } else {
                        console.warn('El usuario se creó pero hubo un problema al guardar el perfil financiero inicial.');
                    }
                } catch (perfilError) {
                    console.error('Error de red al crear perfil financiero:', perfilError);
                }

                // Paso C: Redirigir al Dashboard (AUD-18)
                window.location.href = 'dashboard.html';
            } else {
                alert('Registro exitoso, pero no se obtuvo el token. Inicia sesión manualmente.');
                window.location.href = 'index.html';
            }
        } catch (error) {
            console.error('Error general en el registro:', error);
            alert('Ocurrió un error inesperado durante el proceso de registro.');
        }
    });
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/controller/AnalisisController.java">
package com.nocountry.financeai.controller;

import com.nocountry.financeai.dto.response.AnalisisResponse;
import com.nocountry.financeai.service.AnalisisIAService;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/analisis/predict")
@RequiredArgsConstructor
@Tag(
        name = "Analisis",
        description = "Generacion de diagnosticos financieros generados por modelo dataScience"
)
public class AnalisisController {
    private final AnalisisIAService  analisisIAService;

    @PostMapping
    public AnalisisResponse  analizar(@AuthenticationPrincipal UserDetails userDetails) {
        return analisisIAService.analizar(userDetails.getUsername());

    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/request/AnalisisRequest.java">
package com.nocountry.financeai.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.EstadoCivil;
import com.nocountry.financeai.entity.enums.Sexo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;
@Schema(description = "Datos solicitodos al cliente, necesarios para realizar el analisis")
public record AnalisisRequest (
        @NotNull(message = "Edad es obligatoria")
        @Min(value = 18, message = "La edad minima es 18 años")
        Integer edad,

        @NotNull(message = "El sexo es obligatorio")
        Sexo sexo,

        @JsonProperty("estado_civil")
        @NotNull(message = "Estado civil es obligatorio")
        EstadoCivil estadoCivil,

        @JsonProperty("numero_hijos")
        @NotNull(message = "si tiene hijos, indicar cuantos" )
        @Min(value = 0, message = "numero de hijos no puede ser negativo")
        Integer numeroHijos,

        @JsonProperty("empleo_formal")
        @NotNull(message = "si tiene empleo, indicar cuantos")
        @Min(value = 0, message = "El numero de empleos no puede ser negativo")
        Integer empleoFormal,

        @JsonProperty("ingreso_mensual")
        @Schema(
                description = "Ingreso mensual del usuario",
                example = "4500"
        )
        @NotNull(message = "El ingreso mensual es obligatorio")
        @Positive(message = "El ingreso mensual debe ser mayor a cero")
        BigDecimal ingresoMensual,

        @JsonProperty("linea_credito")
        @NotNull(message = "La línea de crédito es obligatoria")
        @DecimalMin(
                value = "0.0",
                inclusive = true,
                message = "La línea de crédito no puede ser negativa"
        )
        BigDecimal lineaCredito,

        @Schema(
                description = "Lista de transacciones que un usuario realiza, Debe incluir minimo una"
        )
        @NotEmpty(message = "Se debe enviar al menos una transaccion")
        List<@Valid TransactionRequest> transacciones
) {}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/request/RegisterRequest.java">
package com.nocountry.financeai.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.EstadoCivil;
import com.nocountry.financeai.entity.enums.Sexo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record RegisterRequest(

        @Schema(
                description = "Nombre del usuario",
                example = "Carlos"
        )
        @NotBlank(message = "El nombre no puede estar vacío")
        String nombre,

        @Schema(
                description = "Apellido del usuario",
                example = "Gómez"
        )
        @NotBlank(message = "El apellido no puede estar vacío")
        String apellido,

        @Schema(
                description = "Documento o Identificacion del usuario",
                example = "PEMJ920323HJCZNN0"
        )
        @NotBlank(message = "El documento es obligatorio")
        @Size(
                min = 5,
                max = 30,
                message = "El documento debe tener minimo 5 y 30 caracteres"
        )
        String documento,

        @Schema(
                description = "Email del usuario",
                example = "carlosgomez@gmail.com"
        )
        @NotBlank(message = "El email no puede estar vacío")
        @Email(message = "El formato del email no es válido")
        String email,

        @Schema(
                description = "Contraseña del usuario",
                example = "Passwd123*"
        )
        @NotBlank(message = "La contraseña no puede estar vacía")
        @Size(
                min = 8,
                message = "La contraseña debe tener al menos 8 caracteres"
        )
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._-]).+$",
                message = "La contraseña debe contener al menos una mayúscula, una minúscula, un número y un carácter especial."
        )
        String password,

        @Schema(
                description = "Fecha de nacimiento del usuario",
                example = "1995-06-15"
        )
        @JsonProperty("fecha_nacimiento")
        @NotNull(message = "La fecha de nacimiento es obligatoria")
        @Past(message = "La fecha de nacimiento debe estar en el pasado")
        LocalDate fechaNacimiento,

        @Schema(
                description = "Sexo del usuario",
                example = "masculino"
        )
        @NotNull(message = "El sexo es obligatorio")
        Sexo sexo,

        @Schema(
                description = "Estado civil del usuario",
                example = "soltero"
        )
        @JsonProperty("estado_civil")
        @NotNull(message = "El estado civil es obligatorio")
        EstadoCivil estadoCivil,

        @Schema(
                description = "Número de hijos del usuario",
                example = "0"
        )
        @JsonProperty("numero_hijos")
        @NotNull(message = "El número de hijos es obligatorio")
        @Min(
                value = 0,
                message = "El número de hijos no puede ser negativo"
        )
        Integer numeroHijos

) {
    @AssertTrue(
            message = "El usuario debe ser mayor de 18 años"
    )
    public boolean esMayorDeEdad() {
        return fechaNacimiento != null
                && !fechaNacimiento.isAfter(
                LocalDate.now().minusYears(18)
        );
    }


}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/request/TransactionRequest.java">
package com.nocountry.financeai.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.MedioPago;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransactionRequest(

        @Schema(
                description = "Nombre del comercio donde se realizó la transacción",
                example = "Supermercado Éxito"
        )
        @JsonProperty("nombre_comercio")
        @NotBlank(message = "El nombre del comercio es obligatorio")
        String nombreComercio,

        @Schema(
                description = "Monto de la transacción",
                example = "210.00"
        )
        @JsonProperty("monto_transaccion")
        @NotNull(message = "El monto de la transacción es obligatorio")
        @Positive(message = "El monto debe ser mayor a cero")
        BigDecimal montoTransaccion,

        @Schema(
                description = "Medio de pago utilizado",
                example = "debito"
        )

        @JsonProperty("medio_pago")
        @NotNull(message = "El medio de pago es obligatorio")
        MedioPago medioPago

) {
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/response/AnalisisResponse.java">
package com.nocountry.financeai.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.PerfilFinanciero;
import com.nocountry.financeai.entity.enums.RangoAhorro;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Schema(description = "Resultado del analisis financiero generado a partir de los datos del usuario")
public record AnalisisResponse(

        @Schema(
                description = "Clasificacion del perfil financiero del usuario segun analisis",
                example = "En_Observacion",
                allowableValues = {"Saludable", "En observacion", "En riesgo" }
        )
        @JsonProperty("perfil_financiero")
        PerfilFinanciero perfilFinanciero,

        @Schema(
                description = "Probabilidad o nivel de confianza del modelo asociad al perlfil asignado, rango de 0 a 1",
                example = "0.82"
        )
        BigDecimal probabilidad,


        @Schema(
                description = "Porcentaje del nivel de endeudamiento de un usuario",
                example = "0.45"
        )
        @JsonProperty("nivel_endeudamiento")
        BigDecimal nivelEndeudamiento,

        @Schema(
                description = "Clasificacion del nivel de ahorro del usuario (Alta, Media, Baja, Ninguna)",
                example = "ALTA"
        )
        @JsonProperty("rango_ahorro")
        RangoAhorro rangoAhorro,

        @Schema(
                description ="Resumen de gastos agrupados por categoria. Las claves del mapa son las categorias detectadas por el modelo",
                example ="{\"alimentacion\": 650, \"transporte\": 360, \"entretenimiento\":70}"
        )
        @JsonProperty("resumen_gastos")
        Map<String, BigDecimal> resumenGastos,

        @Schema(
                description = "lista de recomendaciones financieras generadas por el modelo, para el usuario",
                example = "[\"Monitorear los gastos recurrentes de entretenimiento\", \"Aumentar la reserva financiera mensual\"]"
        )
        List<String> recomendaciones
) {
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/impl/AuthServiceImpl.java">
package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.dto.request.LoginRequest;
import com.nocountry.financeai.dto.request.RegisterRequest;
import com.nocountry.financeai.dto.response.AuthResponse;
import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.exception.UserAlreadyExistsException;
import com.nocountry.financeai.repository.UserRepository;
import com.nocountry.financeai.security.JwtUtil;
import com.nocountry.financeai.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        // 1. Usamos request.email() en vez de getEmail() por ser un record
        if (userRepository.existsByEmail(request.email())) {
            throw new UserAlreadyExistsException("El correo ya esta registrado");
        }
        if (userRepository.existsByDocumento(request.documento())) {
            throw new UserAlreadyExistsException("El documento ya esta registrado");
        }

        // 2. Usamos request.nombre() tal cual lo definiste en tu record
        UserEntity user = UserEntity.builder()
                .nombre(request.nombre())
                .apellido(request.apellido())
                .documento(request.documento())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .fechaNacimiento(request.fechaNacimiento())
                .sexo(request.sexo())
                .estadoCivil(request.estadoCivil())
                .numeroHijos(request.numeroHijos())
                .build();

        userRepository.save(user);

        // 3. Adaptamos el usuario a UserDetails para que el JwtUtil lo acepte sin errores
        UserDetails userDetails = User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(new ArrayList<>())
                .build();

        String token = jwtUtil.generateToken(userDetails);
        return new AuthResponse(token, "Usuario registrado exitosamente");
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        UserEntity user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new BadCredentialsException("Usuario no encontrado"));

        // Adaptamos el usuario autenticado a UserDetails
        UserDetails userDetails = User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(new ArrayList<>())
                .build();

        String token = jwtUtil.generateToken(userDetails);
        return new AuthResponse(token, "Inicio de sesión exitoso");
    }
}
</file>

<file path="frontend/index.html">
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FinanceAI - Acceso</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="bg-light d-flex align-items-center" style="min-height: 100vh;">

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6 col-lg-5">
                <div class="card shadow-sm border-0">
                    <div class="card-body p-4 p-md-5">
                        <div class="text-center mb-4">
                            <h2 class="text-primary fw-bold">FinanceAI</h2>
                            <p class="text-muted">Tu asistente inteligente de salud financiera</p>
                        </div>
                        
                        <div id="alertPlaceholder"></div>    
                        <ul class="nav nav-pills nav-justified mb-4" id="authTabs" role="tablist">
                            <li class="nav-item" role="presentation">
                                <button class="nav-link active" id="login-tab" data-bs-toggle="pill" data-bs-target="#login" type="button" role="tab">Iniciar Sesión</button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link" id="register-tab" data-bs-toggle="pill" data-bs-target="#register" type="button" role="tab">Registrarse</button>
                            </li>
                        </ul>

                        <div class="tab-content" id="authTabsContent">
                            <div class="tab-pane fade show active" id="login" role="tabpanel">
                                <form id="formLogin">
                                    <div class="mb-3">
                                        <label class="form-label text-secondary">Correo Electrónico</label>
                                        <input type="email" id="loginEmail" class="form-control" autocomplete="email" required>
                                    </div>
                                    <div class="mb-4">
                                        <label class="form-label text-secondary">Contraseña</label>
                                        <input type="password" id="loginPassword" class="form-control" autocomplete="current-password" required>
                                    </div>
                                    <button type="submit" class="btn btn-primary w-100 fw-bold">Ingresar</button>
                                </form>
                            </div>

                            <div class="tab-pane fade" id="register" role="tabpanel">
                                <form id="formRegister">
                                    
                                    <h6 class="text-primary border-bottom pb-2 mb-3 mt-2">Credenciales y Datos Personales</h6>
                                    
                                    <div class="row mb-2">
                                        <div class="col">
                                            <label class="form-label text-secondary small">Nombre</label>
                                            <input type="text" id="regNombre" class="form-control form-control-sm" required>
                                        </div>
                                        <div class="col">
                                            <label class="form-label text-secondary small">Apellido</label>
                                            <input type="text" id="regApellido" class="form-control form-control-sm" required>
                                        </div>
                                    </div>
                                    
                                    <div class="mb-2">
                                        <label class="form-label text-secondary small">Correo Electrónico</label>
                                        <input type="email" id="regEmail" class="form-control form-control-sm" autocomplete="email" required>
                                    </div>
                                    
                                    <div class="mb-3">
                                        <label class="form-label text-secondary small">Contraseña</label>
                                        <input type="password" id="regPassword" class="form-control form-control-sm" autocomplete="new-password" required>
                                    </div>

                                    <h6 class="text-primary border-bottom pb-2 mb-3">Perfil Demográfico Inicial</h6>
                                    
                                    <div class="row mb-2">
                                        <div class="col">
                                            <label class="form-label text-secondary small">Fecha Nacimiento</label>
                                            <input type="date" id="regFechaNacimiento" class="form-control form-control-sm" required>
                                        </div>
                                        <div class="col">
                                            <label class="form-label text-secondary small">Sexo</label>
                                            <select id="regSexo" class="form-select form-select-sm" required>
                                                <option value="MASCULINO">Masculino</option>
                                                <option value="FEMININO">Femenino</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <div class="col">
                                            <label class="form-label text-secondary small">Estado Civil</label>
                                            <select id="regEstadoCivil" class="form-select form-select-sm" required>
                                                <option value="SOLTERO">Soltero/a</option>
                                                <option value="CASADO">Casado/a</option>
                                                <option value="DIVORCIADO">Divorciado/a</option>
                                                <option value="VIUDO">Viudo/a</option>
                                            </select>
                                        </div>
                                        <div class="col">
                                            <label class="form-label text-secondary small">N° Hijos</label>
                                            <input type="number" id="regNumeroHijos" class="form-control form-control-sm" value="0" min="0" required>
                                        </div>
                                    </div>

                                    <h6 class="text-primary border-bottom pb-2 mb-3">Perfil Financiero Inicial (AUD-19)</h6>
                                    
                                    <div class="row mb-2">
                                        <div class="col">
                                            <label class="form-label text-secondary small">Ingreso Mensual ($)</label>
                                            <input type="number" step="0.01" id="regIngresoMensual" class="form-control form-control-sm" required>
                                        </div>
                                        <div class="col">
                                            <label class="form-label text-secondary small">Línea de Crédito ($)</label>
                                            <input type="number" step="0.01" id="regLineaCredito" class="form-control form-control-sm" required>
                                        </div>
                                    </div>

                                    <div class="mb-4 form-check">
                                        <input type="checkbox" class="form-check-input" id="regEmpleoFormal">
                                        <label class="form-check-label small text-secondary">¿Tienes empleo formal?</label>
                                    </div>

                                    <button type="submit" class="btn btn-success w-100 fw-bold">Crear Cuenta y Continuar</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="js/auth.js"></script>
</body>
</html>
</file>

<file path="backend/src/main/resources/db/migration/V3__create_analysis_table.sql">
CREATE TABLE historial_analisis (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    perfil_financiero VARCHAR(50) NOT NULL,
    probabilidad DECIMAL(4,2) NOT NULL,
    nivel_endeudamiento INTEGER NOT NULL,
    frecuencia_ahorro VARCHAR(20) NOT NULL,
    resumen_gastos JSONB,
    recomendaciones JSONB NOT NULL,
    fecha_analisis TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_historial_analisis_usuario
        FOREIGN KEY (usuario_id)
            REFERENCES usuarios(id)
);
</file>

<file path="backend/src/main/java/com/nocountry/financeai/controller/TransactionController.java">
package com.nocountry.financeai.controller;

import com.nocountry.financeai.dto.request.TransactionRequest;
import com.nocountry.financeai.dto.response.TransaccionResponse;
import com.nocountry.financeai.service.TransaccionService;
import com.nocountry.financeai.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transacciones")
@Tag(
        name = "Transacciones",
        description = "Registro y consulta de transacciones")
public class TransactionController {
    private final UserService userService;
    private final TransaccionService transaccionService;

    @PostMapping("/usuario/transacciones")
    public TransaccionResponse crearTransaccionAutenticado(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody TransactionRequest transactionRequest
    ) {
        return transaccionService.crearTransaccionAutenticado(
                userDetails.getUsername(),
                transactionRequest
        );
    }

    @GetMapping("/usuario/transacciones")
    public List<TransaccionResponse> obtenerMisTransacciones(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return transaccionService.obtenerTransaccionesAutenticado(userDetails.getUsername());
    }

    @PatchMapping("/usuario/transacciones/{idTransaccion}")
    public TransaccionResponse actualizarTransaccionAutenticado(
            Authentication authentication,
            @PathVariable Long idTransaccion,
            @Valid @RequestBody TransactionRequest transactionRequest) {
        return transaccionService.actualizarTransaccion(authentication.getName(), idTransaccion, transactionRequest);
    }

    @DeleteMapping("/usuario/transacciones/{idTransaccion}")
    public ResponseEntity<Map<String,String>> eliminarTransaccionAutenticado(
            Authentication authentication,
            @PathVariable Long idTransaccion){
        transaccionService.eliminarTransaccion(authentication.getName(), idTransaccion);
        return ResponseEntity.ok(Map.of("message", "Transaccion eliminada correctamente"));
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/exception/ApiExceptionHandler.java">
package com.nocountry.financeai.exception;

import com.nocountry.financeai.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;

import java.time.LocalDateTime;
import java.util.List;


/**
 * Manejo centralizado de excepciones de la API.
 *
 * Ajustado para mantener respuestas HTTP consistentes mediante ResponseEntity
 * y conservar información detallada de errores para el cliente.
 */
@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {
    // Maneja los recursos que no existen y devuelve HTTP 404.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> gestionarRecursoNoEncontrado(
            ResourceNotFoundException ex
    ) {
        log.warn("Recurso no encontrado: {}", ex.getMessage());

        ErrorResponse error = new ErrorResponse(
                404,
                "Recurso no encontrado",
                List.of(ex.getMessage()),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }
    // Maneja conflictos cuando el usuario intenta crear un perfil financiero que ya existe.
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> gestionarEstadoInvalido(
            IllegalStateException ex
    ) {
        log.warn("Conflicto en el estado de la solicitud: {}", ex.getMessage());

        ErrorResponse error = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                List.of(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> gestionarArgumentoInvalido(IllegalArgumentException ex){
        log.warn("Argumento invalido: {}", ex.getMessage());

        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                List.of(),
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    // Maneja errores de acceso por falta de permisos
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> gestionarAccesoInvalido(AccessDeniedException ex){
        log.warn("Acceso invalido: {}", ex.getMessage());
        ErrorResponse error = new ErrorResponse(
                HttpStatus.FORBIDDEN.value(),
                ex.getMessage(),
                List.of(),
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(error);
    }

    // Manejo general de errores no controlados de la aplicación.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> gestionarErrorGeneral(Exception e) {
        log.error("Error interno del servidor", e);

        ErrorResponse error = new ErrorResponse(
                500,
                "Error interno del servidor",
                List.of(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }

    // Ajustado para devolver errores de validación detallados por campo, manteniendo el código HTTP correcto (400 Bad Request).
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> gestionarErroresValidacion(
            MethodArgumentNotValidException ex
    ) {
        log.warn("Se recibieron datos inválidos en la petición");

        List<String> errores = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        ErrorResponse error = new ErrorResponse(
                400,
                "Error de validacion",
                errores,
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    // Ajustado para diferenciar fallos de disponibilidad del serivicio de analisis(mock-api/modelo-dataScienc) mediante respuesta HTTP 503 Service Unavailable.
    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<ErrorResponse> gestionarErrorConexionIA(
            ResourceAccessException ex
    ) {
        log.error("No fue posible conectar con la API de analisis", ex);

        ErrorResponse error = new ErrorResponse(
                503,
                "El servicio de Analisis no esta disponible",
                List.of(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(error);
    }

    // Manejo de credenciales con autenticiacion JWT
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(
            BadCredentialsException ex
    ) {
        log.warn("Intento de login fallido");

        ErrorResponse error = new ErrorResponse(
                401,
                "Credenciales inválidas. Verifica tu correo y contraseña.",
                List.of(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(error);
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/security/SecurityConfig.java">
package com.nocountry.financeai.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        try {
            return http
                    .csrf(csrf -> csrf.disable())
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(auth -> auth
                            // 1. Endpoints Públicos de Autenticación
                            .requestMatchers(
                                    "/api/v1/auth/**"
                            ).permitAll()

                            // 2. Endpoints Públicos de Documentación Swagger / OpenAPI
                            .requestMatchers(
                                    "/v3/api-docs/**",
                                    "/v3/api-docs",
                                    "/swagger-ui/**",
                                    "/swagger-ui.html",
                                    "/swagger-resources/**",
                                    "/webjars/**"
                            ).permitAll()

                            // 3. Cualquier otra ruta requiere Token JWT
                            .anyRequest().authenticated()
                    )
                    // Interceptar peticiones con JwtAuthFilter antes del filtro por defecto de Spring
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                    .build();
        } catch (Exception e) {
            throw new IllegalStateException("Error al configurar el SecurityFilterChain de Spring Security", e);
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
</file>

<file path=".gitignore">
# --- IntelliJ e IDEs ---
.idea/
*.iml
*.iws
*.ipr
out/

# --- Java y Sistemas de Construcción (Maven/Gradle) ---
target/
build/
.gradle/
*.jar
*.war

# --- Sistema Operativo (Linux/Mac) ---
.DS_Store
Thumbs.db
*.log

# --- Infraestructura y Seguridad ---
.env
*.local
application.properties
application-dev.properties
repomix.config.json
__pycache__/
*.pyc
</file>

<file path="docker-compose.yml">
services:
  postgres-db:
    image: postgres:16-alpine
    container_name: financeai_postgres
    environment:
      POSTGRES_DB: ${POSTGRES_DB}
      POSTGRES_USER: ${POSTGRES_USER}
      POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
    networks:
      - financeai-net

  # Mock obsoleto desplazado al puerto 8001
  mock-api:
    build: ./mock-api
    container_name: financeai_mock_api
    ports:
      - "8001:8001"
    networks:
      - financeai-net

  # Nuevo motor de IA real (Canónico)
  modelo-financeai:
    build: ./data-science/modeloFinanceAI
    container_name: financeai_modelo
    ports:
      - "8000:8000"
    networks:
      - financeai-net

  backend:
    build: ./backend
    container_name: financeai_backend
    ports:
      - "8080:8080"
    depends_on:
      - postgres-db
      - modelo-financeai
    environment:
      SPRING_DATASOURCE_URL: ${SPRING_DATASOURCE_URL}
      SPRING_DATASOURCE_USERNAME: ${POSTGRES_USER}
      SPRING_DATASOURCE_PASSWORD: ${POSTGRES_PASSWORD}
      IA_API_URL: ${IA_API_URL}
      JWT_SECRET: ${JWT_SECRET}
      JWT_EXPIRATION: ${JWT_EXPIRATION}
    networks:
      - financeai-net

volumes:
  postgres_data:

networks:
  financeai-net:
    driver: bridge
</file>

<file path="backend/pom.xml">
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>4.1.0</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <groupId>com.nocountry</groupId>
    <artifactId>financeai</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>financeai</name>
    <description>Proyecto FinanceAI</description>

    <properties>
        <java.version>21</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters Core -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>

        <!-- Base de Datos y Migraciones -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-flyway</artifactId>
        </dependency>
        <dependency>
            <groupId>org.flywaydb</groupId>
            <artifactId>flyway-database-postgresql</artifactId>
        </dependency>
        <dependency>
            <groupId>org.flywaydb</groupId>
            <artifactId>flyway-core</artifactId>
        </dependency>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Utilidades -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.12.6</version>
        </dependency>

        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.12.6</version>
            <scope>runtime</scope>
        </dependency>

        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>0.12.6</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>3.0.3</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId> <!-- CORREGIDO: Unificado en el starter oficial -->
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <annotationProcessorPaths>
                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
</file>

</files>
