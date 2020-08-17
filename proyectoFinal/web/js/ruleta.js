/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

const ruleta = document.querySelector("#ruleta");
ruleta.addEventListener("click", girar);
tema = 50;

function girar() {
  if (tema >= 20) {
    let rand = Math.random() * 7200;
    calcular(rand);
  } else {
    alert("No entro");
  }
}

function calcular(rand) {
  //dividido en las vueltas
  valor = rand / 360;
  //pasar a entero el primer numero antes del punto,
  // para saber cuantos grados giro
  valor = (valor - parseInt(valor.toString().split(".")[0])) * 360;
  ruleta.style.transform = "rotate("+rand+"deg)";
  num = 6;
  //dar los anuncios despues de 5000 segundos
  setTimeout(()=>{
    
      switch (true) {
        //para las 8 diviciones de la ruleta
        case valor > 0 && valor <= 60:
          alert("morado");
          break;
        case valor > 60 && valor <= 120:
          alert("verde");

          break;
        case valor > 120 && valor <= 180:
          alert("amarillo");
          break;
        case valor > 180 && valor <= 240:
            alert("menta");
            break;
        case valor > 240 && valor <= 300:
            alert("blanco");
            break;
        case valor > 300 && valor <= 360:
            alert("azul");
             break;

     }
    
    //if (num = 2) {

      //switch (true) {
        //para las 8 diviciones de la ruleta
        //case valor > 0 && valor <= 180:
          //alert("azul");
          //break;
        //case valor > 180 && valor <= 360:
          //alert("verde");

          //break;



    //}

  },5000)

}

