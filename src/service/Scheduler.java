/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import database.SprinklerDAO;
import gui.MainFrame;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Program;
import model.TimedSprinkler;

/**
 *
 * @author palmyman
 */
public class Scheduler extends TimerTask {

    private Program programToRun;
    private MainFrame mainFrame;

    public Scheduler(MainFrame mainFrame, Program program) {
        this.mainFrame = mainFrame;
        this.programToRun = program;
    }
    
    private void runProgram() {        
        //Set<TimedSprinkler>sprinklers = programToRun.getSprinklers();
        List<TimedSprinkler>sprinklers;
        try {
            sprinklers = new ArrayList<>(SprinklerDAO.getInstance().getByProgramId(programToRun.getId()));
            for (TimedSprinkler sprinkler : sprinklers) {
            try {
                mainFrame.addToLog("Starting sprinkler " + sprinkler.getIndex() + " at Control Panel " + sprinkler.getParentPanelId());
                sprinkler.sprinkle();
                mainFrame.addToLog("Stoping sprinkler " + sprinkler.getIndex() + " at Control Panel " + sprinkler.getParentPanelId());
            } catch (InterruptedException ex) {
                Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        } catch (SQLException ex) {
            Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    @Override
    public void run() {
        mainFrame.addToLog("Starting Program " + programToRun.getName());
        runProgram();
        mainFrame.addToLog("Program " + programToRun.getName() + " finished");
    }
}
