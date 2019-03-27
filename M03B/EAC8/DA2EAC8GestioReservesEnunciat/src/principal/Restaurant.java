package principal;

import elementsMobils.Taula;
import elementsMobils.Reserva;
import elementsMobils.Cambrer;
import java.util.Scanner;


/*
 * Classe que defineix un restaurant. Un restaurant es defineix per un codi, un 
 * nom i una adreça. A més, contindrà arrays amb cambrers, reserves, taules i menjadors. 
 */
/**
 *
 * @author fta
 */
public class Restaurant {
    static public final int SELECTCAMBRER = 1;
    static public final int SELECTRESERVA = 2;
    static public final int SELECTTAULA = 3;
    static public final int SELECTMENJADOR = 4;
    private int codi;
    static private int properCodi = 1; //El proper codi a assignar
    private String nom;
    private String adreca;
    private Element[] elements = new Element[230];
    private int posicioElements = 0; 

    /*
    TODO
     CONSTRUCTOR
     Paràmetres: valors pels atributs nom i adreca
     Accions:
     - Assignar als atributs corresponents els valors passats com a paràmetres
     - Assignar a l'atribut codi el valor de l'atribut properCodi i actualitzar
     properCodi amb el següent codi a assignar.
     */
    public Restaurant(String pNom, String pAdreca) {
        codi = properCodi;
        properCodi++;
        nom = pNom;
        adreca = pAdreca;
    }

    /*
     TODO Mètodes accessors    
     */
    public int getCodi() {
        return codi;
    }

    public void setCodi() {
        codi = properCodi;
    }

    public static int getProperCodi() {
        return properCodi;
    }

    public static void setProperCodi() {
        properCodi++;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String pNom) {
        nom = pNom;
    }

    public String getAdreca() {
        return adreca;
    }

    public void setAdreca(String pAdreca) {
        adreca = pAdreca;
    }

    public Element[] getElements() {
        return elements;
    }

    public int getPosicioElements() {
        return posicioElements;
    }
    
    
    /*
    TODO
     Paràmetres: cap
     Accions:
     - Demanar a l'usuari les dades per consola per crear un nou Restaurant. Les dades
     a demanar són les que necessita el constructor.
     - Heu de tenir en compte que tant el nom com l'adreça, poden ser frases, per exemple, 
     Bon menjar, o bé, C/ Gandia, 2.
     Retorn: El nou restaurant creat.
     */
    public static Restaurant addRestaurant() {
        Scanner dades = new Scanner(System.in);
        String nomRestaurant;
        String adrecaRestaurant;
        System.out.println("Nom del restaurant:");
        nomRestaurant = dades.nextLine();
        System.out.println("Adreça del restaurant:");
        adrecaRestaurant = dades.nextLine();
        return new Restaurant(nomRestaurant, adrecaRestaurant);
    }

    /*
    TODO
     Paràmetres: cap
     Accions:
     - Demanar a l'usuari que introdueixi les noves dades de l'objecte actual
     i modificar els atributs corresponents d'aquest objecte. En aquest cas
     no es pot modificar el contingut dels vectors, només els dels atributs 
     nom i adreça. Evidentment, tampoc podeu modificar el codi.
     - Li heu de mostrar a l'usuari el valor actual dels atributs de l'objecte
     actual, abans de modificar-los.
     Retorn: cap
     */
    public void updateRestaurant() {
        Scanner dades = new Scanner(System.in);

        System.out.println("\nNom del restaurant: " + nom);
        System.out.println("\nEntra el nou nom:");
        nom = dades.nextLine();
        System.out.println("\nAdreça del restaurant: " + adreca);
        System.out.println("\nEntra la nova adreça:");
        adreca = dades.nextLine();
    }

    public void showRestaurant() {
        System.out.println("\nLes dades del restaurant amb codi " + codi + " són:");
        System.out.println("\nNom:" + nom);
        System.out.println("\nAdreça:" + adreca);
    }

    /*
     CAMBRER
     */
 /*
     TODO
     Paràmetres: cap
     Accions:
     - afegeix un nou cambrer al vector de cambrers d'aquest restaurant (l'objecte actual),
     fent servir el mètode de la classe Cambrer pertinent.
     - actualitza la posició del vector de cambrers.
     Retorn: cap
     */
    public void addCambrer() {
        elements[posicioElements] = Cambrer.addCambrer();
        posicioElements++;
    }

    public int selectElement(int seleccio) {   
        if (seleccio == 0){//Cas seleccio = 0;
            while (!(seleccio > 0 && seleccio < 5)){
                Scanner dades = new Scanner(System.in);
                System.out.println("\nQuin tipus vols buscar:");
                System.out.println("\n1 - Cambrer:");
                System.out.println("\n2- Reserva:");
                System.out.println("\n3 - Taula:");
                System.out.println("\n4 - Menjador:");
                seleccio = dades.nextInt();
            }
        }
        String texte="";
        switch (seleccio){
            case SELECTCAMBRER:
                texte = "cambrer";
                break;
            case SELECTRESERVA:
                texte = "reserva";
                break;
            case SELECTTAULA:
                texte = "taula";
                break;
            case SELECTMENJADOR:
                texte = "menjador";
                break;
        }    
        Scanner dades = new Scanner(System.in);
        System.out.println("\nCodi de l'element " + texte + "?:");
        String codi = dades.next();
        boolean trobat = false;
        int pos = -1;
        switch (seleccio){
                case SELECTCAMBRER: // Cambrer
                    for (int i = 0; i < posicioElements && !trobat; i++) {
                        if (elements[i] instanceof Cambrer){
                            if (((Cambrer)elements[i]).getCodi().equals(codi)){
                                pos = i;
                                trobat = true;
                            }
                        }
                    }    
                case SELECTRESERVA: // Reserva
                    for (int i = 0; i < posicioElements && !trobat; i++) {
                        if (elements[i] instanceof Reserva){
                            if (((Reserva)elements[i]).getCodi().equals(codi)){
                                pos = i;
                                trobat = true;
                            }
                        }
                    }
                case SELECTTAULA: // Taula
                    for (int i = 0; i < posicioElements && !trobat; i++) {
                        if (elements[i] instanceof Taula){
                            if (((Taula)elements[i]).getCodi().equals(codi)){
                                pos = i;
                                trobat = true;
                            }
                        }
                    }
                case SELECTMENJADOR: // Menjador
                    for (int i = 0; i < posicioElements && !trobat; i++) {
                        if (elements[i] instanceof Menjador){
                            if (((Menjador)elements[i]).getCodi().equals(codi)){
                                pos = i;
                                trobat = true;
                            }
                        }
                    }
        }   
        return pos;
    }

    /*
     RESERVA
     */
 /*
     TODO
     Paràmetres: cap
     Accions:
     - afegeix una nova reserva al vector de reserves d'aquest restaurant (l'objecte actual),
     fent servir el mètode de la classe Reserva pertinent.
    - actualitza la posició del vector de reserves.
     Retorn: cap
     */
    public void addReserva() {
        elements[posicioElements] = Reserva.addReserva();
        posicioElements++;
    }
    
    /*
     TAULA
     *//*
    TODO
     Paràmetres: cap
     Accions:
     - afegeix una nova taula al vector de taules d'aquest restaurant (l'objecte actual),
     fent servir el mètode de la classe Taula pertinent.    
     - actualitza la posició del vector de taules.
     Retorn: cap
     */
    public void addTaula() {
        elements[posicioElements] = Taula.addTaula();
        posicioElements++;
    }
    /*
     MENJADOR
     */
 /*
     TODO
     Paràmetres: cap
     Accions:
     - afegeix un nou menjador al vector de menjadors d'aquest restaurant (l'objecte actual),
     fent servir el mètode de la classe Menjadors pertinent.    
    - actualitza la posició del vector de menjadors.
     Retorn: cap
     */
    public void addMenjador() {
        elements[posicioElements] = Menjador.addMenjador();
        posicioElements++;
    }

    public void addElementTaula(int tipuselement) { //tipus element només pot ser 1 = cambrer o 2 = reserva.
        Taula taulaSel = null;
        int pos = this.selectElement(SELECTTAULA); // 3 codi de Taula
        if (pos >= 0) {
            taulaSel = ((Taula)this.elements[pos]);
            if (tipuselement == SELECTCAMBRER){ //Cambrer
               pos = this.selectElement(tipuselement); //Selecciona element que vol afegir a taula
               taulaSel.addElementTaula((Cambrer)this.elements[pos]);
            }
            if (tipuselement == SELECTRESERVA){ //Reserva
               pos = this.selectElement(tipuselement);//Selecciona element que vol afegir a taula
               taulaSel.addElementTaula((Reserva)this.elements[pos]);
            }        
        } else {
            System.out.println("\nNo existeix aquesta taula");
        }
    }

    public void addTaulaMenjador() {
        Menjador menjadorSel = null;
        int pos = this.selectElement(SELECTMENJADOR);
        if (pos >= 0) {
            menjadorSel = (Menjador)this.elements[pos];
            int pos2 = this.selectElement(SELECTTAULA);//Selecciona taula.
            if (pos2 >= 0) {
                menjadorSel.addTaulaMenjador((Taula)this.elements[pos2]);
            } else {
                System.out.println("\nNo existeix aquesta taula");
            }
        } else {
            System.out.println("\nNo existeix aquest menjador");
        }
    }
}
