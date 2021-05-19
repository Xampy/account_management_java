/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banxclient.xorm.dao.repository;

import banxclient.exception.entity.EntityNotFountException;
import banxclient.models.entity.BankAccount;
import banxclient.models.entity.BankUser;
import banxclient.xorm.dao.AbstractDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Software
 */
public class BankAccountDaoRepository extends AbstractDAO<BankAccount> {

    public static final String TABLE_NAME = "bank_account";

    public static final String TABLE_COLUMN_NUMBER = "numero";
    public static final String TABLE_COLUMN_CATEGORY = "category";
    public static final String TABLE_COLUMN_AMOUNT = "amount";
    public static final String TABLE_COLUMN_RATE = "rate";
    public static final String TABLE_COLUMN_STATUS = "status";
    public static final String TABLE_COLUMN_BANK_USER_ID = "bank_user_id";

    /**
     * Constructor
     *
     * @param conn Databse connection
     */
    public BankAccountDaoRepository(Connection conn) {
        super(conn);
    }

    @Override
    public boolean create(BankAccount obj) {
        String query = "INSERT INTO ";
        query += BankAccountDaoRepository.TABLE_NAME;
        query += "(numero, category, amount, rate, status, bank_user_id)";
        query += " VALUES(?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stm = this.connect.prepareStatement(query);

            stm.setString(1, obj.getNumber()); //Willl be generated from a service
            stm.setString(2, obj.getCategory());
            stm.setInt(3, obj.getAmount());
            stm.setDouble(4, obj.getRate());
            stm.setString(5, obj.getStatus());
            stm.setLong(6, obj.getBankUserId());

            stm.execute();
            stm.close();
            
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(BankUserDaoRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean delete(BankAccount obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(BankAccount obj) {
        String query = "UPDATE ";
        query += BankAccountDaoRepository.TABLE_NAME;
        query += " SET numero=?, category=?, amount=?, rate=?, status=?, bank_user_id=?";
        query += " WHERE id=" + obj.getId() + ";" ;

        try {
            PreparedStatement stm = this.connect.prepareStatement(query);

            stm.setString(1, obj.getNumber()); //Willl be generated from a service
            stm.setString(2, obj.getCategory());
            stm.setInt(3, obj.getAmount());
            stm.setDouble(4, obj.getRate());
            stm.setString(5, obj.getStatus());
            stm.setLong(6, obj.getBankUserId());

            stm.execute();
            stm.close();
            
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(BankUserDaoRepository.class.getName()).log(Level.SEVERE, null, ex);

            //Handle exception message here
            System.exit(0);
        }

        return false;
    }

    @Override
    public BankAccount find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BankAccount find(BankAccount obj) throws EntityNotFountException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public BankAccount find(BankUser obj) throws EntityNotFountException {
        String query = "SELECT * FROM " + BankAccountDaoRepository.TABLE_NAME;
        query += " WHERE bank_user_id=?;";

        BankAccount account = new BankAccount();

        try {
            PreparedStatement stm = this.connect.prepareCall(query);
            stm.setLong(1, obj.getId());

            System.out.println(query);

            ResultSet result = stm.executeQuery();
            if (result != null) {
                while (result.next()) {

                    account.setId(result.getLong("id"));
                    account.setAmount(result.getInt(TABLE_COLUMN_AMOUNT));
                    account.setBankUserId(obj.getId());
                    account.setNumber(result.getString(TABLE_COLUMN_NUMBER));
                    account.setRate(result.getDouble(TABLE_COLUMN_RATE));
                    account.setCategory(result.getString(TABLE_COLUMN_CATEGORY));
                    account.setStatus(result.getString(TABLE_COLUMN_STATUS));

                }

            } else {
                throw new EntityNotFountException("No user record found for id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BankUserDaoRepository.class.getName()).log(Level.SEVERE, null, ex);
            //throw new EntityNotFountException("No user record found for id");
            System.exit(0);
        }

        return account;
    }

    /**
     * Get All account by the user
     *
     * @param obj
     * @return
     * @throws EntityNotFountException
     */
    public BankAccount[] findAll(BankUser obj) throws EntityNotFountException, Exception {
        String query = "SELECT * FROM " + BankAccountDaoRepository.TABLE_NAME;
        query += " WHERE bank_user_id=?;";

        ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();

        try {
            PreparedStatement stm = this.connect.prepareCall(query);
            stm.setLong(1, obj.getId());

            System.out.println(query);

            ResultSet result = stm.executeQuery();
            if (result != null) {
                while (result.next()) {

                    BankAccount account = new BankAccount();

                    account.setId(result.getLong("id"));
                    account.setAmount(result.getInt(TABLE_COLUMN_AMOUNT));
                    account.setBankUserId(obj.getId());
                    account.setNumber(result.getString(TABLE_COLUMN_NUMBER));
                    account.setRate(result.getDouble(TABLE_COLUMN_RATE));
                    account.setCategory(result.getString(TABLE_COLUMN_CATEGORY));
                    account.setStatus(result.getString(TABLE_COLUMN_STATUS));

                    accounts.add(account);

                }

                return accounts.toArray(new BankAccount[accounts.size()]);

            } else {
                throw new EntityNotFountException("No user record found for id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BankUserDaoRepository.class.getName()).log(Level.SEVERE, null, ex);

            throw new Exception("Une erreur sest produite");
        }
    }

    @Override
    public BankAccount[] select() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Get a user accounts
     *
     * @param user
     * @return
     */
    public BankAccount[] selectByUser(BankUser user) {
        return null;
    }

    @Override
    public BankAccount[] selectWithOptions(int limit, int offset) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getRowsCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
