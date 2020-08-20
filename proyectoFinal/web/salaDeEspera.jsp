<%-- 
    Document   : salaDeEspera
    Created on : 19/08/2020, 03:26:54 PM
    Author     : oscar
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    Integer idSala = (Integer)request.getAttribute("idSalaNueva");
    ResultSet temas = (ResultSet)request.getAttribute("temas");
    ResultSet usuarios = (ResultSet)request.getAttribute("usuarios");
    String idUsuarioOwner = String.valueOf((Integer)request.getAttribute("idUsuarioOwner"));
    String miIdUsuario = String.valueOf((Integer)request.getAttribute("miIdUsuario"));
    
%>
<!DOCTYPE html>
<html>
   <head>
        <meta http-equiv='Content-Type' content='text/html;charset=UTF-8'> 
        <title>Esperando Jugadores</title>
        <link rel="stylesheet" href="css/salaDeEsperaEstilo.css">
        <script src="js/contadorSalaEspera.js"></script>
    </head>
    <body>
        <input type="hidden" id="idSala" value="<%= idSala %>" />
        <div class="contenedorCentrado">
            <div class="maximoContenedorTop">
                <div class="codigoCont">
                    <p>Codigo:</p>
                    <p class="codigo"><%= idSala %></p>
                </div>
                <div class="contenedorTablas">
                    <div class="contTemasSal">
                        <div class="celdaTitulo"><p class="columnaTitulo">Temas</p></div>
                        <div class="tablaTema">         
                            <% while (temas.next()){ %>
                                <div class="celdaTema">
                                    <div class="imgTema"><img src="imgSubidas/<%= temas.getString("tem_icono") %>" width="36" /></div>
                                    <div class="nombreTema"><%= temas.getString("tem_nombre") %></div>
                                </div>
                            <% }%>   
                        </div>
                    </div>
                    <table>
                        <thead>
                            <tr>
                                <td colspan="3" class="celdaTitulo"><p class="columnaTitulo">Jugadores</p></td>
                            </tr>
                        <thead>
                        <tbody id="bodyUsuarios">
                            <% while (usuarios.next()){ %>
                            <tr>
                                <td class="celdaJugador <% if(usuarios.getString("usu_id").equals(idUsuarioOwner)){ out.print("mi_usuario");} %> "><%= usuarios.getString("usu_login") %></td>
                            </tr>
                            <% }%>   
                        </tbody>
                    </table>              
                </div>
                <div class="contenedorBoton">
                    <p class="notificacionEspera">Esperando a que se unan todos...</p>
                    <% 
                        if(miIdUsuario.equals(idUsuarioOwner)){
                            out.print("<a href=\"Juego?accion=iniciarJuego\" class=\"botonIniciar\">Iniciar</a>");
                        }
                    %>
                    
                </div>
            </div>
        </div>
            
    </body>
</html>
