-- V6: Agrega la columna 'documento' a la tabla usuarios.
-- Requerida por UserEntity.documento, RegisterRequest.documento,
-- UserRepository.findByDocumento/existsByDocumento e IniciarAdmin.
-- No se toca V1 (ya pudo haberse aplicado en otros ambientes).
--
-- Se agrega primero sin restricciones para no romper filas ya existentes
-- (ej. si el admin de IniciarAdmin llegó a crearse antes de este fix),
-- se rellena con un valor temporal único por fila, y recién ahí se
-- aplican NOT NULL y UNIQUE.

ALTER TABLE usuarios
    ADD COLUMN documento VARCHAR(30);

UPDATE usuarios
SET documento = 'TEMP_' || id
WHERE documento IS NULL;

ALTER TABLE usuarios
    ALTER COLUMN documento SET NOT NULL;

ALTER TABLE usuarios
    ADD CONSTRAINT uk_usuarios_documento UNIQUE (documento);