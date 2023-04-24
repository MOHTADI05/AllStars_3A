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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pi.dao.classes.Session;
import pi.dao.classes.UtilisateurDAO;
import pi.dao.interfaces.IUtilisateurDAO;
import pi.entities.Utilisateur;
/**
 * FXML Controller class
 *
 * @author swide
 */
public class ListUtilisateurController implements Initializable {

    @FXML
    private TableView<Utilisateur> usertable;
    @FXML
    private TableColumn<Utilisateur, Integer> Cin;
    @FXML
    private TableColumn<Utilisateur, String> Nom;
    @FXML
    private TableColumn<Utilisateur, String> Prenom;
    @FXML
    private TableColumn<Utilisateur, String> Email;
    @FXML
    private TableColumn<Utilisateur, String> Role;
    @FXML
    private TableColumn<Utilisateur, Void> block;
    @FXML
    private Label cin1;
    @FXML
    private Label email1;
    @FXML
    private Label role1;
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
                                             System.out.println("afff " );

         Cin.setCellValueFactory(new PropertyValueFactory<Utilisateur,Integer>("id"));
          Prenom.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("prenom"));
           Nom.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("nom"));
          Email.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("Email"));
           Role.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("Role"));
          //block.setCellValueFactory(new PropertyValueFactory<Utilisateur,Boolean>("is_blocked"));
                IUtilisateurDAO Utilisateurdao = UtilisateurDAO.getInstance();
            usertable.setItems(Utilisateurdao.getAllUtilisateurs());
            
                 Utilisateur utilisateur = Session.getInstance().getLoggedUser();
                 int id=utilisateur.getId();
                   String Cin ;
                   Cin =  Integer.toString(id);
                 cin1.setText(Cin);
                 email1.setText(utilisateur.getEmail());
                 role1.setText(utilisateur.getRole());
                 //block.setCellFactory(column -> new BlockButtonCell());
    
                 
                 
    block.setCellFactory(column -> {
            return new TableCell<Utilisateur, Void>() {
                private final Button actionButton = new Button("Action");

                {
                    actionButton.setOnAction((event) -> {
                        // get the selected item from the table
                        Utilisateur U = getTableView().getItems().get(getIndex());
                       
                        // do something with the data
                        if(U.getisBlocked()==false){
                            System.out.println(U.getisBlocked());
                            Utilisateurdao.blockuser(U);
                              System.out.println(U.getNom() + " bloquer!");
                                    }else if(U.getisBlocked()==true)
                                    {
                                          Utilisateurdao.deblockuser(U);
                                    }
                        
                        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pi/view/list.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
                       
                    });
                  
          //  getTableView().refresh();

                   
                }
                 
        // TODO
                
                @Override
                protected void updateItem(Void item, boolean empty) {
                     Utilisateur a = (Utilisateur) getTableRow().getItem();
                    super.updateItem(item, empty);
                    if (!empty && a.getisBlocked()) {
                        actionButton.setText("Débloquer");
                        setGraphic(actionButton);
                    } else {
                        setGraphic(null);
                        actionButton.setText("Bloquer");
                          setGraphic(actionButton);
                    }
                }
            };
        });

    
    
    }   
     
   
    
}
    


 
 
