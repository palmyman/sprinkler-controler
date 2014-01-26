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
import model.Program;
import model.Sprinkler;
import model.TimedSprinkler;

/**
 *
 * @author palmyman
 */
public class ProgramDAO {
    private Connection conn;
    private PreparedStatement psCreate;
    private PreparedStatement psGetAll;
    private PreparedStatement psGetOne;
    private PreparedStatement psAddSprinkler;
    private PreparedStatement psGetSprinklers;
    private PreparedStatement psGetSprinklersById;
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
            psGetSprinklersById = conn.prepareStatement("SELECT * FROM TIMED_SPRINKLER WHERE PROGRAM_ID = ?");

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
            Program program = new Program(id, name, date, time);
            Collection<TimedSprinkler> timedSprinklers = new ArrayList<>();
            timedSprinklers = getSprinklers(rs.getInt(1));
            for(TimedSprinkler sprinkler : timedSprinklers) {
                program.add(sprinkler);
            }
            programs.add(program);
        }
        return programs;
    }    

    public void addSprinkler(TimedSprinkler sprinkler) throws SQLException {        
        psCreate.setInt(1, sprinkler.getParentPanelId());
        psCreate.setInt(2, sprinkler.getParentProgramId());
        psCreate.setInt(3, sprinkler.getId());
        psCreate.setInt(4, sprinkler.getTime());
        psCreate.execute();
    }
    
    public Collection<TimedSprinkler> getSprinklers() throws SQLException {
        ResultSet rs = psGetSprinklers.executeQuery();
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
    
    public Collection<TimedSprinkler> getSprinklers(int programId) throws SQLException {
        psGetSprinklersById.setInt(1, programId);
        ResultSet rs = psGetSprinklersById.executeQuery();
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
