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
/**
 *
 * 
 */
public class XifratPolybios {
 
    String taulaPolybiosLletres[][] = {
        {"AA", "AB", "AC", "AD", "AE"},
        {"BA", "BB", "BC", "BD", "BE"},
        {"CA", "CB", "CC", "CD", "CE"},
        {"DA", "DB", "DC", "DD", "DE"},
        {"EA", "EB", "EC", "ED", "EE"}
    };
    String taulaPolybiosNumeros[][] = {
        {"11", "12", "13", "14", "15"},
        {"21", "22", "23", "24", "25"},
        {"31", "32", "33", "34", "35"},
        {"41", "42", "43", "44", "45"},
        {"51", "52", "53", "54", "55"}
    };
 
    public String getTextXifratEnLletres(char parTextClar) {
        String parTextXifrat = "";
        int codiAscciiParTextClar = (int) (parTextClar);
        if (codiAscciiParTextClar != 32) {
            if (codiAscciiParTextClar > 73) {
                codiAscciiParTextClar -= 1;
            }
 
            int indexX = (codiAscciiParTextClar - 65) / 5;
            int indexY = (codiAscciiParTextClar % 5);
 
            parTextXifrat = taulaPolybiosLletres[indexX][indexY];
        } else {
            parTextXifrat = " ";
        }
 
        return parTextXifrat;
    }
 
    public String getTextXifratEnNumeros(char parTextClar) {
        String parTextXifrat = "";
        int codiAscciiParTextClar = (int) (parTextClar);
        if (codiAscciiParTextClar != 32) {
            if (codiAscciiParTextClar > 73) {
                codiAscciiParTextClar -= 1;
            }
 
            int indexX = (codiAscciiParTextClar - 65) / 5;
            int indexY = (codiAscciiParTextClar % 5);
 
            parTextXifrat = taulaPolybiosNumeros[indexX][indexY];
        } else {
            parTextXifrat = " ";
        }
 
        return parTextXifrat;
    }
}
