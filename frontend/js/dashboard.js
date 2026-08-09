// ==========================================
// Configuración e Inicio
// ==========================================
// Asumiendo que `fetchProtected` está en api.js. Si no, asegúrate de que agregue la URL base '/api/v1' y el Header de Autorización.

document.addEventListener('DOMContentLoaded', async () => {
    const token = localStorage.getItem('jwtToken');
    // AUD-01: Validamos que haya token
    if (!token || token === 'undefined') {
        window.location.href = 'index.html';
        return;
    }

    // AUD-19: Validar si el usuario ya tiene perfil financiero
    // Verificamos intentando consultar el perfil. (Asumiendo que existe un endpoint GET /perfil)
    // Si el backend aún no tiene GET /perfil, esto fallará y forzará a llenarlo.
    await verificarPerfilFinanciero();

    cargarTransacciones();
});

const btnLogout = document.getElementById('btnLogout');
if (btnLogout) {
    btnLogout.addEventListener('click', () => {
        localStorage.removeItem('jwtToken');
        // También limpiamos banderas locales
        localStorage.removeItem('perfilCompletado');
        window.location.href = 'index.html';
    });
}

// ==========================================
// Módulo de Perfil Financiero (AUD-19)
// ==========================================
async function verificarPerfilFinanciero() {
    // Si ya lo completó en esta sesión localmente, lo dejamos pasar
    if (localStorage.getItem('perfilCompletado') === 'true') return;

    // Aquí llamarías a tu API para validar. Por ahora, mostramos el modal directamente 
    // si no tenemos constancia local de que lo haya llenado.
    const modal = new bootstrap.Modal(document.getElementById('modalPerfilIncompleto'));
    modal.show();

    const formPerfil = document.getElementById('formPerfilFinanciero');
    formPerfil.addEventListener('submit', async (e) => {
        e.preventDefault();
        const btnGuardar = document.getElementById('btnGuardarPerfil');
        btnGuardar.disabled = true;
        btnGuardar.innerText = 'Guardando...';

        const payload = {
            ingresoMensual: parseFloat(document.getElementById('perfilIngreso').value),
            lineaCredito: parseFloat(document.getElementById('perfilCredito').value),
            empleoFormal: document.getElementById('perfilEmpleoFormal').checked
        };

        try {
            // Requisito: Endpoint para crear perfil
            const response = await fetchProtected('/perfil', {
                method: 'POST',
                body: JSON.stringify(payload)
            });

            if (response.ok) {
                localStorage.setItem('perfilCompletado', 'true');
                modal.hide();
            } else {
                alert('Hubo un error al guardar tu perfil. Inténtalo de nuevo.');
                btnGuardar.disabled = false;
                btnGuardar.innerText = 'Guardar y Continuar';
            }
        } catch (error) {
            console.error('Error al guardar perfil:', error);
            btnGuardar.disabled = false;
        }
    });
}

// ==========================================
// Módulo de Transacciones (Slice 2)
// ==========================================
async function cargarTransacciones() {
    try {
        // AUD-03: Ruta correcta hacia el backend Java
        const response = await fetchProtected('/transacciones/usuario/transacciones', { method: 'GET' });
        if (response.ok) {
            const transacciones = await response.json();
            renderizarTablaTransacciones(transacciones);
        }
    } catch (error) {
        console.error('Error al cargar transacciones:', error);
    }
}

const formTransaccion = document.getElementById('formTransaccion');
if (formTransaccion) {
    formTransaccion.addEventListener('submit', async (e) => {
        e.preventDefault();

        // AUD-03: Contrato de payload exacto
        const payload = {
            nombre_comercio: document.getElementById('transComercio').value,
            monto_transaccion: parseFloat(document.getElementById('transMonto').value),
            medio_pago: document.getElementById('transMedioPago').value
        };

        try {
            const response = await fetchProtected('/transacciones/usuario/transacciones', {
                method: 'POST',
                body: JSON.stringify(payload)
            });

            if (response.ok) {
                formTransaccion.reset();
                cargarTransacciones(); // Recargar la tabla
            } else {
                alert('Error al guardar la transacción');
            }
        } catch (error) {
            console.error('Error en el registro:', error);
        }
    });
}

function renderizarTablaTransacciones(transacciones) {
    const tbody = document.getElementById('tablaTransaccionesBody');
    if (!tbody) return;
    tbody.innerHTML = '';

    if (!transacciones || transacciones.length === 0) {
        tbody.innerHTML = '<tr><td colspan="3" class="text-center text-muted">Aún no hay transacciones registradas</td></tr>';
        return;
    }

    transacciones.forEach(t => {
        const tr = document.createElement('tr');
        // Usamos los nombres correctos del backend (monto_transaccion, nombre_comercio)
        tr.innerHTML = `
            <td>${t.nombre_comercio || 'Desconocido'}</td>
            <td><span class="badge bg-secondary">${t.medio_pago || 'N/A'}</span></td>
            <td class="text-end fw-bold">$${t.monto_transaccion ? t.monto_transaccion.toFixed(2) : '0.00'}</td>
        `;
        tbody.appendChild(tr);
    });
}

// ==========================================
// Módulo de Análisis IA (Slice 3)
// ==========================================
const btnAnalizar = document.getElementById('btnAnalizar');
if (btnAnalizar) {
    btnAnalizar.addEventListener('click', async () => {
        btnAnalizar.disabled = true;
        btnAnalizar.innerText = 'Consultando a la IA...';

        try {
            // AUD-03: Ajustado al endpoint correcto del backend
            const response = await fetchProtected('/analisis/predict', { method: 'POST' });

            if (response.ok) {
                const resultado = await response.json();
                mostrarResultadosIA(resultado);
            } else {
                alert('No se pudo completar el análisis. Verifica que tengas transacciones registradas.');
            }
        } catch (error) {
            console.error('Error al solicitar análisis:', error);
        } finally {
            btnAnalizar.disabled = false;
            btnAnalizar.innerText = 'Generar Análisis Inteligente';
        }
    });
}

function mostrarResultadosIA(data) {
    const contenedor = document.getElementById('resultadoContenedor');
    if (!contenedor) return;

    // AUD-02: Corregido de 'EN_RIESGO' a 'RIESGO' para alinear con el enum de Java
    let badgeClass = 'bg-secondary';
    if (data.perfil_financiero === 'SALUDABLE') badgeClass = 'bg-success';
    else if (data.perfil_financiero === 'EN_OBSERVACION') badgeClass = 'bg-warning text-dark';
    else if (data.perfil_financiero === 'RIESGO') badgeClass = 'bg-danger';

    contenedor.classList.remove('d-none');
    document.getElementById('iaPerfil').innerHTML = `<span class="badge ${badgeClass} p-2">${data.perfil_financiero || 'DESCONOCIDO'}</span>`;

    if (data.resumen_gastos && data.resumen_gastos.length > 0) {
        const listaRecomendaciones = data.resumen_gastos.map(r => `<li class="list-group-item bg-transparent text-start small">${r}</li>`).join('');
        document.getElementById('iaRecomendaciones').innerHTML = `<ul class="list-group list-group-flush">${listaRecomendaciones}</ul>`;
    } else {
        document.getElementById('iaRecomendaciones').innerHTML = '<p class="text-muted small">No hay datos suficientes para recomendaciones.</p>';
    }
}