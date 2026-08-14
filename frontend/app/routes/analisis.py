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
