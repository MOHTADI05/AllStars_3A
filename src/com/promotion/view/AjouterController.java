/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promotion.view;

import com.promotion.dao.PromotionDao;
import com.promotion.entity.Categorie;
import com.promotion.entity.Produit;
import com.promotion.entity.Promotion;
import com.promotion.utils.ConnextionSingleton;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AjouterController implements Initializable {
Connection connection = null;
    @FXML
    private Pane BoutonValider;

    @FXML
    private TextField categorie;

    @FXML
    private DatePicker end_date;

  

    @FXML
    private TextField pourcentage;

    @FXML
    private TextField produit;

    @FXML
    private Button save;

    @FXML
    private DatePicker start_date;
      void Close(ActionEvent event) {

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }  
     
    
 
    @FXML
    private void save(javafx.scene.input.MouseEvent event) {
       Categorie categ=new Categorie(1);
    Produit produitv =new Produit(1);
   java.sql.Date date_s = java.sql.Date.valueOf(start_date.getValue());
   java.sql.Date date_e = java.sql.Date.valueOf(end_date.getValue());
    String pourcentagev=pourcentage.getText();
    
   // public Promotion(int id, Date start_date, Date end_date, float pourcentage, Categorie categorie, Produit prodtuit)
    Promotion p=new Promotion(0,date_s,date_e,Float.parseFloat(pourcentagev),categ,produitv);
    PromotionDao promotiondao=new PromotionDao();
    promotiondao.insert(p);

    }

    @FXML
    private void close(javafx.scene.input.MouseEvent event) {
        
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    


}
