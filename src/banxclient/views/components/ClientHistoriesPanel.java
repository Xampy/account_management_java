/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banxclient.views.components;

import banxclient.views.MainWindow;
import banxclient.views.deigns.panels.AbstractClientHistoriesPanel;
import banxclient.xorm.DbRaw;
import banxclient.xorm.factory.DaoFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author Software
 */
public class ClientHistoriesPanel extends AbstractClientHistoriesPanel {

    public ClientHistoriesPanel() {
        super();

        Object[][] data = {
            {"Kathy", "Smith",
                "Snowboarding", new Integer(5), new Boolean(false)},
            {"John", "Doe",
                "Rowing", new Integer(3), new Boolean(true)},
            {"Sue", "Black",
                "Knitting", new Integer(2), new Boolean(false)},
            {"Jane", "White",
                "Speed reading", new Integer(20), new Boolean(true)},
            {"Joe", "Brown",
                "Pool", new Integer(10), new Boolean(false)}
        };

        String[] columnNames = {"First Name",
            "Last Name",
            "Sport",
            "# of Years",
            "Vegetarian"};

        JTable table = new JTable(data, columnNames);

        JScrollPane scrollPane = new JScrollPane(table);
        //scrollPane.setPreferredSize(new Dimension(width, height));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.decode("#F5F5F5")));

        this.tablePanel.add(
                scrollPane
        );

        this.updatePieChart();
    }

    public void updatePieChart() {
        DbRaw db = DaoFactory.getDbRaw();

        String query = "";

        //Get all accounts deposit
        query = "SELECT payment_way, COUNT(*) AS total ";
        query += "FROM `account_operation` ";
        query += "INNER JOIN `bank_account` ";
        query += "ON account_operation.account_id=bank_account.id ";
        query += "INNER JOIN `bank_user` ";
        query += "ON bank_account.bank_user_id=bank_user.id ";
        query += "WHERE ";
        query += "bank_account.id=" + MainWindow.userAccount.getId();
        query += " GROUP by account_operation.payment_way;";

        try {
            //Result
            ResultSet res = db.execute(query);
            if (res != null) {

                DefaultPieDataset dataset = new DefaultPieDataset();

                HashMap<String, Double> map = new HashMap<String, Double>();
                map.put("Espèce", new Double(0));
                map.put("Carte bancaire", new Double(0));
                map.put("Chèque", new Double(0));

                while (res.next()) {
                    int total = res.getInt("total");
                    String payUse = res.getString("payment_way");
                    map.put(payUse, new Double(total));

                    dataset.setValue("Espèce", new Double(45));
                }

                for (String key : map.keySet()) {
                    dataset.setValue(key, new Double(map.get(key)));
                }

                JFreeChart chart = ChartFactory.createPieChart(
                        "", // chart title 
                        dataset, // data    
                        false, // include legend   
                        true,
                        false);
                chart.getPlot().setBackgroundPaint(new Color(255, 255, 255));
                chart.getPlot().setOutlineVisible(false);
                
                ChartPanel chartPanel = new ChartPanel(chart);
                chartPanel.setPreferredSize(new Dimension(100, 100));

                this.operationsPieChartPanel.add(
                        chartPanel
                );

            }
        } catch (Exception ex) {
            Logger.getLogger(ClientAccountsListPanel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Mis à jour des données", JOptionPane.ERROR_MESSAGE);
        }
    }
}
