/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author Skymil
 */
import entity.Reponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;

public class ServiceReponse implements IService<Reponse>{
    Connection conn;
    public ServiceReponse(){
        conn=MyConnection.getInstance().getConn();
    }

    @Override
    public void ajouter(Reponse r) {
        try {
            String query="INSERT INTO `reponse`"
                    + "( `reclamation_id`, `objet`, `description`, `datereponse`) "
                    + "VALUES ('"+r.getReclamation_id()+"','"+r.getObjet()+"','"+r.getDescription()+"','"+r.getDate_reponse()+"')";
            Statement st=conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String query="DELETE FROM reponse WHERE id="+id;
            Statement st=conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(int id, Reponse r) {
        try {
            String query="UPDATE reponse SET "
                    + "`reclamation_id`='"+r.getReclamation_id()+"',"
                    + "`objet`='"+r.getObjet()+"',"
                    + "`description`='"+r.getDescription()+"',"
                    + "`datereponse`='"+r.getDate_reponse()+"' WHERE id="+id;
            Statement st=conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Reponse> afficher() {
        List<Reponse> lr=new ArrayList<>();
        try {
            String query="SELECT * FROM reponse";
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Reponse r=new Reponse();
                r.setId(rs.getInt("id"));
                r.setReclamation_id(rs.getInt("reclamation_id"));
                r.setObjet(rs.getString("objet"));
                r.setDescription(rs.getString("description"));
                r.setDate_reponse(rs.getDate("datereponse"));
                lr.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lr;
    }

    @Override
    public Reponse getById(int id) {
        /*
        try {
            String query="SELECT * FROM reponse WHERE id="+id;
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Reponse r=new Reponse();
                r.setId(rs.getInt("id"));
                r.setReclamation_id(rs.getInt("reclamation_id"));
                r.setObjet(rs.getString("objet"));
                r.setDescription(rs.getString("description"));
                r.setDatereponse(rs.getDate("datereponse"));
                return r;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;*/
        return afficher().stream().filter(r->r.getId()==id).findAny().orElse(null);
    }
   

}
