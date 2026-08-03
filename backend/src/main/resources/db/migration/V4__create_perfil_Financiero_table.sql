CREATE TABLE perfil_financiero (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL UNIQUE,
    empleo_formal INTEGER,
    ingreso_mensual DECIMAL(12,2),
    linea_credito DECIMAL(12,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_perfil_usuario
    FOREIGN KEY (usuario_id)
    REFERENCES usuarios(id)
);