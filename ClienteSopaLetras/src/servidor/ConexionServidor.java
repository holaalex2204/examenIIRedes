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
import java.util.logging.Level;
import java.util.logging.Logger;
import protocol.Mensaje;
import protocol.Reconexion;
import protocol.SopaLetras;

/**
 *
 * @author holaalex2204
 */
public class ConexionServidor {

    Socket cliente;
    ObjectInputStream entrada;
    ObjectOutputStream salida;
    public static boolean edoEspera = true;

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
                    while (edoEspera == true) {                        
                        aux++;
                        if (aux == 10000) {
                            edoEspera = false;
                        }
                    }
                    //por alguna razon ya se tiene lista la sopa de letras y ya se puede comenzar a enviarla
                    escribe(new Mensaje("Informacion", "Sopa de letras"));
                    //Se supondra que ya se tiene una sopa de letras y para efectos practicos se deja una estática (desupés se hace el cambio)
                    String []contenido = {"acasahjklñqwert","asdfghjklñqwert","asdfgholañqwerp","asdfghjklñqwero","asdfghjklñqwert","asdfgrodoñqwerp","asdfghjklñqwera","asdfghjklñqwerl","asdfghjklñqwert","asdfghjklñqwert","asdfghjklñrwert","asdfghjklñqeert","asdfghjklñqwlrt","asdfghjklñqweot","asdfghjklñqwerj"};
                    String []palabras = {"casa","hola","rodo","laptop","reloj"};
                    escribe(new SopaLetras(palabras,contenido));
                }
            }
        } while (m.getTipo().compareTo("Bye") != 0);
    }

    public void rechaza() {
        Object obj;
        Mensaje m = new Mensaje("", "");
        do {
            obj = lee();
            if (obj instanceof Mensaje) {
                m = (Mensaje) obj;                
                if (m.getTipo().compareTo("Peticion") == 0 && m.getContenido().compareTo("Quiero jugar!!") == 0) {
                    escribe(new Reconexion("localhost", 6000));
                }
            }
        } while (m.getTipo().compareTo("Bye") != 0);
    }
    public void escribe(Object obj)
    {
        try {            
            salida.write(1);
            salida.writeObject(obj);
            salida.flush();
            System.out.println("Servidor : " + cliente.getLocalAddress() + ":" + obj.toString());
        } catch (IOException ex) {
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Object lee()
    {
        try {
            //Se supone que el metodo read funcionara para bloquear el socket hasta que halla un bit que leer
            entrada.read();
            //Se manda un bit antes de cada dato para entonces indicar que a continuación viene el objeto que realmente interesa
            Object a = entrada.readObject();
            System.out.println(cliente.getLocalAddress() + ":"+a.toString());
            return a;
        } catch (IOException ex) {
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
