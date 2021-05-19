/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banxclient.views;

import banxclient.controllers.LoginFormController;
import banxclient.models.entity.BankUser;
import banxclient.views.deigns.AbstractLoginWindow;
import banxclient.xorm.factory.DaoFactory;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Software
 */
public class LoginFormWindow extends AbstractLoginWindow {

    private final LoginFormController controller;

    public LoginFormWindow(LoginFormController controller) {
        super();

        this.controller = controller;
        
        setUp();

    }

    private void setUp() {
        this.connectionButton.addActionListener((ActionEvent e) -> {
            final String lastname
                    = LoginFormWindow.this.lastnameTextField.getText();
            final String firstname
                    = LoginFormWindow.this.firstnameTextField.getText();
            final String pass
                    = LoginFormWindow.this.passwordjTextField.getText();

            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {

                    try {
                        BankUser user = LoginFormWindow.this.controller.sigininUser(lastname, firstname, pass);
                        System.out.println("Connexioninitialized");
                        
                       
                        MainWindow.userAccount = DaoFactory.getBankAccountRepository().find(user);
                        
                         //Get the user data here
                        MainWindow main = new MainWindow();
                        System.out.println("Account id " + MainWindow.userAccount.getBankUserId());

                        LoginFormWindow.this.dispose();
                        main.setVisible(true);
                        
                    } catch (Exception ex) {
                        Logger.getLogger(LoginFormWindow.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(
                            null,
                            "Vos identifiants sont incorrects...\nVeuilez revoir ça",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                    }

                }
            });
        });
    }

}
