from decimal import Decimal

from app.models.response import (
    AnalisisFinancieroResponse,
    PerfilFinanciero,
    RangoAhorro,
)

def analizar() -> AnalisisFinancieroResponse:


    resumen = {
        "alimentacion": Decimal("500.00"),
        "transporte": Decimal("150.00"),
        "entretenimiento": Decimal("40.00"),
        "salud": Decimal("75.00"),
        "educacion": Decimal("450.00"),
        "servicios": Decimal("70.00"),
        "otros": Decimal("30.00"),
    }

    return AnalisisFinancieroResponse(
        perfil_financiero=PerfilFinanciero.SALUDABLE,
        probabilidad=Decimal("0.65"),
        nivel_endeudamiento=Decimal("0.45"),
        porcentaje_ahorro=RangoAhorro.MEDIA,
        resumen_gastos=resumen,
        recomendaciones=[
            "Considera aumentar tu ahorro mensual.",
            "Revisa tus gastos en entretenimiento para optimizar tu presupuesto.",
        ],
)
