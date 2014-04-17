/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clientesopaletras;

import javax.swing.JFrame;
import mx.equipoMaravilla.examen2.view.SopaLetras;

/**
 *
 * @author holaalex2204
 */
public class ClienteSopaLetras {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String []contenido = {"asdfghjklñqwert","asdfghjklñqwert","asdfghjklñqwert","asdfghjklñqwert","asdfghjklñqwert","asdfghjklñqwert","asdfghjklñqwert","asdfghjklñqwert","asdfghjklñqwert","asdfghjklñqwert","asdfghjklñqwert","asdfghjklñqwert","asdfghjklñqwert","asdfghjklñqwert","asdfghjklñqwert"};
        JFrame a = new JFrame();
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        a.add(new SopaLetras(contenido));
        a.setSize(600, 600);
        a.show();
        // TODO code application logic here
    }
    
}
