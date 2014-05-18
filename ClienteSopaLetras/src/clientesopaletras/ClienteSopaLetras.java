/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientesopaletras;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import logica.ColocarPalabra;
import logica.ManejoArchivos;
import static logica.ManejoArchivos.palabrasR;
import mx.equipoMaravilla.examen2.client.view.SopaLetrasView;
import protocol.Mensaje;
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
        ConexionCliente conCliente = new ConexionCliente("localhost", 6000);
        conCliente.juega();
        //conCliente.despedir();
        //CODIGO RODO***********************************************************
        //System.out.println(System.getProperty("user.dir"));
        /*
         ncolumnas = 15;
         nfilas = 15;
         tab = new Tablero(nfilas, ncolumnas);

         archivo = new ManejoArchivos();//palabrasR static
         colocar = new ColocarPalabra();

         tab.llenarTablero(nfilas, ncolumnas);

         System.out.println("SOPA DE LETRAS en el arreglo cadenaR -> " + Arrays.toString(cadenaR));
         //System.out.println("SOPA DE LETRAS en el arreglo cadenaR -> " + Arrays.deepToString(cadenaR));
         //CODIGO RODO***********************************************************

         JFrame a = new JFrame();    //Crea unframe para mostrar la sopa de letras
         a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         a.add(new SopaLetrasView(cadenaR, palabrasR));//crea una sopa de letras con las palabras deseadas
         a.setSize(600, 600);//Define un tamaño al contendor de la sopa de letras
         a.show(); //muestra la sopa de letras
         */
    }

}

//pág. relacionado con control de versiones y Netbeans:
        //https://netbeans.org/kb/74/java/import-eclipse_es.html
