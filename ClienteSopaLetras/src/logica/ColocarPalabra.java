/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;

import java.util.ArrayList;

/**
 *
 * @author ramrodo
 */
public class ColocarPalabra {
    
    static ArrayList<String> MEMORIA=new ArrayList<String>();
    
    public void Abajo(){
    //Método para colocar una palabra hacia Abajo
        
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
    
    
}//class
