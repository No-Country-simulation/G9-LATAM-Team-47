document.querySelectorAll('form').forEach(form => form.addEventListener('submit', () => { const button=form.querySelector('button[type="submit"],button:not([type])'); if(button){button.disabled=true;} }));

document.addEventListener('DOMContentLoaded', function () {
    const alerts = document.querySelectorAll('.alert');

    alerts.forEach(function (alert) {
        setTimeout(function () {
            const bsAlert = bootstrap.Alert.getOrCreateInstance(alert);
            bsAlert.close();
        }, 2000);
    });
});

// Trasacciones
function toggleTransactionForm() {
    const panel = document.getElementById('transactionFormPanel');

    if (!panel) return;

    panel.style.display = panel.style.display === 'none'
        ? 'block'
        : 'none';
}