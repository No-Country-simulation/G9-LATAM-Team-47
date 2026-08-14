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
