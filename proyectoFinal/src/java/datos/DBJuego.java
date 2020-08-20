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
import logica.Opcion;
import logica.Ronda;

/**
 *
 * @author bryda
 */
public class DBJuego {
    private DBConexion cn;
    
    public DBJuego(){
        cn = new DBConexion();
    }
    
   
    public Boolean terminarRondaxId(Integer idRonda) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("UPDATE `ronda` SET `estado`='terminado' WHERE `ron_id`=?");
            pstm.setInt(1, idRonda);
            pstm.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e);
            cn.setMensaje(e.getMessage());
        }
        return false;
    }
    public Integer insertarRonda(Ronda ronda) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("INSERT INTO `ronda`(`fk_tema_sala`, `fk_pregunta`, `estado`) VALUES (?,?,?);", Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, ronda.getFkTemaSala());
            pstm.setInt(2, ronda.getFkPregunta());
            pstm.setString(3, ronda.getEstado());
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
    public Integer insertarRondaUsuario(Integer idRonda, Integer idUsuario, Integer puntos) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("INSERT INTO `ronda_usuario`(`fk_ronda`, `fk_usuario`, `puntos`) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, idRonda);
            pstm.setInt(2, idUsuario);
            pstm.setInt(3, puntos);
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
    
    public ResultSet preguntaAleatoriaxSala(Integer fkSala) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("select * from pregunta AS p JOIN tema_sala AS ts ON ts.fk_tema = p.fk_tema "
                    + " WHERE ts.fk_sala = ? ORDER by rand() LIMIT 0,1");
            pstm.setInt(1, fkSala);
            ResultSet res = pstm.executeQuery();
            return res;
        } catch (SQLException e) {
            System.out.println(e);
            cn.setMensaje(e.getMessage());
        }
        return null;
    }
    public ResultSet rondaActualxSala(Integer fkSala) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT * FROM `ronda` AS r JOIN tema_sala AS ts on ts.tem_sal_id = r.fk_tema_sala "
                    + "WHERE ts.fk_sala = ? and estado='activa' LIMIT 0,1");
            pstm.setInt(1, fkSala);
            ResultSet res = pstm.executeQuery();
            return res;
        } catch (SQLException e) {
            System.out.println(e);
            cn.setMensaje(e.getMessage());
        }
        return null;
    }
    public ResultSet preguntaxRonda(Integer idRonda) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("select * from pregunta WHERE pre_id in (select fk_pregunta from ronda where ron_id = ?)");
            pstm.setInt(1, idRonda);
            ResultSet res = pstm.executeQuery();
            return res;
        } catch (SQLException e) {
            System.out.println(e);
            cn.setMensaje(e.getMessage());
        }
        return null;
    }
    
    public ResultSet opcionesxRonda(Integer idRonda) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("select * from opcion WHERE fk_pregunta in (select fk_pregunta from ronda where ron_id = ?)");
            pstm.setInt(1, idRonda);
            ResultSet res = pstm.executeQuery();
            return res;
        } catch (SQLException e) {
            System.out.println(e);
            cn.setMensaje(e.getMessage());
        }
        return null;
    }
    public ResultSet rondaxId(Integer idRonda) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("select * from ronda where ron_id = ?");
            pstm.setInt(1, idRonda);
            ResultSet res = pstm.executeQuery();
            return res;
        } catch (SQLException e) {
            System.out.println(e);
            cn.setMensaje(e.getMessage());
        }
        return null;
    }
    public ResultSet salaxRonda(Integer idRonda) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("select * from sala where sal_id in(SELECT fk_sala from tema_sala where tem_sal_id "
                    + "in(select fk_tema_sala from ronda where ron_id = ?))");
            pstm.setInt(1, idRonda);
            ResultSet res = pstm.executeQuery();
            return res;
        } catch (SQLException e) {
            System.out.println(e);
            cn.setMensaje(e.getMessage());
        }
        return null;
    }
    
    public ResultSet existeRondayUsuario(Integer idRonda, Integer idUsuario) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("select * from ronda_usuario where fk_ronda = ? and fk_usuario = ?");
            pstm.setInt(1, idRonda);
            pstm.setInt(2, idUsuario);
            ResultSet res = pstm.executeQuery();
            return res;
        } catch (SQLException e) {
            System.out.println(e);
            cn.setMensaje(e.getMessage());
        }
        return null;
    }
      
    public ResultSet puntosPorSala(Integer idSala) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT sum(ru.puntos) as puntosTotales,"
                    + " u.usu_login from ronda_usuario as ru JOIN usuario as u on u.usu_id = ru.fk_usuario "
                    + "WHERE ru.fk_ronda in(SELECT r.ron_id from ronda r WHERE r.fk_tema_sala "
                    + "in(SELECT ts.tem_sal_id from tema_sala ts WHERE ts.fk_sala= ? ))  group by ru.fk_usuario ORDER by puntosTotales DESC");
            pstm.setInt(1, idSala);
            ResultSet res = pstm.executeQuery();
            return res;
        } catch (SQLException e) {
            System.out.println(e);
            cn.setMensaje(e.getMessage());
        }
        return null;
    }
    public ResultSet cuentaRondasxSala(Integer idSala) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT count(r.ron_id) as cuenta from ronda as r WHERE r.fk_tema_sala "
                    + "in(SELECT ts.tem_sal_id from tema_sala as ts WHERE ts.fk_sala= ? ) and r.estado = 'terminado'");
            pstm.setInt(1, idSala);
            ResultSet res = pstm.executeQuery();
            return res;
        } catch (SQLException e) {
            System.out.println(e);
            cn.setMensaje(e.getMessage());
        }
        return null;
    }
      
    
    public ResultSet ultimaRondaTerminadaxSala(Integer fkSala) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT * FROM `ronda` AS r JOIN tema_sala AS ts on ts.tem_sal_id = r.fk_tema_sala "
                    + "WHERE ts.fk_sala = ? and estado='terminado' LIMIT 0,1");
            pstm.setInt(1, fkSala);
            ResultSet res = pstm.executeQuery();
            return res;
        } catch (SQLException e) {
            System.out.println(e);
            cn.setMensaje(e.getMessage());
        }
        return null;
    }   


    public String getMensaje() {
        return cn.getMensaje();
    }
}
