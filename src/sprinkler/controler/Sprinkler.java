/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sprinkler.controler;

import java.util.Objects;

/**
 *
 * @author palmyman
 */
public class Sprinkler {
    private int id, range; //degrees of sprinkler range    
    private ControlPanel parentPanel; //reference to parent panel

    public Sprinkler(int id, ControlPanel parentPanel, int range) {
        if(range < 1 || range > 360) throw new IllegalArgumentException("Range must be int <1;360>."); 
        this.id = id;
        this.parentPanel = parentPanel;
        this.range = range;
    }

    public int getId() {
        return id;
    }

    public int getRange() {
        return range;
    }
    
    public int getParentPanelId() {
        return parentPanel.getId();
    }

    public void setRange(int range) {
        if(range < 1 || range > 360) throw new IllegalArgumentException("Range must be int <1;360>.");
        this.range = range;
    }   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.parentPanel);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sprinkler other = (Sprinkler) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.parentPanel, other.parentPanel)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sprinkler{" + "id=" + id + ", range=" + range + ", parentPanelName=" + parentPanel.getName() + '}';
    }
    
    public void sprinkle() {        
        System.out.println(this);
    }
}
