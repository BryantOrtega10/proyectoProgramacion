/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author bryda
 */
public class Ronda {
    private int idRonda;
    private int fkTemaSala;
    private int fkPregunta;
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
