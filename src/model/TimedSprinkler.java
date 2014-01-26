/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author palmyman
 */
public class TimedSprinkler extends Sprinkler {
    private int parentProgramId;
    private int time;    

    public int getTime() {
        return time;
    }
    public TimedSprinkler(Sprinkler sprinkler, int patentProgramId, int time) {        
        super(sprinkler.id, sprinkler.parentPanelId);
        if(time < 1) throw new IllegalArgumentException("Time must be int from N.");
        this.parentProgramId = patentProgramId;
        this.time = time;        
    }
    
    public void sprinkle() throws InterruptedException {
        super.sprinkle(this.time);
    }

    public int getParentProgramId() {
        return parentProgramId;
    }
}
