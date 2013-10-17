/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sprinkler.controler;

import java.sql.Time;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author palmyman
 */
public class Program {
    private int id;
    private String name;
    private Set<Sprinkler> sprinklers;
    private long oneSpriklerTime;

    public Program(int id, String name, long oneSpriklerTime) {
        this.id = id;
        this.name = name;
        this.oneSpriklerTime = oneSpriklerTime;
    }
    
    public boolean add(Sprinkler item) {
        return this.sprinklers.add(item);        
    }
    
    public boolean remove(Sprinkler item) {
        return this.sprinklers.remove(item);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Sprinkler> getSprinklers() {
        return sprinklers;
    }

    public long getOneSpriklerTime() {
        return oneSpriklerTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOneSpriklerTime(long oneSpriklerTime) {
        this.oneSpriklerTime = oneSpriklerTime;
    }
    
    public void runProgram() {
        for (Sprinkler sprinkler : this.sprinklers) {
            sprinkler.sprinkle();
        }
    }
}
