const API_URL = 'http://localhost:8080/api/analisis-financiero';

// Agregar transacción
document.getElementById('btnAgregarTransaccion').addEventListener('click', () => {
    const contenedor = document.getElementById('listaTransacciones');

    const div = document.createElement('div');
    div.innerHTML = `
        <input class="desc" placeholder="Descripción">
        <input class="valor" type="number" placeholder="Monto">
    `;

    contenedor.appendChild(div);
});

// Submit
document.getElementById('financeForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const transacciones = [];

    document.querySelectorAll('#listaTransacciones div').forEach(item => {
        const desc = item.querySelector('.desc').value;
        const valor = parseFloat(item.querySelector('.valor').value);

        if (desc && valor) {
            transacciones.push({ descripcion: desc, valor });
        }
    });

    const payload = {
        ingreso_mensual: parseFloat(document.getElementById('ingreso').value),
        nivel_endeudamiento: parseFloat(document.getElementById('endeudamiento').value),
        frecuencia_ahorro: document.getElementById('ahorro').value,
        transacciones
    };

    try {
        const res = await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });

        const data = await res.json();
        mostrarResultados(data);

    } catch (err) {
        alert('Error conectando al backend');
    }
});

// Render
function mostrarResultados(data) {
    document.getElementById('resultadoContenedor').classList.remove('d-none');

    const badge = document.getElementById('badgePerfil');
    badge.innerText = data.perfil_financiero;
    badge.className = `badge badge-${data.perfil_financiero.replace(/\s/g, '')}`;

    document.getElementById('txtProbabilidad').innerText =
        (data.probabilidad * 100).toFixed(1) + '%';

    const lista = document.getElementById('listaGastos');
    lista.innerHTML = '';

    Object.entries(data.resumen_gastos).forEach(([cat, monto]) => {
        lista.innerHTML += `<li>${cat}: $${monto}</li>`;
    });

    const rec = document.getElementById('contenedorRecomendaciones');
    rec.innerHTML = '';

    data.recomendaciones.forEach(r => {
        rec.innerHTML += `<p>💡 ${r}</p>`;
    });
}