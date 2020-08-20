<%-- 
    Document   : temas
    Created on : 13/08/2020, 01:19:54 PM
    Author     : bryda
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    ResultSet preguntas = (ResultSet)request.getAttribute("preguntas");
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Preguntas</title>
        <link rel='stylesheet' href='css/estiloTemas.css'>
        <script src="js/funciones.js"></script>
    </head>
    <body>
        <div class="contMenu">
            <a href="Temas">Temas</a>
            <a href="Cerrar">Cerrar Sesion</a>
        </div>
        <div class="contDerecho">
            <h1>Preguntas</h1>
            <button type="button" onclick="mostrarPopup('agregarPregPop');" >Agregar Pregunta</button>
            <table width="800">
                <tr>
                    <th>&num;</th>
                    <th>Pregunta</th>
                    <th>Opcion correcta</th>
                    <th></th>
                </tr>
                <% while (preguntas.next()){ %>
                    <tr>
                        <td><%= preguntas.getString("pre_id") %></td>
                        <td><%= preguntas.getString("pre_pregunta") %></td>
                        <td><%= preguntas.getString("pre_opcion_correcta") %></td>
                        <td class="links">
                            <a href="Preguntas?accion=formularioPregMod&id=<%= preguntas.getString("pre_id") %>" onclick="formModPregAjax(this); return false;" class="modificar">Modificar</a>
                            <a href="Preguntas?accion=eliminarPreg&id=<%= preguntas.getString("pre_id") %>" onclick="elimiarPregAjax(this); return false;" class="eliminar">Eliminar</a>
                        </td>
                    </tr>
                <% }%>   
            </table>
        </div>
        <div class="fondoPop" id="fondoPop" onclick="clickFondoPop()"></div>
        <div class="contPopup" id="agregarPregPop">
            <h2>Agregar Pregunta</h2>
            <form action="Preguntas" method="post" id="formAddPreg" onsubmit="agregarPregAjax(); return false;" enctype="multipart">
                <input type="hidden" name="accion" value="agregarPreg" />
                <input type="hidden" name="fk_tema" value="<%= request.getParameter("fkTema") %>" />
                <table>
                    <tr>
                        <td colspan="2"><label for="pregunta">Pregunta:</label></td>
                    </tr>
                    <tr>
                        <td colspan="2"><textarea name="pregunta" id="pregunta"></textarea></td>
                    </tr>
                    <tr>
                        <td><label for="opcionA">Opcion A</label></td><td><input type="text" name="opcionA" id="opcionA" /></td>
                    </tr>
                    <tr>
                        <td><label for="opcionB">Opcion B</label></td><td><input type="text" name="opcionB" id="opcionB" /></td>
                    </tr>
                    <tr>
                        <td><label for="opcionC">Opcion C</label></td><td><input type="text" name="opcionC" id="opcionC" /></td>
                    </tr>
                    <tr>
                        <td><label for="opcionD">Opcion D</label></td><td><input type="text" name="opcionD" id="opcionD" /></td>
                    </tr>
                    <tr>
                        <td><label for="opcionCorrecta">Opcion Correcta:</label></td>
                        <td>
                            <select name="opcionCorrecta">
                                <option value="">Seleccione una</option>
                                <option value="A">A</option>
                                <option value="B">B</option>
                                <option value="C">C</option>
                                <option value="D">D</option>                                
                            </select>
                        </td>
                    </tr>
                    
                </table>
                
                
                <button type="submit">Ingresar Pregunta</button>                
            </form>
        </div>
        <div class="contPopup" id="editarPregPop">
            
        </div>
    </body>
</html>
