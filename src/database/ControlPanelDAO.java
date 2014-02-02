/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import model.ControlPanel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author palmyman
 */
public class ControlPanelDAO extends DAO {

    private static ControlPanelDAO instance = new ControlPanelDAO();

    public static ControlPanelDAO getInstance() {
        return instance;
    }

    ControlPanelDAO() {
        try {
            psCreate = conn.prepareStatement("INSERT INTO CONTROL_PANEL VALUES(DEFAULT, ?, ?, ?)");
            psGetAll = conn.prepareStatement("SELECT * FROM CONTROL_PANEL");
            psGetOne = conn.prepareStatement("SELECT * FROM CONTROL_PANEL WHERE ID=?");
        } catch (SQLException ex) {
            Logger.getLogger(ControlPanelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void create(ControlPanel panel) throws SQLException {
        psCreate.setString(1, panel.getName());
        psCreate.setString(2, panel.getPanelIP());
        psCreate.setInt(3, panel.getSprinklerCount());
        psCreate.execute();
    }

    public Collection<ControlPanel> getAll() throws SQLException {
        ResultSet rs = psGetAll.executeQuery();
        Collection<ControlPanel> panels = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String host = rs.getString(3);            
            int count = rs.getInt(4);
            panels.add(new ControlPanel(id, name, host, count));
        }
        return panels;
    }

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
