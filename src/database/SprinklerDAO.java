/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Sprinkler;
import model.TimedSprinkler;

/**
 *
 * @author palmyman
 */

/**
 * @Class SprinklerDAO - Data access object for Sprinkler
 */
public class SprinklerDAO extends DAO {
    private PreparedStatement psGetByProgramId;
    
    private static SprinklerDAO instance = new SprinklerDAO();
    
    /**
     * Instance getter
     * @return instance
     */
    public static SprinklerDAO getInstance() {
        return instance;
    }
    
    private SprinklerDAO() {
        try {
            psCreate = conn.prepareStatement("INSERT INTO SPRINKLER VALUES(DEFAULT, ?, ?, ?, ?)");
            psDelete = conn.prepareStatement("DELETE FROM SPRINKLER WHERE ID=?");
            psGetAll = conn.prepareStatement("SELECT * FROM SPRINKLER");
            psGetByProgramId = conn.prepareStatement("SELECT * FROM SPRINKLER WHERE PROGRAM_ID=?");

        } catch (SQLException ex) {
            Logger.getLogger(ControlPanelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Persists new TimedSprinkler
     * @param sprinkler TimedSprinkler to persist
     * @throws SQLException
     */
    public void create(TimedSprinkler sprinkler) throws SQLException {        
        psCreate.setInt(1, sprinkler.getParentProgramId());
        psCreate.setInt(2, sprinkler.getParentPanelId());
        psCreate.setInt(3, sprinkler.getIndex());
        psCreate.setInt(4, sprinkler.getTime());
        psCreate.execute();
    }
    
    /**
     * Deletes TimedSprinkler from database by ID
     * @param id ID of TimedSprinkler to delete
     * @throws SQLException
     */
    public void delete(int id) throws SQLException {
        psDelete.setInt(1, id);
        psDelete.execute();
    }
    
    /**
     * Gets all TimedSprinklers from database
     * @return Set of all TimedSprinklers
     * @throws SQLException
     */
    public Set<TimedSprinkler> getAll() throws SQLException {
        ResultSet rs = psGetAll.executeQuery();
        Set<TimedSprinkler> sprinklers = new TreeSet<>();
        while (rs.next()) {
            int id = rs.getInt(1);
            int programId = rs.getInt(2);
            int panelId = rs.getInt(3);
            int index = rs.getInt(4);
            int time = rs.getInt(5);            
            sprinklers.add(new TimedSprinkler(new Sprinkler(id, panelId), id, programId, time));
        }
        return sprinklers;
    }
    
    /**
     * Gets TimedSprinklers by Program ID
     * @param programId ID of planed Program
     * @return Set of TimedSprinklers planed in Program
     * @throws SQLException
     */
    public Set<TimedSprinkler> getByProgramId(int programId) throws SQLException {
        psGetByProgramId.setInt(1, programId);
        ResultSet rs = psGetByProgramId.executeQuery();
        Set<TimedSprinkler> sprinklers = new TreeSet<>();
        while (rs.next()) {
            int id = rs.getInt(1);            
            int panelId = rs.getInt(3);
            int index = rs.getInt(4);
            int time = rs.getInt(5);            
            sprinklers.add(new TimedSprinkler(new Sprinkler(index, panelId), id, programId, time));
        }
        return sprinklers;
    }
}
