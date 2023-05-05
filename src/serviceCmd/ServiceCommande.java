/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceCmd;

import entiteCmd.Commande;
import java.sql.Connection;
import java.sql.Date;
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
import pi.utils.MyConnection;

/**
 *
 * @author oumayma
 */
public class ServiceCommande implements IServiceCommande<Commande> {
    
    
    private Connection conn;
    PreparedStatement stmt;
    PreparedStatement ste;

    public ServiceCommande() {
         conn = MyConnection.getInstance();
    }
 // ---------------------------------- Ajouter Commande ---------------------------------------//

    /**
     *
     * @param c
     * @return
     */
    @Override
     public void ajouterCommande(Commande c) {
        
    
          try {
              
        String sql ="INSERT INTO commande (id,nom_p,date,quantite,prix) Values(?,?,?,?,?)";
        stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        
        // Set the parameter values for the INSERT statement
        
      
           ste=conn.prepareStatement(sql);
             stmt.setInt(1, c.getId_cmd());
             stmt.setString (2,c.getNomP());
                          //stmt.setString(3,c.getDate());
                          stmt.setDate(3,Date.valueOf(c.getDate()));

             stmt.setInt(4, c.getQuantite());
            
             stmt.setFloat(5,c.getPrix());
             stmt.executeUpdate();
           System.out.println("Commande Ajoutée avec succés !");
           }catch (SQLException e) {
           Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, e);
              }
     }
    

        //--------------------------------------- Afficher Commande ------------------------------------------------//
    @Override
    
     public List<Commande> afficherCommande(){
        List<Commande> commande =  new ArrayList<>();
      String sql="select * from commande";
      
      try
      {
          ste=conn.prepareStatement(sql);
          
          ResultSet rs=ste.executeQuery();
                  while(rs.next()){
                      Commande C = new Commande();
                      C.setId_cmd(rs.getInt("id"));
                      C.setNomP(rs.getString("nom_p"));
                      C.setQuantite(rs.getInt("quantite"));
                  
                      C.setPrix(rs.getFloat("prix"));
                      C.setDate(                rs.getDate("date").toLocalDate());
                      //commande.add(c);
                      System.out.println("id commande : "+C.getId_cmd()+ "\n nom de livraison : "+C.getNomP()+ "\n Quantite: "+C.getQuantite()+"\n  Prix: "+C.getPrix()
                              +"\n Date:"+C.getDate()) ;
                  }
      }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      return commande;
    }
 
  
    
 // ---------------------------------- Modifier Commande ---------------------------------------//

    @Override
    public void modifierCommande(Commande e) {

       
            try {
          
         String req = "UPDATE `commande` SET  `id`='" 
                 + e.getId_cmd()+ "',`nom_p`='" + e.getNomP()+
                 "',`quantite`='" 
                 + e.getQuantite()+ "',`prix`='"+e.getPrix()+"',`date`='"+e.getDate()+
                 "' WHERE id=" + e.getId_cmd();
         
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("La commande  est modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
       
       
       
        
        
        
    }
 // ---------------------------------- Supprimer Commande ---------------------------------------//

    @Override
    public void supprimerCommande(int id) {
       String sql = "DELETE from commande where id = '"+id+"' ";
        try{
           Statement st= conn.createStatement();
           st.executeUpdate(sql);
           System.out.println("Commande supprimée avec succés !");
       }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    }
    
 // ---------------------------------- chercher Commande ---------------------------------------//


    @Override
    public List<Commande> displayByID(int id)
{
        
            List <Commande> myList= new ArrayList<>();

    String req="select * from commande where id='"+id+"' ";

    try {
        Statement st = conn.createStatement();
        ResultSet rs=st.executeQuery(req);
        while(rs.next()){
        
                      Commande C = new Commande();
                      C.setId_cmd(rs.getInt("id"));
                      C.setNomP(rs.getString("nom_p"));
                      C.setQuantite(rs.getInt("quantite"));
                     
                      C.setPrix(rs.getFloat("prix"));
                      C.setDate(rs.getDate("date").toLocalDate()
);
                      myList.add(C);

        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return myList;
        
        
    }
       
    public ObservableList <Commande> afficherCommande2() {
             String querry ="SELECT * FROM `commande`";

ObservableList <Commande> ch = FXCollections.observableArrayList();
    
        try {
        Statement stm =conn.createStatement();
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            Commande c = new Commande();

                      c.setId_cmd(rs.getInt("id"));
                      c.setNomP(rs.getString("nom_p"));
                      c.setQuantite(rs.getInt("quantite"));
                     
                      c.setPrix(rs.getFloat("prix"));
                      c.setDate(rs.getDate("date").toLocalDate());

            
            ch.add(c);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
     return ch ;   
    }
    
    public ObservableList <Commande> afficherCommandeById(int id) {
     String querry ="select * from commande where id='"+id+"' ";

ObservableList <Commande> ch = FXCollections.observableArrayList();
    
        try {
        Statement stm =conn.createStatement();
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
       Commande c = new Commande();
                      c.setId_cmd(rs.getInt("id"));
                      c.setNomP(rs.getString("nom_p"));
                      c.setQuantite(rs.getInt("quantite"));
                    
                      c.setPrix(rs.getFloat("prix"));
                      c.setDate(                rs.getDate("date").toLocalDate());
                      ch.add(c);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
     return ch ;   
    }

    public boolean existeCommande(int id) {
        try {
        PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM commande WHERE id = ?");
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
    
   


    public Commande getCommande(int id) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Commande commande = null;

    try {
        stmt = conn.prepareStatement("SELECT * FROM commande WHERE id = ?");
        stmt.setInt(1, id);
        rs = stmt.executeQuery();

        if (rs.next()) {
            commande = new Commande(
                rs.getInt("id"),
                rs.getString("nom_p"),
                rs.getInt("quantite"),
               (int) rs.getFloat("prix"),
                rs.getDate("date").toLocalDate()
                   // rs.getDate("date").toLocalDate()
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close resources
    }

    return commande;
}

    public Commande rechercherCommande(int id) {
    String sql = "SELECT * FROM commande WHERE id = ?";
    try {
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Commande commande = new Commande();
            commande.setId_cmd(rs.getInt("id"));
            commande.setNomP(rs.getString("nom_p"));
          
            commande.setDate(                rs.getDate("date").toLocalDate());
            commande.setPrix(rs.getFloat("prix"));
            commande.setQuantite(rs.getInt("quantite"));
      
            return commande;
        } else {
            return null;
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    }
}
    ///////////////////////afficherCommandeByNom///////////////////////////////////////////
    
    public ObservableList <Commande> afficherCommandeByNom(String nom_p) {
     String query ="select * from commande where nom_p=?";

ObservableList <Commande> ch = FXCollections.observableArrayList();
    
        try {
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, nom_p);
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            Commande c = new Commande();

                      c.setId_cmd(rs.getInt("id"));
                      c.setNomP(rs.getString("nom_p"));
                      c.setQuantite(rs.getInt("quantite"));
               
                      c.setPrix(rs.getFloat("prix"));
                      c.setDate(                rs.getDate("date").toLocalDate());
            
            ch.add(c);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
     return ch ;   
    }
    
    ////////////////////////////////////////affichercommandeByPrenom///////////////////////////////////////////
    
     public ObservableList<Commande> affichercommandeByPrenom(String date) {
    String query = "SELECT * FROM commande WHERE date = ?";
    ObservableList<Commande> commande = FXCollections.observableArrayList();
    
    try {
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, date);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            Commande c = new Commande();
                      c.setId_cmd(rs.getInt("id"));
                      c.setNomP(rs.getString("nom_p"));
                      c.setQuantite(rs.getInt("quantite"));
                  
                      c.setPrix(rs.getFloat("prix"));
                      c.setDate(                rs.getDate("date").toLocalDate());
            
            commande.add(c);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage()); 
    }
    
    return commande;
}

     public Commande readById(int id) {
        Commande c = null; // Déclarer la variable p en dehors du bloc if

    try {
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM commande WHERE id = "+id);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            
            c = new Commande (rs.getInt(1), 
                          rs.getString(2), 
                          rs.getInt(3), 
                          (int) rs.getFloat(4),
                rs.getDate("date").toLocalDate()
            );
                          
           //System.out.print("aaaa:"+p);
                    }
    } catch (SQLException e) {
            // Gérer l'exception SQLException
            System.out.print(e.getMessage());
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, e);
    }
    return c;
    }
    
      public void supprimerCommande(Commande e) throws SQLException {
               String req="delete from commande where id=?";
       
       // try {
            
            PreparedStatement stm;
            stm=conn.prepareStatement(req);
            stm.setInt(1,e.getId_cmd());
            int i=stm.executeUpdate();
            System.out.println(i+ " Commande  suprimé");
        //} catch (SQLException ex) {
            //System.out.println(ex.getMessage());
        //}
    }
      
      public Iterable<Commande> getListeCommande() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
      public ObservableList<Commande> search2(String searchTerm) {
        ObservableList<Commande> list = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM commande");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Commande commande = new Commande(

                         rs.getInt(1), 
                          rs.getString(2), 
                          rs.getInt(4), 
                          (int) rs.getFloat(5),
                rs.getDate(3).toLocalDate()
            
                );
                list.add(commande);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
      
      public ObservableList<Commande> search3(String searchTerm) {
    ObservableList<Commande> list = FXCollections.observableArrayList();
    try {
        String query = "SELECT * FROM commande WHERE date LIKE ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, searchTerm + "%");
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Commande commande = new Commande(
                          rs.getInt(1), 
                          rs.getString(2), 
                          rs.getInt(4), 
                           (int) rs.getFloat(5),
                rs.getDate(3).toLocalDate()
            );
            list.add(commande);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}
public Commande getCommandeById(int id) {
    Commande commande = null;
    String sql = "SELECT * FROM commande WHERE id = ?";
    try {
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            commande = new Commande();
            commande.setId_cmd(rs.getInt("id"));
            commande.setNomP(rs.getString("nom_p"));
            commande.setQuantite(rs.getInt("quantite"));
            
            commande.setPrix(rs.getFloat("prix"));
            commande.setDate(                rs.getDate("date").toLocalDate());
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return commande;
}




}