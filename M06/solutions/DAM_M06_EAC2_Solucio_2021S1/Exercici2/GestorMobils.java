/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestors;

import javax.persistence.EntityManager;
import model.TelefonIntelligent;

/**
 * Classe que gestiona la persistencia dels objectes de la classe model.TelefonIntelligent
 * @author professor
 */
public class GestorMobils {
    
    
    private EntityManager telefon = null;

    
    /**
     * Crea un gestor de telèfons intel·ligents que treballara amb l'EntityManager TelefonIntelligent
     * @param dispositiu context on es fan persistents dels telèfons intel·ligents
     */
    public GestorMobils(EntityManager dispositiu) {
        
       this.telefon = dispositiu;
       
    }
    
    
    /**
     * Dona d'alta un telèfon intel·ligent en la base de dades. Si ja n'hi ha alguna amb el seu mateix referència, llenca una excepcio.
     * @param fitxa fitxa del telèfon intel·ligent a donar d'alta
     * @throws gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau duplicada.
     */
    public void inserir(TelefonIntelligent fitxa) throws GestorException {
        
        if(telefon.find(TelefonIntelligent.class, fitxa.getRef())==null){
            
            telefon.getTransaction().begin();
            telefon.merge(fitxa);
            telefon.getTransaction().commit();
        
        } else {
            
            throw new GestorException("Referència duplicada");
        }
        
    }

    
    /**
     * Modifica la fitxa d'un telèfon intel·ligent de la base de dades. Si no n'hi ha cap amb la seva referència, llenca una excepcio.
     * @param fitxa fitxa del telèfon intel·ligent actualitzat
     * @throws gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau duplicada.
     */
    public void modificar(TelefonIntelligent fitxa) throws GestorException {
        
        if(telefon.find(TelefonIntelligent.class,fitxa.getRef())!=null){
            
            telefon.getTransaction().begin();
            telefon.merge(fitxa);
            telefon.getTransaction().commit();
            
        } else {
            
            throw new GestorException("Referència inexistent");
            
        }
    }    
    
    
    /**
     * Esborra la fitxa d'un telèfon intel·ligent amb una determinada referència
     * @param codi codi del telèfon intel·ligent a esborrar
     * @throws gestors.GestorException si la referència no correspon a cap telèfon intel·ligent de la base de dades
     */
    public void eliminar(int codi) throws GestorException {

        TelefonIntelligent treuRef;
        treuRef = telefon.find(TelefonIntelligent.class, codi);
        
        if(treuRef!=null){
            
            telefon.getTransaction().begin();
            telefon.remove(treuRef);
            telefon.getTransaction().commit();
        } else {
            
            throw new GestorException("Referència inexistent");
            
        }        

    }

   
    /**
     * Obte el telèfon intel·ligent de la base de dades amb una referència determinada
     * @param refMobil referència del telèfon intel·ligent a obtenir
     * @return fitxa del telèfon intel·ligent amb refMobil o null si no hi ha cap telèfon intel·ligent a la base de dades amb aquesta referència
     */
    public TelefonIntelligent obtenirMobil(int refMobil) {

        TelefonIntelligent codi=telefon.find(TelefonIntelligent.class, refMobil);
        if(codi!=null){
            telefon.detach(codi);
            return codi;
        }else {
            return null;
        }
    }
    
 
}
