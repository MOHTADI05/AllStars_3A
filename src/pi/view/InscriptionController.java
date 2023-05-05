/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import pi.dao.classes.UtilisateurDAO;
import pi.dao.interfaces.IUtilisateurDAO;
import pi.entities.Utilisateur;
import pi.utils.MyConnection;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author swide
 */
public class InscriptionController implements Initializable {
    
    private Connection connection = MyConnection.getInstance();
     //connection = MyConnection.getInstance();

    @FXML
    private Button inscription;
    @FXML
    private TextField cin;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField mdp;
    private TextField role;
    @FXML
    private Button retour;
    @FXML
    private PasswordField confirmerMdp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void inscription(ActionEvent event) {
        
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
        
        String passwordConfirmation = confirmerMdp.getText();
        
        /*String Role;
        Role=role.getText();*/
              
        Utilisateur Utilisateur = new Utilisateur();
        Utilisateur.setId(id);
        Utilisateur.setNom(Nom);
        Utilisateur.setPrenom(Prenom);
        Utilisateur.setPassword(Mdp);
        Utilisateur.setEmail(Email);
        Utilisateur.setRole("Client");
        Utilisateur.setisBlocked(false);
        
                IUtilisateurDAO Utilisateurdao = UtilisateurDAO.getInstance();
        if (!Mdp.equals(passwordConfirmation)) {
        // Les mots de passe ne correspondent pas
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Les mots de passe ne correspondent pas");
        alert.showAndWait();
        return;
    }            if(isValidNom(Nom)&& isValidId(Cin)&&verifierEmail(Email)){     
        Utilisateurdao.inscription(Utilisateur);
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Personne insérée avec succés!");
        alert.show();
        nom.setText("");
        prenom.setText("");
             try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pi/view/login.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            else{ 
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("echec de l'inscription!");}
       
        
        
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
       PreparedStatement ps = null;
    ResultSet rs = null;
     try {
        ps = connection.prepareStatement("SELECT * FROM Utilisateur WHERE id = ?");
        ps.setString(1, input);
        rs = ps.executeQuery();
        boolean x=rs.next();   
        if( x == true ){ 
            showErrorMessage("L'cin doit etres UNIQUE !!.");
            return false;
            }
         return !rs.next();
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    } 
    
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

    @FXML
    private void retour(ActionEvent event) {
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pi/view/login.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
