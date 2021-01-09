package gestor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import logicaAplicacio.gestors.GestorException;
import logicaAplicacio.gestors.GestorProfessor;
import logicaAplicacio.gestors.GestorInstitut;
import logicaAplicacio.gestors.GestorSecundaria;
import logicaAplicacio.gestors.GestorTecnics;
import logicaAplicacio.model.Professor;
import logicaAplicacio.model.Secundaria;
import logicaAplicacio.model.Tecnics;
import logicaAplicacio.model.Institut;
import static clonador.Clonador.clona;
import comparador.Comparador;
import static comparador.Comparador.compara;
import static comparador.Comparador.comparaLlistes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author professor
 */
public class TestGestors {
    
    private  EntityManagerFactory emf;
    private  EntityManager em;
    private  final String PU = "jpa2021s1";
    private  GestorProfessor gestorProfessor;
    private  GestorInstitut gestorInstitut;
    private  GestorSecundaria gestorSecundaria;
    private  GestorTecnics gestorTecnics;
    
    private final ArrayList totesAltes=new ArrayList();
    
    private final ArrayList<Institut> totsInstituts = new ArrayList<>(),
    // Resultat de consultar els institus despres de fer diferents proves
                                      totsInstitutExcepte7=new ArrayList<>();   // despres d'eliminar l'institut amb codi 7
    
    private final ArrayList<Professor> totsProfessors = new ArrayList<>(),
    // Resultat de consultar els professors despres de fer diferents proves
                                    totsTecnics=new ArrayList<>(),   // tots els professors tipus tècnic
                                    totsProfessorsExcepteEl8 = new ArrayList<>(),  // despres d'eliminar el professor amb codi 8
                                    totsProfessorsCanviatEl3 = new ArrayList<>(),  // despres de modificar el professor amb codi 3
                                    totsIncrementatsEl50pc= new ArrayList<>(),   // depres d'incrementar el sou de tots els professors un 50%
                                    totsSecundariaIncrementaSou20Einformatica= new ArrayList<>(),   // depres d'incrementar un 20% el sou als professors amb títol d'Enginyeria informàtica
                                    elsDeEinformatica = new ArrayList<>(),            // despres d'etiquetar els professors amb títol Enginyeria informàtica
                                    elsDeSanitari=new ArrayList<>(),             // despres d'etiquetar els professors amb especialitat Sanitària
                                    elsDeSou1760=new ArrayList<>(),        //resultat de consultar els professors del cos de tècnics amb sou 1760
                                    elsDeCalGravat=new ArrayList<>(),        //resultat de consultar els tecnics assignats a l'Institut Cal Gravat
                                    totsProfessorsInstitut2=new ArrayList<>();   // els professors l'institut amb codi 2              
    
    
    /**
     * Abans de cada test neteja la base de dades i les omplim amb les dades de prova
     * @throws GestorException 
     */
    @Before
    public void setUp() throws GestorException {
        obre();
        creaDadesPrincipals();  //omplim a memoria les llistes anteriors
        // ho esborrem tot
        
        List<Institut> auxLlistaInstitus=gestorInstitut.obtenirInstituts();
        
        
        List<Professor> auxLlistaProfessors=gestorProfessor.obtenirProfessors();
        
         for(Institut b:auxLlistaInstitus){
            gestorInstitut.eliminar(b.getCodi());
        }
        
        for(Professor b:auxLlistaProfessors){
            gestorProfessor.eliminar(b.getIdProfe());
        }
        
        fesAltes();//fem l'alta de les dades de prova
        
    } 
    
    /**
     * En acabar els tests, tanca l'entityManager i la factoria d'entities manager
     */
    
    @After()
    public  void classEnds(){
        tanca();
    }
    
    
    // metodes que proven coses; abans de cadascun s'executa setUp i despres classEnds
    @Test
    public void provaConsultaTotsTecnics(){
        assertTrue(Comparador.comparaLlistes(gestorTecnics.obtenirTecnics(),totsTecnics,1));        
    }
    
    @Test
    public void provaConsultaTotsProfessors(){
        assertTrue(Comparador.comparaLlistes(gestorProfessor.obtenirProfessors(),totsProfessors,1));        
    }

    @Test
    public void provaEliminarProfessor8() throws GestorException{
        gestorProfessor.eliminar(8);
        assertTrue(comparaLlistes(gestorProfessor.obtenirProfessors(),totsProfessorsExcepteEl8,1));
    }
    
    /**
     *
     * @throws GestorException
     */
    @Test(expected=GestorException.class)
    public void provaEliminarProfessor88() throws GestorException{
        gestorProfessor.eliminar(88);
    }

    @Test
    public void provaEliminarInstitut7() throws GestorException{
        gestorInstitut.eliminar(7);    // es un institut sense cap professors assignat; amb un cap dona error d'integritat
        assertTrue(comparaLlistes(gestorInstitut.obtenirInstituts(),totsInstitutExcepte7,1));
    }
    
    @Test
    public void provaModificarProfessort3() throws GestorException{
        Professor a=gestorProfessor.obtenirProfessor(3);
        a.setNom("###");  a.setSalari(1000);
        gestorProfessor.modificar(a);
        assertTrue(comparaLlistes(gestorProfessor.obtenirProfessors(),totsProfessorsCanviatEl3,1));
    }

    /**
     *
     * @throws GestorException
     */
    @Test(expected=GestorException.class)
    public void provaModificarProfessor88() throws GestorException{
        //Tecnics(int codi, String nom, float sou, Institut centre, String formacio)
        Professor a = (Professor) new Tecnics(88,"###",1000, "ok", null);
        gestorProfessor.modificar(a);
    }
    
    @Test
    public void provaConsultarProfessor5() throws GestorException{
        Professor a=gestorProfessor.obtenirProfessor(5);
        assertTrue(compara(a,totsProfessors.get(4),2));
    }

    @Test
    public void provaConsultarProfessor88() throws GestorException{
        assertNull(gestorProfessor.obtenirProfessor(88));
    }

    @Test
    public void provaConsultarProfessorsInstitut2() throws GestorException{
        List<Professor> d= gestorInstitut.obtenirInstitut(2).getProfessors();
        assertTrue(comparaLlistes(d,totsProfessorsInstitut2,2));
    }
    
    @Test
    public void provaIncrementarSalari() throws GestorException{
        gestorProfessor.incrementarSou(50);
          assertTrue(comparaLlistes(gestorProfessor.obtenirProfessors(),totsIncrementatsEl50pc,1));
    }    
    
    @Test
    public void provaConsultarProSecundariaPerTitol() throws GestorException{
        assertTrue(comparaLlistes(gestorSecundaria.obtenirProSecundariaPerTitulacio("Enginyeria informàtica"),elsDeEinformatica,1));
    }      

    @Test
    public void provaConsultarTecnicsPerEspecialitat() throws GestorException{
        assertTrue(comparaLlistes(gestorTecnics.obtenirTecnicsPerEspecialitat("Sanitària"),elsDeSanitari,1));
    } 
    
    @Test
    public void provaConsultarTecnicsCalGravat() throws GestorException{
        assertTrue(comparaLlistes(gestorTecnics.obtenirTecnicsUnInsti(5),elsDeCalGravat,1));
    } 

    @Test
    public void provaConsultarTecnicsAmbSou1760() throws GestorException{
        assertTrue(comparaLlistes(gestorTecnics.obtenirTecnicsPerSou(1760),elsDeSou1760,1));
    }
    
    @Test
    public void provaIncrementarSalariEngInformatics() throws GestorException{
        gestorSecundaria.incrementarSousSegonsTitol("Enginyeria informàtica", 20);
        assertTrue(comparaLlistes(gestorProfessor.obtenirProfessors(),totsSecundariaIncrementaSou20Einformatica,1));
    }
    
    
    private void fesAltes() throws GestorException {
        for(Object o:totesAltes){
            if(o instanceof Professor){
               if(gestorProfessor.obtenirProfessor(((Professor) o).getIdProfe())==null){
                   gestorProfessor.inserir((Professor)o);
               }
            }
            if(o instanceof Institut){
               if(gestorInstitut.obtenirInstitut(((Institut) o).getCodi())==null){
                   gestorInstitut.inserir((Institut)o);
               }
            }

            em.detach(o);

        }
    }
    
    //Institut (int codi, String nom)
    private  Institut creaInstitut(int codi, String nom, String urbs){
          return new Institut(codi, nom, urbs);
    }
    
    //Secundaria(int codi, String nom, float sou, String titulacio)
    private  Secundaria creaSecundaria(int codi, String nom, float sou, String titulacio, Institut centre){
        Secundaria s = new Secundaria(codi, nom, sou, titulacio, centre);
        
        centre.getProfessors().add(s);
        
        return s;
    }
    
    //Tecnics(int codi, String nom, float sou, String formacio)
    private  Tecnics creaTecnic(int codi, String nom, float sou, String formacio, Institut centre){
        Tecnics t = new Tecnics(codi, nom, sou, formacio, centre);
        
        centre.getProfessors().add(t);
        
        return t;
        
    }

    private  void creaDadesPrincipals(){  // s'omplen les llistes amb les dades de prova i els resultats de les consultes
        
        //Instituts
	totsInstituts.add(creaInstitut(1, "Institut Francesc Vidal i Barraquer","Tarragona"));
	totsInstituts.add(creaInstitut(2, "Institut Mare de Déu de la Mercè","Barcelona"));
	totsInstituts.add(creaInstitut(3, "Institut Esteve Terradas i Illa","Cornellà de Llobregat"));
	totsInstituts.add(creaInstitut(4, "Institut Montilivi","Girona"));
	totsInstituts.add(creaInstitut(5, "Institut Cal Gravat","Manresa"));
	totsInstituts.add(creaInstitut(6, "Institut Joan Oró Lleida","Lleida"));
        totsInstituts.add(creaInstitut(7, "Institut de L'Ebre","Tortosa"));


	//Tipus Tècnics
	totsProfessors.add(creaTecnic(1,"Anna Figueres",1760F,"Informàtica",totsInstituts.get(0)));
	totsProfessors.add(creaTecnic(2,"Elena Garcia",2250F,"Dret",totsInstituts.get(1)));
	totsProfessors.add(creaTecnic(3,"Mohamed Ben Zaid",2050F,"Psicologia", totsInstituts.get(2)));
	totsProfessors.add(creaTecnic(4,"Anselm Herrera",1900F,"Sanitària", totsInstituts.get(4)));


	//Tipus Secundaria
	totsProfessors.add(creaSecundaria(5,"Adrian Folch",1960F,"Enginyeria en sistemes i tecnologia naval",totsInstituts.get(1)));
	totsProfessors.add(creaSecundaria(6,"Aida Barrionuevo",1700F,"Administració i direcció d’empreses",totsInstituts.get(0)));
	totsProfessors.add(creaSecundaria(7,"Andrea Torres",1870F,"Prevenció i seguretat integral",totsInstituts.get(5)));
	totsProfessors.add(creaSecundaria(8,"Paula Sanz",1670F,"Psicologia social i organitzacional",totsInstituts.get(4)));
	totsProfessors.add(creaSecundaria(9,"Dante Molero",1780F,"Ciències de l'activitat física i de l'esport",totsInstituts.get(4)));
	totsProfessors.add(creaSecundaria(10,"Carmen Roca",2700F,"Veterinària",totsInstituts.get(2)));
	totsProfessors.add(creaSecundaria(11,"Erick Sanchez",2010F,"Enginyeria informàtica",totsInstituts.get(3)));
	totsProfessors.add(creaSecundaria(12,"Norah Sanllehi",2310F,"Enginyeria informàtica",totsInstituts.get(0)));
	totsProfessors.add(creaSecundaria(13,"Adrian Roca",1930F,"Enginyeria Forestal",totsInstituts.get(5)));
	totsProfessors.add(creaSecundaria(14,"Lucas Tapia",1760F,"Administració i direcció d’empreses",totsInstituts.get(3)));
	totsProfessors.add(creaSecundaria(15,"Stella Camacho",2010F,"Arquitectura tècnica i edificació",totsInstituts.get(2)));
	totsProfessors.add(creaSecundaria(16,"Joshua Gonzalez",1440F,"Enginyeria electrònica industrial i automàtica",totsInstituts.get(0)));
        
        totesAltes.addAll(totsInstituts);
        totesAltes.addAll(totsProfessors);

        
        for(Professor a:totsProfessors){
            if(a.getIdProfe()!=8){
                totsProfessorsExcepteEl8.add((Professor) clona(a, 2));
            }
            
            if(a.getIdProfe()<=4){
                totsTecnics.add((Professor) clona(a, 2));
                
            }
            if(a.getInstitut().getCodi()==2){
                totsProfessorsInstitut2.add((Professor) clona(a, 2));
                
            }
            Professor aux=(Professor) clona(a, 2);
            if(aux.getIdProfe()==3){
                aux.setNom("###");  aux.setSalari(1000);
                
            }
            
            totsProfessorsCanviatEl3.add(aux);
            totsIncrementatsEl50pc.add((Professor) clona(a, 2));
            totsSecundariaIncrementaSou20Einformatica.add((Professor) clona(a, 2));
            
            if(a instanceof Secundaria){
                Secundaria p = (Secundaria)a;
                if(p.getTitol().equals("Enginyeria informàtica")){
                    elsDeEinformatica.add(p);
                }
                
            } else {
                Tecnics e =(Tecnics)a;
                
                if(e.getSalari()==1760) {
                    elsDeSou1760.add(e);
                    
                } else if(e.getEspecialitat().equals("Sanitària")) {
                    elsDeSanitari.add(e);
                    
                }
                if (e.getInstitut().getCodi()==5  ) {
                    elsDeCalGravat.add(e);

                }
            }
        } // fi del for que recorre tots els professors
        //for(Professor a:totsIncrementatsEl50pc)
        
        totsIncrementatsEl50pc.forEach((a) -> {
            a.setSalari((float) (a.getSalari()*1.5));
        });
        
        totsSecundariaIncrementaSou20Einformatica.forEach((a) -> {
            if ((a instanceof Secundaria)) {
                Secundaria s = (Secundaria)a;
                if (s.getTitol().equals("Enginyeria informàtica")){
                    a.setSalari((float) (a.getSalari()*1.2));
                }
            }
        });
        
         for(Institut a:totsInstituts){
            if(a.getCodi()!=7){
                totsInstitutExcepte7.add((Institut) clona(a, 2));
            }
         }
        

    }
        
        
    private void obre(){  // obrim l'EntityManager i creem els gestors
        try{
        emf = Persistence.createEntityManagerFactory(PU);
        em=emf.createEntityManager();
        
        gestorInstitut= new GestorInstitut(em);
        gestorProfessor= new GestorProfessor(em);
        gestorTecnics= new GestorTecnics(em);
        gestorSecundaria= new GestorSecundaria(em);
        
         
        }catch(Exception e){
            System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVV");
            System.out.println(e.getMessage());
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        }
    }
    
    private void tanca(){  //tanquem l'EntityManager
        em.close();
        emf.close();
        
    }
    
}
