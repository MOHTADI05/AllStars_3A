/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.Controlleur;

import pi.dao.classes.ActiviteDAO;
import pi.dao.classes.ExerciceDAO;
import pi.dao.classes.IActiviteDao;
import pi.utils.MyConnection;
import pi.entities.Activite;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
public class MainBackController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button AcBtn;

    @FXML
    private TableColumn<Activite, String> CalCol;

    @FXML
    private TableColumn<Activite, String> DureeCol;

    @FXML
    private TableColumn<Activite, String> MatCol;

    @FXML
    private TableColumn<Activite, String> NivCol;

    @FXML
    private TableColumn<Activite, String> ObjCol;

    @FXML
    private TableView<Activite> TableA;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnMod;

    @FXML
    private Button btnSupp;

    @FXML
    private ImageView imageV1;

    @FXML
    private ImageView imageV2;

    @FXML
    private Button import_btn;

    @FXML
    private AnchorPane mainForm;
    @FXML
    private TextField matetxt;

    @FXML
    private TextField obj_txt;
    @FXML
    private TextField caltxt;

    @FXML
    private TextField dureetxt;
    @FXML
    private TextField Nivtxt;
    @FXML
    private Button btn_Vex;
    @FXML
    private Button back;

    @FXML
    private TextField rech;
    @FXML
    private Pagination pagination;

    private Image image;

    private Alert alert;
    private Statement st;
    private ResultSet rs;

    private ObservableList<Activite> listActivite;
    private ObservableList<Activite> activite = FXCollections.observableArrayList();

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
    }

    public boolean isValidCalorie(String input) {
        if (input == null || input.isEmpty()) {
            showErrorMessage("calorie ne peut pas être vide.");
            return false;
        }
        //String x=String.valueOf(input);

        if (!input.matches("\\d+")) {
            showErrorMessage("Les calorie doit contenir uniquement des chiffres.");
            return false;
        }
        int calorie = Integer.parseInt(input);
        if (calorie <= 0) {
            showErrorMessage("L'ID doit être un entier positif.");
            return false;
        }

        return true;
    }

    public boolean isValidObjectif(String input) {
        if (input.isEmpty()) {
            showErrorMessage("l'objectif ne doit pas etre vide.");
            return false;

        } else if (!input.matches("[a-zA-Z]+")) {
            showErrorMessage("l'objectif  ne doit contenir que des lettre.");
            return false;

        }
        return true;
    }

    public boolean isValidDuree(String input) {
        if (input.isEmpty()) {
            showErrorMessage("duree ne doit pas être vide.");
            return false;

        } else if (!input.matches("^([0-1][0-9]|[2][0-3]):([0-5][0-9])$")) {
            showErrorMessage("duree doit être sous la forme 'HH:MM'.");
            return false;
        }

        return true;
    }

    public boolean isValidNiveau(String input) {
        if (input.isEmpty()) {
            showErrorMessage("Niveau ne doit pas être vide.");
            return false;

        } else if (!input.matches("novice|medium|difficile")) {
            showErrorMessage("Niveau ne peut être que novice, medium ou difficile.");
            return false;
        }

        return true;
    }

    public void afficherA() {
        IActiviteDao pdao = ActiviteDAO.getInstance();
        activite = pdao.displayAll();
        ObjCol.setCellValueFactory(new PropertyValueFactory<>("objectif"));
        NivCol.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        CalCol.setCellValueFactory(new PropertyValueFactory<>("calorie"));
        DureeCol.setCellValueFactory(new PropertyValueFactory<>("duree"));
        MatCol.setCellValueFactory(new PropertyValueFactory<>("materiel"));
        //TableA.setItems(activite);
        int paginat = 1;
        if (activite.size() % nbPage() == 0) {
            paginat = activite.size() / nbPage();
        } else if (activite.size() > nbPage()) {
            paginat = activite.size() / nbPage() + 1;
        }
pagination.setPageCount(paginat);
pagination.setCurrentPageIndex(0);
pagination.setPageFactory(this::createPaginationRech);
    }
    
public void afficherRec() {
    IActiviteDao pdao = ActiviteDAO.getInstance();
    activite = pdao.displayAll();
    ObjCol.setCellValueFactory(new PropertyValueFactory<>("objectif"));
    NivCol.setCellValueFactory(new PropertyValueFactory<>("niveau"));
    CalCol.setCellValueFactory(new PropertyValueFactory<>("calorie"));
    DureeCol.setCellValueFactory(new PropertyValueFactory<>("duree"));
    MatCol.setCellValueFactory(new PropertyValueFactory<>("materiel"));

    // Création d'une FilteredList pour filtrer les données en fonction de la recherche
    FilteredList<Activite> filteredData = new FilteredList<>(activite, b -> true);
    
    // Ajout d'un écouteur sur le champ de recherche pour mettre à jour la liste filtrée
    rech.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(act -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String searchKeyWord = newValue.toLowerCase();
            if (act.getObjectif().toLowerCase().contains(searchKeyWord)) {
                return true;
            } else if (act.getNiveau().toLowerCase().contains(searchKeyWord)) {
                return true;
            }
            return false;
        });
        pagination.setPageCount((int) Math.ceil(filteredData.size() / (double) nbPage()));
        pagination.setCurrentPageIndex(0);
        createPagination(0);
    });

    // Création d'une SortedList pour trier les données en fonction de l'ordre de tri de la table
    SortedList<Activite> sortedData = new SortedList<>(filteredData);
    sortedData.comparatorProperty().bind(TableA.comparatorProperty());
    
    // Affichage des données dans la table
    TableA.setItems(sortedData);

    // Configuration de la pagination
    pagination.setPageCount((int) Math.ceil(filteredData.size() / (double) nbPage()));
    pagination.setPageFactory(this::createPagination);
}

    public int nbPage() {
        return 5;
    }

    private Node createPagination(int pageIndex) {
        int fromIndex = pageIndex * nbPage();
        int toIndex = Math.min(fromIndex + nbPage(), activite.size());
        TableA.setItems(FXCollections.observableArrayList(activite.subList(fromIndex, toIndex)));
        return new BorderPane(TableA);
    }

    public void recherche() {
        Connection cs;

         cs =  MyConnection.getInstance();
        try {
            st = cs.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ActiviteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String req = "SELECT objectif, niveau, calorie, duree, materiel FROM activite";
        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                String ActiviteObj = rs.getString("objectif");
                int ActiviteCal = rs.getInt("calorie");
                String ActiviteNiv = rs.getString("niveau");
                String ActiviteDu = rs.getString("duree");
                String ActiviteMat = rs.getString("materiel");

                activite.add(new Activite(ActiviteObj, ActiviteCal, ActiviteNiv, ActiviteDu, ActiviteMat));
            }
            ObjCol.setCellValueFactory(new PropertyValueFactory<>("objectif"));
            NivCol.setCellValueFactory(new PropertyValueFactory<>("niveau"));
            CalCol.setCellValueFactory(new PropertyValueFactory<>("calorie"));
            DureeCol.setCellValueFactory(new PropertyValueFactory<>("duree"));
            MatCol.setCellValueFactory(new PropertyValueFactory<>("materiel"));
            TableA.setItems(activite);
            
            FilteredList<Activite> filterData = new FilteredList<>(activite, b -> true);
            rech.textProperty().addListener((observable, oldValue, newValue) -> {
                filterData.setPredicate(act -> {
                    if (newValue.isEmpty() || newValue == null) {
                        return true;

                    }
                    String searchKeyWord = newValue.toLowerCase();
                    if (act.getObjectif().toLowerCase().indexOf(searchKeyWord) > -1) {
                        return true;
                    } else if (act.getNiveau().toLowerCase().indexOf(searchKeyWord) > -1) {
                        return true;
                    }
                    return false;
                });

            });
            SortedList<Activite> sortedData = new SortedList<>(filterData);
            sortedData.comparatorProperty().bind(TableA.comparatorProperty());
            TableA.setItems(sortedData);

        } catch (SQLException ex) {
            Logger.getLogger(MainBackController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private List<Activite> getActivitesFiltrees(String recherche, int pageIndex, int nbPage) {
    // Filtre les activités en fonction de la recherche
    FilteredList<Activite> filteredData = new FilteredList<>(activite, a -> true);
    if (recherche != null && !recherche.isEmpty()) {
        String searchKeyWord = recherche.toLowerCase();
        filteredData.setPredicate(a -> a.getObjectif().toLowerCase().contains(searchKeyWord) || 
                a.getNiveau().toLowerCase().contains(searchKeyWord));
    }
    // Trie les activités en fonction de la colonne sélectionnée
    SortedList<Activite> sortedData = new SortedList<>(filteredData);
    sortedData.comparatorProperty().bind(TableA.comparatorProperty());
    // Renvoie la liste d'activités correspondant à la page courante
    int fromIndex = pageIndex * nbPage;
    int toIndex = Math.min(fromIndex + nbPage, sortedData.size());
    return sortedData.subList(fromIndex, toIndex);
}
private Node createPaginationRech(int pageIndex) {
    String recherche = rech.getText();
    List<Activite> activites = getActivitesFiltrees(recherche, pageIndex, nbPage());
    TableA.setItems(FXCollections.observableArrayList(activites));
    return new BorderPane(TableA);
}



    @FXML
    public void AddActivite() {
        if (isValidObjectif(obj_txt.getText())
                && isValidCalorie(caltxt.getText())
                && isValidDuree(dureetxt.getText())
                && isValidNiveau(Nivtxt.getText())
                && isValidObjectif(matetxt.getText())
                && data.path != null) {

            String calorieString = caltxt.getText();
// Convertir la chaîne de caractères en entier
            int calorieInt = Integer.parseInt(calorieString);

// Assigner la valeur entière à la propriété de l'objet
            btnAdd.setOnAction(event -> {
                String path = data.path;
                path = path.replace("\\", "\\\\");
                Activite p = new Activite(obj_txt.getText(), Nivtxt.getText(), calorieInt, dureetxt.getText(), matetxt.getText(), path);
                IActiviteDao pdao = ActiviteDAO.getInstance();
                pdao.InsertActivite(p);

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Personne insérée avec succés!");
                alert.show();
                afficherA();
                clearBtn();
                String title = "activite ajouté";
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

    @FXML
    public void importBtn() {
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new ExtensionFilter("Open Image File ", "*png", "*jpg"));
        File file = openFile.showOpenDialog(mainForm.getScene().getWindow());
        if (file != null) {
            data.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 138, 138, false, true);
            imageV1.setImage(image);
        }
    }

    public void clearBtn() {
        obj_txt.setText("");
        caltxt.setText("");
        Nivtxt.setText("");
        dureetxt.setText("");
        matetxt.setText("");
        data.path = "";
        data.id = 0;
        imageV1.setImage(null);

    }

    @FXML
    public void SelectedActivite() {
        Activite a = TableA.getSelectionModel().getSelectedItem();
        int num = TableA.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }
        obj_txt.setText(a.getObjectif());
        caltxt.setText(String.valueOf(a.getCalorie()));
        Nivtxt.setText(a.getNiveau());
        dureetxt.setText(a.getDuree());
        matetxt.setText(a.getMateriel());
        String path = "File:" + a.getImageFile();
        image = new Image(path, 170, 171, false, true);
        //data.path = "File: " + a.getImageFile();
        data.id = a.getId();
        // image = new Image(data.path, 117, 102, false, true);
        imageV2.setImage(image);

    }

    @FXML
    public void UpdateBtn() {
        if (isValidObjectif(obj_txt.getText())
                && isValidCalorie(caltxt.getText())
                && isValidDuree(dureetxt.getText())
                && isValidNiveau(Nivtxt.getText())
                && isValidObjectif(matetxt.getText())
                && data.path != null || data.id != 0) {

// Assigner la valeur entière à la propriété de l'objet
            btnMod.setOnAction(event -> {
                String calorieString = caltxt.getText();
// Convertir la chaîne de caractères en entier
                int calorieInt = Integer.parseInt(calorieString);
                String path = data.path;
                path = path.replace("\\", "\\\\");
                Activite p = new Activite(obj_txt.getText(), Nivtxt.getText(), calorieInt, dureetxt.getText(), matetxt.getText(), path);
                IActiviteDao pdao = ActiviteDAO.getInstance();
                pdao.updateActivite(p);

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("activite modifié avec succés!");
                alert.show();
                afficherA();
                clearBtn();
                String title = "activite modifié";
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

    @FXML
    public void DeleteBtn() {
      if (
                data.path != null && data.id != 0) {

            btnSupp.setOnAction(event -> {
                String calorieString = caltxt.getText();
// Convertir la chaîne de caractères en entier
                int calorieInt = Integer.parseInt(calorieString);
                String path = data.path;
                path = path.replace("\\", "\\\\");
                Activite p = new Activite(obj_txt.getText(), Nivtxt.getText(), calorieInt, dureetxt.getText(), matetxt.getText(), path);
                IActiviteDao pdao = ActiviteDAO.getInstance();
                pdao.deleteActivite(p);

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("activite supprimé avec succés!");
                alert.show();
                afficherA();
                clearBtn();
                String title = "activite supprimé";
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

    @FXML
    public void VoirExercice() {
        if (data.id == 0) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("veuillez choisir une activité");
            alert.showAndWait();
        } else {
            btn_Vex.setOnAction(event -> {

                try {
                    Parent page1 = FXMLLoader.load(getClass().getResource("/pi/view/exerciceBack.fxml"));
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherA();
       

        back.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pi/view/list.fxml"));
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
