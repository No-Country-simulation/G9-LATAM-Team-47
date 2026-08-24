let lineChart, pieChart, txPieChart, aiHealthChart, aiEvolutionChart, aiCategoriesChart, aiPaymentChart;
let ultimoPayload = null;

const userProfileButton = document.getElementById('userProfileButton');
const userProfileMenu = document.getElementById('userProfileMenu');

if (userProfileButton && userProfileMenu) {
    userProfileButton.addEventListener('click', function (event) {
        event.stopPropagation();
        userProfileMenu.classList.toggle('show');
    });

    document.addEventListener('click', function () {
        userProfileMenu.classList.remove('show');
    });
}

function switchView(viewName, element) {
    document.querySelectorAll('.app-view').forEach(el => el.style.display = 'none');
    const target = document.getElementById('view-' + viewName);
    if (target) target.style.display = 'block';

    document.querySelectorAll('.nav-menu .nav-item').forEach(el => el.classList.remove('active'));
    if (element) element.classList.add('active');

    if (viewName === 'ia') loadAIData();
    else if (viewName === 'perfil') loadPerfilData();
    else if (viewName === 'perfil-financiero') loadPerfilFinancieroData();
    else if (viewName === 'historial') loadHistorialData();
    else if (viewName === 'dashboard') loadDashboardData();
}

const params = new URLSearchParams(window.location.search);
const initialView = params.get('view');

if (initialView) {
    switchView(initialView);
}

function poblarSelectMedios(labels) {
    const sel = document.getElementById('filterTxPayment');
    if (!sel) return;
    const actual = sel.value;
    sel.innerHTML = '<option value="">Todos los medios de pago</option>' + labels.map(l => `<option value="${l}">${l}</option>`).join('');
    sel.value = actual;
}

function applyPaymentFilter(value) {
    if (!ultimoPayload) return;
    renderAllTransactionsTable(ultimoPayload.all_transactions, value);
}

function renderAllTransactionsTable(items, filtroMedio) {
    const body = document.getElementById('allTransactionsTableBody');
    if (!body) return;
    const filtrados = filtroMedio ? items.filter(t => t.medio_pago === filtroMedio) : items;
    body.innerHTML = filtrados.length === 0
        ? '<tr><td colspan="4" style="text-align:center; color:#64748B; padding:20px;">No se encontraron transacciones</td></tr>'
        : filtrados.map(tx => `<tr><td><strong>${tx.nombre || ''}</strong></td><td style="color:#64748B;">${tx.fecha || ''}</td><td>${tx.medio_pago || ''}</td><td style="text-align:right; color:#EF4444; font-weight:600;">${tx.monto}</td></tr>`).join('');
}

function loadDashboardData() {
    fetch('/dashboard/data')
        .then(r => { if (!r.ok) throw new Error('No se pudo cargar el panel'); return r.json(); })
        .then(data => {
            ultimoPayload = data;
            const warn = document.getElementById('dashboardWarning');
            if (warn) warn.innerHTML = '';

            document.getElementById('kpiTransacciones').innerText = data.kpis.transacciones;
            document.getElementById('kpiGastoTotal').innerText = data.kpis.gasto_total;
            document.getElementById('kpiPerfil').innerText = data.kpis.perfil_financiero;
            document.getElementById('kpiAhorro').innerText = data.kpis.rango_ahorro;

            poblarSelectMedios(data.payment_methods || []);

            const ctxLine = document.getElementById('performanceChart');
            if (ctxLine) {
                if (lineChart) lineChart.destroy();
                lineChart = new Chart(ctxLine.getContext('2d'), {
                    type: 'line',
                    data: { labels: data.chart.labels, datasets: [{ label: 'Gasto ($)', data: data.chart.values, borderColor: '#2563EB', backgroundColor: 'rgba(37,99,235,0.1)', borderWidth: 2, fill: true, tension: 0.3 }] },
                    options: { responsive: true, maintainAspectRatio: false, plugins: { legend: { display: false } } }
                });
            }

            const ctxPie = document.getElementById('pieChart');
            if (ctxPie) {
                if (pieChart) pieChart.destroy();

                const pieLabels = data.pie_chart.labels;
                const pieValues = data.pie_chart.values;
                const pieTotal = pieValues.reduce((sum, value) => sum + Number(value), 0);

                pieChart = new Chart(ctxPie.getContext('2d'), {
                    type: 'doughnut',
                    data: {
                        labels: pieLabels,
                        datasets: [{
                            data: pieValues,
                            backgroundColor: ['#2563EB', '#22C55E', '#7C3AED', '#F59E0B', '#EF4444', '#60A5FA']
                        }]
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        cutout: '55%',
                        plugins: {
                            legend: {
                                position: 'right',
                                labels: {
                                    boxWidth: 10,
                                    font: { size: 10 },
                                    generateLabels(chart) {
                                        const dataset = chart.data.datasets[0];

                                        return chart.data.labels.map((label, index) => {
                                            const value = Number(dataset.data[index]);
                                            const percentage = pieTotal
                                                ? ((value / pieTotal) * 100).toFixed(1)
                                                : '0.0';

                                            return {
                                                text: `${label} — ${percentage}% ($${value.toLocaleString('es-CO')})`,
                                                fillStyle: dataset.backgroundColor[index],
                                                strokeStyle: dataset.backgroundColor[index],
                                                lineWidth: 0,
                                                hidden: false,
                                                index: index,
                                                width: chart.width - 40
                                            };
                                        });
                                    }
                                }
                            }
                        }
                    }
                });
            }

            const tbody = document.getElementById('transactionsTableBody');
            if (tbody) {
                tbody.innerHTML = data.transactions.length === 0
                    ? '<tr><td colspan="3" style="text-align:center; color:#64748B;">No hay transacciones</td></tr>'
                    : data.transactions.map(tx => `<tr><td><strong>${tx.nombre || ''}</strong></td><td>${tx.fecha || ''}</td><td style="color:#EF4444; font-weight:600;">${tx.monto}</td></tr>`).join('');
            }

            renderAllTransactionsTable(data.all_transactions, '');

            const ctxTxPie = document.getElementById('txPieChart');
            if (ctxTxPie) {
                if (txPieChart) txPieChart.destroy();
                txPieChart = new Chart(ctxTxPie.getContext('2d'), {
                    type: 'doughnut',
                    data: { labels: data.payment_chart.labels, datasets: [{ data: data.payment_chart.values, backgroundColor: ['#2563EB', '#22C55E', '#7C3AED', '#F59E0B', '#EF4444', '#60A5FA'] }] },
                    options: { responsive: true, maintainAspectRatio: false, cutout: '55%', plugins: { legend: { position: 'bottom' } } }
                });
            }
        })
        .catch(err => {
            console.error('Error cargando el panel:', err);
            const warn = document.getElementById('dashboardWarning');
            if (warn) warn.innerHTML = '<div class="alert alert-warning">No se pudo cargar tu información. Probá recargar la página.</div>';
        });
}

function loadAIData() {
    fetch('/dashboard/analisis-data')
        .then(r => r.json())
        .then(data => {
            if (!data.existe) {
                document.getElementById('aiTituloSalud').innerText = 'Sin análisis disponible';
                document.getElementById('aiProbText').innerText = data.mensaje || 'Registrá al menos una transacción para generar tu análisis.';
                return;
            }
            const prob = Math.round(Number(data.probabilidad || 0) * 100);
            document.getElementById('aiTituloSalud').innerText = data.perfil_financiero || 'Sin datos';
            document.getElementById('aiProbText').innerText = `Probabilidad del modelo: ${prob}%`;
            document.getElementById('aiEstadoTexto').innerText = 'Último análisis sincronizado con tu cuenta.';

            const endeudamiento = data.nivel_endeudamiento != null ? Math.round(Number(data.nivel_endeudamiento) * 100) + '%' : 'N/D';
            document.getElementById('aiProgresoList').innerHTML =
                `<li><strong>Endeudamiento:</strong> ${endeudamiento}</li><li><strong>Rango de ahorro:</strong> ${data.rango_ahorro || 'N/D'}</li>`;

            document.getElementById('aiTopCategoria').innerText = data.top_categoria
                ? `${data.top_categoria}: $${Number(data.top_categoria_monto).toFixed(2)}`
                : 'Sin datos suficientes todavía.';

            const recList = document.getElementById('aiRecomendacionesList');
            recList.innerHTML = (data.recomendaciones && data.recomendaciones.length)
                ? data.recomendaciones.map(r => `<li>• ${r}</li>`).join('')
                : '<li>Sin recomendaciones por ahora.</li>';

            const ctxHealth = document.getElementById('aiHealthChart');
            if (ctxHealth) {
                if (aiHealthChart) aiHealthChart.destroy();
                aiHealthChart = new Chart(ctxHealth.getContext('2d'), {
                    type: 'doughnut',
                    data: { labels: ['Probabilidad', 'Resto'], datasets: [{ data: [prob, 100 - prob], backgroundColor: ['#22C55E', '#E2E8F0'], borderWidth: 0 }] },
                    options: { responsive: true, maintainAspectRatio: false, cutout: '75%', plugins: { legend: { display: false }, tooltip: { enabled: false } } },
                    plugins: [{
                        id: 'centerText',
                        beforeDraw(chart) {
                            const { width, height, ctx } = chart;
                            ctx.restore();
                            const fontSize = (height / 110).toFixed(2);
                            ctx.font = `bold ${fontSize}em sans-serif`;
                            ctx.textBaseline = 'middle';
                            ctx.fillStyle = '#166534';
                            ctx.textAlign = 'center';
                            ctx.fillText(prob + '%', width / 2, height / 2);
                            ctx.save();
                        }
                    }]
                });
            }

            const ctxEval = document.getElementById('aiEvolutionChart');
            if (ctxEval) {
                if (aiEvolutionChart) aiEvolutionChart.destroy();
                aiEvolutionChart = new Chart(ctxEval.getContext('2d'), {
                    type: 'line',
                    data: { labels: data.chart.labels, datasets: [{ data: data.chart.values, borderColor: '#22C55E', backgroundColor: 'rgba(34,197,94,0.08)', borderWidth: 2, fill: true, tension: 0.3, pointBackgroundColor: '#22C55E', pointRadius: 4 }] },
                    options: { responsive: true, maintainAspectRatio: false, plugins: { legend: { display: false } }, scales: { y: { beginAtZero: true } } }
                });
            }

            const ctxCat = document.getElementById('aiCategoriesChart');
            if (ctxCat) {
                if (aiCategoriesChart) aiCategoriesChart.destroy();

                const catLabels = data.pie_chart.labels || [];
                const catValues = (data.pie_chart.values || []).map(Number);
                const catTotal = catValues.reduce((sum, value) => sum + value, 0);

                aiCategoriesChart = new Chart(ctxCat.getContext('2d'), {
                    type: 'doughnut',
                    data: {
                        labels: catLabels,
                        datasets: [{
                            data: catValues,
                            backgroundColor: [
                                '#2563EB',
                                '#7C3AED',
                                '#22C55E',
                                '#F97316',
                                '#EF4444'
                            ],
                            borderWidth: 2,
                            borderColor: '#fff'
                        }]
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        cutout: '55%',
                        plugins: {
                            legend: {
                                position: 'right',
                                labels: {
                                    boxWidth: 10,
                                    font: { size: 10 },

                                    generateLabels(chart) {
                                        const dataset = chart.data.datasets[0];

                                        return chart.data.labels.map((label, index) => {
                                            const value = Number(dataset.data[index]) || 0;

                                            const percentage = catTotal
                                                ? ((value / catTotal) * 100).toFixed(1)
                                                : '0.0';

                                            return {
                                                text: `${label} — ${percentage}% ($${value.toLocaleString('es-CO')})`,
                                                fillStyle: dataset.backgroundColor[index],
                                                strokeStyle: dataset.backgroundColor[index],
                                                lineWidth: 0,
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

            const ctxPay = document.getElementById('aiPaymentChart');
            if (ctxPay) {
                if (aiPaymentChart) aiPaymentChart.destroy();

                const payLabels = data.payment_chart.labels || [];
                const payValues = (data.payment_chart.values || []).map(Number);
                const payTotal = payValues.reduce((sum, value) => sum + value, 0);

                aiPaymentChart = new Chart(ctxPay.getContext('2d'), {
                    type: 'doughnut',
                    data: {
                        labels: payLabels,
                        datasets: [{
                            data: payValues,
                            backgroundColor: [
                                '#2563EB',
                                '#22C55E',
                                '#F97316',
                                '#7C3AED',
                                '#EF4444'
                            ],
                            borderWidth: 2,
                            borderColor: '#fff'
                        }]
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        cutout: '55%',
                        plugins: {
                            legend: {
                                position: 'right',
                                labels: {
                                    boxWidth: 10,
                                    font: { size: 10 },

                                    generateLabels(chart) {
                                        const dataset = chart.data.datasets[0];

                                        return chart.data.labels.map((label, index) => {
                                            const value = Number(dataset.data[index]) || 0;

                                            const percentage = payTotal
                                                ? ((value / payTotal) * 100).toFixed(1)
                                                : '0.0';

                                            return {
                                                text: `${label} — ${percentage}% ($${value.toLocaleString('es-CO')})`,
                                                fillStyle: dataset.backgroundColor[index],
                                                strokeStyle: dataset.backgroundColor[index],
                                                lineWidth: 0,
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
        })
        .catch (err => console.error('Error al cargar Análisis IA:', err));
}

function loadPerfilData() {
    fetch('/dashboard/perfil-data')
        .then(r => r.json())
        .then(data => {
            const dl = document.getElementById('perfilDetalle');
            if (!dl) return;
            const filas = [
                ['Nombre', `${data.nombre || ''} ${data.apellido || ''}`.trim()],
                ['Documento', data.documento || 'N/D'],
                ['Correo', data.email || 'N/D'],
                ['Fecha de nacimiento', data.fecha_nacimiento || 'N/D'],
                ['Estado civil', data.estado_civil || 'N/D'],
                ['Sexo', data.sexo || 'N/D'],
                ['Número de hijos', data.numero_hijos != null ? data.numero_hijos : 'N/D'],
            ];
            dl.innerHTML = filas.map(([k, v]) => `<dt style="color:#64748B;">${k}</dt><dd style="margin:0;">${v}</dd>`).join('');
        })
        .catch(err => console.error('Error al cargar el perfil:', err));
}

function loadPerfilFinancieroData() {
    fetch('/dashboard/perfil-financiero-data')
        .then(r => r.json())
        .then(data => {
            const dl = document.getElementById('perfilFinancieroDetalle');
            if (!dl) return;

            if (data.error) {
                dl.innerHTML = `<p style="color:#EF4444;">${data.mensaje || data.error}</p>`;
                return;
            }

            const filas = [
                ['Empleo Formal', data.empleo_formal ? 'Sí' : 'No'],
                ['Ingreso Mensual', `$${data.ingreso_mensual || '0.00'}`],
                ['Línea de Crédito', `$${data.linea_credito || '0.00'}`]
            ];
            dl.innerHTML = filas.map(([k, v]) => `<dt style="color:#64748B;">${k}</dt><dd style="margin:0; font-weight:600;">${v}</dd>`).join('');
        })
        .catch(err => console.error('Error al cargar el perfil financiero real:', err));
}

function loadHistorialData() {
    fetch('/dashboard/historial-data')
        .then(r => r.json())
        .then(data => {
            const cont = document.getElementById('historialLista');
            if (!cont) return;
            if (!data.items || data.items.length === 0) {
                cont.innerHTML = '<p style="color:#64748B;">Todavía no hay análisis guardados.</p>';
                return;
            }
            cont.innerHTML = data.items.map(item => `
        <div class="card" style="padding:15px;">
          <div style="display:flex; justify-content:space-between;">
            <strong>${item.perfil_financiero || 'Sin datos'}</strong>
            <span>${item.probabilidad != null ? Math.round(item.probabilidad * 100) + '%' : 'N/D'}</span>
          </div>
          <ul style="margin:8px 0 0 0; padding-left:18px; color:#334155; font-size:0.85rem;">
            ${(item.recomendaciones || []).map(r => `<li>${r}</li>`).join('')}
          </ul>
        </div>`).join('');
        })
        .catch(err => console.error('Error al cargar el historial:', err));
}

window.addEventListener('DOMContentLoaded', loadDashboardData);