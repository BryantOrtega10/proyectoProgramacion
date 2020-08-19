/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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

function agregarUsuarioAjax() {
    var fd = new FormData(document.getElementById("formAddUsu"));
    
    var xhttp = new XMLHttpRequest();
    
    xhttp.onload  = function() {
        if (this.status === 200) {
            if(this.responseText==="OK"){
                window.open("Salas", "_self");
            }
            else{
                alert(this.responseText);
            }
        }      
    };
  xhttp.open("POST", "UsuarioServlet", true);
  xhttp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
  xhttp.send(urlencodeFormData(fd));
  
}
function iniciarSesAjax() {
    var fd = new FormData(document.getElementById("formInicio"));
    
    var xhttp = new XMLHttpRequest();
    
    xhttp.onload  = function() {
        if (this.status === 200) {
            if(this.responseText==="OK"){
                window.open("Salas", "_self");
            }
            else{
                document.getElementById("respIniciar").innerHTML = (this.responseText);
                document.getElementById("respIniciar").style.display = "block";
            }
        }      
    };
  xhttp.open("POST", "UsuarioServlet", true);
  xhttp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
  xhttp.send(urlencodeFormData(fd));
  
}

function enviarTemas(){
    var temasDisp = document.getElementById("temasI");
    var temasFinal = document.getElementById("temasF");
    while(temasDisp.selectedOptions.length > 0){
        temasFinal.appendChild(temasDisp.selectedOptions[0]); 
    }
    
}
function devolverTemas(){
    var temasDisp = document.getElementById("temasI");
    var temasFinal = document.getElementById("temasF");
    while(temasFinal.selectedOptions.length > 0){
        temasDisp.appendChild(temasFinal.selectedOptions[0]); 
    }
}

function completarSala() {
    
    var temasFinal = document.getElementById("temasF");
    for (var i = 0; i < temasFinal.options.length; i++) {
        temasFinal.options[i].selected = true;
    }    
    var fd = new FormData(document.getElementById("formcompletarSala"));
    
    var xhttp = new XMLHttpRequest();
    
    xhttp.onload  = function() {
        if (this.status === 200) {
            if(this.responseText==="OK"){
                alert("Sala creada satisfactoriamente");
                window.open("Salas?accion=salaEspera", "_self");
            }
            else{
                alert(this.responseText);
                
            }
        }      
    };
  xhttp.open("POST", "Salas", true);
  xhttp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
  xhttp.send(urlencodeFormData(fd));
  
}
