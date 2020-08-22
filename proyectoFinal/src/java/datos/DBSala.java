/*
 * Clase Preguntas
 *
 * Version 1
 *
 * 19 de Agosto de 2020
 *
 * Bryant Ortega
*/
package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import logica.Sala;
import logica.Usuario;

/**
 * La clase DBSala se encarga de la comunicación
 * de la información, con la base de datos, relacionada 
 * con el manejo de las salas en juego.
 */
public class DBSala {
private DBConexion cn;
    
    public DBSala(){
        cn = new DBConexion();
    }
    
    public Boolean modificar(Sala sala) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("UPDATE `sala` SET "
                    + "`sal_nombre`=?,"
                    + "`sal_estado`=?,"
                    + "`sal_rondas`=?, "
                    + "`sal_ronda_actual`=?,"
                    + "`sal_estado_int`=? "
                    + "WHERE `sal_id`=?");
            pstm.setString(1, sala.getNombre());
            pstm.setString(2, sala.getEstado());
            pstm.setInt(3, sala.getRondas());
            pstm.setInt(4, sala.getRondaActual());
            pstm.setString(5, sala.getEstadoInt());            
            pstm.setInt(6, sala.getIdSala());
            pstm.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e);
            cn.setMensaje(e.getMessage());
            return false;
        }
    }

    public Integer insertar(Sala sala) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("INSERT INTO `sala`(`sal_nombre`, `sal_estado`, "
                    + "`fk_usuario_owner`) VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, sala.getNombre());
            pstm.setString(2, sala.getEstado());
            pstm.setInt(3, sala.getCreador().getIdUsuario());
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
    public Boolean insertarRelacionTemas(Sala sala) {
        for (String tema : sala.getTemasRelacion()) {
            try {
                PreparedStatement pstm = cn.getConexion().prepareStatement("INSERT INTO `tema_sala`(`fk_sala`, `fk_tema`) VALUES (?,?);", Statement.RETURN_GENERATED_KEYS);
                pstm.setInt(1, sala.getIdSala());
                pstm.setString(2, tema);
                pstm.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
                cn.setMensaje(e.getMessage());
                return false;
            }
        }
        return true;
    }
    public Integer insertarRelacionUsuario(Sala sala, int idUsuario){
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("INSERT INTO `sala_usuario`(`fk_usuario`, `fk_sala`) VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, idUsuario);
            pstm.setInt(2, sala.getIdSala());            
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
    
    public ResultSet consultarPorId(int id) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT * "
                    + " FROM sala WHERE sal_id = ?");
            pstm.setInt(1, id);
            ResultSet res = pstm.executeQuery();
            return res;
        } catch (SQLException e) {
            System.out.println(e);
            cn.setMensaje(e.getMessage());
        }
        return null;
    }
    public ResultSet consultarPorIdyEstado(int id, String estado) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT * "
                    + " FROM sala WHERE sal_id = ? and sal_estado = ?");
            pstm.setInt(1, id);
            pstm.setString(2, estado);
            ResultSet res = pstm.executeQuery();
            return res;
        } catch (SQLException e) {
            System.out.println(e);
            cn.setMensaje(e.getMessage());
        }
        return null;
    }
    public ResultSet consultaPorEstadoPorUsuario(String estado,int idUsuario) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT * "
                    + " FROM sala WHERE (fk_usuario_owner = ? OR sal_id in(SELECT fk_sala from sala_usuario where fk_usuario = ?)) and sal_estado = ?");
            pstm.setInt(1, idUsuario);
            pstm.setInt(2, idUsuario);
            pstm.setString(3, estado);
            
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
                    + " FROM sala WHERE sal_id = ?");
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

