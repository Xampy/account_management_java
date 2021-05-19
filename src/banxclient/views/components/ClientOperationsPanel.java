/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banxclient.views.components;

import banxclient.controllers.OpeartionController;
import banxclient.models.entity.AccountOperation;
import banxclient.utils.ObsActionEnum;
import banxclient.utils.ObservableInterface;
import banxclient.utils.ObserverInterface;
import banxclient.views.MainWindow;
import banxclient.views.deigns.panels.AbstractClientOperationsPanel;
import banxclient.xorm.factory.DaoFactory;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Software
 */
public class ClientOperationsPanel extends AbstractClientOperationsPanel implements ObservableInterface {
    
    
    
    private ArrayList<ObserverInterface> observers;
    public ClientOperationsPanel(){
        super();
        this.observers = new ArrayList<ObserverInterface>();
        
        
        this.makeTransactionButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AccountOperation operation = OpeartionController.validate(
                            ClientOperationsPanel.this.typeOperationComboBox.getSelectedItem().toString(),
                            ClientOperationsPanel.this.payementTypeComboBox.getSelectedItem().toString(),
                            ClientOperationsPanel.this.amountTextField.getText(),
                            ClientOperationsPanel.this.themeTextField.getText());
                    
                     if ( DaoFactory.getAccountOperationDaoRepository().create(operation) ){
                        
                         
                         System.out.println("Before operation aamous is " + MainWindow.userAccount.getAmount());
                         
                         
                        //Update the user amount here
                        MainWindow.userAccount.setAmount( operation.getAmountAf() );
                        if ( DaoFactory.getBankAccountRepository().update(MainWindow.userAccount)){
                             
                             
                            //notify the parent
                             ClientOperationsPanel.this.updateObervers(ObsActionEnum.UPDATE_AMOUNT, null);

                             System.out.println("After operation aamous is " + MainWindow.userAccount.getAmount());

                             JOptionPane.showMessageDialog(
                                     ClientOperationsPanel.this,
                                     "Votre Opération de \n"
                                     + ClientOperationsPanel.this.typeOperationComboBox.getSelectedItem().toString()
                                     + " de " + ClientOperationsPanel.this.amountTextField.getText() + "FCFA"
                                     + "\n a été éffectuée avec succès",
                                     "Operation sur compte",
                                     JOptionPane.INFORMATION_MESSAGE);
                         }
                        
                    }else {
                        JOptionPane.showMessageDialog(
                            ClientOperationsPanel.this, "Une erreur inconnue s'est produite\n" 
                                    + "\nVeuillez réessayer plus tard", "Opération sur compte", JOptionPane.ERROR_MESSAGE);
                    }
                     
                     
                } catch (Exception ex) {
                    Logger.getLogger(ClientOperationsPanel.class.getName()).log(Level.SEVERE, null, ex);
                    
                    JOptionPane.showMessageDialog(ClientOperationsPanel.this, ex.getMessage(), "Operation sur compte", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });
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
