/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package doc.controllers;

import java.util.List;

/**
 *
 * @author moez
 */
public interface IProduitDAO<C> {
      public void ajoutProduit(C c);
    public void modifierProduit(C c, int id);
    public void supprimerProduit(int id);
    public List<C> afficherProduits();
    
    
    
}
