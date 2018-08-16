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
import java.util.ArrayList;
import model.Account;
import model.University;

/**
 *
 * @author Noid
 */
public class UniversityDAOImpl implements UniversityDAO {
    University s = new University();
    private ArrayList<University> unis = new ArrayList<>();
    private final String driverName = "org.apache.derby.jdbc.ClientDriver";
    private final String connStr1 = "jdbc:derby://localhost:1527/ProjectTestDB";
    
    
    @Override
    public University getUni(String name){
    Connection DBConn = null;
        DBHelper.loadDriver(driverName);
        DBConn = DBHelper.connect2DB(connStr1, "itkstu", "student");

        try {
            String sqlStr = "SELECT * FROM LINKEDU.University WHERE Schoolname = ?";
            PreparedStatement stmt = DBConn.prepareStatement(sqlStr);
            stmt.setString(1, name);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                s.setName(rs.getString("schoolname"));
                s.setAddress(rs.getString("address"));
                s.setCity(rs.getString("city"));
                s.setState(rs.getString("st"));
                s.setZip(rs.getString("zip"));
                s.setLink(rs.getString("admissionlink"));
                
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
    public University getUni(Account aAccount) {
        
         Connection DBConn = null;
        DBHelper.loadDriver(driverName);
        DBConn = DBHelper.connect2DB(connStr1, "itkstu", "student");

        try {
            String sqlStr = "SELECT * FROM LINKEDU.University WHERE username = ?";
            PreparedStatement stmt = DBConn.prepareStatement(sqlStr);
            stmt.setString(1, aAccount.getUsername());
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                s.setName(rs.getString("schoolname"));
                s.setAddress(rs.getString("address"));
                s.setCity(rs.getString("city"));
                s.setState(rs.getString("st"));
                s.setZip(rs.getString("zip"));
                s.setLink(rs.getString("admissionlink"));
                
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
     public int signup(University aUni, String username){
        System.out.println("hit");
    Connection DBConn = null;
        int result = 0;
        try {
            DBHelper.loadDriver(driverName);
            DBConn = DBHelper.connect2DB(connStr1, "itkstu", "student");
            
            String sqlStr = "INSERT INTO LINKEDU.UNIVERSITY (USERNAME, SCHOOLNAME, ADDRESS, CITY, ST, ZIP, ADMISSIONLINK)  VALUES ( ?,? ,?,?,?,?,? )";
            System.out.println("hit1");
            PreparedStatement stmt = DBConn.prepareStatement(sqlStr);
            System.out.println("user name in university sign up is" + aUni.getUsername());
            
           stmt.setString(1, username);
             stmt.setString(2, aUni.getName());
             stmt.setString(3, aUni.getAddress());
             System.out.println("school added to stmt : " + aUni.getName());
            
            stmt.setString(4, aUni.getCity());
            stmt.setString(5, aUni.getState());
            stmt.setString(6, aUni.getZip());
            stmt.setString(7, aUni.getLink());
            


//            INSERT INTO LINKEDU.STUDENT (USERNAME, HS, FIRSTNAME, LASTNAME, CITY, ST, ZIP, ACT, SAT, UNI1, UNI2, UNI3) 
//	VALUES ('dthoma3', 'Homewood-Flossmoor High School', 'Dwayne', 'Thomas', 'Homewood', 'IL', '60430', '22', NULL, 'Illinois State University', NULL, NULL);

            
            System.out.println("hit3");
            boolean yo = stmt.execute();
            System.out.println("insert worked: " + yo);
            System.out.println("University processed");
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
    public University getHighlight(int num) {  //1-3, NOT 0-2
        //Check Highlightedschools and get the string of the indicated (by int num) highlight
        String name="";
        int count=1;
        
        Connection DBConn = null;
        DBHelper.loadDriver(driverName);
        DBConn = DBHelper.connect2DB(connStr1, "itkstu", "student");

        try {
            String sqlStr = "SELECT * FROM LINKEDU.highlightedschools";
            PreparedStatement stmt = DBConn.prepareStatement(sqlStr);
            //stmt.setString(1, name);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
               /* if(num==count){
                name= rs.getString("uni");
                }
                count++; */
               name= rs.getString("uni");
                
                
            }
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
       // name holds the name of the wanted highlighted school, search LinkedU.University for the school and return it
        return getUni(name);
        
       
    }

    @Override
    public int updateUni(University aUni) {
        int result =0;
        String usrname="";
        Connection DBConn = null;
        DBHelper.loadDriver(driverName);
        DBConn = DBHelper.connect2DB(connStr1, "itkstu", "student");

        try {
            String sqlStr = "SELECT * FROM LINKEDU.UNIVERSITY WHERE SCHOOLNAME=?";
            String sqlStr1 = "DELETE FROM LINKEDU.UNIVERSITY WHERE SCHOOLNAME=?";
            String sqlStr2 = "INSERT INTO LINKEDU.UNIVERSITY (USERNAME, SCHOOLNAME, ADDRESS, CITY, ST, ZIP, ADMISSIONLINK)  VALUES ( ?,? ,?,?,?,?,? )";

             
            PreparedStatement stmt = DBConn.prepareStatement(sqlStr);
          
            PreparedStatement stmt1 = DBConn.prepareStatement(sqlStr1);
          
            PreparedStatement stmt2 = DBConn.prepareStatement(sqlStr2);
            
            System.out.println("pass in "+aUni.getName());
            stmt.setString(1, aUni.getName());
            stmt1.setString(1, aUni.getName());
            
            ResultSet rs = stmt.executeQuery(); // search for uni and get its usernamename
           System.out.println("first select worked");
           
           if (rs.next()) {
           usrname =  rs.getString("username");
            }
           
            System.out.println("HEY LISTEN 0 ");
   
           
            
            int rs1 = stmt1.executeUpdate(); // delete old uni
            //int rs1 = 0;
            
            System.out.println("delete :" + rs1);
            
            stmt2.setString(1, usrname); //setup up insert statement
            stmt2.setString(2, aUni.getName());
            stmt2.setString(3, aUni.getAddress());
            stmt2.setString(4, aUni.getCity());
            stmt2.setString(5, aUni.getState());
            stmt2.setString(6, aUni.getZip());
            stmt2.setString(7, aUni.getLink());
            
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

    @Override
    public int updateHighlight(University aUni) {
        int result =0;
        Connection DBConn = null;
        DBHelper.loadDriver(driverName);
        DBConn = DBHelper.connect2DB(connStr1, "itkstu", "student");

        try {
            String sqlStr = "DELETE FROM LINKEDU.HIGHLIGHTEDSCHOOLS";
            String sqlStr1 = "INSERT INTO LINKEDU.HIGHLIGHTEDSCHOOLS (UNI)" +
                     "values (?)";
                    
            PreparedStatement stmt = DBConn.prepareStatement(sqlStr);
            PreparedStatement stmt1 = DBConn.prepareStatement(sqlStr1);
            
            stmt1.setString(1, aUni.getName());
            System.out.println("pls");
            int rs = stmt.executeUpdate();
            System.out.println("we out");
            int rs1 = stmt1.executeUpdate();
             System.out.println("here");
            
             if(rs==1 && rs1==1){result=1; }
            
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

    @Override
    public University searchUni(University uni) {
        Connection DBConn = null;
        DBHelper.loadDriver(driverName);
        DBConn = DBHelper.connect2DB(connStr1, "itkstu", "student");
        try {
            String sqlStr = "SELECT * FROM LINKEDU.UNIVERSITY WHERE SCHOOLNAME = ?";
            PreparedStatement stmt = DBConn.prepareStatement(sqlStr);
            stmt.setString(1, uni.getName());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { 
                s.setName(rs.getString("SCHOOLNAME"));
                s.setAddress(rs.getString("ADDRESS"));
                s.setCity(rs.getString("CITY"));
                s.setState(rs.getString("ST"));
                s.setZip(rs.getString("ZIP")); 
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

    
    
}
