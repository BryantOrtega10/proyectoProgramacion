/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;

/**
 *
 * @author bryda
 */
public class Pregunta {
    private int idPregunta;
    private String txtPregunta;
    private int fkTema;
    private String opcionCorrecta;
    private ArrayList<Opcion> opciones;

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getTxtPregunta() {
        return txtPregunta;
    }

    public void setTxtPregunta(String txtPregunta) {
        this.txtPregunta = txtPregunta;
    }

    public int getTema() {
        return fkTema;
    }

    public void setTema(int fkTema) {
        this.fkTema = fkTema;
    }

    public String getOpcionCorrecta() {
        return opcionCorrecta;
    }

    public void setOpcionCorrecta(String opcionCorrecta) {
        this.opcionCorrecta = opcionCorrecta;
    }

    public ArrayList<Opcion> getOpciones() {
        return opciones;
    }

    public void setOpciones(ArrayList<Opcion> opciones) {
        this.opciones = opciones;
    }
    
    
}
