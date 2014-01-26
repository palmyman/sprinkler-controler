/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author palmyman
 */
public class Sprinkler implements Comparable<Sprinkler> {

    protected int id;
    protected int parentPanelId;

    public Sprinkler(int id, int parentPanelId) {
        this.id = id;
        this.parentPanelId = parentPanelId;        
    }

    public int getParentPanelId() {
        return parentPanelId;
    }

    @Override
    public int compareTo(Sprinkler other) {
        if (this.parentPanelId == other.parentPanelId) {
            return this.id - other.id;
        }
        return this.parentPanelId - other.parentPanelId;

    }

    public ControlPanel getParentPanel() {        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        hash = 79 * hash + this.parentPanelId;
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
        if (this.parentPanelId != other.parentPanelId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sprinkler{" + "id=" + id + ", parentPanelId=" + parentPanelId + '}';
    }

    public void sprinkle(int time) throws InterruptedException {
        if (time < 1) {
            throw new IllegalArgumentException("Time must be int from N.");
        }
        System.out.println(this + "sprinkling for" + time + "s");       
        Thread.sleep(time * 1000);
    }
}
