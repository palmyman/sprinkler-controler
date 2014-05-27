/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Program;
import model.TimedSprinkler;

/**
 *
 * @author palmyman
 */

/**
 * @Class ProgramDAO - Data access object for Program
 */
public class ProgramDAO extends DAO {

    private static ProgramDAO instance = new ProgramDAO();
    private static PreparedStatement psGetUpcoming;

    /**
     * Instance getter
     * @return instance
     */
    public static ProgramDAO getInstance() {
        return instance;
    }

    private ProgramDAO() {
        try {
            psCreate = conn.prepareStatement("INSERT INTO PROGRAM VALUES(DEFAULT, ?, ?, ?)");
            psUpdate = conn.prepareStatement("UPDATE PROGRAM SET DATE=?, TIME=? WHERE ID=?");
            psDelete = conn.prepareStatement("DELETE FROM PROGRAM WHERE ID=?");
            psDeleteChilds = conn.prepareStatement("DELETE FROM SPRINKLER WHERE PROGRAM_ID=?");
            psGetAll = conn.prepareStatement("SELECT * FROM PROGRAM");
            psGetUpcoming = conn.prepareStatement("SELECT * FROM PROGRAM WHERE DATE > CURRENT_DATE OR (DATE = CURRENT_DATE AND TIME >= CURRENT_TIME)");

        } catch (SQLException ex) {
            Logger.getLogger(ControlPanelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Persists new Program
     * @param program Program to create
     * @throws SQLException
     */
    public void create(Program program) throws SQLException {
        psCreate.setString(1, program.getName());
        psCreate.setDate(2, program.getDate());
        psCreate.setTime(3, program.getTime());
        psCreate.execute();
    }

    /**
     * Updates Program
     * @param program Program to update
     * @throws SQLException
     */
    public void update(Program program) throws SQLException {
        psUpdate.setDate(1, program.getDate());
        psUpdate.setTime(2, program.getTime());
        psUpdate.setInt(3, program.getId());
        psUpdate.execute();
    }

    /**
     * Deletes Program by ID
     * @param id ID of Program to delete
     * @throws SQLException
     */
    public void delete(int id) throws SQLException {
        psDeleteChilds.setInt(1, id);
        psDeleteChilds.execute();
        psDelete.setInt(1, id);
        psDelete.execute();
    }

    /**
     * Gets all Program records from database
     * @return Set of all Program records from database
     * @throws SQLException
     */
    public Set<Program> getAll() throws SQLException {
        ResultSet rs = psGetAll.executeQuery();
        Set<Program> programs = new TreeSet<>();
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            Date date = rs.getDate(3);
            Time time = rs.getTime(4);
            Program program = new Program(id, name, date, time);
            Collection<TimedSprinkler> timedSprinklers = new ArrayList<>();
            timedSprinklers = SprinklerDAO.getInstance().getByProgramId(id);
            for (TimedSprinkler sprinkler : timedSprinklers) {
                program.add(sprinkler);
            }
            programs.add(program);
        }
        return programs;
    }

    /**
     * Gets upcoming Program records from database ordered by time
     * @return Set of upcoming Program records from database
     * @throws SQLException
     */
    public Set<Program> getUpcoming() throws SQLException {
        ResultSet rs = psGetUpcoming.executeQuery();        
        Set<Program> programs = new TreeSet<>();
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            Date date = rs.getDate(3);
            Time time = rs.getTime(4);
            Program program = new Program(id, name, date, time);
            Collection<TimedSprinkler> timedSprinklers = new ArrayList<>();
            timedSprinklers = SprinklerDAO.getInstance().getByProgramId(id);
            for (TimedSprinkler sprinkler : timedSprinklers) {
                program.add(sprinkler);
            }
            programs.add(program);
        }
        return programs;
    }
}
