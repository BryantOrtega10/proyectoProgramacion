/*
 * Clase Ronda
 *
 * Version 1
 *
 * 18 de Agosto de 2020
 *
 * Bryant Ortega
*/
package logica;

/**
 * La clase Ronda es la clase encargada
 * de guardar informacion de referencia de estass.
 */
public class Ronda {
    private int idRonda;
    private int fkTemaSala;  /* Llave foranea que la relaciona con el respectivo tema */
    private int fkPregunta;  /* Llave foranea que la relaciona con la respectiva pregunta */
    private String estado;

    public int getIdRonda() {
        return idRonda;
    }

    public void setIdRonda(int idRonda) {
        this.idRonda = idRonda;
    }

    public int getFkTemaSala() {
        return fkTemaSala;
    }

    public void setFkTemaSala(int fkTemaSala) {
        this.fkTemaSala = fkTemaSala;
    }

    public int getFkPregunta() {
        return fkPregunta;
    }

    public void setFkPregunta(int fkPregunta) {
        this.fkPregunta = fkPregunta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
