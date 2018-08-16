package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.UniversityDAOImpl;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import model.University;



/**
 *
 * @author Noid
 */

@ManagedBean 
@ApplicationScoped

public class highlights implements Serializable { 
    
    @ManagedProperty(value="#{pageView}")
    private pageView pv;
    
    private University h1; // stores the highlighted schools, use for mainpage info
    private University h2;
    private University h3;
    
    private University vh;
    
    private String h1name;  // for input on the admins page where they select which schools will be highlighted
    private String h2name;
     private String h3name;
    
    
    public highlights(){
      h1 = new University();
      h2 = new University();
      h3 = new University();
      
    }
    
      public String viewHighlight(int num){
       if(num==1){
       //pv.setUmodel(h1);
       setVh(h1);
       
       }
       else if(num==2){
        pv.setUmodel(h2);
       
       }
       else{
        pv.setUmodel(h3);
       
       }
       return pv.renderUniversity(vh);
    }

    /**
     * @return the h1
     */
    public University getH1() {
        return h1;
    }

    /**
     * @param h1 the h1 to set
     */
    public void setH1(University h1) {
        this.h1 = h1;
    }

    /**
     * @return the h2
     */
    public University getH2() {
        return h2;
    }

    /**
     * @param h2 the h2 to set
     */
    public void setH2(University h2) {
        this.h2 = h2;
    }

    /**
     * @return the h3
     */
    public University getH3() {
        return h3;
    }

    /**
     * @param h3 the h3 to set
     */
    public void setH3(University h3) {
        this.h3 = h3;
    }

    /**
     * @return the h1name
     */
    public String getH1name() {
        return h1name;
    }

    /**
     * @param h1name the h1name to set
     */
    public void setH1name(String h1name) {
        this.h1name = h1name;
    }

    /**
     * @return the h2name
     */
    public String getH2name() {
        return h2name;
    }

    /**
     * @param h2name the h2name to set
     */
    public void setH2name(String h2name) {
        this.h2name = h2name;
    }

    /**
     * @return the h3name
     */
    public String getH3name() {
        return h3name;
    }

    /**
     * @param h3name the h3name to set
     */
    public void setH3name(String h3name) {
        this.h3name = h3name;
    }
    
    
    public void loadHighlights(){ // check LinkedU.highlightedschools and load the found schools into h1/h2/h3
        UniversityDAOImpl temp = new UniversityDAOImpl();
    h1 = temp.getHighlight(1);
    //h2 = temp.getHighlight(2);
     //h3 = temp.getHighlight(3);    
    }
    
  
   

    public  String setHighlights(){  // admins page command button action, use h1-3name to get the universities using UniversityDAOImpl getUni()
        //use the string given to find the university in the university table
        h1name = h1.getName();
        
        UniversityDAOImpl temp = new UniversityDAOImpl();
        
        
        //set h1/h2/h3 to that university
        h1 = temp.getUni(h1name); //h1 should, at this point hold the new highlight
        
        //make entry/update into highlightedschools
        temp.updateHighlight(h1);
    //return "index.xhtml?faces-redirect=true";
    return "index";
    }
    
    public String test(){
    return pv.renderUniversity(new University());
    }
    
    public String viewh1(){
     //return view.renderUniversity(h1);
     return pv.renderUniversity( new University());
    }
    
    public String viewh2(){
     return pv.renderUniversity(h2);
    }
    
    public String viewh3(){
     return pv.renderUniversity(h3);
    }

    /**
     * @return the pv
     */
    public pageView getPv() {
        return pv;
    }

    /**
     * @param pv the pv to set
     */
    public void setPv(pageView pv) {
        this.pv = pv;
    }

    /**
     * @return the vh
     */
    public University getVh() {
        return vh;
    }

    /**
     * @param vh the vh to set
     */
    public void setVh(University vh) {
        this.vh = vh;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 
    
    
}
