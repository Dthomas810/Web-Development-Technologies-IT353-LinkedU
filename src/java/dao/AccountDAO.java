/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import model.Account;

/**
 *
 * @author IT353S740
 */
public interface AccountDAO {
    
    public int signup(Account aAccount);
   
    public int setPassword(Account aAccount);
    public boolean findAccount(Account aUser); 
    public boolean findAdminAccount(Account aUser);
    public boolean findStudentAccount(Account aUser);
    
    
}
