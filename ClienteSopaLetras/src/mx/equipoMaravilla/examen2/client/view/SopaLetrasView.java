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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;
import mx.equipoMaravilla.examen2.client.event.PalabraEncontradaEvent;
import mx.equipoMaravilla.examen2.client.event.PalabraEncontradaListener;

/**
 *
 * @author holaalex2204
 */
public class SopaLetrasView extends JPanel implements mx.equipoMaravilla.examen2.client.event.PalabraEncontradaListener{
    private String palabra = new String();
    private int encontrados[];
    private String palabras[];
    private JLabel[][] sopita;     
    private JLabel tiempo;
    private JLabel mensajeGanador;
    private ArrayList<JLabel> etiquetasPalabras;
    ArrayList<PalabraEncontradaListener> listeners = new ArrayList<PalabraEncontradaListener>();
    public SopaLetrasView(String contenido[], String palabras[]) {
        //Inicializa el panel que contiene la sopa de letras
        JPanel sopa = new JPanel();
        Font fuenteTitulo = new Font(Font.SANS_SERIF, Font.BOLD, 22);
        Font fuente = new Font(Font.SANS_SERIF, Font.ITALIC, 18);
        sopa.setBackground(Color.white);
        GridLayout grid = new GridLayout(0, 15);
        grid.setHgap(15);
        grid.setVgap(15);
        sopa.setLayout(grid);
        etiquetasPalabras= new ArrayList<JLabel>();
        sopita = new JLabel[15][15];
        if (contenido.length != 15) {
            return;
        }
        for (int fila = 0; fila < 15; fila++) {
            if (contenido[fila].length() != 15) {
                return;
            }
            for (int col = 0; col < 15; col++) {
                sopita[fila][col] = new JLabel((contenido[fila].charAt(col) + " "), JLabel.CENTER);
                sopita[fila][col].setBackground(new Color(80, 189, 254));
                sopita[fila][col].setOpaque(true);
                sopita[fila][col].setFont(fuente);
                sopita[fila][col].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        
                    }
                    @Override
                    public void mousePressed(MouseEvent e) {                        
                        ((JLabel)e.getComponent()).setBackground(Color.red);
                        palabra = ((JLabel) e.getSource()).getText();                        
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        verifica();
                    }
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        if (e.getModifiers() == MouseEvent.BUTTON1_MASK ){//&& ((JLabel)e.getComponent()).getBackground()!=Color.red )  {                            
                            ((JLabel)e.getComponent()).setBackground(Color.red);
                            palabra = palabra + ((JLabel) e.getSource()).getText();                            
                        }
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        //System.out.print(((JLabel) e.getSource()).getText());
                    }
                });
                sopa.add(sopita[fila][col]);                
            }
        }
        //Inicializa el panel que contiene las palabras a buscar
        JPanel panelPalabras = new JPanel();
        panelPalabras.setLayout(new GridLayout(0, 3));
        JLabel titulo = new JLabel("SOPA LETRAS", JLabel.CENTER);
        titulo.setFont(fuenteTitulo);        
        panelPalabras.add(new JLabel());
        panelPalabras.add(titulo);
        panelPalabras.add(new JLabel());
        for (int i = 0; i < palabras.length; i++) {
            JLabel aux = new JLabel(palabras[i], JLabel.LEFT);
            aux.setFont(fuente);
            aux.setBackground(Color.white);
            aux.setOpaque(true);
            etiquetasPalabras.add(aux);
            panelPalabras.add(etiquetasPalabras.get(i));
        }
        for (int i = 0; i < (3 - palabras.length % 3); i++) {
            JLabel aux = new JLabel();
            aux.setFont(fuente);
            aux.setBackground(Color.white);
            aux.setOpaque(true);
            panelPalabras.add(aux);
        }        
        //Inicializa el panel que contiene tiempo y mensaje ganador
        JPanel panelInformacion = new JPanel();
        tiempo = new JLabel();
        mensajeGanador  = new JLabel();
        panelInformacion.add(tiempo);
        panelInformacion.add(mensajeGanador);
        //Inicializa el panel principal que contiene todo
        BorderLayout border = new BorderLayout();
        setLayout(border);
        //sopa.setSize(500, 500);
        add(sopa, BorderLayout.CENTER);
        add(panelPalabras, BorderLayout.NORTH);
        add(panelInformacion,BorderLayout.SOUTH);
        
        encontrados = new int[palabras.length];
        this.palabras = palabras;
    }
    private void verifica()
    {
        float filas = 0 ;
        float cols = 0;
        int f=-1;
        int c=-1;
        palabra  = palabra.replace(" ", "");
        System.out.println(palabra);
        for(int i = 0 ; i<palabras.length;i++)
        {
            if((palabra.compareTo(palabras[i])==0 && encontrados[i]==0) |(new StringBuilder(palabra).reverse().toString().compareTo(palabras[i])==0 && encontrados[i]==0))
            {                
                for(int fila =0; fila<15; fila++)
                {
                    for(int col = 0 ; col<15; col++)
                    {                                             
                        if(sopita[fila][col].getBackground()== Color.red)
                        {                 
                            if(f==-1)   f= fila;
                            if(c==-1)   c= col;
                            filas = filas + fila;
                            cols = cols + col;                           
                        }
                    }
                }                
                filas = filas/palabra.length();
                cols = cols/palabra.length();
                filas = filas +1;
                cols = cols+1;
                f++;
                c++;      
                if(!(((filas == f  && (cols-c)==((float)palabra.length()-1)/2)| (cols==c && (filas-f)==((float)palabra.length()-1)/2))|(Math.abs(filas-f))==Math.abs(cols-c)))
                {
                    break;
                }
                int filaInicial = 16;
                int filaFinal =16;
                int colInicial = 16;
                int colFinal = -1;
                for(int fila =0; fila<15; fila++)
                {
                    for(int col = 0 ; col<15; col++)
                    {                                             
                        if(sopita[fila][col].getBackground()== Color.red)
                        {                 
                            sopita[fila][col].setBackground(Color.green);
                            if(colInicial>col)
                            {
                                filaInicial = fila;
                                colInicial = col;
                            }
                            if(colFinal<=col)
                            {
                                colFinal = col;
                                filaFinal = fila;
                            }
                        }
                    }
                }    
                System.out.println("Encontraste una palabra!");
                System.out.println(filaInicial);
                System.out.println(filaFinal);
                System.out.println(colFinal);
                System.out.println(colInicial);
                notificaPalabraEncontrada(new PalabraEncontradaEvent(filaInicial, filaFinal, colInicial, colFinal,palabras[i], this));
                //AQUI TIENES QUE AVISAR QUE YA ENCONTRO UNA PALABRA
                return;
            }
        }
        for(int fila =0; fila<15; fila++)
        {
            for (int col = 0; col < 15; col++) {
                if (sopita[fila][col].getBackground() == Color.red) {
                    sopita[fila][col].setBackground(new Color(80, 189, 254));
                }
            }
        }
        palabra = new String();
    }
    private void notificaPalabraEncontrada(PalabraEncontradaEvent ev)
    {
        for(PalabraEncontradaListener lis : listeners)
        {
            lis.handlePalabraEncontradaEvent(ev);
        }
    }
    public ArrayList<PalabraEncontradaListener> getListeners() {
        return listeners;
    }

    public void addListeners(PalabraEncontradaListener listeners) {
        this.listeners.add(listeners);
    }

    @Override
    public void handlePalabraEncontradaEvent(PalabraEncontradaEvent ev) {
        Random r = new Random();
        System.out.println("El servidor ha terminado de reportar a la vista de la palabra "+ ev.getPalabra());
        Color colorAleatorio ;
        colorAleatorio = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));        
        do{ 
            System.out.println(((colorAleatorio.getRed()+colorAleatorio.getBlue()+colorAleatorio.getGreen())/3));
            colorAleatorio = colorAleatorio.brighter();            
        }while(((colorAleatorio.getRed()+colorAleatorio.getBlue()+colorAleatorio.getGreen())/3) <= 140);
        int difFilas = Math.round(((float)(ev.getFilaFin() - ev.getFilaInicio()))/ev.getPalabra().length());
        int difCols = Math.round(((float)(ev.getColFinal() - ev.getColInicio()))/ev.getPalabra().length());
        int fila, col;
        System.out.println(ev.getPalabra().length());
        System.out.println(ev.getColFinal());
        System.out.println(ev.getColInicio());
        System.out.println(ev.getFilaFin());
        System.out.println(ev.getFilaInicio());
        System.out.println("La diferencia de columnas es " + difCols);
        System.out.println("La diferencia de FILAS es " + difFilas);
        //Colorea en la sopa de letras la palabra encontrada si es que no ha sido coloreada
        for(int i = 0 ; i<ev.getPalabra().length() ; i++)
        {            
            fila = (ev.getFilaInicio()+i*difFilas);
            col  = (ev.getColInicio()+i*difCols);
            System.out.println("Coloreando la celda " + fila + ","+ col);            
            sopita[fila][col].setBackground(colorAleatorio);
        }        
        for(JLabel etiq : etiquetasPalabras)
        {
            if(etiq.getText().compareTo(ev.getPalabra())==0)
            {
                etiq.setBackground(colorAleatorio);
            }
        }    
    }    
    public void setTiempo(int tiempo) {
        this.tiempo.setText(tiempo+" segundos restantes.");
        System.out.println("El tiempo restante es de " + tiempo);
    }
    public void setMensajeGanador(String msj)
    {
        mensajeGanador.setText(msj);
    }
}
