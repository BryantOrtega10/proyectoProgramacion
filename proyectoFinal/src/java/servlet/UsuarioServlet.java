/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import datos.DBUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Usuario;

/**
 *
 * @author bryda
 */
public class UsuarioServlet extends HttpServlet {

    private DBUsuario BdUsuario; 
    private Usuario usuGen; 
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
        usuGen = new Usuario();
        BdUsuario= new DBUsuario();
        if(request.getParameter("accion") != null && request.getParameter("accion").equals("registrarCliente")){     
            String pass = request.getParameter("pass");
            String rPass = request.getParameter("rpass");
            if(!pass.equals(rPass)){
                out.print("Error las contraseñas no coinciden");
                return;
            }
          
            usuGen.setLogin(request.getParameter("usuario"));
            usuGen.setEmail(request.getParameter("email"));
            usuGen.setPass(pass);
            usuGen.setRol("cliente");
            try {
                ResultSet res = BdUsuario.consultarPorLoginOEmail(usuGen.getLogin(), usuGen.getEmail());
                if(!res.isBeforeFirst()){
                    
                    int idUsuario = BdUsuario.insertar(usuGen);
                    usuGen.setIdUsuario(idUsuario);
                    if(idUsuario != 0){
                        HttpSession misession= request.getSession(true);
                        usuGen.setPass("");
                        misession.setAttribute("usuario",usuGen);
                        out.print("OK");
                    }
                    else{
                        out.print("Error al agregar usuario: " + BdUsuario.getMensaje());
                    }
                }
                else{
                    out.print("Error usuario o email ya está ocupado, usa otro");
                }
            } catch (SQLException ex) {
                out.print("Error al consultar el usuario: " + BdUsuario.getMensaje());
            }                
        }
        if(request.getParameter("accion") != null && request.getParameter("accion").equals("iniciarSesion")){     
            
            try {
                ResultSet res = BdUsuario.consultarPorLoginyPass(request.getParameter("usuario"), request.getParameter("pass"));
                if(res.next()){
                    usuGen.setIdUsuario(res.getInt("usu_id"));
                    usuGen.setEmail(res.getString("usu_email"));
                    usuGen.setLogin(res.getString("usu_login"));
                    usuGen.setRol(res.getString("usu_rol"));
                    HttpSession misession= request.getSession(true);
                    usuGen.setPass("");
                    misession.setAttribute("usuario",usuGen);
                    
                    out.print("OK");
                    
                    
                    
                }
                else{
                    out.print("Usuario o contraseña incorrectos");
                }
            } catch (SQLException ex) {
                out.print("Error al consultar el usuario: " + BdUsuario.getMensaje());
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
