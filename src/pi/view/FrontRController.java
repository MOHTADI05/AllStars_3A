/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.view;

import pi.entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pi.dao.classes.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class FrontRController implements Initializable {
    
    @FXML
    private TextArea tfdescription;

    @FXML
    private TextField tfobject;

    @FXML
    private Button voirR;
       ServiceReclamation sr=new ServiceReclamation();
    @FXML
    private Button profil;

@FXML
    private void envoyer(ActionEvent event) {
        Reclamation r=new Reclamation();
        r.setDate_reclamation(new Date(System.currentTimeMillis()));
        r.setDescription(tfdescription.getText());
        r.setEmail("aaa@gmail.com");
        r.setEtat(0);
        r.setObjet(tfobject.getText());
        if(controleDeSaisie().length()>0){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning adding claim");
            alert.setContentText(controleDeSaisie());
            alert.showAndWait();
        }
        else{
            sr.ajouter(r);
            
        }
        
    }
      public String controleDeSaisie(){
        String erreurs="";
        if(tfobject.getText().trim().isEmpty()){
            erreurs+="-Le champs objet est vide!\n";
        }
        if(tfdescription.getText().trim().isEmpty()){
            erreurs+="-Le champs description est vide!\n";
        }
        if(tfdescription.getText().trim().length()<10){
            erreurs+="-Le champs description doit etre > 10 charactere!\n";
        }
        return erreurs;
    }
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        voirR.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pi/view/listR.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FrontRController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
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
               ex.printStackTrace();
            }
    }
    }    
    

