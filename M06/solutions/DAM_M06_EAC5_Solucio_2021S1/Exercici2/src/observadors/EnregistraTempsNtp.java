/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observadors;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * PropertyChangeListener que enregistra una marca de temps NTP per la qual correspon
 * a un servidor, tot indicant l'hora.
 * @author professor
 * 
 */
public class EnregistraTempsNtp implements PropertyChangeListener {

    private final List<String> llistaTempsNtp=new ArrayList<>();

    /**
     * obte la llista amb el registre de els temps i servidors NTP cercats
     * @return 
     */
    public List<String> getLlistaTempsNtp() {
        return llistaTempsNtp;
    }

     /**
     * afegeix una nova adreça de servidor NTP a la llista seguit per l'hora, els minuts
     * (format HH:MM) en que aquest estat s'assigna pel servidor
     * @param evt 
     */          
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String Temps = evt.getNewValue().toString();
        llistaTempsNtp.add(Temps);

    }


    /**
     * Obte una cadena amb el servidor i temps NTP
     * separats per un salt de linia
     * @return cadena amb els estats, cadascun en una linia
     */
    @Override
    public String toString() {
        String resultat="", salt="";
        
        for(String s:llistaTempsNtp){
            resultat+=salt+s;
            salt="\n";
        }
        
        return resultat;
    }
    
}
