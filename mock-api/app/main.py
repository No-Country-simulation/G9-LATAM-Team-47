from fastapi import FastAPI
from app.routers.analisis import router

app = FastAPI(
    tittle="Hackathton IA API",
    description="API de analisis financiero",
    version="1.0.0"
)

app.include_router(router)