/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banxclient.models.entity;

import java.util.ArrayList;

/**
 *
 * @author Software
 */
public class BankUser {
    private long id;
    private String lastname;
    private String firstname;
    private String password;
    
    /**
     * 
     */
    private ArrayList<BankAccount> accounts;
    
    /**
     * Constructor
     * 
     * @param lastname
     * @param firstname
     * @param password 
     */
    public BankUser(String lastname, String firstname, String password) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.password = password;
        
        this.accounts = new ArrayList<BankAccount>();
    }

    public BankUser() {
    }
    
    
    

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<BankAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<BankAccount> accounts) {
        this.accounts = accounts;
    }
    
    public void addAccount(BankAccount account){
        this.accounts.add(account);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
    
    
    
}
