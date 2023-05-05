/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pi.dao.classes.CategorieDAO;
import pi.entities.Categorie;

/**
 * FXML Controller class
 *
 * @author Moez
 */
public class CategorieBackController implements Initializable {

    @FXML
    private AnchorPane mainForm;
    @FXML
    private Button AcBtn;
    @FXML
    private Button back;
    @FXML
    private TableView<Categorie> TableCat;
    @FXML
    private TableColumn<Categorie, Integer> idCol;
    @FXML
    private TableColumn<Categorie, String> nomCol;
    @FXML
    private TableColumn<Categorie, String> infoCol;
    @FXML
    private TextField Rech;
    @FXML
    private TextField nomTxt;
    @FXML
    private TextField infoTxt;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnMod;

    CategorieDAO ser = new CategorieDAO();

    public Boolean VerifCatChamps() {
        int verif = 0;
        String style = " -fx-border-color: red;";
        if (nomTxt.getText().trim().equals("")) {
            nomTxt.setStyle(style);
            verif = 1;
        }
        if (infoTxt.getText().trim().equals("")) {
            infoTxt.setStyle(style);
            verif = 1;
        }
        if (verif == 0) {
            return true;
        }
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Alert");
        al.setContentText("Verifier les champs");
        al.setHeaderText(null);
        al.show();
        return false;
    }

    private void afficheTableView() {
        List cats = ser.afficherCategorie();
        ObservableList list = FXCollections.observableArrayList(cats);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        infoCol.setCellValueFactory(new PropertyValueFactory<>("information"));
        TableCat.setItems(list);
    }

    public void clear() {
        nomTxt.setText("");
        infoTxt.setText("");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficheTableView();
    }

    @FXML
    private void SelectedActivite(MouseEvent event) {
        nomTxt.setText("" + TableCat.getSelectionModel().getSelectedItem().getNom());
        infoTxt.setText("" + "" + TableCat.getSelectionModel().getSelectedItem().getInformation());
    }

    @FXML
    private void addCat(ActionEvent event) {
        Categorie c = new Categorie();
        if (VerifCatChamps()) {
            c.setNom(nomTxt.getText());
            c.setInformation(infoTxt.getText());
            ser.ajoutCategorie(c);
            Alert resAlert = new Alert(Alert.AlertType.INFORMATION);
            resAlert.setHeaderText(null);
            resAlert.setContentText("Categorie a ete cree avec sucess");
            resAlert.showAndWait();
            clear();
            afficheTableView();
        }
    }

    @FXML
    private void updateCat(ActionEvent event) {
        if (VerifCatChamps()) {
            Categorie c=new Categorie();
            c.setNom(nomTxt.getText());
            c.setInformation(infoTxt.getText());
            ser.modifierCategorie(c,TableCat.getSelectionModel().getSelectedItem().getId());
            afficheTableView();
            Alert resAlert = new Alert(Alert.AlertType.INFORMATION);
            resAlert.setHeaderText(null);
            resAlert.setContentText("Categorie a ete modifiée avec sucess");
            resAlert.showAndWait();
            clear();
        }
    }

    @FXML
    private void backHome(ActionEvent event) {
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pi/view/list.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
               ex.printStackTrace();
            }
    }

}
