<%-- 
    Document   : salaPuntuaciones
    Created on : 19/08/2020, 08:00:39 PM
    Author     : oscar
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ResultSet puntuacion = (ResultSet)request.getAttribute("puntuacion");
    String idUsuarioOwner = String.valueOf((Integer)request.getAttribute("idUsuarioOwner"));
    String miIdUsuario = String.valueOf((Integer)request.getAttribute("miIdUsuario"));
    String idRonda = String.valueOf((Integer)request.getAttribute("idRonda"));
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv='Content-Type' content='text/html;charset=UTF-8'> 
        <title>Puntuación</title>
        <link rel="stylesheet" href="css/puntuacionSalaEstilo.css">
        
        <script src="js/contadorPuntuaciones.js"></script>
    </head>
    <body>
        <div class="contenedorCentrado">
            <p class='preguntaSalaTitulo'>Así vamos...</p>
            <table class="puntuaciones">
                <input type="hidden" id="idRonda" value="<%=idRonda%> " />
                    
    
                <tbody id="jugadores">    
                    <tr>
                        <td class="celdaJugadorTitulo"><p class="columnaTitulo">Jugadores</p></td>
                        <td class="lineaDivisora" rowspan="100"></td>
                        <td class="celdaPuntuacionTitulo"><p class="columnaTitulo">Puntuación</p></td>
                    </tr>
                    <% while (puntuacion.next()){ %>
                    <tr>
                        <td class="celdaJugador"><%= puntuacion.getString("usu_login") %></td>
                        <td class="celdaPuntuacion"><%= puntuacion.getString("puntosTotales") %></td>
                    </tr>
                    <% } %>
                </tbody>
            </table><br><br>
            <% 
                if(miIdUsuario.equals(idUsuarioOwner)){
                    out.print("<a href=\"Juego?accion=siguienteRonda&idRonda="+idRonda+"\" class=\"botonIniciar\">Siguiente Ronda</a>");
                }
            %>
                
                
                
        </div>
    </body>
</html>
