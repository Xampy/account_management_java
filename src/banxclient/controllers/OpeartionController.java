/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banxclient.controllers;

import banxclient.models.entity.AccountOperation;
import banxclient.models.entity.BankOperation;
import banxclient.views.MainWindow;

/**
 *
 * @author Software
 */
public class OpeartionController {

    /**
     * Validate an account operation objectoin before operation to be done.
     *
     * Control the amuont available if not return false
     *
     *
     * @return
     */
    public static AccountOperation validateDeposit(
            String operationType, String paymentUse,
            String _amount, String theme) throws Exception {

        AccountOperation op = new AccountOperation();
        
        int amount;
        
        try {
            amount = Integer.parseInt(_amount);
        }catch (Exception e){
            throw new Exception("La valeur de somme est incorrecte");
        }

        op.setAmount(amount);
        try {
            op.setAccountId(MainWindow.userAccount.getId());
            op.setAmountBf(MainWindow.userAccount.getAmount());
            op.setAmountAf(MainWindow.userAccount.getAmount() - amount);
        } catch (Exception e) {
            throw new Exception("Vous n'avez pas de compte\nEssayer de créer un comte");
        }
        op.setPaymentWay(paymentUse);
        op.setTheme(theme);
        
        op.setAmountBf(MainWindow.userAccount.getAmount());
        op.setAmountAf(MainWindow.userAccount.getAmount() + amount);

        return op;
    }

    public static AccountOperation validateWithdraw(
            String operationType, String paymentUse,
            String _amount, String theme) throws Exception {
        
        int amount;
        
        try {
            amount = Integer.parseInt(_amount);
        }catch (Exception e){
            throw new Exception("La valeur de somme est incorrecte");
        }
        
        if (MainWindow.userAccount.getAmount() - amount < 0) {
            throw new Exception("Impossible de faire le retrait\n" + "La somme demandée excède la somme disponible sur le compte");
        } else {
            AccountOperation op = new AccountOperation();

            op.setAmount(amount);
            try {
                op.setAccountId(MainWindow.userAccount.getId());
                op.setAmountBf(MainWindow.userAccount.getAmount());
                op.setAmountAf(MainWindow.userAccount.getAmount() - amount);
            } catch (Exception e) {
                throw new Exception("Vous n'avez pas de compte\nEssayer de créer un comte");
            }
            
            
            op.setPaymentWay(paymentUse);
            op.setTheme(theme);
            
            op.setAmountBf(MainWindow.userAccount.getAmount());
            op.setAmountAf(MainWindow.userAccount.getAmount() - amount);
            
            return op;
        }
    }

    public static AccountOperation validate(
            String operationType, String paymentUse,
            String amount, String theme) throws Exception {

        if (operationType.equals("Dépot")) {
            return validateDeposit(operationType, paymentUse, amount, theme);
        }

        return validateWithdraw(operationType, paymentUse, amount, theme);

    }

}
