/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banxclient.views.components;

import banxclient.utils.ObsActionEnum;
import banxclient.utils.ObserverInterface;
import banxclient.views.MainWindow;
import banxclient.views.deigns.panels.AbstractAccountDetailPanel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import banxclient.utils.ObservableInterface;
import banxclient.xorm.DbRaw;
import banxclient.xorm.factory.DaoFactory;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Software
 */
public class ClientAccountDetailPanel extends AbstractAccountDetailPanel  implements ObservableInterface, ObserverInterface{
    
    
    /**
     * Client solde component 
     */
    private ClientSoldeChartPanel clientSoldePanel;
    
    /**
     * Client operation panel
     */
    private ClientOperationsPanel clientOperationsPanel;
    
    /**
     * Client histories panel
     */
    private ClientHistoriesPanel clientHistoriesPanel;
    
    
    
    //Observers
    private ArrayList<ObserverInterface> observers;
    
    
    //Click status
    
    
    public final String COMPONENT_CLIENT_SOLDE_PANEL = "CLIENT_SOLDE";
    public final String COMPONENT_CLIENT_OPERATION_PANEL = "CLIENT_OPERATION";
    public final String COMPONENT_CLIENT_HISTORIES_PANEL = "CLIENT_Histories";
    
    
    
    public ClientAccountDetailPanel(){
        super();
        
        
        //Construct the client solde view
        this.clientSoldePanel = new ClientSoldeChartPanel();
        
        
        //Construct the client operation view
        this.clientOperationsPanel = new ClientOperationsPanel();
        this.clientOperationsPanel.addObserver(this);
        
        //Construct the client histories view
        this.clientHistoriesPanel = new ClientHistoriesPanel();
        
        
        
        
        
        this.accountDetailContainer.add(this.clientSoldePanel, COMPONENT_CLIENT_SOLDE_PANEL);
        this.accountDetailContainer.add(this.clientOperationsPanel, COMPONENT_CLIENT_OPERATION_PANEL);
        this.accountDetailContainer.add(this.clientHistoriesPanel, COMPONENT_CLIENT_HISTORIES_PANEL);
        
        
        
        this.observers = new ArrayList<ObserverInterface>();
        
        
        
       //[START  handling click]
       
       
       //[START  handling click on sub menu]
       this.soldeButtonLabel.addMouseListener(this.subMenuListener);
       this.operationsButtonLabel.addMouseListener(this.subMenuListener);
       this.historiqueButtonLabel.addMouseListener(this.subMenuListener);
       //[END  handling click on sub menu]
       
       //[END  handling click]
       
       
       
       this.updateStats();
       this.updateData();
    }
    
    
    
    public void updateData(){
        this.accountAmountLabel.setText( String.valueOf(MainWindow.userAccount.getAmount()) + "  $");
        this.accountNumberLabel.setText( MainWindow.userAccount.getNumber() );
        this.accountStatusLabel.setText( MainWindow.userAccount.getStatus() );
    }
    
    
    
    
    private MouseListener subMenuListener = new MouseListener(){
        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel item = (JLabel)e.getSource();
            
            
            
            JLabel[] arr = new JLabel[]{
                ClientAccountDetailPanel.this.soldeButtonLabel,
                ClientAccountDetailPanel.this.operationsButtonLabel,
                ClientAccountDetailPanel.this.historiqueButtonLabel
            };
            for(int i=0; i < 3; i ++){
                if( arr[i].getText().equals(item.getText())){
                    arr[i].setFont(new Font("Sergo UI", Font.BOLD, 18));
                    arr[i].setForeground(Color.BLACK);
                }else {
                    arr[i].setFont(new Font("Sergo UI", Font.PLAIN, 14));
                    arr[i].setForeground(new Color(181,189,200));
                }
            }
            
            
            if (item.getText().equals("Solde")){
                System.out.println("On Solde");
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        ( (CardLayout)ClientAccountDetailPanel.this.
                            accountDetailContainer.getLayout())
                        .show(ClientAccountDetailPanel.this.accountDetailContainer, COMPONENT_CLIENT_SOLDE_PANEL);
                    }
                });
            }else if (item.getText().equals("Opérations")){
                System.out.println("On Opérations");
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        ( (CardLayout)ClientAccountDetailPanel.this.
                            accountDetailContainer.getLayout())
                        .show(ClientAccountDetailPanel.this.accountDetailContainer, COMPONENT_CLIENT_OPERATION_PANEL );
                    }
                });
            }else if (item.getText().equals("Historique")){
                System.out.println("On Historiques");
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        ( (CardLayout)ClientAccountDetailPanel.this.
                            accountDetailContainer.getLayout())
                        .show(ClientAccountDetailPanel.this.accountDetailContainer, COMPONENT_CLIENT_HISTORIES_PANEL );
                    }
                });
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }
    };
    
    
    
    
    
    
    
    
    
    
     /**
     * Update the accounts stats
     * Grouping all account
     */
    public void updateStats(){
        DbRaw db = DaoFactory.getDbRaw();
        
        String query = "";
        
        //Get all accounts deposit
        query = "SELECT COUNT(*) as total_deposit FROM `account_operation` ";
        query += "WHERE " ;
        query += "account_operation.amount_af > account_operation.amount_bf ";
        query += "AND account_operation.account_id=" + MainWindow.userAccount.getId() + ";"  ;
        
        
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
        query = "SELECT COUNT(*) as total_withdraw FROM `account_operation` ";
        query += "WHERE " ;
        query += "account_operation.amount_af < account_operation.amount_bf ";
        query += "AND account_operation.account_id=" + MainWindow.userAccount.getId() + ";";
        
        
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    @Override
    public void addObserver(ObserverInterface obs) {
        try {
            this.observers.add(obs);
	}catch(Exception e){
            System.err.println("Exception occured while adding observer ");
            System.out.println(e.getMessage());
            
            System.exit(1);
	}
    }

    @Override
    public void updateObervers(ObsActionEnum action, Object value) {
        System.out.println(this.getClass().getName() + " is updating observers ");
	try {
            for(ObserverInterface obs: this.observers){
                obs.update(action, value);
            }
	}catch(Exception e){
            System.err.println("Exception occured while updating observers... ");
            System.out.println(e.getMessage());
            System.exit(1);
	}
    }

    @Override
    public void deleteObserver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(ObsActionEnum action, Object value) {
        if( action == ObsActionEnum.UPDATE_AMOUNT){
            this.updateData();
            this.updateStats();
        }
    }
}
