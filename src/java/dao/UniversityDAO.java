/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Account;
import model.University;

/**
 *
 * @author Noid
 */
public interface UniversityDAO { 
    public University getUni(String name);
    
     public University searchUni(University uni);
    

        public University getUni(Account aAccount); // find and return University associated with passed in account's username
    
    public int signup(University aUni, String username);
    
    //called by load highlights, returns the highlighted school of index num
    public University getHighlight(int num);
    
    public int updateHighlight(University aUni);
    
    public int updateUni(University aUni);
    
}
