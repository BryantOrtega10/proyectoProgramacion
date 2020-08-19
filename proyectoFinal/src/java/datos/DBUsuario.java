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
import logica.Usuario;

/**
 *
 * @author bryda
 */
public class DBUsuario {
    
    private DBConexion cn;
    
    public DBUsuario(){
        cn = new DBConexion();
    }
    
    public Boolean modificar(Usuario usuario) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("UPDATE `usuario` SET "
                    + "`usu_login`=?,"
                    + "`usu_pass`=?,"
                    + "`usu_email`=? "
                    + "WHERE `usu_id`=?");
            pstm.setString(1, usuario.getLogin());
            pstm.setString(2, usuario.getPass());
            pstm.setString(3, usuario.getEmail());
            pstm.setInt(4, usuario.getIdUsuario());
            pstm.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e);
            cn.setMensaje(e.getMessage());
            return false;
        }
    }

    public Integer insertar(Usuario usuario) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("INSERT INTO `usuario`(`usu_login`, `usu_pass`, "
                    + "`usu_email`, `usu_rol`) VALUES (?, AES_ENCRYPT(?, 'UDistrital*10'), ?, ?);", Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, usuario.getLogin());
            pstm.setString(2, usuario.getPass());
            pstm.setString(3, usuario.getEmail());
            pstm.setString(4, usuario.getRol());
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
                    + " FROM usuario WHERE usu_id = ?");
            pstm.setInt(1, id);
            ResultSet res = pstm.executeQuery();
            return res;
        } catch (SQLException e) {
            System.out.println(e);
            cn.setMensaje(e.getMessage());
        }
        return null;
    }
    public ResultSet consultarPorLoginOEmail(String login, String email) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT * "
                    + " FROM usuario WHERE usu_login = ? or usu_email = ?");
            pstm.setString(1, login);
            pstm.setString(2, email);
            ResultSet res = pstm.executeQuery();
            return res;
        } catch (SQLException e) {
            System.out.println(e);
            cn.setMensaje(e.getMessage());
        }
        return null;
    }
    public ResultSet consultarPorLoginyPass(String login, String pass) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT * "
                    + " FROM usuario WHERE usu_login = ? and usu_pass = AES_ENCRYPT(?, 'UDistrital*10')");
            pstm.setString(1, login);
            pstm.setString(2, pass);
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
                    + " FROM usuario WHERE usu_id = ?");
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
