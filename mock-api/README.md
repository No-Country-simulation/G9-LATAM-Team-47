# FinanceAI - Mock API

## Descripción

Microservicio desarrollado con FastAPI que simula el servicio de Inteligencia Artificial utilizado por FinanceAI.


---

## Tecnologías

- Python 3.13
- FastAPI
- Pydantic v2
- Uvicorn

---

## Crear entorno virtual

```bash
python -m venv .venv
```

### Linux

```bash
source .venv/bin/activate
```

### Windows

```bash
.venv\Scripts\activate
```

---

## Instalar dependencias

```bash
pip install fastapi uvicorn pydantic
```

o las que realmente estés usando (`scikit-learn`, `joblib`, etc., cuando ya entren al proyecto).

---

## Ejecutar

```bash
uvicorn app.main:app --reload
```

La API estará disponible en:

```text
http://localhost:8000
```

---

## Documentación

Swagger

```text
http://localhost:8000/docs
```

OpenAPI

```text
http://localhost:8000/openapi.json
```

---

## Endpoint disponible

### POST `/predict`

Genera un diagnóstico financiero simulado.

---

## Estado del proyecto

- ✔ Mock API implementada.
- ✔ Documentación OpenAPI.
- ✔ Lista para integración con Spring Boot.
- 🔄 Pendiente integración del modelo real.