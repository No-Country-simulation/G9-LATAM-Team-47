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
