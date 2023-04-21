/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promotion.view;

import com.promotion.dao.RegimeDao;
import com.promotion.entity.Categorie;
import com.promotion.entity.Produit;
import com.promotion.entity.Regime;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author User
 */
public class HomeController implements Initializable {
 private ListData listdata = new ListData();
private Parent fxml;
    private AnchorPane root;
    @FXML
    private TableView<Regime> personsTable;
    @FXML
    private TableColumn<Regime, Integer> idc;
    
    @FXML
    private TableColumn<Regime, ?> start_datec;
    @FXML
    private TableColumn<Regime, ?> end_datec;
    private TextField pourcentage;
    @FXML
    private DatePicker start_date;
    @FXML
    private TextField end_date;
    @FXML
    private Button save;
 
    @FXML
    private TextField nameCol;
    @FXML
    private TextField discriptionCol;
    @FXML
    private TextField typeCol;
    @FXML
    private TextField caloriesCol;
    @FXML
    private TableColumn<Regime, String> nameShow;
    @FXML
    private TableColumn<Regime, Integer> caloreisShow;
    @FXML
    private TableColumn<Regime, Integer> typeShow;
    @FXML
    private TableColumn<Regime, String> discriptionShow;
    @FXML
    private Button typeNext;
    @FXML
    private TextField eChercher;
    @FXML
    private Button bchercher;
    private Button Front;
    @FXML
    private Button FrontB;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      personsTable.setItems(listdata.getPersons());
        idc.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        nameShow.setCellValueFactory(new PropertyValueFactory<>("name"));
        start_datec.setCellValueFactory(new PropertyValueFactory<>("start_date"));
        end_datec.setCellValueFactory(new PropertyValueFactory<>("end_date"));
        caloreisShow.setCellValueFactory(new PropertyValueFactory<>("calories"));
        discriptionShow.setCellValueFactory(new PropertyValueFactory<>("discription"));
        
      
    }    

    


    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

 public void delete(){
    RegimeDao pdao =new RegimeDao();
    pdao.delete(personsTable.getSelectionModel().getSelectedItem().getId());
    System.out.println(personsTable.getSelectionModel().getSelectedItem().getId());
    }
    @FXML
    private void supprimer(MouseEvent event) {
         delete();
   personsTable.getItems().removeAll(personsTable.getSelectionModel().getSelectedItem());
   System.out.println(personsTable);
    }
 
 
           
            

    @FXML
    private void save(MouseEvent event) {
    if (validateInputs()) {
    String  Name= nameCol.getText();
    String Desc= discriptionCol.getText();
    java.sql.Date date_s = java.sql.Date.valueOf(start_date.getValue());
    int Cal = Integer.parseInt(caloriesCol.getText());
    int Type = Integer.parseInt(typeCol.getText());
    int date_e = Integer.parseInt(end_date.getText());
    
    
   // public Regime(int id, Date start_date, Date end_date, float pourcentage, Categorie categorie, Produit prodtuit)
    Regime p=new Regime(Name,Desc,Cal,Type,date_s,date_e);
    RegimeDao regimedao=new RegimeDao();
    regimedao.insert(p);
    }}
    
      @FXML
    private void update(MouseEvent event) {
   RegimeDao se = new RegimeDao();

        
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Erreur");
//            alert.setHeaderText(null);
//            alert.setContentText(" vérifier vos informations ");
//        
//         // Jouer le son
//            String soundFile = "C:\\Users\\mohta\\Desktop\\khalil\\testRegime\\src\\son";
//            Media sound = new Media(new File(soundFile).toURI().toString());
//            MediaPlayer mediaPlayer = new MediaPlayer(sound);
//            mediaPlayer.setOnReady(() -> mediaPlayer.play());
//            mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.dispose());  
//         alert.showAndWait();
//        } else {
        
        Regime e =  (Regime) personsTable.getSelectionModel().getSelectedItem();
            


        e.setName(nameCol.getText());
        e.setDiscription(discriptionCol.getText());
        e.setCalories(Integer.valueOf(caloriesCol.getText()));
        e.setType(Integer.valueOf(typeCol.getText()));
        e.setEnd_date(Integer.valueOf(end_date.getText()));

       
        
        se.update(e);
       se.displayAll();
         
    
     
    }

    private void handle(MouseEvent event) {
        
        personsTable.getSelectionModel().getSelectedItem();
       Regime promo=new Regime();

      promo=personsTable.getSelectionModel().getSelectedItem();
      String st_date = ""+promo.getStart_date();
      LocalDate localDate_s = LocalDate.parse(st_date);
      String en_date = ""+promo.getEnd_date();
      LocalDate localDate_e = LocalDate.parse(en_date);
      start_date.setValue(localDate_s);



    
    
      pourcentage.setText(""+promo.getName());
       //produit.setText(""+promo.getStart_date());
  
      /*LocalDate localStartDate = st_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      start_date.setValue(localStartDate);
      System.out.println(localStartDate);*/
    }
    private boolean validateInputs() {
    // Vérifier si le nom est vide
    if (nameCol.getText().isEmpty()) {
        String soundFile = "C:\\Users\\mohta\\Desktop\\khalil\\testRegime\\src\\son\\mixkit-game-show-wrong-answer-buzz-950.wav";
        Media sound = new Media(new File(soundFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

        showAlert("Veuillez saisir un nom");
        return false;
    }
    if (!nameCol.getText().matches("^[a-zA-Z]+$")) {
        showAlert("Le nom ne doit contenir que des lettres");
        return false;
    }

    // Vérifier si la description est vide
    if (discriptionCol.getText().isEmpty()) {
        showAlert("Veuillez saisir une description");
        return false;
    }

    // Vérifier si les calories sont un nombre entier
    try {
        Integer.parseInt(caloriesCol.getText());
    } catch (NumberFormatException e) {
        showAlert("Veuillez saisir un nombre entier pour les calories");
        return false;
    }

    // Vérifier si le type est un nombre entier
    try {
        Integer.parseInt(typeCol.getText());
    } catch (NumberFormatException e) {
        showAlert("Veuillez saisir un nombre entier pour le type");
        return false;
    }

    // Vérifier si la date de fin est un nombre entier
    try {
        Integer.parseInt(end_date.getText());
    } catch (NumberFormatException e) {
        showAlert("Veuillez saisir un nombre entier pour la date de fin");
        return false;
    }

    // Si toutes les validations ont réussi, renvoyer true
    return true;
}


private void showAlert(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

   
   
 @FXML
private void next(ActionEvent event)  throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Type.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) typeNext.getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }
@FXML
private void Front(ActionEvent event)  throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("front.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) FrontB.getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }


 @FXML
 private void getSelected(javafx.scene.input.MouseEvent event) {
        int index = personsTable.getSelectionModel().getSelectedIndex();
    if (index <= -1){
   
        return;
    }
    nameCol.setText(nameShow.getCellData(index).toString());
    discriptionCol.setText(discriptionShow.getCellData(index).toString());
   
    typeCol.setText(typeShow.getCellData(index).toString());
    caloriesCol.setText(caloreisShow.getCellData(index).toString());

   
    }

    @FXML
    private void chercher(ActionEvent event) {
        RegimeDao rs = new RegimeDao();
        ObservableList<Regime> list = FXCollections.observableList(rs.displayAll());
        RegimeDao e = new RegimeDao();
        //ObservableList<Categorie> list = FXCollections.observableList(sc.afficher());
        nameShow.setCellValueFactory(new PropertyValueFactory<>("name"));
        personsTable.setItems(list);

        rs.displayAll();
      personsTable.setItems(list);

        FilteredList<Regime> filteredData;
        filteredData = new FilteredList<>(list, b -> true);
        eChercher.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Evenement -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (Evenement.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                
                }  else {
                    return false;
                }

            });

        }));
        SortedList<Regime> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(personsTable.comparatorProperty());
        personsTable.setItems(sortedData);
    }

    @FXML
    private void trie(MouseEvent event) {
        RegimeDao pd=new RegimeDao();
        ObservableList<Regime> pourcentageliste = (ObservableList<Regime>) pd.trieparcalorie();
      personsTable.setItems(pourcentageliste);
    }
    
    }
    


