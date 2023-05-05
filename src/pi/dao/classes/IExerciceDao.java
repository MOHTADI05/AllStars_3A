/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dao.classes;

import pi.entities.Exercice;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author MSI
 */
public interface IExerciceDao {
        void InsertExercice(Exercice exercice);
boolean updateExercice(Exercice exercice );

     void deleteExercice(Exercice exercice);

   // Activite findDepotById(int id);

   // Activite findDepotByAdresse(String adr);

    List<Exercice> DisplayAllExercice();
     Exercice displayById(int id) ;
     Exercice findExerciceById(int id) ;
     ObservableList<Exercice> displayAll();
     ObservableList<Exercice> displayByActivite(int idActivite);
     void adddislike(Exercice exercice);
     void addlike(Exercice exercice);
      public Exercice MostLikedEx();
}
