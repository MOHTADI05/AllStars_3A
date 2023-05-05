/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.GUIcmd;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author user
 */
public class RubriqueCommandeController implements Initializable {

    @FXML
    private Button ToAjout;
    
    @FXML
    private Button ToAffich;
    @FXML
    private Button ToAjoutliv;
    private Button Tomodliv;
    private Button Tosupliv;
    @FXML
    private Button Toaffliv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Pass_ajout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/GUIcmd/addcommande.fxml"));
                Parent root = loader.load();
               AddcommandeController aa = loader.getController();
                ToAjout.getScene().setRoot(root);
    }

   


    @FXML
    private void Pass_affiche(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/GUIcmd/AfficherCommande.fxml"));
                Parent root = loader.load();
               AfficherCommandeController aa = loader.getController();
                ToAffich.getScene().setRoot(root);
    }
    
    @FXML
    private void Pass_ajoutliv(ActionEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/GUIcmd/Addlivraison.fxml"));
                Parent root = loader.load();
               AddlivraisonController aa = loader.getController();
                ToAjoutliv.getScene().setRoot(root);
    }

    

   

    @FXML
    private void Pass_afficheliv(ActionEvent event) throws IOException {
        
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherliv.fxml"));
//                Parent root = loader.load();
//               AfficherlivController aa = loader.getController();
//                Toaffliv.getScene().setRoot(root);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/GUIcmd/afficherliv_1.fxml"));
                Parent root = loader.load();
               afficherliv_1Controller aa = loader.getController();
                Toaffliv.getScene().setRoot(root);
    }
    
    
}
