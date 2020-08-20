/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


setInterval(function(){
    var xhttp = new XMLHttpRequest();
    xhttp.onload  = function() {
        if (this.status === 200) {
            document.getElementById("bodyUsuarios").innerHTML = this.responseText;
        }      
    };
  xhttp.open("GET", "Salas?accion=recargarUsuariosEspera&idSala="+document.getElementById('idSala').value, true);
  xhttp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
  xhttp.send();
},5000);