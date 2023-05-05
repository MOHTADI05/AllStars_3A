/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promotion.view;

import pi.dao.classes.RegimeDao;
import pi.entities.Regime;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author MSI
 */
public class RegimeController implements Initializable{

    
    
     @FXML
    private Label Aname;

    @FXML
    private ImageView acImage;

    @FXML
    private Button  btnVoirplus;
     private Regime activite;
     private Image image;
     public void setActivite(Regime regime){
         this.activite=activite;
         data.id=activite.getId();
         Aname.setText(activite.getObjectif());
         
       
         
           btnVoirplus.setOnAction(event -> {
          data.id=activite.getId();
    
                    try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/voirPlus.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
          
      }
    /* 
private void handleBtnVoirPlus(ActionEvent event) {
    
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/view/voirPlus.fxml"));
        Parent root = loader.load();
        VoirPlusController detailsActiviteController = loader.getController();
        detailsActiviteController.setActivite(activite);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }  
}*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
}
