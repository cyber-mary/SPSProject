function validatePassword() {
  if (document.getElementById("password").value != document.getElementById("confirm_password").value) {
    confirm_password.setCustomValidity("Passwords Don't Match");
  } else {
    confirm_password.setCustomValidity('');
  }
}