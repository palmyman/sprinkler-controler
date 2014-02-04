/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author palmyman
 */
public class Program implements Comparable<Program> {
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.id;
        hash = 61 * hash + Objects.hashCode(this.date);
        hash = 61 * hash + Objects.hashCode(this.time);
        return hash;
    }

    public boolean equals(Program obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Program other = (Program) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
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

    @Override
    public int compareTo(Program other) {
        Date dateTime = new Date(this.getDate().getTime() + this.getTime().getTime());
        Date dateTimeOther = new Date(other.getDate().getTime() + other.getTime().getTime());
        return dateTime.compareTo(dateTimeOther);
    }

}
