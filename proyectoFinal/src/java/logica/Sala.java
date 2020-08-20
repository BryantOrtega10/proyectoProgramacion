/*
 * Clase Sala
 *
 * Version 1
 *
 * 18 de Agosto de 2020
 *
 * Bryant Ortega
*/
package logica;

import java.util.ArrayList;
import servlet.Temas;

/**
 * La clase Sala es la clase base
 * para instanciar salas.
 */
public class Sala {
    private int idSala;
    private String nombre;
    private String estado;             /* Determina si la sala ya tiene sus atributos */ 
    private Usuario creador; 
    private String[] temasRelacion;    /* Temas que contendra la sala */
    private int rondas;
    private String estadoInt;          /* Determina si la sala ya esta en juego */
    private int rondaActual;
    
    public Sala(){
        this.idSala = 0;
        this.nombre = "";
        this.estado = "";
        this.estadoInt = "enSala";
    }
    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getCreador() {
        return creador;
    }

    public void setCreador(Usuario creador) {
        this.creador = creador;
    }


    public String[] getTemasRelacion() {
        return temasRelacion;
    }

    public void setTemasRelacion(String[] temasRelacion) {
        this.temasRelacion = temasRelacion;
    }

    public int getRondas() {
        return rondas;
    }

    public void setRondas(int rondas) {
        this.rondas = rondas;
    }

    public String getEstadoInt() {
        return estadoInt;
    }

    public void setEstadoInt(String estadoInt) {
        this.estadoInt = estadoInt;
    }

    public int getRondaActual() {
        return rondaActual;
    }

    public void setRondaActual(int rondaActual) {
        this.rondaActual = rondaActual;
    }
    
    
    
}
