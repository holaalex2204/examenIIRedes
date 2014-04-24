/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidor;

/**
 *
 * @author ramrodo
 */
public class Servidor {
    
    static Conexion conn = null;
    /**
     * @param args the command line arguments
     */
static Tablero tab = null;
    
    public static void main(String[] args) {
        ///Tablero tab = new Tablero(5,5,8);
       /*
        Conexion obj = new Conexion();
        System.out.println(obj.recibir().getTipo());
        System.out.println(obj.recibir().getTipo());
        obj.enviar(new Casilla(5,5,"q onda Alex"));
        */
        
        //tab = new Tablero(5,5,5);
        
        //descomentar lo q viene para probar ya cliente-servidor
        
        conn = new Conexion();
        
        
        String str[] = {"MENSAJE ENVIADO"};
        conn.enviar(str);
        //Casilla obj = new Casilla(0,0,"");
        //str = conn.recibir();//Aqui se detiene hasta que el servidor(este) reciba un objeto
        //System.out.println(str);
        
        
        //CLIENTE
        /*
            str = conn.recibir();
            if(str.equals("-")){
                Tablero.destapar(str);
                conn.enviar(new Casilla(-1,-1,"Termine"));
            }
        */
        //CLIENTE    
           
      
    }//main
    
}//class Servidor
