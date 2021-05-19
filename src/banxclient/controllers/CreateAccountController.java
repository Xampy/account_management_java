/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banxclient.controllers;

import banxclient.models.entity.AccountOperation;
import banxclient.models.entity.BankAccount;
import banxclient.views.MainWindow;

/**
 *
 * @author Software
 */
public class CreateAccountController {
    
    
    public static BankAccount validate(String category, String amount, 
            String rate) throws Exception{
       
        BankAccount ac = new BankAccount();
        
        try{
            ac.setBankUserId( MainWindow.userAccount.getBankUserId());
        }catch (Exception e) {
            throw e;
        }
        
        try {
            ac.setAmount( Integer.parseInt(amount) );
        }catch (Exception e){
            throw new Exception("La valeur de somme est incorrecte");
        }
        
        try {
            ac.setRate( Double.parseDouble(rate) );
        }catch (Exception e){
            throw new Exception("La valeur de taux est incorrecte");
        }
        
        ac.setCategory(category);
        
        return ac;
       
    }
}
