/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banxclient.controllers;

import banxclient.models.LoginFormModel;
import banxclient.models.entity.BankUser;

/**
 *
 * @author Software
 */
public class LoginFormController {
    private LoginFormModel model;

    public LoginFormController(LoginFormModel model) {
        this.model = model;
    }
    
    
    public BankUser sigininUser(String lastname, String firstname, String password) throws Exception{
        return this.model.login(lastname, firstname, password);
    }
    
    
}
