//bootstrap JS code to enable button collapse
var collapseElementList = [].slice.call(document.querySelectorAll('.collapse'))
var collapseList = collapseElementList.map(function (collapseEl) {
  return new bootstrap.Collapse(collapseEl)
})

// function createFamily() {
//   const data = document.getElementById("create-family-name").value;

//   console.log({ data });
//   if (data !== "" || data !== undefined) {
//     $.ajax({
//       type: 'post',
//       url: '/family',
//       dataType: 'JSON',
//       data: data
//     });
//   }
// }

// $('#create-fam-form').submit(function () {
//   $(this)
//     .find('input[family_name]')
//     .filter(function () {
//       return !this.value;
//     })
//     .prop('family_name', '');
// });