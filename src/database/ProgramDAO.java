/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Program;
import model.TimedSprinkler;

/**
 *
 * @author palmyman
 */
public class ProgramDAO extends DAO {            
    private static ProgramDAO instance = new ProgramDAO();
    
    public static ProgramDAO getInstance() {
        return instance;
    }
    
    private ProgramDAO() {
        try {            
            psCreate = conn.prepareStatement("INSERT INTO PROGRAM VALUES(DEFAULT, ?, ?, ?)");
            psGetAll = conn.prepareStatement("SELECT * FROM PROGRAM");            

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
    
    public Collection<Program> getAll() throws SQLException {
        ResultSet rs = psGetAll.executeQuery();
        Collection<Program> programs = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            Date date = rs.getDate(3);
            Time time = rs.getTime(4);
            Program program = new Program(id, name, date, time);
            Collection<TimedSprinkler> timedSprinklers = new ArrayList<>();
            timedSprinklers = SprinklerDAO.getInstance().getByProgramId(rs.getInt(1));
            for(TimedSprinkler sprinkler : timedSprinklers) {
                program.add(sprinkler);
            }
            programs.add(program);
        }
        return programs;
    }        
}
