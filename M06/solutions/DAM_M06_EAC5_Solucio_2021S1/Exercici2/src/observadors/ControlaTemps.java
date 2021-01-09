/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observadors;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
/**
 * VetoableChangeListener que evita registrar servidors NTP i temps iguals a l'últim introduït
 * @author professor
 * 
 */
public class ControlaTemps implements VetoableChangeListener {
   
  
        public static final String noNTP = "noNTP";// No esta permès
        public static final String badNTP = "badNTP"; // Servidor inexistent
        
     /**
     * Llenca una excepcio si es vol cercar un servidor NTP sense cap caràcter d'entrada o un que no existeix
     * El missatge de l'excepcio sera diferent si el servidor esta en blanc o si es erroni.
     * @param evt esdeveniment amb informacio sobre el canvi que es demana
     * @throws PropertyVetoException excepcio que indica que el nou valor no es correcte
     */   
    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
       String valor=evt.getNewValue().toString();
       if (valor.equals(noNTP)) {
           throw new PropertyVetoException("Compte! No has introduït cap caràcter", evt);
       }
       else if(valor.equals(badNTP)){
           throw new PropertyVetoException("Compte! El servidor introduit no existeix", evt);        
    }
    }
     
   
}

