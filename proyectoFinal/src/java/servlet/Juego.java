/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import datos.DBJuego;
import datos.DBSala;
import datos.DBTemas;
import datos.DBUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Ronda;
import logica.Sala;
import logica.Usuario;

/**
 *
 * @author bryda
 */
public class Juego extends HttpServlet {
    
    private DBSala BdSala; 
    private DBJuego BdJuego;
    private Sala salaGen; 
    private Ronda rondaGen; 
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
        
        HttpSession misession=  request.getSession();
        Usuario usuario= (Usuario) misession.getAttribute("usuario");
        
        BdSala = new DBSala();
        BdJuego = new DBJuego();
        salaGen = new Sala();
        rondaGen = new Ronda();
        
        if(usuario != null){
            
            if(request.getParameter("accion") != null && request.getParameter("accion").equals("podio")){
                
                try{
                    int idRonda = Integer.parseInt(request.getParameter("idRonda"));
                    ResultSet sala = BdJuego.salaxRonda(idRonda);
                    if(sala.next()){
                        ResultSet puntuacion = BdJuego.puntosPorSala(sala.getInt("sal_id"));
                        RequestDispatcher view = request.getRequestDispatcher("podio.jsp");
                        request.setAttribute("miIdUsuario", usuario.getIdUsuario()); 
                        request.setAttribute("idUsuarioOwner", sala.getInt("fk_usuario_owner")); 
                        request.setAttribute("idRonda", idRonda); 
                        request.setAttribute("puntuacion", puntuacion);                               
                        view.forward(request, response);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex);
                    out.print("Error al consultar pregunta 123 : " + BdJuego.getMensaje());
                    
                }
            }
            if(request.getParameter("accion") != null && request.getParameter("accion").equals("obtenerPuntuaciones")){
                try{
                    int idRonda = Integer.parseInt(request.getParameter("idRonda"));
                    ResultSet sala = BdJuego.salaxRonda(idRonda);
                    if(sala.next()){
                        ResultSet puntuacion = BdJuego.puntosPorSala(sala.getInt("sal_id"));
                        out.print("<tr>\n" +
"                        <td class=\"celdaJugadorTitulo\"><p class=\"columnaTitulo\">Jugadores</p></td>\n" +
"                        <td class=\"lineaDivisora\" rowspan=\"100\"></td>\n" +
"                        <td class=\"celdaPuntuacionTitulo\"><p class=\"columnaTitulo\">Puntuación</p></td>\n" +
"                    </tr>");
                        while (puntuacion.next()){
                        out.print("<tr>\n" +
"                        <td class=\"celdaJugador\">" + puntuacion.getString("usu_login")+ "</td>\n" +
"                        <td class=\"celdaPuntuacion\">" + puntuacion.getString("puntosTotales")+"</td>\n" +
"                    </tr>");
                        }
                        
                        
                        
                    }
                } catch (SQLException ex) {
                    System.out.println(ex);
                    out.print("Error al consultar pregunta  : " + BdJuego.getMensaje());
                    
                }
            }
            else if(request.getParameter("accion") != null && request.getParameter("accion").equals("siguienteRonda")){
                try{ 
                    int idRonda = Integer.parseInt(request.getParameter("idRonda"));
                    BdJuego.terminarRondaxId(idRonda);
                    ResultSet sala = BdJuego.salaxRonda(idRonda);
                    if(sala.next()){
                        ResultSet cuentaRondas = BdJuego.cuentaRondasxSala(sala.getInt("sal_id"));
                        if(cuentaRondas.next()){
                            
                            
                            if(cuentaRondas.getInt("cuenta") == sala.getInt("sal_rondas")){
                                salaGen.setEstado("Terminada");
                                salaGen.setEstadoInt("Terminada");
                                salaGen.setIdSala(sala.getInt("sal_id"));
                                salaGen.setRondas(sala.getInt("sal_rondas"));
                                salaGen.setNombre(sala.getString("sal_nombre"));
                                salaGen.setRondaActual(sala.getInt("sal_rondas"));
                                
                                BdSala.modificar(salaGen);
                                response.sendRedirect("Juego?accion=podio&idRonda="+idRonda);
                                
                            }
                            else{
                                salaGen.setEstado("activa");
                                salaGen.setEstadoInt("enPregunta");
                                salaGen.setIdSala(sala.getInt("sal_id"));
                                salaGen.setRondas(sala.getInt("sal_rondas"));
                                salaGen.setNombre(sala.getString("sal_nombre"));
                                salaGen.setRondaActual(sala.getInt("sal_ronda_actual") + 1);
                                BdSala.modificar(salaGen);
                                ResultSet preguntaAleatoria = BdJuego.preguntaAleatoriaxSala(salaGen.getIdSala());
                                if(preguntaAleatoria.next()){
                                    rondaGen.setEstado("activa");
                                    rondaGen.setFkTemaSala(preguntaAleatoria.getInt("tem_sal_id"));
                                    rondaGen.setFkPregunta(preguntaAleatoria.getInt("pre_id"));
                                    int idRondaEnv = BdJuego.insertarRonda(rondaGen);
                                    response.sendRedirect("Juego?accion=pregunta&idRonda="+idRondaEnv);
                                }
                            }
                            
                        }
                    }
                    

                    
                } catch (SQLException ex) {
                    System.out.println(ex);
                    out.print("Error al consultar pregunta : " + BdJuego.getMensaje());
                }
            }
            else if(request.getParameter("accion") != null && request.getParameter("accion").equals("comoVamos")){
               
                try{
                    int idRonda = Integer.parseInt(request.getParameter("idRonda"));
                    ResultSet sala = BdJuego.salaxRonda(idRonda);
                    if(sala.next()){
                        ResultSet puntuacion = BdJuego.puntosPorSala(sala.getInt("sal_id"));
                        RequestDispatcher view = request.getRequestDispatcher("salaPuntuaciones.jsp");
                        request.setAttribute("miIdUsuario", usuario.getIdUsuario()); 
                        request.setAttribute("idUsuarioOwner", sala.getInt("fk_usuario_owner")); 
                        request.setAttribute("idRonda", idRonda); 
                        request.setAttribute("puntuacion", puntuacion);                               
                        view.forward(request, response);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex);
                    out.print("Error al consultar pregunta 123 : " + BdJuego.getMensaje());
                    
                }
            }
            else if(request.getParameter("accion") != null && request.getParameter("accion").equals("agregarPuntosUser")){
                
               
                int idRonda = Integer.parseInt(request.getParameter("idRonda"));
                try{
                    
                    int tiempoActual = Integer.parseInt(request.getParameter("tiempoActual"));
                    String opcionSeleccionada = request.getParameter("opcionSeleccionada");
                    
                    ResultSet pregunta = BdJuego.preguntaxRonda(idRonda);
                    if(pregunta.next()){
                        int puntos = 0;
                        if(tiempoActual > 0 && pregunta.getString("pre_opcion_correcta").equals(opcionSeleccionada)){
                            puntos = (10 * tiempoActual);
                        }
                        else if(tiempoActual <= 0 && pregunta.getString("pre_opcion_correcta").equals(opcionSeleccionada)){
                            puntos = 5;
                        }
                        ResultSet existeUsuarioRonda = BdJuego.existeRondayUsuario(idRonda, usuario.getIdUsuario());
                        if(!existeUsuarioRonda.next()){
                            BdJuego.insertarRondaUsuario(idRonda, usuario.getIdUsuario(), puntos);
                        }
                        
                        
                    }
                } catch (SQLException ex) {
                    out.print("Error al consultar pregunta : " + BdJuego.getMensaje());
                }
                response.sendRedirect("Juego?accion=comoVamos&idRonda="+idRonda);
            }            
            else if(request.getParameter("accion") != null && request.getParameter("accion").equals("pregunta")){
                
                int idRonda = Integer.parseInt(request.getParameter("idRonda"));
                ResultSet pregunta = BdJuego.preguntaxRonda(idRonda);
                ResultSet opciones = BdJuego.opcionesxRonda(idRonda);
                ResultSet ronda = BdJuego.rondaxId(idRonda);

                RequestDispatcher view = request.getRequestDispatcher("preguntaSala.jsp");
                request.setAttribute("pregunta", pregunta);       
                request.setAttribute("opciones", opciones);
                request.setAttribute("ronda", ronda);
                view.forward(request, response);
                
            }
            else if(request.getParameter("accion") != null && request.getParameter("accion").equals("ruleta")){
                RequestDispatcher view = request.getRequestDispatcher("ruleta.jsp");

                view.forward(request, response);
            }
            else if(request.getParameter("accion") != null && request.getParameter("accion").equals("verificarEstado")){
                try{
                    
                    ResultSet salaActiva = BdSala.consultaPorEstadoPorUsuario("activa",usuario.getIdUsuario());
                    
                    if(salaActiva.next()){
                        if(salaActiva.getString("sal_estado_int").equals("enPregunta")){
                            
                            ResultSet ultimaRondaActiva = BdJuego.rondaActualxSala(salaActiva.getInt("sal_id"));
                            
                            if(ultimaRondaActiva.next()){
                                
                                if(request.getParameter("idRonda") != null && request.getParameter("idRonda").equals(String.valueOf(ultimaRondaActiva.getInt("ron_id")))){
                                    
                                }
                                else{
                                    out.print("Juego?accion=pregunta&idRonda="+ultimaRondaActiva.getInt("ron_id"));
                                }
                            }
                            else{
                       
                            }
                        }
                    }
                    else{
                        ResultSet salaTerminada = BdSala.consultaPorEstadoPorUsuario("Terminada",usuario.getIdUsuario());
                        if(salaTerminada.next()){
                            ResultSet ultimaRondaTerminadaxSala = BdJuego.ultimaRondaTerminadaxSala(salaTerminada.getInt("sal_id"));    
                            if(ultimaRondaTerminadaxSala.next()){
                                out.print("Juego?accion=podio&idRonda="+ultimaRondaTerminadaxSala.getInt("ron_id"));
                            }                            
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println(ex);
                    out.print("Error al consultar la sala: " + BdSala.getMensaje());
                }
            }
            else if(request.getParameter("accion") != null && request.getParameter("accion").equals("iniciarJuego")){
                try{
                    ResultSet salaActiva = BdSala.consultaPorEstadoPorUsuario("activa",usuario.getIdUsuario());
                    if(salaActiva.next()){
                        
                        salaGen.setIdSala(salaActiva.getInt("sal_id"));
                        salaGen.setNombre(salaActiva.getString("sal_nombre"));
                        salaGen.setEstado(salaActiva.getString("sal_estado"));
                        salaGen.setRondas(salaActiva.getInt("sal_rondas"));
                        salaGen.setEstadoInt("enPregunta");
                        salaGen.setRondaActual(1);
                        
                        BdSala.modificar(salaGen);
                        ResultSet preguntaAleatoria = BdJuego.preguntaAleatoriaxSala(salaGen.getIdSala());
                        if(preguntaAleatoria.next()){
                            rondaGen.setEstado("activa");
                            rondaGen.setFkTemaSala(preguntaAleatoria.getInt("tem_sal_id"));
                            rondaGen.setFkPregunta(preguntaAleatoria.getInt("pre_id"));
                            int idRonda = BdJuego.insertarRonda(rondaGen);
                            response.sendRedirect("Juego?accion=pregunta&idRonda="+idRonda);
                        }
                        
                    }
                } catch (SQLException ex) {
                    out.print("Error al consultar la sala: " + BdSala.getMensaje());
                }
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
