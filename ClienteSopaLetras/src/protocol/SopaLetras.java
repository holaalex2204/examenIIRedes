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
public class SopaLetras implements java.io.Serializable{
    private String[] palabras;
    private String [] contenido;
    public SopaLetras(String[] palabras, String[] contenido) {
        this.palabras = palabras;
        this.contenido = contenido;
    }
    
    public String[] getPalabras() {
        return palabras;
    }

    public void setPalabras(String[] palabras) {
        this.palabras = palabras;
    }

    public String[] getContenido() {
        return contenido;
    }

    public void setContenido(String[] contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "SopaLetras{" + "palabras=" + palabras + ", contenido=" + contenido + '}';
    }
    
}
