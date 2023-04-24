/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author Skymil
 */
public class FXMLreclamationuserController implements Initializable {

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
    @FXML
    private TextField tfobject;
    @FXML
    private TextArea tfdescription;
    ObservableList<Reclamation> data=FXCollections.observableArrayList();
    ServiceReclamation sr=new ServiceReclamation();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refresh();
    }    

    @FXML
    private void envoyer(ActionEvent event) {
        Reclamation r=new Reclamation();
        r.setDate_reclamation(new Date(System.currentTimeMillis()));
        r.setDescription(tfdescription.getText());
        r.setEmail("aaa@gmail.com");
        r.setEtat("Non traitée");
        r.setObjet(tfobject.getText());
        if(controleDeSaisie().length()>0){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning adding claim");
            alert.setContentText(controleDeSaisie());
            alert.showAndWait();
        }
        else{
            sr.ajouter(r);
            refresh();
        }
        
    }

    @FXML
    private void modifier(ActionEvent event) {
        Reclamation r=tvreclamation.getSelectionModel().getSelectedItem();
        if(r!=null){
            
            r.setDate_reclamation(new Date(System.currentTimeMillis()));
            r.setDescription(tfdescription.getText());
            r.setEmail("aaa@gmail.com");
            r.setDate_traitement(null);
            r.setEtat("Non traitée");
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
    public void refresh(){
        data.clear();
        data=FXCollections.observableArrayList(sr.afficherReclamationUser("aaa@gmail.com"));
        tcemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tcobjet.setCellValueFactory(new PropertyValueFactory<>("objet"));
        tcetat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tcdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tcdatereclamation.setCellValueFactory(new PropertyValueFactory<>("date_reclamation"));
        tcdatetraitement.setCellValueFactory(new PropertyValueFactory<>("date_traitement"));
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
}

//    @FXML
//    private void ajouter(ActionEvent event) {
//    }
//     @FXML
//     public static String replaceBadWords(String originalString, String badWordsFilePath) {
//        String cleanedString = originalString;
//        // Read bad words from file
//        List<String> badWords = null;
//        try {
//            badWords = Files.readAllLines(Paths.get(badWordsFilePath));
//        } catch (IOException ex) {
//            Logger.getLogger(FXMLreclamationadminController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        // Replace bad words with asterisks
//        for (String badWord : badWords) {
//            if (badWord != null && !badWord.isEmpty()) {
//                StringBuilder asterisksBuilder = new StringBuilder();
//                for (int i = 0; i < badWord.length(); i++) {
//                    asterisksBuilder.append("*");
//                }
//                String asterisks = asterisksBuilder.toString();
//                cleanedString = cleanedString.replaceAll("(?i)\\b" + badWord + "\\b", asterisks);
//            }
//        }
//        return cleanedString;
//    }
//}
