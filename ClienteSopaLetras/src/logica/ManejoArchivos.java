/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.Scanner;
/**
 *
 * @author ramrodo
 */
public class ManejoArchivos {
    
    
    public static String palabrasR[] = null;
    
    public ManejoArchivos(){
        
        //Tamaño MAX de palabras en el archivo = 20
        String palabrasT[] = new String[20];        
        int x=0, t=0;        
        String strLinea;
        try{
            // Abrimos el archivo
            File f1 = new File("Archivo.txt"); // Creamos un objeto file
            FileInputStream fstream = new FileInputStream(f1.getAbsolutePath());   
            // Creamos el objeto de entrada
            DataInputStream entrada = new DataInputStream(fstream);
            // Creamos el Buffer de Lectura
            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
                
            
            while ((strLinea = buffer.readLine()) != null)   {
                System.out.println(strLinea);
                palabrasT[x]=strLinea;
                x++;
            }
           
            //Formando el array que contiene SOLO las palabras del archivo
            palabrasR = new String[x];
            for(int y=0; y<x; y++)
                palabrasR[y]=palabrasT[y];
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
    }
    
    
}
