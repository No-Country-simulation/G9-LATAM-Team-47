from unittest.mock import patch
from app.services.exceptions import AuthenticationError


def test_protected_page_redirects_without_session(client):
    response = client.get("/dashboard/")
    assert response.status_code == 302
    assert "/login" in response.headers["Location"]


def test_login_saves_token(client):
    with patch("app.routes.auth.api.login", return_value={"token": "jwt-test", "message": "ok"}):
        response = client.post("/login", data={"email": "user@example.com", "password": "secret"})
    assert response.status_code == 302
    with client.session_transaction() as session:
        assert session["access_token"] == "jwt-test"


def test_logout_clears_session(client):
    with client.session_transaction() as session:
        session["access_token"] = "jwt-test"
    response = client.post("/logout")
    assert response.status_code == 302
    with client.session_transaction() as session:
        assert "access_token" not in session


def test_401_handler_clears_session(client, app):
    with client.session_transaction() as session:
        session["access_token"] = "expired"
    @app.get("/_test_auth_error")
    def test_error():
        raise AuthenticationError("expirada", 401)
    response = client.get("/_test_auth_error")
    assert response.status_code == 302
    with client.session_transaction() as session:
        assert "access_token" not in session
