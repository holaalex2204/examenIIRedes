/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.equipoMaravilla.examen2.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author holaalex2204
 */
public class SopaLetras extends JPanel {
    public SopaLetras(String contenido[])
    {
        Font fuente = new Font(Font.SANS_SERIF,Font.ITALIC,18);
        setBackground(Color.white);
        GridLayout grid =new GridLayout(0,15);
        grid.setHgap(10);
        grid.setVgap(10);
        setLayout(grid);
        if(contenido.length!=15)    return;
        for(int fila = 0 ; fila<15;fila++)
        {
            if(contenido[fila].length()!=15)    return;
            for(int col = 0 ; col<15;col++)
            {
                JLabel aux = new JLabel((contenido[fila].charAt(col)+" "),JLabel.CENTER );
                aux.setBackground(new Color(80,189,254));
                aux.setOpaque(true);
                aux.setFont(fuente);
                add(aux);
            }
        }
    }
}
