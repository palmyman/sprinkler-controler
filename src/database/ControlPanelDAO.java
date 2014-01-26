/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.net.InetAddress;
import java.net.UnknownHostException;
import model.ControlPanel;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author palmyman
 */
public class ControlPanelDAO {
    private Connection conn;
    private PreparedStatement psCreate;
    private PreparedStatement psGetAll;
    private PreparedStatement psGetOne;
    private static ControlPanelDAO instance = new ControlPanelDAO();
    
    public static ControlPanelDAO getInstance() {
        return instance;
    }
    
    ControlPanelDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:derby:/home/palmyman/derby;  create=true");
            DatabaseMetaData md = conn.getMetaData();
            ResultSet rs = md.getTables(null, null, "CONTROL_PANEL", null);
            if (!rs.next()) { // tabulka neexistuje
                Statement s = conn.createStatement();
                s.executeUpdate("CREATE TABLE CONTROL_PANEL"
                        + "	(ID INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
                        + "	NAME VARCHAR(100),"
                        + "     IP VARCHAR(20),"
                        + "	SPRINKLER_COUNT INT)");
            }
            psCreate = conn.prepareStatement("INSERT INTO CONTROL_PANEL VALUES(DEFAULT, ?, ?, ?)");
            psGetAll = conn.prepareStatement("SELECT * FROM CONTROL_PANEL");
            psGetOne = conn.prepareStatement("SELECT * FROM CONTROL_PANEL WHERE ID=?");

        } catch (SQLException ex) {
            Logger.getLogger(ControlPanelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void create(ControlPanel panel) throws SQLException {        
        psCreate.setString(1, panel.getName());
        psCreate.setString(2, panel.getPanelIP().getHostAddress());
        psCreate.setInt(3, panel.getSprinklerCount());
        psCreate.execute();
    }
    
    public Collection<ControlPanel> getALL() throws SQLException, UnknownHostException {
        ResultSet rs = psGetAll.executeQuery();
        Collection<ControlPanel> panels = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String host = rs.getString(3);
            //InetAddress ip;
            //ip = InetAddress.getByName(host);
            int count = rs.getInt(4);            
            panels.add(new ControlPanel(id, name, host, count));
        }
        return panels;
    }
    
    public ControlPanel getOne(int id) throws SQLException, UnknownHostException {
        psGetOne.setInt(1, id);
        ResultSet rs = psGetOne.executeQuery();
        ControlPanel panel;
        if(rs.next()) {            
            String name = rs.getString(2);
            String host = rs.getString(3);
            //InetAddress ip;
            //ip = InetAddress.getByName(host);
            int count = rs.getInt(4);
            panel = new ControlPanel(id, name, host, count);
        } else {
            panel = null;
        }
        
        return panel;
    }
}
