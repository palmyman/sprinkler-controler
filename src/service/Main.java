/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import database.ControlPanelDAO;
import gui.MainFrame;
import java.net.UnknownHostException;
import java.sql.SQLException;

/**
 *
 * @author palmyman
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, SQLException {
        ControlPanelDAO.getInstance().create(new model.ControlPanel(1, "ahoj", null, 22));
        new MainFrame();
    }
}
