/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m9.uf1.eac3.b2;

import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

public class BruteForce {
    /*
       * 
       Aquest projecte et dona totes les claus de l'algorisme Cesar
    
    
    Si volem xifrar amb una clau = 6, simplement desplaçariem la nostra lletra 6
    posicions en l'alfaber a -> seria la g. Per desencriptar seria el procès invers
    exemple 1:
       para la clau 13 : 
       A B C D E F G H I J K L M --> 13 LLETRES 
       N O P Q R S T U V W X Y Z
      *Si volem desxifrar hola amb una clau 13 ens quedaría així
                H O L A
                U B Y N
    */

    private Character[] alfabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private char[] textDecodificat;
    private String[] resultat;
    private List<Character> ListAlfabet; // llista de caracters amb l'objecte Character 

    public BruteForce(){
        //asList torna un tipus List quant se l'invoca passant un array com a parámetre 
        
        ListAlfabet = Arrays.asList(alfabet);
        resultat = new String[alfabet.length];
    }

    public String[] BusquedaDesxifrat(String TextXifrat) {
        // .toCharArray -->     converteix un String amb un array de chars
        //Converteix totes les lletres en majuscules i les guarda en un array de chars
        TextXifrat = TextXifrat.toUpperCase();
        textDecodificat = TextXifrat.toCharArray();
        int[] arrayindex = new int[TextXifrat.length()];
        for (int i = 0; i < TextXifrat.length(); i ++){ //Guardo en un array les posicions de la paraula inicial
            arrayindex[i] = trobarIndexCaracter(textDecodificat[i]);
        }
        
        for (int n = 0; n < alfabet.length ; n++ ){
            String paraula = "";
            for (int i = 0; i < TextXifrat.length() ;i++){
               int j = arrayindex[i];
               if(j + n >= alfabet.length){
                   j = j + n - alfabet.length;
               } else {
                   j = j + n;
               }
               paraula = paraula.concat(alfabet[j].toString()); 
            }
            resultat[n] = paraula;
        } 
        return resultat;        
    }

    private int trobarIndexCaracter(char c){
        int i = -1;
        boolean trobat = false;
        while (!trobat){
            i ++;
            trobat = (c == alfabet[i]);
        }
        return i;
    }

    
}