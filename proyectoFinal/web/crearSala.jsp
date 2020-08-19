<%-- 
    Document   : crearSala
    Created on : 18/08/2020, 05:59:25 PM
    Author     : oscar
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    Integer idSala = (Integer)request.getAttribute("idSalaNueva");
    ResultSet temas = (ResultSet)request.getAttribute("temas");
    
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Crear Sala</title>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <link rel="stylesheet" href="css/crearSalaEstilo.css">
        <script src="js/funcionesCliente.js"></script>
    </head>
    <body>
        <form action="Sala" method="POST" id="formcompletarSala" onsubmit="completarSala(); return false;">
            <input type="hidden" name="accion" value="completarSala" />
            <div class="centrado">            
                <p class="crearSalaTitulo">Crear una sala</p>
                <div class="codigoCont">
                    <p>Codigo:</p>
                    <p class="codigo"><%= idSala %></p>
                </div>
                <div class="codigoCont">
                    <label>Nombre Sala:</label>
                    <input type="text" name="nm_sala" />
                </div>
                
                <div class="numRondasCont">
                    <p>Numero de rondas:</p>
                    <select name="numRondas" id="numRondas" class="selects">
                        <% for (int i = 1; i <= 10; i++) {%>
                            <option value="<%= i %>"><%= i %></option>
                        <% } %>

                    </select>
                </div>

                <div class="temasCont">                
                    <div class="ladoDerecho">
                        <p class="sinmargen">Temas disponibles:</p>
                        <select id="temasI" class="selects" multiple>
                            <% while (temas.next()){ %>
                                <option value="<%= temas.getInt("tem_id") %>"><%= temas.getString("tem_nombre") %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="parteCentral">
                        <button type="button" onclick="enviarTemas(); return false;">&gt;&gt;</button>
                        <button type="button" onclick="devolverTemas(); return false;">&lt;&lt;</button>
                    </div>
                    <div class="ladoDerecho">
                        <p class="sinmargen">Temas seleccionados</p>
                        <select name="temas" id="temasF" class="selects" multiple>
                        </select>
                    </div>
                </div>
                <button type="submit" class="continuarbtn">Continuar</button>
           
            </div>
        </form>
    </body>
</html>
