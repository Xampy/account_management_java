/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banxclient.views.core.table;

import banxclient.models.entity.BankAccount;
import banxclient.models.tableModel.AccountsTableModel;
import banxclient.views.core.AbstractTableWidget;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author Software
 */
public class AccountsTableWidget extends AbstractTableWidget {

    public interface AccountsTableListener {
        void onSeeMoreClicked(BankAccount account);
    }

    private AccountsTableListener listener;

    public AccountsTableWidget(int width, int height, TableModel model, AccountsTableListener listener) {
        super(width, height, model);
        // TODO Auto-generated constructor stub

        this.listener = listener;
        this.scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.scrollPane.setBackground(Color.WHITE);
        this.table.setDefaultRenderer(
                JButton.class, new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof JButton) {
                    return (JButton) value;
                } else {
                    return this;
                }
            }
        });

        this.table.setDefaultRenderer(
                Object.class,
                new TableBodyRenderer(this.table.getDefaultRenderer(Object.class)));

        this.table.getColumn("ACTIONS").setCellRenderer(
                new MarginedButtonRenderer(100, 40, "Voir détail"));
        this.table.getColumn("ACTIONS").setCellEditor(new ButtonEditor(new JTextField()));
        this.table.getTableHeader().setBorder(BorderFactory.createEmptyBorder());
        this.table.getTableHeader().setForeground(Color.GRAY);
        this.table.getTableHeader().setPreferredSize(new Dimension((width - 75) / 4, 30));
        this.table.getTableHeader().setOpaque(false);
        this.table.getTableHeader().setDefaultRenderer(
                new TableHeaderRenderer(this.table.getTableHeader().getDefaultRenderer()));
        this.table.getTableHeader().setBackground(Color.white);
        this.table.setSelectionBackground(Color.decode("#F5F5F5"));

        this.table.setGridColor(Color.decode("#F5F5F5"));
        this.table.setShowVerticalLines(false);
        this.table.setRowHeight(40);

        //tableau.setTableData(   data, title);
        this.table.setBorder(BorderFactory.createEmptyBorder());
    }
    
    
    

    public JTable getTable() {
        return this.table;
    }
    
    
    
    
    
    
    

    class ButtonEditor extends DefaultCellEditor {

        protected JButton button;
        //private TableButtonListener listener = new TableButtonListener();
        private MButtonListener listener = new MButtonListener();

        public ButtonEditor(JTextField arg0) {
            super(arg0);
            this.button = new JButton("Voir Détail");
            this.button.setOpaque(true);
            this.button.addActionListener(listener);

        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.listener.setRow(row);
            this.listener.setAccount(
                    (BankAccount) ((AccountsTableModel) table.getModel()).getRowAt(row));
            System.out.println("Row Col " + row + " " + column + " " + value.getClass());
            return this.button;

        }
        
        
        class MButtonListener implements ActionListener {
            private int row;
            private BankAccount account;

            public void setRow(int row) {
                this.row = row;
            }

            public void setAccount(BankAccount ac) {
                this.account = ac;
            }
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (AccountsTableWidget.this.listener != null) {
                    AccountsTableWidget.this.listener.onSeeMoreClicked(this.account);
                }
            }
        }

        /*class MButtonListener implements ActionPerfomed {

            private int row;
            private BankAccount account;

            public void setRow(int row) {
                this.row = row;
            }

            public void setAccount(BankAccount ac) {
                this.account = ac;
            }

            @Override
            public void mouseClicked(MouseEvent arg0) {
                // TODO Auto-generated method stub
                System.out.println("Clicked made " + this.row + " " + this.account);

                //Ask the main to switch 
                //to account detail window
                if (AccountsTableWidget.this.listener != null) {
                    AccountsTableWidget.this.listener.onSeeMoreClicked(this.account);
                }

            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

        }*/
    }

    class TableHeaderRenderer implements TableCellRenderer {

        private final TableCellRenderer baseRenderer;

        public TableHeaderRenderer(TableCellRenderer baseRenderer) {
            this.baseRenderer = baseRenderer;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JComponent c = (JComponent) this.baseRenderer.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);
            c.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
            return c;
        }
    }

    class TableBodyRenderer implements TableCellRenderer {

        protected final TableCellRenderer baseRenderer;

        public TableBodyRenderer(TableCellRenderer baseRenderer) {
            this.baseRenderer = baseRenderer;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JComponent c = (JComponent) this.baseRenderer.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);
            c.setBorder(BorderFactory.createEmptyBorder());
            return c;
        }
    }

    class MarginedButtonRenderer extends JButton implements TableCellRenderer {

        public MarginedButtonRenderer(int width, int height, String text) {
            super(text);
            // TODO Auto-generated constructor stub
        }

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }

    }

}
