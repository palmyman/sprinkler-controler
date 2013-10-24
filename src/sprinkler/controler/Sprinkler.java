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
public class Sprinkler implements Comparable<Sprinkler> {
    protected int id; //degrees of sprinkler time    
    protected ControlPanel parentPanel; //reference to parent panel

    public Sprinkler(int id, ControlPanel parentPanel) {        
        this.id = id;
        this.parentPanel = parentPanel;        
        parentPanel.add(this);
    }
    
    @Override
    public int compareTo(Sprinkler other) {
        return this.id - other.id;
    }

    public int getId() {
        return id;
    }

    public String getParentPanelName() {
        return parentPanel.getName();
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
        return "Sprinkler{" + "id=" + id + ", parentPanelName=" + parentPanel.getName() + '}';
    }
    
    public void sprinkle(int time) throws InterruptedException {
        if(time < 1) throw new IllegalArgumentException("Time must be int from N.");
        System.out.println(this);
        int counter = time;
        while(counter > 0) {            
            System.out.println(counter);            
            Thread.sleep(500);
            counter--;
        }
        System.out.println();
    }
}
