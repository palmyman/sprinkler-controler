/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import database.SprinklerDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.TimedSprinkler;

/**
 *
 * @author palmyman
 */
class SprinklerTableModel extends AbstractTableModel {

    List<TimedSprinkler> sprinklers;

    public SprinklerTableModel() {
        refresh();
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Panel";
            case 1:
                return "Index";
            case 2:
                return "Duration";
            case 3:
                return "Id";
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return sprinklers.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int row, int col) {
        TimedSprinkler p = sprinklers.get(row);
        switch (col) {
            case 0:
                return p.getParentPanelId();
            case 1:
                return p.getIndex();
            case 2:
                return p.getTime();
            case 3:
                return p.getId();
        }
        return null;
    }

    public void refresh() {
        try {
            sprinklers = new ArrayList<>(SprinklerDAO.getInstance().getAll());
            fireTableDataChanged();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public void refresh(int programId) {
        try {
            sprinklers = new ArrayList<>(SprinklerDAO.getInstance().getByProgramId(programId));
            fireTableDataChanged();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
}
