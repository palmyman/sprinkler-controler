/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import gui.MainFrame;
import java.util.Date;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Program;
import model.TimedSprinkler;

/**
 *
 * @author palmyman
 */
class ProgramRun extends Thread {

    private MainFrame mainFrame;
    private Program program;
    private Set<TimedSprinkler> sprinklers;

    public ProgramRun(MainFrame mainFrame, Program program) {
        this.mainFrame = mainFrame;
        this.program = program;
        this.sprinklers = program.getSprinklers();
    }

    public void run() {
        Date dateTime = new Date(program.getDate().getTime() + program.getTime().getTime());

        for (TimedSprinkler sprinkler : sprinklers) {
            try {
                mainFrame.addToLog("Starting sprinkler " + sprinkler.getIndex() + " at Control Panel " + sprinkler.getParentPanelId());
                sprinkler.sprinkle();
                mainFrame.addToLog("Stoping sprinkler " + sprinkler.getIndex() + " at Control Panel " + sprinkler.getParentPanelId());
            } catch (InterruptedException ex) {
                Logger.getLogger(ProgramRun.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
