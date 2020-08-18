/*
 * Clase DBOpciones
 *
 * Version 1
 *
 * 15 de Agosto de 2020
 *
 * Bryant Ortega
*/
package datos;

import java.sql.*;
import java.util.ArrayList;
import logica.Tema;

/**
 * La clase DBTemas se encarga de la comunicación de la 
 * información, con la base de datos, relacionada 
 * con el manejo de los temas en el juego
 */
public class DBTemas {
    
    private DBConexion cn;
    
    public DBTemas(){
        cn = new DBConexion();
    }
    
    public Boolean modificar(Tema tema) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("UPDATE `tema` SET `tem_nombre`=?,`tem_icono`=? WHERE `tem_id`=?");
            pstm.setString(1, tema.getNombre());
            pstm.setString(2, tema.getIcono());
            pstm.setInt(3, tema.getIdtema());
            pstm.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e);
            cn.setMensaje(e.getMessage());
            return false;
        }
    }

    public Integer insertar(Tema tema) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("INSERT INTO `tema`(`tem_nombre`,"
                    + " `tem_icono`) VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, tema.getNombre());
            pstm.setString(2, tema.getIcono());
            pstm.executeUpdate();
            ResultSet rs = pstm.getGeneratedKeys();
            if(rs.next())
            {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e);
            cn.setMensaje(e.getMessage());
        }
        return 0;
    }

    public ResultSet consultar() {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT *, (SELECT count(pre_id) FROM pregunta WHERE fk_tema = tem_id) as 'num_preguntas' "
                    + " FROM tema "
                    + " ORDER BY tem_nombre");
            ResultSet res = pstm.executeQuery();
            return res;
        } catch (SQLException e) {
            System.out.println(e);
            cn.setMensaje(e.getMessage());
        }
        return null;
    }

    public ResultSet consultarPorId(int id) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT * "
                    + " FROM tema WHERE tem_id = ?");
            pstm.setInt(1, id);
            ResultSet res = pstm.executeQuery();
            return res;
        } catch (SQLException e) {
            System.out.println(e);
            cn.setMensaje(e.getMessage());
        }
        return null;
    }
    
     public ResultSet consultarPorNombre(String nombreTema) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT * "
                    + " FROM tema WHERE tem_nombre LIKE ?");
            pstm.setString(1, nombreTema);
            ResultSet res = pstm.executeQuery();
            return res;
        } catch (SQLException e) {
            System.out.println(e);
            cn.setMensaje(e.getMessage());
        }
        return null;
    }
     
    public Boolean eliminarPorId(int id) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("DELETE "
                    + " FROM tema WHERE tem_id = ?");
            pstm.setInt(1, id);
            pstm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            cn.setMensaje(e.getMessage());
            return false;
        }
        
    }
    
    public String getMensaje() {
        return cn.getMensaje();
    }
   
}
