/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clientesopaletras;

import javax.swing.JFrame;
import mx.equipoMaravilla.examen2.client.view.SopaLetras;
import servidor.Tablero;
import logica.ManejoArchivos;

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
    
    public static void main(String[] args) {
        String []contenido = {"asdfghjklñqwert","asdfghjklñqwert","asdfghjklñqwert","asdfghjklñqwert","asdfghjklñqwert","asdfghjklñqwert","asdfghjklñqwert","asdfghjklñqwert","asdfghjklñqwert","asdfghjklñqwert","asdfghjklñqwert","asdfghjklñqwert","asdfghjklñqwert","asdfghjklñqwert","asdfghjklñqwert"};
        String []palabras = {"hola","holas","hola","holas"};
        JFrame a = new JFrame();
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        a.add(new SopaLetras(contenido,palabras));
        a.setSize(600, 600);
        a.show();        
                
        //CODIGO RODO
        int filas=10;
        int columnas=10;
        tab = new Tablero(filas,columnas);
        System.out.println(System.getProperty("user.dir"));
        archivo = new ManejoArchivos();
        //CODIGO RODO
    }
    
}
