/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidor;

import java.util.ArrayList;

/**
 *
 * @author ramrodo
 */
public class Tablero {

    public static Casilla Matriz [][];
    public static String cadenaR[] = null;
    
   
    public Tablero(int alto, int ancho){
        
        Matriz = new Casilla[alto+2][ancho+2];
        cadenaR = new String[alto];
        //Limpiar el arreglo en cadenas vacías
        for (int x=0 ;x < cadenaR.length; x++)
            cadenaR[x] = "";

        int i,j;
        //Inicializar todo el tablero con tipo Casilla
        for(i=0;i<=(alto+1);i++){
            for(j=0;j<=(ancho+1);j++){
                Matriz[i][j] = new Casilla(0,0,"");
                Matriz[i][j].fila = i;
                Matriz[i][j].col = j;       
                Matriz[i][j].valor = "-";
            }
        }
        
        
        //Llenando de letras del abecedario aleatoriamente
        String caracteres = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        int xAscii;
        int filas = alto, columnas = ancho;
        String car;
        for(i = 0; i < filas; i++){
            for(j = 0; j < columnas; j++){
                xAscii = (int)(Math.random()* caracteres.length());
                car = caracteres.substring(xAscii,xAscii+1);
                //System.out.println(car);
                Matriz[i+1][j+1].valor = car;
                //para colocar matriz en arreglo
                cadenaR[i] += car;
            }
        }
        //Llenando de letras del abecedario aleatoriamente
        
        
        //COMPROBAR
        System.out.println("TABLERO: ");
        for(i=0;i<=(alto+1);i++){
            for(j=0;j<=(ancho+1);j++){          
                System.out.print(Matriz[i][j].getFila() + "," + Matriz[i][j].getCol() + "=" + Matriz[i][j].valor+"\t");
            }
            System.out.println();
        }
        //COMPROBAR
   
}//constructor Tablero
}

