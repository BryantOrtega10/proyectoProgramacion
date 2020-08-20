<%-- 
    Document   : preguntaSala
    Created on : 18/08/2020, 11:23:44 AM
    Author     : oscar
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    ResultSet pregunta = (ResultSet)request.getAttribute("pregunta");
    ResultSet opciones = (ResultSet)request.getAttribute("opciones");
    ResultSet ronda = (ResultSet)request.getAttribute("ronda");
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv='Content-Type' content='text/html;charset=UTF-8'> 
        <title>Pregunta</title>
        <link rel="stylesheet" href="css/preguntaSalaEstilo.css">
        <script src="js/contadorPregunta.js"></script>

    </head>
    <body>
        <form action="Juego" method="post" id="formAgregarPregunta">
        <%  
            if(ronda.next()){
        %>    
        <input type="hidden" id="tiempoFinal" value="<%= ronda.getString("fechaCreacion")%>" />
        <input type="hidden" name="idRonda" value="<%= ronda.getInt("ron_id")%>" />
        <input type="hidden" id="tiempoActual" name="tiempoActual" value="60" />
        <input type="hidden" id="opcionSeleccionada" name="opcionSeleccionada" />
        <input type="hidden" value="agregarPuntosUser" name="accion" />
         <%} %>
            
        <%
        if(pregunta.next()){
        %>
            <div id="timer">60</div>
            <div class="contenedorCentrado">
                <p class='preguntaSalaTitulo'></p>
                <div class="contenedorPregunta">
                    <p class="preguntaText"><%= pregunta.getString("pre_pregunta")%></p>
                </div>
                <div class="contenedorOpciones">
                    <% if(opciones.next()){ %>
                        <p class="opcionText" id="opcionA" onclick="asignarOpcion('A');">A. <%= opciones.getString("opc_opcion")%></p>
                        <input type="hidden" name="idOpcionA" value="<%= opciones.getInt("opc_id")%>" />                    
                    <% } %>
                    <% if(opciones.next()){ %>
                        <p class="opcionText" id="opcionB" onclick="asignarOpcion('B');">B. <%= opciones.getString("opc_opcion")%></p>
                        <input type="hidden" name="idOpcionB" value="<%= opciones.getInt("opc_id")%>" />
                    <% } %>
                </div>
                <div class="contenedorOpciones">
                    <% if(opciones.next()){ %>
                        <p class="opcionText" id="opcionC" onclick="asignarOpcion('C');">C. <%= opciones.getString("opc_opcion")%></p>
                        <input type="hidden" name="idOpcionC" value="<%= opciones.getInt("opc_id")%>" />
                    <% } %>
                    <% if(opciones.next()){ %>
                        <p class="opcionText" id="opcionD" onclick="asignarOpcion('D');">D. <%= opciones.getString("opc_opcion")%></p>
                        <input type="hidden" name="idOpcionD" value="<%= opciones.getInt("opc_id")%>" />
                    <% } %>
                </div>
            </div>
        <% }        
        %>
        </form>
        
    </body>
</html>
