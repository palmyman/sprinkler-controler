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

    /**
     *
     * @return
     */
    public int getTime() {
        return time;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param sprinkler
     * @param id
     * @param patentProgramId
     * @param time
     */
    public TimedSprinkler(Sprinkler sprinkler, int id, int patentProgramId, int time) {        
        super(sprinkler.index, sprinkler.parentPanelId);        
        this.id = id;
        this.parentProgramId = patentProgramId;
        this.time = time;        
    }
    
    /**
     *
     * @throws InterruptedException
     */
    public void sprinkle() throws InterruptedException {
        super.sprinkle(this.time);
    }

    /**
     *
     * @return
     */
    public int getParentProgramId() {
        return parentProgramId;
    }
}
