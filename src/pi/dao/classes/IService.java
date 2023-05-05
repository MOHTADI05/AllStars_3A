/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dao.classes;

import java.util.List;


public interface IService<T> {
    void ajouter(T t);
    void supprimer(int id);
    void modifier(int id,T t);
    List<T> afficher();
    T getById(int id);
}
