/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import sprinkler.controler.Program;
import sprinkler.controler.TimedSprinkler;

/**
 *
 * @author palmyman
 */
public class ProgramDAO {
    private Connection conn;
    private PreparedStatement psCreate;
    private PreparedStatement psGetAll;
    private PreparedStatement psAddSprinkler;
    private PreparedStatement psGetSprinklers;
    private static ProgramDAO instance = new ProgramDAO();
    
    public static ProgramDAO getInstance() {
        return instance;
    }
    
    private ProgramDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:derby:/home/palmyman/derby;  create=true");
            DatabaseMetaData md = conn.getMetaData();
            ResultSet rs = md.getTables(null, null, "PROGRAM", null);
            if (!rs.next()) { // tabulka neexistuje
                Statement s = conn.createStatement();
                s.executeUpdate("CREATE TABLE PROGRAM"
                        + "	(ID INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
                        + "	NAME VARCHAR(100),"
                        + "     DATE DATE,"
                        + "     TIME TIME)");
            }
            psCreate = conn.prepareStatement("INSERT INTO PROGRAM VALUES(DEFAULT, ?, ?, ?)");
            psGetAll = conn.prepareStatement("SELECT * FROM PROGRAM");
            
            rs = md.getTables(null, null, "TIMED_SPRINKLER", null);
            if (!rs.next()) { // tabulka neexistuje
                Statement s = conn.createStatement();
                s.executeUpdate("CREATE TABLE TIMED_SPRINKLER"
                        + "	(PROGRAM_ID INT NOT NULL,"                        
                        + "     PANEL_ID INT NOT NULL,"
                        + "     ID INT NOT NULL,"                        
                        + "     SECONDS INT NOT NULL)");
            }
            psAddSprinkler = conn.prepareStatement("INSERT INTO TIMED_SPRINKLER VALUES(?, ?, ?, ?)");
            psGetSprinklers = conn.prepareStatement("SELECT * FROM TIMED_SPRINKLER");

        } catch (SQLException ex) {
            Logger.getLogger(ControlPanelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void create(Program program) throws SQLException {        
        psCreate.setString(1, program.getName());
        psCreate.setDate(2, program.getDate());
        psCreate.setTime(3, program.getTime());
        psCreate.execute();
    }
    
    public Collection<Program> getALL() throws SQLException, UnknownHostException {
        ResultSet rs = psGetAll.executeQuery();
        Collection<Program> programs = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            Date date = rs.getDate(3);
            Time time = rs.getTime(4);
            programs.add(new Program(id, name, date, time));
        }
        return programs;
    }
    
    public void addSprinkler(TimedSprinkler sprinkler) throws SQLException {        
        psCreate.setInt(1, sprinkler.getParentPanel().getId());
        psCreate.setInt(2, sprinkler.getParentProgram().getId());
        psCreate.setInt(3, sprinkler.getId());
        psCreate.setInt(3, sprinkler.getTime());
        psCreate.execute();
    }
}
