

package com.promotion.dao;

import com.promotion.dao.idao;
import com.promotion.entity.Regime;
import com.promotion.entity.Type;
import com.promotion.utils.MyConnection;
import com.promotion.utils.ConnextionSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

public class TypeDao implements idao<Type> {
   Connection myconn =MyConnection.getInstance().getConnexion();
    private static TypeDao instance;
    private Statement st;
    private ResultSet rs;
    public TypeDao(){
     ConnextionSingleton cs=ConnextionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(TypeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static TypeDao getInstance(){
        if(instance==null) 
            instance=new TypeDao();
        return instance;
    }
    
    
    
    @Override
    public void insert(Type R) {
     
        
         try {
      
        
             
            String sql="insert into Type (name,discription) values(?,?)"; 
            PreparedStatement ste= myconn.prepareStatement(sql);
            ste.setString(1,R.getName());
            ste.setString(2,R.getDiscription());
           
              
               
            
            ste.executeUpdate();
            JOptionPane.showMessageDialog(null , "Added");
           
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void delete(int id) {
        String req="delete from type where id="+id;
        Type p=displayById(id);
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(TypeDao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

    @Override
    public List<Type> displayAll() {
         String req="select * from type";
        ObservableList<Type> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Type p=new Type();
                p.setId(rs.getInt(1));
                p.setName(rs.getString("name"));
                p.setDiscription(rs.getString("discription"));
               
                 
               
             
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TypeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type displayById(int id) {
       String req="select * from type where id ="+id;
           Type p=new Type();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId(rs.getInt(1));
                p.setName(rs.getString("name"));
                p.setDiscription(rs.getString("discription"));
                
        } catch (SQLException ex) {
            Logger.getLogger(TypeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Type p) {
     String qry = "UPDATE type SET name = '"+p.getName()+"', discription = '"+p.getDiscription();

        try {
            if (st.executeUpdate(qry) > 0) {
               
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TypeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    

    @Override
    public void modifier(int id, Type os) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Regime> display() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
