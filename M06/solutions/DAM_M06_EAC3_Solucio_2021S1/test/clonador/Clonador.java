/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clonador;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 * Utilitats per a clonar objectes que tenen propietats
 * @author professor
 */
public class Clonador {
    
    /**
     * Clona un objecte fins a un determinat nivell d'imbricacio
     * @param o objecte a clonar
     * @param nivell nivell d'imbricacio
     * @return copia de l'objecte original
     */
    
   public static Object clona(Object o, int nivell) {
       try{
           Class classe;
           Object resultat;

           if(nivell==0){
               return null;
           }

           classe=o.getClass();

           resultat=classe.getConstructor().newInstance();

           Method metodes[]=classe.getMethods();

           for(Method m:metodes){
               Class retorn=m.getReturnType();
               if(Modifier.toString(m.getModifiers()).contains("public")
                       && m.getName().matches("get[^az].*")
                       && !m.getName().equals("getClass")
                       && m.getParameterCount()==0
                       && retorn!=void.class){ // es un getter

                       Object valor = m.invoke(o);




                       if(valor instanceof List){
                           List llistaDesti=(List)m.invoke(resultat);

                           copiaLlista(llistaDesti,(List)valor, nivell - 1);

                       }

                       else {
                           String nomSetter= "set"+(m.getName().substring(3));
                           Method setter=classe.getMethod(nomSetter, retorn);


                           if(valor==null || esAssignable(valor.getClass().getSimpleName())){

                                setter.invoke(resultat, valor);
                           }
                           else {
                               setter.invoke(resultat, clona(valor, nivell - 1));
                           }

                       }
               }
           } // for
           return resultat;
       }catch(NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException |  InvocationTargetException e){
           System.err.println(e.getMessage());
           return null;  
       }
    }
   /**
    *  Omple la llista desti amb una copia (clon) de cadascun dels objectes de la primera llista; el clonatge dels objectes es fa fins a un determinat nivell.
     * @param desti llista on s'afegeixen els objectes clonats
     * @param l llista amb els objectes originals; no es modifica
     * @param nivell nivell d'imbriació del clonatge dels objectes.
    */
    public static void copiaLlista(List desti, List l, int nivell)  {
        try{
            if(nivell==0){ return; }

            for(Object ob:l){
                desti.add(clona(ob,nivell));
            }
        }catch(IllegalArgumentException e){
            System.err.println(e.getMessage());  
       }
    }

    private static boolean esAssignable(String tipus) {
        return "Class Boolean Byte Short Integer Long Character Float Double String boolean byte short int long char float double ".contains(tipus+" ");
    }
    

    }

