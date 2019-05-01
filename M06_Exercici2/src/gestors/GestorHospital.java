/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestors;

import java.util.ArrayList;
import java.util.List;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;
import model.Hospital;
import org.basex.api.client.ClientQuery;
import org.basex.api.client.ClientSession;

/**
 * Classe que gestiona la persistencia dels objectes de la classe model.Departament
 * @author professor
 */
public class GestorHospital {
    

    protected static final String CLAU_DUPLICADA = "CLAU DUPLICADA";
    protected static final String CLAU_INEXISTENT = "CLAU INEXISTENT";

    private XQConnection con=null;

    /**
     * Crea un gestor d'Hospitals que treballara amb la connexio ja oberta con
     * @param con connexió a traves de la qual es fan persistents els departaments
     */
    public GestorHospital(XQConnection con) {
       this.con = con;
    }
    
    /**
     * Dona d'alta un hospital a la base de dades.Si ja n'hi ha algun amb el seu mateix identificador, llenca una excepcio.
     * @param hospital hospital a donar d'alta
     * @throws gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau duplicada.
     */
    public void inserir(Hospital hospital) throws GestorException  {
    	try {
    		XQExpression query = con.createExpression();
            query.executeQuery("insert node " + Utilitats.formaHospitalXML(hospital) + " into doc(\"hospitals/hospitals.xml\")/collection(\"hospitals\")//hospitals");
		} catch (XQException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new GestorException(e.getMessage());
		}
    	
    }

    
    /**
     * Esborra de la base de dades l'hospital que te un codi determinat
     * @param idHospital identificador de l'hospital a esborrar
     * @throws gestors.GestorException si el codi no correspon a cap hospital de la base de dades
     * o hi ha un error en l'acces a la base de dades
     */
   
    public void eliminar(int idHospital) throws GestorException {
    	try {
	        XQExpression query = con.createExpression();
	        query.executeQuery("delete node doc(\"hospitals/hospitals.xml\")/collection(\"hospitals\")//hospitals//hospital[@id=" + idHospital + "]");
    	} catch (XQException e) {
			e.printStackTrace();
			throw new GestorException(e.getMessage());
		}
    }

    
    /**
     * Obte l'hospital de la base de dades amb un determinat identificador.
     * @param idHospital identificador de l'hospital a obtenir
     * @return hospital amb <b>idHospital</b> o null si no hi ha cap hospital a la base de dades amb aquest identificador.
     * @throws gestors.GestorException en cas d'error a la base de dades
     */
   
    public Hospital obtenirHospital(int idHospital) throws GestorException {
    	Hospital hospital =  null;
    	try {
            XQExpression query = con.createExpression();
            XQResultSequence result = query.executeQuery("doc(\"hospitals/hospitals.xml\")"
                    + "/collection(\"hospitals\")//hospitals//hospital[@id=" + idHospital + "]");
            return hospital = result.next() ? Utilitats.obteHospital(result.getItemAsString(null)): null;
		} catch (XQException e) {
			e.printStackTrace();
			throw new GestorException(e.getMessage());
		}
    }

    /**
     * Obte una llista amb els hospitals de la base de dades que tenen una especialitat determinada
     * @param especialitat que han de terni els hospitals de la llista resultat
     * @return llista amb els hospitals de la base de dades que tenen l'<b>especialitat</b>
     * @throws gestors.GestorException en cas d'error a la base de dades
     */
    
    public List<Hospital> obtenirHospitalsPerEspecialitat(String especialitat) throws GestorException{
    	List<Hospital> arrayHospitals = new ArrayList<Hospital>();
    	try {
        	
            XQExpression query = con.createExpression();
            XQResultSequence result = query.executeQuery("doc(\"hospitals/hospitals.xml\")"
                    + "/collection(\"hospitals\")//hospitals//hospital[especialitats//especialitat=\"" + especialitat+ "\"]");
            
            while(result.next()){
            	arrayHospitals.add(Utilitats.obteHospital(result.getItemAsString(null)));
            }
            return arrayHospitals;
		} catch (XQException e) {
			e.printStackTrace();
			throw new GestorException(e.getMessage());
		}
    }


    /**
     * Obte una llista amb els hospitals de la base de dades que tenen un pressupost superior o igual a un de derminat
     * @param pressupostMinim pressupost mínim que han de terni els hospitals de la llista resultat
     * @return llista amb els hospitals de la base de dades que tenen un pressupost >= pressupostMinim
     * @throws gestors.GestorException en cas d'error a la base de dades
     */
    
    public List<Hospital> obtenirHospitalsPerPressupostMinim(float pressupostMinim) throws GestorException{
    	List<Hospital> arrayHospitals = new ArrayList<>();
    	try {
            XQExpression query = con.createExpression();
            XQResultSequence result = query.executeQuery("doc(\"hospitals/hospitals.xml\")/collection(\"hospitals\")//hospitals//hospital[pressupost>=\"" +pressupostMinim+ "\"]");
            while(result.next()){
            	arrayHospitals.add(Utilitats.obteHospital(result.getItemAsString(null)));
            }
            return arrayHospitals;
		} catch (XQException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new GestorException(e.getMessage());
		}

    }

}
