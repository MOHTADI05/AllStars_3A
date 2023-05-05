/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package doc.views;

import doc.utils.MyConnection;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import doc.controllers.ProduitDAO;
import doc.entities.produit;
import doc.utils.BadWordsFilter;
import doc.utils.MailTools;

/**
 * FXML Controller class
 *
 * @author chaker
 */
public class ProdBackController implements Initializable {

    @FXML
    private AnchorPane mainForm;
    @FXML
    private Button AcBtn;
    @FXML
    private Button back;
    @FXML
    private TableView<produit> tableViewProd;
    @FXML
    private TableColumn<produit, String> nom;
    @FXML
    private TableColumn<produit, Integer> prix;
    @FXML
    private TableColumn<produit, String> detail;
    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_prix;
    @FXML
    private TextField tf_detail;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnSupp;
    @FXML
    private Button btnMod;

    /**
     * Initializes the controller class.
     */
    ProduitDAO su = new ProduitDAO();
    @FXML
    private ComboBox<Integer> CpmboboxCpurs;
    @FXML
    private TextField setPromptText;
    @FXML
    private ChoiceBox<String> ChoiceBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadNames();
        afficheTableView();
        filteredSearch();
        ChoiceBox.getItems().addAll("nom", "detail");
        ChoiceBox.setValue("nomination");
        setPromptText.setPromptText("Rechercher ");

    }

    static int id;

    @FXML
    private void SelectedActivite(MouseEvent event) {
        
        tf_nom.setText("" + tableViewProd.getSelectionModel().getSelectedItem().getNom());
        tf_detail.setText("" + "" + tableViewProd.getSelectionModel().getSelectedItem().getDetail());
        tf_prix.setText("" + tableViewProd.getSelectionModel().getSelectedItem().getPrix());
        produit selectedProd = tableViewProd.getSelectionModel().getSelectedItem();
        id = selectedProd.getCategorie_id();
        id_produit = selectedProd.getId();
        CpmboboxCpurs.setDisable(true);

    }

    @FXML
    private void AddProduit(ActionEvent event) {
        produit p = new produit();
        String userInput = tf_detail.getText();
        if (BadWordsFilter.containsBadWords(userInput)) {
            // Display an alert to the user that their input contains bad words
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Your input contains bad words. Please try again.");
            alert.showAndWait();
        } else if (VerifUserChamps()) {

            p.setNom(tf_nom.getText());
            p.setPrix(Integer.parseInt(tf_prix.getText()));
            p.setDetail(tf_detail.getText());
            p.setCategorie_id(CpmboboxCpurs.getSelectionModel().getSelectedItem());
            su.ajoutProduit(p);
            try {
                MailTools.sendMail("moez.benzayed@esprit.tn", "produit a ete ajouter avec sucess de compte", "");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            Alert resAlert = new Alert(Alert.AlertType.INFORMATION);
            resAlert.setHeaderText(null);
            resAlert.setContentText("produit a ete cree avec sucess");
            resAlert.showAndWait();

            clear();
            afficheTableView();

        }

    }

    @FXML
    private void DeleteProduit(ActionEvent event) {

        su.supprimerProduit(id_produit);
        afficheTableView();

    }
    static int id_produit;

    @FXML
    private void UpdateProduit(ActionEvent event) {
        if (VerifUserChamps()) {
            produit p = new produit();
            p.setNom(tf_nom.getText());
            p.setPrix(Integer.parseInt(tf_prix.getText()));
            p.setDetail(tf_detail.getText());
            p.setCategorie_id(id);
            su.modifierProduit(p, id_produit);

            afficheTableView();
            clear();
            Alert resAlert = new Alert(Alert.AlertType.INFORMATION);
            resAlert.setHeaderText(null);
            resAlert.setContentText("produit a ete mise a jour sucess");
            resAlert.showAndWait();
        }

    }

    public Boolean VerifUserChamps() {

        int verif = 0;

        String style = " -fx-border-color: red;";

        if (tf_nom.getText().trim().equals("")) {
            tf_nom.setStyle(style);
            verif = 1;
        }
        if (tf_prix.getText().trim().equals("")) {
            tf_prix.setStyle(style);
            verif = 1;
        }
        if (tf_detail.getText().trim().equals("")) {
            tf_detail.setStyle(style);
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
        List prodList = su.afficherProduits();
        ObservableList list = FXCollections.observableArrayList(prodList);
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        detail.setCellValueFactory(new PropertyValueFactory<>("detail"));

        tableViewProd.setItems(list);

    }

    public void clear() {
        tf_detail.setText("");
        tf_nom.setText("");
        tf_prix.setText("");

    }

    public void loadNames() {

        try {
            Connection cnx;
            cnx = MyConnection.getInstance().getConnexion();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT id from categorie ");
            ObservableList data = FXCollections.observableArrayList();
            while (rs.next()) {
                data.add(rs.getInt("id"));
            }
            CpmboboxCpurs.setItems(data);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void filteredSearch() {

        List<produit> List_event = su.afficherProduits();
        ObservableList<produit> list = FXCollections.observableArrayList(List_event);
        FilteredList<produit> flpProd = new FilteredList(list, p -> true);
        setPromptText.textProperty().addListener((obs, oldValue, newValue) -> {
            switch (ChoiceBox.getValue()) {
                case "nom":
                    flpProd.setPredicate(p -> p.getNom().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
                case "detail":
                    flpProd.setPredicate(p -> p.getDetail().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
            }
        });
        tableViewProd.setItems(flpProd);
        ChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
                -> {
            if (newVal != null) {
                setPromptText.setText("");
            }
        });
    }

    @FXML
    private void backHome(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/doc/views/CategorieBack.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void exportToCsv(ActionEvent event) {
                    FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save CSV File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File outputFile = fileChooser.showSaveDialog(tableViewProd.getScene().getWindow());
        
        if (outputFile != null) {
            try {
                // Open a FileWriter for the output file
                FileWriter writer = new FileWriter(outputFile);
                
                // Write the header row
                writer.write("Nom ,Prix\n");
                
                // Write the data rows
                for (produit item : tableViewProd.getItems()) {
                    String row = String.format("%s,%s\n", item.getNom(), item.getPrix());
                    writer.write(row);
                }
                
                // Close the FileWriter
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}
