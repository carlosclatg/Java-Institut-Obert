package gestors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import model.Hospital;
import net.xqj.basex.BaseXXQDataSource;
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
    
    private final String SERVER_NAME="localhost", PORT="1984", USER="admin",PASSWORD="admin";
    
    protected static final String ARREL = "doc(\"hospitals/hospitals.xml\")/collection(\"hospitals\")//"; //arrel del document
    
    private GestorHospital gestor;
    private XQConnection conn;
    

//dilluns-Cardiologia, dimarts-Endocrinologia, dimecres-Reumatologia, dijous-Gastroenterologia, divendres-Pneumologia
    
    
    private final Hospital[] hospitals={
            new Hospital(1,"Vall d'Hebron","Vicenç Martínez",579,"Passeig Vall d'Hebron 119-129 BCN",new String[]{"Cardiologia","Reumatologia","Gastroenterologia"}),
            new Hospital(2,"Sant Joan Reus", "Oscar Ros", 211,"Av. Doctor Josep Laporte, 2 Reus",new String[]{"Cardiologia","Endocrinologia","Reumatologia"}),
            new Hospital(3,"Clínic Barcelona","Josep Maria Campistol", 474,"C. Sabino Arana, 1 BCN",new String[]{"Cardiologia","Gastroenterologia","Pneumologia"}),
            new Hospital(4,"Arnau de Vilanova de Lleida", "Jaume Capdevila", 198,"Av. Alcalde Rovira Roure, 80 Lleida",new String[]{"Cardiologia","Endocrinologia","Reumatologia","Gastroenterologia"}),
            new Hospital(5,"Hospital del Mar","Olga Pané", 311,"Passeig Marítim Barceloneta, 25-29 BCN",new String[]{"Cardiologia","Reumatologia","Pneumologia"})
    };
    
    
    @Before
    public void setUp() throws GestorException, XQException {
        obrir();
        buidarDades();
        ferAltes();
        
    }
    
    @After
    public void tearDown() throws GestorException, XQException {
        tancar();
    }
    
    
    private void tancarIObrir() throws GestorException, XQException{
        tancar();
        obrir();
        
    }
    
    
    private void obrir() throws GestorException, XQException{
     XQDataSource xqs = new BaseXXQDataSource();
        xqs.setProperty("serverName", SERVER_NAME);
        xqs.setProperty("port", PORT);
        conn = xqs.getConnection(USER, PASSWORD);
        gestor = new GestorHospital(conn);
     
    }
    
    private void tancar() throws GestorException, XQException  {
        conn.close();
    }
    
     private void buidarDades() throws XQException{
          esborrarTots();
    }
    
    private void esborrarTots() throws XQException {

            String ordre = "delete node "+ARREL+"hospitals/hospital";
            
            XQExpression expr=conn.createExpression();
                    
            expr.executeQuery(ordre);

            expr.close();
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
                p1.getPressupost()==p2.getPressupost()&&
                p1.getAdreca().equals(p2.getAdreca()) &&
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
    // als metodes provaConsultaPerIdTots i provaConsultaPerIdCap
    @Test
    public void provaAltes(){
        
    }
    
    @Test 
    public void provaConsultaPerIdTots() throws GestorException, XQException{
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
    public void provaConsultaPerIdCap() throws GestorException, XQException{
        tancarIObrir();  //ens assegurem que s'han gravat els buffers

        assertNull(gestor.obtenirHospital(30));
        assertNull(gestor.obtenirHospital(-2));
        assertNull(gestor.obtenirHospital(80));

    }
    
    @Test
    public void provaObtenirHospitalPerEspecial() throws GestorException, XQException{
        tancarIObrir();
        
        List<Hospital> l=gestor.obtenirHospitalsPerEspecialitat("Cardiologia");
        
        assertTrue(l.size()==5 && comprovaHospital(l,1)&& comprovaHospital(l,2)&& comprovaHospital(l,3)&& comprovaHospital(l,4)&& comprovaHospital(l,5));
        
        l=gestor.obtenirHospitalsPerEspecialitat("Endocrinologia");
        
        assertTrue(l.size()==2 && comprovaHospital(l,2)&& comprovaHospital(l,4));
        
        l=gestor.obtenirHospitalsPerEspecialitat("Reumatologia");
        
        assertTrue(l.size()==4 && comprovaHospital(l,1)&& comprovaHospital(l,2)&& comprovaHospital(l,4)&& comprovaHospital(l,5));
        
        l=gestor.obtenirHospitalsPerEspecialitat("Gastroenterologia");
        
        assertTrue(l.size()==3 && comprovaHospital(l,1)&& comprovaHospital(l,3)&& comprovaHospital(l,4));
        
        l=gestor.obtenirHospitalsPerEspecialitat("Pneumologia");
        
        assertTrue(l.size()==2 && comprovaHospital(l,3)&& comprovaHospital(l,5));

    }
    
    @Test
    public void provaObtenirHospitalPerPressupostMinim() throws GestorException, XQException{
        tancarIObrir();
        
        List<Hospital> l=gestor.obtenirHospitalsPerPressupostMinim(311);
        
        assertTrue(l.size()==3 && comprovaHospital(l,1)&& comprovaHospital(l,3)&& comprovaHospital(l,3)&& comprovaHospital(l,5));
        

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
    public void provaEliminar() throws  GestorException, XQException {
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