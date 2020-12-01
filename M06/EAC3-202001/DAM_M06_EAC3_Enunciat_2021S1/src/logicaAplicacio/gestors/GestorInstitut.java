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
        return em.createNamedQuery("Institut.getAllInstituts").getResultList();
    }
    
       /**
     * Esborra l'institut de la base de dades que te determinat codi
     * @param idInsti codi de l'institut a esborrar
     * @throws logicaAplicacio.gestors.GestorException si el codi no correspon a cap institut de la base de dades
     * o hi ha un error en aquesta
     */
    public void eliminar(int idInsti) throws GestorException {
        if(obtenirInstitut(idInsti) == null) throw new GestorException("Not existing Institut");
        Query q = em.createNamedQuery("Institut.deleteById");
        q.setParameter("id", idInsti);
    }
    
    /**
     * Obte l'institut de la base de dades amb un codi determinat
     * @param idInsti codi de  l'institut a obtenir
     * @return  institut o null en cas de no haver-hi cap institut persistent amb aquest codi
     */
    public Institut obtenirInstitut(int idInsti) {
        Query q = em.createNamedQuery("Institut.getById");
        return (Institut) q.setParameter("id", idInsti).getSingleResult();
    }
    
    /**
     * Dona d'alta un institut en la base de dades. Si ja n'hi ha algun amb el seu mateix codi, llenca una excepcio.
     * @param insti Institut a inserir
     * @throws logicaAplicacio.gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau duplicada.
     */
    public void inserir(Institut insti) throws GestorException {
        if(obtenirInstitut(insti.getCodi()) != null) throw new GestorException("Already existing Institut");
        em.createNativeQuery("INSERT INTO Institut (codi, nom, ciutat) VALUES (?,?,?)")
                .setParameter(1, insti.getCodi())
                .setParameter(2, insti.getNom())
                .setParameter(3, insti.getCiutat())
                .executeUpdate();
    }
    
    /**
     * Modifica un institut de la base de dades. Si no n'hi ha cap amb el seu codi, llenca una excepcio.
     * @param insti Institut a actualitzar
     * @throws logicaAplicacio.gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau inexistent.
    */
    public void modificar(Institut insti) throws GestorException{
        if(obtenirInstitut(insti.getCodi()) == null) throw new GestorException("Non Existing Institut");

        Institut institut = em.find(Institut.class, insti.getCodi());
        em.getTransaction().begin();
        institut.setCiutat(insti.getCiutat());
        institut.setNom(insti.getNom());
        institut.setProfessors(insti.getProfessors());
        em.getTransaction().commit();
    }
    
    
}
