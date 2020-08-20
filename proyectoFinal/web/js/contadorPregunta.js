/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



var x = setInterval(function() {
    var dateCount = new Date(document.getElementById("tiempoFinal").value);
    dateCount.setSeconds(dateCount.getSeconds() + 60);
    var countDownDate = dateCount.getTime();
    
    var now = new Date().getTime();
    var distance = countDownDate - now;
    var seconds = Math.floor((distance % (1000 * 60)) / 1000);
    
    document.getElementById("tiempoActual").value = seconds;
    document.getElementById("timer").innerHTML = seconds;
    if (distance < 0) {
      clearInterval(x);
      document.getElementById("formAgregarPregunta").submit();
    }
}, 1000);


function asignarOpcion(opcion){
    document.getElementById("opcionSeleccionada").value=opcion;
    document.getElementById("opcion"+opcion).className="opcionText activa";
    document.getElementById("formAgregarPregunta").submit();
    
}
function urlencodeFormData(fd){
    var s = '';
    function encode(s){ return encodeURIComponent(s).replace(/%20/g,'+'); }
    for(var pair of fd.entries()){
        if(typeof pair[1]=='string'){
            s += (s?'&':'') + encode(pair[0])+'='+encode(pair[1]);
        }
    }
    return s;
}
