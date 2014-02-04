/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import database.ProgramDAO;
import gui.MainFrame;
import java.sql.SQLException;
import java.util.Date;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Program;

/**
 *
 * @author palmyman
 */
public class Scheduler extends Thread {

    private Program programToRun;    
    private MainFrame mainFrame;
    private boolean terminate;

    public Scheduler(MainFrame mainFrame) {
        super("Scheduler");
        this.mainFrame = mainFrame;
        this.terminate = false;
        this.programToRun = new Program(
                0,
                "Unknow", 
                new java.sql.Date(new Date(0).getTime()), 
                new java.sql.Time(((java.util.Date) new Date(0)).getTime()));
        update();
        start();
    }

    public void update() {
        //programToRun = null;
        Set<Program> programs;
        try {
            programs = new TreeSet<>(ProgramDAO.getInstance().getAll());
            Program nowProgram = new Program(
                0,
                "Unknow", 
                new java.sql.Date(new Date(0).getTime()), 
                new java.sql.Time(((java.util.Date) new Date(0)).getTime()));
            for (Program program : programs) {                
                if (program.compareTo(nowProgram) > 0) {
                    continue;
                }
                if (!programToRun.equals(program)) {                
                    programToRun = new Program(program.getId(), program.getName(), program.getDate(), program.getTime());
                    mainFrame.addToLog("Next Program is " + programToRun.getName());
                    break;
                }                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void stopScheduler() {
        terminate = true;
    }

    public void run() {
        Date dateTime = new Date(programToRun.getDate().getTime() + programToRun.getTime().getTime());
        while (!terminate) {
            if(dateTime.after(new Date()))
                new ProgramRun(mainFrame, programToRun);
            try {                
                sleep(500);                
            } catch (InterruptedException e) {
            }
        }
    }
}
