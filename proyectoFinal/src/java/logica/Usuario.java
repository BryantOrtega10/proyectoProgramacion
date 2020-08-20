/*
 * Clase Usuario
 *
 * Version 1
 *
 * 19 de Agosto de 2020
 *
 * Bryant Ortega
*/
package logica;

/**
 * La clase Usuario es la clase base
 * para instanciar Usuarios.
 */
public class Usuario {
    private int idUsuario;
    private String login;
    private String pass;
    private String email;
    private String rol;       /* Especifica si el usuario es admin o jugador */

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    
}
