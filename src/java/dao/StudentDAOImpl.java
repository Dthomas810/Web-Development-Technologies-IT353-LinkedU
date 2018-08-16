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
import model.Student;

/**
 *
 * @author IT353S737
 */
public class StudentDAOImpl implements StudentDAO {

    Student s = new Student();
    private String driverName = "org.apache.derby.jdbc.ClientDriver";
    private final String connStr1 = "jdbc:derby://localhost:1527/ProjectTestDB";
    //String result = "";
    
    @Override
    public int signup(Student aStudent, String username){
        System.out.println("hit");
    Connection DBConn = null;
        int result = 0;
        try {
            DBHelper.loadDriver(driverName);
            DBConn = DBHelper.connect2DB(connStr1, "itkstu", "student");
            
            String sqlStr = "INSERT INTO LINKEDU.STUDENT" +          //add new new updated touple
                            "(USERNAME, HS, FIRSTNAME, LASTNAME, CITY, ST, ZIP, ACT, SAT, UNI1) VALUES" +
                           " (? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
            System.out.println("hit1");
            PreparedStatement stmt = DBConn.prepareStatement(sqlStr);
            System.out.println("user name in student sign up is" + aStudent.getUsername());
            
           stmt.setString(1, username);
             stmt.setString(2, "Homewood-Flosmoor High School");
             stmt.setString(3, aStudent.getFirstname());
             System.out.println("sat added to stmt : " + aStudent.getSat());
            stmt.setString(4, aStudent.getLastname());
            stmt.setString(5, aStudent.getCity());
            stmt.setString(6, aStudent.getState());
            stmt.setString(7, aStudent.getZip());
            stmt.setString(8, aStudent.getAct());
            stmt.setString(9, aStudent.getSat());
            stmt.setString(10, aStudent.getUni1());


//            INSERT INTO LINKEDU.STUDENT (USERNAME, HS, FIRSTNAME, LASTNAME, CITY, ST, ZIP, ACT, SAT, UNI1, UNI2, UNI3) 
//	VALUES ('dthoma3', 'Homewood-Flossmoor High School', 'Dwayne', 'Thomas', 'Homewood', 'IL', '60430', '22', NULL, 'Illinois State University', NULL, NULL);

            
            System.out.println("hit3");
            boolean yo = stmt.execute();
            System.out.println("insert worked: " + yo);
            System.out.println("Student processed");
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
    public Student searchStu(Student aStu) {
        Connection DBConn = null;
        DBHelper.loadDriver(driverName);
        DBConn = DBHelper.connect2DB(connStr1, "itkstu", "student");

        try {
            String sqlStr = "SELECT * FROM LINKEDU.STUDENT WHERE FIRSTNAME = ? AND LASTNAME = ?";
            PreparedStatement stmt = DBConn.prepareStatement(sqlStr);
            stmt.setString(1, aStu.getFirstname());
            stmt.setString(2, aStu.getLastname());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                //result = "results";
                s.setFirstname(rs.getString("firstname"));
                //System.out.println(s.getFirstname());
                s.setLastname(rs.getString("lastname"));
                s.setCity(rs.getString("city"));
                s.setState(rs.getString("st"));
                s.setZip(rs.getString("zip"));
                s.setAct(rs.getString("act"));
                s.setSat(rs.getString("sat"));
                s.setUni1(rs.getString("uni1"));
                s.setUni2(rs.getString("uni2"));
                s.setUni3(rs.getString("uni3"));
            }
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return s;
    }
    
    
    @Override
    public Student searchStu(Account aAccount) {
        Connection DBConn = null;
        DBHelper.loadDriver(driverName);
        DBConn = DBHelper.connect2DB(connStr1, "itkstu", "student");

        try {
            String sqlStr = "SELECT * FROM LINKEDU.STUDENT WHERE username = ?";
            PreparedStatement stmt = DBConn.prepareStatement(sqlStr);
            stmt.setString(1, aAccount.getUsername());
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                //result = "results";
                s.setFirstname(rs.getString("firstname"));
                //System.out.println(s.getFirstname());
                s.setLastname(rs.getString("lastname"));
                s.setCity(rs.getString("city"));
                s.setState(rs.getString("st"));
                s.setZip(rs.getString("zip"));
                s.setAct(rs.getString("act"));
                s.setSat(rs.getString("sat"));
                s.setUni1(rs.getString("uni1"));
                s.setUni2(rs.getString("uni2"));
                s.setUni3(rs.getString("uni3"));
            }
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return s;
    }
    

    @Override
    public int search() {
        Connection DBConn = null;
        int result = 0;
        try {
            DBHelper.loadDriver(driverName);
            DBConn = DBHelper.connect2DB(connStr1, "itkstu", "student");
            String sqlStr = "SELECT * FROM LinkedU.Student WHERE firstname = ? and lastname = ?";
            PreparedStatement stmt = DBConn.prepareStatement(sqlStr);
            stmt.setString(1, s.getFirstname());
            stmt.setString(2, s.getLastname());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = 1;
                s.setFirstname(rs.getString("firstname"));
                s.setLastname(rs.getString("lastname"));
                s.setCity(rs.getString("city"));
                s.setState(rs.getString("st"));
                s.setZip(rs.getString("zip"));
                s.setAct(rs.getString("act"));
                s.setSat(rs.getString("sat"));
                s.setUni1(rs.getString("uni1"));
                s.setUni2(rs.getString("uni2"));
                s.setUni3(rs.getString("uni3"));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }
    
    public boolean studentCheck(Student aStu){
    Connection DBConn = null;
        boolean result = false;
        try {
            DBHelper.loadDriver(driverName);
            DBConn = DBHelper.connect2DB(connStr1, "itkstu", "student");

            // With the connection made, create a statement to talk to the DB server.
            // Create a SQL statement to query, retrieve the rows one by one (by going to the
            // columns), and formulate the result string to send back to the client.
            String sqlStr = "SELECT * FROM LinkedU.Student WHERE username = ?";
            PreparedStatement stmt = DBConn.prepareStatement(sqlStr);
            stmt.setString(1, aStu.getUsername());
            //stmt.setString(2, aAccount.getPassword());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = true;
                aStu.setUsername(rs.getString("Username"));
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

    @Override
    public int updateStu(Student aStu) {
        int result =0;
        String uname="";
        String hs="";
        Connection DBConn = null;
        DBHelper.loadDriver(driverName);
        DBConn = DBHelper.connect2DB(connStr1, "itkstu", "student");

        try {
            String sqlStr0 = "SELECT * FROM LINKEDU.STUDENT WHERE firstname = ? and lastname = ?"; // use to get the username from the table and put into String uname
            String sqlStr1 = "DELETE FROM LINKEDU.STUDENT  WHERE firstname = ? and lastname = ?";  // Delete old touple
            String sqlStr = "INSERT INTO LINKEDU.STUDENT" +          //add new new updated touple
                            "(USERNAME, HS, FIRSTNAME, LASTNAME, CITY, ST, ZIP, ACT, SAT, UNI1) VALUES" +
                           " (? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
            
            PreparedStatement stmt0 = DBConn.prepareStatement(sqlStr0);
            PreparedStatement stmt1 = DBConn.prepareStatement(sqlStr1);
            PreparedStatement stmt = DBConn.prepareStatement(sqlStr);
            
            stmt0.setString(1, aStu.getFirstname() );
            stmt0.setString(2, aStu.getLastname() );
            
            stmt1.setString(1, aStu.getFirstname() );
            stmt1.setString(2, aStu.getLastname() );
             
            
            //ResultSet rs = stmt.executeQuery();
            //stmt.executeQuery();
            
           ResultSet rs = stmt0.executeQuery();
           System.out.println("first select worked");
            
            
            if (rs.next()) {
           uname =  rs.getString("username");
           hs = rs.getString("hs");
            }
            System.out.println("username is " + uname);
            System.out.println("HS is "+hs);
            
            
            int pls = stmt1.executeUpdate();
            System.out.println("delete :" + pls);
            
            stmt.setString(1, uname);
             stmt.setString(2, hs);
             stmt.setString(3, aStu.getFirstname());
             System.out.println("sat added to stmt : " + aStu.getSat());
            stmt.setString(4, aStu.getLastname());
            stmt.setString(5, aStu.getCity());
            stmt.setString(6, aStu.getState());
            stmt.setString(7, aStu.getZip());
            stmt.setString(8, aStu.getAct());
            stmt.setString(9, aStu.getSat());
            stmt.setString(10, aStu.getUni1());
//            stmt.setString(11, aStu.getUni2());
//            stmt.setString(12, aStu.getUni3());
            
            
            
            boolean yo = stmt.execute();
            System.out.println("insert worked: " + yo);
            
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
       
        return result;
    }

    

}
