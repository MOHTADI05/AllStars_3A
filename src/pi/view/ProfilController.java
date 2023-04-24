/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.view;

import com.itextpdf.text.Image;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import pi.dao.classes.Session;
import pi.dao.classes.UtilisateurDAO;
import pi.dao.interfaces.IUtilisateurDAO;
import pi.entities.Utilisateur;

/**
 * FXML Controller class
 *
 * @author swide
 */
public class ProfilController implements Initializable {

    @FXML
    private Label cin;
    @FXML
    private Label nom;
    @FXML
    private Label email;
    @FXML
    private Label prenom;
    @FXML
    private Label mdp;
    @FXML
    private Label role;
    @FXML
    private Button modifier;
    @FXML
    private Button deconnecter;
    @FXML
    private Label cin1;
    @FXML
    private Label nom1;
    @FXML
    private Label email1;
    @FXML
    private Label prenom1;
    @FXML
    private Label mdp1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        
         IUtilisateurDAO Utilisateurdao = UtilisateurDAO.getInstance();
              
       Utilisateur utilisateur = Session.getInstance().getLoggedUser();
          if (utilisateur != null) {
                   int id=utilisateur.getId();
                   String Cin ;
                   Cin =  Integer.toString(id);
    cin.setText(Cin);
    nom.setText(utilisateur.getNom());
    prenom.setText(utilisateur.getPrenom());
    email.setText(utilisateur.getEmail());
  //      mdp.setText(utilisateur.getPassword());
    role.setText(utilisateur.getRole());
    // etc. pour afficher les autres données de l'utilisateur

} else {
    JOptionPane.showMessageDialog(null, "erreur", "compte pas trouver", JOptionPane.ERROR_MESSAGE);
}


        // TODO
    }    

    @FXML
    private void modifier(ActionEvent event) {
        
         IUtilisateurDAO Utilisateurdao = UtilisateurDAO.getInstance();
           Utilisateur utilisateur = Session.getInstance().getLoggedUser();
               try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pi/view/modifier.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        ;  
               
    
    }

    @FXML
    private void deconnecter(ActionEvent event) {
        Session.getInstance().clear();
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
