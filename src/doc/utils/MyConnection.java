/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package doc.utils;
/**
 *
 * @author ASUS
 */
import java.sql.*;

public class MyConnection {
    
    static String url="jdbc:mysql://localhost:3306/doctortechInt";
    static String login="root";
    static String password;
    static Connection myConnexBD;
    
    public static MyConnection instance;
    
    private MyConnection(){
            try{
        myConnexBD = DriverManager.getConnection(url, login, password);
            System.out.println("connexion reussie");
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    
   public static MyConnection getInstance(){
        if(instance == null){
            instance = new MyConnection();
        }
        return instance;
    }
    
    public Connection getConnexion(){
        return myConnexBD;
    }
}