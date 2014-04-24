/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidor;

/**
 *
 * @author ramrodo
 */
public class Casilla implements java.io.Serializable{
    int fila;
    int col;
    String valor;

    public Casilla(int fila, int col, String tipo) {
        this.fila = fila;
        this.col = col;
    }
   
    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
}
