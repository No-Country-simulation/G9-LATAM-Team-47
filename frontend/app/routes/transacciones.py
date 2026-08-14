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
