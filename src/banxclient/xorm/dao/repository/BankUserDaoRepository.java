/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banxclient.xorm.dao.repository;

import banxclient.exception.auth.LoginException;
import banxclient.exception.entity.EntityNotFountException;
import banxclient.models.entity.BankAccount;
import banxclient.models.entity.BankUser;
import banxclient.xorm.dao.AbstractDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Software
 */
public class BankUserDaoRepository extends AbstractDAO<BankUser>{
    
    public static final String TABLE_NAME = "bank_user";
    
    public static final String TABLE_COLUMN_LASTNAME = "lastname";
    public static final String TABLE_COLUMN_FIRSTNAME = "firstname";
    public static final String TABLE_COLUMN_PASSWORD = "password";
    
    /**
     * Constructor
     * @param conn Databse connection 
     */
    public BankUserDaoRepository(Connection conn){
        super(conn);
    }

    @Override
    public boolean create(BankUser obj) {
        String query = "INSERT INTO ";
        query += BankUserDaoRepository.TABLE_NAME;
        query += "(lastname, firstname, password ) VALUES(?, ?, ?)";
        
        try{
            PreparedStatement stm = this.connect.prepareStatement(query);
            
            stm.setString(1, obj.getLastname());
            stm.setString(2, obj.getFirstname());
            
            //Here need to hash the password
            stm.setString(3, obj.getPassword());
            
            stm.execute();
            stm.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(BankUserDaoRepository.class.getName()).log(Level.SEVERE, null, ex);
            
            //Handle exception message here
            
            System.exit(0);
        }
        
        return true;
    }
    

    @Override
    public boolean delete(BankUser obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(BankUser obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BankUser find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public BankUser find(BankUser obj) throws EntityNotFountException{
        String query = "SELECT * FROM " + BankUserDaoRepository.TABLE_NAME;
        query += " WHERE lastname='" +  obj.getLastname() + "'";
        query += " AND firstname='" + obj.getFirstname() + "'"; 
        query += " AND password='" + obj.getPassword()+ "';";
        BankUser user = new BankUser();
        
        try{
            Statement stm = this.connect.createStatement();
            System.out.println(query);
            
            ResultSet result = stm.executeQuery(query);
            if (result != null){
                while(result.next()){
                    user.setLastname( result.getString( BankUserDaoRepository.TABLE_COLUMN_LASTNAME) );
                    user.setFirstname( result.getString( BankUserDaoRepository.TABLE_COLUMN_FIRSTNAME) );
                    user.setPassword( result.getString( BankUserDaoRepository.TABLE_COLUMN_PASSWORD)  );
                    user.setId( result.getLong("id")  );
                }
                if (user.getFirstname() == null){
                    throw new EntityNotFountException("No user record found for id");
                }
            }else{
                throw new EntityNotFountException("No user record found for id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BankUserDaoRepository.class.getName()).log(Level.SEVERE, null, ex);
            //throw new EntityNotFountException("No user record found for id");
            System.exit(0);
        }
        
        return user;
    }

    @Override
    public BankUser[] select() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BankUser[] selectWithOptions(int limit, int offset) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getRowsCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    
    
}
