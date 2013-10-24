/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sprinkler.controler;

import java.net.InetAddress;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author palmyman
 */
public class ControlPanel implements Comparable<ControlPanel> {    
    private String name;
    private InetAddress panelIP;
    private boolean sprinkling;
    private SortedSet<Sprinkler> sprinklers;

    public ControlPanel(String name, InetAddress panelIP) {        
        this.name = name;
        this.panelIP = panelIP;
        this.sprinkling = false;
        this.sprinklers = new TreeSet<>();
    }
    
    @Override
    public int compareTo(ControlPanel other) {
        return this.name.compareTo(other.name);
    }
    
    public String getName() {
        return name;
    }

    public InetAddress getPanelIP() {
        return panelIP;
    }

    public boolean isSprinkling() {
        return sprinkling;
    }

    public SortedSet<Sprinkler> getSprinklers() {
        return sprinklers;
    }
    
    public boolean add(Sprinkler item) {
        return this.sprinklers.add(item);        
    }
    
    public boolean remove(Sprinkler item) {
        return this.sprinklers.remove(item);
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
