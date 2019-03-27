/*
Realitzem un petit client FTP, aquest es connecta a un servidor instal·lat en el localhost, o la IP
que dessitgis, un cop conectat intentarem passar un document de l'origen a la carpeta del servidor FTP. 
 */
package ioc.dam.m9.uf3.eac2.b1;

/**
 *
 * @author Usuari
 */
import java.io.BufferedInputStream;
import java.io.FileInputStream; 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTP;  
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpClient 
{
    static final int OPCIO1 = 1;
    static final int OPCIO2 = 2;
    static final int OPCIO3 = 3;
    
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException
    {
        try
        {
            Scanner lector=new Scanner(System.in);
            String usuari = llegirUsuari();
            String password = llegirPassword();
            // Creant el nostre objecte ftpClient 
            FTPClient ftpClient = new FTPClient();
            //System.out.println("1");
            String server = "localhost";
            
            //Crear connexio
            ftpClient.connect(server);
            ftpClient.user(usuari);
            ftpClient.pass(password);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            System.out.println(ftpClient.getReplyString());
            // Conectant al servidor
            //Verificar connexió amb el servidor.
            int reply = ftpClient.getReplyCode();
            if(!FTPReply.isPositiveCompletion(reply)){
                
                ftpClient.disconnect();
                System.err.println("FTP server refused connection.");
                System.exit(1);
                
            } else {
                
                menu(ftpClient);
                
            }

            //Verificar si fa el canvi de directori de treball
            //Canvi de directori de treball
            //Fer una activació per enviar qualsevol tipus d'arxiu            
            //Anem al menu per mirar les diverses opcions

            ftpClient.logout(); //Tancaar sessió 
            ftpClient.disconnect();//Ens desconectem del server
        }
        catch(Exception e)
                {   
                }
    }
    
    
    
    public static void menu(FTPClient ftpClient) throws IOException{
        Scanner lector=new Scanner(System.in);
        String menu = " ----MENU ---- "
                + "\n Premeu " + OPCIO1 +" si voleu copiar un fitxer "
                + "\n Premeu " + OPCIO2 +" si voleu borrar un fitxer "
                + "\n Premeu " + OPCIO3 +" per sortir ";
        
        int opcio = 0;
        
        while(opcio != OPCIO3){
            System.out.println(menu);
            opcio = lector.nextInt(); //No faig comprovacio de tipus
            if(opcio == OPCIO1){
                copiarFitxerFTP(ftpClient);
            } else if (opcio == OPCIO2) {
                esborrarFitxerFTP(ftpClient);
            } else if(opcio != OPCIO3){
                System.out.println("Introdueix un codi correcte");
            }
        }       
    } 
    
    public static String llegirUsuari(){
        String introusuari = "Introdueix nom usuari";
        System.out.println(introusuari);
        Scanner lector=new Scanner(System.in);
        return lector.nextLine();
    }
    public static String llegirPassword(){
        String password = "Introdueix password";
        System.out.println(password);
        Scanner lector=new Scanner(System.in);
        return lector.nextLine();
    }
    
    public static String llegirNomFitxerEsborrar(){
        String password = "Nom fitxer a esborrar";
        System.out.println(password);
        Scanner lector=new Scanner(System.in);
        return lector.nextLine();
    }
    
        
    public static String llegirNomFitxerCopiar(){
        String password = "Nom fitxer a copiar";
        System.out.println(password);
        Scanner lector=new Scanner(System.in);
        return lector.nextLine();
    }
    
    public static void copiarFitxerFTP(FTPClient ftpClient) {

        try {
            String nomfitxer = llegirNomFitxerCopiar();
            FileInputStream fis = new FileInputStream(nomfitxer);
            ftpClient.storeFile(nomfitxer, fis);
            System.out.println("transferencia realitzada ok!");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Fitxer no trobat!!!");
            return;
        } catch (IOException e){
            e.printStackTrace();
            return;
        }  
    }
    

    public static void esborrarFitxerFTP(FTPClient ftpClient) throws IOException{
        String nom = llegirNomFitxerEsborrar();
        ftpClient.deleteFile(nom);
        
    }
    
}
