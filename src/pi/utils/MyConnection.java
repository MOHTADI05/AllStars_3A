/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.utils;

/**
 *
 * @author swide
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class MyConnection {
 
    String filename= null;
    public static String path;
    Properties properties;
    private String url;
    private String login;
    private String password;
    private static Connection instance;
        private Connection cnx;
  private Connection conn;

    
    
      public Connection getCnx() {
        return cnx;
    }
      

    private MyConnection() {
            try {
                properties = new Properties();
                url = "jdbc:mysql://localhost:3306/doctortechint";
                login = "root";
                password = "";
                instance= DriverManager.getConnection(url, login, password);
                System.out.println("Connection established");
            } catch (SQLException ex) {
                Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    
    public static Connection getInstance() {
        if (instance == null) {
            new MyConnection();
        }
        return instance;
    }
    
     public String getp()
   {
       return path;
   }
     
     public Connection getConn() {
        return conn;
    }
}
