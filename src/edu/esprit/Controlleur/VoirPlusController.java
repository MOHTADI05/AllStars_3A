/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.Controlleur;


import pi.dao.classes.ActiviteDAO;
import pi.dao.classes.ExerciceDAO;
import pi.dao.classes.IActiviteDao;
import pi.dao.classes.IExerciceDao;
import pi.entities.Activite;
import pi.entities.Exercice;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class VoirPlusController implements Initializable {

    @FXML
    private Button b_btn;

    @FXML
    private Label duee;

    @FXML
    private GridPane grid_ex;

    @FXML
    private Label materiel;

    @FXML
    private ScrollPane meniScroll;

    @FXML
    private Label niveau;

    @FXML
    private Label objectif;
    private int idActivite;
    private ObservableList<Exercice> menuListExercice;
    private ObservableList<Exercice> exercice = FXCollections.observableArrayList();
    private Activite activite;
    private Exercice ML;
    private ObservableList<Activite> act = FXCollections.observableArrayList();

    public void menuExercice() {
        int idActivite = data.id;
        IExerciceDao pdao = ExerciceDAO.getInstance();
        exercice = pdao.displayByActivite(idActivite);
        ML=pdao.MostLikedEx();
        System.out.println(ML);
        int row = 0;
        int column = 0;

        grid_ex.getRowConstraints().clear();
        grid_ex.getColumnConstraints().clear();
        for (int q = 0; q < exercice.size(); q++) {
            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/pi/view/ExDisplay.fxml"));
                AnchorPane pane = load.load();
                ExDisplayController AcCont = load.getController();
                AcCont.setExercice(exercice.get(q));
                if (exercice== ML){
                   AcCont.setEtoile();
                }
                if (column == 1) {
                    column = 0;
                    row += 1;
                }
                grid_ex.add(pane, column++, row);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
        objectif.setText(activite.getObjectif());
        niveau.setText(activite.getNiveau());
        duee.setText(activite.getDuree());
        materiel.setText(activite.getMateriel());
    }

    public void affactivite() {
        
        IActiviteDao pdao = ActiviteDAO.getInstance();
   activite = pdao.displayById(data.id);
//        data.id = activite.getId();
        objectif.setText(activite.getObjectif());
        niveau.setText(activite.getNiveau());
        duee.setText(activite.getDuree());
        materiel.setText(activite.getMateriel());

    }

    public void back() {

        b_btn.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pi/view/ActiviteAff.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(MainBackController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuExercice();
        affactivite();
    }

}
