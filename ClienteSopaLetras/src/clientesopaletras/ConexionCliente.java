/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientesopaletras;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import protocol.Mensaje;
import protocol.Reconexion;

/**
 *
 * @author holaalex2204
 */
public class ConexionCliente {
    Socket cliente;
    ObjectOutputStream salida;
    ObjectInputStream entrada ;
    public ConexionCliente(String ip, int port) {        
        Mensaje msj = new Mensaje("","");        
        Reconexion rec = new Reconexion(ip,port);                
        try {
            do {
                System.out.println("Inicio de la peticion");
                cliente = new Socket(rec.getIp(),rec.getPort());
                System.out.println("Se ha establecido la conexión");
                salida = new ObjectOutputStream(cliente.getOutputStream());
                System.out.println("Se hac reado fluojo de salida");
                entrada= new ObjectInputStream(cliente.getInputStream());
                System.out.println("se ha crado flujo de entrada");
                System.out.println("Creacion del cliente");
                salida.writeObject(new Mensaje("Peticion", "Quiero jugar!!"));
                salida.flush();
                System.out.println("Envie un objeto");
                
                Object obj = entrada.readObject();
                if(obj instanceof Mensaje) //Se ha aceptado la conexión o se ha avisado de algo en específico
                {
                   msj = (Mensaje)obj; 
                   System.out.println(cliente.getLocalAddress() + ":" + msj.getTipo() + "->" + msj.getContenido());                   
                }
                else if(obj instanceof Reconexion) //No es posible comenzar en ese servidor, de modo que se asigna uno nuevo
                {                    
                   rec = (Reconexion)obj;
                }
            }
            while (msj.getTipo().compareTo("Acepto")!=0);
            salida.writeObject(new Mensaje("Bye","Adios"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
}
