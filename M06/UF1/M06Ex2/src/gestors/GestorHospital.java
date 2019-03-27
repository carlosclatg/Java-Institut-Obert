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
import model.Hospital;

/**
 * Classe que gestiona la persistencia dels objectes de la classe model.Hospital
 * @author professor
 */
public class GestorHospital {

    private Connection con = null;

    /**
     * Crea un gestor d'Hospital que treballara amb la connexio con
     * @param con connexio a traves de la qual es fan persistents els hospitals
     */
    public GestorHospital(Connection con) {
       this.con = con;
    }
    
    /**
     * Dona d'alta un hospital en la base de dades. Si ja n'hi ha algun amb el seu mateix id, llenca una excepcio.
     * @param as hospital a donar d'alta
     * @throws gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau duplicada.
     */
    public void inserir(Hospital as) throws GestorException  {
        //TODO codificar el metode inserir
    }

  
    
    /**
     * Esborra de la base de dades un hospital que té un identificador determinat
     * @param idHospital id de l'hospital a esborrar
     * @throws gestors.GestorException si el id no correspon a cap hospital de la base de dades
     * o hi ha un error en l'acces a la base de dades
     */
   
    public void eliminar(int idHospital) throws GestorException {
       //TODO codificar el metode eliminar

   
    
    /**
     * Obte l'hospital de la base de dades amb un determinat id.
     * @param idHospital id de l'hospital a obtenir
     * @return hospital amb <b>idHospital</b> o null si no hi ha cap hospital a la base de dades
     * @throws gestors.GestorException en cas d'error a la base de dades
     */
   
    public Hospital obtenirHospital(int idHospital) throws GestorException  {
        try {
            List<Hospital> lp=new ArrayList<>();
            PreparedStatement ps=con.prepareStatement("SELECT id, director, (hospital).nom,);
            ps.setInt(1, idHospital);
            ResultSet rs=ps.executeQuery();
            
            if(rs.next()){
                return recuperaHospital(rs);
            }
        } catch (Exception e){
            
        }
	return null; // s'ha posat perque es pugui compilar; cal substituir-lo pel codi correcte
    }

    /**
     * Obté una llista amb els hospitals de la base de dades que donen assistència per una especialitat determinada
     * @param especial especialitat utilitzada per a obtenir la llista
     * @return llista amb els hospitals de la base de dades que contenen <b>especial</b> al seu array de llistes d'especialitats
     * @throws gestors.GestorException en cas d'error a la base de dades
     */
    
    public List<Hospital> obtenirHospitalPerEspecialitats(String especial) throws GestorException  {
        //TODO codificar el metode obtenirHospitalPerEspecialitats
        return null; // s'ha posat perque es pugui compilar; cal substituir-lo pel codi correcte
    }
    

}
