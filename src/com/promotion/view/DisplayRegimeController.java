/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promotion.view;

import com.promotion.entity.Regime;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mohta
 */
public class DisplayRegimeController implements Initializable {
   @FXML
    private Button btn_voirPlus;

    @FXML
    private Label nomtxt;
    Regime regime;
    /**
     * Initializes the controller class.
     */
    public void setActivite(Regime regime){
         this.regime=regime;
         nomtxt.setText(regime.getName());
         
           btn_voirPlus.setOnAction(event -> {
    
                    try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/voirPlus.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(DisplayRegimeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
          
      }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
