/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientesopaletras;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import mx.equipoMaravilla.examen2.client.event.PalabraEncontradaEvent;
import mx.equipoMaravilla.examen2.client.event.PalabraEncontradaListener;
import mx.equipoMaravilla.examen2.client.view.SopaLetrasView;
import protocol.Mensaje;
import protocol.Reconexion;
import protocol.SopaLetras;
import protocol.Tiempo;

/**
 *
 * @author holaalex2204
 */
public class ConexionCliente implements PalabraEncontradaListener{

    Socket cliente;
    ObjectOutputStream salida;
    ObjectInputStream entrada;
    PalabraEncontradaListener listener;
    ArrayList<PalabraEncontradaEvent> palabras;
    final static int nfilas = 15;
    final static int ncolumnas = 15;
    public ConexionCliente(String ip, int port) {
        Mensaje msj = new Mensaje("", "");
        Reconexion rec = new Reconexion(ip, port);
        try {
            do {
                System.out.println("Inicio de la peticion");
                cliente = new Socket(rec.getIp(), rec.getPort());
                System.out.println("Se ha establecido la conexión");
                salida = new ObjectOutputStream(cliente.getOutputStream());
                System.out.println("Se hac reado fluojo de salida");
                System.out.println(cliente.getPort());
                entrada = new ObjectInputStream(cliente.getInputStream());
                System.out.println("se ha crado flujo de entrada");
                System.out.println("Creacion del cliente");
                escribe(new Mensaje("Peticion", "Quiero jugar!!"));
                System.out.println("Envie un objeto");
                Object obj = lee();
                if (obj instanceof Mensaje) //Se ha aceptado la conexión o se ha avisado de algo en específico
                {
                    msj = (Mensaje) obj;
                } else if (obj instanceof Reconexion) //No es posible comenzar en ese servidor, de modo que se asigna uno nuevo
                {
                    rec = (Reconexion) obj;
                    escribe(new Mensaje("Bye", "Adios"));
                    entrada.close();
                    salida.close();
                    cliente.close();
                }
            } while (msj.getTipo().compareTo("Acepto") != 0);
            palabras = new ArrayList<PalabraEncontradaEvent>();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void juega() {
        //Primero esperaremos hasta que el servidor nos diga que dejemos de esperar
        //El servidor estará enviando constantemente mensajes de espera hasta que ya se pueda comenzar a jugar
        Mensaje msj = new Mensaje("", "");
        SopaLetras sopa = null;
        SopaLetrasView vista;
        try {
            do {
                msj = (Mensaje) lee();
            } while (msj.getTipo().compareTo("Espera") == 0);
            if (msj.getTipo().equals("Informacion") && msj.getContenido().compareTo("Sopa de letras") == 0) {
                System.out.println("Estoy listo para recibir la sopa de letras");
                sopa = (SopaLetras) lee();
                JFrame a = new JFrame();    //Crea unframe para mostrar la sopa de letras
                a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //crea una sopa de letras con las palabras deseadas
                vista  =new SopaLetrasView(sopa.getContenido(), sopa.getPalabras()) ;
                //le pedimos a vista que nos avise cada vez que el jugador encuentre una palabra
                vista.addListeners(this);
                a.add(vista);
                a.setSize(600, 600);//Define un tamaño al contendor de la sopa de letras
                System.out.println("Estoy listo para que empieze el juego");
                msj = (Mensaje) lee();
                if (msj.getTipo().compareTo("Comencemos!!") == 0) {
                    a.show(); //muestra la sopa de letras   
                    do {
                        Thread.sleep(1000);
                        while (palabras.size() > 0) {
                            escribe(new Mensaje("Encontre palabra", ""));
                            escribe(palabras.get(0));
                            palabras.remove(0);
                        }
                        escribe(new Mensaje("Turno Escritura Servidor", ""));
                        do
                        {                            
                            msj = (Mensaje) lee();
                            if(msj.getTipo().compareTo("Palabra encontrada")==0)
                            {
                                vista.handlePalabraEncontradaEvent(((PalabraEncontradaEvent)lee()));
                                vista.setMensajeGanador(((Mensaje) lee()).getContenido());
                            }
                            else if(msj.getTipo().compareTo("Tiempo")==0)
                            {
                                vista.setTiempo((((Tiempo)lee()).getSegundos()));
                            }
                            else
                            {
                                
                            }
                         
                        }while(msj.getTipo().compareTo("Turno Escritura Cliente")!=0 && msj.getTipo().compareTo("Fin del juego")!=0);
                    } while (msj.getTipo().compareTo("Fin del juego") != 0);
                    despedir();
                }
            }
        } catch (Exception ev) {
            ev.printStackTrace();
        }

    }

    public void despedir() {
        escribe(new Mensaje("Bye", "Adios"));
    }

    public void escribe(Object obj) {
        try {
            salida.write(1);
            salida.writeObject(obj);
            salida.flush();
            System.out.println("Cliente : " + cliente.getLocalAddress() + ":" + obj.toString());
        } catch (IOException ex) {
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Object lee() {
        try {
            //Se supone que el metodo read funcionara para bloquear el socket hasta que halla un bit que leer
            entrada.read();
            //Se manda un bit antes de cada dato para entonces indicar que a continuación viene el objeto que realmente interesa
            Object a = entrada.readObject();
            System.out.println("Servidor" + ":" + a.toString());
            return a;
        } catch (IOException ex) {
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public PalabraEncontradaListener getListener() {
        return listener;
    }

    public void setListener(PalabraEncontradaListener listener) {
        this.listener = listener;
    }

    @Override
    public void handlePalabraEncontradaEvent(PalabraEncontradaEvent ev) {
        //En este metodo se reciben los avisos que la vista genera cada vez que el cliente encuentra una nueva palabra
        System.out.println("La vista me ha avisado que ha encontrado esta palabra");
        //Agrega la palabra a la lista de palabras encontradas que posteriormente se enviaran al servidor 
        palabras.add(ev);        
    }

    private void elseif(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
