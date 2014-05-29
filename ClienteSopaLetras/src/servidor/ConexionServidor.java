/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import clientesopaletras.ConexionCliente;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.equipoMaravilla.examen2.client.event.PalabraEncontradaEvent;
import mx.equipoMaravilla.examen2.client.event.PalabraEncontradaListener;
import protocol.Mensaje;
import protocol.Reconexion;
import protocol.SopaLetras;

/**
 *
 * @author holaalex2204
 */
public class ConexionServidor extends Thread implements Runnable {

    Socket cliente;
    ObjectInputStream entrada;
    ObjectOutputStream salida;
    public static boolean edoEspera = true;
    public boolean aceptar;
    public boolean jugando;
    private PalabraEncontradaListener listener;
    ArrayList<PalabraEncontradaEvent> palabras;
    private SopaLetras sopa;
    private int portNext;
    /**
     *
     * @param cliente
     */
    
    ConexionServidor(Socket cliente, boolean b, SopaLetras sopa, int portNext) {
        super();
        try {
            this.cliente = cliente;
            System.out.println("Conexión recibida");
            entrada = new ObjectInputStream(cliente.getInputStream());
            System.out.println("Se ha creado Flujo de entrad");
            salida = new ObjectOutputStream(cliente.getOutputStream());
            System.out.println("Se ha creado flujo de salida");
            System.out.println("Creacion del Socket");
            this.aceptar = b;
            this.sopa = sopa;
            jugando = false;
            palabras = new ArrayList<PalabraEncontradaEvent>();
            this.portNext = portNext+1;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void empieza() {
        int aux = 0;
        Object obj;
        Mensaje m = new Mensaje("", "");
        do {
            obj = lee();
            if (obj instanceof Mensaje) {
                m = (Mensaje) obj;
                if (m.getTipo().compareTo("Peticion") == 0 && m.getContenido().compareTo("Quiero jugar!!") == 0) {
                    escribe(new Mensaje("Acepto", "Bienvenido"));
                    //Comienza el periodo de espera
                    escribe(new Mensaje("Espera", " cantidad de jugadores"));
                    //por alguna razon ya se tiene lista la sopa de letras y ya se puede comenzar a enviarla
                    escribe(new Mensaje("Informacion", "Sopa de letras"));
                    //Se supondra que ya se tiene una sopa de letras y para efectos practicos se deja una estática (desupés se hace el cambio)                    
                    escribe(sopa);
                    //este periodo de espera será hasta que haya pocos jugadores
                    while (edoEspera == true) {
                        aux++;
                        if (aux == 10000) {
                            edoEspera = false;
                        }
                    }
                    escribe(new Mensaje("Comencemos!!", "Ahora"));
                    jugando = true;
                    do {
                        do {
                            m = (Mensaje) lee();
                            if (m.getTipo().compareTo("Encontre palabra") == 0) {
                                //avisa al servidor que el cliente al que atiene encontro una palabra
                                listener.handlePalabraEncontradaEvent(((PalabraEncontradaEvent) lee()));
                            }
                        } while ((m.getTipo().compareTo("Turno Escritura Servidor") != 0));
                        while (palabras.size() > 0) {
                            escribe(new Mensaje("Palabra encontrada", "nueva palabra"));
                            escribe(palabras.get(0));
                            palabras.remove(0);
                        }
                        escribe(new Mensaje("Turno Escritura Cliente", "Turno de escribir del cliente"));

                    } while (jugando && m.getTipo().compareTo("Bye") != 0);
                    if (m.getTipo().compareTo("Bye") != 0) {

                    }
                }
            }
        } while (m.getTipo().compareTo("Bye") != 0);
        jugando = false;
    }

    public void rechaza() {
        Object obj;
        Mensaje m = new Mensaje("", "");
        do {
            obj = lee();
            if (obj instanceof Mensaje) {
                m = (Mensaje) obj;
                if (m.getTipo().compareTo("Peticion") == 0 && m.getContenido().compareTo("Quiero jugar!!") == 0) {
                    escribe(new Reconexion("localhost", portNext));
                }
            }
        } while (m.getTipo().compareTo("Bye") != 0);
    }
    public void escribe(Object obj) {
        try {
            salida.write(1);
            salida.writeObject(obj);
            salida.flush();
            System.out.println("Servidor : " + cliente.getLocalAddress() + ":" + obj.toString());
        } catch (IOException ex) {
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Object lee() {
        try {
            //Se supone que el metodo read funcionara para bloquear el socket hasta que halla un bit que leer
            System.out.println("Esperando Bit de lectura para desbloquear");
            entrada.read();
            System.out.println("Esperando Objeto en el canal de lectura");
            //Se manda un bit antes de cada dato para entonces indicar que a continuación viene el objeto que realmente interesa
            Object a = entrada.readObject();
            System.out.println(cliente.getLocalAddress() + ":" + a.toString());
            return a;
        } catch (IOException ex) {
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void run() {
        try {
            System.out.println("Acabo de recibir a un amiguito!!");
            if (aceptar) {
                empieza();
            } else {
                rechaza();
            }
            salida.close();
            entrada.close();
            cliente.close();
        } catch (IOException ex) {
            Logger.getLogger(ConexionServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PalabraEncontradaListener getListener() {
        return listener;
    }

    public void setListener(PalabraEncontradaListener listener) {
        this.listener = listener;
    }

    private void encontrePalabra(PalabraEncontradaEvent a) {
        listener.handlePalabraEncontradaEvent(a);
    }

    public void addPalabra(PalabraEncontradaEvent palabra) {
        palabras.add(palabra);

    }

}
