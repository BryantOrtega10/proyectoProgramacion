<%-- 
    Document   : login
    Created on : 11/08/2020, 06:20:14 PM
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv='Content-Type' content='text/html;charset=UTF-8'>
        <title>Quest Land</title>
        <link rel='stylesheet' href='css/loginEstilo.css'>
    </head>
    <body>
        <div class='loginPanel'>
            <p class='loginTitulo'>Quest Land</p>
            <table class='loginSpace'>
                <tr>
                    <td><input type='text' id='usuario' placeholder='Usuario'></td>
                </tr>
                <tr>
                    <td class='espacioContraseña'><input type='password' id='pass' placeholder='Contraseña'></td>
                </tr>
            </table>
            <div class='buttonPanel'>
                <button class='loginButton'>Ingresar</button>
            </div>
            <div class='olvCtr'>
                <p class='pregunta'>¿Olvidó la contraseña?</p>
                <a href='' class='respuesta'>Recordar contraseña</a>
            </div>
            <div class='crCnt'>
                <p class='pregunta'>¿No tiene una cuenta?</p>
                <a href='' class='respuesta'>Crear cuenta</a>
            </div>
        </div>
    </body>
</html>
