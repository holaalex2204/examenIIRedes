/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;

import java.util.ArrayList;
import static servidor.Tablero.Matriz;
import static clientesopaletras.ClienteSopaLetras.nfilas;
import static clientesopaletras.ClienteSopaLetras.ncolumnas;
import static logica.ManejoArchivos.palabrasR;
/**
 *
 * @author ramrodo
 */
public class ColocarPalabra {
    
    static ArrayList<String> MEMORIA=new ArrayList<String>();
    //MEMORIA.add(new String(i+","+j));
    private int[][] pos;
    
    public ColocarPalabra(){
    pos = new int[1][2];
}//constructor
    
    public void Abajo(){
    //Método para colocar una palabra hacia Abajo
        xyPalabra();
    }
    
    
    public boolean seSobrepone(int xfila, int xcolumna){
        
        return false;
    }
    
    public void setLetra(int xfila, int xcolumna, char c) {
        
    }
    
    public int[][] xyPalabra() {
        //Min + (int)(Math.random() * ((Max - Min) + 1))
        //private int[][] pos;
        //pos = new int[1][2];
        int[][] auxpos;
        auxpos = new int[1][2];
        
        for (int z=0; z<=palabrasR.length; z++){
            
            int xx = 1 + (int)(Math.random() * ((nfilas - 1) + 1));
            int yy = 1 + (int)(Math.random() * ((ncolumnas - 1) + 1));
            
            int tam = palabrasR[z].length();
            
            /*AQUI FALTA DEPENDE DE CADA MÉTODO
                - ERROR: Argumento en éste método para saber cómo se colocará la palabra
            */
                
            
        }
        
        
        
        
        
        
        
        return auxpos;
    }
    
    
    
    public static boolean esta_en_memoria(int x, int y){
        
        if(MEMORIA.isEmpty()){
            //System.out.println("MEMORIA VACIA");
                return false;
            }
        
        int mi=0;
        for(mi=0; mi<MEMORIA.size(); mi++){
            if(MEMORIA.get(mi).equals(new String(x+","+y)))
                return true;
        }//for
        return false;
    }//esta_en_memoria
    //MEMORIA.add(new String(i+","+j));
    
}//class
