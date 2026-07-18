-- 1. Tabla de Usuarios
CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Tabla de Transacciones (Ingresos y Gastos)
CREATE TABLE transacciones (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    monto NUMERIC(12, 2) NOT NULL, -- Crucial para finanzas: soporta hasta 99,999,999.99 sin perder decimales
    tipo VARCHAR(10) NOT NULL,     -- 'INGRESO' o 'GASTO'
    categoria VARCHAR(50) NOT NULL, -- 'ALIMENTACION', 'TRANSPORTE', 'VIVIENDA', etc.
    descripcion VARCHAR(255),
    fecha TIMESTAMP NOT NULL,
    CONSTRAINT fk_transacciones_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- 3. Tabla de Historial de Análisis de IA (Para guardar lo que responde el modelo)
CREATE TABLE historial_analisis (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    perfil_financiero VARCHAR(50) NOT NULL, -- 'SALUDABLE', 'EN_OBSERVACION', 'RIESGO'
    probabilidad NUMERIC(5, 4) NOT NULL,    -- Confianza del modelo (ej: 0.8500)
    recomendaciones TEXT NOT NULL,          -- Guardaremos las sugerencias como texto largo o JSON
    fecha_analisis TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_analisis_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);