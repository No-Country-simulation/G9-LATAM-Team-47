from flask import Blueprint, flash, redirect, render_template, url_for
from ..decorators import login_required
from ..services.exceptions import FinanceAIError, ResourceNotFoundError
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


@analisis_bp.get("/ultimo")
@login_required
def latest():
    try:
        result = api.get_latest_analysis()
        return render_template("analisis/resultado.html", result=result)
    except ResourceNotFoundError:
        try:
            result = api.request_analysis()
            flash("Aún no tenías un análisis registrado: se generó uno nuevo con tus datos actuales.", "info")
            return render_template("analisis/resultado.html", result=result)
        except FinanceAIError as error:
            flash(f"El análisis no pudo completarse: {error}", "danger")
            return redirect(url_for("dashboard.index"))
    except FinanceAIError as error:
        flash(f"No fue posible obtener tu análisis: {error}", "danger")
        return redirect(url_for("dashboard.index"))