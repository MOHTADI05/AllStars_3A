/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.view;

import java.io.IOException;
import pi.entities.Reclamation;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pi.dao.classes.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ListRController implements Initializable {
 

    
    @FXML
    private TableView<Reclamation> tvreclamation;
    @FXML
    private TableColumn<Reclamation, String> tcemail;
    @FXML
    private TableColumn<Reclamation, String> tcobjet;
    @FXML
    private TableColumn<Reclamation, String> tcdescription;
    @FXML
    private TableColumn<Reclamation, Date> tcdatereclamation;
    @FXML
    private TableColumn<Reclamation, Date> tcdatetraitement;
    @FXML
    private TableColumn<Reclamation, String> tcetat;
    
    private TextArea tfdescription;

    private TextField tfobject;
        ObservableList<Reclamation> data=FXCollections.observableArrayList();
   ServiceReclamation sr=new ServiceReclamation();
    @FXML
    private AnchorPane menuForm;
    @FXML
    private Button Profil;
    @FXML
    private Button Retour;
    /**
     * Initializes the controller class.
     */
     public void refresh(){
        data.clear();
        data=FXCollections.observableArrayList(sr.afficherReclamationUser("aaa@gmail.com"));
        tcemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tcobjet.setCellValueFactory(new PropertyValueFactory<>("objet"));
        tcetat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tcdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tcdatereclamation.setCellValueFactory(new PropertyValueFactory<>("date_reclamation"));
        //tcdatetraitement.setCellValueFactory(new PropertyValueFactory<>("date_traitement"));
        tvreclamation.setItems(data);
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
     @FXML
    private void fillforum(MouseEvent event) {
        Reclamation r=tvreclamation.getSelectionModel().getSelectedItem();
        if(r!=null){
            tfobject.setText(r.getObjet());
            tfdescription.setText(r.getDescription());
        }
    }
    private void modifier(ActionEvent event) {
        Reclamation r=tvreclamation.getSelectionModel().getSelectedItem();
        if(r!=null){
            
            r.setDate_reclamation(new Date(System.currentTimeMillis()));
            r.setDescription(tfdescription.getText());
            r.setEmail("aaa@gmail.com");
            r.setEtat(0);
            r.setObjet(tfobject.getText());
            if(controleDeSaisie().length()>0){
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning modifying claim");
                alert.setContentText(controleDeSaisie());
                alert.showAndWait();
            }
            else{
                sr.modifier(r.getId(),r);
                refresh();
            }
            
        }
        
    }

    @FXML
    private void supprimer(ActionEvent event) {
        Reclamation r=tvreclamation.getSelectionModel().getSelectedItem();
        if(r!=null){
            sr.supprimer(r.getId());
            refresh();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
refresh();
    }    

    @FXML
    private void Profil(ActionEvent event) {
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

    @FXML
    private void Retour(ActionEvent event) {
          try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pi/view/FrontR.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
               ex.printStackTrace();
            }
    }
    
}
