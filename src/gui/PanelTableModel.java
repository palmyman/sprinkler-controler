/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import database.ControlPanelDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.ControlPanel;

/**
 *
 * @author palmyman
 */
class PanelTableModel extends AbstractTableModel {

    List<ControlPanel> panels;

    public PanelTableModel() {
        refresh();
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Id";
            case 1:
                return "Name";
            case 2:
                return "Sprinklers";
            case 3:
                return "IP";
            case 4:
                return "Status";
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return panels.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
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
            case 3:
                return p.getIP();
            case 4:
                return "off-line";
        }
        return null;
    }

    public void refresh() {
        try {
            panels = new ArrayList<>(ControlPanelDAO.getInstance().getAll());
            fireTableDataChanged();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
}
