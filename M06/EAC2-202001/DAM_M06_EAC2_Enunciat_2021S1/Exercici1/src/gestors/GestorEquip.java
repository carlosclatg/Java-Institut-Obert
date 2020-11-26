/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestors;

import model.Equip;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe que gestiona la persistencia dels objectes de la classe tipus equip model.Equip
 * @author alumne
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
    public void inserir(Equip escuderia) throws GestorException, SQLException {

        PreparedStatement comStm = null;

        StringBuilder comStr = new StringBuilder();
        comStr.append("INSERT INTO equip (id, nom, seu, fundada, corredor, podis)");
        comStr.append("VALUES(?,?,?,?,(?,?,?,?),?)");

        comStm = connex.prepareStatement(comStr.toString());

        comStm.setInt(1, escuderia.getId());
        comStm.setString(2, escuderia.getNom());
        comStm.setString(3, escuderia.getSeu());
        comStm.setInt(4, escuderia.getFundada());
        comStm.setString(5, escuderia.getMatricula());
        comStm.setString(6, escuderia.getNomPilot());
        comStm.setInt(7, escuderia.getKg());
        comStm.setString(8, escuderia.getLlocNaixement());
        comStm.setArray(9, connex.createArrayOf("varchar", escuderia.getPodis().toArray()));

        comStm.execute();
        comStm.close();

    }

    
    
    /**
     * Esborra de la base de dades un equip amb un id determinat
     * @param idEquip id de l'equip  a esborrar
     * @throws gestors.GestorException si el codi no correspon a cap equip de la base de dades
     * o hi ha un error en l'acces a la base de dades
     */
       public void eliminar(int idEquip) throws GestorException, SQLException {

           PreparedStatement comStm=null;
           StringBuilder comStr = new StringBuilder();
           comStr.append("DELETE FROM equip WHERE id = ?");
           comStm = connex.prepareStatement(comStr.toString());

           comStm.setInt(1,idEquip);

           if(comStm.executeUpdate()!=1){
               comStm.close();
               throw new GestorException("Non-existing id");
           }

       }


    
    /**
     * Obte l'equip de la base de dades amb un determinat id.
     * @param idEquip identificador de l'equip a obtenir
     * @return equip amb idEquip o bé null si no hi ha cap equip a la base de dades
     * @throws gestors.GestorException en cas d'error a la base de dades
     */
    public Equip obtenirEquip(int idEquip) throws GestorException, SQLException {

        PreparedStatement comStm;
        StringBuilder comStr = new StringBuilder();

        comStr.append("SELECT id, nom, seu, fundada, (corredor).matricula, " +
                "(corredor).nomPilot, (corredor).kg, (corredor).llocNaixement, " +
                "podis " +
                "FROM equip WHERE id = ?");

        comStm = connex.prepareStatement(comStr.toString(),ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

        comStm.setInt(1,idEquip);

        ResultSet rs = comStm.executeQuery();

        if(!rs.next()){
            return null;
        }else if (!rs.isLast()){
            connex.close();
            throw new GestorException("Duplicate key in DB");
        }else{
            return buildEquip(rs);
        }
    }

    
    /**
     * Obté una llista amb els equips de la base de dades que compten amb un podi
     * @param podi podi utilitzat per a obtenir la llista dels equips que han fet podi al GP 2020
     * @return Llista amb els equips de la base de dades que contenen un podi al seu array de llistat de podis
     * @throws gestors.GestorException en cas d'error a la base de dades
     */
    public List<Equip> obtenirEquipPerPodi(String podi) throws GestorException, SQLException {
            PreparedStatement comStm;
            StringBuilder comStr = new StringBuilder();
            comStr.append("SELECT id, nom, seu, fundada, (corredor).matricula, " +
                    "(corredor).nomPilot, (corredor).kg, (corredor).llocNaixement, " +
                    "podis FROM equip " +
                    "WHERE ? = ANY (podis)");

            comStm = connex.prepareStatement(comStr.toString(),ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

            comStm.setString(1,podi);

            ResultSet rs=comStm.executeQuery();

            List<Equip> list = new ArrayList<Equip>();

            if(rs!= null){
                while(rs.next()){
                    list.add(buildEquip(rs));
                }
            }

            return list;
    }

    private Equip buildEquip(ResultSet rs) throws SQLException {
        Equip equip = new Equip();
        equip.setId(rs.getInt("id"));
        equip.setNom(rs.getString("nom"));
        equip.setSeu(rs.getString("seu"));
        equip.setFundada(rs.getInt("fundada"));
        equip.setMatricula(rs.getString("matricula"));
        equip.setNomPilot(rs.getString("nomPilot"));
        equip.setKg(rs.getInt("kg"));
        equip.setLlocNaixement(rs.getString("llocnaixement"));
        equip.getPodis().addAll(Arrays.asList((String[])rs.getArray("podis").getArray()));


        return equip;
    }


}
