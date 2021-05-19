/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banxclient.models.entity;

/**
 *
 * @author Software
 */
public class BankAccount {
    private long id = 0;
    private String number = "acc_anonymous";
    private String category;
    private int amount;
    private double rate = 0.0d;
    private String status;
    private long bankUserId;
    
    public BankAccount(){
        
    }

    public BankAccount(String number, String type, int amount, String status) {
        this.number = number;
        this.category = type;
        this.amount = amount;
        this.status = status;
    }
    
    

    public void setId(long id) {
        this.id = id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCategory(String type) {
        this.category = type;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    

    public long getId() {
        return id;
    }
    
    public String getNumber() {
        return number;
    }

    public String getCategory() {
        return this.category;
    }

    public int getAmount() {
        return amount;
    }

    public double getRate() {
        return rate;
    }

    public String getStatus() {
        return status;
    }   

    public long getBankUserId() {
        return bankUserId;
    }

    public void setBankUserId(long bankUserId) {
        this.bankUserId = bankUserId;
    }
    
}
