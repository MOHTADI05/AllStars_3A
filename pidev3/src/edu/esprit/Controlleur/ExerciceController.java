/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.Controlleur;

import edu.esprit.dao.classes.ActiviteDAO;
import edu.esprit.dao.classes.ExerciceDAO;
import edu.esprit.dao.classes.IActiviteDao;
import edu.esprit.dao.classes.IExerciceDao;
import esprit.dao.entities.Activite;
import esprit.dao.entities.Exercice;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ExerciceController implements Initializable {
    @FXML
    private TableColumn<Activite, String> DislikeCol;

    @FXML
    private TableColumn<Activite, String> RepCol;

    @FXML
    private TableColumn<Activite, String> likeCol;

    @FXML
    private TableColumn<Activite, String> nomCol;
   @FXML
    private TableView<Activite> table_Ex;
    @FXML
    private TableColumn<Activite, String> tempsCol;
    private ObservableList<Exercice> exercice = FXCollections.observableArrayList();
    /*
    public void afficherEx(){
        IExerciceDao pdao= ExerciceDAO.getInstance();
        exercice = pdao.displayAll();
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom_Exercice"));
        tempsCol.setCellValueFactory(new PropertyValueFactory<>("temps"));
        RepCol.setCellValueFactory(new PropertyValueFactory<>("repetion"));
        likeCol.setCellValueFactory(new PropertyValueFactory<>("likes"));
        DislikeCol.setCellValueFactory(new PropertyValueFactory<>("dislike"));
        table_Ex.setItems(exercice);

    }*/
        private ObservableList<Activite> listActivite;
    private ObservableList<Activite> activite = FXCollections.observableArrayList();

    public void afficherA() {
        IActiviteDao pdao = ActiviteDAO.getInstance();
        activite = pdao.displayAll();
        nomCol.setCellValueFactory(new PropertyValueFactory<>("objectif"));
        tempsCol.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        RepCol.setCellValueFactory(new PropertyValueFactory<>("calorie"));
        likeCol.setCellValueFactory(new PropertyValueFactory<>("duree"));
        DislikeCol.setCellValueFactory(new PropertyValueFactory<>("materiel"));
        table_Ex.setItems(activite);

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherA();
    }    
    
}
