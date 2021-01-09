/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaAplicacio.gestors;

import logicaAplicacio.model.Institut;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Classe per abstreure diferents accions sobre els objectes de tipus Institut
 * @author professor
 */
public class GestorInstitut {

    private EntityManager em = null;

    /**
     * Constructor que associa el gestor a un EntityManager
     * @param em EntityManager al qual s'associa el gestor
     */
    public GestorInstitut(EntityManager em) {
        this.em = em;
    }
    
     /**
     * Consultar tots els instituts de la base de dades
     * @return Llista amb tots els instituts de la base de dades
     */
    public List<Institut> obtenirInstituts() {
       Query q = em.createNamedQuery("Institut.tots");

       List<Institut> resultat=q.getResultList();
       for(Institut b:resultat){
           em.detach(b);
       }
       return resultat;
    }
    
       /**
     * Esborra l'institut de la base de dades que te determinat codi
     * @param idInsti codi de l'institut a esborrar
     * @throws logicaAplicacio.gestors.GestorException si el codi no correspon a cap institut de la base de dades
     * o hi ha un error en aquesta
     */
    public void eliminar(int idInsti) throws GestorException {
        Institut base=em.find(Institut.class, idInsti);
        if(base!=null){
            em.getTransaction().begin();
            em.remove(base);
            em.getTransaction().commit();
        }
        else {
            throw new GestorException("Clau inexistent");
        } 
    }
    
    /**
     * Obte l'institut de la base de dades amb un codi determinat
     * @param idInsti codi de  l'institut a obtenir
     * @return  institut o null en cas de no haver-hi cap institut persistent amb aquest codi
     */
    public Institut obtenirInstitut(int idInsti) {
        Institut base=em.find(Institut.class, idInsti);
        if(base!=null){
            em.detach(base);
            return base;
        }else {
            return null;
        }

    }
    
    /**
     * Dona d'alta un institut en la base de dades. Si ja n'hi ha algun amb el seu mateix codi, llenca una excepcio.
     * @param insti Institut a inserir
     * @throws logicaAplicacio.gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau duplicada.
     */
    public void inserir(Institut insti) throws GestorException {
        if(em.find(Institut.class, insti.getCodi())==null){
            em.getTransaction().begin();
            em.merge(insti);
            em.getTransaction().commit();
        }
        else {
            throw new GestorException("Clau duplicada");
        }

    }
    
    /**
     * Modifica un institut de la base de dades. Si no n'hi ha cap amb el seu codi, llenca una excepcio.
     * @param insti Institut a actualitzar
     * @throws logicaAplicacio.gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau inexistent.
    */
    public void modificar(Institut insti) throws GestorException{
        if(em.find(Institut.class, insti.getCodi())!=null){
            em.getTransaction().begin();
            em.merge(insti);
            em.getTransaction().commit();
        }
        else {
            throw new GestorException("Clau inexistent");
        }
    }
    
    
}
