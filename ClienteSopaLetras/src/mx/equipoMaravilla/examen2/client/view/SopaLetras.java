/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.equipoMaravilla.examen2.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author holaalex2204
 */
public class SopaLetras extends JPanel {
    public SopaLetras(String contenido[], String palabras[])
    {        
        //Inicializa el panel que contiene la sopa de letras
        JPanel sopa = new JPanel();        
        Font fuente = new Font(Font.SANS_SERIF,Font.ITALIC,18);
        sopa.setBackground(Color.white);
        GridLayout grid =new GridLayout(0,15);
        grid.setHgap(10);
        grid.setVgap(10);
        sopa.setLayout(grid);
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
                sopa.add(aux);
            }
        }
        //Inicializa el panel que contiene las palabras a buscar
        JPanel panelPalabras = new JPanel();            
        panelPalabras.setLayout(new GridLayout(0,3));
        for(int i = 0 ; i<palabras.length;i++)
        {            
            JLabel aux = new JLabel(palabras[i],JLabel.LEFT);
            aux.setFont(fuente);
            panelPalabras.add(aux);
        }
        //Inicializa el panel principal que contiene todo
        BorderLayout border = new BorderLayout();        
        setLayout(border);                
        //sopa.setSize(500, 500);
        add(sopa,BorderLayout.CENTER);
        add(panelPalabras,BorderLayout.NORTH);
    }
}
