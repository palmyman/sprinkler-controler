/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sprinkler.controler;

import java.net.InetAddress;
import java.util.Collection;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author palmyman
 */
public class ControlPanel implements Comparable<ControlPanel> {

    private int id;
    private String name;
    private InetAddress panelIP;
    private int sprinklerCount;

    public int getSprinklerCount() {
        return sprinklerCount;
    }

    public ControlPanel(int id, String name, InetAddress panelIP, int sprinklerCount) {
        this.id = id;
        this.name = name;
        this.panelIP = panelIP;
        this.sprinklerCount = sprinklerCount;
    }

    public int getId() {
        return id;
    }    

    public String getName() {
        return name;
    }

    public InetAddress getPanelIP() {
        return panelIP;
    }
    
    @Override
    public int compareTo(ControlPanel other) {
        return this.name.compareTo(other.name);
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.name);
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
        final ControlPanel other = (ControlPanel) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
}
