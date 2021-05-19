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
public class BankOperation {
    /**
     * Identifier
     */
    private int id;
    /**
     * Operation type
     * 
     * "depot" | "retrait" | "transfert"
     */
    private String type;
    /**
     * Operation purpose. Can be empty
     */
    private String theme = "";
    /**
     * Operation amount
     */
    private int amount;
    /**
     * Amoutn before the operation
     */
    private int amountBeforeOperation;
    /**
     * Amoutn after the operation
     */
    private int amountAfterOperation;
    /**
     * Operation method
     * 
     * "
     */
    private String payementUse;
    /**
     * Operation Date
     */
    private String date;
    
    /**
     * Operation completed status
     * By default it's true
     */
    private boolean completed = true;
    
    /**
     * Bank Account number
     */
    private String bankAccountNumero;
    
    
    
    public BankOperation(){
        
    }
}
