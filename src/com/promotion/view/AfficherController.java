/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promotion.view;

import com.promotion.dao.RegimeDao;
import com.promotion.entity.Regime;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AfficherController implements Initializable {

   Connection connection = null ;
    @FXML
    private TableView<Regime> personsTable;
    @FXML
    private TableColumn<Regime, Integer> idc;
    @FXML
    private TableColumn<Regime, Float> pourcentagec;
    @FXML
    private TableColumn<?, ?> start_datec;
    @FXML
    private TableColumn<?, ?> end_datec;
 
    private ListData listdata = new ListData();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        personsTable.setItems(listdata.getPersons());
        idc.setCellValueFactory(new PropertyValueFactory<>("id"));
        pourcentagec.setCellValueFactory(new PropertyValueFactory<>("pourcentage"));
        start_datec.setCellValueFactory(new PropertyValueFactory<>("start_date"));
        end_datec.setCellValueFactory(new PropertyValueFactory<>("end_date"));
    } 

    @FXML
    private void update(javafx.scene.input.MouseEvent event) {
        RegimeDao pdao=new RegimeDao();
        Regime p=new Regime();
        p=personsTable.getSelectionModel().getSelectedItem();
        p.setId(personsTable.getSelectionModel().getSelectedItem().getId());
        p.setStart_date(personsTable.getSelectionModel().getSelectedItem().getStart_date());
        p.setEnd_date(personsTable.getSelectionModel().getSelectedItem().getEnd_date());
         p.setName(personsTable.getSelectionModel().getSelectedItem().getName());
        pdao.update(p);
              
        
    }
       public void delete(){
    RegimeDao pdao =new RegimeDao();
    pdao.delete(personsTable.getSelectionModel().getSelectedItem().getId());
    System.out.println(personsTable.getSelectionModel().getSelectedItem().getId());
    }
@FXML
    private void supprimer(javafx.scene.input.MouseEvent event) {
   delete();
   personsTable.getItems().removeAll(personsTable.getSelectionModel().getSelectedItem());
   System.out.println(personsTable);
          
    }

    @FXML
    private void EXIT(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
   
}
