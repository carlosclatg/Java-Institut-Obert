/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eac1.ex1;

import java.io.File;
import java.io.FileFilter;

/**
 *
 * @author Usuario
 */
public class Ex1 {
    private static File workFolder;
    private static char code;
    /**
     * Abstraction of uses of folders
     * 
     * @param {string} route
     * @param {string} code
     *
     * Returns a response based on the code received. It returns a response of files and folders
     * in the route specified, filtered according to the code provided.
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            if(args.length != 2) throw new Exception("error code 2 - incorrect arguments"); //nombre de paràmetres és diferent de dos.
            workFolder = new File(args[0]);
            
            if(!workFolder.exists()) throw new Exception("error code 2 - folder does not exist"); //Carpeta no existeix
            code = Character.toUpperCase(args[1].charAt(0));
            
            
            switch(code) {
                case 'C':
                    FileFilter filterC = (File file) -> {
                        return ((file.isDirectory()) && (!file.isHidden()));
                    };
                    File[] folders = workFolder.listFiles(filterC);
                    System.out.println("Non hidden folder: " + folders.length);
                    for (File f : folders) {
                        printFolder(f);
                    }
                    break;
                    
                case 'O':
                    FileFilter filterO = (File file) -> {
                        return ((file.isHidden())) ;
                    };
                    File[] filesandfolders = workFolder.listFiles(filterO);
                    System.out.println("Hidden elements: " + filesandfolders.length);
                    for(File f: filesandfolders){
                        if(f.isDirectory()){
                            printFolder(f);
                        } else {
                            printFile(f);
                        }
                    }
                    break;  
                    
                case 'F':
                    FileFilter filterF = (File file) -> {
                        return ((file.isFile()) && (!file.isHidden()));
                    };
                    File[] files = workFolder.listFiles(filterF);
                    System.out.println("Non hidden file: " + files.length);
                    for (File f: files) {
                        printFile(f);
                    }
                    break;
  
                default: throw new Exception("error code 2 -not a valid code"); //Cas no C,F,O
            }
        }catch( Exception ex){
            ex.printStackTrace(System.err);
        }    
    }

    private static void printFile(File file) { //Imprimir fitxers
        String read = (file.canRead()) ? "r":"-";
        String write = (file.canWrite()) ? "w":"-";
        String execute = (file.canExecute()) ? "x":"-";
        System.out.println(">> " + file.getName() + " " + read + "" + write + "" + execute + "");
    }
    
    private static void printFolder(File directory) { //imprimir carpeta
        long size = folderSize(directory);
        System.out.println("-- " + directory.getName() + " " + size + " bytes");
    } 

    
    public static long folderSize(File directory) { //Calculem de manera recursiva el tamany, per si hi ha carpetes que contenen fitxers.
        try{
            long length = 0;
            for (File file : directory.listFiles()) {
                if (file.isFile())
                    length += file.length();
                else
                    length += folderSize(file); 
            }
            return length;
        } catch (Exception e){ //Per si listFiles torna null.
            return 0;
        }
        
    }

    
    

 
}
    

