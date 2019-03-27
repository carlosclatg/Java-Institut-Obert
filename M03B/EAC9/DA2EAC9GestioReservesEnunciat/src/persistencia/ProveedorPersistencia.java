package persistencia;

import principal.GestioReservesExcepcio;
import principal.Restaurant;


/**
 *
 * @author Francesca
 */
public interface ProveedorPersistencia {
    public void desarRestaurant(String nomFitxer, Restaurant restaurant)throws GestioReservesExcepcio;
    public void carregarRestaurant(String nomFitxer)throws GestioReservesExcepcio;
}
