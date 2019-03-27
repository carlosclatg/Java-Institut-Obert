package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Restaurant;
import principal.GestioReservesExcepcio;

/**
 *
 * @author fta
 */
public class GestorJDBC implements ProveedorPersistencia {

    private Restaurant restaurant;

    private Connection conn; //Connexió a la base de dades

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    /*
     PreparedStatement necessaris
     */

 /*
     * TODO
     *
     * Obtenir un restaurant
     * 
     * Sentència select de la taula restaurant
     * Columnes: totes
     * Files: totes les que el codi de restaurant sigui el donat per paràmetre
     *
     */
    private static String codiRestaurantSQL = "";

    private PreparedStatement codiRestaurantSt;

    /*
     * TODO
     *
     * Inserir a restaurant
     * 
     * Sentència d'inserció de la taula restaurant
     * Els valors d'inserció són els que es donaran per paràmetre
     *
     */
    private static String insereixRestaurantSQL = "";

    private PreparedStatement insereixRestaurantSt;

    /*
     * TODO
     *
     * Actualitzar restaurant
     * 
     * Sentència d'actualització de la taula restaurant
     * Files a actualitzar: les que es corresponguin amb el codi donat per paràmetre
     * Columnes a actualitzar: nom i adreca als valors donats per paràmetre
     *
     */
    private static String actualitzaRestaurantSQL = "";

    private PreparedStatement actualitzaRestaurantSt;

    /*
     * TODO
     *
     * Eliminar cambrers (donat el codi d'un restaurant)
     * 
     * Sentència d'eliminació de la taula cambrer
     * Files a eliminar: les que es corresponguin amb el codi del restaurant donat per paràmetre
     *
     */
    private static String eliminaCambrerSQL = "";

    private PreparedStatement eliminaCambrerSt;

    /*
     * TODO
     *
     * Inserir a cambrer
     * 
     * Sentència d'inserció de la taula cambrer
     * Els valors d'inserció són els que es donaran per paràmetre
     *
     */
    private static String insereixCambrerSQL = "";

    private PreparedStatement insereixCambrerSt;

    /*
     Seleccionar cambrer donat un restaurant
     */
 /*
     * TODO
     *
     * Seleccionar els cambrers d'un restaurant
     * 
     * Sentència select de la taula cambrer
     * Columnes: totes
     * Files: totes les que el codi de restaurant sigui el donat per paràmetre
     *
     */
    private static String selCambrerSQL = "";

    private PreparedStatement selCambrerSt;

    /*
     *TODO
     * 
     *Paràmetres: cap
     *
     *Acció:
     *  - Heu d'establir la connexio JDBC amb la base de dades EAC111718S2
     *  - Heu de crear els objectes PrepareStatement declarats com a atributs d'aquesta classe
     *    amb els respectius SQL declarats com a atributs just sobre cadascun d'ells.
     *  - Heu de fer el catch de les possibles excepcions (en aquest mètode no llançarem GestioReservesExcepcio,
     *    simplement, mostreu el missatge a consola de l'excepció capturada)
     *
     *Retorn: cap
     *
     */
    public void estableixConnexio() throws SQLException {

    }

    /**
     * Tanca la connexió i posa la referència a la connexió a null.
     *
     * @throws SQLException
     */
    public void tancaConnexio() throws SQLException {
        try {
            conn.close();
        } finally {
            conn = null;
        }
    }

    /*
     *TODO
     * 
     *Paràmetres: el nom del fitxer i el restaurant a desar
     *
     *Acció:
     *  - Heu de desar el restaurant sobre la base de dades:
     *  - El restaurant s'ha de desar a la taula restaurants (nomFitxer conté el codi del restaurant)
     *  - Cada cambrer del restaurant, s'ha de desar com registre de la taula cambrer
     *  - Heu de tenir en compte que si el restaurant ja existia amb aquest codi, llavors heu de fer el següent:
     *     - Actualitzar el registre restaurant ja existent
     *     - Eliminar tots els cambrers d'aquest restaurant de la taula cambrer i després inserir els nous com si hagués estat
     *       un restaurant nou.
     *  - Si al fer qualsevol operació es dona una excepció, llavors heu de llançar l'excepció GestioReservesExcepcio amb codi "GestorJDBC.desar"
     *
     *Retorn: cap
     *
     */
    @Override
    public void desarRestaurant(String nomFitxer, Restaurant restaurant) throws GestioReservesExcepcio {

    }

    /*
     *TODO
     * 
     *Paràmetres: el nom del fitxer del restaurant
     *
     *Acció:
     *  - Heu de carregar el restaurant des de la base de dades (nomFitxer és el codi del restaurant)
     *  - Per fer això, heu de cercar el registre restaurant de la taula amb codi = nomFitxer
     *  - A més, heu d'afegir els cambrers al restaurant a partir de la taula cambrer
     *  - Si al fer qualsevol operació es dona una excepció, llavors heu de llançar l'excepció GestioReservesExcepcio 
     *    amb codi "GestorJDBC.carregar"
     *  - Si el nomFitxer donat no existeix a la taula restaurant (és a dir, el codi = nomFitxer no existeix), llavors
     *    heu de llançar l'excepció GestioReservesExcepcio amb codi "GestorJDBC.noexist"
     *
     *Retorn: cap
     *
     */
    @Override
    public void carregarRestaurant(String nomFitxer) throws GestioReservesExcepcio {
        
    }

}
