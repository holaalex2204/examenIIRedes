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
public class TimerEvent extends  java.util.EventObject implements java.io.Serializable {    
    int tiempo;
    
    public TimerEvent(Object source) {
        super(source);
    }

    public TimerEvent(int tiempo, Object source) {
        super(source);
        this.tiempo = tiempo;
    }
    

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }       
}
