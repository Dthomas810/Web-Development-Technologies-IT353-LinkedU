package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import model.Account;
import model.Student;
import model.University;


/**
 *
 * @author Noid
 */

// is called by other controllers, passing in the student/university to be viewed.
// The view pages (studentView and UniversityView) can only be accesed by calling viewing 
// methods in other controllers that'll call the appropriate rendermethdods here, which will pull up the 
// appropriate view page (studentView and UniversityView) 

@ManagedBean 
@ApplicationScoped
public class pageView { 
    
    
    
    
    private  Student Stumodel;
     private  University Umodel;
     
     
     public pageView(){
     Stumodel = new Student();
     Umodel = new University();
     }
     
     public  String renderStudent(Student student){
       Stumodel = student;
       
         //Stumodel.setFirstname("butt");
         return "ViewS.xhtml";
     }
     
     public  String renderUniversity(University university){
            Umodel = university;
     return "ViewU.xhtml";
     }

    /**
     * @return the Smodel
     */
    public  Student getStumodel() {
        return Stumodel;
    }

    /**
     * @param Smodel the Smodel to set
     */
    public void setStumodel(Student Smodel) {
        this.Stumodel = Smodel;
    }

    /**
     * @return the Umodel
     */
    public University getUmodel() {
        return Umodel;
    }

    /**
     * @param Umodel the Umodel to set
     */
    public void setUmodel(University Umodel) {
        this.Umodel = Umodel;
    }
     
     
    
}
