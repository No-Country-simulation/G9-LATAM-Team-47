// ==========================================
// Configuración y Utilidades Base de la API
// ==========================================
const BASE_URL = 'http://localhost:8080/api/v1';

/**
 * Función genérica (fetch wrapper) para consumir endpoints protegidos.
 * Inyecta automáticamente el token JWT en las cabeceras.
 */
async function fetchProtected(endpoint, options = {}) {
    const token = localStorage.getItem('jwtToken');

    if (!token) {
        console.warn("No hay sesión activa. Redirigiendo...");
        window.location.href = 'index.html';
        return null;
    }

    const defaultHeaders = {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
    };

    const config = {
        ...options,
        headers: {
            ...defaultHeaders,
            ...options.headers
        }
    };

    try {
        const response = await fetch(`${BASE_URL}${endpoint}`, config);

        // Si el token expiró o es inválido, Spring Boot devolverá 401 o 403
        if (response.status === 401 || response.status === 403) {
            localStorage.removeItem('jwtToken');
            window.location.href = 'index.html';
            throw new Error('Sesión expirada o no autorizada');
        }

        return response;
    } catch (error) {
        console.error('Error en fetchProtected:', error);
        throw error;
    }
}