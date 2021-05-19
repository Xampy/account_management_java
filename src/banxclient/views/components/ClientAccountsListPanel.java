/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banxclient.views.components;

import banxclient.models.entity.BankAccount;
import banxclient.models.entity.BankUser;
import banxclient.models.tableModel.AccountsTableModel;
import banxclient.utils.ObsActionEnum;
import banxclient.utils.ObservableInterface;
import banxclient.utils.ObserverInterface;
import banxclient.views.MainWindow;
import banxclient.views.core.table.AccountsTableWidget;
import banxclient.views.core.table.AccountsTableWidget.AccountsTableListener;
import banxclient.views.deigns.panels.AbstractAccountListPanel;
import banxclient.xorm.DbRaw;
import banxclient.xorm.factory.DaoFactory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author Software
 */
public class ClientAccountsListPanel extends AbstractAccountListPanel implements ObservableInterface {

    /**
     * Data Model
     */
    private AccountsTableModel dataModel;
    private ArrayList<ObserverInterface> observers;

    public ClientAccountsListPanel() {
        super();
        this.observers = new ArrayList<ObserverInterface>();

        transcationChart();
        setAccountTable();
    }

    private void setAccountTable() {
        String title[] = {"NUMERO", "TYPE", "SOLDE", "ACTIONS"};
        BankUser user = new BankUser();
        System.out.println("MainWindow " + MainWindow.userAccount.getBankUserId());
        user.setId( MainWindow.userAccount.getBankUserId() );
        
        BankAccount[] data = null;
        try {
            data = DaoFactory.getBankAccountRepository().findAll(user);
        } catch (Exception ex) {
            Logger.getLogger(ClientAccountsListPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.dataModel = new AccountsTableModel(data, title);

        AccountsTableWidget tableau = new AccountsTableWidget(
                594,//ClientAccountsListPanel.this.tableContainerPanel.getWidth(),
                233,//ClientAccountsListPanel.this.tableContainerPanel.getHeight(),
                this.dataModel,
                new AccountsTableListener() {
            @Override
            public void onSeeMoreClicked(BankAccount account) {
                System.out.println("Click on see more");
                
                //First dispacth main window to selectec the current
                //account
                MainWindow.userAccount = account;
                
                ClientAccountsListPanel.this.updateObervers(
                        ObsActionEnum.VIEW_SWITCH_TO_SINGLE_ACCOUNT_VIEW,
                    MainWindow.MAIN_WINDOW_ACCOUNT_DETAIL_PANEL_NAME);
            }
        }
        );
        this.tableContainerPanel.setBackground(Color.WHITE);
        this.tableContainerPanel.add(tableau, BorderLayout.CENTER);
        
        
        this.updateStats();
    }
    
    
    
    /**
     * Update the accounts stats
     * Grouping all account
     */
    public void updateStats(){
        DbRaw db = DaoFactory.getDbRaw();
        
        //Get all accounts count
        String query = "SELECT COUNT(*)as total_account FROM `bank_account`";
        query += "WHERE bank_account.bank_user_id=1;";
        
        
        try {
            //Result
            ResultSet res = db.execute(query);
            if ( res != null){
                while(res.next()){
                    int totalAccount = res.getInt("total_account");
                    this.totalAccountLabel.setText( String.valueOf(totalAccount) );
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ClientAccountsListPanel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Mis à jour des données", JOptionPane.ERROR_MESSAGE);
        }
        
        
        //Get all accounts deposit
        query = "SELECT COUNT(*) as total_deposit ";
        query += "FROM `account_operation` ";
        query += "INNER JOIN `bank_account` ";
        query += "ON account_operation.account_id=bank_account.id ";
        query += "INNER JOIN `bank_user` ";
        query += "ON bank_account.bank_user_id=bank_user.id ";
        query += "WHERE " ;
        query += "bank_user.id=1 AND account_operation.amount_af > account_operation.amount_bf;";
        
        
        try {
            //Result
            ResultSet res = db.execute(query);
            if ( res != null){
                while(res.next()){
                    int total = res.getInt("total_deposit");
                    this.totalDepositLabel.setText( String.valueOf(total) );
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ClientAccountsListPanel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Mis à jour des données", JOptionPane.ERROR_MESSAGE);
        }
        
        //Get all accounts withdraw
        query = "SELECT COUNT(*) as total_withdraw ";
        query += "FROM `account_operation` ";
        query += "INNER JOIN `bank_account` ";
        query += "ON account_operation.account_id=bank_account.id ";
        query += "INNER JOIN `bank_user` ";
        query += "ON bank_account.bank_user_id=bank_user.id ";
        query += "WHERE " ;
        query += "bank_user.id=1 AND account_operation.amount_af < account_operation.amount_bf;";
        
        
        try {
            //Result
            ResultSet res = db.execute(query);
            if ( res != null){
                while(res.next()){
                    int total = res.getInt("total_withdraw");
                    this.totalWithdrawLabel.setText( String.valueOf(total) );
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ClientAccountsListPanel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Mis à jour des données", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    
    
    
    
    
    

    private void transcationChart() {
        JFreeChart chart = createChart(createDataset());
        chart.getPlot().setBackgroundPaint(new Color(255, 255, 255));
        chart.getPlot().setOutlineVisible(false);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(100, 100));

        this.statsChartPanel.add(
                chartPanel
        );
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(new Double(45), "r", "Jan");
        dataset.setValue(new Double(5), "d", "Jan");

        dataset.setValue(new Double(45), "r", "Fev");
        dataset.setValue(new Double(20), "d", "Fev");

        dataset.setValue(new Double(45), "r", "Mar");
        dataset.setValue(new Double(20), "d", "Mar");

        dataset.setValue(new Double(45), "r", "Avr");
        dataset.setValue(new Double(20), "d", "Avr");

        dataset.setValue(new Double(45), "r", "Mai");
        dataset.setValue(new Double(20), "d", "Mai");

        dataset.setValue(new Double(45), "r", "Jui");
        dataset.setValue(new Double(20), "d", "Jui");

        dataset.setValue(new Double(45), "r", "Juil");
        dataset.setValue(new Double(20), "d", "Juil");

        dataset.setValue(new Double(45), "r", "Aoû");
        dataset.setValue(new Double(20), "d", "Aoû");

        dataset.setValue(new Double(45), "r", "Sep");
        dataset.setValue(new Double(20), "d", "Sep");

        dataset.setValue(new Double(45), "r", "Oct");
        dataset.setValue(new Double(20), "d", "Oct");

        dataset.setValue(new Double(45), "r", "Nov");
        dataset.setValue(new Double(20), "d", "Nov");

        dataset.setValue(new Double(45), "r", "Dec");
        dataset.setValue(new Double(20), "d", "Dec");
        return dataset;
    }

    private static JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "", // chart title
                "",
                "",
                dataset, // data   
                PlotOrientation.VERTICAL,
                false, // include legend   
                true,
                false);

        return chart;
    }

    @Override
    public void addObserver(ObserverInterface obs) {
        try {
            this.observers.add(obs);
        } catch (Exception e) {
            System.err.println("Exception occured while adding observer ");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateObervers(ObsActionEnum action, Object value) {
        System.out.println(this.getClass().getName() + " is updating observers ");
        try {
            for (ObserverInterface obs : this.observers) {
                obs.update(action, value);
            }
        } catch (Exception e) {
            System.err.println("Exception occured while updating observers... ");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteObserver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
