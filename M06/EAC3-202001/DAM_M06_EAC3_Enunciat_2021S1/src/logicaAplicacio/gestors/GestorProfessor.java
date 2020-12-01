/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logicaAplicacio.gestors;

import logicaAplicacio.model.Institut;
import logicaAplicacio.model.Professor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Classe per abstreure diferents accions sobre els objectes de tipus Professor
 * @author professor
 */
public class GestorProfessor {

    private EntityManager em = null;

    /**
     * Constructor que associa el gestor a un EntityManager
     * @param em EntityManager al qual s'associa el gestor
     */
    public GestorProfessor(EntityManager em) {
        this.em = em;
    }

    /**
     * Consultar tots els professor de la base de dades
     * @return Llista amb tots els professor de la base de dades
     */
    public List<Professor> obtenirProfessors() {
        return em.createNamedQuery("Professor.getAll").getResultList();

    }
     /**
     * Esborra el professor de la base de dades que te un determinat codi
     * @param idProfe codi que identifica el professor a esborrar
     * @throws logicaAplicacio.gestors.GestorException si el codi no correspon a cap professor de la base de dades
     * o hi ha un error en aquesta
     */
    public void eliminar(int idProfe) throws GestorException {
        if(obtenirProfessor(idProfe) == null) throw new GestorException("Not existing Professor");
        Query q = em.createNamedQuery("Professor.deleteById");
        q.setParameter("id", idProfe);
   
    }

    /**
     * Obte el professor de la base de dades amb un codi determinat
     * @param idProfe codi del professor a obtenir
     * @return professor o null en cas de no haver-hi cap professor persistent amb aquest codi
     */
    public Professor obtenirProfessor(int idProfe) {
        Query q = em.createNamedQuery("Professor.getById");
        return (Professor) q.setParameter("id", idProfe).getSingleResult();
    }

    /**
     * Dona d'alta un professor en la base de dades. Si ja n'hi ha algun amb el seu mateix codi, llenca una excepcio.
     * @param profe professor a inserir
     * @throws logicaAplicacio.gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau duplicada.
     */
    public void inserir(Professor profe) throws GestorException {
        if(obtenirProfessor(profe.getIdProfe()) != null) throw new GestorException("Already existing Institut");
        em.createNativeQuery("INSERT INTO Professor (idProfe, nom, salari) VALUES (?,?,?)")
                .setParameter(1, profe.getIdProfe())
                .setParameter(2, profe.getNom())
                .setParameter(3, profe.getSalari())
                .executeUpdate();

    }

   /**
     * Modifica un professor de la base de dades. Si no n'hi ha cap amb el seu codi, llenca una excepcio.
     * @param profe professor a actualitzar
     * @throws logicaAplicacio.gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau inexistent.
    */
    public void modificar(Professor profe) throws GestorException{
        if(obtenirProfessor(profe.getIdProfe()) == null) throw new GestorException("Non Existing Professor");

        Professor p = em.find(Professor.class, profe.getIdProfe());
        em.getTransaction().begin();
        p.setNom(profe.getNom());
        p.setSalari(profe.getSalari());
        p.setInstitut(profe.getInstitut());
        em.getTransaction().commit();
    }
    
   /**
     * Incrementa el salari de tots els professors de la base de dades en un percentatge determinat
     * @param percentantge tant per cent (%) d'increment 
     */
    public void incrementarSou(float percentantge){
        float percen = 1 + percentantge/100;
        em.createNativeQuery("UPDATE Professor p SET p.salari = p.salari * :percentatge")
                .setParameter("percentatge", percen)
                .executeUpdate();

    }
    
}
