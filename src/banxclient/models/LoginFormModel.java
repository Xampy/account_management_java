/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banxclient.models;

import banxclient.exception.entity.EntityNotFountException;
import banxclient.models.entity.BankUser;
import banxclient.xorm.dao.repository.BankUserDaoRepository;
import banxclient.xorm.factory.DaoFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Software
 */
public class LoginFormModel {
    
    BankUserDaoRepository repository = DaoFactory.getBankUserRepository();
    
    public LoginFormModel(){
        
    }
    
    /**
     * Check User existence
     * 
     * @param lastname
     * @param firstname
     * @param password
     * @return BankUser 
     */
    public BankUser login(String lastname, String firstname, String password) throws Exception{
        BankUser user = null;
        try {
            user = this.repository.find(
                    new BankUser(lastname, firstname, password)
            );
        } catch (EntityNotFountException ex) {
            Logger.getLogger(LoginFormModel.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("No user record found");
        }
        
        return user;
    }
}
