/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sprinkler.controler;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author palmyman
 */
public class Program implements Comparable<Program> {
    private int id;
    private String name;
    private SortedSet<TimedSprinkler> sprinklers;    

    public Program(int id, String name) {
        this.id = id;
        this.name = name;        
        this.sprinklers = new TreeSet<>();
    }
        
    @Override
    public int compareTo(Program other) {
        return this.name.compareTo(other.name);
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
}
