/*Display the JSON content in the news.html table* */

//Sample JSON response from DisplayFamilyServlet...
/*var groupArray = [
  {'name':'Larry', 'status':'Vaccinated',         'group':'Smith'},
  {'name':'Moe',   'status':'Covid-19 Positive',  'group':'Smith'},
  {'name':'Currly','status':'Vaccinated',         'group':'Adams'}
]*/

//Retrieve JSON object from Servlet URL and set it to groupArray
//Possibly use a different method
/*$.ajax({
  method:'GET',
  url:'/group-details',
  success:function(response){
      groupArray = response.data
      buildTable(groupArray)
      console.log(groupArray)
  }
})
*/
//Function that builds the content of table rows with JSON object as parameter
function buildTable(data){
    var table = document.getElementById('statusTable')
  
    for(var i=0; i < data.length; i++){
        var row = `<tr>
                        <td>${data[i].name}</td>
                        <td>${data[i].status}</td>
                        <td>${data[i].group}</td>
                </tr>`
        table.innerHTML += row
    }
  }
  