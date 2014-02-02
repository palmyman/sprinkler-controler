/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 *
 * @author palmyman
 */
public class ControlPanel implements Comparable<ControlPanel> {

    private int id;
    private String name;
    private String panelIP;
    private int sprinklerCount;

    public int getSprinklerCount() {
        return sprinklerCount;
    }

    public ControlPanel(int id, String name, String host, int sprinklerCount) {
        this.id = id;
        this.name = name;
        this.panelIP = host;
        this.sprinklerCount = sprinklerCount;
    }

    public String getPanelIP() {
        return panelIP;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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
