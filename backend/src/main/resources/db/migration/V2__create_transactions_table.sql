CREATE TABLE transacciones (
id BIGSERIAL PRIMARY KEY,
usuario_id BIGINT NOT NULL,
monto NUMERIC(12, 2) NOT NULL,
tipo VARCHAR(10) NOT NULL,
categoria VARCHAR(50) NOT NULL,
descripcion VARCHAR(255),
fecha TIMESTAMP NOT NULL,
CONSTRAINT fk_transacciones_usuario
FOREIGN KEY (usuario_id)
REFERENCES usuarios(id)
ON DELETE CASCADE
);