
const passwordInput = document.getElementById('password');

passwordInput.addEventListener('mouseenter', () => {
    passwordInput.type = 'text';
});

passwordInput.addEventListener('mouseleave', () => {
    passwordInput.type = 'password';
});