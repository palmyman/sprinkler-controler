/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sprinkler.controler;

/**
 *
 * @author palmyman
 */
public class Sprinkler {
    private int id, range; //degrees of sprinkler range
    private boolean grass; //true if placed on grass, false if placed on clay
    private double ratio; // multiplyer to water some areas more then others
    private ControlPanel parentPanel; //reference to parent panel

    public Sprinkler(int id, ControlPanel parentPanel, int range, boolean grass, double ratio) {
        if(range < 1) throw new IllegalArgumentException("Range can not be lower then one.");
        if(ratio < 0) throw new IllegalArgumentException("Ratio must be double from N");
        this.id = id;
        this.parentPanel = parentPanel;
        this.range = range;
        this.grass = grass;
        this.ratio = ratio;
    }

    public int getId() {
        return id;
    }

    public int getRange() {
        return range;
    }    

    public boolean isOnGrass() {
        return grass;
    }    

    public double getRatio() {
        return ratio;
    }

    public int getParentPanelId() {
        return parentPanel.getId();
    }

    public void setRange(int range) {
        if(range < 1) throw new IllegalArgumentException("Range can not be lower then one.");
        this.range = range;
    }    

    public void setRatio(double ratio) {
        if(ratio < 0) throw new IllegalArgumentException("Ratio must be double from N");
        this.ratio = ratio;
    }
    
    public void sprincle() {        
        //TODO
    }
}
