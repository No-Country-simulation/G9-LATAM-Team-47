from pydantic import BaseModel
from decimal import Decimal
from enum import Enum


class FrecuenciaAhorro(str, Enum):
    ALTA = "ALTA"
    MEDIA = "MEDIA"
    BAJA = "BAJA"

class TransaccionRequest(BaseModel):
    descripcion: str
    valor: Decimal

class AnalisisFinancieroRequest(BaseModel):
    ingresoMensual: Decimal
    nivelEndeudamiento: int
    frecuenciaAhorro: FrecuenciaAhorro
    transacciones: list[TransaccionRequest]


