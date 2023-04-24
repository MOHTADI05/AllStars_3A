/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.Controlleur;

import edu.esprit.dao.classes.ActiviteDAO;
import edu.esprit.dao.classes.ExerciceDAO;
import edu.esprit.dao.classes.IActiviteDao;
import edu.esprit.dao.classes.IExerciceDao;
import esprit.dao.entities.Activite;
import esprit.dao.entities.Exercice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author MSI
 */
public class ListData {
      private ObservableList<Exercice> exercice=FXCollections.observableArrayList();

    public ListData() {
        
        IExerciceDao pdao=ExerciceDAO.getInstance();
        exercice= pdao.displayAll();
        System.out.println(exercice);
    }
    
    public ObservableList<Exercice> getActivite(){
        return exercice;
    }
   
}
