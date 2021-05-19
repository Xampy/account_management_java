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
public class AccountOperation {

    private long id;
    private String paymentWay;
    private int amount;
    private String theme;
    private long accountId;
    private int amountBf;
    private int amountAf;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(String paymentWay) {
        this.paymentWay = paymentWay;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public int getAmountBf() {
        return amountBf;
    }

    public void setAmountBf(int amountBf) {
        this.amountBf = amountBf;
    }

    public int getAmountAf() {
        return amountAf;
    }

    public void setAmountAf(int amountAf) {
        this.amountAf = amountAf;
    }

}
