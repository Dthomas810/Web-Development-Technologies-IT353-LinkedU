/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

@ManagedBean
public class UploadController {
    
    private Part file;

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
    
    public String upload(){
        
       
        String path = FacesContext.getCurrentInstance()
                .getExternalContext().getRealPath("images");
       // path = path.substring(0, path.indexOf("\\build"));
       // path = path+"\\web\\resources\\images\\";
        try{
            InputStream in = file.getInputStream();
            byte[] data = new byte[in.available()];
            in.read(data);
            FileOutputStream out = new FileOutputStream(new File(path+"profilepic.jpg"));
            in.close();
            out.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
            
        
        return "uploadSuccess";
    }
}

