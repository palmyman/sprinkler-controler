/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sprinkler.controler;

/**
 *
 * @author palmyman
 */
public class TimedSprinkler extends Sprinkler {
    private Program parentProgram;
    private int time;

    public Program getParentProgram() {
        return parentProgram;
    }

    public int getTime() {
        return time;
    }
    public TimedSprinkler(Sprinkler sprinkler, int time) {        
        super(sprinkler.id, sprinkler.parentPanel);
        if(time < 1) throw new IllegalArgumentException("Time must be int from N.");
        this.time = time;        
    }
    
    public void sprinkle() throws InterruptedException {
        super.sprinkle(this.time);
    }
    
}
