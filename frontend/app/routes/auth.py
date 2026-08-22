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
            profile = api.get_my_profile()
            session["nombre_usuario"] = profile.get("nombre", "Usuario")
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
