/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author MSI
 */
public class MyConnection {
    String filename= null;
public static String path;
    private String url="jdbc:mysql://127.0.0.1:3306/pidev2";
    private String login="root";
    private String pwd="";
    private Connection cnx;
    private static MyConnection instance;

    public Connection getCnx() {
        return cnx;
    }
    
    
    private MyConnection() {
        try {
            cnx=DriverManager.getConnection(url, login, pwd);
        } catch (SQLException ex) {
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   public static MyConnection getInstance(){
       
       if(instance==null)
           instance=new MyConnection();
       return instance;
   }
     
   public void filen(){
       try{
           JFileChooser chooser = new JFileChooser();
           chooser.setDialogTitle("choisir une image png");
           chooser.setApproveButtonText("ajouter une image");
           chooser.showOpenDialog(null);
           File f = chooser.getSelectedFile();
           filename=f.getAbsolutePath();
           this.path=(filename);
           
       }catch(Exception e ){
           System.out.println(e);
           JOptionPane.showMessageDialog(null, "veuiller choisir une image");
           
       }
       
   }
   public String getp()
   {
       return path;
   }
}
