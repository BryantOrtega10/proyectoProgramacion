/*
 * Clase Preguntas
 *
 * Version 1
 *
 * 16 de Agosto de 2020
 *
 * Bryant Ortega
*/
package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import logica.Pregunta;


/**
 * La clase DBPreguntas se encarga de la comunicación de la 
 * información, con la base de datos, relacionada 
 * con el manejo de los preguntas en el juego
 */
public class DBPreguntas {
    
    private DBConexion cn;
    
    public DBPreguntas(){
        cn = new DBConexion();
    }
    
    
    public Boolean modificar(Pregunta pregunta) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("UPDATE `pregunta` SET `pre_pregunta`=?,`pre_opcion_correcta`=? WHERE `pre_id`=?");
            pstm.setString(1, pregunta.getTxtPregunta());
            pstm.setString(2, pregunta.getOpcionCorrecta());
            pstm.setInt(3, pregunta.getIdPregunta());
            pstm.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e);
            cn.setMensaje(e.getMessage());
            return false;
        }
    }

    
    public Integer insertar(Pregunta pregunta) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("INSERT INTO `pregunta`(`pre_pregunta`,"
                    + " `pre_opcion_correcta`, `fk_tema`) VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, pregunta.getTxtPregunta());
            pstm.setString(2, pregunta.getOpcionCorrecta());
            pstm.setInt(3, pregunta.getTema());
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

    /**
     * El metodo consultarPorTema cunsulta
     * las preguntas relaciondas con una tema
     * en especifico
     */
    public ResultSet consultarPorTema(Integer tema) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT * "
                    + " FROM pregunta WHERE fk_tema = ?"
                    + " ORDER BY pre_id");
            pstm.setInt(1, tema);
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
                    + " FROM pregunta WHERE pre_id = ?");
            pstm.setInt(1, id);
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
                    + " FROM pregunta WHERE pre_id = ?");
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
