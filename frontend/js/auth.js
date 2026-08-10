// ==========================================
// Módulo de Autenticación (Login y Registro)
// ==========================================
const BASE_URL = 'http://localhost:8080/api/v1';

// ==========================================
// 1. Manejo de Inicio de Sesión (Login)
// ==========================================
const formLogin = document.getElementById('formLogin');
if (formLogin) {
    formLogin.addEventListener('submit', async (e) => {
        e.preventDefault();

        const email = document.getElementById('loginEmail').value;
        const password = document.getElementById('loginPassword').value;

        try {
            const response = await fetch(`${BASE_URL}/auth/login`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ email, password })
            });

            if (response.ok) {
                const data = await response.json();

                // El backend devuelve el JWT real en el campo 'token' (AuthResponse.token)
                const token = data.token;

                if (token) {
                    localStorage.setItem('jwtToken', token);
                    // AUD-18: Redirigir al dashboard unificado
                    window.location.href = 'dashboard.html';
                } else {
                    alert('Error crítico: No se encontró el token de acceso en la respuesta.');
                }
            } else {
                alert('Credenciales inválidas o error en el servidor.');
            }
        } catch (error) {
            console.error('Error de red en login:', error);
            alert('No se pudo conectar con el servidor backend.');
        }
    });
}

// ==========================================
// 2. Manejo de Registro y Perfil (AUD-19)
// ==========================================
const formRegister = document.getElementById('formRegister');
if (formRegister) {
    formRegister.addEventListener('submit', async (e) => {
        e.preventDefault();

        // Estructura exigida por RegisterRequest en el backend Java
        const registerPayload = {
            nombre: document.getElementById('regNombre').value,
            apellido: document.getElementById('regApellido').value,
            email: document.getElementById('regEmail').value,
            password: document.getElementById('regPassword').value,
            fechaNacimiento: document.getElementById('regFechaNacimiento').value,
            sexo: document.getElementById('regSexo').value,
            estadoCivil: document.getElementById('regEstadoCivil').value,
            numeroHijos: parseInt(document.getElementById('regNumeroHijos').value || 0)
        };

        // Datos del perfil financiero capturados en el mismo formulario (AUD-19)
        const perfilPayload = {
            ingresoMensual: parseFloat(document.getElementById('regIngresoMensual').value || 0),
            lineaCredito: parseFloat(document.getElementById('regLineaCredito').value || 0),
            empleoFormal: document.getElementById('regEmpleoFormal').checked
        };

        try {
            // Paso A: Registrar usuario en el backend
            const responseReg = await fetch(`${BASE_URL}/auth/register`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(registerPayload)
            });

            if (!responseReg.ok) {
                alert('Error al registrar el usuario. Es posible que el correo ya esté en uso.');
                return;
            }

            const dataReg = await responseReg.json();

            // El backend devuelve el JWT real en el campo 'token' (AuthResponse.token)
            const token = dataReg.token;

            if (token) {
                // Guardar token temporalmente para autenticar la petición de perfil
                localStorage.setItem('jwtToken', token);

                // Paso B: Crear automáticamente el perfil financiero (Solución a AUD-19)
                try {
                    const responsePerfil = await fetch(`${BASE_URL}/perfil`, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                            'Authorization': `Bearer ${token}`
                        },
                        body: JSON.stringify(perfilPayload)
                    });

                    if (responsePerfil.ok) {
                        localStorage.setItem('perfilCompletado', 'true');
                    } else {
                        console.warn('El usuario se creó pero hubo un problema al guardar el perfil financiero inicial.');
                    }
                } catch (perfilError) {
                    console.error('Error de red al crear perfil financiero:', perfilError);
                }

                // Paso C: Redirigir al Dashboard (AUD-18)
                window.location.href = 'dashboard.html';
            } else {
                alert('Registro exitoso, pero no se obtuvo el token. Inicia sesión manualmente.');
                window.location.href = 'index.html';
            }
        } catch (error) {
            console.error('Error general en el registro:', error);
            alert('Ocurrió un error inesperado durante el proceso de registro.');
        }
    });
}
