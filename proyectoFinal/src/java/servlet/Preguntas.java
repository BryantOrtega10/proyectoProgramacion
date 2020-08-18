/*
 * Clase Preguntas
 *
 * Version 1
 *
 * 16 de Agosto de 2020
 *
 * Bryant Ortega
*/
package servlet;

import datos.DBOpciones;
import datos.DBPreguntas;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Opcion;
import logica.Pregunta;
import logica.Tema;

/**
 * La clase Preguntas se encarga de manejar las peticiones de
 * creacióm, edición y eliminación de preguntas
 */
@WebServlet(name = "Preguntas", urlPatterns = {"/Preguntas"})
public class Preguntas extends HttpServlet {
    private DBPreguntas BdPreguntas;
    private DBOpciones BdOpciones;
    private Pregunta preguntaGen;
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
        BdPreguntas = new DBPreguntas();
        BdOpciones = new DBOpciones();
        preguntaGen = new Pregunta();                                           
        if(request.getParameter("accion") != null &&  request.getParameter("accion").equals("formularioPregMod")){
            
            /* Código para mostrar la pantalla de modificación de una pregunta */
            int id = Integer.parseInt(request.getParameter("id"));
            ResultSet res = BdPreguntas.consultarPorId(id);
            
            try {
                
                if(res.next()){
                    
                    /* Se setean los parámetros de la pregunta */
                    preguntaGen.setIdPregunta(res.getInt("pre_id"));
                    preguntaGen.setOpcionCorrecta(res.getString("pre_opcion_correcta"));
                    preguntaGen.setTema(res.getInt("fk_tema"));
                    preguntaGen.setTxtPregunta(res.getString("pre_pregunta"));
                    
                    ResultSet resOpc = BdOpciones.consultarPorPregunta(preguntaGen.getIdPregunta());   /* Se consultan las opciones de la respectiva pregunta*/
                    ArrayList<Opcion> opciones = new ArrayList();
                    while (resOpc.next()) { 
                        
                        /* Se agregan las opciones consultadas*/
                        opciones.add(new Opcion(resOpc.getInt("opc_id"),resOpc.getString("opc_opcion"), resOpc.getInt("fk_pregunta")));
                    }
                    preguntaGen.setOpciones(opciones);
                    
                    out.print("<h2>Modificar Pregunta</h2>\n" +
                    "            <form action=\"Preguntas\" method=\"post\" id=\"formModPreg\" onsubmit=\"modificarPregAjax(); return false;\" >" +
                    "                <input type=\"hidden\" name=\"accion\" value=\"modificarPreg\" />\n" +
                    "                <input type=\"hidden\" name=\"id_pregunta\" value='" + String.valueOf(preguntaGen.getIdPregunta())+ "' />" +
                    "                <table>\n" +
                    "                    <tr>\n" +
                    "                        <td colspan=\"2\"><label for=\"pregunta\">Pregunta:</label></td>\n" +
                    "                    </tr>\n" +
                    "                    <tr>\n" +
                    "                        <td colspan=\"2\"><textarea name=\"pregunta\" id=\"pregunta\">"+preguntaGen.getTxtPregunta()+"</textarea></td>\n" +
                    "                    </tr>\n" +
                    "                    <tr>\n" +
                    "                        <td><label for=\"opcionA\">Opcion A</label></td>"
                    + "                      <td><input type=\"text\" name=\"opcionA\" id=\"modOpcionA\" value=\""+opciones.get(0).getTxtOpcion()+"\"/>"
                    + "                         <input type=\"hidden\" name=\"idOpcionA\" value=\""+String.valueOf(opciones.get(0).getIdOpcion())+"\"/></td>\n" +
                    "                    </tr>\n" +
                    "                    <tr>\n" +
                    "                        <td><label for=\"opcionB\">Opcion B</label></td>"
                    + "                      <td><input type=\"text\" name=\"opcionB\" id=\"modPpcionB\" value=\""+opciones.get(1).getTxtOpcion()+"\"/>"
                    + "                         <input type=\"hidden\" name=\"idOpcionB\" value=\""+String.valueOf(opciones.get(1).getIdOpcion())+"\"/></td>\n" +
                    "                    </tr>\n" +
                    "                    <tr>\n" +
                    "                        <td><label for=\"opcionC\">Opcion C</label></td>"
                    + "                      <td><input type=\"text\" name=\"opcionC\" id=\"modOpcionC\" value=\""+opciones.get(2).getTxtOpcion()+"\"/>"
                    + "                          <input type=\"hidden\" name=\"idOpcionC\" value=\""+String.valueOf(opciones.get(2).getIdOpcion())+"\"/></td>\n" +
                    "                    </tr>\n" +
                    "                    <tr>\n" +
                    "                        <td><label for=\"opcionD\">Opcion D</label></td>"
                    + "                      <td><input type=\"text\" name=\"opcionD\" id=\"modOpcionD\" value=\""+opciones.get(3).getTxtOpcion()+"\"/>"
                    + "                          <input type=\"hidden\" name=\"idOpcionD\" value=\""+String.valueOf(opciones.get(3).getIdOpcion())+"\"/></td>\n" +
                    "                    </tr>\n" +
                    "                    <tr>\n" +
                    "                        <td><label for=\"opcionCorrecta\">Opcion Correcta:</label></td>\n" +
                    "                        <td>\n" +
                    "                            <select name=\"opcionCorrecta\">\n" +
                    "                                <option value=\"\">Seleccione una</option>\n");
                    out.print("<option value=\"A\" "+( preguntaGen.getOpcionCorrecta().equals("A") ? "selected" : "" )+">A</option>\n");
                    out.print("<option value=\"B\" "+( preguntaGen.getOpcionCorrecta().equals("B") ? "selected" : "" )+">B</option>\n");
                    out.print("<option value=\"C\" "+( preguntaGen.getOpcionCorrecta().equals("C") ? "selected" : "" )+">C</option>\n");
                    out.print("<option value=\"D\" "+( preguntaGen.getOpcionCorrecta().equals("D") ? "selected" : "" )+">D</option>");
                    out.print("                 </select>\n" +
                    "                        </td>\n" +
                    "                    </tr>\n" +
                    "                    \n" +
                    "                </table>\n" +
                    "                \n" +
                    "                \n" +
                    "                <button type=\"submit\">Modificar Pregunta</button>" +
                    "            </form>");
                }
                else{
                    out.print("Error pregunta no encontrados");
                }
            } catch (SQLException ex) {
                out.print("Error al consultar la pregunta: " + BdPreguntas.getMensaje() + "<br>");
                out.print(ex.toString());
            }     


        }
        else if(request.getParameter("accion") != null &&  request.getParameter("accion").equals("eliminarPreg")){
            
            /* Código para eliminar una pregunta */
            int id = Integer.parseInt(request.getParameter("id"));
            if(BdPreguntas.eliminarPorId(id)){
                
                /* La pregunta se elimino con sus opciones */
                out.print("OK");
                /* La impresion del OK se toma por AJAX y permite la notificacion en pantalla al usuario*/
            }
            else{
                out.print("Error al eliminar el tema: " + BdPreguntas.getMensaje());
            }
        }
        else if(request.getParameter("accion") != null &&  request.getParameter("accion").equals("agregarPreg")){
            
            /* Código para agregar una pregunta */
            String pregunta = request.getParameter("pregunta");
            String opcionCorrecta = request.getParameter("opcionCorrecta");
            int fk_tema = Integer.parseInt(request.getParameter("fk_tema"));
            

            
            ArrayList<Opcion> opciones = new ArrayList();
            opciones.add(new Opcion(request.getParameter("opcionA"), 0));
            opciones.add(new Opcion(request.getParameter("opcionB"), 0));
            opciones.add(new Opcion(request.getParameter("opcionC"), 0));
            opciones.add(new Opcion(request.getParameter("opcionD"), 0));
            
            preguntaGen.setTxtPregunta(pregunta);
            preguntaGen.setOpcionCorrecta(opcionCorrecta);
            preguntaGen.setTema(fk_tema);
            preguntaGen.setOpciones(opciones);
            
            
            Integer idPregunta = BdPreguntas.insertar(preguntaGen);
            if(idPregunta != 0){
                
                /* Existe una pregunta y se guardan sus opciones */
                for (Opcion opcion : opciones) {
                    opcion.setFkPregunta(idPregunta);
                    BdOpciones.insertar(opcion);
                }
                out.print("OK");
                /* La impresion del OK se toma por AJAX y permite la notificacion en pantalla al usuario*/
            }
            else{
                out.print("Error al agregar el preguntas: " + BdPreguntas.getMensaje());
            }
        }
        else if(request.getParameter("accion") != null &&  request.getParameter("accion").equals("modificarPreg")){
            
            /* Código para guardar las modificaciones de una pregunta */
            String pregunta = request.getParameter("pregunta");
            String opcionCorrecta = request.getParameter("opcionCorrecta");
            int id_pregunta = Integer.parseInt(request.getParameter("id_pregunta"));


            
            
            int idOpcionA = Integer.parseInt(request.getParameter("idOpcionA"));
            int idOpcionB = Integer.parseInt(request.getParameter("idOpcionB"));
            int idOpcionC = Integer.parseInt(request.getParameter("idOpcionC"));
            int idOpcionD = Integer.parseInt(request.getParameter("idOpcionD"));
            
            
            

            ArrayList<Opcion> opciones = new ArrayList();
            opciones.add(new Opcion(request.getParameter("opcionA"), idOpcionA));
            opciones.add(new Opcion(request.getParameter("opcionB"), idOpcionB));
            opciones.add(new Opcion(request.getParameter("opcionC"), idOpcionC));
            opciones.add(new Opcion(request.getParameter("opcionD"), idOpcionD));
            
            preguntaGen.setTxtPregunta(pregunta);
            preguntaGen.setOpcionCorrecta(opcionCorrecta);
            preguntaGen.setOpciones(opciones);
            preguntaGen.setIdPregunta(id_pregunta);
                    
            
            if(BdPreguntas.modificar(preguntaGen)){
                
                /* Si la pregunta se guarda se guardan las nuevas opciones de la pregunta*/
                for (Opcion opcion : opciones) {
                    BdOpciones.modificar(opcion);
                }
                out.print("OK");
                /* La impresion del OK se toma por AJAX y permite la notificacion en pantalla al usuario*/
            }
            else{
                out.print("Error al modificar el preguntas: " + BdPreguntas.getMensaje());
            }
        }
        else{ 
            
            /* Muesta el menu de las preguntas relacionadas con un tema*/
            int fkTema = Integer.parseInt(request.getParameter("fkTema"));                             /* Se obtiene el tema de la preguntas por URL */
            ResultSet res = BdPreguntas.consultarPorTema(fkTema);
            if(res!= null){
                
                /* Se envia al jsp respectivo con la información de las preguntas*/
                RequestDispatcher view = request.getRequestDispatcher("preguntas.jsp");
                request.setAttribute("preguntas", res);
                view.forward(request, response);
            }
            else{
                out.print("Error al consultar las preguntas: " + BdPreguntas.getMensaje());
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
