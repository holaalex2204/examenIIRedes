/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author ramrodo
 */
public class Conexion {
    
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private ServerSocket ss;
    private Socket cl;
    
    public Conexion() {    
        try {
            ss = new ServerSocket(6000);
            //cl = new Socket("localhost", 5000);
            //cl = ss.accept();  
            //System.out.println(cl.getInetAddress());
            //oos = new ObjectOutputStream(cl.getOutputStream());
            //ois = new ObjectInputStream(cl.getInputStream());  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void enviar(String str[]){
        try{
            System.out.println("Esperando solicitud para enviar...");
            cl = ss.accept();  
            System.out.println("Solicitud de aceptacion...");
            System.out.println(cl.getInetAddress());
            oos = new ObjectOutputStream(cl.getOutputStream()); 
            oos.writeObject(str);
            System.out.println("SE ENVIÓ... ");
            System.out.print(str);
            oos.flush(); 
            oos.close();
            cl.close();
        }
        catch (Exception ev){
            ev.printStackTrace();
        }
    }//enviar
    
    public String[] recibir(){
        String str[] = null;
        try{
            cl = ss.accept();  
            System.out.println(cl.getInetAddress());
            ois = new ObjectInputStream(cl.getInputStream()); 
            str = (String [])ois.readObject();  
            ois.close();
            cl.close();
        }
        catch (Exception ev){
            ev.printStackTrace();
        }
        
        return str;
    }//recibir
    
}
