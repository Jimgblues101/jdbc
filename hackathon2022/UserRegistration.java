/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 * UserRegistration.java
 * This is the User Registration section
 * Author: Mdumisi Kelvin Letsie (220120137)
 * 24 July 2022
 */
package za.ac.cput.hackathon2022;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import javax.swing.JOptionPane;

public class UserRegistration {
    static final String DATABASE_URL = "jdbc:derby://localhost:1527/Hackathon2022";
    private final String username = "Administrator";
    private final String password = "password";

    private String userTitle;
    private String userFirstName;
    private String userSurname;
    private String userEmail;
    private String userPhoneNumber;
    private String userPassword;

    public UserRegistration() {
    }

    public UserRegistration (String userTitle, String userFirstName, String userSurname, String userEmail, String userPhoneNumber, String userPassword) {
        this.userTitle = userTitle;
        this.userFirstName = userFirstName;
        this.userSurname = userSurname;
        this.userEmail = userEmail;
        this.userPhoneNumber = userPhoneNumber;
        this.userPassword = userPassword;
    }

    public String getUserTitle() {
        return userTitle;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


@Override
    public String toString() {
    return "User{" + "userTitle=" + userTitle + ", userFirstName=" + userFirstName + "userSurname=" + userSurname + "userEmail=" + userEmail + "userPhoneNumber=" + userPhoneNumber + "userPassword=" + userPassword + '}';
    }

// PASSWORD MATCHING AND VALIDATION 

    public static boolean passwordsMatch(String userPassword, String userConfirmPassword){
            boolean matches = false;
            if (Objects.equals(userPassword, userConfirmPassword)) {
            matches = true;
            }
                else if (!Objects.equals(userPassword, userConfirmPassword)){
                matches = false;
            }
            return matches;
    }

    public void save() {
            Connection connection = null; // manage connection
            Statement statement = null; // query statement
            int ok;

            try {
                // establish connection to database
                connection = DriverManager.getConnection(DATABASE_URL, username, password);
                // create Statement for querying database
                statement = connection.createStatement();
                //ok returns the number of rows affected in the database
                ok = statement.executeUpdate("INSERT INTO Users VALUES('" + userTitle +"', '" + userFirstName + "', '" + userSurname + "', '" + userPhoneNumber + "', '" + userEmail + "', '" + userPassword + "')");
                if (ok > 0) {
                JOptionPane.showMessageDialog(null, "Success! User added.");
                System.exit(0);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Error: Could not add User.");

                }
            }
            catch(SQLException sqlException) {
                JOptionPane.showMessageDialog(null, "Error: Could not add User." + sqlException);

                //txtSubjectCode.hasFocus();
            }

            catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Error: " + exception);

                    //txtSubjectCode.hasFocus();
            }
            finally {
            // Method 1
            try {
                if (statement != null)
                statement.close();
            }
            catch (Exception exception) {
                  JOptionPane.showMessageDialog(null, exception.getMessage(),"Warning", JOptionPane.ERROR_MESSAGE);

            }
            try {
                if (connection != null)
                    connection.close();
            }
            catch(Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);

            }
        }

    }

}
