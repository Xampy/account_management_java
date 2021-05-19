/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banxclient.views;

import banxclient.controllers.LoginFormController;
import banxclient.models.entity.BankUser;
import banxclient.utils.ObsActionEnum;
import banxclient.utils.ObservableInterface;
import banxclient.utils.ObserverInterface;
import banxclient.views.deigns.AbstractLoginWindow;
import banxclient.xorm.factory.DaoFactory;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Software
 */
public class LoginFormWindow extends AbstractLoginWindow implements ObservableInterface {

    private final LoginFormController controller;
    private ArrayList<ObserverInterface> observers;

    public LoginFormWindow(LoginFormController controller) {
        super();

        this.controller = controller;
        this.observers = new ArrayList<>();
        
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
                        MainWindow.userAccount.setBankUserId(user.getId());
                        
                         //Get the user data here
                        MainWindow main = new MainWindow();
                        LoginFormWindow.this.addObserver(main);
                        
                        
                        
                        System.out.println(MainWindow.userAccount.getBankUserId());
                        
                        
                        LoginFormWindow.this.updateObervers(
                                ObsActionEnum.UPDATE_USERNAME, 
                                user.getFirstname() + " " + user.getLastname());
                        
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

    @Override
    public void addObserver(ObserverInterface obs) {
        this.observers.add(obs);
    }

    @Override
    public void updateObervers(ObsActionEnum action, Object value) {
        for(ObserverInterface obs: this.observers){
            obs.update(action, value);
        }
    }

    @Override
    public void deleteObserver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
