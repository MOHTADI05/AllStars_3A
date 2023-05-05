/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promotion.view;

import pi.entities.Regime;
import pi.dao.classes.RegimeDao;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;



/**
 *
 * @author wiemhjiri
 */
public class ListData {
    
     /**
     * The data as an observable list of Persons.
     */
    
    private ObservableList<Regime> persons=FXCollections.observableArrayList();
    

    public ListData() {
         int id;
        RegimeDao pdao=RegimeDao.getInstance();
        persons=  (ObservableList<Regime>) pdao.displayAll();
       
        System.out.println(persons);
        
    }
    
    
    public ObservableList<Regime> getPersons(){
        return persons;
    }
   
}
