/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import logica.Sala;
import logica.Usuario;

/**
 *
 * @author bryda
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
                    + "`sal_rondas`=? "
                    + "WHERE `sal_id`=?");
            pstm.setString(1, sala.getNombre());
            pstm.setString(2, sala.getEstado());
            pstm.setInt(3, sala.getRondas());
            pstm.setInt(4, sala.getIdSala());
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
    public ResultSet consultaPorEstadoPorUsuario(String estado,int idUsuario) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT * "
                    + " FROM sala WHERE fk_usuario_owner = ? and sal_estado = ?");
            pstm.setInt(1, idUsuario);
            pstm.setString(2, estado);
            
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

