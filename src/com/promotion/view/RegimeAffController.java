/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promotion.view;

import pi.dao.classes.RegimeDao;
import pi.entities.Regime;
import com.promotion.view.RegimeController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class RegimeAffController implements Initializable {

    @FXML
    private ScrollPane meniScroll;

    @FXML
    private AnchorPane menuform;

    @FXML
    private GridPane menugrid;
    private ObservableList<Regime> menuListActivite;
        private ObservableList<Regime> regime = FXCollections.observableArrayList();

    public void menuActivite(){
        
        RegimeDao pdao = RegimeDao.getInstance();
        regime.clear();
        regime.addAll(pdao.displayAll());

      
        int row=0;
                int column=0;

        menugrid.getRowConstraints().clear();
        menugrid.getColumnConstraints().clear();
        for(int q=0;q< regime.size(); q++ ){
            try{
                

                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/com/promotion/view/Regime.fxml"));
            AnchorPane pane = load.load();
            RegimeController AcCont=load.getController();
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
        menuActivite();
    }    
    
}
