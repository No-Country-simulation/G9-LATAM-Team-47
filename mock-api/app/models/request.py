from decimal import Decimal
from enum import Enum

from pydantic import BaseModel


class Sexo(str, Enum):
    MASCULINO = "MASCULINO"
    FEMENINO = "FEMENINO"


class EstadoCivil(str, Enum):
    SOLTERO = "SOLTERO"
    CASADO = "CASADO"
    DIVORCIADO = "DIVORCIADO"
    VIUDO = "VIUDO"


class MedioPago(str, Enum):
    EFECTIVO = "EFECTIVO"
    DEBITO = "DEBITO"
    CREDITO = "CREDITO"
    TRANSFERENCIA = "TRANSFERENCIA"

class TransaccionRequest(BaseModel):
    nombre_comercio: str
    monto_transaccion: Decimal
    medio_pago: MedioPago


class AnalisisFinancieroRequest(BaseModel):
    edad: int
    sexo: Sexo
    estado_civil: EstadoCivil
    numero_hijos: int
    empleo_formal: int
    ingreso_mensual: Decimal
    linea_credito: Decimal
    transacciones: list[TransaccionRequest]
