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
public class Opcion {
    private int idOpcion;
    private String txtOpcion;
    private int fkPregunta;

    public Opcion(){
        this.idOpcion = 0;
        this.txtOpcion = "";
        this.fkPregunta = 0;
    }

    public Opcion(String txtOpcion, int fkPregunta) {
        this.idOpcion = 0;
        this.txtOpcion = txtOpcion;
        this.fkPregunta = fkPregunta;
    }

    public Opcion(int idOpcion, String txtOpcion, int fkPregunta) {
        this.idOpcion = idOpcion;
        this.txtOpcion = txtOpcion;
        this.fkPregunta = fkPregunta;
    }
    
        
    public int getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(int idOpcion) {
        this.idOpcion = idOpcion;
    }

    public String getTxtOpcion() {
        return txtOpcion;
    }

    public void setTxtOpcion(String txtOpcion) {
        this.txtOpcion = txtOpcion;
    }

    public int getFkPregunta() {
        return fkPregunta;
    }

    public void setFkPregunta(int fkPregunta) {
        this.fkPregunta = fkPregunta;
    }

   

    
}
