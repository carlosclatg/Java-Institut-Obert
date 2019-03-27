package elementsMobils;


import java.util.Scanner;

/*
 * Classe que defineix una reserva. Una reserva es defineix pel seu codi, nom i 
 * telefon del client, total de comensals, data i hora.
 */
/**
 *
 * @author fta
 */
public class Reserva extends ElementTaula{

    private int totalComensals;
    private String data;
    private String hora;

    /*
     TODO CONSTRUCTOR
     Paràmetres: valors per tots els atributs de la classe.
     Accions:
     - Assignar als atributs els valors passats com a paràmetres
     */
    public Reserva (String codi, String nom, String telefon, int totalComensals, String data, String hora) {
        super(codi,nom,telefon);
        this.totalComensals = totalComensals;
        this.data = data;
        this.hora = hora;
    }

    /*
     TODO Mètodes accessors    
     */
   
    public int getComensals() {
        return totalComensals;
    }

    public void setComensals(int pComensals) {
        totalComensals = pComensals;
    }

    public String getData() {
        return data;
    }

    public void setData(String pData) {
        data = pData;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String pHora) {
        hora = pHora;
    }

    /*
    TODO
     Paràmetres: cap
     Accions:
     - Demanar a l'usuari les dades per consola per crear una nova reserva. Les dades
     a demanar són les que necessita el constructor.
     - La data s'ha de demanar en format "dd/mm/aa" i l'hora "hh:mm"
     - També heu de tenir en compte que tant el nom com el torn, poden ser frases,
     per exemple, Francesc Xavier, o bé, matí i tarda.
     Retorn: La nova reserva creada.
     */
    public static Reserva addReserva() {
        Scanner dades = new Scanner(System.in);
        String codiReserva;
        String nomClient;
        String telefonClient;
        int totalComensalsReserva;
        String dataReserva;
        String horaReserva;

        System.out.println("Codi de la reserva:");
        codiReserva = dades.next();
        dades.nextLine(); //Neteja buffer
        System.out.println("Nom del client:");
        nomClient = dades.nextLine();
        System.out.println("Telefon del client:");
        telefonClient = dades.next();
        System.out.println("Total de comensals:");
        totalComensalsReserva = dades.nextInt();
        System.out.println("Data de la reserva (dd/mm/aa):");
        dataReserva = dades.next();
        System.out.println("Hora de la reserva (hh:mm):");
        horaReserva = dades.next();

        return new Reserva(codiReserva, nomClient, telefonClient, totalComensalsReserva, dataReserva, horaReserva);
    }

    /*
    TODO
     Paràmetres: cap
     Accions:
     - Demanar a l'usuari que introdueixi les noves dades de l'objecte actual
     i modificar els atributs corresponents d'aquest objecte.     
     - Li heu de mostrar a l'usuari el valor actual dels atributs de l'objecte
     actual, abans de modificar-los.
     Retorn: cap
     */
    public void updateReserva() {
        Scanner dades = new Scanner(System.in);
        System.out.println("\nCodi de la reserva: " + super.getCodi());
        System.out.println("\nEntra el nou codi:");
        super.setCodi(dades.next());
        dades.nextLine(); //Neteja buffer
        System.out.println("\nNom de la reserva: " + super.getNom());
        System.out.println("\nEntra el nou nom:");
        super.setNom(dades.nextLine());
        System.out.println("\nTelefon del client: " + super.getTelefon());
        System.out.println("\nEntra el nou telefon:");
        super.setTelefon(dades.next());
        System.out.println("\nTotal comensals: " + totalComensals); // No es necessari possar-ho amb mètodes accesors pq estem dins de la classe.
        System.out.println("\nEntra el nou nombre de comensals:");
        totalComensals = dades.nextInt();
        System.out.println("\nData de la reserva: " + data);
        System.out.println("\nEntra la nova data (dd/mm/aa):");
        data = dades.next();
        System.out.println("\nHora de la reserva: " + hora);
        System.out.println("\nEntra la nova hora (hh:mm):");
        hora = dades.next();
    }

    public void showReserva() {
        // Es podria fer els tres primers atributs(els heretats de ElementTaula) també amb super.showElement(), aplicant herència.
        //Ho deixo així per a que quedi més clar que és una reserva.
        System.out.println("\nLes dades de la reserva amb codi " + super.getCodi() + " són:");
        System.out.println("\nNom:" + super.getNom());
        System.out.println("\nTelèfon:" + super.getTelefon());
        System.out.println("\nTotal comensals:" + totalComensals);
        System.out.println("\nData:" + data);
        System.out.println("\nHora:" + hora);
    }
}
