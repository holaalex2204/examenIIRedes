/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import clientesopaletras.ClienteSopaLetras;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import protocol.Mensaje;

/**
 *
 * @author ramrodo
 */
public class Servidor {

    static Conexion conn = null;
    /**
     * @param args the command line arguments
     */
    static Tablero tab = null;

    public static void main(String[] args) {
        try {
            System.out.println("Se intenta levantar el servidor");
            ServerSocket servidor = new ServerSocket(6000);
            System.out.println("Esperando Petición");
            Socket cliente = servidor.accept();
            System.out.println("Conexión recibida");
            ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
            System.out.println("Se ha creado Flujo de entrad");
            ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream());
            System.out.println("Se ha creado flujo de salida");
            System.out.println("Creacion del Socket");
            Object obj = entrada.readObject();
            if (obj instanceof Mensaje) {
                Mensaje m = (Mensaje) obj;
                System.out.println("Hemos recibido un mensaje!!");
                System.out.println(cliente.getLocalAddress() + ":" + m.getTipo() + "->" + m.getContenido());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    /*ServerSocket server = null;
     Socket client = null;
     BufferedReader in = null;
     PrintWriter out = null;
     String line;
     try {
     server = new ServerSocket(6000);
     } catch (IOException e) {
     System.out.println("Could not listen on port 6000");
     System.exit(-1);
     }
     System.out.println("Estoy por empezar el cliente");
     try {
     client = server.accept();
     } catch (IOException e) {
     System.out.println("Accept failed: 4321");
     System.exit(-1);
     }
     System.out.println("Termine de aceptar la conexión");
     try {
     in = new BufferedReader(new InputStreamReader(
     client.getInputStream()));
     out = new PrintWriter(client.getOutputStream(),
     true);
     } catch (IOException e) {
     System.out.println("Read failed");
     System.exit(-1);
     }
     System.out.println("Termine de asociar los lectores y escritores");
     while (true) {
     try {
     line = in.readLine();
     //Send data back to client
     System.out.println(line);
     } catch (IOException e) {
     System.out.println("Read failed");
     System.exit(-1);
     }
     }
     }

     ///Tablero tab = new Tablero(5,5,8);
     /*
     Conexion obj = new Conexion();
     System.out.println(obj.recibir().getTipo());
     System.out.println(obj.recibir().getTipo());
     obj.enviar(new Casilla(5,5,"q onda Alex"));
     */
        //tab = new Tablero(5,5,5);
    //descomentar lo q viene para probar ya cliente-servidor
        /*
     conn = new Conexion();
               
     String str[] = {"MENSAJE ENVIADO"};
     conn.enviar(str);
     //Casilla obj = new Casilla(0,0,"");
     //str = conn.recibir();//Aqui se detiene hasta que el servidor(este) reciba un objeto
     //System.out.println(str);
        
        
     //CLIENTE
     /*
     str = conn.recibir();
     if(str.equals("-")){
     Tablero.destapar(str);
     conn.enviar(new Casilla(-1,-1,"Termine"));
     }
     */
    //CLIENTE    
}//main
