/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.Controlleur;

import java.io.IOException;
import pi.dao.classes.ActiviteDAO;
import pi.dao.classes.IActiviteDao;
import pi.entities.Activite;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pi.view.HomeController;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ActiviteAffController implements Initializable {

    @FXML
    private ScrollPane meniScroll;

    @FXML
    private AnchorPane menuform;

    @FXML
    private GridPane menugrid;
    private ObservableList<Activite> menuListActivite;
        private ObservableList<Activite> activite = FXCollections.observableArrayList();
    @FXML
    private Button profile;

    public void menuActivite(){
        
        IActiviteDao pdao = ActiviteDAO.getInstance();
        activite = pdao.displayAll();
        
        int row=0;
                int column=0;

        menugrid.getRowConstraints().clear();
        menugrid.getColumnConstraints().clear();
        for(int q=0;q< activite.size(); q++ ){
            try{
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/pi/view/Activite.fxml"));
            AnchorPane pane = load.load();
            ActiviteControlleur AcCont=load.getController();
            AcCont.setActivite(activite.get(q));
            if (column == 3){
                column=0;
                row+=1;
            }
            menugrid.add(pane, column++, row);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
  
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IActiviteDao pdao = ActiviteDAO.getInstance();
        activite = pdao.displayAll();
        
        int row=0;
                int column=0;

        menugrid.getRowConstraints().clear();
        menugrid.getColumnConstraints().clear();
        for(int q=0;q< activite.size(); q++ ){
            try{
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/pi/view/Activite.fxml"));
            AnchorPane pane = load.load();
            ActiviteControlleur AcCont=load.getController();
            AcCont.setActivite(activite.get(q));
            if (column == 3){
                column=0;
                row+=1;
            }
            menugrid.add(pane, column++, row);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }    

    @FXML
    private void profile(ActionEvent event) {
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
