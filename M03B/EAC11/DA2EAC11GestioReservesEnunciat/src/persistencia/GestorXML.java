package persistencia;

import model.Taula;
import model.Cambrer;
import model.Reserva;
import model.ElementTaula;
import java.io.File;
import java.util.Iterator;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import principal.GestioReservesExcepcio;
import model.Menjador;
import model.Restaurant;

/**
 *
 * @author fta
 */
public class GestorXML implements ProveedorPersistencia {

    private Document doc;
    private Restaurant restaurant;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void desarRestaurant(String nomFitxer, Restaurant restaurant) throws GestioReservesExcepcio {
        construeixModel(restaurant);
        desarModel(nomFitxer);
    }

    @Override
    public void carregarRestaurant(String nomFitxer) throws GestioReservesExcepcio {
        carregarFitxer(nomFitxer);
        fitxerRestaurant();
    }

    /*Paràmetres: Restaurant a partir de la qual volem construir el model
     *
     *Acció: 
     * - Llegir els atributs de l'objecte Restaurant passat per paràmetre 
     *   per construir un model (document XML) sobre el Document doc (atribut de GestorXML).
     *
     * - L'arrel del document XML és "restaurant". Aquesta arrel, l'heu d'afegir 
     *   a doc. Un cop fet això, heu de recórrer l'ArrayList elements de 
     *   Restaurant i per a cada element, afegir un fill a doc. Cada fill 
     *   tindrà com atributs els atributs de l'objecte (codi, nom, …).
     *
     * - Si es tracta d'una taua, a més, heu d'afegir fills addicionals amb 
     *   els cambrers i reserves assignats a la taula .
     *
     * - Si es tracta d'un menjador, a més, heu d'afegir fills addicionals amb les
     *   seves taules.
     *
     * - En el cas del cambrer, si és actiu, a l'atribut actiu del document XML li
     *   heu d'assignar "actiu", si no, "no actiu"
     *
     *Retorn: cap
     */
    public void construeixModel(Restaurant restaurant) throws GestioReservesExcepcio {
        //Es construeix el document model
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {
            builder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            throw new GestioReservesExcepcio("GestorXML.model");
        }

        this.doc = (Document) builder.newDocument();

        //Element arrel
        Element arrel = doc.createElement("restaurant");
        arrel.setAttribute("codi", String.valueOf(restaurant.getCodi()));
        arrel.setAttribute("nom", restaurant.getNom());
        arrel.setAttribute("adreca", restaurant.getAdreca());
        doc.appendChild(arrel);

        for (int i = 0; i < restaurant.getElements().length; i++) {

            if (restaurant.getElements()[i] instanceof Cambrer) {

                Element unelement = doc.createElement("cambrer");

                unelement.setAttribute("codi", ((Cambrer) restaurant.getElements()[i]).getCodi());
                unelement.setAttribute("nom", ((Cambrer) restaurant.getElements()[i]).getNom());
                unelement.setAttribute("telefon", ((Cambrer) restaurant.getElements()[i]).getTelefon());
                unelement.setAttribute("torn", ((Cambrer) restaurant.getElements()[i]).getTorn());

                if (((Cambrer) restaurant.getElements()[i]).getActiu()) {
                    unelement.setAttribute("actiu", "actiu");
                } else {
                    unelement.setAttribute("actiu", "no actiu");
                }

                arrel.appendChild(unelement);

            } else if (restaurant.getElements()[i] instanceof Reserva) {

                Element unelement = doc.createElement("reserva");

                unelement.setAttribute("codi", ((Reserva) restaurant.getElements()[i]).getCodi());
                unelement.setAttribute("nom", ((Reserva) restaurant.getElements()[i]).getNom());
                unelement.setAttribute("telefon", ((Reserva) restaurant.getElements()[i]).getTelefon());
                unelement.setAttribute("totalComensals", (String.valueOf(((Reserva) restaurant.getElements()[i]).getComensals())));
                unelement.setAttribute("data", ((Reserva) restaurant.getElements()[i]).getData());
                unelement.setAttribute("hora", ((Reserva) restaurant.getElements()[i]).getHora());

                arrel.appendChild(unelement);

            } else if (restaurant.getElements()[i] instanceof Taula) {

                Element unelement = doc.createElement("taula");

                unelement.setAttribute("codi", ((Taula) restaurant.getElements()[i]).getCodi());
                unelement.setAttribute("places", (String.valueOf(((Taula) restaurant.getElements()[i]).getPlaces())));

                Iterator j = ((Taula) restaurant.getElements()[i]).getElementsTaula().iterator();

                while (j.hasNext()) {

                    Object elementTaula = j.next();

                    if (elementTaula instanceof Cambrer) {

                        Element fill = doc.createElement("cambrer");

                        fill.setAttribute("codi", ((Cambrer) elementTaula).getCodi());
                        fill.setAttribute("nom", ((Cambrer) elementTaula).getNom());
                        fill.setAttribute("telefon", ((Cambrer) elementTaula).getTelefon());
                        fill.setAttribute("torn", ((Cambrer) elementTaula).getTorn());

                        if (((Cambrer) elementTaula).getActiu()) {
                            fill.setAttribute("actiu", "actiu");
                        } else {
                            fill.setAttribute("actiu", "no actiu");
                        }

                        unelement.appendChild(fill);

                    } else if (elementTaula instanceof Reserva){ //ÉS reserva

                        Element fill = doc.createElement("reserva");

                        fill.setAttribute("codi", ((Reserva) elementTaula).getCodi());
                        fill.setAttribute("nom", ((Reserva) elementTaula).getNom());
                        fill.setAttribute("telefon", ((Reserva) elementTaula).getTelefon());
                        fill.setAttribute("totalComensals", (String.valueOf(((Reserva) elementTaula).getComensals())));
                        fill.setAttribute("data", ((Reserva) elementTaula).getData());
                        fill.setAttribute("hora", ((Reserva) elementTaula).getHora());

                        unelement.appendChild(fill);

                    }

                }

                arrel.appendChild(unelement);

            } else if (restaurant.getElements()[i] instanceof Menjador){ //És menjador

                Element unelement = doc.createElement("menjador");

                unelement.setAttribute("codi", (((Menjador) restaurant.getElements()[i]).getCodi()));
                unelement.setAttribute("places", (String.valueOf(((Menjador) restaurant.getElements()[i]).getPlaces())));
                unelement.setAttribute("placesOcupades", (String.valueOf(((Menjador) restaurant.getElements()[i]).getPlacesOcupades())));

                Iterator j = ((Menjador) restaurant.getElements()[i]).getTaules().iterator();

                while (j.hasNext()) {

                    Object taula = j.next();

                    Element fill = doc.createElement("taula");

                    fill.setAttribute("codi", ((Taula) taula).getCodi());
                    fill.setAttribute("places", (String.valueOf(((Taula) taula).getPlaces())));

                    Iterator k = ((Taula) taula).getElementsTaula().iterator();

                    while (k.hasNext()) {

                        Object elementTaula = k.next();

                        if (elementTaula instanceof Cambrer) {

                            Element net = doc.createElement("cambrer");

                            net.setAttribute("codi", ((Cambrer) elementTaula).getCodi());
                            net.setAttribute("nom", ((Cambrer) elementTaula).getNom());
                            net.setAttribute("telefon", ((Cambrer) elementTaula).getTelefon());
                            net.setAttribute("torn", ((Cambrer) elementTaula).getTorn());

                            if (((Cambrer) elementTaula).getActiu()) {
                                net.setAttribute("actiu", "actiu");
                            } else {
                                net.setAttribute("actiu", "no actiu");
                            }

                            fill.appendChild(net);

                        } else if (elementTaula instanceof Reserva){ //És reserva

                            Element net = doc.createElement("reserva");

                            net.setAttribute("codi", ((Reserva) elementTaula).getCodi());
                            net.setAttribute("nom", ((Reserva) elementTaula).getNom());
                            net.setAttribute("telefon", ((Reserva) elementTaula).getTelefon());
                            net.setAttribute("totalComensals", (String.valueOf(((Reserva) elementTaula).getComensals())));
                            net.setAttribute("data", ((Reserva) elementTaula).getData());
                            net.setAttribute("hora", ((Reserva) elementTaula).getHora());

                            fill.appendChild(net);

                        }

                    }

                    unelement.appendChild(fill);

                }

                arrel.appendChild(unelement);
            }
        }

    }

    public void desarModel(String nomFitxer) throws GestioReservesExcepcio {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(doc);
            File f = new File(nomFitxer + ".xml");
            StreamResult result = new StreamResult(f);
            transformer.transform(source, result);
        } catch (Exception ex) {
            throw new GestioReservesExcepcio("GestorXML.desar");
        }
    }

    public void carregarFitxer(String nomFitxer) throws GestioReservesExcepcio {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            File f = new File(nomFitxer + ".xml");
            doc = builder.parse(f);
        } catch (Exception ex) {
            throw new GestioReservesExcepcio("GestorXML.carregar");
        }
    }

    /*Paràmetres: cap
     *
     *Acció: 
     * - Llegir el fitxer del vostre sistema i carregar-lo sobre l'atribut doc.
    
     * - Per assignar valors als atributs de Restaurant i la resta d'objectes 
     *   que formen els elements de Restaurant.
    
     * - Primer creeu l'objecte Restaurant a partir de l'arrel del document per després 
     *   recórrer el doc i per cada fill, afegir un objecte a l'atribut elements de 
     *   Restaurant mitjançant el mètode escaient de la classe Restaurant.
    
     * - Si el fill (del doc) que s'ha llegit és una taula o un menjador, recordeu 
     *   que a més d'afegir-los a elements, també haureu d'afegir en la taula 
     *   els seus cambrers i reserves, i en el menjador les seves taules.
     *
     *Retorn: cap
     */
    private void fitxerRestaurant() throws GestioReservesExcepcio {

        Element arrel = doc.getDocumentElement();

        //Es crea l'objecte restaurant
        int codi = Integer.parseInt(arrel.getAttribute("codi"));
        String nom = arrel.getAttribute("nom");
        String adreca = arrel.getAttribute("adreca");

        restaurant = new Restaurant(adreca, nom, codi);

        //Recorregut de nodes fill d'un element       
        NodeList llistaFills = arrel.getChildNodes();

        for (int j = 0; j < llistaFills.getLength(); j++) {

            Node n = llistaFills.item(j);

            if (n.getNodeType() == Node.ELEMENT_NODE) {

                String tipusActiu = n.getNodeName();

                if (tipusActiu.equals("cambrer")) {

                    String codia = ((Element) n).getAttribute("codi");
                    String noma = ((Element) n).getAttribute("nom");
                    String telefona = ((Element) n).getAttribute("telefon");
                    String torna = ((Element) n).getAttribute("torn");

                    boolean actiua = false;

                    if (((Element) n).getAttribute("actiu").equals("actiu")) {
                        actiua = true;
                    }

                    restaurant.addCambrer(new Cambrer(codia, noma, telefona, torna, actiua));

                } else if (tipusActiu.equals("reserva")) {

                    String codia = ((Element) n).getAttribute("codi");
                    String noma = ((Element) n).getAttribute("nom");
                    String telefona = ((Element) n).getAttribute("telefon");
                    Integer totalComensalsa = Integer.parseInt(((Element) n).getAttribute("totalComensals"));
                    String dataa = ((Element) n).getAttribute("data");
                    String horaa = ((Element) n).getAttribute("hora");

                    restaurant.addReserva(new Reserva(codia, noma, telefona, totalComensalsa, dataa, horaa));

                } else if (tipusActiu.equals("taula")) {

                    String codia = ((Element) n).getAttribute("codi");
                    int placesa = Integer.parseInt(((Element) n).getAttribute("places"));

                    Taula taula = new Taula(codia, placesa);
                    restaurant.addTaula(taula);

                    NodeList gElements = n.getChildNodes();
                    for (int i = 0; i < gElements.getLength(); i++) {

                        Node ng = gElements.item(i);
                        if (ng.getNodeType() == Node.ELEMENT_NODE) {
                            
                            Integer tipusActiui = 0;
                            
                            if (ng.getNodeName().equals("cambrer")) {
                                tipusActiui = 1;
                            } else {
                                tipusActiui = 2;
                            }

                            int index = restaurant.selectElement(tipusActiui, ((Element) ng).getAttribute("codi"));
                            taula.addElementTaula((ElementTaula) restaurant.getElements()[index]);

                        }
                    }

                } else if (tipusActiu.equals("menjador")){ //És menjdor

                    String codia = ((Element) n).getAttribute("codi");
                    int placesa = Integer.parseInt(((Element) n).getAttribute("places"));
                    int placesOcupadesa = Integer.parseInt(((Element) n).getAttribute("placesOcupades"));

                    Menjador menjador = new Menjador(codia, placesa, placesOcupadesa);
                    restaurant.addMenjador(menjador);

                    NodeList gElements = n.getChildNodes();
                    for (int i = 0; i < gElements.getLength(); i++) {

                        Node ng = gElements.item(i);
                        
                        if (ng.getNodeType() == Node.ELEMENT_NODE) {

                            int index = restaurant.selectElement(3, ((Element) ng).getAttribute("codi"));
                            menjador.addTaulaMenjador((Taula) restaurant.getElements()[index]);

                        }
                    }
                }
            }
        }
    }
}
