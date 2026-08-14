from app.services.financeai_api import normalize_analysis, normalize_transaction


def test_transaction_accepts_camel_case():
    value = normalize_transaction({"nombreComercio": "Tienda", "montoTransaccion": 10, "medioPago": "EFECTIVO"})
    assert value["nombre_comercio"] == "Tienda"
    assert value["monto_transaccion"] == 10


def test_analysis_accepts_both_contracts():
    value = normalize_analysis({"perfilFinanciero": "SALUDABLE", "resumenGastos": {"salud": 20}})
    assert value["perfil_financiero"] == "SALUDABLE"
    assert value["resumen_gastos"]["salud"] == 20
