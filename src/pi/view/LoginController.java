/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.view;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pi.dao.classes.UtilisateurDAO;
import pi.dao.interfaces.IUtilisateurDAO;
import pi.entities.Utilisateur;

/**
 * FXML Controller class
 *
 * @author swide
 */
public class LoginController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private PasswordField mdp;
    @FXML
    private Button login;
    private Stage primaryStage;
    private Parent parentPage;
    @FXML
    private Button inscrire;
    @FXML
    private Hyperlink mdpOublier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) throws IOException {
         IUtilisateurDAO Utilisateurdao = UtilisateurDAO.getInstance();
         Utilisateur U= Utilisateurdao.findByEmail(email.getText());
       // System.out.println( mdp.getText());
                  //System.out.println("getpasswmd");

         // System.out.println( U.getPassword());

          Utilisateurdao.login(email.getText(),mdp.getText());
         Stage stage1 = (Stage) login.getScene().getWindow();

            // Ferme la fenêtre
           stage1.close();

          
              
         /*try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pi/view/profil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
          }else {        
              System.out.println("LOGIN khdemech !!!!");
                }*/
             //   System.out.println("bien !!!!");
      
    }

    @FXML
    private void inscription(ActionEvent event) {
        
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pi/view/inscription.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void mdpOublier(ActionEvent event) {
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pi/view/MdpOublier.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}



    
   

