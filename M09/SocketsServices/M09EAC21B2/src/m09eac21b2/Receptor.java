package m09eac21b2;

 /* Donada una url i una etiqueta, mostra les línies de url on apareix l'etiqueta
 *  @params args: url tag
 */

import java.io.IOException; 
import java.net.DatagramPacket; 
import java.net.DatagramSocket; 
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;


public class Receptor  {
    static DatagramSocket socket;
    static int PORT = 9090;
    static String PREGUNTA = "Com et dius?";
    static String RESPOSTA = "Em dic Carlos";
    static String RESPOSTANULL = "No entenc";

    public static void main(String[] args) throws IOException{
        
        byte [] receivingData = new byte[1024];
        byte [] sendingData;
        InetAddress clientIP;
        int clientPort;
        socket = new DatagramSocket(PORT);

        //el servidor atén el port indefinidament
        while(true){
            //creació del paquet per rebre les dades
            DatagramPacket packet = new DatagramPacket(receivingData, 1024);
            //espera de les dades que es guarden a packet (segons API)
            socket.receive(packet);
            //processament de les dades rebudes i obtenció de la resposta
            sendingData = processData(packet);
            //obtenció de l'adreça del client
            clientIP = packet.getAddress();
            //obtenció del port del client
            clientPort = packet.getPort();
            //creació del paquet per enviar la resposta
            packet = new DatagramPacket(sendingData, sendingData.length, clientIP, clientPort);
            //enviament de la resposta
            socket.send(packet);
        }
    }

    private static byte[] processData(DatagramPacket data) {
        
        String pregunta = new String( //Constructor String API JAva
            data.getData(),
            data.getOffset(),
            data.getLength(),
            StandardCharsets.UTF_8 
            );
        System.out.println("He rebut la següent pregunta: " + pregunta);
        
        if(PREGUNTA.equals(pregunta)){
           return RESPOSTA.getBytes();
       }
       return RESPOSTANULL.getBytes();
    }
    
}