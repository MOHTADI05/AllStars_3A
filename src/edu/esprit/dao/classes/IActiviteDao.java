/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.classes;

import esprit.dao.entities.Activite;
import java.util.HashMap;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author MSI
 */
public interface IActiviteDao {
       void InsertActivite(Activite Activite);

     boolean updateActivite(Activite A);

     void deleteActivite(Activite a);

   // Activite findDepotById(int id);

   // Activite findDepotByAdresse(String adr);

    List<Activite> DisplayAllActivite();
     Activite displayById(int id) ;
     Activite findActiviteById(int id);
     ObservableList<Activite> displayAll();
      HashMap<String, Integer> populateCombo();
      ObservableList displayComboBox();
      void RechercheActivite();
}
