/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

setInterval(function(){
    var xhttp = new XMLHttpRequest();
    xhttp.onload  = function() {
        if (this.status === 200) {
            document.getElementById("jugadores").innerHTML = this.responseText;
        }      
    };
  xhttp.open("GET", "Juego?accion=obtenerPuntuaciones&idRonda="+document.getElementById("idRonda").value, true);
  xhttp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
  xhttp.send();
},5000);

setInterval(function(){
    var xhttp = new XMLHttpRequest();
    xhttp.onload  = function() {
        if (this.status === 200) {
            if(this.responseText!=""){
                window.open(this.responseText, "_self");
            }
        }      
    };
  xhttp.open("GET", "Juego?accion=verificarEstado&idRonda="+document.getElementById("idRonda").value, true);
  xhttp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
  xhttp.send();
},5000);
