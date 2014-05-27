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

    /**
     *
     */
    protected int index;

    /**
     *
     */
    protected int parentPanelId;

    /**
     *
     * @param index
     * @param parentPanelId
     */
    public Sprinkler(int index, int parentPanelId) {
        this.index = index;
        this.parentPanelId = parentPanelId;        
    }

    /**
     *
     * @return
     */
    public int getParentPanelId() {
        return parentPanelId;
    }

    @Override
    public int compareTo(Sprinkler other) {
        if (this.parentPanelId == other.parentPanelId) {
            return this.index - other.index;
        }
        return this.parentPanelId - other.parentPanelId;

    }

    /**
     *
     * @return
     */
    public ControlPanel getParentPanel() {        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     */
    public int getIndex() {
        return index;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.index;
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
        if (this.index != other.index) {
            return false;
        }
        if (this.parentPanelId != other.parentPanelId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sprinkler{" + "id=" + index + ", parentPanelId=" + parentPanelId + '}';
    }

    /**
     *
     * @param duration
     * @throws InterruptedException
     */
    public void sprinkle(int duration) throws InterruptedException {                       
        Thread.sleep(duration * 1000);
    }
}
