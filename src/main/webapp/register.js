
// Form validation
document.addEventListener('DOMContentLoaded', function() {
    const forms = document.querySelectorAll('form');

    forms.forEach(form => {
        form.addEventListener('submit', function(event) {
            event.preventDefault();

            // Remove any existing error messages
            const existingErrors = form.querySelectorAll('.error-message');
            existingErrors.forEach(error => error.remove());

            let isValid = true;

            // Validate each required field
            const requiredFields = form.querySelectorAll('[required]');
            requiredFields.forEach(field => {
                if (!field.value.trim()) {
                    isValid = false;
                    showError(field, 'This field is required');
                } else if (field.type === 'email' && !isValidEmail(field.value)) {
                    isValid = false;
                    showError(field, 'Please enter a valid email address');
                } else if (field.type === 'tel' && !isValidPhone(field.value)) {
                    isValid = false;
                    showError(field, 'Please enter a valid phone number');
                } else if (field.type === 'password' && field.value.length < 6) {
                    isValid = false;
                    showError(field, 'Password must be at least 6 characters long');
                }
            });

            if (isValid) {
                form.submit();
            }
        });
    });
});

function showError(field, message) {
    const errorDiv = document.createElement('div');
    errorDiv.className = 'error-message';
    errorDiv.textContent = message;
    field.parentNode.appendChild(errorDiv);
    field.classList.add('invalid');
}

function isValidEmail(email) {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
}

function isValidPhone(phone) {
    return /^\+?[\d\s-]{10,}$/.test(phone);
}