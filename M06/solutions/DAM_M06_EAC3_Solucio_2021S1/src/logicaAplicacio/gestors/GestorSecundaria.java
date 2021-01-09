/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaAplicacio.gestors;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import logicaAplicacio.model.Secundaria;

/**
 * Classe que gestiona la persistencia dels objectes de la classe logicaAplicacio.model.Secundaria
 * @author professor
 */
public class GestorSecundaria {
    private EntityManager em = null;

    /**
     * Crea un gestor de professor de secundària que treballara amb l'EntityManager em
     * @param em context on es fan persistents els professors de secundària
     */
    public GestorSecundaria(EntityManager em) {
       this.em = em;
    }

    /**
     * Consultar tots els professor de la base de dades
     * @return Llista amb tots els professor de secundària de la base de dades
     */
    public List<Secundaria> obtenirProfesSecundaria() {
       Query q = em.createNamedQuery("Secundaria.tots");

       List<Secundaria> resultat=q.getResultList();
       for(Secundaria b:resultat){
           em.detach(b);
       }
       return resultat;
    }
   
    /**
     * Obte una llista amb tots els professors de secundària de la base de dades amb una titulació determinada
     * @param titulacio titulació dels professors de secundària a obtenir
     * @return llistat amb els professors de secundària a la base de dades amb una titulació determinada
     */
    public List<Secundaria> obtenirProSecundariaPerTitulacio(String titulacio) {
       Query q = em.createNamedQuery("Secundaria.perTitulacio");
       q.setParameter("titulacio", titulacio);
       List<Secundaria> resultat=q.getResultList();
       resultat.forEach((a) -> {
           em.detach(a);
        });
       return resultat;
    }
    
     /**
     * Incrementa el salari de tots els professors de secundària de la base de dades que tenen un determinat títol en un percentatge concret
     * @param titol titulació del professors de secundària dels quals incrementarem els sous
     * @param percentantge tant per cent (%) d'increment 
     */
    public void incrementarSousSegonsTitol(String titol, float percentantge){
        Query q = em.createNamedQuery("Secundaria.incrementSouSegonsTitol");
        q.setParameter("percentatge", percentantge);
        q.setParameter("titulacio",titol);
        em.getTransaction().begin();
        q.executeUpdate();
        em.getTransaction().commit();
    }

}