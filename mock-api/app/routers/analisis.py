from fastapi import APIRouter

from app.models.request import AnalisisFinancieroRequest
from app.models.response import AnalisisFinancieroResponse
from app.services.analisis_service import analizar

router = APIRouter(
    prefix="/analisis-financiero",
    tags=["Analisis Financiero"]
)
@router.post("", response_model=AnalisisFinancieroResponse)
def analizar_usuario(
        request:AnalisisFinancieroRequest) -> AnalisisFinancieroResponse:
    return analizar()
