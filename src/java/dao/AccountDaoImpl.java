/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;

/**
 *
 * @author IT353S740
 */
public class AccountDaoImpl implements AccountDAO {
    
     String driverName = "org.apache.derby.jdbc.ClientDriver";
    String connStr = "jdbc:derby://localhost:1527/ProjectTestDB";
    
    public int setPassword(Account aAccount){  // is called while already logged in, so update the accounts password ussing aAccount's password
    return 0; 
    }
    
   
    
    @Override
     public int signup(Account aAccount){ // make connection, create the account and student/university, return 1 if worked  0 if failed
      Connection DBConn = null;
        int result = 0;
        try {
            DBHelper.loadDriver(driverName);
            DBConn = DBHelper.connect2DB(connStr, "itkstu", "student");
            
            String sqlStr = "INSERT  INTO LinkedU.Account  "
                    + "VALUES (?,?,?,?)";
            PreparedStatement stmt = DBConn.prepareStatement(sqlStr);
            System.out.println("user name is" + aAccount.getUsername());
            
            stmt.setString(1, aAccount.getUsername());
            stmt.setString(2, aAccount.getPassword());
            stmt.setString(3, aAccount.getEmail());
            stmt.setString(4, aAccount.getPhonenumber());
            result = stmt.executeUpdate();
            System.out.println("Account processed");
        }
        catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        finally
        {
            try
            {
                
            }
            catch(Exception e)
            {
            e.printStackTrace();
            }
        }
        return result;
    
}

       @Override
    public boolean findAccount(Account aAccount) {
        Connection DBConn = null;
        boolean result = false;
        try {
            DBHelper.loadDriver(driverName);
            DBConn = DBHelper.connect2DB(connStr, "itkstu", "student");

            // With the connection made, create a statement to talk to the DB server.
            // Create a SQL statement to query, retrieve the rows one by one (by going to the
            // columns), and formulate the result string to send back to the client.
            String sqlStr = "SELECT * FROM LinkedU.Account WHERE username = ? and password = ?";
            PreparedStatement stmt = DBConn.prepareStatement(sqlStr);
            stmt.setString(1, aAccount.getUsername());
            stmt.setString(2, aAccount.getPassword());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = true;
                aAccount.setUsername(rs.getString("Username"));
                aAccount.setPassword(rs.getString("Password"));
               
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }
    
    public boolean findAdminAccount(Account aAccount){
    Connection DBConn = null;
        boolean result = false;
        try {
            DBHelper.loadDriver(driverName);
            DBConn = DBHelper.connect2DB(connStr, "itkstu", "student");

            // With the connection made, create a statement to talk to the DB server.
            // Create a SQL statement to query, retrieve the rows one by one (by going to the
            // columns), and formulate the result string to send back to the client.
            String sqlStr = "SELECT * FROM LinkedU.Admins WHERE username = ?";
            PreparedStatement stmt = DBConn.prepareStatement(sqlStr);
            stmt.setString(1, aAccount.getUsername());
            //stmt.setString(2, aAccount.getPassword());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = true;
                aAccount.setUsername(rs.getString("Username"));
               // aAccount.setPassword(rs.getString("Password"));
               
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }
    public boolean findStudentAccount(Account aAccount){
    Connection DBConn = null;
        boolean result = false;
        try {
            DBHelper.loadDriver(driverName);
            DBConn = DBHelper.connect2DB(connStr, "itkstu", "student");

            // With the connection made, create a statement to talk to the DB server.
            // Create a SQL statement to query, retrieve the rows one by one (by going to the
            // columns), and formulate the result string to send back to the client.
            String sqlStr = "SELECT * FROM LinkedU.Student WHERE username = ?";
            PreparedStatement stmt = DBConn.prepareStatement(sqlStr);
            stmt.setString(1, aAccount.getUsername());
            //stmt.setString(2, aAccount.getPassword());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = true;
                aAccount.setUsername(rs.getString("Username"));
               // aAccount.setPassword(rs.getString("Password"));
               
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }
    
}
