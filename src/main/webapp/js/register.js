//bootstrap JS code to enable button collapse
var collapseElementList = [].slice.call(document.querySelectorAll('.collapse'))
var collapseList = collapseElementList.map(function (collapseEl) {
  return new bootstrap.Collapse(collapseEl)
})

function init() {
  const form = document.querySelector('form');
  form.addEventListener('submit', handleSubmit);
}

function validatePassword() {
  if (document.getElementById("password").value != document.getElementById("confirm_password").value) {
    confirm_password.setCustomValidity("Passwords Don't Match");
  } else {
    confirm_password.setCustomValidity('');
  }
}

function handleSubmit(event) {
  event.preventDefault();
  const data = new FormData(event.target);
  const value = Object.fromEntries(data.entries());

  console.log({ value });

  $.ajax({
    type: 'post',
    url: '/account-handler',
    dataType: 'JSON',
    data: value
  });
}
