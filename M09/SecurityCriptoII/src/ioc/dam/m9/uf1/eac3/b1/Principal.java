/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m9.uf1.eac3.b1;

import static ioc.dam.m9.uf1.eac3.b1.ServidorEnviador.ClientHibrid;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;



/**
 *
 * @author Usuari
 */

public class Principal{
public static void main(String[] args) {    
    try {
        ServerSocket serverSocket = new ServerSocket();
        InetSocketAddress addr = new InetSocketAddress("localhost", 7777);      

        serverSocket.bind(addr);

        while (true) {        

          System.out.println("Esperant connexions dels clients");
          try (Socket newSocket = serverSocket.accept()) {
              ClientHibrid(newSocket);
          }      
        }
    } catch (IOException e) {      
        e.printStackTrace();
        System.out.println("Problemes de connexió");
    }  
  } 
}


