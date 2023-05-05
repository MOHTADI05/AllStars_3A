/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.GUIcmd;


import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import entiteCmd.PDF;
import entiteCmd.Commande;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.time.LocalDate;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pi.view.HomeController;

import serviceCmd.ServiceCommande;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AfficherCommandeController implements Initializable {

    @FXML
    private TextField rech;
        ObservableList<Commande> eventList2;
      @FXML
    private Button To_rub;
      @FXML
    private Button toModif;
      @FXML
    private Button supprimer;
      @FXML
    private Button pdf;
    @FXML
    private TableView<Commande> oaffich;
    @FXML
    private TableColumn<Commande, String> oaddress;
    @FXML
    private TableColumn<Commande, String> onumcl;
    @FXML
    private TableColumn<Commande, String> oprix;
    @FXML
    private TableColumn<Commande, String> omail;

    /**
     * Initializes the controller class.
     */
    
    ServiceCommande sr = new ServiceCommande() ;
   Commande r = new Commande() ;
    @FXML
    private TextField add;
    @FXML
    private Button ajj;
    @FXML
    private Button Act;
    @FXML
    private Button reclamtion;
    @FXML
    private Button deconnecter12;
    @FXML
    private Button profil;
    @FXML
    private Button pdf1;

    
   
    public void loadData() throws SQLException{
          
            ObservableList<Commande> fle = new ServiceCommande().afficherCommande2();
            System.err.println(fle.size());
            
                   System.out.println(fle.get(1));

        //oid.setCellValueFactory(new PropertyValueFactory<>("id_cmd"));
        
        oaddress.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
        onumcl.setCellValueFactory(new PropertyValueFactory<>("date"));
         oprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        omail.setCellValueFactory(new PropertyValueFactory<>("quantite"));
       
           oaffich.setItems(fle);
       
        
        
        
        
      }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
        loadData();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
        handleTableSelection();
    }    


    
    /* hezz hethi  w badel hasb les attributs mte3ek */    
private void handleTableSelection() {
    Commande offreSelectionnee = oaffich.getSelectionModel().getSelectedItem();
    if (offreSelectionnee != null) {
        // Récupérer les données de la ligne sélectionnée
        //String idOffre = offreSelectionnee.getId_off();
        String cat = offreSelectionnee.getNomP();
         int nbDisp = offreSelectionnee.getQuantite();
               float prix = offreSelectionnee.getPrix();
        LocalDate location = offreSelectionnee.getDate();
      
       
        
        // Faire quelque chose avec ces données (par exemple les afficher dans une boîte de dialogue)
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information sur l'offre sélectionnée");
        alert.setHeaderText(null);
        alert.setContentText( "\nNom Du Produit : " + cat + "\nDate : " + location + "\nPrix : " + prix + "\nQuantite : " + nbDisp );
        alert.showAndWait();
    }
    oaffich.setOnMouseClicked(event -> {
    if (event.getClickCount() == 1) { // une seule pression de souris
        handleTableSelection();
    }
});

oaffich.setOnKeyPressed(event -> {
    if (event.getCode().equals(KeyCode.ENTER)) { // appui sur la touche Entrée
        handleTableSelection();
    }
});

}

 @FXML
    private void versModif(ActionEvent event) throws IOException {
        
                eventList2=oaffich.getSelectionModel().getSelectedItems();
                int id=eventList2.get(0).getId_cmd();
                if ( id !=0)
                {
               
                
                    try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pi/GUIcmd/EditCommande.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
               ex.printStackTrace();
            }
    }

    }
    
        @FXML
    private void pass_rubrique (ActionEvent event){
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
    private void Supprimer(ActionEvent event) throws SQLException {
        eventList2 = oaffich.getSelectionModel().getSelectedItems();
        int id = eventList2.get(0).getId_cmd();
        Commande e = new Commande(id);
        ServiceCommande ps = new ServiceCommande();
        ps.supprimerCommande(e);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("L'offre a été supprimée avec succès !");
        alert.showAndWait();
        loadData();
    }
    
     @FXML
    private void pdf(ActionEvent event) throws DocumentException, BadElementException, IOException, FileNotFoundException, InterruptedException, SQLException {
    
        PDF pd=new PDF();
        try{
        pd.GeneratePdf("Liste des commandes");
            System.out.println("impression done");
        } catch (Exception ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
    
    @FXML
private void clic_desc() {
    ObservableList<Commande> commandes = oaffich.getItems();
    commandes.sort(Comparator.comparingDouble(Commande::getPrix).reversed());
    oaffich.setItems(commandes);
}

@FXML
private void clic_asec(ActionEvent event) {
    ObservableList<Commande> commandes = oaffich.getItems();
    commandes.sort(Comparator.comparingDouble(Commande::getPrix));
    oaffich.setItems(commandes);
}

    @FXML
    private void FiltreKeyRealsead(KeyEvent event) {
         ServiceCommande su = new ServiceCommande();
    ObservableList<Commande> list = su.search2("");
        List<Commande> list1 = list.stream()
        .filter((Commande u) -> u.getNomP().length() >= rech.getText().length())
        .filter(u -> u.getNomP().substring(0, rech.getText().length()).equalsIgnoreCase(rech.getText()))
        .collect(Collectors.<Commande>toList());

         
    ObservableList<Commande> userList = FXCollections.observableArrayList();
    list1.forEach((Commande u) -> userList.add(u));

    oaffich.setItems(userList);
    }

    @FXML
    private void FiltreKeyRealsead2(KeyEvent event) {
        ServiceCommande su = new ServiceCommande();
ObservableList<Commande> list = su.search3("");
List<Commande> list1 = list.stream()
        .filter((Commande u) -> u.getNomP().length() >= add.getText().length())
       .filter(u -> u.getNomP().substring(0, add.getText().length()).equalsIgnoreCase(add.getText()))
        .collect(Collectors.toList());

ObservableList<Commande> userList = FXCollections.observableArrayList();
userList.addAll(list1);

oaffich.setItems(userList);
    }

    @FXML
    private void ajtcmd(ActionEvent event) {
        
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pi/GUIcmd/addcommande.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
               ex.printStackTrace();
            }
        
        
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
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void livraison(ActionEvent event) {
           try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pi/GUIcmd/afficherliv_1.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }



    
}
