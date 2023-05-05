/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promotion.view;

import pi.entities.Type;
import pi.dao.classes.TypeDao;
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
public class ListType {
    
     /**
     * The data as an observable list of Persons.
     */
    
    private ObservableList<Type> persons=FXCollections.observableArrayList();
    

    public ListType() {
         int id;
        TypeDao pdao=TypeDao.getInstance();
        persons=  (ObservableList<Type>) pdao.displayAll();
       
        System.out.println(persons);
        
    }
    
    
    public ObservableList<Type> getPersons(){
        return persons;
    }
   
}
