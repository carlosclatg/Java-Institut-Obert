/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logicaAplicacio.gestors;

/**
 *
 * @author professor
 */
public class GestorException extends Exception {
    /**
     * Construeix una excepcio relacionada amb la gestio de la persistencia
     * @param message missatge de l'excepcio
     */
    public GestorException(String message) {
        super(message);
    }
    
    
}

