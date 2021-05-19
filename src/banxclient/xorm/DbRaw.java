/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banxclient.xorm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Software
 */
public class DbRaw {
    protected Connection connect = null;

    public DbRaw(Connection conn) {
        this.connect = conn;
    }
    
    
    public ResultSet execute(String sql) throws Exception{
        
        try {
            Statement stm = this.connect.createStatement();
            return stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DbRaw.class.getName()).log(Level.SEVERE, null, ex);
            
            throw new Exception("Une erreur inconnue s'est produite\nVeuillez réessayer  plutard");
        }
    }
}
