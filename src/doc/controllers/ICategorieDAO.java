/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doc.controllers;

import java.util.List;

/**
 *
 * @author Moez
 */
public interface ICategorieDAO<C> {
    public void ajoutCategorie(C c);
    public void modifierCategorie(C c, int id);
    public void supprimerCategorie(int id);
    public List<C> afficherCategorie();
}
