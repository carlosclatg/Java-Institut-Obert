/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import gestors.GestorException;
import gestors.GestorHospital;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import model.Hospital;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


/**
 *
 * @author professor
 */
public class TestGestor {
    
    private final String TAULA="hospital";
    
    private final String URL="jdbc:postgresql://localhost:5432/eac2_1819s2";
    private final String USER="ioc";
    private final String PSW="ioc";
   
    private GestorHospital gestor;
    

//dilluns-Cariologia, dimarts-Endocrinologia, dimecres-Reumatologia, dijous-Gastroenterologia, divendres-Pneumologia
    
    
    private Connection con;
    
    
    private final Hospital[] hospitals={
            new Hospital(1,"Vicenç Martínez", "Vall d'Hebron",579,"Passeig Vall d'Hebron 119-129 BCN",new String[]{"Cariologia","Reumatologia","Gastroenterologia"}),
            new Hospital(2,"Oscar Ros", "Sant Joan Reus",211,"Av. Doctor Josep Laporte, 2 Reus",new String[]{"Cariologia","Endocrinologia","Reumatologia"}),
            new Hospital(3,"Josep Maria Campistol", "Clínic Barcelona",474,"C. Sabino Arana, 1 BCN",new String[]{"Cariologia","Gastroenterologia","Pneumologia"}),
            new Hospital(4,"Jaume Capdevila", "Arnau de Vilanova de Lleida",198,"Av. Alcalde Rovira Roure, 80 Lleida",new String[]{"Cariologia","Endocrinologia","Reumatologia","Gastroenterologia"}),
            new Hospital(5,"Olga Pané", "Hospital del Mar",311,"Passeig Marítim Barceloneta, 25-29 BCN",new String[]{"Cariologia","Reumatologia","Pneumologia"})
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
        con = DriverManager.getConnection(URL, USER, PSW);
        con.setAutoCommit(true);
        gestor = new GestorHospital(con);
     
    }
    
    private void tancar() throws SQLException{
        con.close();
    }
    
    private void buidarTaula() throws SQLException{
        PreparedStatement ps=con.prepareStatement("DELETE FROM "+TAULA);
        
        ps.executeUpdate();
    }
    
    private void ferAltes() throws GestorException{
        for(Hospital a:hospitals){
            gestor.inserir(a);
        }
    }
    
    
    public boolean comparaHospitals(Hospital p1, Hospital p2){
        return  p1.getId()==p2.getId() &&
                p1.getDirector().equals(p2.getDirector()) &&
                p1.getNom().equals(p2.getNom()) &&
                p1.getpressupost()==p2.getpressupost()&&
                p1.getAdreça().equals(p2.getAdreça()) &&
                comparaArraysCadena(p1.getEspecialitats(),p2.getEspecialitats());
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
    // als m?todes provaConsultaPerIdTots i provaConsultaPerIdCap
    @Test
    public void provaAltes(){
        
    }
    
    @Test 
    public void provaConsultaPerIdTots() throws SQLException, GestorException{
        tancarIObrir();  //ens assegurem que s'han gravat els buffers
        
        Hospital a=gestor.obtenirHospital(3);
        assertTrue(comparaHospitals(a,hospitals[2]));
        
        a=gestor.obtenirHospital(1);
        assertTrue(comparaHospitals(a,hospitals[0]));
        
        a=gestor.obtenirHospital(4);
        assertTrue(comparaHospitals(a,hospitals[3]));
        
        a=gestor.obtenirHospital(2);
        assertTrue(comparaHospitals(a,hospitals[1]));
        
        a=gestor.obtenirHospital(5);
        assertTrue(comparaHospitals(a,hospitals[4]));
    }
    
    //provem a fer consultes per ids innexistents
    
    @Test 
    public void provaConsultaPerIdCap() throws SQLException, GestorException{
        tancarIObrir();  //ens assegurem que s'han gravat els buffers

        assertNull(gestor.obtenirHospital(30));
        assertNull(gestor.obtenirHospital(-2));
        assertNull(gestor.obtenirHospital(80));

    }
    
    @Test
    public void provaObtenirHospitalPerEspecial() throws SQLException, GestorException{
        tancarIObrir();
        
        List<Hospital> l=gestor.obtenirHospitalPerEspecialitats("Cariologia");
        
        assertTrue(l.size()==5 && comprovaHospital(l,1)&& comprovaHospital(l,2)&& comprovaHospital(l,3)&& comprovaHospital(l,4)&& comprovaHospital(l,5));
        
        l=gestor.obtenirHospitalPerEspecialitats("Endocrinologia");
        
        assertTrue(l.size()==2 && comprovaHospital(l,2)&& comprovaHospital(l,4));
        
        l=gestor.obtenirHospitalPerEspecialitats("Reumatologia");
        
        assertTrue(l.size()==4 && comprovaHospital(l,1)&& comprovaHospital(l,2)&& comprovaHospital(l,4)&& comprovaHospital(l,5));
        
        l=gestor.obtenirHospitalPerEspecialitats("Gastroenterologia");
        
        assertTrue(l.size()==3 && comprovaHospital(l,1)&& comprovaHospital(l,3)&& comprovaHospital(l,4));
        
        l=gestor.obtenirHospitalPerEspecialitats("Pneumologia");
        
        assertTrue(l.size()==2 && comprovaHospital(l,3)&& comprovaHospital(l,5));

    }
    
    private boolean comprovaHospital(List<Hospital> l, int id){
        for(Hospital p:l){
            if(p.getId()==id && comparaHospitals(p,hospitals[id-1])){ // aprofitem que posicio = id - 1
                return true;
            }
        }
        return false;
    }
    
    @Test
    public void provaEliminar() throws SQLException, GestorException {
        gestor.eliminar(3);
        tancarIObrir();
        
        assertNull(gestor.obtenirHospital(3));
        
        Hospital a=gestor.obtenirHospital(1);
        assertTrue(comparaHospitals(a,hospitals[0]));
        
        a=gestor.obtenirHospital(4);
        assertTrue(comparaHospitals(a,hospitals[3]));
        
        a=gestor.obtenirHospital(2);
        assertTrue(comparaHospitals(a,hospitals[1]));


    }
    
    
    
}
