/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//args >> /home/profe/proves t
//args >> /home/profe/proves 4
package eac1.ex1;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author professor
 */
public class Exercici1 {
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    
    private static int comptaDir, comptaFitx;
    
    public Exercici1 () {
            Exercici1.comptaDir = 0;
            Exercici1.comptaFitx = 0;
    }
  
    public static void main(String[] args) throws IOException {
                
        File uri; //Carpeta que llistarem
        String imbricacio = null; //Nivell de profunditat que es vol mostrar
        int aux=0;
        
        //Primer argument de la línia d'ordres
        uri = new File(args[0]);
        
        //Segon argument de la línia d'ordres
        imbricacio = args[1];
        
         
        if(args.length!=2){
            
            finalitzar("Nombre d'arguments erronis");
            
        } else if (!uri.isDirectory()) {
            
            finalitzar("El promer paràmetre és erroni");
            
        } else {//Parametres correctes, és un directori
            
            //Mostra l'arbre del sistema de fitxers que penja d'uri fins al més profund
            if (imbricacio.toUpperCase().equalsIgnoreCase("T")){
                
                infoMostra(uri, Integer.MAX_VALUE);          

            //Mostra tot l'arbre del sistema de fitxers que penja d'uri fins a una profunditat màxima indicada   
            } else {
                try{
                    aux = Integer.parseInt(imbricacio);
                }catch(NumberFormatException ex){
                    finalitzar("El segon paràmetre és erroni");
                }
                infoMostra(uri, aux);
                
            }
            
        }
        
    }


  public static void infoMostra(File arxiu, int prof) {
    System.out.println(arxiu);
    recorrer(arxiu, "", prof);
    System.out.println("\n" + comptaDir + " directoris, " + comptaFitx + " arxius");
  }

  
  private static void registra(File arxiu) {
    if (arxiu.isDirectory()) {
      comptaDir++;
    } else {
      comptaFitx++;
    }
  }
  
  
  private static void recorrer(File node, String prefixe, int profunditat) {
    
    //Lambda expression - filtre per comprovar arxius no ocults amb permís de lectura
    FileFilter filter = (File arxiuSeleccionat) -> {
        //Arxiu és un arxiu no ocult amb permís de lectura
        return (arxiuSeleccionat.canRead()&&!arxiuSeleccionat.isHidden());//
    };
    
    File arxiu;
    File[] llistatFitxers = node.listFiles(filter);
    Arrays.sort(llistatFitxers);
    
    if (profunditat>0) {
        profunditat--;
        for (int ref = 0; ref < llistatFitxers.length; ref++) {
            arxiu = llistatFitxers[ref];
            
            registra(arxiu);
            
            if (ref == llistatFitxers.length - 1) {
                System.out.println(prefixe + "|__ " + arxiu.getName());
                if (arxiu.isDirectory()) {
                    recorrer(arxiu, prefixe + "    ", profunditat);
                }
            } else {
                System.out.println(prefixe + "|-- " + arxiu.getName());
                if (arxiu.isDirectory()) {
                    recorrer(arxiu, prefixe + "|  ", profunditat);
                }
            }
        }
    }
    
  }
  
   
    
    //System.err amb codi d'error 2
    private static void finalitzar(String missatge) {
        System.err.println(missatge);
        System.exit(2);
    }
    
}
