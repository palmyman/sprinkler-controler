/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.net.UnknownHostException;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author danecek
 */
public class MainPanel extends JPanel {

    public MainPanel() throws UnknownHostException {        
        setLayout(new BorderLayout());
        add(new JScrollPane(new JTable(new PanelsModel())));
    }
    
}
