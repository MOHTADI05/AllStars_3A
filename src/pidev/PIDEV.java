/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;


import com.promotion.dao.RegimeDao;
import com.promotion.entity.Categorie;
import com.promotion.entity.Produit;
import com.promotion.entity.Regime;
import java.io.IOException;
import java.sql.Date;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author User
 */
public class PIDEV extends Application {
   //  parentPage = FXMLLoader.load(getClass().getResource("/com/promotion/view/Accueil.fxml"));
    private Stage primaryStage;
    private Parent parentPage;
    @Override
    public void start(Stage primaryStage) {
       this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Hello Mohtadi");
        try {
            //Parent parent = FXMLLoader.load(getClass().getResource("/com/promotion/view/afficher.fxml"));
           Parent parent = FXMLLoader.load(getClass().getResource("/com/promotion/view/home.fxml"));
            Scene scene = new Scene(parent);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.show();
        } catch (IOException ex) {
          //  Logger.getLogger(OperationTable.class.getName()).log(Level.SEVERE, null, ex);
        }

    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       launch(args);
//    Regime p =new Regime();
//    p.setId(1);
//    p.setType(1);
//    p.setName("khalill");
//    p.setDiscription("test");
//    Date d=new Date(2018-03-27);
//    p.setStart_date(d);
//    p.setCalories(1);
//    p.setEnd_date(d);
//    
//   
//   
//    RegimeDao promotiondao=new RegimeDao();
//  promotiondao.update(p);
    
    
       
      
    }
    
}
