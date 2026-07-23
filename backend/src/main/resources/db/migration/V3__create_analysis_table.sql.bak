CREATE TABLE historial_analisis (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT,
    perfil_financiero VARCHAR(50),
    probabilidad DECIMAL(4,2),
    resumen_gastos JSONB,
    recomendaciones JSONB,
    fecha_analisis TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);