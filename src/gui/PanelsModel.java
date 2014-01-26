/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import database.ControlPanelDAO;
import java.net.UnknownHostException;
import model.ControlPanel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author danecek
 */
class PanelsModel extends AbstractTableModel {

    List<ControlPanel> panels;

    public PanelsModel() throws UnknownHostException {
        refresh();
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0: return "Id";
            case 1: return "Name";
            case 2: return "SprinklerCount";
                
        }
        return null;
    }
    
    

    @Override
    public int getRowCount() {
        return panels.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int row, int col) {
        ControlPanel p = panels.get(row);
        switch (col) {
            case 0:
                return p.getId();
            case 1:
                return p.getName();
            case 2:
                return p.getSprinklerCount();
        }
        return null;
    }
    
    public void refresh() throws UnknownHostException {
        try {
            panels =  new ArrayList<>(ControlPanelDAO.getInstance().getALL());
            fireTableDataChanged();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
}
