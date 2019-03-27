package model;

import java.util.Scanner;

/**
 *
 * @author fta
 */
public class Reserva extends ElementTaula {

    private int totalComensals;
    private String data;
    private String hora;

    public Reserva(String pCodi, String pNom, String pTelefon, int pTotalComensals, String pData, String pHora) {
        super(pCodi, pNom, pTelefon);
        totalComensals = pTotalComensals;
        data = pData;
        hora = pHora;
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

    public void updateElement() {
        Scanner dades = new Scanner(System.in);
        
        super.updateElement();
        
        System.out.println("\nTotal comensals: " + totalComensals);
        System.out.println("\nEntra el nou nombre de comensals:");
        totalComensals = dades.nextInt();
        System.out.println("\nData de la reserva: " + data);
        System.out.println("\nEntra la nova data (dd/mm/aa):");
        data = dades.next();
        System.out.println("\nHora de la reserva: " + hora);
        System.out.println("\nEntra la nova hora (hh:mm):");
        hora = dades.next();
    }

    public void showElement() {
        
        super.showElement();
        
        System.out.println("\nTotal comensals:" + totalComensals);
        System.out.println("\nData:" + data);
        System.out.println("\nHora:" + hora);
    }
}
