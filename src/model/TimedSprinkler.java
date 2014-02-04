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
    private int id;
    private int parentProgramId;
    private int time;    

    public int getTime() {
        return time;
    }

    public int getId() {
        return id;
    }
    public TimedSprinkler(Sprinkler sprinkler, int id, int patentProgramId, int time) {        
        super(sprinkler.index, sprinkler.parentPanelId);        
        this.id = id;
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
