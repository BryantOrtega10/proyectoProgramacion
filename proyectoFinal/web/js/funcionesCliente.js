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


