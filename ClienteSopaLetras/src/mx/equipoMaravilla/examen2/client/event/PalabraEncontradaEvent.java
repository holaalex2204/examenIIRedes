/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.equipoMaravilla.examen2.client.event;

/**
 *
 * @author holaalex2204
 */
public class PalabraEncontradaEvent extends  java.util.EventObject implements java.io.Serializable {
    int filaInicio;
    int filaFin;
    int colInicio;
    int colFinal;
    String palabra;
    int tiempo;
    int id;
    public PalabraEncontradaEvent(Object source) {
        super(source);
    }

    public PalabraEncontradaEvent(int filaInicio, int filaFin, int colInicio, int colFinal, String palabra,Object source) {
        super(source);
        this.filaInicio = filaInicio;
        this.filaFin = filaFin;
        this.colInicio = colInicio;
        this.colFinal = colFinal;
        this.palabra = palabra;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
    

    public int getFilaInicio() {
        return filaInicio;
    }

    public void setFilaInicio(int filaInicio) {
        this.filaInicio = filaInicio;
    }

    public int getFilaFin() {
        return filaFin;
    }

    public void setFilaFin(int filaFin) {
        this.filaFin = filaFin;
    }

    public int getColInicio() {
        return colInicio;
    }

    public void setColInicio(int colInicio) {
        this.colInicio = colInicio;
    }

    public int getColFinal() {
        return colFinal;
    }

    public void setColFinal(int colFinal) {
        this.colFinal = colFinal;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
