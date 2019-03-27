package principal;


/**
 *
 * @author fta
 */
public class GestioReservesExcepcio extends Exception {
    
    private String codiCausa = "desconegut";
    private String missatge;

    public GestioReservesExcepcio(String codiCausa) {
        this.codiCausa = codiCausa;
        switch (codiCausa) {
            case "1":
                this.missatge = "L'opció ha de ser correcta";
                break;
            case "2":
                this.missatge = "Ja no hi caben més elements";
                break;
            case "3":
                this.missatge = "El codi està repetit";
                break;
            case "GestorXML.model":
                this.missatge = "No s'ha pogut crear el model XML per desar el restaurant";
                break;
            case "GestorXML.desar":
                this.missatge = "No s'ha pogut desar el restaurant a causa d'error d'entrada/sortida";
                break;
            case "GestorXML.carrega":
                this.missatge = "No s'ha pogut carregar el restaurant a causa d'error d'entrada/sortida";
                break;
            default:
                this.missatge = "Error desconegut";
                break;
        }
    }

}
