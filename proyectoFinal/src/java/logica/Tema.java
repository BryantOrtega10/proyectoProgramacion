/*
 * Clase Tema
 *
 * Version 1
 *
 * 16 de Agosto de 2020
 *
 * Bryant Ortega
*/
package logica;

import java.util.ArrayList;

/**
 * La clase Temaa es la clase base
 * para instanciar las tenas.
 */
public class Tema {
    private int idtema;
    private String nombre;
    private String icono;
    private ArrayList<Pregunta> preguntas;

    public int getIdtema() {
        return idtema;
    }

    public void setIdtema(int idtema) {
        this.idtema = idtema;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }
    
}
