<%-- 
    Document   : registro
    Created on : 18/08/2020, 04:03:14 PM
    Author     : Bryant
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
        <div class="datosRegistro">
            <p class='loginTitulo'>Quest Land</p>
            <form action="UsuarioServlet" autocomplete="off" id="formAddUsu" method="POST"  onsubmit="agregarUsuarioAjax(); return false;">
                <input type="hidden" name="accion" value="registrarCliente" />
                <table class='loginSpace'>
                    <tr>
                        <td><input type='email' name='email' placeholder='Email'></td>
                    </tr>
                    <tr>
                        <td><input type='text' name='usuario' placeholder='Usuario'></td>
                    </tr>
                    <tr>
                        <td><input type='password' name='pass' placeholder='Contraseña'></td>
                    </tr>
                    <tr>
                        <td><input type='password' name='rpass' placeholder='Repita la contraseña'></td>
                    </tr>
                </table>
                <div class='buttonPanel'>
                    <button class='loginButton' >Registrarme</button>
                </div>
                <a href="index.jsp" class="volver">Volver</a>
            </form>
        </div>
    </body>
</html>
