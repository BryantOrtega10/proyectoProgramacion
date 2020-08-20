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
        <script src="js/funcionesCliente.js"></script>
    </head>
    <body>
        <div class='centrado'>
            <div class='ingresarSalaContenedor'>
                <p class='ingresarSalaTitulo'>Ingresar a sala</p>
                <form action="Salas" method="post" id="formIngresarSala" onsubmit="ingresarSala(); return false;">
                    <input type="hidden" name="accion" value="ingresarSala" />
                    <table>
                        <tr>
                            <td><label for='codigoIngresarSala'>Código:</label></td>
                            <td><input type='number' name="codigoSala" id='codigoIngresarSala'></td>
                            <td><button type="submit" class="continuarbtn">Continuar</button></td>
                        </tr>
                    </table>
                </form>
                <div class='crSl'>
                    <p class='pregunta'>¿No tiene sala? Puede...</p>
                    <a href='Salas?accion=crearSala' class='respuesta'>Crear una sala</a>
                </div>
            </div>
        </div>
    </body>
</html>
