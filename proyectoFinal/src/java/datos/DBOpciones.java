/*
 * Clase DBOpciones
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
import logica.Opcion;
import logica.Pregunta;

/**
 * La clase DBOpciones se encarga de la comunicación
 * de la información, con la base de datos, relacionada 
 * con el manejo de los opciones de cada pregunta en el juego
 */
public class DBOpciones {
    private DBConexion cn;
    
    public DBOpciones(){
        cn = new DBConexion();
    }
    
    public Boolean modificar(Opcion opcion) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("UPDATE `opcion` SET `opc_opcion`=? WHERE `opc_id`=?");
            pstm.setString(1, opcion.getTxtOpcion());
            pstm.setInt(2, opcion.getIdOpcion());
            pstm.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e);
            cn.setMensaje(e.getMessage());
            return false;
        }
    }

    public Integer insertar(Opcion opcion) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("INSERT INTO `opcion`(`opc_opcion`,"
                    + " `fk_pregunta`) VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, opcion.getTxtOpcion());
            pstm.setInt(2, opcion.getFkPregunta());
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
     * El metodo consultarPorPregunta cunsulta
     * las opciones relaciondas con una pregunta
     * en especifico
     */
    public ResultSet consultarPorPregunta(Integer fkPregunta) {
        try {
            PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT * "
                    + " FROM opcion WHERE fk_pregunta = ? "
                    + " ORDER BY opc_id");
            pstm.setInt(1, fkPregunta);
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
