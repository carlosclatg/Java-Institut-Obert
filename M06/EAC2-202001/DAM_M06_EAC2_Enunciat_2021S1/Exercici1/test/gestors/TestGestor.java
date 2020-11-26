package gestors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import model.Equip;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


/**
 *
 * @author alumne
 */
public class TestGestor {
    
    private final String TAULA="equip";
    
    private final String URL="jdbc:postgresql://localhost:5432/eac2_2021s1";
    private final String USER="ioc";
    private final String PSW="ioc";
   
    private GestorEquip gestor;
    
    
    private Connection connexio;
    
    
    private final Equip[] equips = {
            new Equip(1, "Ducati Team", "Bolonia, Italia", 1954, "AD04", "Andrea Dovizioso", 68, "Forlimpopoli", new String[]{"GP de Österreich", "GP de R. Txeca", "GP de Espanya"}),
            new Equip(2, "Repsol Honda Team", "Aalst, Bélgica", 1994, "MM93", "Marc Marquez", 65, "Cervera", new String[]{ "GP de Österreich", "GP de Espanya", "GP de Emília-Romanya"}),
            new Equip(3, "Monster Energy Yamaha", "Lesmo, Italia", 1999, "VR46", "Valentino Rossi", 69, "Urbino", new String[]{"GP de Catalunya", "GP de San Marino", "GP de R. Txeca"}),
            new Equip(4, "Monster Energy Yamaha", "Lesmo, Italia", 1999, "MV12", "Maverick Viñales", 64, "Figueres", new String[]{"GP de Emília-Romanya", "GP de Espanya", "GP de Österreich", "GP de Catalunya"}),
            new Equip(5, "Red Bull KTM Factory Racing", "Mattighofen, Austria", 2015, "PE44", "Pol Espargaro", 63, "Granollers", new String[]{"GP de San Marino", "GP de R. Txeca", "GP de Emília-Romanya", "GP de Catalunya", "GP de Emília-Romanya"}),
    };
    
    
    @Before
    public void setUp() throws SQLException, GestorException {
        obrir();
        buidarTaula();
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
        connexio = DriverManager.getConnection(URL, USER, PSW);
        connexio.setAutoCommit(true);
        gestor = new GestorEquip(connexio);
    }
    
    private void tancar() throws SQLException{
        connexio.close();
    }
    
    private void buidarTaula() throws SQLException{
        PreparedStatement ps=connexio.prepareStatement("DELETE FROM "+TAULA);
        
        ps.executeUpdate();
    }
    
    private void ferAltes() throws GestorException, SQLException {
        for(Equip a:equips){
            gestor.inserir(a);
        }
    }
    
    
    public boolean comparaEquips(Equip p1, Equip p2){
        return  p1.getId()==p2.getId() &&
                p1.getNom().equals(p2.getNom()) &&
                p1.getFundada()==p2.getFundada() &&
                p1.getSeu().equals(p2.getSeu()) &&
                p1.getMatricula().equals(p2.getMatricula()) &&
                p1.getNomPilot().equals(p2.getNomPilot()) &&
                comparaArraysCadena(p1.getPodis(),p2.getPodis());
    }
    
    public boolean comparaArraysCadena(List<String> l1, List<String> l2){
        
        if(l1.size()!=l2.size()){
            return false;
        }
        
        for(String s:l1){
            if(!l2.contains(s)){
                return false;
            }
        }
        return true;
    }
    
    // les altes es fan cada vegada. Si hi ha les altes correctes es comprova 
    // als metodes provaConsultaPerCodiTots i provaConsultaPerCodiCap
    @Test
    public void provaAltes(){
        
    }
    
    @Test 
    public void provaConsultaPerCodiTots() throws SQLException, GestorException{
        tancarIObrir();  //ens assegurem que s'han gravat els buffers
        
        Equip a=gestor.obtenirEquip(3);
        assertTrue(comparaEquips(a,equips[2]));
        
        a=gestor.obtenirEquip(1);
        assertTrue(comparaEquips(a,equips[0]));
        
        a=gestor.obtenirEquip(4);
        assertTrue(comparaEquips(a,equips[3]));
        
        a=gestor.obtenirEquip(2);
        assertTrue(comparaEquips(a,equips[1]));
        
        a=gestor.obtenirEquip(5);
        assertTrue(comparaEquips(a,equips[4]));
    }
    
    //provem a fer consultes per codis innexistents
    
    @Test 
    public void provaConsultaPerIdCap() throws SQLException, GestorException {
        tancarIObrir();  //ens assegurem que s'han gravat els buffers

        assertNull(gestor.obtenirEquip(40));
        assertNull(gestor.obtenirEquip(-3));
        assertNull(gestor.obtenirEquip(70));

    }
    
    @Test
    public void provaObtenirEquipsPerPodis() throws SQLException, GestorException {
        tancarIObrir();
        
        List<Equip> l=gestor.obtenirEquipPerPodi("GP de Espanya");
        
        assertTrue(l.size()==3 && comprovaEquip(l,1)&& comprovaEquip(l,2)&& comprovaEquip(l,4));
        
        l=gestor.obtenirEquipPerPodi("GP de Österreich");
        
        assertTrue(l.size()==3 && comprovaEquip(l,1)&& comprovaEquip(l,2)&& comprovaEquip(l,4));
        
        l=gestor.obtenirEquipPerPodi("GP de R. Txeca");
        
        assertTrue(l.size()==3 && comprovaEquip(l,1)&& comprovaEquip(l,3)&& comprovaEquip(l,5));
        
        l=gestor.obtenirEquipPerPodi("GP de Emília-Romanya");
        
        assertTrue(l.size()==3 && comprovaEquip(l,2)&& comprovaEquip(l,5)&& comprovaEquip(l,4));
        
        l=gestor.obtenirEquipPerPodi("GP de Catalunya");
        
        assertTrue(l.size()==3 && comprovaEquip(l,3)&& comprovaEquip(l,4)&& comprovaEquip(l,5));
        
        l=gestor.obtenirEquipPerPodi("GP de San Marino");
        
        assertTrue(l.size()==2 && comprovaEquip(l,3)&& comprovaEquip(l,5));

    }
    
    private boolean comprovaEquip(List<Equip> l, int id){
        for(Equip p:l){
            if(p.getId()==id && comparaEquips(p,equips[id-1])){ // aprofitem que posicio = id - 1
                return true;
            }
        }
        return false;
    }
    
    @Test
    public void provaEliminar() throws SQLException, GestorException {
        gestor.eliminar(3);
        tancarIObrir();
        
        assertNull(gestor.obtenirEquip(3));
        
        Equip a=gestor.obtenirEquip(1);
        assertTrue(comparaEquips(a,equips[0]));
        
        a=gestor.obtenirEquip(4);
        assertTrue(comparaEquips(a,equips[3]));
        
        a=gestor.obtenirEquip(2);
        assertTrue(comparaEquips(a,equips[1]));

    }
    
    
    
}
