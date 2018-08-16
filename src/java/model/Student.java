/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.StudentDAO;
import dao.StudentDAOImpl;

/**
 *
 * @author IT353S740
 */
public class Student extends Account {
    private String firstname;
    private String Lastname;
    private String city;
    private String state;
    private String zip;
    private String act;
    private String sat;
    
    private String uni1;
    private String uni2;
    private String uni3;
    
    //new  ,  set at signup and will option to update when logged in. Determines you end up on the email listings of any of your favorite schools. 
    private boolean emailOpt = false;  
    private boolean textOpt = false; 
    
    
    
    public boolean isStudent(){
    StudentDAO stu = new StudentDAOImpl();
    return stu.studentCheck(this);
    }
    
    

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the Lastname
     */
    public String getLastname() {
        return Lastname;
    }

    /**
     * @param Lastname the Lastname to set
     */
    public void setLastname(String Lastname) {
        this.Lastname = Lastname;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * @return the act
     */
    public String getAct() {
        return act;
    }

    /**
     * @param act the act to set
     */
    public void setAct(String act) {
        this.act = act;
    }

    /**
     * @return the sat
     */
    public String getSat() {
        return sat;
    }

    /**
     * @param sat the sat to set
     */
    public void setSat(String sat) {
        this.sat = sat;
    }

    /**
     * @return the uni1
     */
    public String getUni1() {
        return uni1;
    }

    /**
     * @param uni1 the uni1 to set
     */
    public void setUni1(String uni1) {
        this.uni1 = uni1;
    }

    /**
     * @return the uni2
     */
    public String getUni2() {
        return uni2;
    }

    /**
     * @param uni2 the uni2 to set
     */
    public void setUni2(String uni2) {
        this.uni2 = uni2;
    }

    /**
     * @return the uni3
     */
    public String getUni3() {
        return uni3;
    }

    /**
     * @param uni3 the uni3 to set
     */
    public void setUni3(String uni3) {
        this.uni3 = uni3;
    }
    
    /**
     * @return the emailOpt
     */
    public boolean isEmailOpt() {
        return emailOpt;
    }

    /**
     * @param emailOpt the emailOpt to set
     */
    public void setEmailOpt(boolean emailOpt) {
        this.emailOpt = emailOpt;
    }

    /**
     * @return the textOpt
     */
    public boolean isTextOpt() {
        return textOpt;
    }

    /**
     * @param textOpt the textOpt to set
     */
    public void setTextOpt(boolean textOpt) {
        this.textOpt = textOpt;
    }

    
}
