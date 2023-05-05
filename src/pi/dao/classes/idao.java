/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dao.classes;

import pi.dao.classes.idao;
import java.util.List;
import javafx.collections.ObservableList;
import pi.entities.Regime;

/**
 *
 * @author User
 */
public interface idao<T> {
    public void insert(T o);
    public void delete(int i);
   public List<T> displayAll();
   public T displayById(int id);
    public void modifier(int id, T os);
   public void update(T os);

    public ObservableList<Regime> display();
}
