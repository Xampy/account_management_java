/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banxclient.xorm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Software
 */
public class DatabaseConnection {

    private final String url = "jdbc:mysql://localhost:3306/bankx";
    private final String user = "root";
    private final String passwd = "";
    private static Connection connection;

    DatabaseConnection()  {
        try {
            DatabaseConnection.connection = DriverManager.getConnection(url, user, passwd);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static Connection getInstance() {
        if (DatabaseConnection.connection == null) {
            DatabaseConnection c = (new DatabaseConnection());
        }
        return DatabaseConnection.connection;
    }
}
