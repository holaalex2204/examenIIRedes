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
import logica.ColocarPalabra;
import logica.ManejoArchivos;
import mx.equipoMaravilla.examen2.client.event.PalabraEncontradaEvent;
import protocol.SopaLetras;

/**
 *
 * @author holaalex2204
 */
public class Servidor extends Thread implements mx.equipoMaravilla.examen2.client.event.PalabraEncontradaListener {
    final int cantidadJugadores  = 2;
    final int duracionJuego = 300;//duración del juego en segudnos
    Timer tiempo;
    ServerSocket servidor;
    int port;
    SopaLetras sopa;    
    private int palabrasRestantes;
    private int idJugadorGanador;
    int contadorPalabras[] = new int[cantidadJugadores];
    ArrayList<ConexionServidor> jugadores;
    public Servidor(int port, SopaLetras sopa) {
        super();        
        try {
            this.port = port;
            System.out.println("Se intenta levantar el servidor en el puerto " + port);
            servidor = new ServerSocket(port);
            jugadores = new ArrayList<ConexionServidor>();
            this.sopa = sopa;
            palabrasRestantes = sopa.getPalabras().length;            
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println(port);
        }
    }
    public void run()
    {
        tiempo = new Timer(duracionJuego);
        try {            
            while(true)
            {
                
                System.out.println("Esperando Petición");
                Socket cliente = servidor.accept();
                System.out.println("Recibi petición!!" + port);
                ConexionServidor con;
                if(jugadores.size()<cantidadJugadores)
                {
                    con = new ConexionServidor(cliente,true,sopa,port,jugadores.size());                    
                }
                else
                {
                    con = new ConexionServidor(cliente,false,sopa,port,0);
                }                
                System.out.println("Ya establecí la conexión");
                con.setListener(this);
                con.start();
                if(con.aceptar){
                    //Agregamos al jugador a la lista de jugadores Activos unicamente si esta jugando
                    jugadores.add(con);
                    System.out.println("La cantidad de jugadores hasta el momento es de  " + jugadores.size());
                    if(jugadores.size() == cantidadJugadores) //checamos si ya se alcanzaron la cantidad de jugadores deseados
                    {
                        //Inicializamos las condiciones de juego
                        sopa = new SopaLetras(ManejoArchivos.palabrasR,Tablero.cadenaR);
                        palabrasRestantes = sopa.getPalabras().length; 
                        for(int i = 0 ; i<cantidadJugadores; i++){
                            contadorPalabras[i] = 0;
                        }
                        tiempo.setSegundos(duracionJuego);
                        tiempo.activo =true;
                        for(ConexionServidor conServ: jugadores)
                        {
                            conServ.jugando = true;
                            tiempo.addTimerListener(conServ);
                        }
                        tiempo.start();                        
                    }
                }
                //Checa si ya termino el juego
                if(!tiempo.activo && jugadores.size()==cantidadJugadores)
                {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jugadores = new ArrayList<ConexionServidor>();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void handlePalabraEncontradaEvent(PalabraEncontradaEvent ev) {
        palabrasRestantes--;                
        if(palabrasRestantes==0)
        {
            tiempo.activo=false;
            System.out.println("Han encontrado todas las palabras");
        }
        contadorPalabras[ev.getId()]++;
        System.out.println("El jugador con id " + ev.getId() + "encontro una palabra");
        if(ev.getId() != idJugadorGanador)
        {
            if(contadorPalabras[idJugadorGanador] < contadorPalabras[ev.getId()])
            {
                idJugadorGanador = ev.getId();
            }
        }
        ev.setTiempo(tiempo.getSegundos());
        for(ConexionServidor con : jugadores)
        {            
            if(con.jugando)
            {              
                System.out.println("El id del jugador actual es " + con.getIdGamer());
                System.out.println("El id del jugador que va ganando es  " + idJugadorGanador);
                if(con.getIdGamer() == idJugadorGanador)
                {
                    con.ganador = "Sigue asi!! Vas ganando =D";
                }
                else
                {
                    int aux;
                    aux = contadorPalabras[idJugadorGanador] +1;
                    aux = aux - contadorPalabras[con.getIdGamer()];
                    con.ganador = "Ya casi lo logras!! Estas a " ;                    
                    con.ganador += aux ;
                    con.ganador +=  " palabras de ganar! Apurale!!";
                }
                con.addPalabra(ev);
            }
        }        
    }
    
}
