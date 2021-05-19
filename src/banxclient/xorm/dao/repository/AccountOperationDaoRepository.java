/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banxclient.xorm.dao.repository;

import banxclient.exception.entity.EntityNotFountException;
import banxclient.models.entity.AccountOperation;
import banxclient.xorm.dao.AbstractDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Software
 */
public class AccountOperationDaoRepository extends AbstractDAO<AccountOperation>{
    
    public static final String TABLE_NAME = "account_operation";
    
    public static final String TABLE_COLUMN_PAYMENT_WAY = "payment_way";
    public static final String TABLE_COLUMN_AMOUNT = "amount";
    public static final String TABLE_COLUMN_THEME = "theme";
    public static final String TABLE_COLUMN_ACCOUNT_ID = "account_id";
    public static final String TABLE_COLUMN_AMOUNT_AFTER = "amount_af";
    public static final String TABLE_COLUMN_AMOUNT_BEFORE = "amount_bf";
    
    public AccountOperationDaoRepository(Connection cnctn) {
        super(cnctn);
    }

    @Override
    public boolean create(AccountOperation obj) {
        String query = "INSERT INTO ";
        query += AccountOperationDaoRepository.TABLE_NAME;
        query += "(payment_way, amount, theme, account_id, amount_bf, amount_af )";
        query += " VALUES(?, ?, ?, ?, ?, ?)";
        
        try{
            PreparedStatement stm = this.connect.prepareStatement(query);
            
            stm.setString(1, obj.getPaymentWay());
            stm.setInt(2, obj.getAmount());
            stm.setString(3, obj.getTheme());
            stm.setLong(4, obj.getAccountId());
            stm.setInt(5, obj.getAmountBf());
            stm.setInt(6, obj.getAmountAf());
            
            
            
            stm.execute();
            stm.close();
            
            
            
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(BankUserDaoRepository.class.getName()).log(Level.SEVERE, null, ex);

        }
        
        return true;
    }

    @Override
    public boolean delete(AccountOperation obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(AccountOperation obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AccountOperation find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AccountOperation find(AccountOperation obj) throws EntityNotFountException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AccountOperation[] select() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AccountOperation[] selectWithOptions(int limit, int offset) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getRowsCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
