package m09eac21b1;

import java.io.BufferedReader; 
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader; 
import java.io.IOException;
import java.io.OutputStream; 
import java.io.OutputStreamWriter;
import java.io.PrintWriter; 
import java.net.InetAddress;
import java.net.InetSocketAddress; 
import java.net.Socket;


public class Enviador {
    static final int PORT = 9999;
    static final String FILE = "ejemplo.txt";
    static Socket socket;
    
    public static void main(String[] args) {
        
        try{
            socket = null;
            //Usará una instancia de sockets
            InetAddress inetAddress = InetAddress.getLocalHost();
            
            while(!checkSocketConnection(inetAddress)){ //Comprovem si està en marxa el srv, si no esperem.    
            }
            
            
            //El flujo de salida circula por el socket
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //Part de fitxer
            File f = new File(FILE);
            FileReader fr = new FileReader(f);
            BufferedReader b = new BufferedReader(fr);

            String cadena;
            while((cadena = b.readLine())!=null) {
                out.write(cadena); //Escribo texto
                out.newLine(); //pasamos de linea
                out.flush();
                System.out.println(cadena); //Per comprobar que llegeix linia a linia.
            }
            //Cerramos Buffers de lectura y escritura
            b.close();
            out.close(); 
            
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private static boolean checkSocketConnection(InetAddress inetAddress) {
    try {
        socket = new Socket(inetAddress.getHostAddress(), PORT);
        return true;
    } catch (IOException ex) {
        return false;
    }
    }
    
}
