/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import datos.DBSala;
import datos.DBTemas;
import datos.DBUsuario;
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
    private DBUsuario BdUsuario; 
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
        BdUsuario = new DBUsuario();
        salaGen = new Sala();
        
        
        if(usuario != null){            
            
            
            if(request.getParameter("accion") != null && request.getParameter("accion").equals("recargarUsuariosEspera")){
                try {    

                    ResultSet salaActiva = BdSala.consultaPorEstadoPorUsuario("activa",usuario.getIdUsuario());
                    if(salaActiva.next()){
                        ResultSet usuarios = BdUsuario.consultarPorSala(salaActiva.getInt("sal_id"), salaActiva.getInt("fk_usuario_owner"));
                        
                        while (usuarios.next()){
                            out.println("<tr>"+
                                "<td class=\"celdaJugador "+(usuarios.getInt("usu_id") == salaActiva.getInt("fk_usuario_owner")  ? "mi_usuario" : "")+" \">"+usuarios.getString("usu_login")+"</td>"+
                            "</tr>");
                        }
                        
                        
                        
                    }
                    
                } catch (SQLException ex) {
                    out.print("Error al consultar salas "+BdSala.getMensaje() );
                }
            }
            else if(request.getParameter("accion") != null && request.getParameter("accion").equals("ingresarSala")){
                try {
                    int codigoSala = Integer.parseInt(request.getParameter("codigoSala"));
                    ResultSet salaActivaPrev = BdSala.consultaPorEstadoPorUsuario("activa",usuario.getIdUsuario());
                    
                    if(salaActivaPrev.next()){
                        if(codigoSala == salaActivaPrev.getInt("sal_id")){
                            out.print("OK");
                        }
                        else{
                            out.print("Ya existe sala activa con el id "+ salaActivaPrev.getInt("sal_id"));
                        }
                    }
                    else{
                        ResultSet salaActiva = BdSala.consultarPorIdyEstado(codigoSala, "activa");                    
                        if(salaActiva.next()){
                            salaGen.setIdSala(salaActiva.getInt("sal_id"));
                            if(BdSala.insertarRelacionUsuario(salaGen, usuario.getIdUsuario()) != 0){
                                out.print("OK");
                            }
                            else{
                                out.print("Error al crear relacion usuario sala "+BdSala.getMensaje());
                            }
                        }
                        else{
                            out.print("Error la sala no existe o no esta activa " );
                        }
                    }
                    
                } catch (SQLException ex) {
                    out.print("Error al consultar salas "+BdSala.getMensaje() );
                }
            }
            else if(request.getParameter("accion") != null && request.getParameter("accion").equals("salaEspera")){
                try {
                    ResultSet salaActiva = BdSala.consultaPorEstadoPorUsuario("activa",usuario.getIdUsuario());
                    
                    if(salaActiva.next()){
                        
                        ResultSet temas = BdTemas.consultarPorSala(salaActiva.getInt("sal_id"));
                        ResultSet usuarios = BdUsuario.consultarPorSala(salaActiva.getInt("sal_id"), salaActiva.getInt("fk_usuario_owner"));
                        
                        
                        
                        RequestDispatcher view = request.getRequestDispatcher("salaDeEspera.jsp");
                        request.setAttribute("idSalaNueva", salaActiva.getInt("sal_id"));
                        request.setAttribute("idUsuarioOwner", salaActiva.getInt("fk_usuario_owner"));
                        request.setAttribute("miIdUsuario", usuario.getIdUsuario());
                        request.setAttribute("temas", temas);       
                        request.setAttribute("usuarios", usuarios);       
                        view.forward(request, response);
                    }
                    
                    
                    
                    
                } catch (SQLException ex) {
                    out.print("Error al consultar salas "+BdSala.getMensaje() );
                }
            }
            else if(request.getParameter("accion") != null && request.getParameter("accion").equals("completarSala")){
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
                        //Existe una sala activa debe mostrar la sala
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
