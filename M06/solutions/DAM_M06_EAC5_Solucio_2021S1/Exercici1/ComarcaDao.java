/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.uf4.eac5.aplicacio.dao;

import ioc.dam.m6.uf4.eac5.aplicacio.Comarca;
import ioc.dam.m6.persistencia.AbstractJdbcDaoSimplificat;
import ioc.dam.m6.persistencia.JdbcPreparedDao;
import ioc.dam.m6.persistencia.JdbcPreparedQueryDao;
import ioc.dam.m6.persistencia.JdbcQueryDao;
import ioc.dam.m6.persistencia.UtilitatJdbcPlus;
import ioc.dam.m6.persistencia.excepcions.UtilitatJdbcSQLException;
import ioc.dam.m6.persistencia.excepcions.UtilitatPersistenciaException;
import ioc.dam.m6.uf4.eac5.aplicacio.Municipi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Professor
 * 
 */
public class ComarcaDao extends AbstractJdbcDaoSimplificat<Comarca> {

    /**
     * Constructor que rep una connexio per parametre.
     * @param connection la connexio amb la que aquest objecte treballa
     */ 
    
    public ComarcaDao(Connection connection) {
        super(connection);
    }

    @Override
    protected boolean esPersistent(final Comarca contrada) throws UtilitatPersistenciaException {
        boolean persis;
        
        JdbcPreparedQueryDao jdbcQueryDao = new JdbcPreparedQueryDao() {
            
            @Override
            public Object writeObject(ResultSet rlts) throws SQLException {
                return rlts.getInt(1);
            }

            @Override
            public String getStatement() {
                return "select count(id_comarca) from comarca where id_comarca=?";
            }

            @Override
            public void setParameter(PreparedStatement prdStm) throws SQLException {
                int camp=0;
                prdStm.setInt(++camp, contrada.getId_comarca());
            }
        };
        persis = ((Integer)UtilitatJdbcPlus.obtenirObjecte(con, jdbcQueryDao))>=1;
        
        return persis;
    }
    

    @Override
    protected void modificar(final Comarca contrada) throws UtilitatPersistenciaException {
       JdbcPreparedDao jdbcPreparedDao = new JdbcPreparedDao() {
            @Override
            public void setParameter(PreparedStatement prdStm) throws SQLException {
                int camp=0;
                prdStm.setString(++camp, contrada.getNom());
                prdStm.setString(++camp,contrada.getCapital());
                prdStm.setInt(++camp, contrada.getSuperficie());
                prdStm.setInt(++camp, contrada.getId_comarca());
            }
            @Override
            public String getStatement() {
                return "update comarca set nom=?, capital=?, superficie=? where id_comarca=?";
            }
        };
        UtilitatJdbcPlus.executar(con, jdbcPreparedDao);
    }

    @Override
    protected void inserir(final Comarca contrada) throws UtilitatPersistenciaException {
        JdbcPreparedDao jdbcPreparedDao = new JdbcPreparedDao() {
            @Override
            public void setParameter(PreparedStatement prdStm) throws SQLException {
                int camp=0;
                prdStm.setInt(++camp,contrada.getId_comarca());
                prdStm.setString(++camp, contrada.getNom());
                prdStm.setString(++camp,contrada.getCapital());
                prdStm.setInt(++camp,contrada.getSuperficie());
                }

            @Override
            public String getStatement() {
                return "insert into comarca(id_comarca, nom, capital, superficie) values(?,?,?,?)";
            }
        };
        UtilitatJdbcPlus.executar(con, jdbcPreparedDao);
    }
    

    @Override
    public Comarca refrescar(final Comarca contrada) throws UtilitatPersistenciaException {
        Comarca regio;
        JdbcPreparedQueryDao jdbcConsultaDao = new JdbcPreparedQueryDao() {
            @Override
            public Object writeObject(ResultSet rlts) throws SQLException {
                int marc=0;
                contrada.setNom(rlts.getString(++marc));
                contrada.setCapital(rlts.getString(++marc));
                contrada.setSuperficie(rlts.getInt(++marc)); 
                
                do{
                   int camp=marc;
                   if(!rlts.wasNull()){
                       Municipi consistori = new Municipi();
                       consistori.setId_municipi(rlts.getInt(++camp));
                       consistori.setNom(rlts.getString(++camp));
                       consistori.setVegueria(rlts.getString(++camp));
                       consistori.setDensitat(rlts.getFloat(++camp));
                       consistori.setComarca(contrada);
                       contrada.getMunicipis().add(consistori);
                   }
                   
                } while (rlts.next());
                
                return contrada;
                
                }

            @Override
            public String getStatement() {
                return "SELECT contrada.nom, contrada.capital, contrada.superficie, comu.id_municipi, comu.nom, comu.vegueria, comu.densitat, comu.integrat"
                        + " FROM municipi comu LEFT JOIN comarca contrada ON comu.integrat=contrada.id_comarca WHERE contrada.id_comarca=?";
            }

            @Override
            public void setParameter(PreparedStatement prdStm) throws SQLException {
                int camp=0;
                prdStm.setInt(++camp, contrada.getId_comarca());
                }
            };

        regio = (Comarca) UtilitatJdbcPlus.obtenirObjecte(con, jdbcConsultaDao);
        
        return regio;
    }

    
    @Override
    public void eliminar(final Comarca contrada) throws UtilitatPersistenciaException {
         JdbcPreparedDao jdbcQueryDao = new JdbcPreparedDao() {

            @Override
            public void setParameter(PreparedStatement prdStm) throws SQLException {
                int camp=0;
                prdStm.setInt(++camp, contrada.getId_comarca());
            }

            @Override
            public String getStatement() {
                return "delete from comarca where id_comarca = ?";
            }
        };
        UtilitatJdbcPlus.executar(con, jdbcQueryDao);    
    }

    @Override
    public List<Comarca> obtenirTot() throws UtilitatPersistenciaException {
     JdbcQueryDao jdbcConsultaDao = new JdbcQueryDao() {

            @Override
            public Object writeObject(ResultSet rlts) throws SQLException {
                Comarca contrada = new Comarca();
                int camp=0;
                contrada.setId_comarca(rlts.getInt(++camp)); 
                contrada.setNom(rlts.getString(++camp));
                contrada.setCapital(rlts.getString(++camp));
                contrada.setSuperficie(rlts.getInt(++camp));
                try {
                   refrescar(contrada);
                } catch (Exception e) {}

                return contrada;
            }

            @Override
            public String getStatement() {
                return "SELECT id_comarca, nom, capital, superficie FROM comarca";
            }
        };      
        List<Comarca> comarques = UtilitatJdbcPlus.obtenirLlista(con, jdbcConsultaDao);
        
        return comarques;      
    }

    /**
     * Permet obtenir una llista de totes les comarques del sistema de
     * persistencia de l'extensió indicada pel parametre.
     * No inclou les dades dels municipis que aquestes comarques tenen assignades.
     * Per obtenir-les, cal refrescar cada comarca.
     * @param extensio superfície em Km^2 que han fet que les comarques formen part del resultat.
     * @return llista amb totes les comarques del sistema de persistencia de
     * de la superfície indicada pel parametre
     * @throws UtilitatJdbcSQLException si es produeix un error 
     */   
    public List<Comarca> obtenirComarquesPerSuperficie(int extensio) throws UtilitatJdbcSQLException {
	JdbcQueryDao jdbcQueryDao = new JdbcQueryDao() {

            @Override
            public Object writeObject(ResultSet rlts) throws SQLException {
                Comarca regio = new Comarca();
                int camp=0;
                regio.setId_comarca(rlts.getInt(++camp)); 
                regio.setNom(rlts.getString(++camp));
                regio.setCapital(rlts.getString(++camp));
                regio.setSuperficie(rlts.getInt(++camp));
                try {
                   refrescar(regio);
                } catch (Exception e) {}

                return regio;
            }

            @Override
            public String getStatement() {
                return "SELECT id_comarca, nom, capital, superficie FROM comarca WHERE superficie ='"+extensio+"'";
            }

        };      
        
        List<Comarca> comarques = UtilitatJdbcPlus.obtenirLlista(con, jdbcQueryDao);   
        
        return comarques;      
    }
}