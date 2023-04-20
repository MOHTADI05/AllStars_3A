

package com.promotion.dao;

import com.promotion.dao.idao;
import com.promotion.entity.Regime;
import com.promotion.utils.MyConnection;
import com.promotion.utils.ConnextionSingleton;
import java.sql.Connection;
import java.sql.Date;
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

public class RegimeDao implements idao<Regime> {
   Connection myconn =MyConnection.getInstance().getConnexion();
    private static RegimeDao instance;
    private Statement st;
    private ResultSet rs;
    public RegimeDao(){
     ConnextionSingleton cs=ConnextionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(RegimeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static RegimeDao getInstance(){
        if(instance==null) 
            instance=new RegimeDao();
        return instance;
    }
    
    
    
    @Override
    public void insert(Regime R) {
     
        
         try {
      
        
             
            String sql="insert into Regime (name,discription,d_date,nb_calorie,type_id,duree) values(?,?,?,?,?,?)"; 
            PreparedStatement ste= myconn.prepareStatement(sql);
            ste.setString(1,R.getName());
            ste.setString(2,R.getDiscription());
            ste.setObject(3,R.getStart_date());
            ste.setInt(4,R.getCalories());
            ste.setInt(5,R.getType());
            ste.setObject(6,R.getEnd_date());
              
               
            
            ste.executeUpdate();
            JOptionPane.showMessageDialog(null , "Added");
           
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void delete(int id) {
        String req="delete from regime where id="+id;
        Regime p=displayById(id);
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(RegimeDao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }
public List<Regime> displayAll() {
         String req="select * from regime";
        ObservableList<Regime> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Regime p=new Regime();
                p.setId(rs.getInt(1));
                p.setName(rs.getString("name"));
                p.setDiscription(rs.getString("discription"));
                p.setStart_date(rs.getDate("d_date"));
                p.setEnd_date(rs.getInt("duree"));
                p.setType(rs.getInt("type_id"));
                 
                p.setCalories(rs.getInt("nb_calorie"));
              //  p.setProduit(rs.getProduit().getId("produit"));
              //  p.setCategorie(rs.getObject(req, Categorie));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RegimeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list; //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Regime> trieparcalorie() {
         String req="select * from regime order by nb_calorie";
        ObservableList<Regime> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Regime p=new Regime();
                p.setId(rs.getInt(1));
                p.setName(rs.getString("name"));
                p.setDiscription(rs.getString("discription"));
                p.setStart_date(rs.getDate("d_date"));
                p.setEnd_date(rs.getInt("duree"));
                p.setType(rs.getInt("type_id"));
                 
                p.setCalories(rs.getInt("nb_calorie"));
              //  p.setProduit(rs.getProduit().getId("produit"));
              //  p.setCategorie(rs.getObject(req, Categorie));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RegimeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Regime displayById(int id) {
       String req="select * from regime where id ="+id;
           Regime p=new Regime();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId(rs.getInt(1));
                p.setName(rs.getString("name"));
                p.setDiscription(rs.getString("discription"));
                p.setStart_date(rs.getDate("d_date"));
                p.setEnd_date(rs.getInt("duree"));
                p.setType(rs.getInt("type_id"));
                 
                p.setCalories(rs.getInt("nb_calorie"));
        } catch (SQLException ex) {
            Logger.getLogger(RegimeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Regime p) {
     String qry = "UPDATE regime SET name = '"+p.getName()+"',type_id = '"+p.getType()+"', d_date = '"+p.getStart_date()+"', duree = '"+p.getEnd_date()+"', discription = '"+p.getDiscription()+"', nb_calorie = '"+p.getCalories()+"' WHERE id = "+p.getId();

         try {
            Statement st = myconn.createStatement();
            st.executeUpdate(qry);
            System.out.println("Regime modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
 

    @Override
       public void modifier(int id, Regime t) {
        try {
            String query="UPDATE `regime` SET "
                    + "`nom`='"+t.getName()+"',"
                    + "`type_id`='"+t.getType()+"',"
                    + "`d_date`='"+t.getStart_date()+"',"
                    + "'duree = '"+t.getEnd_date()+"',"
                    + "`discription`='"+t.getDiscription()+"',"
                    + "`nb_calorie`='"+t.getCalories()+"' WHERE id="+id;
            
            String query2="UPDATE `regime` SET "
                    + "`nom`=?,"
                    + "`type_id`=?,"
                    + "`d_date`=?,"
                    + "`duree`=?,"
                    + "`discription`=?,"
                   + "`nb_calorie`=? WHERE id=?";
            PreparedStatement ps=myconn.prepareStatement(query2);
            ps.setString(1,t.getName());
            ps.setInt(2,t.getType());
            ps.setDate(3,(Date) t.getStart_date());
            
            ps.setInt(4, t.getEnd_date());
            
            ps.setString(5,t.getDiscription());
            ps.setInt(6,t.getCalories());
            
            ps.setInt(7,id);
            ps.executeUpdate();
            //Statement st=conn.createStatement();
            //st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(RegimeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    } //To change body of generated methods, choose Tools | Templates.

    @Override
    public ObservableList<Regime> display() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }

