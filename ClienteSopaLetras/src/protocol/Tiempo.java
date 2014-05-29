/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package protocol;

/**
 *
 * @author holaalex2204
 */
public class Tiempo implements java.io.Serializable{
    private int segundos;

    public Tiempo(int segundos) {
        this.segundos = segundos;
    }
    
    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }
    
}
