/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.classes;

import edu.esprit.Controlleur.data;
import edu.esprit.Controlleur.dataE;
import edu.esprit.util.MyConnection;
import esprit.dao.entities.Activite;
import esprit.dao.entities.Exercice;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author MSI
 */
public class ExerciceDAO implements IExerciceDao{
    private static ExerciceDAO instance;
    private Statement st;
    private ResultSet rs;
    
    private ExerciceDAO() {
        MyConnection cs=MyConnection.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ExerciceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public void InsertExercice(Exercice exercice)
 {      String requete="insert into exercice (activite_id,nom_exercice,temps,repetion,image_exercice)  values ('"+exercice.getId_Activite().getId()+"','"+exercice.getNom_Exercice()+"','"+exercice.getTemps()+"','"+exercice.getRepet()+"','"+exercice.getImage_Exer()+"')";

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
            Logger.getLogger(ExerciceDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        }*/
 }
 public ObservableList<Exercice> displayAll() {
        ObservableList<Exercice> list=FXCollections.observableArrayList();       
        
        String requete = "select * from exercice WHERE activite_id= "+data.id;
         try {
            rs=st.executeQuery(requete);
            while(rs.next()){
                Exercice a=new Exercice();
                a.setId(rs.getInt("id"));
                           // ps.setInt(3, st.getDepot().getId_dep());
                //a.getId_Activite().setId(rs.getInt("activite_id"));
                        
                //a.setId_Activite(rs.getInt("activite_id"));
                a.setNom_Exercice(rs.getString("nom_exercice"));
                 a.setTemps(rs.getString("temps"));
                a.setRepet(rs.getString("repetion"));
                 a.setImage_Exer(rs.getString("image_exercice"));
                list.add(a);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ExerciceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
 
 public void addlike(Exercice exercice){
    int like=0;
    like=exercice.getLikes()+1;
    System.out.println(like);
    String req = "UPDATE exercice SET likes ='" +like + "' WHERE id ='" + dataE.id+ "'";
    try {

                st.executeUpdate(req);

    } catch (SQLException ex) {
                Logger.getLogger(ExerciceDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
     
 }
 public void adddislike(Exercice exercice){
     int dislike=0;
    dislike=exercice.getDislikes()+1;
    System.out.println(dislike);
         
String req = "UPDATE exercice SET dislike = '" +dislike + "' WHERE id = '" + dataE.id+ "'";
try {
           
            st.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(ExerciceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
 
 public Exercice MostLikedEx(){
     String req = "SELECT * FROM exercice  ORDER BY likes DESC LIMIT 1";
                 Exercice e = new Exercice();

     try {
            rs= st.executeQuery(req);
            while (rs.next()) {

            e.setId(rs.getInt("id"));
            e.setNom_Exercice(rs.getString("nom_Exercice"));
            e.setRepet(rs.getString("repetion"));
            e.setTemps(rs.getString("temps"));
            //e.setImage(rs.getString("image"));
            e.setLikes(rs.getInt("likes"));
            e.setDislikes(rs.getInt("dislike"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExerciceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                 return e;

 }
 public ObservableList<Exercice> displayByActivite(int idActivite) {
    String req = "SELECT * FROM exercice WHERE activite_id = " + idActivite;
    ObservableList<Exercice> list = FXCollections.observableArrayList();
    try {
        rs = st.executeQuery(req);
        while (rs.next()) {
            Exercice e = new Exercice();
            e.setId(rs.getInt("id"));
            e.setNom_Exercice(rs.getString("nom_Exercice"));
            e.setRepet(rs.getString("repetion"));
            e.setTemps(rs.getString("temps"));
            //e.setImage(rs.getString("image"));
            e.setLikes(rs.getInt("likes"));
            e.setDislikes(rs.getInt("dislike"));
            
//            e.getId_Activite().setId(rs.getInt("activite_id"));
            list.add(e);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ExerciceDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
}

 
 
 
  public boolean updateExercice(Exercice exercice) {
       String path=dataE.path;
     path=path.replace("\\","\\\\");
String requete= "UPDATE exercice SET " 
        + "activite_id = '"+exercice.getId_Activite().getId()+"', nom_exercice = '"
        +exercice.getNom_Exercice()+"' , repetion = '"
        +exercice.getRepet()+"',  temps = '"
        +exercice.getTemps()+"', materiel = '"
        +exercice.getImage_Exer()+"', image_exercice = '"
        +path +"' WHERE id= " +dataE.id;
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
  
    
   public void deleteExercice(Exercice a) {
        String requete = "delete from exercice where id='"+dataE.id+"'";
         Exercice p=displayById(a.getId());
        
          if(p!=null)
              try {
           
            st.executeUpdate(requete);
             
        } catch (SQLException ex) {
            Logger.getLogger(ExerciceDAO.class.getName()).log(Level.SEVERE, null, ex);
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

  public Exercice displayById(int id) {
    String req="select * from exercice where id ="+id;
    Exercice a=new Exercice();
    try {
        rs=st.executeQuery(req);
        rs.next();
        Activite activite = new Activite();
        activite.setId(rs.getInt("activite_id"));
        a.setId_Activite(activite);
        a.setNom_Exercice(rs.getString("nom_Exercice"));
        a.setTemps(rs.getString("temps"));
        a.setRepet(rs.getString("repetion"));
    } catch (SQLException ex) {
        Logger.getLogger(ExerciceDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return a;
}
   public Exercice findExerciceById(int id) {
    String req = "SELECT * FROM exercice WHERE id = " + id;
    Exercice a = null;
    try {
        System.out.println("Executing query: " + req);
        rs = st.executeQuery(req);
        if (rs.next()) {
                        a = new Exercice();

             Activite activite = new Activite();
        activite.setId(rs.getInt("activite_id"));
        a.setId_Activite(activite);
        a.setNom_Exercice(rs.getString("nom_Exercice"));
        a.setTemps(rs.getString("temps"));
        a.setRepet(rs.getString("repetion"));
        
        } else {
            System.out.println("No rows returned for id = " + id);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ActiviteDAO.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Error executing query: " + req);
    }
    return a;
}

   
   /* public Activite findDepotById(int id) {
        Activite depot = new Activite();
        String requete = "select * from Activite where Id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                depot.setId(resultat.getInt(1));
                depot.setObjectif(resultat.getString(2));   
            }
            return depot;

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du depot " + ex.getMessage());
            return null;
        }
}*/

    public List<Exercice> DisplayAllExercice() {

        List<Exercice> list = new ArrayList<Exercice>();

        String requete = "select * from exercice";
         try {
            rs=st.executeQuery(requete);
            while(rs.next()){
                Exercice a=new Exercice();
                a.setId(rs.getInt("id"));
                           // ps.setInt(3, st.getDepot().getId_dep());
                a.getId_Activite().setId(rs.getInt("activite_id"));
                        
                //a.setId_Activite(rs.getInt("activite_id"));
                a.setNom_Exercice(rs.getString("nom_Exercice"));
                 a.setTemps(rs.getString("temps"));
                a.setRepet(rs.getString("repetion"));
                 a.setImage_Exer(rs.getString("image_exercice"));
                list.add(a);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ExerciceDAO.class.getName()).log(Level.SEVERE, null, ex);
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
  private static IExerciceDao iExerciceDao;

    public static IExerciceDao getInstance() {
        if (iExerciceDao == null) {
            iExerciceDao = new  ExerciceDAO();
        }
        return iExerciceDao;
    }


 
}
