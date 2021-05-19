/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banxclient.models.tableModel;

import banxclient.models.entity.BankAccount;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Software
 */
public class AccountsTableModel extends AbstractTableModel {

    private BankAccount[] data;
    private ArrayList<BankAccount> rows;
    private String[] title;

    public AccountsTableModel(BankAccount[] data, String[] title) {
        this.data = data;
        this.title = title;
        this.rows = new ArrayList<>();

        for (BankAccount ac : data) {
            this.rows.add(ac);
        }
        this.data = this.rows.toArray(this.data);
    }

    @Override
    public String getColumnName(int col) {
        return this.title[col];
    }

    public Class getColumnClass(int col) {
        //this.data[0][col].getClass();

        if (col == 0) {
            return String.class;
        }

        return String.class;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        if (col == 3) {
            return true;
        }
        return false;
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return this.title.length;
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return this.data.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        //return this.data[row][col];
        if (col == 0) {
            return "   " + this.data[row].getNumber();
        } else if (col == 1) {
            return this.data[row].getCategory();
        }
        return this.data[row].getAmount();
    }

    /**
     * Add multiple data row to table data list
     *
     * @param accounts list of {@code BankAccount}
     */
    public void addRows(BankAccount[] accounts) {
        this.resetData();
        for (BankAccount ac : accounts) {
            this.rows.add(ac);
        }
        this.data = this.rows.toArray(this.data);
        this.fireTableDataChanged();
    }

    public void addRow(BankAccount account) {
        this.rows.add(account);
        this.data = this.rows.toArray(this.data);

        this.fireTableDataChanged();
    }

    /**
     * Erase all data in the table
     *
     */
    public void resetData() {
        this.rows.clear();
        this.fireTableDataChanged();
    }

    public Object getRowAt(int row) {
        return this.data[row];
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        // TODO Auto-generated method stub
        if (!this.getColumnName(col).equals("ACTIONS")) {
            //this.data[row][col] = value;
            if (col == 0) {
                this.data[row].setNumber((String) value);
            } else if (col == 1) {
                this.data[row].setCategory((String) value);
            } else if (col == 2) {
                this.data[row].setAmount((Integer) value);
            }

        }
    }

}
