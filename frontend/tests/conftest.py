import pytest
from app import create_app


class TestConfig:
    TESTING = True
    SECRET_KEY = "test-secret"
    BACKEND_API_URL = "http://backend.test/api/v1"
    REQUEST_TIMEOUT = 1
    SESSION_COOKIE_HTTPONLY = True
    SESSION_COOKIE_SAMESITE = "Lax"
    SESSION_COOKIE_SECURE = False
    WTF_CSRF_ENABLED = False


@pytest.fixture
def app():
    return create_app(TestConfig)


@pytest.fixture
def client(app):
    return app.test_client()
