/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.GUIcmd;

import entiteCmd.Commande;
import entiteCmd.Livraison;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import serviceCmd.ServiceLivraison;
import pi.utils.MyConnection;
import pi.view.HomeController;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AddlivraisonController implements Initializable {

    private TextField cmd_Fliv;
    @FXML
    private TextField adress_Fliv;
    @FXML
    private TextField transportFliv;
    @FXML
    private DatePicker date_liv;
    @FXML
    private Button torubLiv;
    @FXML
    private ComboBox<String> cle;
    @FXML
    private Button Ajouter_bliv;
    @FXML
    private Button Act;
    @FXML
    private Button reclamtion;
    @FXML
    private Button deconnecter12;
    @FXML
    private Button profil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        List<String> id_offre = new ArrayList<>();
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
       conn = MyConnection.getInstance();
        String sql = "SELECT id FROM commande";
           
    try {
        //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctortechint", "root", "");
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT id FROM commande");
         stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        while (rs.next()) {
            id_offre.add(Integer.toString(rs.getInt("id")));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
     cle.getItems().addAll(id_offre);
        
        
        // TODO
    }    
    
     ServiceLivraison sr = new ServiceLivraison() ;
    Livraison r = new Livraison();
    Commande c = new Commande ();
 
    @FXML
  private void ajouter_Liv(ActionEvent event) throws IOException {
    if ( adress_Fliv.getText().isEmpty() || transportFliv.getText().isEmpty() || 
    date_liv.getValue() == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs !");
        alert.showAndWait();}
    else{
    
    r.setAddress_liv(adress_Fliv.getText());
    r.setDate_liv(date_liv.getValue());
    r.setTransporteur(transportFliv.getText());
          String s1 = cle.getSelectionModel().getSelectedItem();
        c.setId_cmd(Integer.parseInt(s1));
        r.setCmd(c);
        sr.ajouterLivraison(r);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Succès");
alert.setHeaderText(null);
alert.setContentText("Votre Livraison est ajoutée !");
alert.showAndWait();
  

} 


   
    
}
  
    @FXML
    private void pass_rub_liv(ActionEvent event) throws IOException {
        
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

    @FXML
    private void cle(ActionEvent event) {
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

}
