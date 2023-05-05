/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.view;

//import gestionreclamationsante.FXMain;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.stage.Stage;
import pi.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Skymil
 */
public class FXMLstatreclamationController implements Initializable {

    @FXML
    private PieChart piechart;
    ObservableList<Data> data=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        stat();
    }    
    public void stat(){
        try {
            String query="SELECT count(*) as nbr, date_reclamation as date from reclamation GROUP BY date_reclamation";
            Statement st=MyConnection.getInstance().createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                data.add(new Data("Date:"+rs.getDate("date"),rs.getInt("nbr")));
            }
            piechart.setData(data);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLstatreclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    @FXML
    private void retour(ActionEvent event) {
        Stage stageclose=(Stage)((Node)event.getSource()).getScene().getWindow();
        stageclose.close();
        try {
            Parent root=FXMLLoader.load(getClass().getResource("/pi/view/Back.fxml"));

            Scene scene = new Scene(root);
            Stage primaryStage=new Stage();
            primaryStage.setTitle("Gestion recalamation");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
