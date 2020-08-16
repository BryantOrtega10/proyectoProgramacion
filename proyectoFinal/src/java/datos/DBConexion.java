/*
 * DBConexion.java
 * 
 * Created on 7/04/2008, 10:01:58 PM
 * 
 */

package datos;


import java.sql.*;
/**
 * Clase que permite conectar con mysql
 * @author alejo
 */
public class DBConexion {
    private static final String BD = "preguntados";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "";
    private static String url = "jdbc:mysql://localhost/"+BD;
    private static String mensaje = "";
    
    private Connection conexion = null;
    /**
     * Constructor de la clase
     */
    public DBConexion() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(url,LOGIN,PASSWORD);

            if (conexion!=null){
                    System.out.println("Conexión a base de datos "+BD+" OK");
            }
        }catch(SQLException e){
                mensaje = "Sql Error: " + e.getMessage();
        }catch(ClassNotFoundException e){
                mensaje = "Class Error: " + e.getMessage();
                System.out.println(e.getMessage());
        }
    }
    
    /**
     * Metodo para retornar mensajes de control
     * @return 
     */
    public static String getMensaje() {
        return mensaje;
    }

    /**
     * Metodo para setear mensajes de control
     * @param mensaje 
     */
    public static void setMensaje(String mensaje) {
        DBConexion.mensaje = mensaje;
    }
     
    /**
     * metodo que retorna la conexion a la bd
     * @return 
     */
    public Connection getConexion(){
        return conexion;
    }

    /**
     * metodo que desconecta la base de datos
     */
    public void desconectar(){
        conexion = null;
    }

}
