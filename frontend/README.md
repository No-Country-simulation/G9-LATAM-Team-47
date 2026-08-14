# FinanceAI Frontend Flask

Capa de presentación Flask para consumir el backend Spring Boot de FinanceAI. No accede directamente a PostgreSQL ni a FastAPI.

## Funciones incluidas

- Registro y login contra Spring Boot.
- JWT guardado en la sesión de Flask.
- Creación de perfil financiero después del registro.
- Dashboard, alta y consulta de transacciones.
- Vistas preparadas para análisis e historial.
- Normalización temporal de respuestas camelCase/snake_case.
- Manejo centralizado de 400, 401, 403, 404, 409, 5xx y timeouts.

## Ejecución local

```bash
python -m venv .venv
```

Windows:

```powershell
.venv\Scripts\Activate.ps1
pip install -r requirements.txt
Copy-Item .env.example .env
flask --app run.py run --debug
```

Define `FLASK_SECRET_KEY` y confirma que `BACKEND_API_URL` apunte a Spring Boot.

## Pruebas

```bash
pytest -q
```

## Pendientes externos

El análisis y el historial requieren que Spring Boot y el motor de IA resuelvan la ruta y el contrato de respuesta. La ausencia de `GET /api/v1/perfil` impide consultar el perfil existente, pero no bloquea su creación.
