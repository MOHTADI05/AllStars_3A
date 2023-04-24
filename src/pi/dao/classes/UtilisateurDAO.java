/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pi.dao.classes;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import pi.dao.interfaces.IUtilisateurDAO;
import pi.entities.Utilisateur;
import java.util.List;
import pi.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;


/**
 *
 * @author swide
 */
public class UtilisateurDAO implements IUtilisateurDAO {
    
       private Connection connection;

    private UtilisateurDAO() {
        connection = MyConnection.getInstance();
    }

    /*@Override
    public void inscription(Utilisateur U) {
         
        String hashedMdp = BCrypt.hashpw(U.getPassword(), BCrypt.gensalt());

        String requete = "insert into utilisateur (id,nom,prenom,email,password,role,is_Blocked) values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
             ps.setInt(1, U.getId());
            ps.setString(2, U.getNom());
            ps.setString(3, U.getPrenom());
            ps.setString(4, U.getEmail());
            ps.setString(5, hashedMdp);
            ps.setString(6, U.getRole());
            ps.setBoolean(7, U.getisBlocked());

            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
    }
*/
       @Override
   public boolean check(String email, String mdp, String role) {
    boolean authentifie = false;
   /* ResultSet result = null;
    role = "Client";
    String requete = "SELECT * FROM utilisateur WHERE email = ? and role = ?";*/
  Utilisateur U= findByEmail(email);
              String hashedMdp = U.getPassword();
                 System.out.println(hashedMdp);
                System.out.println(mdp);
              if(BCrypt.checkpw(mdp,hashedMdp)){
                  authentifie=true;
              }

/*  try {
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setString(1, email);
        ps.setString(2, role);
        result = ps.executeQuery();
        
        if (result.next()) {
            String hashedMdp = result.getString("password");
               
                System.out.println(email);
               // System.out.println(hashedMdp);
                //System.out.println(mdp);
                 System.out.println(BCrypt.checkpw(mdp,hashedMdp));
            if (mdp==hashedMdp) {
                System.out.println(mdp);

                authentifie = true;
            }
          
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }*/

    return authentifie;
}

    
     /*  @Override
    public void login(Utilisateur U)  {
       
        //System.out.println(U.getPassword());
    if (check(U.getEmail(), U.getPassword(),U.getRole())) {
        // Connexion réussie 
       
        Session.getInstance().setLoggedUser(U);
        String Role=U.getRole();
        System.out.println(Role);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/view/profil.fxml"));
        Parent root;
        try {
            root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
             ex.printStackTrace();  
        }
    } else if(checkA(U.getEmail(), U.getPassword(),U.getRole())) {
          Session.getInstance().setLoggedUser(U);
        String Role=U.getRole();
        System.out.println(Role);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/view/list.fxml"));
        Parent root;
        try {
                        // Récupère la fenêtre courante
           
            root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
             ex.printStackTrace();
            
        }
    }else //if(!checkA(U.getEmail(), U.getPassword(),U.getRole())&&!check(U.getEmail(), U.getPassword(),U.getRole()))
    {
        // Connexion échouée
                                 

        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de connexion");
        alert.setHeaderText("Informations d'identification incorrectes");
        alert.setContentText("Le nom d'utilisateur ou le mot de passe est incorrect. Veuillez réessayer.");
        alert.showAndWait();
    }
    
}*/

    
     @Override
    public boolean  checkA(String email,String mdp,String role) {
       
         boolean authentifie = false;

        ResultSet result = null;
        role="Admin";
                String requete = "SELECT * FROM utilisateur WHERE email =? and password =? and role =?";
                 try {
                PreparedStatement ps = connection.prepareStatement(requete);
                ps.setString(1, email);
                ps.setString(2, mdp);
                 ps.setString(3, role);
                result = ps.executeQuery();
                    } catch (SQLException ex) 
                    {
                        ex.printStackTrace();
                                }
           try {
               if(!result.isBeforeFirst())
               {
                   System.out.println("invalide !!!!");
                   return authentifie;
               }else
               {
                   try {
                       while(result.next())
                       {
                           System.out.println("vvvvv !!!!");
                           authentifie=true;
                       }
                   } catch (SQLException ex) {
                       ex.printStackTrace();
                   }
               }
           } catch (SQLException ex) {
                    ex.printStackTrace();  
           }
                 return authentifie;
            }   
    
       @Override
   public Utilisateur findByEmail(String email) {
    Utilisateur utilisateur = null;
   
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    try {
        stmt = connection.prepareStatement("SELECT * FROM utilisateur WHERE email = ?");
        stmt.setString(1, email);
      //  stmt.setString(2, password);
        rs = stmt.executeQuery();
        
        if (rs.next()) {
            utilisateur = new Utilisateur();
            utilisateur.setId(rs.getInt("id"));
            utilisateur.setNom(rs.getString("nom"));
            utilisateur.setPrenom(rs.getString("prenom"));
            utilisateur.setEmail(rs.getString("email"));
            utilisateur.setPassword(rs.getString("password"));
            utilisateur.setRole(rs.getString("role"));

        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return utilisateur;
}


    
    
    @Override
    public void updateUtilisateur(Utilisateur U) {
               // String requete = "insert into utilisateur (id,nom,prenom,email,password,role,is_Blocked) values (?,?,?,?,?,?,?)";
    String hashedMdp = BCrypt.hashpw(U.getPassword(), BCrypt.gensalt());

         String requete = "update utilisateur set nom=?, prenom=?, email=?,password=?,role=? where Id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, U.getNom());
            ps.setString(2, U.getPrenom());
            ps.setString(3, U.getEmail());
            ps.setString(4, hashedMdp);
            ps.setString(5, U.getRole());
            ps.setInt(6, U.getId());

            //ps.setBoolean(7, U.getisBlocked());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public boolean deleteUtilisateur(int id) {
        String requete = "delete from utilisateur where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
            return true;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
            return false;
        }
        
    }

    @Override
    public Utilisateur findUtilisateurById(int id) {
 Utilisateur utilisateur = null;
   
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    try {
        stmt = connection.prepareStatement("SELECT * FROM utilisateur WHERE id = ? ");
        stmt.setInt(1, id);
        rs = stmt.executeQuery();
        
        if (rs.next()) {
            utilisateur = new Utilisateur();
            utilisateur.setId(rs.getInt("id"));
            utilisateur.setNom(rs.getString("nom"));
            utilisateur.setPrenom(rs.getString("prenom"));
            utilisateur.setEmail(rs.getString("email"));
            utilisateur.setPassword(rs.getString("password"));
            utilisateur.setRole(rs.getString("role"));

        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return utilisateur;  
    }
    
    @Override
     public ObservableList<Utilisateur> getAllUtilisateurs() {
        ObservableList<Utilisateur> list = FXCollections.observableArrayList();
        Connection conn = MyConnection.getInstance();
        String query = "SELECT * FROM utilisateur";

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                user.setisBlocked(rs.getBoolean("is_blocked"));
        
                //user.setMotDePasse(rs.getString("motDePasse"));
                list.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
    
    
     
     
     /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     
     @Override
     public void inscription(Utilisateur U) {
    boolean inscrit = false;
    
    
    String hashedMdp = BCrypt.hashpw(U.getPassword(), BCrypt.gensalt(13));
        String requete = "insert into utilisateur (id,nom,prenom,email,password,role,is_Blocked) values (?,?,?,?,?,?,?)";
    try {
         PreparedStatement ps = connection.prepareStatement(requete);
             ps.setInt(1, U.getId());
            ps.setString(2, U.getNom());
            ps.setString(3, U.getPrenom());
            ps.setString(4, U.getEmail());
            ps.setString(5, hashedMdp);
            ps.setString(6, U.getRole());
            ps.setBoolean(7, U.getisBlocked());

        int result = ps.executeUpdate();
        if (result > 0) {
          //  inscrit = true;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    //return inscrit;
}

    public void login(String email, String mdp) {
        boolean login=false;
    ResultSet result = null;
    String requete = "SELECT * FROM utilisateur WHERE email = ?";
    try {
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setString(1, email);
        result = ps.executeQuery();
        if (result.next()) {
            String hashedMdp = result.getString("password");
                 System.out.println(hashedMdp );
         
                        String role=result.getString("role");
                         System.out.println(role );
System.out.println("ligne 381" );
                       // if(role=="Client"){
                       try{
                            boolean check=BCrypt.checkpw(mdp, hashedMdp);
                            System.out.println("ligne 383" );
                       }catch(Exception e)
                       {e.printStackTrace();
                       }
                                                   boolean check=BCrypt.checkpw(mdp, hashedMdp);

                      System.out.println(mdp );

                             System.out.println(check);
                            if (check==true) {
                                if(result.getBoolean("is_blocked")==true)
                                {
                                     Alert alert = new Alert(AlertType.ERROR);
                                            alert.setTitle("Erreur compte bloquer");
                                            alert.setHeaderText("Erreur compte bloquer");
                                            alert.setContentText("Erreur compte bloquer!!");
                                            alert.showAndWait();
                                }else{
                                     Utilisateur user = new Utilisateur();
                                     user.setId(result.getInt("id"));
                                     user.setNom(result.getString("nom"));
                                     user.setPrenom(result.getString("prenom"));
                                     user.setEmail(result.getString("email"));
                                     user.setRole(role);
                                     user.setisBlocked(result.getBoolean("is_blocked"));   
                                              Session.getInstance().setLoggedUser(user);
                                     login=true;
                                      System.out.println(login );
                                      
                                       String Role=user.getRole();
                                    System.out.println(Role);
                                        if (Role.equalsIgnoreCase("Client")){
                                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/view/profil.fxml"));
                                                    Parent root;
                                                    try {
                                                        root = loader.load();
                                                        Scene scene = new Scene(root);
                                                        Stage stage = new Stage();
                                                        stage.setScene(scene);
                                                        stage.show();
                                                    } catch (IOException ex) {
                                                         ex.printStackTrace();  
                                                    }
                                     }else if(Role.equalsIgnoreCase("Admin")){ 
                                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/view/list.fxml"));
                                                    Parent root;
                                                    
                                                    try {
                                                        
                                                        root = loader.load();
                                                        Scene scene = new Scene(root);
                                                        Stage stage = new Stage();
                                                        stage.setScene(scene);
                                                        stage.show();
                                                    } catch (IOException ex) {
                                                         ex.printStackTrace();  
                                                    }
                                     }else if(Role.equalsIgnoreCase("Nutistionist")){ 
                                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/view/list.fxml"));
                                                    Parent root;
                                                    
                                                    try {
                                                        
                                                        root = loader.load();
                                                        Scene scene = new Scene(root);
                                                        Stage stage = new Stage();
                                                        stage.setScene(scene);
                                                        stage.show();
                                                    } catch (IOException ex) {
                                                         ex.printStackTrace();  
                                                    }
                                     }
                            }
                            
                            
                       }
        }else{  
                                           Alert alert = new Alert(AlertType.ERROR);
                                            alert.setTitle("Erreur de connexion");
                                            alert.setHeaderText("Informations d'identification incorrectes");
                                            alert.setContentText("Le nom d'utilisateur ou le mot de passe est incorrect. Veuillez réessayer.");
                                            alert.showAndWait();
                                     }
                                     
                            
                      //  }
        //}
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
//return login   ;                           
}
    
     @Override
    public void blockuser(Utilisateur U) {
               // String requete = "insert into utilisateur (id,nom,prenom,email,password,role,is_Blocked) values (?,?,?,?,?,?,?)";
   

         String requete = "update utilisateur set is_blocked=? where Id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setBoolean(1, true);
            ps.setInt(2, U.getId());
           

            //ps.setBoolean(7, U.getisBlocked());
            ps.executeUpdate();
            System.out.println("bloquer");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du bloquage " + ex.getMessage());
        }
    }
 @Override
    public void deblockuser(Utilisateur U) {
               // String requete = "insert into utilisateur (id,nom,prenom,email,password,role,is_Blocked) values (?,?,?,?,?,?,?)";
   

         String requete = "update utilisateur set is_blocked=? where Id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setBoolean(1, false);
            ps.setInt(2, U.getId());
           

            //ps.setBoolean(7, U.getisBlocked());
            ps.executeUpdate();
            System.out.println("bloquer");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du bloquage " + ex.getMessage());
        }
    }

    @Override
     public void updatePassword(String email, String newPassword) {
        // Connexion à la base de données
       
        
        // Requête SQL pour mettre à jour le mot de passe de l'utilisateur avec l'e-mail spécifié
        String query = "update utilisateur set password = ? WHERE email = ?";
        String hashedMdp = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        PreparedStatement stmt;
           try {
               stmt = connection.prepareStatement(query);
                stmt.setString(1, hashedMdp);
                stmt.setString(2, email);
                 int rowsAffected = stmt.executeUpdate();
                             System.out.println("mdp changer");

           } catch (SQLException ex) {
               Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, ex);
           }
        
        
    }
     
 
    

    @Override
    public Utilisateur findDepotByAdresse(String adr) {
        return null;
    }

    @Override
    public List<Utilisateur> DisplayAllDepots() {
        return null;
    }
    
    private static IUtilisateurDAO IUtilisateurDAO;

    public static IUtilisateurDAO getInstance() {
        if (IUtilisateurDAO == null) {
            IUtilisateurDAO = new UtilisateurDAO();
        }
        return IUtilisateurDAO;
    }

    @Override
    public ObservableList<Utilisateur> displayAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
