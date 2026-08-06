-- Renombrar la columna frecuencia_ahorro a rango_ahorro para coincidir con la entidad JPA
ALTER TABLE historial_analisis
RENAME COLUMN frecuencia_ahorro TO rango_ahorro;

-- Cambiar el tipo de dato de INTEGER a NUMERIC(4,2) para soportar BigDecimal
ALTER TABLE historial_analisis
ALTER COLUMN nivel_endeudamiento TYPE NUMERIC(4,2);