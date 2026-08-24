function renderTransactionsChart(items) {
    const ctxTxPie = document.getElementById('txPieChart');

    if (!ctxTxPie) return;

    const paymentCounts = {};

    items.forEach(tx => {
        const medio = tx.medio_pago || 'OTRO';
        paymentCounts[medio] = (paymentCounts[medio] || 0) + 1;
    });

    const labels = Object.keys(paymentCounts);
    const values = Object.values(paymentCounts);

    if (typeof txPieChart !== 'undefined' && txPieChart instanceof Chart) {
        txPieChart.destroy();
    }

    txPieChart = new Chart(ctxTxPie.getContext('2d'), {
        type: 'doughnut',
        data: {
            labels: labels,
            datasets: [{
                data: values,
                backgroundColor: [
                    '#2563EB',
                    '#22C55E',
                    '#7C3AED',
                    '#F59E0B',
                    '#EF4444',
                    '#60A5FA'
                ]
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            cutout: '55%',
            plugins: {
                legend: {
                    position: 'bottom',
                    align: 'center',
                    labels: {
                        boxWidth: 10,
                        font: { size: 10 },
                        textAlign: 'left',
                        generateLabels(chart) {
                            const labels = chart.data.labels;
                            const values = chart.data.datasets[0].data;
                            const total = values.reduce((sum, value) => sum + value, 0);

                            return labels.map((label, index) => {
                                const value = values[index];
                                const porcentaje = total
                                    ? Math.round((value / total) * 100)
                                    : 0;

                                return {
                                    text: `${label} · ${value} ${value === 1 ? 'transacción' : 'transacciones'} · ${porcentaje}%`,
                                    fillStyle: chart.data.datasets[0].backgroundColor[index],
                                    strokeStyle: chart.data.datasets[0].backgroundColor[index],
                                    hidden: false,
                                    index: index
                                };
                            });
                        }
                    }
                }
            }
        }
    });
}

function renderAllTransactionsTable(items) {
    const body = document.querySelector('table tbody');
    if (!body) return;

    const total = items.reduce((sum, tx) => {
        const monto = Number(
            String(tx.monto || '')
                .replace('$', '')
                .replace(/,/g, '')
        ) || 0;

        return sum + monto;
    }, 0);

    body.innerHTML = items.length === 0
        ? '<tr><td colspan="4" style="text-align:center; color:#64748B; padding:20px;">No se encontraron transacciones</td></tr>'
        : items.map(tx => `
        <tr>
            <td><strong>${tx.nombre || ''}</strong></td>
            <td style="color:#64748B;">${tx.fecha || ''}</td>
            <td>${tx.medio_pago || ''}</td>
            <td style="text-align:right; color:#EF4444; font-weight:600;">${tx.monto}</td>
        </tr>
    `).join('') + `
        <tr style="border-top:2px solid #E2E8F0;">
            <td colspan="3" style="font-weight:700; padding-top:14px;">
                Total · ${items.length} ${items.length === 1 ? 'transacción' : 'transacciones'}
            </td>
            <td style="text-align:right; font-weight:700; color:#EF4444; padding-top:14px;">
                $${total.toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 })}
            </td>
        </tr>
    `;
}

function poblarSelectMedios(labels) {
    const sel = document.getElementById('filterTxPayment');
    if (!sel) return;

    const actual = sel.value;

    sel.innerHTML =
        '<option value="">Todos los medios de pago</option>' +
        labels.map(l => `<option value="${l}">${l}</option>`).join('');

    sel.value = actual;
}

function loadTransactionsData() {

    fetch('/dashboard/data')
        .then(response => {
            if (!response.ok) {
                throw new Error('No se pudieron cargar las transacciones');
            }
            return response.json();
        })
        .then(data => {
            poblarSelectMedios(data.payment_methods || []);
            const periodo = document.getElementById('transactionPeriodFilter')?.value || '1';
            const medioPago = document.getElementById('filterTxPayment')?.value || '';

            const transaccionesFiltradas = filtrarPorPeriodo(data.all_transactions, periodo);
            const transaccionesFinales = medioPago
                ? transaccionesFiltradas.filter(tx => tx.medio_pago === medioPago)
                : transaccionesFiltradas;

            renderAllTransactionsTable(transaccionesFinales);
            renderTransactionsChart(transaccionesFinales);
        })
        .catch(error => {
            console.error('Error cargando transacciones:', error);
        });
}

function filtrarPorPeriodo(items, meses) {
    if (!meses || meses === 'all') return items;

    const ahora = new Date();
    const inicio = new Date(ahora);
    inicio.setMonth(inicio.getMonth() - Number(meses));

    const hoy = new Date(
        ahora.getFullYear(),
        ahora.getMonth(),
        ahora.getDate()
    );

    const fechaInicio = new Date(
        inicio.getFullYear(),
        inicio.getMonth(),
        inicio.getDate()
    );

    return items.filter(tx => {
        const fechaTx = new Date(tx.fecha);
        const fecha = new Date(
            fechaTx.getFullYear(),
            fechaTx.getMonth(),
            fechaTx.getDate()
        );

        return fecha >= fechaInicio && fecha <= hoy;
    });
}

const transactionPeriodFilter = document.getElementById('transactionPeriodFilter');
const filterTxPayment = document.getElementById('filterTxPayment');

if (transactionPeriodFilter) {
    transactionPeriodFilter.addEventListener('change', loadTransactionsData);
}

if (filterTxPayment) {
    filterTxPayment.addEventListener('change', loadTransactionsData);
}



document.addEventListener('DOMContentLoaded', () => {
    loadTransactionsData();
});