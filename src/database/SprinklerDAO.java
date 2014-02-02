/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Sprinkler;
import model.TimedSprinkler;

/**
 *
 * @author palmyman
 */
public class SprinklerDAO extends DAO {
    private PreparedStatement psGetByProgramId;
    
    private static SprinklerDAO instance = new SprinklerDAO();
    
    public static SprinklerDAO getInstance() {
        return instance;
    }
    
    private SprinklerDAO() {
        try {
            psCreate = conn.prepareStatement("INSERT INTO SPRINKLER VALUES(DEFAULT, ?, ?, ?, ?)");
            psGetAll = conn.prepareStatement("SELECT * FROM SPRINKLER");
            psGetByProgramId = conn.prepareStatement("SELECT * FROM SPRINKLER WHERE PROGRAM_ID = ?");

        } catch (SQLException ex) {
            Logger.getLogger(ControlPanelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void create(TimedSprinkler sprinkler) throws SQLException {        
        psCreate.setInt(1, sprinkler.getParentPanelId());
        psCreate.setInt(2, sprinkler.getParentProgramId());
        psCreate.setInt(3, sprinkler.getId());
        psCreate.setInt(4, sprinkler.getTime());
        psCreate.execute();
    }
    
    public Collection<TimedSprinkler> getAll() throws SQLException {
        ResultSet rs = psGetAll.executeQuery();
        Collection<TimedSprinkler> sprinklers = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt(1);
            int programId = rs.getInt(2);
            int panelId = rs.getInt(3);
            int time = rs.getInt(4);            
            sprinklers.add(new TimedSprinkler(new Sprinkler(id, panelId), programId, time));
        }
        return sprinklers;
    }
    
    public Collection<TimedSprinkler> getByProgramId(int programId) throws SQLException {
        psGetByProgramId.setInt(1, programId);
        ResultSet rs = psGetByProgramId.executeQuery();
        Collection<TimedSprinkler> sprinklers = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt(1);            
            int panelId = rs.getInt(3);
            int time = rs.getInt(4);            
            sprinklers.add(new TimedSprinkler(new Sprinkler(id, panelId), programId, time));
        }
        return sprinklers;
    }
}
