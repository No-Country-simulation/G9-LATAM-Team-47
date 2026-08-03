from decimal import Decimal

from app.models.response import (
    AnalisisFinancieroResponse,
    PerfilFinanciero,
    ResumenGastosResponse,
)

def analizar():
    resumen = ResumenGastosResponse(
        alimentacion=Decimal("300.00"),
        transporte=Decimal("100.00"),
        entretenimiento=Decimal("50.00"),
        salud=Decimal("75.00"),
        educacion=Decimal("550.00"),
        servicios=Decimal("80.00"),
        otros=Decimal("30.00"),
    )

    return AnalisisFinancieroResponse(
        perfilFinanciero=PerfilFinanciero.SALUDABLE,
        probabilidad=Decimal("0.75"),
        resumenGastos=resumen,
        recomendaciones=[
            "Considera aumentar tu ahorro mensual.",
            "Revisa tus gastos en entretenimiento para optimizar tu presupuesto.",
        ],
    )  