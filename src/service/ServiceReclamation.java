/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import entity.Reclamation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import utils.MyConnection;


public class ServiceReclamation implements IService<Reclamation>{
    Connection conn;
    private PreparedStatement preparedStatement;
    public ServiceReclamation(){
        conn=MyConnection.getInstance().getConn();
    }
   @Override
 /*  public void ajouter(Reclamation t) {
        try {
            String query="INSERT INTO `reclamation`"
                   + "(`email`, `objet`, "
                   + "`description`, `date_reclamation`, "
    + " `etat`) "
                    + "VALUES ('"+t.getEmail()+"','"+t.getObjet()+"','"+t.getDescription()+"',"
                   + "'"+t.getDate_reclamation()+"','"+t.getEtat()+"')";
            Statement st=conn.createStatement();
            st.executeUpdate(query);
     } catch (SQLException ex) {
          Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
      }
          }
  */
  public void ajouter(Reclamation t) {
    try {
        // Replace "bad" with asterisks in the description field
        String cleanedDescription = t.getDescription().replaceAll("(?i)bad|Merde|sale|merde|fuck", "****");
        
        // Construct the SQL query
        String query = "INSERT INTO `reclamation` (`email`, `object`, `nom`, `description`, `date_reclamation`, `etat`) "
                     + "VALUES ('" + t.getEmail() + "','" + t.getObjet()+ "','" + "Baha" + "','" + cleanedDescription + "',"
                     + "'" + t.getDate_reclamation() + "','" + t.getEtat() + "')";

        Statement st = conn.createStatement();
        st.executeUpdate(query);
    } catch (SQLException ex) {
        Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
    }
}



    @Override
    public void supprimer(int id) {
        try {
            String query="DELETE FROM `reclamation` WHERE id="+id;
            Statement st=conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(int id, Reclamation t) {
        try {
            String query="UPDATE `reclamation` SET "
                    + "`email`='"+t.getEmail()+"',"
                    + "`object`='"+t.getObjet()+"',"
                    + "`description`='"+t.getDescription()+"',"
                    + "`date_reclamation`='"+t.getDate_reclamation()+"',"
                    + "`etat`='"+t.getEtat()+"' WHERE id="+id;
            
            String query2="UPDATE `reclamation` SET "
                    + "`email`=?,"
                    + "`object`=?,"
                    + "`description`=?,"
                    + "`date_reclamation`=?,"
                    + "`etat`=? WHERE id=?";
            PreparedStatement ps=conn.prepareStatement(query2);
            ps.setString(1,t.getEmail());
            ps.setString(2,t.getObjet());
            ps.setString(3,t.getDescription());
            ps.setDate(4, (Date) t.getDate_reclamation());
           
            
            ps.setInt(5,t.getEtat());
            ps.setInt(6,id);
            ps.executeUpdate();
            //Statement st=conn.createStatement();
            //st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Reclamation> afficher() {
        List<Reclamation> lr=new ArrayList<>();
        try {
            String query="SELECT * FROM `reclamation`";
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Reclamation r=new Reclamation();
                r.setId(rs.getInt("id"));
                r.setDate_reclamation(rs.getDate("date_reclamation"));
                r.setDescription(rs.getString("description"));
                r.setObjet(rs.getString("object"));
                r.setEmail(rs.getString("email"));
                r.setEtat(rs.getInt("etat"));
                lr.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lr;
    }
    public List<Reclamation> afficherReclamationUser(String email){
        return afficher().stream()
                .filter(r->r.getEmail().equals(email)).collect(Collectors.toList());
    }
    @Override
    public Reclamation getById(int id) {
        /*
        try {
            String query="SELECT * FROM `reclamation` WHERE id="+id;
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Reclamation r=new Reclamation();
                r.setId(rs.getInt("id"));
                r.setDate_reclamation(rs.getDate("date_reclamation"));
                r.setDate_traitement(rs.getDate("date_traitement"));
                r.setDescription(rs.getString("description"));
                r.setObjet(rs.getString("objet"));
                r.setEmail(rs.getString("email"));
                r.setEtat(rs.getString("etat"));
                return r;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;*/
        return afficher().stream().filter(r->r.getId()==id).findAny().orElse(null);
    }
     public List<Reclamation> triParDate(){
        return afficher().stream()
                .sorted(Comparator.comparing(Reclamation::getDate_reclamation))
                .collect(Collectors.toList());
    }

   
}
