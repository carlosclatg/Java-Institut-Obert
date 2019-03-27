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
public class Polybios {
 
    XifratPolybios xifradorPolybios = new XifratPolybios();
    DexifradorPolybios desxifradorPolybios = new DexifradorPolybios();
 
    public String xifrarTextClarEnLletres(String textClar) {
        String textXifrat = "";
        int i = 0;
        while( i < textClar.length()){
            if(textClar.charAt(i) == ' '){ //Cas espai
                textXifrat =  textXifrat.concat(" ");
            } else {
                textXifrat = textXifrat.concat(xifradorPolybios.getTextXifratEnLletres(textClar.charAt(i)));    
            } 
            
            i++;
        }
        return textXifrat;
    }
 
    public String desxifrarTextXifratDeLletres(String textXifrat) {
        String textDesxifrat= "";
        int i = 0;
        while (i < textXifrat.length()){
            if(textXifrat.charAt(i) == ' '){//Cas espai
                i ++;
                textDesxifrat = textDesxifrat.concat(" ");
            } else {
                String nextCode = "";
                nextCode = nextCode.concat(textXifrat.substring(i, i+2));
                textDesxifrat = textDesxifrat.concat(desxifradorPolybios.getTextClarDeLletres(nextCode));
                i = i + 2;
            }
        }
        
        return textDesxifrat;
        
    }
    //Per NÚMEROS
 
    public String xifrarTextClarEnNumeros(String textClar) {
        String textXifrat = "";
        int i = 0;
        while( i < textClar.length()){
            if(textClar.charAt(i) == ' '){//Cas espai
                textXifrat =  textXifrat.concat(" ");
            } else {
                textXifrat = textXifrat.concat(xifradorPolybios.getTextXifratEnNumeros(textClar.charAt(i)));    
            } 
            
            i++;
        }
        return textXifrat;
    }
 
    public String desxifrarTextXifratDeNumeros(String textXifrat) {
        String textDesxifrat= "";
        int i = 0;
        while (i < textXifrat.length()){
            if(textXifrat.charAt(i) == ' '){
                i ++;
                textDesxifrat = textDesxifrat.concat(" ");
            } else {
                String nextCode = "";
                nextCode = nextCode.concat(textXifrat.substring(i, i+2));
                textDesxifrat = textDesxifrat.concat(desxifradorPolybios.getTextClarDeNumeros(nextCode));
                i = i + 2;
            }
        }
        
        return textDesxifrat;
    }
}