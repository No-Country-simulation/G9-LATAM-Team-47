// ==========================================
// Configuración de la API
// ==========================================
const API_BASE_URL = 'http://localhost:8080/api/v1/auth';

// ==========================================
// Referencias al DOM
// ==========================================
const loginForm = document.getElementById('loginForm');
const registerForm = document.getElementById('registerForm');
const alertPlaceholder = document.getElementById('alertPlaceholder');

// ==========================================
// Utilidad: Mostrar Alertas de Bootstrap
// ==========================================
const showAlert = (message, type) => {
    const wrapper = document.createElement('div');
    wrapper.innerHTML = [
        `<div class="alert alert-${type} alert-dismissible fade show" role="alert">`,
        `   <div>${message}</div>`,
        '   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>',
        '</div>'
    ].join('');

    alertPlaceholder.innerHTML = ''; // Limpiar alertas previas
    alertPlaceholder.append(wrapper);
};

// ==========================================
// Lógica de Inicio de Sesión (Login)
// ==========================================
if (loginForm) {
    loginForm.addEventListener('submit', async (e) => {
        e.preventDefault();

        const email = document.getElementById('loginEmail').value;
        const password = document.getElementById('loginPassword').value;

        try {
            const response = await fetch(`${API_BASE_URL}/login`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ email, password })
            });

            if (response.ok) {
                const data = await response.json();
                // Guardar JWT en LocalStorage
                localStorage.setItem('jwtToken', data.token);
                showAlert('¡Acceso exitoso! Redirigiendo...', 'success');

                // Redirección al dashboard tras medio segundo
                setTimeout(() => {
                    window.location.href = 'dashboard.html';
                }, 500);
            } else if (response.status === 401) {
                showAlert('Credenciales inválidas. Por favor, verifica tu correo y contraseña.', 'danger');
            } else {
                showAlert('Ocurrió un error al intentar iniciar sesión.', 'warning');
            }
        } catch (error) {
            console.error('Error de red:', error);
            showAlert('No se pudo conectar con el servidor. Verifica que el backend esté en ejecución.', 'danger');
        }
    });
}

// ==========================================
// Lógica de Registro de Usuario
// ==========================================
if (registerForm) {
    registerForm.addEventListener('submit', async (e) => {
        e.preventDefault();

        // Construir el payload respetando el DTO de Spring Boot
        const payload = {
            email: document.getElementById('regEmail').value,
            password: document.getElementById('regPassword').value,
            edad: parseInt(document.getElementById('regEdad').value),
            sexo: document.getElementById('regSexo').value,
            estadoCivil: document.getElementById('regEstadoCivil').value,
            numeroHijos: parseInt(document.getElementById('regHijos').value),
            ingresoMensual: parseFloat(document.getElementById('regIngreso').value),
            lineaCredito: parseFloat(document.getElementById('regCredito').value),
            empleoFormal: document.getElementById('regEmpleoFormal').checked
        };

        try {
            const response = await fetch(`${API_BASE_URL}/register`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(payload)
            });

            if (response.ok) {
                const data = await response.json();
                // Guardar JWT en LocalStorage
                localStorage.setItem('jwtToken', data.token);
                showAlert('¡Cuenta creada exitosamente! Redirigiendo a tu panel...', 'success');

                // Redirección al dashboard
                setTimeout(() => {
                    window.location.href = 'dashboard.html';
                }, 1000);
            } else if (response.status === 400 || response.status === 409) {
                // Captura el error de conflicto (ej. correo ya registrado)
                const errorData = await response.json();
                showAlert(errorData.message || 'El correo electrónico ya está registrado o los datos son inválidos.', 'danger');
            } else {
                showAlert('Ocurrió un error al crear la cuenta. Inténtalo de nuevo.', 'warning');
            }
        } catch (error) {
            console.error('Error de red:', error);
            showAlert('No se pudo conectar con el servidor. Verifica que el backend esté en ejecución.', 'danger');
        }
    });
}