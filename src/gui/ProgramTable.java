/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import database.ProgramDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.Program;

/**
 *
 * @author palmyman
 */
class ProgramTable extends AbstractTableModel {

    List<Program> programs;

    public ProgramTable() {
        refresh();
        
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0: return "Id";
            case 1: return "Name";
            case 2: return "Date";
            case 3: return "Time";                
        }
        return null;
    }
    
    

    @Override
    public int getRowCount() {
        return programs.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Program p = programs.get(row);
        switch (col) {
            case 0:
                return p.getId();
            case 1:
                return p.getName();
            case 2:
                return p.getDate();
            case 3:
                return p.getTime();
        }
        return null;
    }
    
    public void refresh() {
        try {
            programs =  new ArrayList<>(ProgramDAO.getInstance().getAll());
            fireTableDataChanged();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
}
