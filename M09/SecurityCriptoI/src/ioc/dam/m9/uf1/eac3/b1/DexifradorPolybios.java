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
public class DexifradorPolybios {
 
    String taulaPolybios[][] = {
        {"A", "B", "C", "D", "E"},
        {"F", "G", "H", "[IJ]", "K"},
        {"L", "M", "N", "O", "P"},
        {"Q", "R", "S", "T", "U"},
        {"V", "W", "X", "Y", "Z"}
    };
 
    public String getTextClarDeLletres(String textXifrat) {
        
        int indexX = (int) textXifrat.charAt(0) % 5;
        int indexY = (int) textXifrat.charAt(1) % 5;
        
        return this.taulaPolybios[indexX][indexY];
    }
   
    public String getTextClarDeNumeros(String textXifrat) {
        int indexX =  - 1 + (int) (textXifrat.charAt(0) - '0');
        int indexY =  - 1 + (int) (textXifrat.charAt(1) - '0');
        
        return this.taulaPolybios[indexX][indexY];
    }    
}