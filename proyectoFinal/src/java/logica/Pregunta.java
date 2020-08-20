/*
 * Clase Pregunta
 *
 * Version 1
 *
 * 17 de Agosto de 2020
 *
 * Bryant Ortega
*/
package logica;

import java.util.ArrayList;

/**
 * La clase Pregunta es la clase base
 * para instanciar las preguntas.
 */
public class Pregunta {
    private int idPregunta;
    private String txtPregunta;
    private int fkTema;             /* Llave foranea que la relaciona con el respectivo tema */
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
    //Una modificacion para probar
    //Estoy esperando a que cambie de color
}
