/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Account;
import model.Student;

/**
 *
 * @author IT353S737
 */
public interface StudentDAO {
    public int search();
    public int signup(Student aStudent, String username);
    public Student searchStu(Student aStu);
    public Student searchStu(Account aAccount);
    
    public boolean studentCheck(Student aStu); // use to check if account that logged in is a student
    
    public int updateStu(Student aStu);
    
}
