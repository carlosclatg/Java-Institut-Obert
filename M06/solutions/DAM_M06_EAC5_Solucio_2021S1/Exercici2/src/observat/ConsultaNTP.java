/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observat;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import observadors.ControlaTemps;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.NtpV3Packet;
import org.apache.commons.net.ntp.TimeInfo;


/**
 *
 * @author professor
 * 
 */
public class ConsultaNTP {
    
    

    private String cercadorTempsNtp;
    
    VetoableChangeSupport vcs = new VetoableChangeSupport(this);
    PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    public String getTemps(){
        return cercadorTempsNtp;
    }
    
    public void setTemps(String text) throws PropertyVetoException, IOException{
        
        String mostraTemps = getTempsNtp(text);
       
        String oldTemps=this.cercadorTempsNtp;
        vcs.fireVetoableChange("ConsultaNTP.cercadorTempsNtp", oldTemps, mostraTemps);
        this.cercadorTempsNtp=mostraTemps;
        pcs.firePropertyChange("ConsultaNTP.cercadorTempsNtp", oldTemps, mostraTemps);
        
        
        
        
    }
    
/**
 * Retorna una cadena amb el servidor de temps consultat i la data i l'hora actual
 * que aquest proporciona. La cadena és en format llegible.
 * NO L'HEU DE MODIFICAR NI CRIDAR DIRECTAMENT.
 * @param entrada servidor a consultar
 * @return cadena amb el servidor i la data i hora proporcionades.
 * @throws IOException quan es produeix un error en l'operació
 */    

     
    private static String getTempsNtp(String entrada) throws IOException {

            try {
                
                if (entrada.equals("")) { 
                    return ControlaTemps.noNTP; } 
                else {
                    NTPUDPClient timeClient = new NTPUDPClient();
                    InetAddress inetAddress = InetAddress.getByName(entrada);
                    TimeInfo timeInfo = timeClient.getTime(inetAddress);
                    NtpV3Packet message = timeInfo.getMessage();
                    long serverTime = message.getTransmitTimeStamp().getTime();
                    Date time = new Date(serverTime);
                    String temps= time.toString().substring(0, 16);
                    return entrada+"\n"+temps+"\n";
                }
                    
                 
            } catch (UnknownHostException e) {return ControlaTemps.badNTP;}
 
    }
    

      /**
     * permet registrar un VetoableChangeListener que observi l'objecte
     * @param listener observador que volem registrar
     */
    public void addVetoableChangeListener(VetoableChangeListener listener) {
         this.vcs.addVetoableChangeListener(listener);
     }

    /**
     * permet registrar un PropertyChangeListener que observi l'objecte
     * @param listener observador que volem registrar
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
         this.pcs.addPropertyChangeListener(listener);
     }    
    
     public void removePropertyChangeListener(PropertyChangeListener listener) {
         this.pcs.removePropertyChangeListener(listener);
     }
 
    public void removeVetoableChangeListener(VetoableChangeListener listener) {
         this.vcs.removeVetoableChangeListener(listener);
     }
}
