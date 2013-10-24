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
    private int time;
    public TimedSprinkler(Sprinkler sprinkler, int time, Program program) {        
        super(sprinkler.id, sprinkler.parentPanel);
        if(time < 1) throw new IllegalArgumentException("Time must be int from N.");
        this.time = time;
        program.add(this);
    }
    
    public void sprinkle() throws InterruptedException {
        super.sprinkle(this.time);
    }
    
}
