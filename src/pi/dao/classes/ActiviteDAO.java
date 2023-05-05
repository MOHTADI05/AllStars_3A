/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dao.classes;

import edu.esprit.Controlleur.data;
import java.sql.Connection;
import pi.utils.MyConnection;
import pi.entities.Activite;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author MSI
 */
public  class ActiviteDAO implements IActiviteDao{
     private static ActiviteDAO instance;
    private Statement st;
    private ResultSet rs;
           private Connection cs;

    
    private ActiviteDAO() {
         cs= MyConnection.getInstance();
        try {
            st=cs.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ActiviteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public void InsertActivite(Activite Activite)
 {      String requete="insert into activite (objectif,niveau,calorie,duree,materiel,image_file)  values ('"+Activite.getObjectif()+"','"+Activite.getNiveau()+"','"+Activite.getCalorie()+"','"+Activite.getDuree()+"','"+Activite.getMateriel()+"','"+Activite.getImageFile()+"')";

        try {            st.executeUpdate(requete);

         /*   rs=st.executeQuery(requete);
           // while(rs.next()){
            rs.next();
                a.setObjectif(rs.getString("Objectif"));
                a.setNiveau(rs.getString("niveau"));
                a.setCalorie(rs.getInt("calorie"));
                 a.setDuree(rs.getString("duree"));
                a.setMateriel(rs.getString("materiel"));
                 a.setImage(rs.getString("image"));
                a.setImageFile(rs.getString("image_file"));
            //}  */
        } catch (SQLException ex) {
            Logger.getLogger(ActiviteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
 
     /*String requete="insert into activite (objectif,niveau,calorie,duree,materiel,image,image_file  values (?,?,?,?,?,?,?))";
     try{
           PreparedStatement ps = .prepareStatement(requete);
            ps.setString(1, Activite.getObjectif());
            ps.setString(2, Activite.getNiveau());
            ps.setInt(3, Activite.getCalorie());
            ps.setString(4, Activite.getDuree());
            ps.setString(5, Activite.getMateriel());
            ps.setString(6, Activite.getImage());
            ps.setString(7, Activite.getImageFile());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ActiviteDAO.class.getName()).log(Level.SEVERE, null, ex);
 
     /*String requete="insert into activite (objectif,niveau,calorie,duree,materiel,image,image_file  values (?,?,?,?,?,?,?))";
     try{
           PreparedStatement ps = .prepareStatement(requete);
            ps.setString(1, Activite.getObjectif());
            ps.setString(2, Activite.getNiveau());
            ps.setInt(3, Activite.getCalorie());
            ps.setString(4, Activite.getDuree());
            ps.setString(5, Activite.getMateriel());
            ps.setString(6, Activite.getImage());
            ps.setString(7, Activite.getImageFile());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ActiviteDAO.class.getName()).log(Level.SEVERE, null, ex);
 
     /*String requete="insert into activite (objectif,niveau,calorie,duree,materiel,image,image_file  values (?,?,?,?,?,?,?))";
     try{
           PreparedStatement ps = .prepareStatement(requete);
            ps.setString(1, Activite.getObjectif());
            ps.setString(2, Activite.getNiveau());
            ps.setInt(3, Activite.getCalorie());
            ps.setString(4, Activite.getDuree());
            ps.setString(5, Activite.getMateriel());
            ps.setString(6, Activite.getImage());
            ps.setString(7, Activite.getImageFile());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ActiviteDAO.class.getName()).log(Level.SEVERE, null, ex);
 
     /*String requete="insert into activite (objectif,niveau,calorie,duree,materiel,image,image_file  values (?,?,?,?,?,?,?))";
     try{
           PreparedStatement ps = .prepareStatement(requete);
            ps.setString(1, Activite.getObjectif());
            ps.setString(2, Activite.getNiveau());
            ps.setInt(3, Activite.getCalorie());
            ps.setString(4, Activite.getDuree());
            ps.setString(5, Activite.getMateriel());
            ps.setString(6, Activite.getImage());
            ps.setString(7, Activite.getImageFile());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ActiviteDAO.class.getName()).log(Level.SEVERE, null, ex);
 
     /*String requete="insert into activite (objectif,niveau,calorie,duree,materiel,image,image_file  values (?,?,?,?,?,?,?))";
     try{
           PreparedStatement ps = .prepareStatement(requete);
            ps.setString(1, Activite.getObjectif());
            ps.setString(2, Activite.getNiveau());
            ps.setInt(3, Activite.getCalorie());
            ps.setString(4, Activite.getDuree());
            ps.setString(5, Activite.getMateriel());
            ps.setString(6, Activite.getImage());
            ps.setString(7, Activite.getImageFile());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ActiviteDAO.class.getName()).log(Level.SEVERE, null, ex);
 
     /*String requete="insert into activite (objectif,niveau,calorie,duree,materiel,image,image_file  values (?,?,?,?,?,?,?))";
     try{
           PreparedStatement ps = .prepareStatement(requete);
            ps.setString(1, Activite.getObjectif());
            ps.setString(2, Activite.getNiveau());
            ps.setInt(3, Activite.getCalorie());
            ps.setString(4, Activite.getDuree());
            ps.setString(5, Activite.getMateriel());
            ps.setString(6, Activite.getImage());
            ps.setString(7, Activite.getImageFile());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ActiviteDAO.class.getName()).log(Level.SEVERE, null, ex);
 
     /*String requete="insert into activite (objectif,niveau,calorie,duree,materiel,image,image_file  values (?,?,?,?,?,?,?))";
     try{
           PreparedStatement ps = .prepareStatement(requete);
            ps.setString(1, Activite.getObjectif());
            ps.setString(2, Activite.getNiveau());
            ps.setInt(3, Activite.getCalorie());
            ps.setString(4, Activite.getDuree());
            ps.setString(5, Activite.getMateriel());
            ps.setString(6, Activite.getImage());
            ps.setString(7, Activite.getImageFile());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ActiviteDAO.class.getName()).log(Level.SEVERE, null, ex);
 
     /*String requete="insert into activite (objectif,niveau,calorie,duree,materiel,image,image_file  values (?,?,?,?,?,?,?))";
     try{
           PreparedStatement ps = .prepareStatement(requete);
            ps.setString(1, Activite.getObjectif());
            ps.setString(2, Activite.getNiveau());
            ps.setInt(3, Activite.getCalorie());
            ps.setString(4, Activite.getDuree());
            ps.setString(5, Activite.getMateriel());
            ps.setString(6, Activite.getImage());
            ps.setString(7, Activite.getImageFile());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ActiviteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }*/
 }
 public void RechercheActivite(){
     String req="SELECT objectif, niveau, calorie FROM activite";
             ObservableList<Activite> list=FXCollections.observableArrayList();       

     try{
           rs=st.executeQuery(req);
           while(rs.next()){
               Activite a=new Activite();
                a.setObjectif(rs.getString("Objectif"));
                a.setNiveau(rs.getString("niveau"));
                a.setCalorie(rs.getInt("calorie"));
                 
                list.add(a);
           }
     }catch (SQLException ex) {
            Logger.getLogger(ExerciceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
     
 }
   
 public boolean updateActivite(Activite a) {
     String path=data.path;
     path=path.replace("\\","\\\\");
String requete= "UPDATE activite SET " 
        + "objectif = '"+a.getObjectif()+"', niveau = '"
        +a.getNiveau()+"' , calorie = '"
        +a.getCalorie()+"', duree = '"
        +a.getDuree()+"', materiel = '"
        +a.getMateriel()+"', image_file = '"
        +path +"' WHERE id= " +data.id;
        try {
           if (st.executeUpdate(requete) > 0) {
                return true;
           }
        } catch (SQLException ex) {
            Logger.getLogger(ExerciceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        }
       /* 
        String requete = "update Activite set Objectif=?, niveau=?, calorie=?,duree=?, materiel=?, image_file=?  where Id=?";
        try {
           PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, Activite.getObjectif());
            ps.setString(2, Activite.getNiveau());
            ps.setInt(3, Activite.getCalorie());
            ps.setString(4, Activite.getDuree());
            ps.setString(5, Activite.getMateriel());
              ps.setString(6, Activite.getImage());
            ps.setString(7, Activite.getImageFile());
             ps.setInt(8, Activite.getId());

            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
           
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }*/
  
    
   public void deleteActivite(Activite a) {
        String requete = "delete from Activite where id='"+data.id+"'";
         Activite p=displayById(a.getId());
        
          if(p!=null)
              try {
           
            st.executeUpdate(requete);
             
        } catch (SQLException ex) {
            Logger.getLogger(ActiviteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
        /*try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Activite supprimée");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }*/
    }

   public Activite displayById(int id) {
           String req="select * from Activite where id ="+id;
           Activite a=new Activite();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                a.setObjectif(rs.getString("Objectif"));
                a.setNiveau(rs.getString("niveau"));
                a.setCalorie(rs.getInt("calorie"));
                 a.setDuree(rs.getString("duree"));
                a.setMateriel(rs.getString("materiel"));
                 a.setImage(rs.getString("image"));
                a.setImageFile(rs.getString("image_file"));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ActiviteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("n'existe pas");
        }
    return a;
    }
   
public Activite findActiviteById(int id) {
    String req = "SELECT * FROM Activite WHERE id = " + id;
    Activite a = null;
    try {
        System.out.println("Executing query: " + req);
        rs = st.executeQuery(req);
        if (rs.next()) {
            a = new Activite();
            a.setObjectif(rs.getString("Objectif"));
            a.setNiveau(rs.getString("niveau"));
            a.setCalorie(rs.getInt("calorie"));
            a.setDuree(rs.getString("duree"));
            a.setMateriel(rs.getString("materiel"));
            a.setImage(rs.getString("image"));
            a.setImageFile(rs.getString("image_file"));
        } else {
            System.out.println("No rows returned for id = " + id);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ActiviteDAO.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Error executing query: " + req);
    }
    return a;
}


public ObservableList displayComboBox() {
     String req="select * from activite";
        ObservableList list=FXCollections.observableArrayList(); 
         try {
            rs=st.executeQuery(req);
            while(rs.next()){
            String item = rs.getString("objectif");
            list.add(item);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ActiviteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
}

 public ObservableList<Activite> displayAll() {
        String req="select * from activite";
        ObservableList<Activite> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
               Activite a=new Activite();
                a.setId(rs.getInt("id"));
                a.setObjectif(rs.getString("Objectif"));
                a.setNiveau(rs.getString("niveau"));
                a.setCalorie(rs.getInt("calorie"));
                 a.setDuree(rs.getString("duree"));
                a.setMateriel(rs.getString("materiel"));
                 a.setImage(rs.getString("image"));
                a.setImageFile(rs.getString("image_file"));
                list.add(a);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ActiviteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }


    public List<Activite> DisplayAllActivite() {

        List<Activite> list = new ArrayList<Activite>();

        String requete = "select * from Activite";
         try {
            rs=st.executeQuery(requete);
            while(rs.next()){
                Activite a=new Activite();
                a.setId(rs.getInt("id"));
                a.setObjectif(rs.getString("Objectif"));
                a.setNiveau(rs.getString("niveau"));
                a.setCalorie(rs.getInt("calorie"));
                 a.setDuree(rs.getString("duree"));
                a.setMateriel(rs.getString("materiel"));
                 a.setImage(rs.getString("image"));
                a.setImageFile(rs.getString("image_file"));
                list.add(a);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ActiviteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
        /*try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Activite activte = new Activite();
                activte.setId(resultat.getInt(1));
                activte.setObjectif(resultat.getString(2));
                activte.setNiveau(resultat.getString(3));
                activte.setCalorie(resultat.getInt(4));
                activte.setDuree(resultat.getString(5));
                activte.setMateriel(resultat.getString(6));
                activte.setImageFile(resultat.getString(7));

                listedepots.add(activte);
            }
            return listedepots;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des Activite " + ex.getMessage());
            return null;
        }
    }*/
    }
    
    
     public HashMap<String, Integer> populateCombo(){ {
        HashMap<String, Integer> map = new HashMap<>();
        String req="SELECT `id`, `objectif` FROM activite";
                try {
              rs = st.executeQuery(req);

       Activite item;
       
       while(rs.next()){
           
           item = new Activite(rs.getInt(1), rs.getString(2));
           map.put(item.getObjectif(), item.getId());
       }
       
   } catch (SQLException ex) {
       Logger.getLogger(ActiviteDAO.class.getName()).log(Level.SEVERE, null, ex);
   }
     
   return map;
     }
    }
  private static IActiviteDao iActiviteDao;

    public static IActiviteDao getInstance() {
        if (iActiviteDao == null) {
            iActiviteDao = new  ActiviteDAO();
        }
        return iActiviteDao;
    }

  
    

  
   
}
