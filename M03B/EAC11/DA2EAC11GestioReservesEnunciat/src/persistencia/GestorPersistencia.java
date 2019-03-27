package persistencia;

import principal.GestioReservesExcepcio;
import model.Restaurant;

/**
 *
 * @author Francesca
 */
public class GestorPersistencia {

    private ProveedorPersistencia gestor;

    public ProveedorPersistencia getGestor() {
        return gestor;
    }

    public void setGestor(ProveedorPersistencia pGestor) {
        gestor = pGestor;
    }

    public void desarRestaurant(String tipusPersistencia, String nomFitxer, Restaurant restaurant) throws GestioReservesExcepcio {

        switch (tipusPersistencia) {

            case "XML":
                gestor = new GestorXML();
                break;
            case "Serial":
                gestor = new GestorSerial();
                break;
            case "JDBC":
                gestor = new GestorJDBC();
                break;
            default:
                gestor = new GestorDB4O();
                break;
        }

        gestor.desarRestaurant(nomFitxer, restaurant);
    }

    public void carregarRestaurant(String tipusPersistencia, String nomFitxer) throws GestioReservesExcepcio {

        switch (tipusPersistencia) {

            case "XML":
                gestor = new GestorXML();
                break;
            case "Serial":
                gestor = new GestorSerial();
                break;
            case "JDBC":
                gestor = new GestorJDBC();
                break;
            default:
                gestor = new GestorDB4O();
                break;

        }

        gestor.carregarRestaurant(nomFitxer);
    }

}
