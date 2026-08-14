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
