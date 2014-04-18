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

/**
 *
 * @author ramrodo
 */
public class ManejoArchivos {
    
    
    public ManejoArchivos(){
        //String []Palabras = {""};
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
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        //return Palabras;
    }
    
    
}
