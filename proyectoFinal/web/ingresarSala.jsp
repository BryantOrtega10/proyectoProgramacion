<%-- 
    Document   : ingresarSala
    Created on : 11/08/2020, 06:32:46 PM
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv='Content-Type' content='text/html;charset=UTF-8'> 
        <title>Buscar una sala</title>
        <link rel='stylesheet' href='css/ingresarSalaEstilo.css'>
    </head>
    <body>
        <div class='centrado'>
            <div class='ingresarSalaContenedor'>
                <p class='ingresarSalaTitulo'>Ingresar a sala</p>
                <table>
                    <tr>
                        <td><label for='codigoIngresarSala'>Código:</label></td>
                        <td><input type='number' id='codigoIngresarSala'></td>
                    </tr>
                </table>
                <div class='crSl'>
                    <p class='pregunta'>¿No tiene sala? Puede...</p>
                    <a href='' class='respuesta'>Crear una sala</a>
                </div>
            </div>
        </div>
    </body>
</html>
