/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.equipoMaravilla.examen2.client.event.PalabraEncontradaEvent;
import protocol.SopaLetras;

/**
 *
 * @author holaalex2204
 */
public class Servidor extends Thread implements mx.equipoMaravilla.examen2.client.event.PalabraEncontradaListener {

    ServerSocket servidor;
    int port;
    SopaLetras sopa;
    ArrayList<ConexionServidor> jugadores;
    public Servidor(int port) {
        super();        
        try {
            this.port = port;
            System.out.println("Se intenta levantar el servidor en el puerto " + port);
            servidor = new ServerSocket(port);
            jugadores = new ArrayList<ConexionServidor>();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println(port);
        }
    }
    public void run()
    {
        try {            
            while(true)
            {
                System.out.println("Esperando Petición");
                Socket cliente = servidor.accept();
                System.out.println("Recibi petición!!" + port);
                ConexionServidor con = new ConexionServidor(cliente,true);            
                System.out.println("Ya establecí la conexión");
                con.setListener(this);
                con.start();
                jugadores.add(con);
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void handlePalabraEncontradaEvent(PalabraEncontradaEvent ev) {
        //Temporalmente el tiempo es fijo, pero después ya será diferente
        
        ev.setTiempo(10);
        for(ConexionServidor con : jugadores)
        {            
            if(con.jugando)
            {
                con.addPalabra(ev);
            }
        }
    }
    
}
