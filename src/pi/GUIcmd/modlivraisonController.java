/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.GUIcmd;

import entiteCmd.Commande;
import entiteCmd.Livraison;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import serviceCmd.ServiceCommande;
import serviceCmd.ServiceLivraison;

/**
 * FXML Controller class
 *
 * @author user
 */
public class modlivraisonController implements Initializable {

    @FXML
    private TextField adress_FlivM;
    @FXML
    private TextField transportFlivM;
    @FXML
    private DatePicker date_livM;
    @FXML
    private Button ModLiv;
    @FXML
    private Button torubLivM;
    private Label id;
    @FXML
    private ComboBox<String> cmd_FlivM;
         private int idE;
         private Livraison e = new Livraison();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
             
        List<String> id_offre = new ArrayList<>();
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctortechint", "root", "");
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT id_cmd FROM commande");
        while (rs.next()) {
            id_offre.add(Integer.toString(rs.getInt("id_cmd")));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
     cmd_FlivM.getItems().addAll(id_offre);
        
    }    

    @FXML
    private void Modifier_Liv(ActionEvent event) {
               
    Livraison e = new Livraison();
    ServiceLivraison su = new ServiceLivraison() ; 
        ServiceCommande sr = new ServiceCommande();
    Commande r = new Commande() ;
    e.setId_liv(idE); 
    e.setDate_liv(date_livM.getValue());
   
        e.setTransporteur(transportFlivM.getText());
        e.setAddress_liv(adress_FlivM.getText());
        

    String s1 = cmd_FlivM.getSelectionModel().getSelectedItem();
r.setId_cmd(Integer.parseInt(s1));
e.setCmd(r);


        su.modifierLivraison2(e);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("L'entretient a été modifée avec succès !");
        alert.showAndWait();
    }

    @FXML
    private void pass_rub_livM(ActionEvent event) {
    }

    @FXML
    private void AAAAAA(ActionEvent event) {
    }

    void initData(int data) {
                   this.idE=data;
      String stringNumber = Integer.toString(idE);

      id.setText(stringNumber);
      tarata();
     date_livM.setValue(e.getDate_liv());
    adress_FlivM.setText(e.getAddress_liv());
    transportFlivM.setText(e.getTransporteur());
    cmd_FlivM.setValue(String.valueOf(e.getCmd().getId_cmd()));


    }

    private void tarata() {
          ServiceLivraison ps = new ServiceLivraison();
       // e=ps.readById(idE);
       e=ps.displayLivraisonById(idE);
        //System.out.print(e);
    }
    
}
