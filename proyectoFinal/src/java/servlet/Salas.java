/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import datos.DBSala;
import datos.DBTemas;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Sala;
import logica.Usuario;

/**
 *
 * @author bryda
 */
public class Salas extends HttpServlet {

    private DBSala BdSala; 
    private DBTemas BdTemas; 
    private Sala salaGen; 
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
        PrintWriter out = response.getWriter();
        HttpSession misession=  request.getSession();
        Usuario usuario= (Usuario) misession.getAttribute("usuario");
        
        BdSala = new DBSala();
        BdTemas = new DBTemas();
        salaGen = new Sala();
        
        
        if(usuario != null){            
            
            if(request.getParameter("accion") != null && request.getParameter("accion").equals("completarSala")){                
                String[] temasSeleccionados = request.getParameterValues("temas");
                if(temasSeleccionados.length<=2){
                    out.print("Error selecciona al menos 3 temas");
                    return;
                }
                try {
                    ResultSet salaCreacion = BdSala.consultaPorEstadoPorUsuario("enCreacion",usuario.getIdUsuario());
                    if(salaCreacion.next()){
                        int rondas = Integer.parseInt(request.getParameter("numRondas"));
                        
                        salaGen.setIdSala(salaCreacion.getInt("sal_id"));
                        salaGen.setCreador(usuario);
                        salaGen.setNombre(request.getParameter("nm_sala"));
                        salaGen.setEstado("activa");
                        salaGen.setTemasRelacion(temasSeleccionados);
                        salaGen.setRondas(rondas);
                        
                        
                        BdSala.modificar(salaGen);
                        if(BdSala.insertarRelacionTemas(salaGen)){
                            out.print("OK");
                            
                        }
                        else{
                            out.print("Error al modificar la sala: " + BdSala.getMensaje());
                        }
                        
                    }
                    
                } catch (SQLException ex) {
                    out.print("Error al consultar la sala: " + BdSala.getMensaje());
                }
            }
            else if(request.getParameter("accion") != null && request.getParameter("accion").equals("crearSala")){
                ResultSet salaActiva = BdSala.consultaPorEstadoPorUsuario("activa",usuario.getIdUsuario());
                try {
                    if(salaActiva.next()){
                        out.print("Existe una sala activa debe mostrar la sala");
                        response.sendRedirect("Salas?accion=salaEspera");
                    }
                    else{
                        ResultSet salaCreacion = BdSala.consultaPorEstadoPorUsuario("enCreacion",usuario.getIdUsuario());
                        
                        
                        if(salaCreacion.next()){
                            ResultSet temas = BdTemas.consultar();
                            RequestDispatcher view = request.getRequestDispatcher("crearSala.jsp");
                            request.setAttribute("idSalaNueva", salaCreacion.getInt("sal_id"));
                            request.setAttribute("temas", temas);       
                            view.forward(request, response);
                        }
                        else{                            
                            salaGen.setCreador(usuario);
                            salaGen.setEstado("enCreacion");
                            int idSalaNueva = BdSala.insertar(salaGen);
                            if(idSalaNueva!=0){
                                
                                
                                ResultSet temas = BdTemas.consultar();
                                RequestDispatcher view = request.getRequestDispatcher("crearSala.jsp");
                                request.setAttribute("idSalaNueva", idSalaNueva);
                                request.setAttribute("temas", temas);                                
                                view.forward(request, response);
                            }else{
                                out.print("Error al crear la sala: " + BdSala.getMensaje());
                            }
                        }
                    }
                    
                } catch (SQLException ex) {
                    out.print("Error al consultar la sala: " + BdSala.getMensaje());
                }
            }
            else{
                RequestDispatcher view = request.getRequestDispatcher("ingresarSala.jsp");
                view.forward(request, response);
            }
            
        }
        else{
            response.sendRedirect("index.jsp");
            
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
