/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logicaAplicacio.gestors;

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
       Query q = em.createNamedQuery("Professor.tots");

       List<Professor> resultat=q.getResultList();
       for(Professor b:resultat){
           em.detach(b);
       }
       return resultat;
    }

      /**
     * Esborra el professor de la base de dades que te un determinat codi
     * @param idProfe codi que identifica el professor a esborrar
     * @throws logicaAplicacio.gestors.GestorException si el codi no correspon a cap professor de la base de dades
     * o hi ha un error en aquesta
     */
    public void eliminar(int idProfe) throws GestorException {
        Professor base=em.find(Professor.class, idProfe);
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
     * Obte el professor de la base de dades amb un codi determinat
     * @param idProfe codi del professor a obtenir
     * @return professor o null en cas de no haver-hi cap professor persistent amb aquest codi
     */
    public Professor obtenirProfessor(int idProfe) {
        Professor base=em.find(Professor.class, idProfe);
        if(base!=null){
            em.detach(base);
            return base;
        }else {
            return null;
        }

    }

    /**
     * Dona d'alta un professor en la base de dades. Si ja n'hi ha algun amb el seu mateix codi, llenca una excepcio.
     * @param profe professor a inserir
     * @throws logicaAplicacio.gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau duplicada.
     */
    public void inserir(Professor profe) throws GestorException {
        if(em.find(Professor.class,profe.getIdProfe())==null){
            em.getTransaction().begin();
            em.merge(profe);
            em.getTransaction().commit();
        }
        else {
            throw new GestorException("Clau duplicada");
        }

    }

   /**
     * Modifica un professor de la base de dades. Si no n'hi ha cap amb el seu codi, llenca una excepcio.
     * @param profe professor a actualitzar
     * @throws logicaAplicacio.gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau inexistent.
    */
    public void modificar(Professor profe) throws GestorException{
        if(em.find(Professor.class,profe.getIdProfe())!=null){
            em.getTransaction().begin();
            em.merge(profe);
            em.getTransaction().commit();
        }
        else {
            throw new GestorException("Clau inexistent");
        }
    }
    
   /**
     * Incrementa el salari de tots els professors de la base de dades en un percentatge determinat
     * @param percentantge tant per cent (%) d'increment 
     */
    public void incrementarSou(float percentantge){
        Query q = em.createNamedQuery("Professor.incrementa");
        q.setParameter("percentatge", percentantge);
        em.getTransaction().begin();
        q.executeUpdate();
        em.getTransaction().commit();
    }
    
}
