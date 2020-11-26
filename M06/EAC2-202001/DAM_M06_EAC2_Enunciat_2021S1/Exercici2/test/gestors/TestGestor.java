/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import gestors.GestorException;
import gestors.GestorMobils;
import model.TelefonIntelligent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.SQLException;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


/**
 *
 * @author professor
 */
public class TestGestor {
    
    private final String PU = "ObjectDB";

   
    private GestorMobils gestor;
    
    private EntityManagerFactory telefonFac;
    private EntityManager telefon;
    
    
    private final TelefonIntelligent[] telefons = {
        new TelefonIntelligent(1,"Xiaomi Redmi Note 8T", 149.0F, "Android", "Snapdragon 665", 64, true),
	new TelefonIntelligent(2,"Samsung Galaxy A51", 279.0F, "Android", "Exynos9611", 128, true),
	new TelefonIntelligent(3,"Huawei Y5p", 89.0F, "Android", "Mediatek MT6762R", 32, true),
	new TelefonIntelligent(4,"iPhone SE(2ªgen)", 477.0F, "IOS", "Chip A13 Bionic", 64, true),
	new TelefonIntelligent(5,"Alcatel 1SE", 91.0F, "Android", "Octa-Core", 32, false)
    };
    
    private final TelefonIntelligent as4 = new TelefonIntelligent(5,"Alcatel 1SE", 91.0F, "Android", "Octa-Core", 32, false), as5 =  new TelefonIntelligent(5,"Alcatel 1SE", 91.0F, "Android", "Octa-Core", 32, false);
    
    @Before
    public void setUp() throws SQLException, GestorException {
        obrir();
        buidarBaseDades();
        ferAltes();
        
    }
    
    @After
    public void tearDown() throws SQLException {
        tancar();
    }
    
    
    private void tancarIObrir() throws SQLException{
        tancar();
        obrir();
        
    }
    
    
    private void obrir() throws SQLException{
        
        telefonFac = Persistence.createEntityManagerFactory(PU);
        telefon = telefonFac.createEntityManager();        
        
        gestor = new GestorMobils(telefon);
     
    }
    
    private void tancar() throws SQLException{
        telefon.close();
        telefonFac.close();
    }
    
    private void buidarBaseDades() throws SQLException{
        
        Query query = telefon.createQuery("DELETE FROM TelefonIntelligent");
        telefon.getTransaction().begin();
        query.executeUpdate();
        telefon.getTransaction().commit();
    }
    
    private void ferAltes() throws GestorException{
        for(TelefonIntelligent a:telefons){
            gestor.inserir(a);
        }
    }
    
    
    public boolean comparaMobils(TelefonIntelligent p1, TelefonIntelligent p2){
        return  p1.getRef()==p2.getRef() &&
                p1.getModel().equals(p2.getModel()) &&
                p1.getSo().equals(p2.getSo()) &&
                p1.getProcessador().equals(p2.getProcessador()) &&
                p1.getPreu()==p2.getPreu() &&
                p1.getMemoria()==p2.getMemoria() &&
                p1.isSd()==p2.isSd();
    }
    
    // les altes es fan cada vegada. Si hi ha les altes correctes es comprova 
    // als mètodes provaConsultaPerCodiTotes i provaConsultaPerCodiCap
    @Test
    public void provaAltes(){
        
    }  
    
    @Test 
    public void provaConsultaPerRefTotes() throws SQLException, GestorException{
        tancarIObrir();  //ens assegurem que s'han gravat els buffers
        
        TelefonIntelligent a=gestor.obtenirMobil(3);
        System.out.println(a);
        assertTrue(comparaMobils(a,telefons[2]));
        
        a=gestor.obtenirMobil(1);
        System.out.println(a);
        assertTrue(comparaMobils(a,telefons[0]));
        
        a=gestor.obtenirMobil(4);
        System.out.println(a);
        assertTrue(comparaMobils(a,telefons[3]));
        
        a=gestor.obtenirMobil(2);
        System.out.println(a);
        assertTrue(comparaMobils(a,telefons[1]));
    }
    
    //provem a fer consultes per codis innexistents
    
    @Test 
    public void provaConsultaPerRefCap() throws SQLException, GestorException{
        tancarIObrir();  //ens assegurem que s'han gravat els buffers

        assertNull(gestor.obtenirMobil(35));
        assertNull(gestor.obtenirMobil(-1));
        assertNull(gestor.obtenirMobil(8));

    }

    @Test 
    public void provaModificacio() throws SQLException, GestorException{
        tancarIObrir();  //ens assegurem que s'han gravat els buffers
        
        TelefonIntelligent a=gestor.obtenirMobil(3);
        assertTrue(comparaMobils(a,telefons[2]));
        
        a=gestor.obtenirMobil(1);
        assertTrue(comparaMobils(a,telefons[0]));
        
        gestor.modificar(as4);
        a=gestor.obtenirMobil(5);
        assertTrue(comparaMobils(a,as5));
        
        a=gestor.obtenirMobil(2);
        assertTrue(comparaMobils(a,telefons[1]));
    }
    
    
    @Test
    public void provaEliminar() throws SQLException, GestorException {
        gestor.eliminar(3);
        tancarIObrir();
        
        assertNull(gestor.obtenirMobil(3));
        
        TelefonIntelligent a=gestor.obtenirMobil(1);
        assertTrue(comparaMobils(a,telefons[0]));
        
        a=gestor.obtenirMobil(4);
        assertTrue(comparaMobils(a,telefons[3]));
        
        a=gestor.obtenirMobil(2);
        assertTrue(comparaMobils(a,telefons[1]));


    }
    
}
