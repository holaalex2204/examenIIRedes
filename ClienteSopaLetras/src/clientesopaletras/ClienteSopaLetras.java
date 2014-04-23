/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clientesopaletras;

import java.util.Arrays;
import javax.swing.JFrame;
import mx.equipoMaravilla.examen2.client.view.SopaLetras;
import servidor.Tablero;
import logica.ManejoArchivos;
import logica.ColocarPalabra;
import static servidor.Tablero.cadenaR;
import static logica.ManejoArchivos.palabrasR;
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
    
    public static void main(String[] args) {
        
        
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