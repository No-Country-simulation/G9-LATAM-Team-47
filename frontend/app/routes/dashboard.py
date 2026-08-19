from collections import defaultdict
from datetime import datetime

from flask import Blueprint, jsonify, render_template
from ..decorators import login_required
from ..services.exceptions import FinanceAIError, ResourceNotFoundError
from ..services.financeai_api import api
from app.services.exceptions import FinanceAIError, AuthenticationError

dashboard_bp = Blueprint("dashboard", __name__, url_prefix="/dashboard")

_MESES = ["Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"]


def _parse_fecha(value):
    if not value:
        return None
    try:
        return datetime.fromisoformat(str(value).replace("Z", ""))
    except ValueError:
        return None


def _monthly_series(transactions):
    totals = defaultdict(float)
    for tx in transactions:
        fecha = _parse_fecha(tx.get("fecha"))
        if not fecha:
            continue
        totals[(fecha.year, fecha.month)] += float(tx.get("monto_transaccion") or 0)
    ordered_keys = sorted(totals.keys())[-6:]
    labels = [_MESES[m - 1] for (_, m) in ordered_keys]
    values = [round(totals[k], 2) for k in ordered_keys]
    return labels, values


def _payment_series(transactions):
    totals = defaultdict(float)
    for tx in transactions:
        medio = tx.get("medio_pago") or "Sin especificar"
        totals[medio] += float(tx.get("monto_transaccion") or 0)
    labels = list(totals.keys())
    values = [round(totals[k], 2) for k in labels]
    return labels, values


def _error_json(error, default_status=502):
    status = getattr(error, "status_code", None) or default_status
    return jsonify({"error": str(error)}), status


@dashboard_bp.get("/")
@login_required
def index():
    transactions, warning = [], None
    try:
        transactions = api.list_transactions()
    except AuthenticationError:
        # Si el token es inválido o el usuario ya no existe,
        # lanzamos el error para que el manejador global limpie la sesión.
        raise
    except FinanceAIError as error:
        # Cualquier otro error (ej. base de datos lenta) se muestra como advertencia
        warning = str(error)

    total = sum(float(item.get("monto_transaccion") or 0) for item in transactions)
    return render_template("dashboard/index.html", transactions=transactions[:5], total=total, warning=warning)


@dashboard_bp.get("/data")
@login_required
def data():
    try:
        transactions = api.list_transactions()
    except FinanceAIError as error:
        return _error_json(error)

    total_gastado = sum(float(t.get("monto_transaccion") or 0) for t in transactions)
    line_labels, line_values = _monthly_series(transactions)
    pay_labels, pay_values = _payment_series(transactions)
    ordenadas = sorted(transactions, key=lambda t: t.get("fecha") or "", reverse=True)

    try:
        analysis = api.get_latest_analysis()
    except (ResourceNotFoundError, FinanceAIError):
        analysis = None

    resumen_gastos = (analysis or {}).get("resumen_gastos") or {}

    return jsonify({
        "kpis": {
            "transacciones": len(transactions),
            "gasto_total": f"${total_gastado:,.2f}",
            "perfil_financiero": (analysis or {}).get("perfil_financiero") or "Sin datos",
            "rango_ahorro": (analysis or {}).get("rango_ahorro") or "N/D",
        },
        "chart": {"labels": line_labels, "values": line_values},
        "pie_chart": {"labels": list(resumen_gastos.keys()), "values": [float(v) for v in resumen_gastos.values()]},
        "payment_chart": {"labels": pay_labels, "values": pay_values},
        "transactions": [
            {"nombre": t.get("nombre_comercio"), "fecha": t.get("fecha"), "monto": f"${float(t.get('monto_transaccion') or 0):,.2f}"}
            for t in ordenadas[:5]
        ],
        "all_transactions": [
            {"nombre": t.get("nombre_comercio"), "fecha": t.get("fecha"), "medio_pago": t.get("medio_pago"),
             "monto": f"${float(t.get('monto_transaccion') or 0):,.2f}"}
            for t in ordenadas
        ],
        "payment_methods": pay_labels,
    })


@dashboard_bp.get("/analisis-data")
@login_required
def analisis_data():
    try:
        analysis = api.get_latest_analysis()
    except ResourceNotFoundError:
        try:
            analysis = api.request_analysis()
        except FinanceAIError as error:
            return jsonify({"existe": False, "mensaje": str(error)})
    except FinanceAIError as error:
        return _error_json(error)

    try:
        transactions = api.list_transactions()
    except FinanceAIError:
        transactions = []
    line_labels, line_values = _monthly_series(transactions)
    pay_labels, pay_values = _payment_series(transactions)
    resumen_gastos = analysis.get("resumen_gastos") or {}
    top_categoria = max(resumen_gastos, key=resumen_gastos.get) if resumen_gastos else None

    return jsonify({
        "existe": True,
        "perfil_financiero": analysis.get("perfil_financiero"),
        "probabilidad": analysis.get("probabilidad"),
        "nivel_endeudamiento": analysis.get("nivel_endeudamiento"),
        "rango_ahorro": analysis.get("rango_ahorro"),
        "recomendaciones": analysis.get("recomendaciones") or [],
        "top_categoria": top_categoria,
        "top_categoria_monto": resumen_gastos.get(top_categoria) if top_categoria else None,
        "chart": {"labels": line_labels, "values": line_values},
        "pie_chart": {"labels": list(resumen_gastos.keys()), "values": [float(v) for v in resumen_gastos.values()]},
        "payment_chart": {"labels": pay_labels, "values": pay_values},
    })


@dashboard_bp.get("/perfil-data")
@login_required
def perfil_data():
    try:
        perfil = api.get_my_profile()
    except FinanceAIError as error:
        return _error_json(error)
    return jsonify(perfil)


@dashboard_bp.get("/historial-data")
@login_required
def historial_data():
    try:
        items = api.list_history()
    except FinanceAIError as error:
        return _error_json(error)
    return jsonify({"items": items})

@dashboard_bp.route('/perfil-financiero-data')
@login_required
def perfil_financiero_data():
    try:
        data = api.get_financial_profile()
        return jsonify(data)
    except Exception as e:
        return jsonify({"error": str(e)}), 500