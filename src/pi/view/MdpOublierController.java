/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.view;

//import java.net.PasswordAuthentication;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;
  import java.util.Random;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import pi.dao.classes.EmailUtil;
import pi.dao.classes.UtilisateurDAO;
import pi.dao.interfaces.IUtilisateurDAO;
import pi.entities.Utilisateur;


/**
 * FXML Controller class
 *
 * @author swide
 */
public class MdpOublierController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private Button envoyer;
   
    @FXML
    private TextField code;
    @FXML
    private Button valider;
    @FXML
    private Button confirmer;
    
     private String email1;
     
    private int codeAleatoire;
    @FXML
    private AnchorPane codeform;
    @FXML
    private AnchorPane emailform;
    @FXML
    private AnchorPane mdpform;
    @FXML
    private PasswordField Nmdp;
    
    public void setEmail(String email) {
        this.email1 = email;
    }
    
    public void setCodeAleatoire(int codeAleatoire) {
        this.codeAleatoire = codeAleatoire;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          envoyer.setOnAction((event) -> {
                        int randomNumber = generateRandomNumber();
                         System.out.println(randomNumber );
                             setCodeAleatoire(randomNumber);
                                                      System.out.println(codeAleatoire );

                         String emailBody = "Votre numéro de réinitialisation de mot de passe est: " + randomNumber;
        
                        try {
                            System.out.println("1" );
                            EmailUtil.sendEmail(email.getText(), "Réinitialisation de mot de passe", emailBody);
                            System.out.println("2" );
                             } catch (MessagingException ex) 
                             {
                                ex.printStackTrace();
                             }                    
                    });
          
          
          
            valider.setOnAction((event) -> {
                        System.out.println(codeAleatoire );
                        String c=code.getText();
                        int code1=Integer.parseInt(c);
                         if (code1==codeAleatoire) {
            
                           System.out.println("m" );
                           codeform.setVisible(false);
                           mdpform.setVisible(true);
                           
                           

            // Modifier le mot de passe de l'utilisateur dans la base de données
          //  String nouveauMdp = nouveauMdpPasswordField.getText();
            // Code pour modifier le mot de passe dans la base de données
            // ...
            
            // Fermer la fenêtre
           // Stage stage = (Stage) validerButton.getScene().getWindow();
            //stage.close();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Code incorrect");
            alert.setContentText("Le code entré ne correspond pas au code envoyé par email.");
            alert.showAndWait();
        }
                    });
        // TODO
    }    

    @FXML
    private void envoyer(ActionEvent event) {
       int randomNumber = generateRandomNumber();
                             System.out.println(randomNumber );
                             
        String emailBody = "Votre numéro de réinitialisation de mot de passe est: " + randomNumber;
        
        try {
               System.out.println("1" );

            EmailUtil.sendEmail("aissa.swiden@esprit.tn", "Réinitialisation de mot de passe", emailBody);
   System.out.println("2" );

        } catch (MessagingException ex) {
          //  Logger.getLogger(MdpOublierController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }

    public static int generateRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(900000) + 100000;
    }

    @FXML
    private void valider(ActionEvent event) {
        if (code.getText().equals(codeAleatoire)) {
            
                           System.out.println("m" );

            // Modifier le mot de passe de l'utilisateur dans la base de données
          //  String nouveauMdp = nouveauMdpPasswordField.getText();
            // Code pour modifier le mot de passe dans la base de données
            // ...
            
            // Fermer la fenêtre
           // Stage stage = (Stage) validerButton.getScene().getWindow();
            //stage.close();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Code incorrect");
            alert.setContentText("Le code entré ne correspond pas au code envoyé par email.");
            alert.showAndWait();
        }
    }

    @FXML
    private void confirmer(ActionEvent event) {
        
          IUtilisateurDAO Utilisateurdao = UtilisateurDAO.getInstance();
          Utilisateurdao.updatePassword(email.getText(), Nmdp.getText());
          try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pi/view/login.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
          
          
    }



    
}
