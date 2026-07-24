from decimal import Decimal
from enum import Enum
from pydantic import BaseModel, ConfigDict

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
    probabilidad: float
    resumenGastos: ResumenGastosResponse
    recomendaciones: list[str]

    model_config = ConfigDict(
        json_schema_extra={
            "example": {
                "perfilFinanciero": "SALUDABLE",
                "probabilidad": 0.87,
                "resumenGastos": 0 ,
                "recomendaciones": [
                    "Reducir gastos en entretenimiento.",
                    "Incrementar el ahorro mensual."
                ]
            }
        }
    )

