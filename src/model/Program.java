/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.Time;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author palmyman
 */
public class Program implements Runnable {
    private int id;
    private String name;
    private Date date;
    private Time time;
    private SortedSet<TimedSprinkler> sprinklers;    

    public Program(int id, String name, Date date, Time time) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.sprinklers = new TreeSet<>();
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }
    
    public boolean add(TimedSprinkler item) {
        return this.sprinklers.add(item);        
    }
    
    public boolean remove(TimedSprinkler item) {
        return this.sprinklers.remove(item);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SortedSet<TimedSprinkler> getSprinklers() {
        return sprinklers;
    }    

    public void setName(String name) {
        this.name = name;
    }
    
    public void runProgram() throws InterruptedException {
        for (TimedSprinkler sprinkler : this.sprinklers) {
            sprinkler.sprinkle();
        }
    }
            
//    @Override
//    public int compareTo(Program other) {
//        return this.name.compareTo(other.name);
//    }

    @Override
    public void run() {
        for (TimedSprinkler sprinkler : this.sprinklers) {
            try {
                sprinkler.sprinkle();
            } catch (InterruptedException ex) {
                Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
