from flask import Blueprint, render_template
from ..decorators import login_required
from ..services.financeai_api import api

historial_bp = Blueprint("historial", __name__, url_prefix="/historial")


@historial_bp.get("/")
@login_required
def index():
    return render_template("historial/index.html", items=api.list_history())
