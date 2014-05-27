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

    /**
     *
     * @param id
     * @param name
     * @param date
     * @param time
     */
    public Program(int id, String name, Date date, Time time) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.sprinklers = new TreeSet<>();
    }

    /**
     *
     * @return
     */
    public Date getDate() {
        return date;
    }

    /**
     *
     * @return
     */
    public Time getTime() {
        return time;
    }
    
    /**
     *
     * @param item
     * @return
     */
    public boolean add(TimedSprinkler item) {
        return this.sprinklers.add(item);        
    }
    
    /**
     *
     * @param item
     * @return
     */
    public boolean remove(TimedSprinkler item) {
        return this.sprinklers.remove(item);
    }

    /**
     *
     * @return
     */
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

    /**
     *
     * @param obj
     * @return
     */
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

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     *
     * @return
     */
    public SortedSet<TimedSprinkler> getSprinklers() {
        return sprinklers;
    }    

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     *
     * @throws InterruptedException
     */
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
