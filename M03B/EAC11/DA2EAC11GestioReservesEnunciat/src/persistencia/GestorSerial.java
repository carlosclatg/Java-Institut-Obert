package persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;
import principal.GestioReservesExcepcio;
import model.Restaurant;

/**
 *
 * @author fta
 */
public class GestorSerial implements ProveedorPersistencia{
    
    private Restaurant restaurant;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void desarRestaurant(String nomFitxer, Restaurant restaurant) throws GestioReservesExcepcio {
        /*
         *TODO
         *
         *Paràmetres: nom del fitxer i restaurant
         *
         *Acció:
         * - Ha de desar l'objecte Restaurant serialitzat sobre un fitxer del sistema 
         *   operatiu amb nom nomFitxer i extensió ".ser".
         * - Heu de controlar excepcions d'entrada/sortida i en cas de produïrse alguna, 
         *   llavors llançar GestioReservesExcepcio amb codi GestorSerial.desar 
         *
         *Nota: podeu comprovar que la classe Restaurant implementa Serializable  
         *
         *Retorn: cap
         */
        
        try(ObjectOutputStream oos =new ObjectOutputStream(new FileOutputStream(new File(nomFitxer + ".ser")))) {
            oos.writeObject(restaurant);
        } catch (IOException ex) {
            throw new GestioReservesExcepcio("GestorSerial.desar");
        }
        
    }

    @Override
    public void carregarRestaurant(String nomFitxer) throws GestioReservesExcepcio {
        /*
         *TODO
         *
         *Paràmetres: nom del fitxer
         *
         *Acció:
         * - Ha de carregar el fitxer del sistema operatiu amb nom nomFitxer i extensió 
         *   ".ser" sobre un nou objecte Restaurant que es retornarà com a resultat.               
         * - Heu de controlar excepcions d'entrada/sortida i en cas de produïrse alguna, 
         *   llavors llançar GestioReservesExcepcio amb codi GestorSerial.carrega 
         *
         *Retorn: cap
         */
               
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(nomFitxer + ".ser")))) {
            restaurant = (Restaurant) ois.readObject();
        } catch (IOException ex) {
            throw new GestioReservesExcepcio("GestorSerial.carregar");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error de classe: " + ex.getMessage());
        }
    }
}
