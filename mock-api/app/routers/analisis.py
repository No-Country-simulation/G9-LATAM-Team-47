from fastapi import APIRouter
from app.models.response import AnalisisFinancieroResponse
from app.services.analisis_service import analizar

router = APIRouter(
    prefix="/predict",
    tags=["Analisis Financiero"]
)
@router.post("", response_model=AnalisisFinancieroResponse)
def analizar_financiero():
    return analizar()
