/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.Controlleur;


import pi.dao.classes.ExerciceDAO;
import pi.dao.classes.IExerciceDao;
import pi.entities.Activite;
import pi.entities.Exercice;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ExDisplayController implements Initializable {

    @FXML
    private Button dislike;

    @FXML
    private AnchorPane exForm;

    @FXML
    private ImageView img_ex;

    @FXML
    private Button like;

    @FXML
    private Label nom;

    @FXML
    private Label rep;

    @FXML
    private Label temps;
    @FXML
    private Label disliketxt;
    @FXML
    private ImageView star;
    @FXML
    private Label liketxt;
    private Image image;
    private Exercice exercice;

    public void setExercice(Exercice exercice) {
       
        
        this.exercice = exercice;
        nom.setText(exercice.getNom_Exercice());
        rep.setText(exercice.getRepet());
        temps.setText(exercice.getTemps());
        liketxt.setText(Integer.toString(exercice.getLikes()));
        disliketxt.setText(Integer.toString(exercice.getDislikes()));

        String path = "File:" + exercice.getImage_Exer();
        image = new Image(path, 117, 129, false, true);
        img_ex.setImage(image);
dataE.id=exercice.getId();


         like.setOnAction(event -> {
             dataE.id=exercice.getId();
                                         System.out.println(exercice.getId());

                IExerciceDao pdao = ExerciceDAO.getInstance();
                pdao.addlike(exercice);
                            System.out.println("2");

                       liketxt.setText(Integer.toString(exercice.getLikes()));
                       try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pi/view/voirPlus.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                String title="like ajouté";
        TrayNotification tray= new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        
        tray.setAnimationType(type);
        tray.setTitle(title);
        tray.setMessage(title);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));

            } catch (IOException ex) {
                Logger.getLogger(MainBackController.class.getName()).log(Level.SEVERE, null, ex);
            }

            });
         dislike.setOnAction(event -> {
                          dataE.id=exercice.getId();

                                         System.out.println("1");

                IExerciceDao pdao = ExerciceDAO.getInstance();
                pdao.adddislike(exercice);
                                            System.out.println("2");

                       disliketxt.setText(Integer.toString(exercice.getDislikes()));
                         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pi/view/voirPlus.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                String title="Dislike ajouté";
        TrayNotification tray= new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        
        tray.setAnimationType(type);
        tray.setTitle(title);
        tray.setMessage(title);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));

            } catch (IOException ex) {
                Logger.getLogger(MainBackController.class.getName()).log(Level.SEVERE, null, ex);
            }

            });
    }
    public void setEtoile(){
             String path="/pi/view/etoile.png";
            image = new Image(path, 40, 30, false, true);
            star.setImage(image);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
