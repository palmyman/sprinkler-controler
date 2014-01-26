/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

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

    public ControlPanel(int id, String name, String host, int sprinklerCount) throws UnknownHostException {
        this.id = id;
        this.name = name;
        this.panelIP = InetAddress.getByName(host);
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
