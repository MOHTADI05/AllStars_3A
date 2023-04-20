/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promotion.dao;

import com.promotion.entity.Promotion;
import com.promotion.entity.Regime;
import com.promotion.utils.ConnextionSingleton;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author User
 */
//public class PersonneDao implements Idao<Personne>
public class PromotionDao implements idao<Promotion> {
    private static PromotionDao instance;
    private Statement st;
    private ResultSet rs;
    public PromotionDao(){
     ConnextionSingleton cs=ConnextionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static PromotionDao getInstance(){
        if(instance==null) 
            instance=new PromotionDao();
        return instance;
    }
    
    
    
    @Override
    public void insert(Promotion o) {
     
        
         String req = "INSERT INTO promotion (id, produit_id_id, start_date, end_date, pourcentage, id_categ_id) VALUES ('" + o.getId() + "', '" + o.getProduit().getId() + "', '" + o.getStart_date() + "', '" + o.getEnd_date() + "', '" + o.getPourcentage() + "', '" + o.getCategorie() + "')";
         
         try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        String req="delete from promotion where id="+id;
        Promotion p=displayById(id);
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

    @Override
    public List<Promotion> displayAll() {
         String req="select * from promotion";
        ObservableList<Promotion> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Promotion p=new Promotion();
                p.setId(rs.getInt(1));
                p.setPourcentage(rs.getFloat("pourcentage"));
                p.setStart_date(rs.getDate("start_date"));
                p.setEnd_date(rs.getDate("end_date"));
              //  p.setProduit(rs.getProduit().getId("produit"));
              //  p.setCategorie(rs.getObject(req, Categorie));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Promotion displayById(int id) {
       String req="select * from regime where id ="+id;
           Promotion p=new Promotion();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
               p.setId(rs.getInt(1));
                p.setPourcentage(rs.getFloat("pourcentage"));
                p.setStart_date(rs.getDate("start_date"));
                p.setEnd_date(rs.getDate("end_date"));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Promotion p) {
     String qry = "UPDATE promotion SET produit_id_id = '"+p.getProduit().getId()+"', start_date = '"+p.getStart_date()+"', end_date = '"+p.getEnd_date()+"', pourcentage = '"+p.getPourcentage()+"', id_categ_id = '"+p.getCategorie()+"' WHERE id = "+p.getId();

        try {
            if (st.executeUpdate(qry) > 0) {
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    

    @Override
    public void modifier(int id, Promotion os) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Regime> display() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
