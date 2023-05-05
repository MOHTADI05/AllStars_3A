/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.GUIcmd;

import entiteCmd.Commande;
import entiteCmd.Livraison;
import entiteCmd.PDF;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import serviceCmd.ServiceCommande;
import serviceCmd.ServiceLivraison;

/**
 * FXML Controller class
 *
 * @author user
 */
public class afficherliv_1Controller implements Initializable {

    @FXML
    private TableView<Livraison> oaffichliv;
    @FXML
    private TableColumn<Livraison, String> id_commande;
    @FXML
    private TableColumn<Livraison, String> id_livraison;
    @FXML
    private TableColumn<Livraison, String> adresse_livraison;
    @FXML
    private TableColumn<Livraison, String> transporteur_livraison;
    @FXML
    private TableColumn<Livraison, LocalDate> date_liv_aff;
    @FXML
    private TextField rechliv;
    private Button retourliv;
    @FXML
    private Button PDF;
    @FXML
    private Button modifliv;
    @FXML
    private Button suppliv;
    
        ObservableList<Livraison> eventList2;
    @FXML
    private Button Act;
    @FXML
    private Button reclamtion;
    @FXML
    private Button deconnecter12;
    @FXML
    private Button Profil;
    @FXML
    private Button commande;
    @FXML
    private Button ajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         loadData();
         handleTableSelection();
        // TODO
    }    
    
        public void loadData() {
    try {
        ObservableList<Livraison> fle = new ServiceLivraison().afficherLivraison2();
        System.err.println(fle.size());
        date_liv_aff.setCellValueFactory(new PropertyValueFactory<>("date_liv"));
        transporteur_livraison.setCellValueFactory(new PropertyValueFactory<>("transporteur"));
        adresse_livraison.setCellValueFactory(new PropertyValueFactory<>("address_liv"));
        id_livraison.setCellValueFactory(new PropertyValueFactory<>("id_liv"));
       // id_commande.setCellValueFactory(new PropertyValueFactory<>("id_cmd"));

 id_commande.setCellValueFactory(cellData -> {
    Livraison entretient = cellData.getValue();
    if (entretient != null && entretient.getCmd()!= null) {
         int nomOffre = entretient.getCmd().getId_cmd();
        return new SimpleStringProperty(Integer.toString(nomOffre));
    } else {
        return null;
    }
});

     oaffichliv.setItems(fle);
    } catch (NullPointerException ex) {
        System.out.println("Erreur de pointeur nul: " + ex.getMessage());
    } catch (Exception ex) {
        System.out.println("Une erreur s'est produite: " + ex.getMessage());
    }
}
        
private void handleTableSelection() {
    Livraison offreSelectionnee = oaffichliv.getSelectionModel().getSelectedItem();
    if (offreSelectionnee != null) {
        // Récupérer les données de la ligne sélectionnée
        LocalDate date_ent = offreSelectionnee.getDate_liv();
        String liew = offreSelectionnee.getAddress_liv();
        String nom_intervieweur = offreSelectionnee.getTransporteur();
        int id_liv = offreSelectionnee.getId_liv();
        int id_cmd = offreSelectionnee.getCmd().getId_cmd();

        // Faire quelque chose avec ces données (par exemple les afficher dans une boîte de dialogue)
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information sur l'offre sélectionnée");
        alert.setHeaderText(null);
        alert.setContentText( "\ndate : " + date_ent + "\nNom : " + nom_intervieweur + "\nLieu : " + liew + "\nnom interviexr : " + nom_intervieweur +
                "\nType : " + id_liv + "\nOffre Associées: " + id_cmd );
        alert.showAndWait();
    }
    oaffichliv.setOnMouseClicked(event -> {
    if (event.getClickCount() == 1) { // une seule pression de souris
        handleTableSelection();
    }
});

oaffichliv.setOnKeyPressed(event -> {
    if (event.getCode().equals(KeyCode.ENTER)) { // appui sur la touche Entrée
        handleTableSelection();
    }
});
}


    private void to_rubliv(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/view/profil.fxml"));
                Parent root = loader.load();
               RubriqueCommandeController aa = loader.getController();
                retourliv.getScene().setRoot(root);
    }

    @FXML
    private void PDF(ActionEvent event) {
         PDF pd=new PDF();
        try{
        pd.GeneratePdf2("Liste des livraison");
            System.out.println("impression done");
        } catch (Exception ex) {
            Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void SupprimerLivraison(ActionEvent event) throws SQLException {
                eventList2 = oaffichliv.getSelectionModel().getSelectedItems();
        int id = eventList2.get(0).getId_liv();
        Livraison e = new Livraison(id);
        ServiceLivraison ps = new ServiceLivraison();
        ps.supprimerLivraison(e);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("L'entretient a été supprimée avec succès !");
        alert.showAndWait();
        loadData();

    }

    @FXML
    private void modifierliv(ActionEvent event) throws IOException {
                      eventList2=oaffichliv.getSelectionModel().getSelectedItems();
                int id=eventList2.get(0).getId_liv();
                if ( id !=0)
                {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/GUIcmd/modlivraison.fxml"));
                Parent root = loader.load();
                modlivraisonController aa = loader.getController();
                aa.initData(id);
                modifliv.getScene().setRoot(root); }
    }
    
    
    


    @FXML
    private void clic_asecliv(ActionEvent event) {
          ObservableList<Livraison> livraisons = oaffichliv.getItems();
    livraisons.sort(Comparator.comparing(Livraison::getDate_liv));
    oaffichliv.setItems(livraisons);

    }
  
    @FXML
    private void clic_descliv(ActionEvent event) {
        ObservableList<Livraison> livraisons = oaffichliv.getItems();
    livraisons.sort(Comparator.comparing(Livraison::getDate_liv).reversed());
    oaffichliv.setItems(livraisons);
    }

    @FXML
    private void filtre(KeyEvent event) {
            String searchTerm = rechliv.getText();
    ServiceLivraison su = new ServiceLivraison();
    ObservableList<Livraison> list = su.searchtransprteur(searchTerm);
    List<Livraison> filteredList = list.stream()
        .filter(livraison -> livraison.getTransporteur().toLowerCase().contains(searchTerm.toLowerCase()))
        .collect(Collectors.toList());

    oaffichliv.setItems(FXCollections.observableArrayList(filteredList));
    }

    @FXML
    private void Act(ActionEvent event) {
    }

    @FXML
    private void reclamtion(ActionEvent event) {
    }

    @FXML
    private void deconnecter(ActionEvent event) {
    }

    @FXML
    private void profil(ActionEvent event) {
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
    private void comm(ActionEvent event) {
          try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pi/GUIcmd/AfficherCommande.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
               ex.printStackTrace();
            }
    }

    @FXML
    private void ajouter(ActionEvent event) {
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pi/GUIcmd/Addlivraison.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
               ex.printStackTrace();
            }
    }

    
}
