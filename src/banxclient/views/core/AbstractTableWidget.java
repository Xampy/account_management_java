/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banxclient.views.core;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author Software
 */


public class AbstractTableWidget extends JPanel {

    protected JTable table;
    protected JScrollPane scrollPane;
    
    public AbstractTableWidget(
        int width, int height,
	TableModel model ) {
	this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.WHITE);
	this.table = new JTable(model);
	this.table.getTableHeader().setBackground(Color.WHITE);
	this.table.setBorder(BorderFactory.createLineBorder(Color.decode("#F5F5F5")));
	this.scrollPane = new JScrollPane(this.table);
	this.scrollPane.setPreferredSize(new Dimension(width, height));
	this.scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.scrollPane.setBackground(Color.WHITE);
	this.scrollPane.setBorder(BorderFactory.createLineBorder(Color.decode("#F5F5F5")));
	this.add(this.scrollPane);
	this.setVisible(true);
		
    }
    
    
    public AbstractTableWidget(
        int width, int height,
        String[] columns, Object[][] data) {
        
	this.setPreferredSize(new Dimension(width, height));
	this.setBackground(Color.WHITE);
	this.table = new JTable(data, columns);
        this.table.setBackground(Color.red);
	this.table.getTableHeader().setBackground(Color.WHITE);
	this.table.setBorder(BorderFactory.createLineBorder(Color.decode("#F5F5F5")));
	this.scrollPane = new JScrollPane(this.table);
	this.scrollPane.setPreferredSize(new Dimension(width, height));
	this.scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
	this.scrollPane.setBorder(BorderFactory.createLineBorder(Color.decode("#F5F5F5")));
	this.add(this.scrollPane);
	this.setVisible(true);
		
    }
	
}
