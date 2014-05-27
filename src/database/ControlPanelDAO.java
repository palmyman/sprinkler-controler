/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import model.ControlPanel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author palmyman
 */

/**
 * @Class ControlPanelDAO - Data access object for ControlPanel 
 */
public class ControlPanelDAO extends DAO {

    private static final ControlPanelDAO instance = new ControlPanelDAO();    

    /**
     * Instance getter
     * @return instance
     */
    public static ControlPanelDAO getInstance() {
        return instance;
    }

    ControlPanelDAO() {
        try {
            psCreate = conn.prepareStatement("INSERT INTO CONTROL_PANEL VALUES(DEFAULT, ?, ?, ?)");
            psUpdate = conn.prepareStatement("UPDATE CONTROL_PANEL SET NAME=?, IP=?, SPRINKLER_COUNT=? WHERE ID=?");
            psDelete = conn.prepareStatement("DELETE FROM CONTROL_PANEL WHERE ID=?");
            psDeleteChilds = conn.prepareStatement("DELETE FROM SPRINKLER WHERE PANEL_ID=?");
            psGetAll = conn.prepareStatement("SELECT * FROM CONTROL_PANEL");
            psGetOne = conn.prepareStatement("SELECT * FROM CONTROL_PANEL WHERE ID=?");
        } catch (SQLException ex) {
            Logger.getLogger(ControlPanelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Persists new ControlPanel
     * @param panel ControlPanel to persist
     * @throws SQLException
     */
    public void create(ControlPanel panel) throws SQLException {
        psCreate.setString(1, panel.getName());
        psCreate.setString(2, panel.getIP());
        psCreate.setInt(3, panel.getSprinklerCount());        
        psCreate.execute();
    }
    
    /**
     * Updates ControlPanel
     * @param panel ControlPanel to update
     * @throws SQLException
     */
    public void update(ControlPanel panel) throws SQLException {
        psUpdate.setString(1, panel.getName());
        psUpdate.setString(2, panel.getIP());
        psUpdate.setInt(3, panel.getSprinklerCount());
        psUpdate.setInt(4, panel.getId());        
        psUpdate.execute();
    }
    
    /**
     * Deletes ControlPanel
     * @param panel ControlPanel to delete
     * @throws SQLException
     */
    public void delete(ControlPanel panel) throws SQLException {
        psDeleteChilds.setInt(1, panel.getId());
        psDeleteChilds.execute();
        psDelete.setInt(1, panel.getId());
        psDelete.execute();
    }

    /**
     * Gets all ControlPanel records from database
     * @return Set of all ControlPanels from database
     * @throws SQLException
     */
    public Collection<ControlPanel> getAll() throws SQLException {
        ResultSet rs = psGetAll.executeQuery();
        Set<ControlPanel> panels = new TreeSet<>();
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String host = rs.getString(3);            
            int count = rs.getInt(4);
            panels.add(new ControlPanel(id, name, host, count));
        }
        return panels;
    }

    /**
     * Gets one ControlPanel from database by ID
     * @param id ID of ControlPanel
     * @return ControlPanel if found, else returns null
     * @throws SQLException
     */
    public ControlPanel getOne(int id) throws SQLException {
        psGetOne.setInt(1, id);
        ResultSet rs = psGetOne.executeQuery();
        ControlPanel panel;
        if (rs.next()) {
            String name = rs.getString(2);
            String host = rs.getString(3);            
            int count = rs.getInt(4);
            panel = new ControlPanel(id, name, host, count);
        } else {
            panel = null;
        }

        return panel;
    }
}
