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
        <script src="js/funcionesCliente.js"></script>
    </head>
    <body>
        <div class='loginPanel'>
            <p class='loginTitulo'>Quest Land</p>
            <form action="UsuarioServlet" autocomplete="off" id="formInicio" method="POST"  onsubmit="iniciarSesAjax(); return false;">
                <input type="hidden" name="accion" value="iniciarSesion" />
                <table class='loginSpace'>
                    <tr>
                        <td><input type='text' id='usuario' placeholder='Usuario' name="usuario"></td>
                    </tr>
                    <tr>
                        <td class='espacioContraseña'><input type='password' id='pass' placeholder='Contraseña' name="pass"></td>
                    </tr>
                </table>
                <div class='buttonPanel'>
                    <button class='loginButton'>Ingresar</button>
                </div>
                <div id="respIniciar"></div>
            </form>
            <div class='olvCtr'>
                <p class='pregunta'>¿Olvidó la contraseña?</p>
                <a href='' class='respuesta'>Recordar contraseña</a>
            </div>
            <div class='crCnt'>
                <p class='pregunta'>¿No tiene una cuenta?</p>
                <a href='registro.jsp' class='respuesta'>Crear cuenta</a>
            </div>
        </div>
    </body>
</html>
