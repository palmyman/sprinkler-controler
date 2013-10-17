/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sprinkler.controler;

import java.net.InetAddress;
import java.util.Set;

/**
 *
 * @author palmyman
 */
public class ControlPanel {
    private int id;
    private String name;
    private InetAddress panelIP;
    private boolean sprinkling;
    private Set<Sprinkler> sprinklers;

    public ControlPanel(int id, String name, InetAddress panelIP) {
        this.id = id;
        this.name = name;
        this.panelIP = panelIP;
        this.sprinkling = false;
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

    public boolean isSprinkling() {
        return sprinkling;
    }

    public Set<Sprinkler> getSprinklers() {
        return sprinklers;
    }
}
