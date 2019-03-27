/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m9.uf1.eac3.b1;

/**
 *
 * @author Usuari
 */
import java.io.BufferedOutputStream;
import java.util.Base64;
import java.io.BufferedReader; 
import java.io.InputStream; 
import java.io.InputStreamReader; 
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream; 
import java.io.PrintWriter; 
import java.io.UnsupportedEncodingException;
import java.math.BigInteger; 
import java.net.InetAddress;
import java.net.InetSocketAddress; 
import java.net.Socket; 
import java.security.Key; 
import java.security.KeyFactory; 
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher; 
import javax.crypto.KeyGenerator; 
import javax.crypto.SecretKey; 
import javax.crypto.SecretKeyFactory; 
import javax.crypto.spec.DESKeySpec;

public class ClientReceptor {
    
    private static int PORT = 7777;
    private static String prepararClauXifrada(byte[] clau) {    
        String resultat = "";

      for (byte b : clau) 
      {      
          resultat += ((int)b)+",";    
      }
      return resultat;  
    }

    public static void main(String[] args) {        
        String ubicaciofitxers;
        try {
            
            Socket socket = new Socket("localhost", PORT);

            // Reb clau pública utilitzant un algorisme RSA
            /*Key i String implementen seriablizable, així doncs ho podem fer amb un ObjectStream
            i després fem un casting per tal d'obtenir la classe correcte.*/
            ObjectInputStream  oi = new ObjectInputStream (socket.getInputStream());
            PublicKey publicKey = (PublicKey) oi.readObject();
            System.out.println("La clau pública rebuda del srv és :" + publicKey);

            // Creant objecte de xifrat asimètric RSA        
            Cipher rsacipher = Cipher.getInstance("RSA");
            rsacipher.init(Cipher.ENCRYPT_MODE, publicKey);
            
            //Genero clau de sessió simétrica
            KeyGenerator kgen = KeyGenerator.getInstance("DES");
            SecretKey secretkey = kgen.generateKey();
            SecretKeyFactory seckeyfac = SecretKeyFactory.getInstance("DES");      
            DESKeySpec seckeyspec = (DESKeySpec) seckeyfac.getKeySpec(secretkey, DESKeySpec.class);
            String clau_xifrada = prepararClauXifrada(rsacipher.doFinal(seckeyspec.getKey()));  
            

            // Enviant clau de sessió al servidor, xifrada amb la clau pública.           
            ObjectOutputStream out = new ObjectOutputStream((socket.getOutputStream()));
            out.writeObject(clau_xifrada);
            
            System.out.println("La clau xifrada en string és :" +clau_xifrada);
            System.out.println("La SecreKey és" + secretkey +"");
            
            
            // Creant objecte de xifrat simètric  
            Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding"); //Preparem per encriptar amb clau simètrica
            desCipher.init(Cipher.ENCRYPT_MODE, secretkey);
            
            
            // Enviant el id del fitxer
            String ubicaciofitxer = "C:\\Users\\carlo\\Desktop\\desarrollo de aplicaciones\\"
                    + "Q4\\M09 - fils i processos\\EAC6\\DAM_M09_EAC3P2_Enunciat1819_S1d\\fichero.txt";
            byte[] bytefitxer = desCipher.doFinal(ubicaciofitxer.getBytes());
            out.writeObject(bytefitxer);
            out.flush();
            
            //Rebent el fitxer 
            byte[] contingutfitxer_dec = (byte[]) oi.readObject();
            desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding"); //Preparem per desencriptar amb clau simètrica
            desCipher.init(Cipher.DECRYPT_MODE, secretkey);
            byte[] contingutfitxerbytes = desCipher.doFinal(contingutfitxer_dec);
            String contingutfitxer = new String(contingutfitxerbytes, "UTF-8");
            System.out.println("El contingut del fitxer és:\n" + contingutfitxer);

            
            
        } catch (Exception e) {      
            e.printStackTrace();    
        }  
    }
    
/*    public static byte[] encryptData(byte[] data, PublicKey pub) {
        byte[] encryptedData = null;    
        try {
          Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding","SunJCE");
          cipher.init(Cipher.ENCRYPT_MODE, pub);      
          encryptedData =  cipher.doFinal(data);       
        } catch (Exception  ex) {  
          System.err.println("Error xifrant: " + ex);
        }
        return encryptedData;
    }*/
}
            
