/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestors;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Equip;

/**
 * Classe que gestiona la persistencia dels objectes de la classe tipus equip model.Equip
 * @author professor
 */
public class GestorEquip {

    private Connection connex = null;

    /**
     * Crea un gestor d'Equip que treballara amb la connexio connex
     * @param con connexio a traves de la qual es fan persistents de els equips
     */
    public GestorEquip(Connection con) {
       this.connex = con;
    }
    
    
    /**
     * Dona d'alta un Equip en la base de dades. Si ja n'hi ha alguna amb el seu mateix id, llenca una excepcio.
     * @param escuderia equip motoGP a donar d'alta
     * @throws gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau duplicada.
     */
    public void inserir(Equip escuderia) throws GestorException  {

        try {
            
            PreparedStatement ps=connex.prepareStatement("INSERT INTO equip (id, nom, seu, fundada, corredor, podis) VALUES (?,?,?,?,(?,?,?,?),?)");
            
            ps.setInt(1, escuderia.getId());
            ps.setString(2, escuderia.getNom());
            ps.setString(3, escuderia.getSeu());
            ps.setInt(4, escuderia.getFundada());
            ps.setString(5, escuderia.getMatricula());
            ps.setString(6, escuderia.getNomPilot());
            ps.setInt(7, escuderia.getKg());
            ps.setString(8, escuderia.getLlocNaixement());
            ps.setArray(9, obteArrayPodis(escuderia));
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            
            throw new GestorException(ex.getMessage());
        }
    }

    
    /**
     * Obté els podis fets a la temporada 2020 en forma d'Array
     * @param escuderia equip del qual obtenim els podis guanyats
     * @return Array amb el elements d'escuderia.getPodis() 
     * @throws GestorException en cas que es produeixi una excepcio al conntrolador JDBC
     */
    private Array obteArrayPodis(Equip escuderia) throws GestorException {

        try {
            List<String> llistatPodis=escuderia.getPodis();
            
            String[] totsPodis = new String[llistatPodis.size()];
            
            totsPodis = llistatPodis.toArray(totsPodis);  
            
            return connex.createArrayOf("varchar", totsPodis);
            
        } catch (SQLException ex) {
            
            throw new GestorException(ex.getMessage());
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
            
            PreparedStatement ps=connex.prepareStatement("DELETE FROM equip WHERE id=?");
            
            ps.setInt(1,idEquip);
            
            if(ps.executeUpdate()==0){
                
                throw new GestorException("L'equip no existeix a la base de dades");
            }
        } catch (SQLException ex) {
            
            throw new GestorException(ex.getMessage());
        }
    }

    /**
     * Crea un objecte amb les dades de la fila actual del ResultSet.
     * Aquest ResultSet ha de conntenir les dades d'una fila de la taula Equip
     * @param rs ResultSet amb les dades de l'equip que volem crear
     * @return l'Equip generat a partir de la fila del ResultSet
     * @throws GestorException si hi ha un error a la base de dades
     */
    private Equip recuperaEquip(ResultSet rs) throws GestorException {
        
        try {
            
            Equip pr = new Equip();
            
            String[] podis = null;
            
            pr.setId(rs.getInt("id"));
            pr.setNom(rs.getString("nom"));
            pr.setSeu(rs.getString("seu"));
            pr.setFundada(rs.getInt("fundada"));
            podis=(String[])rs.getArray("podis").getArray();
            pr.setPodis(Arrays.asList(podis));
            pr.setMatricula(rs.getString("matricula"));
            pr.setNomPilot(rs.getString("nomPilot"));
            pr.setKg(rs.getInt("kg"));
            pr.setLlocNaixement(rs.getString("llocNaixement"));

            return pr;
            
        } catch (SQLException ex) {
            
            throw new GestorException(ex.getMessage());
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
            
            PreparedStatement ps=connex.prepareStatement("SELECT id, nom, seu, fundada, (corredor).matricula, (corredor).nomPilot, (corredor).kg, (corredor).llocNaixement, podis FROM equip WHERE id = ?");
            
            ps.setInt(1, idEquip);
            
            ResultSet rs=ps.executeQuery();
            
            if(rs.next()){
                
                return recuperaEquip(rs);
            }
            
            return null;
            
        } catch (SQLException ex) {
            
            throw new GestorException(ex.getMessage());
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
            
            List<Equip> lp=new ArrayList<>();
            
            PreparedStatement ps=connex.prepareStatement("SELECT id, nom, seu, fundada, (corredor).matricula, (corredor).nomPilot, (corredor).kg, (corredor).llocNaixement, podis FROM equip WHERE ? = ANY (podis)");
            
            ps.setString(1, podi);
            
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                
                lp.add(recuperaEquip(rs));
            }
            
            return lp;
            
        } catch (SQLException ex) {
            
            throw new GestorException(ex.getMessage());
        }
    }
    

}