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
import logicaAplicacio.model.Tecnics;

/**
 * Classe que gestiona la persistencia dels objectes de la classe logicaAplicacio.model.Tecnics
 * @author professor
 */
public class GestorTecnics {
    private EntityManager em = null;

    /**
     * Crea un gestor de professor del cos de tècnics que treballara amb l'EntityManager em
     * @param em context on es fan persistents els cap
     */
    public GestorTecnics (EntityManager em) {
       this.em = em;
    }

    /**
     * Obte una llista amb tots els professors del cos de tècnics de la base de dades
     * @return llista amb els professors del cos de tècnics de la base de dades
     */
    public List<Tecnics> obtenirTecnics() {
       return em.createNamedQuery("Tecnics.getAll").getResultList();
    }
    
    /**
     * Obté una llista amb tots els professors del cos de tècnics que treballen
     * per un institut amb un identificador determinat que es troba a la base de dades
     * @param codiInsti identificador de l'institut
     * @return llistat dels tècnics d'un isntitut determinat de la base de dades
     */
    public List<Tecnics> obtenirTecnicsUnInsti(int codiInsti) {
       return null;
    }
    
    /**
     * Obté una llista amb tots els professors del cos de tècnics que una
     * especilaitat determinada que es troba a la base de dades
     * @param formacio especialitat del tècnic
     * @return llistat dels tècnics amb una especilaitat determinada de la base de dades
     */
    public List<Tecnics> obtenirTecnicsPerEspecialitat(String formacio) {
        Query q = em.createNamedQuery("Tecnics.getByEspecialitat");
        return (List<Tecnics>) q.setParameter("especialitat", formacio).getResultList();
    }
    
    /**
     * Obté una llista amb tots els professors del cos de tècnics que un
     * salari determinat que es troba a la base de dades
     * @param sou salari del tècnic
     * @return llistat dels tècnics amb una quantitat de salari determinada de la base de dades
     */
    public List<Tecnics> obtenirTecnicsPerSou(float sou) {
        Query q = em.createNamedQuery("Tecnics.getBySalari");
        q.setParameter("salari", sou);
        return (List<Tecnics>) q.getResultList();
    }

}
