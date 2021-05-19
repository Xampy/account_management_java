/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banxclient.views.components;

import banxclient.views.MainWindow;
import banxclient.views.deigns.panels.AbstractClientSoldeChartPanel;
import banxclient.xorm.DbRaw;
import banxclient.xorm.factory.DaoFactory;
import java.awt.Color;
import java.awt.Paint;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Software
 */
public class ClientSoldeChartPanel extends AbstractClientSoldeChartPanel {

    JFreeChart chart = null;

    public ClientSoldeChartPanel() {
        super();

        this.updateLineChart();
        this.setVisible(true);

    }

    public void updateLineChart() {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        DbRaw db = DaoFactory.getDbRaw();

        //Get all accounts deposit
        String query = "";
        query = "SELECT account_operation.amount_af AS amount ";
        query += "FROM `account_operation` ";
        query += "INNER JOIN `bank_account` ";
        query += "ON account_operation.account_id=bank_account.id ";
        query += "INNER JOIN `bank_user` ";
        query += "ON bank_account.bank_user_id=bank_user.id ";
        query += "WHERE ";
        query += "bank_account.id=" + MainWindow.userAccount.getId();
        
        System.out.println(query);

        try {
            //Result
            ResultSet res = db.execute(query);
            if (res != null) {
                int i = 0;
                while (res.next()) {
                    int total = res.getInt("amount");
                    dataset.addValue(total, "solde", String.valueOf(i));
                    i += 1;
                }
            }

            this.chart = ChartFactory.createLineChart(
                    null, // Chart title  
                    "", // X-Axis Label  
                    "", // Y-Axis Label  
                    dataset,
                    PlotOrientation.VERTICAL,
                    false,
                    true,
                    false
            );
            
            chart.setBorderVisible(false);
            chart.getPlot().setBackgroundPaint(new Color(255, 255, 255));
            chart.getPlot().setOutlineVisible(false);
            
            this.soldeChartPanel.removeAll();
            
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setBackground(Color.WHITE);
            chartPanel.setVisible(true);

            this.soldeChartPanel.add(chartPanel);
            this.soldeChartPanel.updateUI();
            
        } catch (Exception ex) {
            Logger.getLogger(ClientAccountsListPanel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Mis à jour des données", JOptionPane.ERROR_MESSAGE);
        }
    }
}
