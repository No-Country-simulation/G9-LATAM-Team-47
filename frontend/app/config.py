import os
from datetime import timedelta


def _as_bool(value: str | None, default=False):
    if value is None:
        return default
    return value.strip().lower() in {"1", "true", "yes", "on"}


class Config:
    SECRET_KEY = os.getenv("FLASK_SECRET_KEY")
    BACKEND_API_URL = os.getenv("BACKEND_API_URL", "http://localhost:8080/api/v1").rstrip("/")
    REQUEST_TIMEOUT = float(os.getenv("REQUEST_TIMEOUT", "10"))
    SESSION_COOKIE_HTTPONLY = True
    SESSION_COOKIE_SAMESITE = "Lax"
    SESSION_COOKIE_SECURE = _as_bool(os.getenv("SESSION_COOKIE_SECURE"), False)
    PERMANENT_SESSION_LIFETIME = timedelta(hours=8)
    WTF_CSRF_TIME_LIMIT = timedelta(hours=8)
    DEBUG = _as_bool(os.getenv("FLASK_DEBUG"), False)
