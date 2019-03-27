package elementsMobils;


import java.util.Scanner;

/*
 * Classe que defineix un cambrer. Un cambrer es defineix pel seu codi, nom, telefon, torn 
 * en què treballa (matí, tarda, matí i tarda,etc.) i si està actiu o no.
 */
/**
 *
 * @author fta
 */
public class Cambrer extends ElementTaula {

    private String torn;
    private boolean actiu;

    /*
     TODO CONSTRUCTOR
     Paràmetres: valors per tots els atributs de la classe, menys l'atribut actiu,
     ja que un cambrer sempre estarà en actiu en el moment que es crea.
     Accions:
     - Assignar als atributs els valors passats com a paràmetres
     - Assignar el valor que sigui escaient a l'atribut actiu
     */

    /*
     TODO Mètodes accessors    
     */

    public Cambrer(String codi, String nom, String telefon, String torn) {
        super(codi, nom, telefon);
        this.torn = torn;
        this.actiu = true;
    }
    
    public String getTorn() {
        return torn;
    }

    public void setTorn(String pTorn) {
        torn = pTorn;
    }

    public boolean getActiu() {
        return actiu;
    }

    public void setActiu(boolean pActiu) {
        actiu = pActiu;
    }

    /*
    TODO
     Paràmetres: cap
     Accions:
     - Demanar a l'usuari les dades per consola per crear un nou cambrer. Les dades
     a demanar són les que necessita el constructor.
     - També heu de tenir en compte que tant el nom com el torn, poden ser frases,
     per exemple, Francesc Xavier, o bé, matí i tarda.
     Retorn: El nou cambrer creat.
     */
    public static Cambrer addCambrer() {
        Scanner dades = new Scanner(System.in);
        String codiCambrer;
        String nomCambrer;
        String telefonCambrer;
        String tornCambrer;

        System.out.println("Codi del cambrer:");
        codiCambrer = dades.next();
        dades.nextLine(); //Neteja buffer
        System.out.println("Nom del cambrer:");
        nomCambrer = dades.nextLine();
        System.out.println("Telefon del cambrer:");
        telefonCambrer = dades.next();
        dades.nextLine(); //Neteja buffer
        System.out.println("Torn del cambrer:");
        tornCambrer = dades.nextLine();

        return new Cambrer(codiCambrer, nomCambrer, telefonCambrer, tornCambrer);
        
    }

    /*
     Paràmetres: cap
     Accions:
     - Demanar a l'usuari que introdueixi les noves dades de l'objecte actual
     i modificar els atributs corresponents d'aquest objecte.
     -Quan mostreu a l'usuari si està actiu o no, ho heu de fer mostrant-li els missatges
     "actiu" o "no actiu".
     -Quan es demani que s'introdueixi el nou estat (si està actiu o no) del cambrer, 
     se li ha d'indicar a l'usuari que introdueixi "1" o "0". Si introdueix 1, 
     el cambrer estarà en actiu i si introdueix 0, doncs no ho estarà. Si l'usuari 
     no introdueix un valor correcte, l'heu d'avisar i tornar a demanar que introdueixi
     l'estat del cambrer, així fins que introdueixi el valor correcte.
     - Li heu de mostrar a l'usuari el valor actual dels atributs de l'objecte
     actual, abans de modificar-los
     Retorn: cap
     */
    public void updateCambrer() {
        Scanner dades = new Scanner(System.in);
        boolean valorCorrecte = false;
        String estatCambrer;
        // Es podria fer els tres primers atributs(els heretats de ElementTaula) també amb super.updateElement(), aplicacant herència.
        //Ho deixo així per a que quedi més clar que és una cambrer.

        System.out.println("\nCodi del cambrer: " + super.getCodi());
        System.out.println("\nEntra el nou codi:");
        super.setCodi(dades.next());
        dades.nextLine(); //Neteja buffer
        System.out.println("\nNom del cambrer: " + super.getNom());
        System.out.println("\nEntra el nou nom:");
        super.setNom(dades.nextLine());
        System.out.println("\nTelefon del cambrer: " + super.getTelefon());
        System.out.println("\nEntra el nou telefon:");
        super.setTelefon(dades.next());
        System.out.println("\nTorn del cambrer: " + torn);
        System.out.println("\nEntra el nou torn:");
        torn = dades.nextLine();
        System.out.print("\nEstat del cambrer: ");
        if (actiu) {
            System.out.println("actiu");
        } else {
            System.out.println("no actiu");
        }
        while (!valorCorrecte) {
            System.out.println("\nEntra el nou estat(Escriu 1 o 0):");
            estatCambrer = dades.next();
            if (estatCambrer.equals("1")) {
                actiu = true;
                valorCorrecte = true;
            } else if (estatCambrer.equals("0")) {
                actiu = false;
                valorCorrecte = true;
            }
        }
    }

    public void showCambrer() {
        // Es podria fer els tres primers atributs (els heretats de ElementTaula) també amb super.updateElement(), aplicacant herència.
        //Ho deixo així per a que quedi més clar que és una cambrer.
        System.out.println("\nLes dades del cambrer amb codi " + super.getCodi() + " són:");
        System.out.println("\nNom:" + super.getNom());
        System.out.println("\nTelefon:" + super.getTelefon());
        System.out.println("\nTorn:" + torn);
        System.out.print("\nEstat: ");
        if (actiu) {
            System.out.println("actiu");
        } else {
            System.out.println("no actiu");
        }
    }
}
