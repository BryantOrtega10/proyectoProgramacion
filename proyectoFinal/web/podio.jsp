<%-- 
    Document   : podio
    Created on : 11/08/2020, 06:41:33 PM
    Author     : oscar
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    ResultSet puntuacion = (ResultSet)request.getAttribute("puntuacion");
    
    
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Ganadores</title>
        <meta http-equiv='Content-Type' content='text/html;charset=UTF-8'>
        <link rel="stylesheet" href="css/podioEstilo.css">
    </head>
    <body>
        <div class="centrado">
            <p class="tituloPodio">Podio</p>
            <div class="podioImagen">
                <% if(puntuacion.next()){
                %>
                    <p class="ganador1"><%= puntuacion.getString("usu_login")%></p>
                <%
                }
                %>
                <% if(puntuacion.next()){
                %>
                    <p class="ganador2"><%= puntuacion.getString("usu_login")%></p>
                <%
                }
                %>
                <% if(puntuacion.next()){
                %>
                    <p class="ganador3"><%= puntuacion.getString("usu_login")%></p>
                <%
                }
                %>
                
            </div>
            <a href="Salas" class="botonIniciar">Volver al inicio</a>
        </div>
    </body>
</html>
