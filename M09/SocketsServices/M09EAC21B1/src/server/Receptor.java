/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader; 
import java.io.DataInputStream;
import java.io.IOException; 
import java.io.InputStream; 
import java.io.InputStreamReader; 
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.InetSocketAddress; 
import java.net.ServerSocket; 
import java.net.Socket;

/**
 *
 * @author carlo
 */
public class Receptor {
    static final int PORT = 9999;
    static Socket socket;
    public static void main(String[] args) throws IOException {
        socket = null;
        ServerSocket server = new ServerSocket(PORT);
        try{
            while(true){ //El server sempre està aixecat
                if(socket == null){
                    System.out.println("Esperant client");
                    socket = server.accept();
                } else {
                    System.out.println("Connexió establesa client - servidor");
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String linea;
                    linea = in.readLine();
                    while (linea != null){
                        System.out.println("" + linea + "");
                        linea = in.readLine();
                    }
                    in.close();
                    socket = null; //Reiniciem socket a null per a que estigui a l'espera
                }
            }            
        } catch (Exception e){
            e.printStackTrace();
        }
          
    }
    
}
