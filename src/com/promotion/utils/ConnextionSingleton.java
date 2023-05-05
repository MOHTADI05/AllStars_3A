/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promotion.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class ConnextionSingleton {
     private String url="jdbc:mysql://127.0.0.1:3306/doctortechInt";
    private String login="root";
    private String pwd="";
    private Connection cnx;
    private static ConnextionSingleton instance;

    public Connection getCnx() {
        return cnx;
    }
    
    
    private ConnextionSingleton() {
        try {
            cnx=DriverManager.getConnection(url, login, pwd);
        } catch (SQLException ex) {
            Logger.getLogger(ConnextionSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   public static ConnextionSingleton getInstance(){
       
       if(instance==null)
           instance=new ConnextionSingleton();
       return instance;
   }
   
}
