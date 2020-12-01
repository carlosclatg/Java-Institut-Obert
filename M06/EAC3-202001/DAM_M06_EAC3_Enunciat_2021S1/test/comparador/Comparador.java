/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package comparador;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 * Utilitats per a a comparar objectes
 * @author professor
 */
public class Comparador {
    
   /**
    * compara dos objectes fins a un determinat nivell d'imbricació
    * @param a primer objecte a comparar
    * @param b segon objecte a comparar
    * @param nivell nivell d'imbricació
    * @return resultat de la comparació (true: son iguals; false: no ho son)
    */
   public static boolean compara(Object a, Object b, int nivell){
       Class classe;
       
       if(a==null || b==null){
           return(a==b);
       }
       
       if (nivell==0)  { return true;}
       
       classe=a.getClass();
       
       if(classe!=b.getClass()) {return false;}
       
       Method metodes[]=classe.getMethods();
       
       
       if(comparableAmbEquals(classe.getSimpleName())){ return a.equals(b);}
       
       for(Method m:metodes){
           Class retorn=m.getReturnType();
           if(Modifier.toString(m.getModifiers()).contains("public")
                   && m.getName().matches("get[^az].*")
                   && m.getParameterCount()==0
                   && retorn!=void.class){ // es un getter
               
                    if(comparableAmbEquals(retorn.getSimpleName())){
                        boolean iguals=false;
                        try {
                             iguals=m.invoke(a).equals(m.invoke(b));
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                            System.err.println(ex.getMessage());
                        }
                        if(!iguals) return false;
                    }else{
                        if(nivell!=0){
                          try{
                            Object resultatA = m.invoke(a);
                            Object resultatB = m.invoke(b);
                      
                            if(resultatA instanceof List && resultatB instanceof List){
                                if(!comparaLlistes(resultatA,resultatB,nivell-1)){ return false;}
                            }
                            
                            if(!compara(resultatA, resultatB, nivell - 1)) {return false;}
                          } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                            System.err.println(ex.getMessage());
                          }      
                          }
                        }
                            
                    }
            }
            return true;
       } // for

    /**
     * compara dues llistes; son igual si totes dues tenen objectes iguals dos a dos encara que l'ordre sigui diferent a cadascuna de les llstes
     * @param a primera llista a comparar
     * @param b segona llista a comparar
     * @param nivell nivell d'imbricació a la comparació dels objectes de les llistes
     * @return  resultat de la comparació: cert si les dues llistes es consideren iguals, fals en cas contrari
     */
    public static boolean comparaLlistes(Object a, Object b, int nivell) {
        if(nivell==0){ return true;}
        
        List llistaA= (List)a , llistaB=(List)b;
        
        if(llistaA.size()!=llistaB.size()) {return false;}
        
        for(Object o:llistaA){
            if(!conte(llistaB,o,nivell)) {return false;}
        }    
        for(Object o:llistaB){
            if(!conte(llistaA,o,nivell)) {return false;}
        } 
        
        return true;
    }

    public static boolean conte(List llista, Object element, int nivell) {
        for(Object o:llista){
            if(compara(o,element,nivell)) {return true;}
        }
        return false;
    }

    
    
    private static boolean comparableAmbEquals(String tipus) {
        return "Class Boolean Byte Short Integer Long Character Float Double String boolean byte short int long char float double ".contains(tipus+" ");
    }
    

    }

