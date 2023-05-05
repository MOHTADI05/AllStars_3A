/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.GUIcmd;

import entiteCmd.Commande;
import entiteCmd.SendSMS;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pi.dao.classes.Session;
import pi.view.HomeController;
import serviceCmd.ServiceCommande;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AddcommandeController implements Initializable {

    @FXML
    private TextField addField;
    @FXML
    private TextField prixField;
    @FXML
    private DatePicker mailField;
    @FXML
    private TextField numField;
    @FXML
    private Button btnAdd;
    @FXML
    private Button toAFF;
    @FXML
    private Button Act;
    @FXML
    private Button reclamtion;
    @FXML
    private Button deconnecter12;
    @FXML
    private Button profil;
    @FXML
    private Button profil1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    ServiceCommande sr = new ServiceCommande() ;
    Commande r = new Commande();

    @FXML
    private void AjouterCommande(ActionEvent event) {
        
        if (addField.getText().isEmpty() || 
        prixField.getText().isEmpty() || /*mailField.getValue().i || */numField.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs !");
        alert.showAndWait();
        return;
    }
// Vérification de la validité de l'adresse e-mail
/*String emailRegex = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
Matcher matcher = pattern.matcher(mailField.getText());
if (!matcher.matches()) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez saisir une adresse e-mail valide !");
    alert.showAndWait();
    
   
    return;
}*/

r.setNomP(addField.getText());

    try {
        r.setPrix(Float.parseFloat(prixField.getText()));
    } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Le champ prix doit étre un nombre !");
        alert.showAndWait();
        return;
    }
r.setDate(mailField.getValue());

/*if (!numField.getText().matches("\\d{8}")) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Le champ numField doit contenir exactement 8 chiffres !");
    alert.showAndWait();
    return;
}
  r.setQuantite(Integer.parseInt(numField.getText()));*/
  try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pi/GUIcmd/AfficherCommande.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

/*sr.ajouterCommande(r);
SendSMS s = new SendSMS();
        SendSMS.sendSms("Votre commande a bien été enregistrer , Elle sera livrer bientot , Merci pour votre confiance !! ", numField.getText());

Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Succès");
alert.setHeaderText(null);
alert.setContentText("Votre Commande est ajoutée !");
alert.showAndWait();
       */
    
        
        
        
    }

    @FXML
    private void Afficher(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/GUIcmd/AfficherCommande.fxml"));
                Parent root = loader.load();
               AfficherCommandeController aa = loader.getController();
                toAFF.getScene().setRoot(root);
        
        
    }
    
    
    private void retour_rub(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/view/profil.fxml"));
                Parent root = loader.load();
               RubriqueCommandeController aa = loader.getController();
                toAFF.getScene().setRoot(root);
        
        
    }

    @FXML
    private void Act(ActionEvent event) {
    }

    @FXML
    private void reclamtion(ActionEvent event) {
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

    @FXML
    private void profil(ActionEvent event) {
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

    @FXML
    private void profil1(ActionEvent event) {
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
    
}
