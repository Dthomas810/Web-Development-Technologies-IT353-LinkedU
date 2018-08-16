/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.AccountDAO;
import dao.AccountDaoImpl;

/**
 *
 * @author IT353S740
 */
public class Account {
    private String username;
    private String password;
    private String email;
    private String phonenumber;
    
    
    public boolean isStudentAccount(){
    AccountDAO acc = new AccountDaoImpl();
        boolean userAuthenticated = acc.findStudentAccount(this);      
        return userAuthenticated;
    }
    
    public boolean isValidAccount(){
    AccountDAO acc = new AccountDaoImpl();
        boolean userAuthenticated = acc.findAccount(this);      
        return userAuthenticated;
    }
    
    public boolean isAdminAccount(){
    AccountDAO acc = new AccountDaoImpl();
        boolean userAuthenticated = acc.findAdminAccount(this);      
        return userAuthenticated;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phonenumber
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * @param phonenumber the phonenumber to set
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    
    
    
    
    
    
    
    
    
}
