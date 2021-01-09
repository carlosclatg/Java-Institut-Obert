package ioc.dam.m6.uf4.eac5.aplicacio.dao;

import ioc.dam.m6.uf4.eac5.aplicacio.Comarca;
import ioc.dam.m6.uf4.eac5.aplicacio.Municipi;
import ioc.dam.m6.persistencia.AbstractJdbcDaoSimplificat;
import ioc.dam.m6.persistencia.JdbcPreparedDao;
import ioc.dam.m6.persistencia.JdbcPreparedQueryDao;
import ioc.dam.m6.persistencia.JdbcQueryDao;
import ioc.dam.m6.persistencia.UtilitatJdbcPlus;
import ioc.dam.m6.persistencia.excepcions.UtilitatPersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

/**
 *
 * @author Professor
 * 
 */
public class MunicipiDao extends AbstractJdbcDaoSimplificat<Municipi> {

   /**
     * Constructor que rep una connexio per parametre.
     * @param connection connexio amb la que aquest objecte treballa
     */
    
    public MunicipiDao(Connection connection) {
        super(connection);

    }

    @Override
    protected boolean esPersistent(final Municipi consistori) throws UtilitatPersistenciaException {
        boolean persis;
        
        JdbcPreparedQueryDao jdbcQueryDao = new JdbcPreparedQueryDao() {

            @Override
            public Object writeObject(ResultSet rlts) throws SQLException {
                return rlts.getInt(1);
            }

            @Override
            public String getStatement() {
                return "select count(id_municipi) from municipi where id_municipi = ?";
            }

            @Override
            public void setParameter(PreparedStatement prdStm) throws SQLException {
                int camp=0;
                prdStm.setInt(++camp, consistori.getId_municipi());
            }
        };
        persis = ((Integer)UtilitatJdbcPlus.obtenirObjecte(con, jdbcQueryDao))>=1;
        
        return persis;
    }

    @Override
    protected void modificar(final Municipi consistori) throws UtilitatPersistenciaException {
        JdbcPreparedDao jdbcPreparedDao = new JdbcPreparedDao() {

            @Override
            public void setParameter(PreparedStatement prdStm) throws SQLException {
                int camp=0;
                prdStm.setString(++camp, consistori.getNom());
                prdStm.setString(++camp, consistori.getVegueria());
                prdStm.setFloat(++camp, consistori.getDensitat());
                if(consistori.getComarca()==null){
                    prdStm.setNull(++camp, Types.INTEGER);                    
                }else{
                    prdStm.setInt(++camp, consistori.getComarca().getId_comarca());
                }
                prdStm.setInt(++camp,consistori.getId_municipi());
            }

            @Override
            public String getStatement() {
                return "update municipi set nom=?, vegueria=?, densitat=?, integrat=? where id_municipi=?";
            }
        };
        UtilitatJdbcPlus.executar(con, jdbcPreparedDao);
    }

    @Override
    protected void inserir(final Municipi consistori) throws UtilitatPersistenciaException {
        JdbcPreparedDao jdbcPreparedDao = new JdbcPreparedDao() {

            @Override
            public void setParameter(PreparedStatement prdStm) throws SQLException {
                int camp=0;
                prdStm.setInt(++camp,consistori.getId_municipi());
                prdStm.setString(++camp, consistori.getNom());
                prdStm.setString(++camp, consistori.getVegueria());
                prdStm.setFloat(++camp, consistori.getDensitat());
                if(consistori.getComarca()==null){
                    prdStm.setNull(++camp, Types.INTEGER);                    
                }else{
                    prdStm.setInt(++camp, consistori.getComarca().getId_comarca());
                }
            }

            @Override
            public String getStatement() {
                return "insert into municipi (id_municipi, nom, vegueria, densitat, integrat) values(?, ?, ?, ?, ?)";
            }
        };
        UtilitatJdbcPlus.executar(con, jdbcPreparedDao);
    }

    @Override
    public Municipi refrescar(final Municipi consistori) throws UtilitatPersistenciaException {
        Municipi comu;
        JdbcPreparedQueryDao jdbcConsultaDao = new JdbcPreparedQueryDao() {

            @Override
            public Object writeObject(ResultSet rlts) throws SQLException {
                int camp=0;
                
                consistori.setNom(rlts.getString(++camp));
                consistori.setVegueria(rlts.getString(++camp));
                consistori.setDensitat(rlts.getFloat(++camp));
                
                Comarca territori=new Comarca();
                territori.setId_comarca(rlts.getInt(++camp));
                if(!rlts.wasNull()){
                    territori.setNom(rlts.getString(++camp));
                    territori.setCapital(rlts.getString(++camp));
                    territori.setSuperficie(rlts.getInt(++camp));
                }else{
                    territori=null;
                }
                consistori.setComarca(territori);
                

                return consistori;
            }

            @Override
            public String getStatement() {
                return "SELECT comu.nom, comu.vegueria, comu.densitat, comu.integrat, contrada.nom, contrada.capital, contrada.superficie"
                        + " FROM municipi comu LEFT JOIN comarca contrada ON comu.integrat=contrada.id_comarca WHERE comu.id_municipi=?";
            }

            @Override
            public void setParameter(PreparedStatement prdStm) throws SQLException {
                int camp=0;
                prdStm.setInt(++camp, consistori.getId_municipi());
            }
        };

        comu = (Municipi) UtilitatJdbcPlus.obtenirObjecte(con, jdbcConsultaDao);
        
        return comu;
    }

    @Override
    public void eliminar(final Municipi consistori) throws UtilitatPersistenciaException {
        JdbcPreparedDao jdbcConsultaDao = new JdbcPreparedDao() {

            @Override
            public void setParameter(PreparedStatement prdStm) throws SQLException {
                int camp=0;
                prdStm.setInt(++camp, consistori.getId_municipi());
            }

            @Override
            public String getStatement() {
                return "delete from municipi where id_municipi = ?";
            }
        };
        UtilitatJdbcPlus.executar(con, jdbcConsultaDao);    
    }

    @Override
    public List<Municipi> obtenirTot() throws UtilitatPersistenciaException {
        
        JdbcQueryDao jdbcConsultaDao = new JdbcQueryDao() {
            
            @Override
            public Object writeObject(ResultSet rlts) throws SQLException {
                int camp=0;
                
                Municipi consistori=new Municipi();
                consistori.setId_municipi(rlts.getInt(++camp));
                consistori.setNom(rlts.getString(++camp));             
                consistori.setVegueria(rlts.getString(++camp));
                consistori.setDensitat(rlts.getFloat(++camp));
                
                Comarca territori=new Comarca();
                territori.setId_comarca(rlts.getInt(++camp));
                if(!rlts.wasNull()){
                    territori.setNom(rlts.getString(++camp));
                    territori.setCapital(rlts.getString(++camp));
                    territori.setSuperficie(rlts.getInt(++camp));
                    
                }else{
                    territori=null;
                }
                consistori.setComarca(territori);
                
                
                return consistori;
            }

            @Override
            public String getStatement() {
                return "SELECT comu.id_municipi, comu.nom, comu.vegueria, comu.densitat, comu.integrat, contrada.nom, contrada.capital, contrada.superficie"
                        + " FROM municipi comu LEFT JOIN comarca contrada ON comu.integrat=contrada.id_comarca";
            }

        };
            List<Municipi> municipis = UtilitatJdbcPlus.obtenirLlista(con, jdbcConsultaDao);        
        
            return municipis;  
    }   

    /**
     * Permet obtenir un llistat amb tots els municipis de la base de dades amb una determinada densitat de població.
     * Inclou les dades de la comarca del municipi.
     * @param habKm2 desnitat en hab/km² que comparteixen els municipis que formaran part del resultat
     * @return llista amb tots els municipis de la base de dades que tenen
     * com a densitat de població l'indicada al paràmetre.
     * @throws UtilitatPersistenciaException si es produeix un error 
     */    
    public List<Municipi> obtenirMunicipisPerDensitat(float habKm2) throws UtilitatPersistenciaException{
        JdbcQueryDao jdbcQueryDao = new JdbcQueryDao() {
            @Override
            public Object writeObject(ResultSet rlts) throws SQLException {
                int camp=0;
                Municipi consistori=new Municipi();
                consistori.setId_municipi(rlts.getInt(++camp));
                consistori.setNom(rlts.getString(++camp));
                consistori.setVegueria(rlts.getString(++camp));
                consistori.setDensitat(rlts.getFloat(++camp));
                Comarca contrada=new Comarca();
                contrada.setId_comarca(rlts.getInt(++camp));
                if(!rlts.wasNull()){
                    contrada.setNom(rlts.getString(++camp));
                    contrada.setCapital(rlts.getString(++camp));
                    contrada.setSuperficie(rlts.getInt(++camp));
                }else{
                    contrada=null;
                }
                consistori.setComarca(contrada);
                return consistori;
                }
            @Override
            public String getStatement() {
                return "SELECT comu.id_municipi, comu.nom, comu.vegueria, comu.densitat, comu.integrat, contrada.nom, contrada.capital, contrada.superficie"
                        + " FROM municipi comu LEFT JOIN comarca contrada ON comu.integrat=contrada.id_comarca where comu.densitat='"+habKm2+"'";
            }
        };
        List<Municipi> municipis = UtilitatJdbcPlus.obtenirLlista(con, jdbcQueryDao);        
        
        return municipis;  
    }
    /**
     * Permet obtenir una llista de tots els municipis de la base de dades
     * que formen part d'una comarca amb una determinada locatiltat com a capital. Inclou les dades de la comarca del municipi.
     * @param localitat capital del comarca dels municipis del resultat
     * @return llistat amb tots els municipis de la base de dades que formen part
     * d'una comarca amb la capital que indicda pel parametre.
     * @throws UtilitatPersistenciaException si es produeix un error 
     */       
    public List<Municipi> obtenirMunicipisPerCapitalComarca(String localitat) throws UtilitatPersistenciaException{   
       JdbcQueryDao jdbcQueryDao = new JdbcQueryDao() {
            @Override
            public Object writeObject(ResultSet rlts) throws SQLException {
                int camp=0;
                Municipi consistori=new Municipi();
                consistori.setId_municipi(rlts.getInt(++camp));
                consistori.setNom(rlts.getString(++camp));
                consistori.setVegueria(rlts.getString(++camp));
                consistori.setDensitat(rlts.getFloat(++camp));
                Comarca regio=new Comarca();
                regio.setId_comarca(rlts.getInt(++camp));
                if(!rlts.wasNull()){
                    regio.setNom(rlts.getString(++camp));
                    regio.setCapital(rlts.getString(++camp));
                    regio.setSuperficie(rlts.getInt(++camp));
                }else{
                    regio=null;
                }
                consistori.setComarca(regio);
                return consistori;
                }
            
            @Override
            public String getStatement() {
                return "SELECT comu.id_municipi, comu.nom, comu.vegueria, comu.densitat, comu.integrat, contrada.nom, contrada.capital, contrada.superficie"
                        + " FROM municipi comu LEFT JOIN comarca contrada ON comu.integrat=contrada.id_comarca where contrada.capital='"+localitat+"'";
            }
        };
        List<Municipi> municipis = UtilitatJdbcPlus.obtenirLlista(con, jdbcQueryDao);        
        
        return municipis;  
        
        }
    }