/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.Controlleur;

import esprit.dao.entities.Activite;
import esprit.dao.entities.Exercice;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ExDisplayController implements Initializable {
     @FXML
    private Button dislike;

    @FXML
    private AnchorPane exForm;

    @FXML
    private ImageView img_ex;

    @FXML
    private Button like;

    @FXML
    private Label nom;

    @FXML
    private Label rep;

    @FXML
    private Label temps;
    
    private Image image;
         private Exercice exercice;

     public void setExercice(Exercice exercice){
         this.exercice=exercice;
         nom.setText(exercice.getNom_Exercice());
         rep.setText(exercice.getRepet());
         temps.setText(exercice.getTemps());
         String path= "File:"+ exercice.getImage_Exer();
         image= new Image(path, 117, 129 ,false, true );
         img_ex.setImage(image);
      }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
