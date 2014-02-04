/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import gui.MainFrame;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Program;

/**
 *
 * @author palmyman
 */
public class Scheduler extends Thread {

    private Collection<Program> programs;
    //programs = new ArrayList<>(ProgramDAO.getInstance().getALL());
    private int count = 5;
    private MainFrame mainFrame;

    public Scheduler(MainFrame mainFrame) {
        super("Scheduler");
        this.mainFrame = mainFrame;
        start();
    }

    public void run() {
        while (true) {
            mainFrame.addToLog(new Integer(count).toString());
            try {
                sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
            }            
            if (count-- == 0) {
                return;
            }
        }
    }
}
