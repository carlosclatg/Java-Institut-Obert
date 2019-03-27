/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m9.uf3.eac2.b2;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 * Programa de prova del CambridgeDictionaryClient
 * curs 18/19
 * 
 */
public class DictionaryTest {
    
    public static void main(String[] args) throws UnsupportedEncodingException {
        
        String[] names = {"lettuce", "apple", "banana", "mother", "wewewewew"};
        
        for (String e: names){
            CambridgeDictionaryClient c = new CambridgeDictionaryClient();
            String traduccio = c.translate(e);
            if(traduccio != null){
                System.out.println("La traduccio de " + e +" és " + traduccio +"");
            } else {
                System.out.println("No s'ha trobat traducció per " + e +"");
            }
            
        }
                
        
    
    }
}
