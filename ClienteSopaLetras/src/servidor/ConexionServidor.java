/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import protocol.Mensaje;
import protocol.Reconexion;

/**
 *
 * @author holaalex2204
 */
public class ConexionServidor {

    Socket cliente;
    ObjectInputStream entrada;
    ObjectOutputStream salida;
    boolean edoEspera = true;

    public ConexionServidor(Socket cliente) {
        try {
            this.cliente = cliente;
            System.out.println("Conexión recibida");
            entrada = new ObjectInputStream(cliente.getInputStream());
            System.out.println("Se ha creado Flujo de entrad");
            salida = new ObjectOutputStream(cliente.getOutputStream());
            System.out.println("Se ha creado flujo de salida");
            System.out.println("Creacion del Socket");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void empieza() {
        int aux = 0;
        try {
            Object obj;
            Mensaje m = new Mensaje("", "");
            do {
                obj = entrada.readObject();
                if (obj instanceof Mensaje) {
                    m = (Mensaje) obj;
                    System.out.println(cliente.getLocalAddress() + ":" + m.getTipo() + "->" + m.getContenido());
                    if (m.getTipo().compareTo("Peticion") == 0 && m.getContenido().compareTo("Quiero jugar!!") == 0) {
                        salida.writeObject(new Mensaje("Acepto", "Bienvenido"));
                        salida.flush();
                        System.out.println("Entrando a zona  de espera");
                        while (edoEspera == true) {
                            salida.writeObject(new Mensaje("Espera", aux + " cantidad de jugadores"));
                            salida.flush();
                            System.out.println("Envie un mensaje de espera");
                            aux++;
                            if (aux == 100) {
                                edoEspera = false;
                            }
                        }
                        salida.writeObject(new Mensaje("Informacion", "Sopa de letras"));
                        salida.flush();

                    }
                }
            } while (m.getTipo().compareTo("Bye") != 0);
        } catch (IOException ex) {
            Logger.getLogger(ConexionServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void rechaza() {
        try {
            Object obj;
            Mensaje m = new Mensaje("", "");
            do {
                obj = entrada.readObject();
                if (obj instanceof Mensaje) {
                    m = (Mensaje) obj;
                    System.out.println(cliente.getLocalAddress() + ":" + m.getTipo() + "->" + m.getContenido());
                    if (m.getTipo().compareTo("Peticion") == 0 && m.getContenido().compareTo("Quiero jugar!!") == 0) {
                        salida.writeObject(new Reconexion("localhost", 6000));
                        salida.flush();
                    }
                }
            } while (m.getTipo().compareTo("Bye") != 0);
        } catch (IOException ex) {
            Logger.getLogger(ConexionServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
