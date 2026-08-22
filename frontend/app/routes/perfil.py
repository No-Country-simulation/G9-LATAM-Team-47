from flask import Blueprint, render_template
from ..decorators import login_required
from ..services.financeai_api import api

perfil_bp = Blueprint("perfil", __name__, url_prefix="/perfil")

@perfil_bp.get("/")
@login_required
def index():
    
    financial_profile = api.get_financial_profile()
    profile = financial_profile

    return render_template(
        "perfil/index.html",
        profile=profile,
        financial_profile=financial_profile
    )