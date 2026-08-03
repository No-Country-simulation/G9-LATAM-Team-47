CREATE TABLE transacciones (
id BIGSERIAL PRIMARY KEY,
usuario_id BIGINT NOT NULL,
monto_transaccion NUMERIC(12, 2) NOT NULL,
tipo VARCHAR(10),
categoria VARCHAR(50),
nombre_comercio VARCHAR(255),
medio_pago VARCHAR(20) NOT NULL,
fecha TIMESTAMP NOT NULL,
CONSTRAINT fk_transacciones_usuario
FOREIGN KEY (usuario_id)
REFERENCES usuarios(id)
ON DELETE CASCADE
);