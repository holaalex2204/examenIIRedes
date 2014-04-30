/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clientesopaletras;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JFrame;
import logica.ColocarPalabra;
import logica.ManejoArchivos;
import static logica.ManejoArchivos.palabrasR;
import mx.equipoMaravilla.examen2.client.view.SopaLetras;
import servidor.Conexion;
import servidor.Tablero;
import static servidor.Tablero.cadenaR;

/**
 *
 * @author holaalex2204
 */
public class ClienteSopaLetras {

    /**
     * @param args the command line arguments
     */
    
    static Tablero tab = null;
    static ManejoArchivos archivo = null;
    public static int nfilas;
    public static int ncolumnas;
    static ColocarPalabra colocar = null;
    static Conexion conn = null;
    
    public static void main(String[] args) {
        
        try{
            conn = new Conexion();
            
        Socket cl;
        cl = new Socket("localhost", 6000);
        cl.setSoLinger(true, 1000);
        cl.setReuseAddress(true);
        System.out.println("Conectado");
            
            
        //String inputStreamString = new Scanner(cl.getInputStream(),"UTF-8").useDelimiter("\\A").next();
        //System.out.println(inputStreamString);
        /*ObjectInputStream ois = new ObjectInputStream(cl.getInputStream());
        String c = (String)ois.readObject();
        System.out.println("RECIBIDO: " + c);*/
        System.out.println("RECIBIDO: " + conn.recibir());
        
            //System.out.println("RECIBIDO: " + inputStreamString);
        //System.out.println("RECIBIDO: " + inputStringBuilder.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        //CODIGO RODO***********************************************************
        //System.out.println(System.getProperty("user.dir"));
        ncolumnas = 15;
        nfilas = 15;
        tab = new Tablero(nfilas,ncolumnas);
        
        archivo = new ManejoArchivos();//palabrasR static
        colocar = new ColocarPalabra();
        
        tab.llenarTablero(nfilas,ncolumnas);
        
        System.out.println("SOPA DE LETRAS en el arreglo cadenaR -> " + Arrays.toString(cadenaR));
        //System.out.println("SOPA DE LETRAS en el arreglo cadenaR -> " + Arrays.deepToString(cadenaR));
        //CODIGO RODO***********************************************************
        
        
        //String []contenido = {"acasahjklñqwert","asdfghjklñqwert","asdfgholañqwerp","asdfghjklñqwero","asdfghjklñqwert","asdfgrodoñqwerp","asdfghjklñqwera","asdfghjklñqwerl","asdfghjklñqwert","asdfghjklñqwert","asdfghjklñrwert","asdfghjklñqeert","asdfghjklñqwlrt","asdfghjklñqweot","asdfghjklñqwerj"};
        //String []palabras = {"casa","hola","rodo","laptop","reloj"};
        JFrame a = new JFrame();
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //a.add(new SopaLetras(contenido,palabras));
        a.add(new SopaLetras(cadenaR,palabrasR));
        a.setSize(600, 600);
        a.show();        
                
    }
    
}




//pág. relacionado con control de versiones y Netbeans:
        //https://netbeans.org/kb/74/java/import-eclipse_es.html