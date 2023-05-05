/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceCmd;

import entiteCmd.Commande;
import entiteCmd.Livraison;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pi.utils.MyConnection;

/**
 *
 * @author user
 */
public class ServiceLivraison implements IServiceLivraison< Livraison> {
    private Connection conn;
    PreparedStatement stmt;
    PreparedStatement ste;

    public ServiceLivraison() {
         conn = MyConnection.getInstance();
    }
    
    @Override
    public void ajouterLivraison(Livraison l) {
       try {
            // Define the SQL INSERT statement
            String sql = "INSERT INTO livraison (adresse,date,status,id_c) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            
            // Set the parameter values for the INSERT statement
            stmt.setDate(2,Date.valueOf(l.getDate_liv()));
          
            stmt.setString(3, l.getTransporteur());
            stmt.setString(1, l.getAddress_liv());
           
            stmt.setInt(4, l.getCmd().getId_cmd());
          
            
           
             stmt.executeUpdate();
             System.out.println("Livraison ajoutée avec succés !");
              }catch (SQLException e) {
           Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, e);
         }

  
    }

    @Override
    public List<Livraison> afficherLivraison() {
        
    List<Livraison> livraisons = new ArrayList<>();
    String sql = "SELECT * FROM livraison";

    try {
        ste = conn.prepareStatement(sql);
        ResultSet rs = ste.executeQuery();

        while (rs.next()) {
            Livraison L = new Livraison();
            L.setId_liv(rs.getInt("id"));
            L.setDate_liv(rs.getDate("date").toLocalDate());
            L.setTransporteur(rs.getString("status"));
            L.setAddress_liv(rs.getString("adresse"));
            
           
             
             // Récupérer l'offre associée à l'entretien
            ServiceCommande sc = new ServiceCommande();
            Commande commande = sc.rechercherCommande(rs.getInt("id_c"));
              L.setCmd(commande);

             
             
   

            livraisons.add(L);

           // System.out.println("ID entretien : " + e.getId_ent() + "\nDate : " + e.getDate_ent() + "\nHeure : " + e.getHeure() + "\nLieu : " + e.getLieu() + "\nIntervieweur : " + e.getNom_interviewr() + "\nType : " + e.getType() + "\nOffre associée : " + e.getOff().getNom());
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    
    return livraisons;
}
    

    
    @Override
    public void supprimerLivraison(int id_Liv) {
        String sql = "DELETE from livraison where id = '"+id_Liv+"' ";
        try{
           Statement st= conn.createStatement();
           st.executeUpdate(sql);
           System.out.println("livraiisin supprimée avec succés !");
       }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    }

    @Override
    public void modifierLivraison(Livraison e,int i) {
         try {
       
         String req = "UPDATE `livraison` SET  `id`='" + e.getId_liv()+ "',`adresse`='" + e.getAddress_liv()
                 + "', `date`='" + e.getDate_liv()+ "',`status`='" + e.getTransporteur()
                +"',`id_c`='"+e.getCmd()+
                 "' WHERE id=" + i;
         
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("La commande  est modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    @Override
    public List<Livraison> displayByID(int id_Liv) {
        List <Livraison> myList= new ArrayList<>();

    String req="select * from livraison where id='"+id_Liv+"' ";

    try {
        Statement st = conn.createStatement();
        ResultSet rs=st.executeQuery(req);
        while(rs.next()){
          Livraison L = new Livraison();
            L.setId_liv(rs.getInt("id"));
            L.setDate_liv(rs.getDate("date").toLocalDate());
            L.setTransporteur(rs.getString("status"));
            L.setAddress_liv(rs.getString("adresse"));
            
           
             
             // Récupérer l'offre associée à l'entretien
            ServiceCommande sc = new ServiceCommande();
            Commande commande = sc.rechercherCommande(rs.getInt("id_c"));
              L.setCmd(commande);

        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return myList;
        
        
    }
    
   
    public ObservableList <Livraison> afficherLivraison2() {
             String querry ="SELECT * FROM  livraison";

ObservableList <Livraison> ch = FXCollections.observableArrayList();
    
        try {
        Statement stm =conn.createStatement();
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            Livraison L = new Livraison();

            L.setId_liv(rs.getInt("id"));
            L.setDate_liv(rs.getDate("date").toLocalDate());
            L.setTransporteur(rs.getString("status"));
            L.setAddress_liv(rs.getString("adresse"));
 
             ServiceCommande sc = new ServiceCommande();
            Commande commande = sc.rechercherCommande(rs.getInt("id_c"));
              L.setCmd(commande);
            
            ch.add(L);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
     return ch ;   
    }
    
     public ObservableList <Livraison> afficherLivraisonById(int id_liv) {
     String querry ="select * from livraison where id='"+id_liv+"' ";

ObservableList <Livraison> ch = FXCollections.observableArrayList();
    
        try {
        Statement stm =conn.createStatement();
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
       Livraison L = new Livraison();
                      L.setId_liv(rs.getInt("id"));
                      L.setDate_liv(rs.getDate("date").toLocalDate());
                      L.setTransporteur(rs.getString("status"));
                      L.setAddress_liv(rs.getString("adresse"));
                      
                       ServiceCommande sc = new ServiceCommande();
            Commande commande = sc.rechercherCommande(rs.getInt("id_c"));
              L.setCmd(commande);
              
                      ch.add(L);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
     return ch ;   
    }

   public boolean existeLivraison(int id) {
        try {
        PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM livraison WHERE id = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int count = rs.getInt(1);
            return count > 0;
        }
        return false;
    } catch (SQLException ex) {
        ex.printStackTrace();
        return false;
    }
    }
    
       public Livraison getLivraison(int id) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Livraison livraison = null;

    try {
        stmt = conn.prepareStatement("SELECT * FROM livraison WHERE id= ?");
        stmt.setInt(1, id);
        rs = stmt.executeQuery();

        if (rs.next()) {
            livraison = new Livraison(
                rs.getInt("id"),
                rs.getDate("date").toLocalDate(),
                rs.getString("status"),
                rs.getString("adresse")
                    
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close resources
    }

    return livraison;
}

       
         public Livraison rechercherLivraison(int id) {
    String sql = "SELECT * FROM livraison WHERE id = ?";
    try {
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Livraison livraison = new Livraison(); 
            
                  livraison.setId_liv(rs.getInt("id"));
                  livraison.setDate_liv(rs.getDate("date").toLocalDate());
                  livraison.setTransporteur(rs.getString("status"));
                  livraison.setAddress_liv(rs.getString("adresse"));
      
            return livraison;
        } else {
            return null;
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    }
}
         
         
          ///////////////////////afficherCommandeByNom///////////////////////////////////////////
    
    public ObservableList <Livraison> afficherCommandeByNom(String address_liv) {
     String query ="select * from livraison where adresse=?";

ObservableList <Livraison> ch = FXCollections.observableArrayList();
    
        try {
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, address_liv);
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            Livraison L = new Livraison();

                       L.setId_liv(rs.getInt("id"));
                      L.setDate_liv(rs.getDate("date").toLocalDate());
                      L.setTransporteur(rs.getString("status"));
                      L.setAddress_liv(rs.getString("adresse"));
            
            ch.add(L);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
     return ch ;   
    }
   
     ////////////////////////////////////////affichercommandeByPrenom///////////////////////////////////////////
    
     public ObservableList<Livraison> affichercommandeByPrenom(String transporteur) {
    String query = "SELECT * FROM livraison WHERE status = ?";
    ObservableList<Livraison> livraison = FXCollections.observableArrayList();
    
    try {
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, transporteur);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            Livraison L = new Livraison();
            
                      L.setId_liv(rs.getInt("id"));
                      L.setDate_liv(rs.getDate("date").toLocalDate());
                      L.setTransporteur(rs.getString("status"));
                      L.setAddress_liv(rs.getString("adresse"));
            
            
            livraison.add(L);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage()); 
    }
    
    return livraison;
}
     
      public Livraison readById(int id) {
        Livraison L = null; // Déclarer la variable L en dehors du bloc if

    try {
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM livraison WHERE id= "+id);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            
            L = new Livraison (
                          rs.getInt(1), 
                          rs.getDate(2).toLocalDate(), 
                          rs.getString(3), 
                          rs.getString(4)
           
            );
                          
           //System.out.print("aaaa:"+p);
                    }
    } catch (SQLException e) {
            // Gérer l'exception SQLException
            System.out.print(e.getMessage());
            Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, e);
    }
    return L;
    }
      
      public void supprimerLivraison(Livraison L) throws SQLException {
               String req="delete from livraison where id=?";
       
       // try {
            
            PreparedStatement stm;
            stm=conn.prepareStatement(req);
            stm.setInt(1,L.getId_liv());
            int i=stm.executeUpdate();
            System.out.println(i+ " Livraison  suprimé");
        //} catch (SQLException ex) {
            //System.out.println(ex.getMessage());
        //}
    }
      
        public Iterable<Livraison> getListeLivraison() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
         public ObservableList<Livraison> search2(String searchTerm) {
        ObservableList<Livraison> list = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM livraison");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Livraison livraison = new Livraison(

                          rs.getInt(1), 
                          rs.getDate(2).toLocalDate(), 
                          rs.getString(3), 
                          rs.getString(4)
            
                );
                list.add(livraison);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
         
       public ObservableList<Livraison> search3(String searchTerm) {
    ObservableList<Livraison> list = FXCollections.observableArrayList();
    try {
        String query = "SELECT * FROM livraison WHERE adresse LIKE ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, searchTerm + "%");
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Livraison livraison = new Livraison(
                    
                          rs.getInt(1), 
                          rs.getDate(2).toLocalDate(), 
                          rs.getString(3), 
                          rs.getString(4)
                         
            );
            list.add(livraison);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}

    

 public Livraison displayLivraisonById(int id_liv) {
    Livraison e = null;
    String sql = "SELECT * FROM livraison WHERE id=?";

    try {
        ste = conn.prepareStatement(sql);
        ste.setInt(1, id_liv);
        ResultSet rs = ste.executeQuery();

        if (rs.next()) {
            e = new Livraison();
            e.setId_liv(rs.getInt("id"));
            e.setDate_liv(rs.getDate("date").toLocalDate());
            e.setTransporteur(rs.getString("status"));
            e.setAddress_liv(rs.getString("adresse"));

            // Récupérer l'id de la commande associée à la livraison
            ServiceCommande servicecommande = new ServiceCommande();
            Commande commande = servicecommande.getCommandeById(rs.getInt("id_c"));
            e.setCmd(commande);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return e;
}

    public void modifierLivraison2(Livraison e) {
            try {
       String req = "UPDATE `livraison` SET `date`=?, `status`=?, `adresse`=?, `id_c`=? WHERE `id`=?" ;
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setDate(1, Date.valueOf(e.getDate_liv()));
        ps.setString(2, e.getTransporteur());
        ps.setString(3, e.getAddress_liv());
        ps.setInt(4, e.getCmd().getId_cmd());
        ps.setInt(5, e.getId_liv());

        ps.executeUpdate();
        System.out.println("L'entretien est modifié !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    }

    public ObservableList<Livraison> searchtransprteur(String searchTerm) {
         ObservableList<Livraison> list = FXCollections.observableArrayList();
    try {
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM livraison WHERE status LIKE ?");
        preparedStatement.setString(1, "%" + searchTerm + "%");
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Livraison e = new Livraison();
            e = new Livraison();
             e.setId_liv(rs.getInt("id"));
            e.setDate_liv(rs.getDate("date").toLocalDate());
            e.setTransporteur(rs.getString("status"));
            e.setAddress_liv(rs.getString("adresse"));

            // Récupérer l'offre associée à l'entretien
              ServiceCommande servicecommande = new ServiceCommande();
            Commande commande = servicecommande.getCommandeById(rs.getInt("id_c"));
            e.setCmd(commande);
            
            
            list.add(e);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
    }
   

  
    
}
