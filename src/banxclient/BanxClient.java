/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banxclient;

import banxclient.controllers.LoginFormController;
import banxclient.models.LoginFormModel;
import banxclient.models.entity.BankUser;
import banxclient.views.LoginFormWindow;
import banxclient.views.MainWindow;
import banxclient.views.deigns.AbstractMainWindowFrame;

/**
 *
 * @author Software
 */
public class BanxClient {
    
    /**
     * The main user of the app
     * the current connected
     */
    public static BankUser user = null;
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                LoginFormWindow loginView = new LoginFormWindow(
                        new LoginFormController(
                                new LoginFormModel()
                        )
                );

                loginView.setVisible(true);
            }
        });
    }

}
