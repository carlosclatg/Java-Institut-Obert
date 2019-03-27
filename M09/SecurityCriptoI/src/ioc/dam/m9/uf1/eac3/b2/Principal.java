/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m9.uf1.eac3.b2;

import javax.swing.JOptionPane;

/**
 *
 * @author Usuari
 */
public class Principal {
    
    public static void main(String[] args) {
        BruteForce t = new BruteForce();
        String paraula = JOptionPane.showInputDialog("Entrar la paraula a encriptar");
        
//fer un bucle que anirà imprimint totes les posibles solucions del xifrat de cesar         
        String[] arrayParaules = t.BusquedaDesxifrat(paraula);
        
        for(String s: arrayParaules){
            System.out.println(s);
        }
    }
    
    
    
}
