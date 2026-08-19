CREATE TABLE historial_analisis (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    perfil_financiero VARCHAR(50) NOT NULL,
    probabilidad DECIMAL(4,2) NOT NULL,
    nivel_endeudamiento INTEGER NOT NULL,
    rango_ahorro VARCHAR(20) NOT NULL,
    resumen_gastos JSONB,
    recomendaciones JSONB NOT NULL,
    fecha_analisis TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_historial_analisis_usuario
        FOREIGN KEY (usuario_id)
            REFERENCES usuarios(id)
);