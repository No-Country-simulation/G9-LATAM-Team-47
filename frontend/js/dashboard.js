// ==========================================
// Protección de Ruta y Cierre de Sesión
// ==========================================
document.addEventListener('DOMContentLoaded', () => {
    // Verificar si el usuario está autenticado al cargar el dashboard
    if (!localStorage.getItem('jwtToken')) {
        window.location.href = 'index.html';
    }

    // Cargar los datos iniciales
    cargarTransacciones();
});

const btnLogout = document.getElementById('btnLogout');
if (btnLogout) {
    btnLogout.addEventListener('click', () => {
        localStorage.removeItem('jwtToken');
        window.location.href = 'index.html';
    });
}

// ==========================================
// Módulo de Transacciones (Slice 2)
// ==========================================

// Obtener y pintar transacciones
async function cargarTransacciones() {
    try {
        const response = await fetchProtected('/transactions', { method: 'GET' });
        if (response.ok) {
            const transacciones = await response.json();
            renderizarTablaTransacciones(transacciones);
        }
    } catch (error) {
        console.error('Error al cargar transacciones:', error);
    }
}

// Enviar nueva transacción
const formTransaccion = document.getElementById('formTransaccion');
if (formTransaccion) {
    formTransaccion.addEventListener('submit', async (e) => {
        e.preventDefault();

        const payload = {
            descripcion: document.getElementById('transDescripcion').value,
            valor: parseFloat(document.getElementById('transValor').value)
        };

        try {
            const response = await fetchProtected('/transactions', {
                method: 'POST',
                body: JSON.stringify(payload)
            });

            if (response.ok) {
                alert('Transacción guardada exitosamente');
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

// Función auxiliar para pintar el HTML de la tabla
function renderizarTablaTransacciones(transacciones) {
    const tbody = document.getElementById('tablaTransaccionesBody');
    if (!tbody) return;

    tbody.innerHTML = '';

    if (transacciones.length === 0) {
        tbody.innerHTML = '<tr><td colspan="2" class="text-center">No hay transacciones registradas</td></tr>';
        return;
    }

    transacciones.forEach(t => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${t.descripcion}</td>
            <td class="text-end fw-bold">$${t.valor.toFixed(2)}</td>
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
        btnAnalizar.innerText = 'Analizando con IA...';

        try {
            // El backend Spring Boot orquestará la llamada al motor de Python (Data Science)
            const response = await fetchProtected('/analisis', { method: 'POST' });

            if (response.ok) {
                const resultado = await response.json();
                mostrarResultadosIA(resultado);
            } else {
                alert('No se pudo completar el análisis en este momento.');
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

    // Configurar clases de Bootstrap según el perfil financiero
    let badgeClass = 'bg-secondary';
    if (data.perfilFinanciero === 'SALUDABLE') badgeClass = 'bg-success';
    else if (data.perfilFinanciero === 'EN_OBSERVACION') badgeClass = 'bg-warning text-dark';
    else if (data.perfilFinanciero === 'EN_RIESGO') badgeClass = 'bg-danger';

    contenedor.classList.remove('d-none');

    document.getElementById('iaPerfil').innerHTML = `<span class="badge ${badgeClass}">${data.perfilFinanciero}</span>`;

    // Validar si existen recomendaciones antes de iterar
    if (data.recomendaciones && data.recomendaciones.length > 0) {
        const listaRecomendaciones = data.recomendaciones.map(r => `<li class="list-group-item">${r}</li>`).join('');
        document.getElementById('iaRecomendaciones').innerHTML = `<ul class="list-group list-group-flush">${listaRecomendaciones}</ul>`;
    } else {
        document.getElementById('iaRecomendaciones').innerHTML = '<p class="text-muted">No hay recomendaciones por el momento.</p>';
    }
}