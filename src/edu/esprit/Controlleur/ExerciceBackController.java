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
import pi.utils.MyConnection;
import pi.entities.Activite;
import pi.entities.Exercice;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ExerciceBackController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private TableColumn<Exercice, String> RepCol;

    @FXML
    private TableView<Exercice> Table;

    @FXML
    private Button btnImport;

    @FXML
    private Button btn_Aff;

    @FXML
    private Button btnadd;

    @FXML
    private Button btnmod;

    @FXML
    private Button btnsupp;

    @FXML
    private Label actID;

    @FXML
    private ImageView imageV;

    @FXML
    private TableColumn<Exercice, String> likeCol;

    @FXML
    private AnchorPane mainForm;
    @FXML
    private Button back;

    @FXML
    private TableColumn<Exercice, String> nomCol;

    @FXML
    private TextField nomtxt;

    @FXML
    private TextField reptxt;

    @FXML
    private TableColumn<Exercice, String> silikeCol;

    @FXML
    private TableColumn<Exercice, String> tempsCol;

    @FXML
    private TextField tempstxt;
    @FXML
    private TextField Rech;

    private Image image;

    private Alert alert;
    private Statement st;
    private ResultSet rs;

    private ObservableList<Exercice> listActivite;
    private ObservableList<Exercice> exercice = FXCollections.observableArrayList();
    private ObservableList activite = FXCollections.observableArrayList();

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
    }

    public boolean isValidNom(String input) {
        if (input.isEmpty()) {
            showErrorMessage("le nom  ne doit pas etre vide.");
            return false;

        } else if (!input.matches("[a-zA-Z]+")) {
            showErrorMessage("le nom  ne doit contenir que des lettre.");
            return false;

        }
        return true;
    }

    public boolean isValidRepet(String input) {
        if (input.isEmpty()) {
            showErrorMessage("repetition ne doit pas être vide.");
            return false;

        } else if (!input.matches("\\d+x\\d+")) {
            showErrorMessage("repetition doit être sous la forme 'nombre x nombre'.");
            return false;
        }

        return true;
    }

    public boolean isValidDuree(String input) {
        if (input.isEmpty()) {
            showErrorMessage("temps ne doit pas être vide.");
            return false;

        } else if (!input.matches("\\d+s")) {
            showErrorMessage("temps doit être sous la forme 'SSs'.");
            return false;
        }

        return true;
    }

    public void afficher() {

        int idActivite = data.id;
        IExerciceDao exerciceDao = ExerciceDAO.getInstance();
        ObservableList<Exercice> exercice = exerciceDao.displayByActivite(idActivite);

        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom_Exercice"));

        RepCol.setCellValueFactory(new PropertyValueFactory<>("Repet"));
        likeCol.setCellValueFactory(new PropertyValueFactory<>("likes"));
        silikeCol.setCellValueFactory(new PropertyValueFactory<>("dislikes"));
        tempsCol.setCellValueFactory(new PropertyValueFactory<>("temps"));

        Table.setItems(exercice);

    }

    /* public void comboBox(){
         IActiviteDao pdao = ActiviteDAO.getInstance();
        activite = pdao.displayComboBox();
        idAtxt.setItems(activite);
     }
     
    public void BindCombo(){
        Activite query = new Activite();
        IActiviteDao exerciceDao = ActiviteDAO.getInstance();
    HashMap<String, Integer> data = exerciceDao.populateCombo();
    
    ObservableList<String> activite = FXCollections.observableArrayList(data.keySet());
    idAtxt.setItems(activite);
    
    // Set a listener to handle when an item is selected
    
    }*/
    public void afficherA() {
        btn_Aff.setOnAction(event -> {
            IExerciceDao pdao = ExerciceDAO.getInstance();
            exercice = pdao.displayAll();
            //activiteidCol.setCellValueFactory(new PropertyValueFactory<>("activite_id"));
            RepCol.setCellValueFactory(new PropertyValueFactory<>("repetion"));
            likeCol.setCellValueFactory(new PropertyValueFactory<>("like"));
            nomCol.setCellValueFactory(new PropertyValueFactory<>("nom_exercice"));
            silikeCol.setCellValueFactory(new PropertyValueFactory<>("dislike"));
            tempsCol.setCellValueFactory(new PropertyValueFactory<>("temps"));

            Table.setItems(exercice);
        });

    }

    public void listeIdActivite() {
        List<Activite> listesDepot = new ArrayList<Activite>();
        IActiviteDao depotDao = ActiviteDAO.getInstance();
        listesDepot = depotDao.DisplayAllActivite();
        for (Activite d : listesDepot) {
            listesDepot.add(d);
        }
        ObservableList listData = FXCollections.observableArrayList(listesDepot);

    }

    public void recherche() {
                    Connection cs;

         cs =  MyConnection.getInstance();
        try {
            st = cs.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ActiviteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String req = "SELECT nom_exercice, repetion, temps, likes, dislike FROM exercice";
        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                String nom_exercice = rs.getString("nom_exercice");
                String Exrepetion = rs.getString("repetion");
                String Extemps = rs.getString("temps");
                int Exlike = rs.getInt("likes");
                int Exdislike = rs.getInt("dislike");

                exercice.add(new Exercice(nom_exercice, Extemps, Exrepetion, Exlike, Exdislike));
            }
           nomCol.setCellValueFactory(new PropertyValueFactory<>("nom_Exercice"));

        RepCol.setCellValueFactory(new PropertyValueFactory<>("Repet"));
        likeCol.setCellValueFactory(new PropertyValueFactory<>("likes"));
        silikeCol.setCellValueFactory(new PropertyValueFactory<>("dislikes"));
        tempsCol.setCellValueFactory(new PropertyValueFactory<>("temps"));
            Table.setItems(exercice);

            FilteredList<Exercice> filterData = new FilteredList<>(exercice, b -> true);
            Rech.textProperty().addListener((observable, oldValue, newValue) -> {
                filterData.setPredicate(act -> {
                    if (newValue.isEmpty() || newValue == null) {
                        return true;

                    }
                    String searchKeyWord = newValue.toLowerCase();
                    if (act.getNom_Exercice().toLowerCase().indexOf(searchKeyWord) > -1) {
                        return true;
                    }
                    return false;
                });

            });
            SortedList<Exercice> sortedData = new SortedList<>(filterData);
            sortedData.comparatorProperty().bind(Table.comparatorProperty());
            Table.setItems(sortedData);

        } catch (SQLException ex) {
            Logger.getLogger(MainBackController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void AddExercice() {
        if (isValidNom(nomtxt.getText())
                && isValidRepet(reptxt.getText())
                && isValidDuree(tempstxt.getText())
                && dataE.path != null) {

// Assigner la valeur entière à la propriété de l'objet
            btnadd.setOnAction(event -> {
                String path = dataE.path;
                path = path.replace("\\", "\\\\");
                //String idAstring =(String) idAtxt.getSelectionModel().getSelectedItem();

// Convertir la chaîne de caractères en entier
                int IdAInt = data.id;

// Créer un objet Activite avec l'ID récupéré
                Activite activite = new Activite();
                activite.setId(IdAInt);

                Exercice p = new Exercice(activite, nomtxt.getText(), tempstxt.getText(), reptxt.getText(), path);
                IExerciceDao pdao = ExerciceDAO.getInstance();
                pdao.InsertExercice(p);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Personne insérée avec succés!");
                alert.show();
                afficher();
                clearBtn();
                String title = "exercice ajouté";
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;

                tray.setAnimationType(type);
                tray.setTitle(title);
                tray.setMessage(title);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));

            });
        }

    }

    public void importBtn() {
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File ", "*png", "*jpg"));
        File file = openFile.showOpenDialog(mainForm.getScene().getWindow());
        if (file != null) {
            dataE.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 117, 102, false, true);
            imageV.setImage(image);
        }
    }

    public void clearBtn() {
        nomtxt.setText("");
        tempstxt.setText("");
        reptxt.setText("");

        dataE.path = "";
        dataE.id = 0;
        imageV.setImage(null);

    }

    public void SelectedExercice() {
        Exercice a = Table.getSelectionModel().getSelectedItem();
        int num = Table.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }
        nomtxt.setText(a.getNom_Exercice());

        tempstxt.setText(a.getTemps());
        reptxt.setText(a.getRepet());
        actID.setText(Integer.toString(data.id));

        dataE.path = "File: " + a.getImage_Exer();
        dataE.id = a.getId();
        image = new Image(data.path, 117, 102, false, true);
        imageV.setImage(image);

    }

    public void UpdateBtn() {
        if (isValidNom(nomtxt.getText())
                && isValidRepet(reptxt.getText())
                && isValidDuree(tempstxt.getText())
                && dataE.path != null && dataE.id != 0) {

// Assigner la valeur entière à la propriété de l'objet
            btnmod.setOnAction(event -> {
                String path = dataE.path;
                path = path.replace("\\", "\\\\");
                // String idAstring =(String) idAtxt.getSelectionModel().getSelectedItem();

// Convertir la chaîne de caractères en entier
                int IdAInt = data.id;

// Créer un objet Activite avec l'ID récupéré
                Activite activite = new Activite();
                activite.setId(IdAInt);

                Exercice p = new Exercice(activite, nomtxt.getText(), reptxt.getText(), tempstxt.getText(), path);

                IExerciceDao pdao = ExerciceDAO.getInstance();
                pdao.updateExercice(p);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("activite modifié avec succés!");
                alert.show();
                afficher();
                clearBtn();
                String title = "exercice mofifié";
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;

                tray.setAnimationType(type);
                tray.setTitle(title);
                tray.setMessage(title);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));

            });
        }
    }

    public void DeleteBtn() {
        if ( dataE.path != null || dataE.id != 0) {

            btnsupp.setOnAction(event -> {
                String path = dataE.path;
                path = path.replace("\\", "\\\\");
                //String idAstring =(String) idAtxt.getSelectionModel().getSelectedItem();

// Convertir la chaîne de caractères en entier
                int IdAInt = data.id;

// Créer un objet Activite avec l'ID récupéré
                Activite activite = new Activite();
                activite.setId(IdAInt);

                Exercice p = new Exercice(activite, nomtxt.getText(), reptxt.getText(), tempstxt.getText(), path);
                IExerciceDao pdao = ExerciceDAO.getInstance();
                pdao.deleteExercice(p);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("activite supprimé avec succés!");
                alert.show();
                afficher();
                clearBtn();
                String title = "exercice supprimé";
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;

                tray.setAnimationType(type);
                tray.setTitle(title);
                tray.setMessage(title);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
            });
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // afficher();
        recherche();
        back.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pi/view/mainBack.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(MainBackController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

}
