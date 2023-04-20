/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promotion.view;

import com.promotion.dao.TypeDao;
import com.promotion.dao.TypeDao;
import com.promotion.dao.TypeDao;
import com.promotion.entity.Type;
import com.promotion.entity.Type;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mohta
 */
public class TypeController implements Initializable {

    @FXML
    private TableView<Type> personsTable;
    @FXML
    private TableColumn<Type, Integer> idc;
    @FXML
    private TableColumn<Type, String> nameShow;
    @FXML
    private TableColumn<Type, String> discriptionShow;
    @FXML
    private TextField nameCol;
    @FXML
    private TextField discriptionCol;
    @FXML
    private Button save;
    @FXML
    private TextField nameCol1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handle(MouseEvent event) {
         personsTable.getSelectionModel().getSelectedItem();
       Type promo=new Type();

      


    
    
   
    }

    @FXML
    private void supprimer(MouseEvent event) {
         TypeDao pdao =new TypeDao();
    pdao.delete(personsTable.getSelectionModel().getSelectedItem().getId());
    System.out.println(personsTable.getSelectionModel().getSelectedItem().getId());
    }
   

    @FXML
    private void update(MouseEvent event) {
        String namev=nameShow.getText();
    String discriptionv=discriptionShow.getText();
    
    
    Type p;
    p=personsTable.getSelectionModel().getSelectedItem();
    //p.personsTable()
    Type promo=new Type(p.getId(),namev,discriptionv);
    TypeDao Types=new TypeDao();
    Types.update(promo);
//    listdata.getPersons(TypeDao.displayAll()); // Mettre à jour la liste des personnes
//tableView.refresh();

     
    }

    @FXML
    private void save(MouseEvent event) {
        if (validateInputs()) {
    String  Name= nameCol.getText();
    String Desc= discriptionCol.getText();
    
    
    
   // public Type(int id, Date start_date, Date end_date, float pourcentage, Categorie categorie, Produit prodtuit)
    Type p=new Type(Name,Desc);
    TypeDao regimedao=new TypeDao();
    regimedao.insert(p);
    }}
    
    
    
    
    
    private boolean validateInputs() {
    // Vérifier si le nom est vide
    if (nameCol.getText().isEmpty()) {
        showAlert("Veuillez saisir un nom");
        return false;
    }
    
    // Vérifier si la description est vide
    if (discriptionCol.getText().isEmpty()) {
        showAlert("Veuillez saisir une description");
        return false;
    }
    if (!nameCol.getText().matches("^[a-zA-Z]+$")) {
    showAlert("Le nom ne doit contenir que des lettres");
    return false;
}

    // Vérifier si les calories sont un nombre entier
    
    
    // Vérifier si le type est un nombre entier
    
    
    // Vérifier si la date de fin est un nombre entier
    
    
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

}
