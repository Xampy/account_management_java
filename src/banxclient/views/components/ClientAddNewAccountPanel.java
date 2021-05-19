/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banxclient.views.components;

import banxclient.controllers.CreateAccountController;
import banxclient.models.entity.BankAccount;
import banxclient.views.MainWindow;
import banxclient.views.deigns.panels.AbstractNewAccountPanel;
import banxclient.xorm.factory.DaoFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Software
 */
public class ClientAddNewAccountPanel extends AbstractNewAccountPanel {
    
    public ClientAddNewAccountPanel(){
        super();
        
        
        
        
        this.saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    BankAccount account = CreateAccountController.validate(
                            ClientAddNewAccountPanel.this.accountTypeComboBox.getSelectedItem().toString(),
                            ClientAddNewAccountPanel.this.amountTextField.getText(),
                            ClientAddNewAccountPanel.this.rateTextField.getText());
                    
                    //update account here
                    account.setBankUserId(MainWindow.userAccount.getBankUserId());
                    
                    
                    //Call the repository to save
                    if ( DaoFactory.getBankAccountRepository().create(account) ){
                        JOptionPane.showMessageDialog(
                                null, 
                                "Votre compte de uméro\n"
                                        + account.getNumber() 
                                        + "\n a été crée avec succès",
                                "Création de compte", 
                                JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(
                            null, "Une erreur inconnue s'est produite\n" 
                                    + "\nVeuillez réessayer plus tard", "Creation de compte", JOptionPane.ERROR_MESSAGE);
                    }
                    
                } catch (Exception ex) {
                    Logger.getLogger(ClientAddNewAccountPanel.class.getName()).log(Level.SEVERE, null, ex);
                    
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Creation de compte", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
    }
}
