/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.view;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import pi.dao.classes.Session;
import pi.dao.classes.UtilisateurDAO;
import pi.dao.interfaces.IUtilisateurDAO;
import pi.entities.Utilisateur;
import pi.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author swide
 */
public class ModifierController implements Initializable {
    private Connection connection = MyConnection.getInstance();

    @FXML
    private Label cin;
    @FXML
    private TextField nom;
    @FXML
    private TextField email;
    @FXML
    private TextField prenom;
    @FXML
    private TextField mdp;
    @FXML
    private Button valider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Utilisateur utilisateur = Session.getInstance().getLoggedUser();
         String id = Integer.toString(utilisateur.getId());
        
         cin.setText(id);
         nom.setText(utilisateur.getNom());
         email.setText(utilisateur.getEmail());
         prenom.setText(utilisateur.getPrenom());
         mdp.setText(utilisateur.getPassword());
         
        // TODO
    }    

    @FXML
    private void valider(ActionEvent event) {
          Utilisateur utilisateur = Session.getInstance().getLoggedUser();
           IUtilisateurDAO Utilisateurdao = UtilisateurDAO.getInstance();
           Utilisateurdao.updateUtilisateur(utilisateur);
           
           
           String Cin ;
        int id=0;
        Cin = cin.getText().toString();
        if(isValidId(Cin)){
         id = Integer.parseInt(Cin);
        }
        //PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String Nom;
        Nom=nom.getText();
        
        String Prenom;
        Prenom=prenom.getText();
        
        String Mdp;
        Mdp=mdp.getText();
        
        String Email;
        Email=email.getText();
        
              
      //  Utilisateur Utilisateur = new Utilisateur();
        utilisateur.setId(id);
        utilisateur.setNom(Nom);
        utilisateur.setPrenom(Prenom);
        utilisateur.setPassword(Mdp);
        utilisateur.setEmail(Email);
        utilisateur.setRole("Client");
        utilisateur.setisBlocked(false);
               // IUtilisateurDAO Utilisateurdao = UtilisateurDAO.getInstance();
        Utilisateurdao.updateUtilisateur(utilisateur);
        JOptionPane.showMessageDialog(null, "modification effectue !!");
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pi/view/profil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    
          
    }
    
    
      public void showErrorMessage(String message) {
    JOptionPane.showMessageDialog(null, message, "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
}
    
      public boolean isValidId(String input) {
    if (input == null || input.isEmpty()) {
        showErrorMessage("L'ID ne peut pas être vide.");
        return false;
    } 
     //String x=String.valueOf(input);
    if (input.length() != 8) {
        showErrorMessage("Le nombre doit contenir exactement 8 chiffres.");
        return false;
    }
    if (!input.matches("\\d+")) {
        showErrorMessage("L'ID doit contenir uniquement des chiffres.");
        return false;
    }
    int id = Integer.parseInt(input);
    if (id <= 0) {
        showErrorMessage("L'ID doit être un entier positif.");
        return false;
    }
    return true;

    
}
     
     public boolean isValidNom(String input) {
    if (input.isEmpty()) {
        showErrorMessage("Le nom ne doit pas etre vide.");
                return false;

} else if (!input.matches("[a-zA-Z]+")) {
        showErrorMessage("Le nom ne doit contenir que des lettre.");
                return false;

}    
    return true;
}
     
public boolean verifierEmail(String email) {
    if (email == null || email.isEmpty()) {
        return false;
    }
    if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
         showErrorMessage("L'email est obligatoire et doit etres valide !!.");
        return false;
    }
 
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {

        ps = connection.prepareStatement("SELECT * FROM Utilisateur WHERE email = ?");
        ps.setString(1, email);
        rs = ps.executeQuery();
        boolean x=rs.next();   
        if( x == true ){ 
            showErrorMessage("L'email doit etres UNIQUE !!.");
            return false;
            }
         return !rs.next();
    } catch (SQLException e) {
        e.printStackTrace();

        return false;
    } 
}
    
}
