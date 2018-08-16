package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import dao.AccountDaoImpl;
import dao.StudentDAO;
import dao.StudentDAOImpl;
import dao.UniversityDAO;
import dao.UniversityDAOImpl;
import javax.faces.application.ConfigurableNavigationHandler;
import java.io.Serializable;
import java.util.Properties;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Account;
import model.Student;
import model.University;


/**
 *
 * @author Noid
 */

@Named(value = "loginController")
@ManagedBean 
@SessionScoped
public class LoginController implements Serializable{    //handles loging in and signup 

    @ManagedProperty(value="#{pageView}")
    private pageView pv;
    
    private boolean loggedIn = false;
    private boolean isAdmin = false; 
    
    private boolean studentLoggedIn = false;
    private boolean universityLoggedIn = false;
    
 
    private String stuResponse = "";
    private String uniResponse = "";
    
    private String stat = "";
    private String page = "results.xhtml";
   
    private UniversityDAO u = new UniversityDAOImpl();
    private StudentDAO s = new StudentDAOImpl();
    
    Account model;
    
    Student Smodel;
     Student SSmodel;
    University Umodel;
     University SUmodel;
    
    String response;
    
    
  public String viewYourProfile(){
      
 
      if(studentLoggedIn){
          
          pv.setStumodel(Smodel);
          return pv.renderStudent(Smodel);}
      
      else if(universityLoggedIn){
          pv.setUmodel(Umodel);
          return pv.renderUniversity(Umodel);}
      
  return "";
  }
  
  public String editProfile(){
   if(studentLoggedIn){
        return "EditS";
   }
   
   else return "EditU";
     
  }
  
  public String updateProfile(){
      int ret = 0;
      if(studentLoggedIn){
      StudentDAOImpl temp = new StudentDAOImpl();
       ret = temp.updateStu(Smodel);
      }
      
      else{
      UniversityDAOImpl temp = new UniversityDAOImpl();
      ret = temp.updateUni(Umodel);
      }
      
      //if(ret==0) return "";
      
      
      return "index.xhtml?faces-redirect=true";
  }
  
    
   
    
    public String processStudentinfo(){ // assumes the Smodel has been filled correctly, combines Model and Smodel
   
   
   // add Smodel to Linkedu.students , then proceed to processsignup() which will do the same for model and LinkedU.Account
     
   s.signup(Smodel, model.getUsername());
     
     
     
     //studentLoggedIn = true;
     
         processSignup(); //account must be created before student
         s.signup(Smodel, model.getUsername());
         return "index";
         
    }
    
    public String processUniversityinfo(){  //setup the same way processStudentInfo is
      
     processSignup();
        u.signup(Umodel,model.getUsername());
    return "index";
    }
    
    
    

  

   

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * Get the value of loggedIn
     *
     * @return the value of loggedIn
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }
    
//    public int accountType(){
//    return acctype; 
//    }

    /**
     * Set the value of loggedIn
     *
     * @param loggedIn new value of loggedIn
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
       
        model = new Account();
        Smodel = new Student();
        SSmodel = new Student();
        Umodel = new University();
         SUmodel = new University();
        
    }
            

    public void checkIfLoggedIn() {
        if (!loggedIn) {
            // Can't just return "login" as it not an "action" event (// Ref: http://stackoverflow.com/questions/16106418/how-to-perform-navigation-in-prerenderview-listener-method)
            FacesContext fc = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
            nav.performNavigation("login?faces-redirect=true");
        }
    }
    
    public void getUniInfo(){ //poll database for user's account specific information, use it to fill model
    UniversityDAOImpl temp = new UniversityDAOImpl();
     Umodel = temp.getUni(model);
    }
     public void getStuInfo(){
       StudentDAOImpl temp = new StudentDAOImpl();
       Smodel = temp.searchStu(model);
    }
     
     
    

    public String processLogin() {
        
        
        if (!model.isValidAccount()) {
            loggedIn = false;
            response = "Invalid username/password!";
            return ""; // stay right at the current page
        } 
        else {
           loggedIn = true;
        if(model.isAdminAccount()){isAdmin=true;}
        
        else if(model.isStudentAccount()){
            studentLoggedIn=true;
            getStuInfo();
        }
        // assumes that if the account that logged in isnt an admin or a student, they must be a university (but isn't actually checking that it is)
        else{
        universityLoggedIn = true;
        getUniInfo();
        }
              
            response = "";
            return "vote.xhtml?faces-redirect=true";
        }  
       //return "test";
    }
    
    public String processSignup(){
        //Use the information put into 'model' and the 
        AccountDaoImpl temp = new AccountDaoImpl();
        System.out.println("user name is" + model.getUsername());
        int ret = temp.signup(model);
        
        if(ret==1)
    return "index.xhtml?faces-redirect=true";
        
        else return "";
    }
    
   

    public String logout() {
//        loggedIn = false;
//        theModel.setUsername("");
//        theModel.setPassword("");
//        theModel.setFavPL("");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession(); // the above is unnecessary once the session is invalidated
        return "index.xhtml?faces-redirect=true";

    }

    /**
     * @return the model
     */
    public Account getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(Account model) {
        this.model = model;
    }


    /**
     * @return the Smodel
     */
    public Student getSmodel() {
        return Smodel;
    }

    /**
     * @param Smodel the Smodel to set
     */
    public void setSmodel(Student Smodel) {
        this.Smodel = Smodel;
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

    /**
     * @return the studentLoggedIn
     */
    public boolean isStudentLoggedIn() {
        return studentLoggedIn;
    }

    /**
     * @param studentLoggedIn the studentLoggedIn to set
     */
    public void setStudentLoggedIn(boolean studentLoggedIn) {
        this.studentLoggedIn = studentLoggedIn;
    }

    /**
     * @return the universityLoggedIn
     */
    public boolean isUniversityLoggedIn() {
        return universityLoggedIn;
    }

    /**
     * @param universityLoggedIn the universityLoggedIn to set
     */
    public void setUniversityLoggedIn(boolean universityLoggedIn) {
        this.universityLoggedIn = universityLoggedIn;
    }

    /**
     * @return the isAdmin
     */
    public boolean isIsAdmin() {
        return isAdmin;
    }

    /**
     * @param isAdmin the isAdmin to set
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
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
     * @return the stuResponse
     */
    public String getStuResponse() {
        SSmodel = returnStuModel();
        //System.out.println(Smodel.getCity());
        String str = "";
        if (SSmodel.getFirstname() != null) {
            str += "<b>Name: </b>" + SSmodel.getFirstname() + " " + SSmodel.getLastname() + "<br/>";
            str += "<b>City: </b>" + SSmodel.getCity() + "<br/>";
            str += "<b>State: </b>" + SSmodel.getState() + "<br/>";
            String act = SSmodel.getAct();
            String sat = SSmodel.getSat();
            if (act != null) {
                str += "<b>ACT Score : </b>" + act + "<br/>";
            } else if (sat != null) {
                str += "<b>SAT Score </b>: " + sat + "<br/><br/>";
            }
            str += "<b>Student's Choice Universities</b>" + "<br/>";
            if (SSmodel.getUni1() != null) {
                str += "1. " + SSmodel.getUni1() + "<br/>";
            } else {
                str += "1. Not set";
            }
            if (SSmodel.getUni2() != null) {
                str += "2. " + SSmodel.getUni2() + "<br/>";
            } else {
                str += "2. Not set <br/>";
            }
            if (SSmodel.getUni3() != null) {
                str += "3. " + SSmodel.getUni3() + "<br/>";
            } else {
                str += "3. Not set <br/>";
            }

            stuResponse = str;
        } else {
            stuResponse = "Student not found. Please try searching again.";
        }
        return stuResponse;
    }

    /**
     * @param stuResponse the stuResponse to set
     */
    public void setStuResponse(String stuResponse) {
        this.stuResponse = stuResponse;
    }

    /**
     * @return the uniResponse
     */
    public String getUniResponse() {
        SUmodel = returnUniModel();
        //System.out.println(Umodel.getName());
        String str = "";
        if (SUmodel.getName() != null) {
            str += "<b>University: </b>" + SUmodel.getName() + "<br/>";
            str += "<b>Address: </b>" + SUmodel.getAddress() + "<br/>";
            str += SUmodel.getCity() + "<br/>";
            str += SUmodel.getState() + "<br/>";
            str += SUmodel.getZip();
            uniResponse = str;
        } else {
            uniResponse = "School not found. Please try searching again.";
        }
        return uniResponse;
    }
    
    public Student returnStuModel() {
        StudentDAO s = new StudentDAOImpl();
        Student r = s.searchStu(SSmodel);
        SSmodel.setFirstname(r.getFirstname());
        SSmodel.setLastname(r.getLastname());
        SSmodel.setCity(r.getCity());
        //System.out.println(Smodel.getCity());
        SSmodel.setState(r.getState());
        SSmodel.setZip(r.getZip());
        SSmodel.setAct(r.getAct());
        SSmodel.setSat(r.getSat());
        SSmodel.setUni1(r.getUni1());
        SSmodel.setUni2(r.getUni2());
        SSmodel.setUni3(r.getUni3());
        return SSmodel;
    }

    public University returnUniModel() {
        UniversityDAO un = new UniversityDAOImpl();
        University r = un.searchUni(SUmodel);
        SUmodel.setName(r.getName());
        SUmodel.setAddress(r.getAddress());
        SUmodel.setCity(r.getCity());
        SUmodel.setState(r.getState());
        SUmodel.setZip(r.getZip());
        //System.out.println(Umodel.getName());
        return SUmodel;
    }
    
    public void sendEmail() {
        // Recipient's email ID needs to be mentioned.
        String to = "daschau@ilstu.edu";
        // Sender's email ID needs to be mentioned
        String from = "daschau@ilstu.edu";
        // Assuming you are sending email from this host
        String host = "outlook.office365.com";
        // Get system properties
        Properties properties = System.getProperties();
        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "587");
        // Get the default Session object.
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("daschau@ilstu.edu", "harleyquinn1");
            }
        });
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));
            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            // Set Subject: header field
            message.setSubject("Someone Wants to Contact You Through LinkedU");
            // Send the actual HTML message, as big as you like
            message.setContent("<h2>A recruiter wants to chat with you! #RECRUITERNAME has expressed interest in your talent. Log in today and send them a message to get started!</h2>",
                    "text/html");
            // Send message
            Transport.send(message);
            stat = "Sent message successfully.";
        } catch (MessagingException mex) {
            System.out.println(mex);
        }
    }
    

    /**
     * @param uniResponse the uniResponse to set
     */
    public void setUniResponse(String uniResponse) {
        this.uniResponse = uniResponse;
    }

    /**
     * @return the stat
     */
    public String getStat() {
        return stat;
    }

    /**
     * @param stat the stat to set
     */
    public void setStat(String stat) {
        this.stat = stat;
    }

    /**
     * @return the page
     */
    public String getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(String page) {
        this.page = page;
    }

    /**
     * @return the u
     */
    public UniversityDAO getU() {
        return u;
    }

    /**
     * @param u the u to set
     */
    public void setU(UniversityDAO u) {
        this.u = u;
    }

    /**
     * @return the SSmodel
     */
    public Student getSSmodel() {
        return SSmodel;
    }

    /**
     * @param SSmodel the SSmodel to set
     */
    public void setSSmodel(Student SSmodel) {
        this.SSmodel = SSmodel;
    }

    /**
     * @return the SUmodel
     */
    public University getSUmodel() {
        return SUmodel;
    }

    /**
     * @param SUmodel the SUmodel to set
     */
    public void setSUmodel(University SUmodel) {
        this.SUmodel = SUmodel;
    }
}
