/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function mostrarPopup(tema){
    document.getElementById(tema).className = "contPopup activo";
    document.getElementById("fondoPop").className = "fondoPop activo";
}
function clickFondoPop(){
    document.getElementsByClassName("contPopup activo")[0].className = "contPopup";
    document.getElementById("fondoPop").className = "fondoPop";
}
function cambiarFoto(input){	
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            var image = new Image();
            image.src = e.target.result;
            image.onload = function() {
                document.getElementById('foto').src=this.src;
                document.getElementById('foto').className = "activo";
                document.getElementById('placeFoto').className = "oculto";
            };          
        };
        reader.readAsDataURL(input.files[0]);	
    }
    else{
        document.getElementById('foto').src="";
        document.getElementById('foto').className = "oculto";
        document.getElementById('placeFoto').className = "activo";
    }
}
function cambiarFotoMod(input){	
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            var image = new Image();
            image.src = e.target.result;
            image.onload = function() {
                document.getElementById('modFoto').src=this.src;
                document.getElementById('modFoto').className = "activo";
                document.getElementById('modPlaceFoto').className = "oculto";
            };          
        };
        reader.readAsDataURL(input.files[0]);	
    }
    else{
        document.getElementById('modFoto').src="";
        document.getElementById('modFoto').className = "oculto";
        document.getElementById('modPlaceFoto').className = "activo";
    }
}
function agregarTemaAjax() {
    var fd = new FormData(document.getElementById("formAddTema"));
    
    var xhttp = new XMLHttpRequest();
    
    xhttp.onload  = function() {
        if (this.status === 200) {
            if(this.responseText==="OK"){
                alert("Se agregado el tema correctamente");
                window.location.reload();
            }
            else{
                alert(this.responseText);
            }
        }      
    };
  xhttp.open("POST", "Temas", true);
  xhttp.send(fd);
  
}
function modificarTemaAjax() {
    var fd = new FormData(document.getElementById("formModTema"));
    
    var xhttp = new XMLHttpRequest();
    xhttp.onload  = function() {
        if (this.status === 200) {
            if(this.responseText==="OK"){
                alert("Se modificado el tema correctamente");
                window.location.reload();
            }
            else{
                alert(this.responseText);
            }
        }      
    };
  xhttp.open("POST", "Temas", true);
  xhttp.send(fd);
  
}
function formModTemaAjax(elemento){    
    var xhttp = new XMLHttpRequest();
    xhttp.onload  = function() {
        if (this.status === 200) {
            document.getElementById("editarTemaPop").innerHTML = this.responseText;
            mostrarPopup("editarTemaPop");
        }      
    };
    xhttp.open("GET", elemento.href , true);
    xhttp.send();
}
function elimiarTemaAjax(elemento){
    var xhttp = new XMLHttpRequest();
    xhttp.onload  = function() {
        if (this.status === 200) {
            if(this.responseText==="OK"){
                alert("Se eliminado el tema correctamente");
                window.location.reload();
            }
            else{
                alert(this.responseText);
            }
        }      
    };
    xhttp.open("GET", elemento.href , true);
    xhttp.send();
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

function agregarPregAjax() {
    var fd = new FormData(document.getElementById("formAddPreg"));
    
    var xhttp = new XMLHttpRequest();
    
    xhttp.onload  = function() {
        if (this.status === 200) {
            if(this.responseText==="OK"){
                alert("Se agregado la pregunta correctamente");
                window.location.reload();
            }
            else{
                alert(this.responseText);
            }
        }      
    };
  xhttp.open("POST", "Preguntas", true);
  xhttp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
  xhttp.send(urlencodeFormData(fd));
  
}
function formModPregAjax(elemento){    
    var xhttp = new XMLHttpRequest();
    xhttp.onload  = function() {
        if (this.status === 200) {
            document.getElementById("editarPregPop").innerHTML = this.responseText;
            mostrarPopup("editarPregPop");
        }      
    };
    xhttp.open("GET", elemento.href , true);
    xhttp.send();
}
function modificarPregAjax() {
    var fd = new FormData(document.getElementById("formModPreg"));
    
    var xhttp = new XMLHttpRequest();
    
    xhttp.onload  = function() {
        if (this.status === 200) {
            if(this.responseText==="OK"){
                alert("Se modificado la pregunta correctamente");
                window.location.reload();
            }
            else{
                alert(this.responseText);
            }
        }      
    };
  xhttp.open("POST", "Preguntas", true);
  xhttp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
  xhttp.send(urlencodeFormData(fd));
  
}
function elimiarPregAjax(elemento){
    var xhttp = new XMLHttpRequest();
    xhttp.onload  = function() {
        if (this.status === 200) {
            if(this.responseText==="OK"){
                alert("Se eliminado la pregunta correctamente");
                window.location.reload();
            }
            else{
                alert(this.responseText);
            }
        }      
    };
    xhttp.open("GET", elemento.href , true);
    xhttp.send();
}