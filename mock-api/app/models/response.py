from decimal import Decimal
from enum import Enum
from pydantic import BaseModel

class PerfilFinanciero(str, Enum):
    SALUDABLE = "SALUDABLE"
    EN_OBSERVACION = "EN_OBSERVACION"
    EN_RIESGO = "EN_RIESGO"

class ResumenGastosResponse(BaseModel):
    alimentacion: Decimal
    transporte: Decimal
    entretenimiento: Decimal
    salud: Decimal
    educacion: Decimal
    servicios: Decimal
    otros: Decimal

class AnalisisFinancieroResponse(BaseModel):
    perfilFinanciero: PerfilFinanciero
    probabilidad: Decimal
    resumenGastos: ResumenGastosResponse
    recomendaciones: list[str]