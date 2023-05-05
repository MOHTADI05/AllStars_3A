/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.GUIcmd;

import entiteCmd.Commande;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import pi.dao.classes.Session;
import pi.view.HomeController;
import serviceCmd.ServiceCommande;

/**
 * FXML Controller class
 *
 * @author user
 */
public class EditCommandeController implements Initializable {

    @FXML
    private TextField addFieldM;
    @FXML
    private TextField nomFieldM;
    @FXML
    private TextField prixFieldM;
    @FXML
    private TextField mailFieldM;
    private TextField numFieldM;
    @FXML
    private Button btnEdit;
    @FXML
    private Button toAFFM;
    @FXML
    private Button To_rubrique;
    private Label id;
        
       private int  idC;  
      Commande r = new Commande();
    @FXML
    private Button Act;
    @FXML
    private Button reclamtion;
    @FXML
    private Button deconnecter12;
    @FXML
    private Button profil;
    @FXML
    private DatePicker mailField;
        
        
    /**
     * Initializes the controller class.
     */
        
         public void tarata()
       {
        
        ServiceCommande su = new ServiceCommande();
        r=su.readById(idC);
        //System.out.print(e);
       }
         
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
 ServiceCommande su = new ServiceCommande() ; 

    @FXML
    private void ModifierCommande(ActionEvent event) throws IOException {
       
     Commande c = new Commande();
     ServiceCommande su = new ServiceCommande() ; 
     
        c.setId_cmd(idC);
        c.setNomP(addFieldM.getText());
       
       // c.setDate(mailFieldM.getValue());
        c.setQuantite(Integer.parseInt(numFieldM.getText()));
     
        try {
        c.setPrix(Float.parseFloat(prixFieldM.getText()));
    } catch (NumberFormatException r) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Le champ prix doit étre un nombre !");
        alert.showAndWait();
        return;
    }
     if (!numFieldM.getText().matches("\\d{8}")) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Le champ numField doit contenir exactement 8 chiffres !");
    alert.showAndWait();
    return;
}
  r.setQuantite(Integer.parseInt(numFieldM.getText()));
// try {
//    c.setQuantite(Integer.parseInt(numFieldM.getText()));
//} catch (NumberFormatException r) {
//    Alert alert = new Alert(Alert.AlertType.ERROR);
//    alert.setTitle("Erreur");
//    alert.setHeaderText(null);
//    alert.setContentText("Le champ nb_place doit être un nombre entier!");
//    alert.showAndWait();
//    return;
//}
        
     
        
        su.modifierCommande(c);
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("modification éffectuée");
                alert.showAndWait();
       
}

    

    @FXML
    private void Afficher2(ActionEvent event) throws IOException {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/GUIcmd/AfficherCommande.fxml"));
                Parent root = loader.load();
               AfficherCommandeController aa = loader.getController();
                toAFFM.getScene().setRoot(root);
        
    }

    @FXML
    private void pass_rubrique(ActionEvent event) throws IOException {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/view/profil.fxml"));
                Parent root = loader.load();
               RubriqueCommandeController aa = loader.getController();
                To_rubrique.getScene().setRoot(root);
        
    }

   public void initData(int data) {
       this.idC=data;
      String stringNumber = Integer.toString(idC);

      id.setText(stringNumber);
      tarata();
      
    addFieldM.setText(r.getNomP());
  
    prixFieldM.setText(String.valueOf(r.getPrix()));
 ///   mailFieldM.setText(r.getDate());
    numFieldM.setText(String.valueOf(r.getQuantite()));
            }

    @FXML
    private void Act(ActionEvent event) {
    }

    @FXML
    private void reclamtion(ActionEvent event) {
    }

    @FXML
    private void deconnecter(ActionEvent event) {
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
}
