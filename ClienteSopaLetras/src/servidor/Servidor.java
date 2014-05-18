/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author holaalex2204
 */
public class Servidor extends Thread{

    ServerSocket servidor;
    int port;
    public Servidor(int port) {
        super();
        try {
            this.port = port;
            System.out.println("Se intenta levantar el servidor en el puerto " + port);
            servidor = new ServerSocket(port);
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
                con.start();
                System.out.println("Estoy continuando en la búsqueda de peticiones");
                
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
