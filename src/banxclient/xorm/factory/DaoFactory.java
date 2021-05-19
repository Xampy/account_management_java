/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banxclient.xorm.factory;

import banxclient.xorm.DatabaseConnection;
import banxclient.xorm.DbRaw;
import banxclient.xorm.dao.repository.AccountOperationDaoRepository;
import banxclient.xorm.dao.repository.BankAccountDaoRepository;
import banxclient.xorm.dao.repository.BankUserDaoRepository;
import java.sql.Connection;

/**
 *
 * @author Software
 */
public class DaoFactory {

    protected static final Connection connection = DatabaseConnection.getInstance();

    public static BankUserDaoRepository getBankUserRepository() {
        return new BankUserDaoRepository(connection);
    }
    
    public static BankAccountDaoRepository getBankAccountRepository() {
        return new BankAccountDaoRepository(connection);
    }
    
    public static AccountOperationDaoRepository getAccountOperationDaoRepository(){
        return new AccountOperationDaoRepository(connection);
    }
    
    public static DbRaw getDbRaw(){
        return new DbRaw(connection);
    }
}
