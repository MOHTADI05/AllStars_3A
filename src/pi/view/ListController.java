/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.view;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.lang.String;
import static java.util.Collections.list;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import pi.dao.classes.Session;
import pi.dao.classes.UtilisateurDAO;
import pi.dao.interfaces.IUtilisateurDAO;
import pi.entities.Utilisateur;
import pi.utils.MyConnection;
/**
 * FXML Controller class
 *
 * @author swide
 */
public class ListController implements Initializable {

        private Connection connection = MyConnection.getInstance();

    
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
    @FXML
    private Button deconnecter;
    @FXML
    private Button ajoute;
    @FXML
    private TextField cin2;
    @FXML
    private TextField nom2;
    @FXML
    private TextField prenom2;
    @FXML
    private TextField email2;
    @FXML
    private PasswordField mdp2;
    @FXML
    private ComboBox role2;
    @FXML
    private Button supprimer;
    
    private ObservableList<Utilisateur> user = FXCollections.observableArrayList();
    @FXML
    private TextField Recherche;
     
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       /* System.out.println("chnaamel rech");
            
        try{
        
        }catch(Exception e){e.printStackTrace();}
        System.out.println("aamalt rech");*/

       
           ObservableList<String> list = FXCollections.observableArrayList("Coach","Nutistionist");
         role2.setItems(list);

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
      
              //  recherche(); 
              
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
                  
         supprimer.setOnAction((event) -> {
          Utilisateur U = usertable.getSelectionModel().getSelectedItem();
              System.out.println(U.getId());
    if(Utilisateurdao.deleteUtilisateur(U.getId())){
    reft(event);
    usertable.refresh();
    }else{
                System.out.println("Suppression pas marcher");

    }
    
    });
         
     
    
                   
                }
                 
        // TODO recherche();
              
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

    @FXML
    private void deconnecter(ActionEvent event) {
         Session.getInstance().clear();
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


    @FXML
    private void ajoute(ActionEvent event) {
        
             String Cin ;
        int id=0;
        Cin = cin2.getText().toString();
        if(isValidId(Cin)){
         id = Integer.parseInt(Cin);
        }
        //PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        String Nom;
        Nom=nom2.getText();
        
        String Prenom;
        Prenom=prenom2.getText();
        
        String Mdp;
        Mdp=mdp2.getText();

        
        String Email;
        Email=email2.getText();
        
        String Role;
        Role=role2.getSelectionModel().getSelectedItem().toString();
        
              
        Utilisateur Utilisateur = new Utilisateur();
        Utilisateur.setId(id);
        Utilisateur.setNom(Nom);
        Utilisateur.setPrenom(Prenom);
        Utilisateur.setPassword(Mdp);
        Utilisateur.setEmail(Email);
        Utilisateur.setRole(Role);
        Utilisateur.setisBlocked(false);
        
                IUtilisateurDAO Utilisateurdao = UtilisateurDAO.getInstance();
            if(isValidNom(Nom)&& isValidId(Cin)&&verifierEmail(Email)){     
        Utilisateurdao.inscription(Utilisateur);
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Personne insérée avec succés!");
        alert.show();
        nom2.setText("");
        prenom2.setText("");
        cin2.setText("");
        email2.setText("");
        mdp2.setText("");
        reft(event);
        
   
            }
        
    }
    
 
    
    
    public void reft(ActionEvent event){  
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pi/view/list.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
                       }
     
    public void showErrorMessage(String message) {
    JOptionPane.showMessageDialog(null, message, "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
}
    
     public boolean isValidId(String input) {
    if (input == null || input.isEmpty()) {
        showErrorMessage("L'ID ne peut pas être vide.");
        return false;
    } 
     //String x=String.valueOf(input);
    if (input.length() != 8) {
        showErrorMessage("Le nombre doit contenir exactement 8 chiffres.");
        return false;
    }
    if (!input.matches("\\d+")) {
        showErrorMessage("L'ID doit contenir uniquement des chiffres.");
        return false;
    }
    int id = Integer.parseInt(input);
    if (id <= 0) {
        showErrorMessage("L'ID doit être un entier positif.");
        return false;
    }
       PreparedStatement ps = null;
    ResultSet rs = null;
     try {
        ps = connection.prepareStatement("SELECT * FROM Utilisateur WHERE id = ?");
        ps.setString(1, input);
        rs = ps.executeQuery();
        boolean x=rs.next();   
        if( x == true ){ 
            showErrorMessage("L'cin doit etres UNIQUE !!.");
            return false;
            }
         return !rs.next();
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    } 
    
}
     
     public boolean isValidNom(String input) {
    if (input.isEmpty()) {
        showErrorMessage("Le nom ne doit pas etre vide.");
                return false;

} else if (!input.matches("[a-zA-Z]+")) {
        showErrorMessage("Le nom ne doit contenir que des lettre.");
                return false;

}    
    return true;
}
     
public boolean verifierEmail(String email) {
    if (email == null || email.isEmpty()) {
        return false;
    }
    if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
         showErrorMessage("L'email est obligatoire et doit etres valide !!.");
        return false;
    }
 
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {

        ps = connection.prepareStatement("SELECT * FROM Utilisateur WHERE email = ?");
        ps.setString(1, email);
        rs = ps.executeQuery();
        boolean x=rs.next();   
        if( x == true ){ 
            showErrorMessage("L'email doit etres UNIQUE !!.");
            return false;
            }
         return !rs.next();
    } catch (SQLException e) {
        e.printStackTrace();

        return false;
    } 
}

    public void recherche() {
       PreparedStatement st = null;
         ResultSet rs = null;
        String req = "SELECT * FROM Utilisateur";
        try {
             st = connection.prepareStatement("SELECT * FROM Utilisateur");
            rs = st.executeQuery();
            
            while (rs.next()) {
                   int Ucin = rs.getInt("id");
              
                String Unom = rs.getString("nom");
                 String Uprenom = rs.getString("prenom");
                  String Uemail = rs.getString("email");
                String Urole = rs.getString("role");
               String Umdp = rs.getString("password");
               
                user.add(new Utilisateur(Ucin, Urole, Unom,Uprenom,Uemail,Umdp));
            }
      
         Cin.setCellValueFactory(new PropertyValueFactory<Utilisateur,Integer>("id"));
          Prenom.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("prenom"));
           Nom.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("nom"));
          Email.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("Email"));
           Role.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("Role"));
        
            usertable.setItems(user);

            FilteredList<Utilisateur> filterData = new FilteredList<>(user, b -> true);
            Recherche.textProperty().addListener((observable, oldValue, newValue) -> {
                filterData.setPredicate(act -> {
                    if (newValue.isEmpty() || newValue == null) {
                        return true;
                    }
                    String searchKeyWord = newValue.toLowerCase();
                    if (act.getNom().toLowerCase().indexOf(searchKeyWord) > -1) {
                        
                        return true;
                    
                    }if (act.getRole().toLowerCase().indexOf(searchKeyWord) > -1) {
                        
                        return true;
                        
                    
                    }
                    
                    return false;
                });

            });
            SortedList<Utilisateur> sortedData = new SortedList<>(filterData);
            sortedData.comparatorProperty().bind(usertable.comparatorProperty());
            usertable.setItems(sortedData);

        } catch (SQLException ex) {
           ex.getStackTrace();
            // Logger.getLogger(MainBackController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
}
    


 
 
