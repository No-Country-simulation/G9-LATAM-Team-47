from decimal import Decimal
from enum import Enum

from pydantic import BaseModel

class PerfilFinanciero(str, Enum):
    SALUDABLE = "SALUDABLE"
    EN_OBSERVACION = "EN_OBSERVACION"
    EN_RIESGO = "EN_RIESGO"

class RangoAhorro(str, Enum):
    ALTA = "ALTA"
    MEDIA = "MEDIA"
    BAJA = "BAJA"
    NINGUNA = "NINGUNA"

class AnalisisFinancieroResponse(BaseModel):
    perfil_financiero: PerfilFinanciero
    probabilidad: Decimal
    nivel_endeudamiento: Decimal
    porcentaje_ahorro: RangoAhorro
    resumen_gastos: dict[str, Decimal]
    recomendaciones: list[str]
