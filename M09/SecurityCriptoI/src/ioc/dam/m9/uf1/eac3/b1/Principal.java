/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m9.uf1.eac3.b1;

/**
 *
 * @author Usuari
 */
public class Principal {
public static final String ANSI_RESET = "\u001B[0m";
public static final String ANSI_BLACK = "\u001B[30m";
public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {
        
        
        //PART 1 - AMB LLETRES
        Polybios polybios = new Polybios();
        String textClar = "Programacio de serveis i processos";
        textClar = textClar.toUpperCase();
        
        System.out.println(textClar);
        System.out.println(ANSI_RED + "Text clar xifrat" + ANSI_RESET);
        String textXifrat;
        textXifrat = polybios.xifrarTextClarEnLletres(textClar);
        System.out.println(textXifrat);
        System.out.println(ANSI_RED + "Ara desxifrem un text xifrat, de lletres" + ANSI_RESET);
        String textDesxifrat;
        textDesxifrat = polybios.desxifrarTextXifratDeLletres(textXifrat);
        System.out.println(textDesxifrat);
        
        System.out.println("\n----------------------"
                + "\n----------------------\n ");
        
        //AMB NUMEROS
        String ntextClar = "Programacio de serveis i processos";
        ntextClar = ntextClar.toUpperCase();
        System.out.println(ntextClar);
        System.out.println(ANSI_RED + "Text clar xifrat en numeros" + ANSI_RESET);
        String ntextXifrat;
        ntextXifrat = polybios.xifrarTextClarEnNumeros(ntextClar);
        System.out.println(ntextXifrat);
        System.out.println(ANSI_RED + "Ara desxifrem un text xifrat, de numeros" + ANSI_RESET);
        String ntextDesxifrat;
        ntextDesxifrat = polybios.desxifrarTextXifratDeNumeros(ntextXifrat);
        System.out.println(ntextDesxifrat);
        
        
        
    }
}