<%-- 
    Document   : temas
    Created on : 13/08/2020, 01:19:54 PM
    Author     : bryda
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    ResultSet temas = (ResultSet)request.getAttribute("temas");
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Temas</title>
        <link rel='stylesheet' href='css/estiloTemas.css'>
        <script src="js/funciones.js"></script>
    </head>
    <body>
        <div class="contMenu">
            <a href="Temas">Temas</a>
            <a href="Usuarios">Usuarios</a>
            <a href="Cerrar">Cerrar S</a>
        </div>
        <div class="contDerecho">
            <h1>Temas</h1>
            <button type="button" onclick="mostrarPopup('agregarTemaPop');" >Agregar Tema</button>
            <table width="800">
                <tr>
                    <th>&num;</th>
                    <th>Nombre</th>
                    <th>Icono</th>
                    <th></th>
                </tr>
                <% while (temas.next()){ %>
                    <tr>
                        <td><%= temas.getString("tem_id") %></td>
                        <td><%= temas.getString("tem_nombre") %></td>
                        <td><img src="imgSubidas/<%= temas.getString("tem_icono") %>" width="36" /></td>
                        <td class="links">

                            <a href="Preguntas?fkTema=<%= temas.getString("tem_id") %>">Preguntas(<%= temas.getString("num_preguntas") %>)</a>
                            <a href="Temas?accion=formularioTemasMod&id=<%= temas.getString("tem_id") %>" onclick="formModTemaAjax(this); return false;" class="modificar">Modificar</a>
                            <a href="Temas?accion=eliminarTema&id=<%= temas.getString("tem_id") %>" onclick="elimiarTemaAjax(this); return false;" class="eliminar">Eliminar</a>

                        </td>
                    </tr>
                <% }%>   
            </table>
        </div>
        <div class="fondoPop" id="fondoPop" onclick="clickFondoPop()"></div>
        <div class="contPopup" id="agregarTemaPop">
            <h2>Agregar tema</h2>
            <form action="Temas" method="post" id="formAddTema" onsubmit="agregarTemaAjax(); return false;" enctype="multipart">
                <input type="hidden" name="accion" value="agregarTema" />
                <table>
                    <tr>
                        <td><label for="nombreTema">Tema:</label></td><td><input type="text" name="nombreTema" id="nombreTema" /></td>
                    </tr>
                    <tr>
                        <td><label for="icono">Icono:</label></td>
                        <td>
                            <div class="contFoto">
                            <img src="" class="oculto" id="foto" />
                            <input type="file" accept="image/*" name="icono" id="icono" onchange="cambiarFoto(this);">
                                <div id="placeFoto" class="activo">					
                                        Click para seleccionar
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
                
                
                <button type="submit">Ingresar Tema</button>                
            </form>
        </div>
        <div class="contPopup" id="editarTemaPop">
            
        </div>
    </body>
</html>
