/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.UnknownHostException;
import javax.swing.JFrame;

/**
 *
 * @author danecek
 */
public class MainFrame extends JFrame {
    
    public MainFrame() throws UnknownHostException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(new MenuBar());
        add(new MainPanel());
        pack();
        setVisible(true);
    }
}
