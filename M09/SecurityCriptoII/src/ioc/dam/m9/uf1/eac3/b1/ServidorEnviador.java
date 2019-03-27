/**Teniu el mètode traduirClauXifrada(), que ja teniu implementat, tal i com hem comentat anteriorment a l'exercici.

Després el mètode ClientHibrid(), on s'utilitza un xifrat híbrid.

Abans que res s'hauria de generar un parell de claus RSA, ho podem fer creant un objecte del tipus KeyPairGenerator.


Tot seguit enviarem la clau pública al client. 

El següent pas es rebre la clau de sessió del client encriptada, i la desxifrarem utilitzant la nostra clau privada. (Aquest seria el pas de criptografia asimètrica)
            
Cipher rsaCipher = Cipher.getInstance("RSA");      
rsaCipher.init(Cipher.DECRYPT_MODE, keypair.getPrivate());      
SecretKeyFactory secretkeyfactory = SecretKeyFactory.getInstance("DES");  
DESKeySpec deskeyspec = new DESKeySpec(rsaCipher.doFinal(traduirClauXifrada(clau_xifrada)));      
SecretKey key = secretkeyfactory.generateSecret(deskeyspec);  

Un cop ja tenim la clau de sessió (Criptografia asimètrica), ja podem utilitzar un algorisme simètric per transferir la informació. El primer pas és crear un objecte de xifrat, ja sigui DES, TripleDES o l'algorisme simètric que vulgueu.

Tot seguit rebre el id del fitxer a enviar, i tot seguit l'enviarem xifrant la informació byte a byte amb la clau de sessió simètrica.
package ioc.dam.m9.uf1.eac3.b1;
*/
/**
 *
 * @author Usuari
 */

package ioc.dam.m9.uf1.eac3.b1;
import java.io.BufferedOutputStream;
import java.io.BufferedReader; 
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileReader;
import java.io.IOException; 
import java.io.InputStream; 
import java.io.InputStreamReader; 
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream; 
import java.io.OutputStreamWriter;
import java.io.PrintWriter; 
import java.net.InetSocketAddress; 
import java.net.ServerSocket; 
import java.net.Socket; 
import java.security.InvalidKeyException;
import java.security.KeyFactory; 
import java.security.KeyPair; 
import java.security.KeyPairGenerator; 
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher; 
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey; 
import javax.crypto.SecretKeyFactory; 
import javax.crypto.spec.DESKeySpec;

    
  public class ServidorEnviador {

    private static byte[] traduirClauXifrada(String clau) {
    String[] items = clau.split(",");    
    byte[] resultat = new byte[items.length];        
    for (int i = 0; i < items.length; i++) {      
        resultat[i] = (byte) Integer.parseInt(items[i]);
      }    
              
      return resultat;  
    }
    
    public static void ClientHibrid(Socket socket) {    
        
        try {
            KeyPair keypair;
            PrivateKey privateKey;
            PublicKey publicKey;
            SecretKey clausessioDES;
            // Generant el parell de claus RSA
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(512);
            keypair = keyGen.genKeyPair();
            publicKey = keypair.getPublic();
            privateKey = keypair.getPrivate();
            
            System.out.println(publicKey);
            // Enviant la clau pública al client 
            /*Key i String implementen seriablizable, així doncs ho podem fer amb un ObjectStream
            i després fem un casting per tal d'obtenir la classe correcte.*/
            ObjectOutputStream out = new ObjectOutputStream((socket.getOutputStream()));
            out.writeObject(publicKey);
            out.flush();

            // Rep la clau de sessió       
            String clau_xifrada;
            ObjectInputStream  oi = new ObjectInputStream (socket.getInputStream());
            clau_xifrada = (String) oi.readObject();
            
            // Desxifrant la clau de sessió
            clausessioDES = decryptRSAObteKey(privateKey, clau_xifrada); //Aquesta és la clau simètrica desencriptada amb la clau privada.
            System.out.println("La clau de sessio en String és " + clau_xifrada );
            System.out.println("La clau de sessio (Secret Key) és :" + clausessioDES + "");

            // Creant el objecte de xifrat de sessió     
            Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            desCipher.init(Cipher.DECRYPT_MODE, clausessioDES);
            
            // Rep el id del fitxer
            byte[] bytefitxer_cod = (byte[]) oi.readObject(); //byte codificats amb clau simètrica
            byte[] bytefixer_dec = desCipher.doFinal(bytefitxer_cod);//Deco amb clau simètrica i obtenim bytes
            String rutafitxer = new String(bytefixer_dec, "UTF-8"); //De bytes a string.
            System.out.println("La ruta del fitxer és:" + rutafitxer);

            
            // Enviant fitxer xifrat amb la clau de sessió
            File f = new File(rutafitxer);
            if(f.exists()){ //Si troba el fitxer
                FileReader fr = new FileReader(f);
                BufferedReader b = new BufferedReader(fr);
                String cadenatotal = "";
                String cadena;
                while((cadena = b.readLine())!=null) { //Enviem el fitxer sencer de cop per simplificar.
                    cadenatotal += cadena; //Concateno la linea
                    cadenatotal = cadenatotal.concat("\n"); //Afegeixo salt de linia
                }
                desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding"); //Ara encriptem les dades amb la clau simètrica
                desCipher.init(Cipher.ENCRYPT_MODE, clausessioDES);
                byte[] cadenatotalencryp = desCipher.doFinal(cadenatotal.getBytes());
                out.writeObject(cadenatotalencryp);
                out.flush();
            } else {
                System.out.println("Fitxer no trobat"); // Si no el troba.
            }
            

        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException e) {      
            e.printStackTrace();
            System.out.println("Problemes en l'enviament del fitxer");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServidorEnviador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ServidorEnviador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static SecretKey decryptRSAObteKey(PrivateKey privatekey, String clau_xifrada) throws Exception {
        Cipher rsaCipher = Cipher.getInstance("RSA");  
        rsaCipher.init(Cipher.DECRYPT_MODE, privatekey);
        SecretKeyFactory secretkeyfactory = SecretKeyFactory.getInstance("DES");  
        DESKeySpec deskeyspec = new DESKeySpec(rsaCipher.doFinal(traduirClauXifrada(clau_xifrada)));      
        SecretKey key = secretkeyfactory.generateSecret(deskeyspec);  
        return key;
    }

  }  