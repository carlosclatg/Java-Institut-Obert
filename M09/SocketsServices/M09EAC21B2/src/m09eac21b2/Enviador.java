/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m09eac21b2;

import java.io.IOException; 
import java.net.DatagramPacket; 
import java.net.DatagramSocket; 
import java.net.InetAddress; 
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class Enviador {
    
    static int PORT = 9090;
    static String PREGUNTA = "Com et dius?";
    static DatagramPacket packet;
    
    public static void main(String[] args) throws Exception {
        
        InetAddress serverIP = InetAddress.getLocalHost();
        DatagramSocket socket = new DatagramSocket();
 
        byte [] receivedData = new byte[1024];
        byte [] sendingData;
        
        sendingData = getFirstRequest(); //
        packet = new DatagramPacket (sendingData, sendingData.length , serverIP, PORT);
        socket.send(packet); //Enviament del paquet
        System.out.println("Pregunta: " + PREGUNTA);

        //A partir d'aquí resposta
        
        packet = new DatagramPacket(receivedData, 1024);
        socket.receive(packet);

        //Convertim a string amb constructor d'string que permet deco els bytes
        String resposta = new String(
            packet.getData(),
            packet.getOffset(),
            packet.getLength(),
            StandardCharsets.UTF_8 // or some other charset
            );

        System.out.println("Resposta: "  + resposta);
        
    } 

    private static byte[] getFirstRequest() {
        return PREGUNTA.getBytes();
    }
}
