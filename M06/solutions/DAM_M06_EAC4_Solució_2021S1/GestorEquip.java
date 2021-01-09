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
import model.Equip;

/**
 * Classe que gestiona la persistencia dels objectes de la classe tipus equip model.Equip
 * @author alumne
 */
public class GestorEquip {


    protected static final String CLAU_DUPLICADA = "CLAU DUPLICADA";
    protected static final String CLAU_INEXISTENT = "CLAU INEXISTENT";
    protected static final String ARREL = "doc(\"equips/equips.xml\")/collection(\"equips\")//"; //arrel del document (permet simplificar les expressions)
    
    private XQConnection con=null;

    /**
     * Crea un gestor d'Equip que treballara amb la connexio connex
     * @param con connexio a traves de la qual es fan persistents de els equips
     */
    public GestorEquip(XQConnection con) {
       this.con = con;
    }
    
    
    /**
     * Dona d'alta un Equip en la base de dades. Si ja n'hi ha alguna amb el seu mateix id, llenca una excepcio.
     * @param escuderia equip motoGP a donar d'alta
     * @throws gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau duplicada.
     */
    public void inserir(Equip escuderia) throws GestorException, XQException  {
       try {
            if(obtenirEquip(escuderia.getId())==null){  // no hi es: correcte
                XQExpression expr=con.createExpression();
                expr.executeQuery("insert node "+Utilitats.formaEquipXML(escuderia)+" into "+ARREL+"equips");
                expr.close();
            }
            else {
                throw new GestorException (CLAU_DUPLICADA);
            }
        } catch (XQException ex) {
                throw new GestorException (ex.getMessage());
        } 
    }

    
    
    /**
     * Esborra de la base de dades un equip amb un id determinat
     * @param idEquip id de l'equip  a esborrar
     * @throws gestors.GestorException si el codi no correspon a cap equip de la base de dades
     * o hi ha un error en l'acces a la base de dades
     */
    public void eliminar(int idEquip) throws GestorException {
       try {
            if(obtenirEquip(idEquip)==null){
                throw new GestorException (CLAU_INEXISTENT);
            }

            // existeix: l'esborrem
            XQExpression expr=con.createExpression();            
            expr.executeQuery("delete node "+ARREL+"equips/equip[@id = \""+idEquip+"\" ]");
            expr.close();
       } catch (XQException ex) {
            throw new GestorException (ex.getMessage());
       }
    }


    
    /**
     * Obte l'equip de la base de dades amb un determinat id.
     * @param idEquip identificador de l'equip a obtenir
     * @return equip amb idEquip o bé null si no hi ha cap equip a la base de dades
     * @throws gestors.GestorException en cas d'error a la base de dades
     */
    public Equip obtenirEquip(int idEquip) throws GestorException  {

	try {
            XQExpression expr=con.createExpression();
            XQResultSequence query= expr.executeQuery("for $h in "+ARREL+"equips/equip[@id = \""+idEquip+"\"] return $h");
            
            String primerText=null;
            while(query.next()){
                primerText=query.getItemAsString(null);
            }
            expr.close();
            if(primerText==null){
                return null;
            }else{
                return Utilitats.obteEquip(primerText);
        }} catch (XQException ex) {
            throw new GestorException (ex.getMessage());
        }
    }

    
    /**
     * Obté una llista amb els equips de la base de dades que compten amb un podi
     * @param podi podi utilitzat per a obtenir la llista dels equips que han fet podi al GP 2020
     * @return Llista amb els equips de la base de dades que contenen un podi al seu array de llistat de podis
     * @throws gestors.GestorException en cas d'error a la base de dades
     */
    public List<Equip> obtenirEquipPerPodi(String podi) throws GestorException  {
        try {
            XQExpression expr=con.createExpression();
            XQResultSequence query=(XQResultSequence) expr.executeQuery("for $h in "+ARREL+"equips/equip[podis/podi = \""+podi+"\"] return $h");

            List<Equip> llista = new ArrayList<>();
            
            while(query.next()){
                llista.add(Utilitats.obteEquip(query.getItemAsString(null)));
            }
            expr.close();
            return llista;
        } catch (XQException ex) {
            throw new GestorException (ex.getMessage());
        }
    }

}