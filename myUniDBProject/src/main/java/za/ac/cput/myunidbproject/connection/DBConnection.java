
package za.ac.cput.myunidbproject.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBConnection.java
 * @author Genereux
 */
public class DBConnection {
    public static Connection derbyConnection() throws SQLException{
       String DATABASE_URL = "jdbc:derby://localhost:1527//University";
       String username = "Login";
       String password = "Admin";
       Connection con = DriverManager.getConnection(DATABASE_URL, username, password);
       return con;
    }
}
