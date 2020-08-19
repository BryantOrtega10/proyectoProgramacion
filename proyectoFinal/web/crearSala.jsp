<%-- 
    Document   : crearSala
    Created on : 18/08/2020, 05:59:25 PM
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Crear Sala</title>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <link rel="stylesheet" href="css/crearSalaEstilo.css">
    </head>
    <body>
        <div class="centrado">
            <p class="crearSalaTitulo">Crear una sala</p>
            <div class="centradoHorizontal">

            </div>
            <div class="codigoCont">
                <p>Codigo:</p>
                <p class="codigo">123456</p>
            </div>
            <div class="numRondasCont">
                <p>Numero de rondas:</p>
                <select name="numRondas" id="numRondas" class="selects">
                    <option value="1">1</option>
                    <option value="1">1</option>
                </select>
            </div>
            <div class="temasCont">
                <p>Temas: </p>
                <select name="temas" id="temas" class="selects">
                    <option value="">Un tema</option>
                    <option value="">Otro tema</option>
                    <option value="">Y asi...</option>
                </select>
            </div>
            <a href="" class="continuarbtn">Continuar</a>
        </div>
    </body>
</html>
