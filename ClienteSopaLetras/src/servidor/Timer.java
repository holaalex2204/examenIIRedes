/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidor;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.equipoMaravilla.examen2.client.event.TimerEvent;
import mx.equipoMaravilla.examen2.client.event.TimerListener;

/**
 *
 * @author holaalex2204
 */
public class Timer extends Thread implements Runnable{
    int segundos;
    boolean activo;
    private ArrayList<TimerListener> timerListeners;
    public Timer(int segundos) {
        this.segundos = segundos;
        activo  = false;
        timerListeners= new ArrayList<TimerListener>();
    }

    public void addTimerListener(TimerListener timerListener) {
        timerListeners.add(timerListener);
    }
     
    @Override
    public void run() {
        TimerEvent ev = new TimerEvent(this);
        while(segundos!=0 &&activo)
        {
            segundos--;
            ev.setTiempo(segundos);
            try {
                Thread.sleep(1000);
                for(TimerListener tl : timerListeners)
                {
                    tl.handleTimeEvent(ev);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(!activo)
        {
            segundos = 0;
            ev.setTiempo(0);
            for(TimerListener tl : timerListeners)
            {
                tl.handleTimeEvent(ev);
            }
        }
        activo=false;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }
    
    
    
}
