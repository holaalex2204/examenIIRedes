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
    static ArrayList<String> MEMaux=new ArrayList<String>();
    private int[][] pos;
    private int[] arrMetodos={1,2,3,4,5,6,7,8,1,2,3,4,5,6,7};//Arreglo que contiene la orientación. Especificación más abajo.
    //private int[] arrMetodos={1,2,3,4,5,6,7,8,1,1,4,4,8,8,7};//Arreglo que contiene la orientación. Especificación más abajo.
    
    public ColocarPalabra(){
    pos = new int[1][2];
    MEMORIA.clear();
    MEMaux.clear();
    xyPalabra();//LLAMADA PPAL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
    setLetra();
    }//constructor
    
    
    
    
    public boolean seSobrepone(int i, int j, int tam, int forma){
        //MEMORIA.add(new String(i+","+j));
        MEMaux.clear();
        
        if(esta_en_memoria(i,j))
            return true;
        
        MEMaux.add(new String(i+","+j));
        
        for(int k=1; k<tam; k++){
    
            switch(forma){
                //--------------------------------------------------------------
                case 1: //Abajo
                    i++;
                    if(esta_en_memoria(i,j))
                        return true;
                    else
                        MEMaux.add(new String(i+","+j));
                    break;
                //--------------------------------------------------------------  
                case 2: //AbajoDerecha
                    i++;j++;
                    if(esta_en_memoria(i,j))
                        return true;
                    else
                        MEMaux.add(new String(i+","+j));
                    break;
                //--------------------------------------------------------------
                case 3: //AbajoIzquierda
                    i++;j--;
                    if(esta_en_memoria(i,j))
                        return true;
                    else
                        MEMaux.add(new String(i+","+j));
                    break;
                //--------------------------------------------------------------
                case 4: //Arriba
                    i--;
                    if(esta_en_memoria(i,j))
                        return true;
                    else
                        MEMaux.add(new String(i+","+j));
                    break;
                //--------------------------------------------------------------
                case 5: //ArribaDerecha
                    i--;j++;
                    if(esta_en_memoria(i,j))
                        return true;
                    else
                        MEMaux.add(new String(i+","+j));
                    break;
                //--------------------------------------------------------------    
                case 6: //ArribaIzquierda
                    i--;j--;
                    if(esta_en_memoria(i,j))
                        return true;
                    else
                        MEMaux.add(new String(i+","+j));
                    break;
                //--------------------------------------------------------------    
                case 7: //Derecha
                    j++;
                    if(esta_en_memoria(i,j))
                        return true;
                    else
                        MEMaux.add(new String(i+","+j));
                    break;
                //--------------------------------------------------------------    
                case 8: //Izquierda
                    j--;
                    if(esta_en_memoria(i,j))
                        return true;
                    else
                        MEMaux.add(new String(i+","+j));
                    break;
                //--------------------------------------------------------------   
                default:
                    System.out.println("Ocurre algo RARO!!! ningún metodo seleccionado de nuevo");
                    break;
                //--------------------------------------------------------------
            }//switch
            
        }//for
        
        //Si todo fue bien, es decir, no hubo sopreposición de letras...
        //Vaciando MEMaux a MEMORIA
        for(int w=0; w<MEMaux.size(); w++){
            MEMORIA.add(MEMaux.get(w));
        }        
        return false;
    }//seSobrepone
    
    
    
    
    public void setLetra() {
        //char[] charPalabra = palabra.toCharArray();
        //return jletra[xfila][xcolumna].getText().charAt(0);
        int x=0, y=0, e=0;
        for (int a=0; a<palabrasR.length; a++){
            char[] charPalabra = palabrasR[a].toCharArray();
            
            for (int b=0; b<charPalabra.length; b++){
                String coordenadas = MEMORIA.get(e);
                e++;
                String[] numerosComoArray = coordenadas.split(",");
                x = Integer.parseInt(numerosComoArray[0]);
                y = Integer.parseInt(numerosComoArray[1]);
                Matriz[x][y].setValor(Character.toString(charPalabra[b]));
                //System.out.println("----------------------------------------------------------");
                //System.out.println("x y letra " + x + " " + y + " " + charPalabra[b]);
            }
            
            
            
        }//for
        
        
        //COMPROBAR
        System.out.println("TABLERO: ");
        for(int i=0;i<=(15+1);i++){
            for(int j=0;j<=(15+1);j++){          
                System.out.print(Matriz[i][j].getFila() + "," + Matriz[i][j].getCol() + "=" + Matriz[i][j].getValor()+"\t");
            }
            System.out.println();
        }
        //COMPROBAR
        
    }
    
    
    
    public void xyPalabra() {
        //Min + (int)(Math.random() * ((Max - Min) + 1))
        //private int[][] pos;
        //pos = new int[1][2];
        int[][] auxpos;
        auxpos = new int[1][2];
        int xOrientacion = 0;
        
        for (int z=0; z<palabrasR.length; z++){
            //System.out.println("palabrasR.length " + palabrasR.length);
            /*Números aleatorios del 1 al 15, para
                - Posición posible para el tablero; y
                - para forma (orientación).
            */
            
            int xfila = 1 + (int)(Math.random() * ((nfilas - 1) + 1));
            int ycolumna = 1 + (int)(Math.random() * ((ncolumnas - 1) + 1));
            
            int tam = palabrasR[z].length();
            
            /* Valor de la variable xOrientacion
                1. Abajo
                2. AbajoDerecha
                3. AbajoIzquierda
                4. Arriba
                5. ArribaDerecha
                6. ArribaIzquierda
                7. Derecha
                8. Izquierda
            */
            xOrientacion = arrMetodos[ycolumna-1];
            
            switch(xOrientacion){
                //--------------------------------------------------------------
                case 1: //Abajo
                    if( (tam + xfila) < 15 ){
                        if(!seSobrepone(xfila,ycolumna,tam,xOrientacion))
                            System.out.println("EXITO: Coordenadas: " + xfila + "," + ycolumna + "\t" + xOrientacion + "->" + palabrasR[z]);
                        else
                            z--;
                    }else
                        z--;
                    break;
                //--------------------------------------------------------------    
                case 2: //AbajoDerecha
                    if( (tam + ycolumna) < 15 && (tam + xfila) < 15){
                        if(!seSobrepone(xfila,ycolumna,tam,xOrientacion))
                            System.out.println("EXITO: Coordenadas: " + xfila + "," + ycolumna + "\t" + xOrientacion + "->" + palabrasR[z]);
                        else
                            z--;
                    }else
                        z--;
                    break;
                //--------------------------------------------------------------    
                case 3: //AbajoIzquierda
                    if(tam <= ycolumna && (tam + xfila) < 15){
                        if(!seSobrepone(xfila,ycolumna,tam,xOrientacion))
                            System.out.println("EXITO: Coordenadas: " + xfila + "," + ycolumna + "\t" + xOrientacion + "->" + palabrasR[z]);
                        else
                            z--;
                    }else
                        z--;
                    break;
                //--------------------------------------------------------------    
                case 4: //Arriba
                    if(tam <= xfila){
                        if(!seSobrepone(xfila,ycolumna,tam,xOrientacion))
                            System.out.println("EXITO: Coordenadas: " + xfila + "," + ycolumna + "\t" + xOrientacion + "->" + palabrasR[z]);
                        else
                            z--;
                    }else
                        z--;
                    break;
                //--------------------------------------------------------------    
                case 5: //ArribaDerecha
                    if( (tam + ycolumna) < 15 && tam <= xfila){
                        if(!seSobrepone(xfila,ycolumna,tam,xOrientacion))
                            System.out.println("EXITO: Coordenadas: " + xfila + "," + ycolumna + "\t" + xOrientacion + "->" + palabrasR[z]);
                        else
                            z--;
                    }else
                        z--;
                    break;
                //--------------------------------------------------------------    
                case 6: //ArribaIzquierda
                    if(tam <= ycolumna && tam <= xfila){
                        if(!seSobrepone(xfila,ycolumna,tam,xOrientacion))
                            System.out.println("EXITO: Coordenadas: " + xfila + "," + ycolumna + "\t" + xOrientacion + "->" + palabrasR[z]);
                        else
                            z--;
                    }else
                        z--;
                    break;
                //--------------------------------------------------------------    
                case 7: //Derecha
                    if( (tam + ycolumna) < 15 ){
                        if(!seSobrepone(xfila,ycolumna,tam,xOrientacion))
                            System.out.println("EXITO: Coordenadas: " + xfila + "," + ycolumna + "\t" + xOrientacion + "->" + palabrasR[z]);
                        else
                            z--;
                    }else
                        z--;
                    break;
                //--------------------------------------------------------------    
                case 8: //Izquierda
                    if(tam <= ycolumna){
                        if(!seSobrepone(xfila,ycolumna,tam,xOrientacion))
                            System.out.println("EXITO: Coordenadas: " + xfila + "," + ycolumna + "\t" + xOrientacion + "->" + palabrasR[z]);
                        else
                            z--;
                    }else
                        z--;
                    break;
                //--------------------------------------------------------------    
                default:
                    System.out.println("ALGO RARO PASA... no selecciona ningún método entre el 1 y 8");
                    System.out.println("MAL: Coordenadas: " + xfila + "," + ycolumna + "\t" + xOrientacion + "->" + palabrasR[z]);
                    break;
            }//switch

        }//for
        
        //System.out.println("MEMORIA FINAL (Contiene las palabras, coordenadas -> " + MEMORIA);
        
    }//xyPalabra()
    
    
    
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
