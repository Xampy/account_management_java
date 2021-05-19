/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banxclient.views;

import banxclient.controllers.LoginFormController;
import banxclient.models.LoginFormModel;
import banxclient.models.entity.BankAccount;
import banxclient.utils.ObsActionEnum;
import banxclient.utils.ObserverInterface;
import banxclient.views.components.ClientAccountsListPanel;
import banxclient.views.components.ClientAccountDetailPanel;
import banxclient.views.components.ClientAddNewAccountPanel;
import banxclient.views.deigns.AbstractMainWindowFrame;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Software
 */
public class MainWindow extends AbstractMainWindowFrame implements ObserverInterface {

    /**
     * The user account
     *
     * Xhen authenticated the current account holden by the user
     */
    public static BankAccount userAccount = null;

    /**
     * Container of detail on a single account
     */
    ClientAccountDetailPanel accountDetailPanel;

    /**
     * Container fo all user acount
     */
    ClientAccountsListPanel clientAccountsListPanel;

    /**
     * Containing adding a new account
     */
    ClientAddNewAccountPanel clientAddNewAccountPanel;

    public static final String MAIN_WINDOW_ACCOUNT_LIST_PANEL_NAME = "LIST";
    public static final String MAIN_WINDOW_ACCOUNT_DETAIL_PANEL_NAME = "DETAIL";
    public static final String MAIN_WINDOW_ACCOUNT_ADD_PANEL_NAME = "ADD_ACCOUNT";

    public MainWindow() {
        super();

        this.clientAccountsListPanel = new ClientAccountsListPanel();
        this.clientAccountsListPanel.addObserver(this);

        if (MainWindow.userAccount != null) {
            this.accountDetailPanel = new ClientAccountDetailPanel();
            this.centerContainerPanel.add(this.accountDetailPanel, MainWindow.MAIN_WINDOW_ACCOUNT_DETAIL_PANEL_NAME);
        }

        this.clientAddNewAccountPanel = new ClientAddNewAccountPanel();

        this.centerContainerPanel.add(this.clientAccountsListPanel, MainWindow.MAIN_WINDOW_ACCOUNT_LIST_PANEL_NAME);
        this.centerContainerPanel.add(this.clientAddNewAccountPanel, MainWindow.MAIN_WINDOW_ACCOUNT_ADD_PANEL_NAME);

        setUp();

    }

    private void setUp() {

        this.accountlistMenuBtnLabel.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                System.out.println("Main window user account " + MainWindow.userAccount.getId());

                MainWindow.this.clientAccountsListPanel = new ClientAccountsListPanel();
                MainWindow.this.clientAccountsListPanel.addObserver(MainWindow.this);
                MainWindow.this.centerContainerPanel.add(MainWindow.this.clientAccountsListPanel, MainWindow.MAIN_WINDOW_ACCOUNT_LIST_PANEL_NAME);

                System.out.println(" click made");
                ((CardLayout) MainWindow.this.centerContainerPanel.getLayout())
                        .show(MainWindow.this.centerContainerPanel, MainWindow.MAIN_WINDOW_ACCOUNT_LIST_PANEL_NAME);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                /*MainWindow.this.quitMenuBtnLabel.setFont( new Font("Sergoe UI", 14, Font.BOLD) );
                MainWindow.this.quitMenuBtnLabel.setForeground(Color.BLACK);*/
            }

            @Override
            public void mouseExited(MouseEvent e) {
                /*MainWindow.this.quitMenuBtnLabel.setFont( new Font("Sergoe UI", 12, Font.PLAIN) );
                MainWindow.this.quitMenuBtnLabel.setForeground(new Color(204,204,204));*/
            }
        });

        this.addAccountBtnLabel1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(" click made");
                ((CardLayout) MainWindow.this.centerContainerPanel.getLayout())
                        .show(MainWindow.this.centerContainerPanel, MainWindow.MAIN_WINDOW_ACCOUNT_ADD_PANEL_NAME);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                /*MainWindow.this.quitMenuBtnLabel.setFont( new Font("Sergoe UI", 14, Font.BOLD) );
                MainWindow.this.quitMenuBtnLabel.setForeground(Color.BLACK);*/
            }

            @Override
            public void mouseExited(MouseEvent e) {
                /*MainWindow.this.quitMenuBtnLabel.setFont( new Font("Sergoe UI", 12, Font.PLAIN) );
                MainWindow.this.quitMenuBtnLabel.setForeground(new Color(204,204,204));*/
            }
        });

    }

    @Override
    public void update(ObsActionEnum action, Object value) {

        if (action == ObsActionEnum.VIEW_SWITCH_TO_SINGLE_ACCOUNT_VIEW) {

            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {

                    MainWindow.this.accountDetailPanel = new ClientAccountDetailPanel();
                    MainWindow.this.centerContainerPanel.add(MainWindow.this.accountDetailPanel, MainWindow.MAIN_WINDOW_ACCOUNT_DETAIL_PANEL_NAME);
                    ((CardLayout) MainWindow.this.centerContainerPanel.getLayout())
                            .show(
                                    MainWindow.this.centerContainerPanel,
                                    MainWindow.MAIN_WINDOW_ACCOUNT_DETAIL_PANEL_NAME);
                }
            });

        }else if (action == ObsActionEnum.UPDATE_USERNAME){
            this.usernameLabel.setText((String) value);
        }

    }

}
