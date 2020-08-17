/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import datos.DBTemas;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Tema;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



/**
 *
 * @author bryda
 */
public class Temas extends HttpServlet {

    private DBTemas BdTemas;
    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 1024 * 1024;
    private int maxMemSize = 4 * 1024;
    private File file;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        // 
        BdTemas = new DBTemas();
        // si se quiere comprobar que es un request de ficheros
        isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            // Esta implementación crea instancias de FileItem que mantienen su contenido en memoria
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //asigna al item el tamaño maximo para memoria ram ya definido antes 
            factory.setSizeThreshold(maxMemSize);
            ServletFileUpload upload = new ServletFileUpload(factory);
            //asigna al item el tamaño maximo para subirlo ya definido antes
            upload.setSizeMax(maxFileSize);
            String imgDefecto = "";
            //a convertir una ruta de contenido en una ruta absoluta del sistema de archivos de disco
            filePath = getServletContext().getRealPath("/imgSubidas/");
            try {
                //crea una lista y le manda los archivos que se habian subido
                List fileItems = upload.parseRequest(request);
                Iterator i = fileItems.iterator();
                while (i.hasNext()) {
                    FileItem fi = (FileItem) i.next();
                    //si se recibe un archivo de formulario de una solicitud POST 
                    if (fi.isFormField()) {
                        //Asigna a nombreCambo el nombre que recibio del archivo
                        String nombreCampo = fi.getFieldName();
                        //para tildes y acentos
                        String valor = new String(fi.getString().getBytes("ISO-8859-1"), "UTF-8");
                        request.setAttribute(nombreCampo, valor);
                    } else {
                        
                        imgDefecto = fi.getName();
                        if (!imgDefecto.equals("")) {
                            //para saber si tiene \\ o / , Si tiene \\ entonces se quitan,para solo tener el nombre de la img sin la ruta
                            if (imgDefecto.lastIndexOf("\\") >= 0) {
                                file = new File(filePath + imgDefecto.substring(imgDefecto.lastIndexOf("\\")));
                            } else {
                                file = new File(filePath + imgDefecto.substring(imgDefecto.lastIndexOf("\\") + 1));
                            }
                            if (!file.isFile()) {
                                fi.write(file);
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
            //
            Tema temaGen = new Tema();
            //añadir tema
            if(request.getAttribute("accion") != null && request.getAttribute("accion").equals("agregarTema")){     
                //asigna al nuevo tema el nombre y imagen recibidos 
                temaGen.setNombre((String) request.getAttribute("nombreTema"));
                temaGen.setIcono(imgDefecto);
                try {
                    ResultSet res = BdTemas.consultarPorNombre(temaGen.getNombre());
                    //si no esta usando el nombre inserta el nuevo tema determinar si el cursor está en la posición predeterminada del ResultSet.
                    if(!res.isBeforeFirst()){
                        if(BdTemas.insertar(temaGen) != 0){
                            //si inserta el nuevo tema 
                            out.print("OK");
                        }
                        else{
                            out.print("Error al agregar el tema: " + BdTemas.getMensaje());
                        }
                    }
                    else{
                        out.print("Error ese nombre de tema ya está ocupado usa otro");
                    }
                } catch (SQLException ex) {
                    out.print("Error al consultar el tema: " + BdTemas.getMensaje());
                }                
            }
            //modificar tema
            else if(request.getAttribute("accion") != null && request.getAttribute("accion").equals("modificarTema")){
                temaGen.setNombre((String) request.getAttribute("nombreTema"));
                if(imgDefecto.equals("")){
                    temaGen.setIcono((String) request.getAttribute("iconoAnt"));
                }
                else{
                    temaGen.setIcono(imgDefecto);
                }
                temaGen.setIdtema(Integer.parseInt((String) request.getAttribute("idTema")));
                Boolean valido = true;
                if(!request.getAttribute("nombreTemaAnt").equals(request.getAttribute("nombreTema"))){
                    try {
                        ResultSet res = BdTemas.consultarPorNombre(temaGen.getNombre());
                        valido = !res.isBeforeFirst();
                    }
                    catch (SQLException ex) {
                       out.print("Error al consultar el tema: " + BdTemas.getMensaje());
                    }
                }
                if(valido){
                    if(BdTemas.modificar(temaGen)){
                        out.print("OK");
                    }
                    else{
                        out.print("Error al modificar el tema: " + BdTemas.getMensaje());
                    }
                }
                else{
                    out.print("Error ese nombre de tema ya está ocupado usa otro");
                }
            }
        }
        else{
            if(request.getParameter("accion") != null &&  request.getParameter("accion").equals("formularioTemasMod")){
                int id = Integer.parseInt(request.getParameter("id"));
                //Metodo de DBTemas que consulta un tema por id
                ResultSet res = BdTemas.consultarPorId(id);
                
                try {
                    if(res.next()){
                        Tema tema = new Tema();
                        tema.setNombre(res.getString("tem_nombre"));
                        tema.setIcono(res.getString("tem_icono"));
                        tema.setIdtema(res.getInt("tem_id"));
                        
                        out.print("<h2>Modificar tema</h2>" +
                            "<form action=\"Temas\" method=\"post\" id=\"formModTema\" onsubmit=\"modificarTemaAjax(); return false;\">" +
                            "   <input type=\"hidden\" name=\"accion\" value=\"modificarTema\" />" +
                            "   <input type=\"hidden\" name=\"idTema\" value=\""+String.valueOf(tema.getIdtema())+"\" />" +
                            "   <input type=\"hidden\" name=\"iconoAnt\" value=\""+tema.getIcono()+"\" />" +
                            "   <input type=\"hidden\" name=\"nombreTemaAnt\" value=\""+tema.getNombre()+"\" />" +
                            "   <table>" +
                            "       <tr> <td><label>Tema::</label></td>" +
                            "           <td><input type=\"text\" name=\"nombreTema\" value=\""+tema.getNombre()+"\" /></td>" +
                            "       </tr>"+
                            "       <tr> <td><label>Icono:</label></td>" +
                            "       <td><div class=\"contFoto\">" +
                            "           <img src=\"imgSubidas/"+tema.getIcono()+"\" class=\"\" id=\"modFoto\" />" +
                            "           <input type=\"file\" accept=\"image/*\" name=\"icono\" onchange=\"cambiarFotoMod(this);\">" +
                            "           <div id=\"modPlaceFoto\" class=\"oculto\">" +
                            "               Click para seleccionar" +
                            "          </div>" +
                            "       </div></td>" +
                            "       </tr>"+        
                            "   </table>"+
                            "   <button type=\"submit\">Modificar Tema</button>" +
                            "</form>");
                    }
                    else{
                        out.print("Error tema no encontrados");
                    }
                } catch (SQLException ex) {
                    out.print("Error al consultar el tema: " + BdTemas.getMensaje());
                }     
                
                
            }
            else if(request.getParameter("accion") != null &&  request.getParameter("accion").equals("eliminarTema")){
                int id = Integer.parseInt(request.getParameter("id"));
                if(BdTemas.eliminarPorId(id)){
                    out.print("OK");
                }
                else{
                    out.print("Error al eliminar el tema: " + BdTemas.getMensaje());
                }
            }
            else{                
                ResultSet res = BdTemas.consultar();
                if(res!= null){
                    RequestDispatcher view = request.getRequestDispatcher("temas.jsp");
                    request.setAttribute("temas", res);
                    view.forward(request, response);
                }
                else{
                    out.print("Error al consultar el tema: " + BdTemas.getMensaje());
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
