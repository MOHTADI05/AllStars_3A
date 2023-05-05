/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promotion.view;

import pi.dao.classes.RegimeDao;
import pi.dao.classes.idao;
import pi.entities.Regime;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author mohta
 */
public class FrontController implements Initializable {
@FXML
    private AnchorPane menuForm;

    @FXML
    private GridPane menugrid;
    
    private ObservableList<Regime> menuListActivite;
        private ObservableList<Regime> regime = FXCollections.observableArrayList();

    public void menuActivite(){
        
        idao pdao = RegimeDao.getInstance();
        regime = pdao.display();
        
        int row=0;
                int column=0;

        menugrid.getRowConstraints().clear();
        menugrid.getColumnConstraints().clear();
        for(int q=0;q< regime.size(); q++ ){
            try{
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/edu/esprit/view/Activite.fxml"));
            AnchorPane pane = load.load();
            DisplayRegimeController AcCont=load.getController();
            AcCont.setActivite(regime.get(q));
            if (column == 3){
                column=0;
                row+=1;
            }
            menugrid.add(pane, column++, row);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
