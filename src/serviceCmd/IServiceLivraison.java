/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceCmd;

import entiteCmd.Livraison;
import java.util.List;

/**
 *
 * @author user
 */
public interface IServiceLivraison<L> {
    
     public void ajouterLivraison(Livraison L);
    public List<Livraison> afficherLivraison();
    public void modifierLivraison (Livraison L,int i);
    public void supprimerLivraison(int id_Liv);
    public  List <Livraison> displayByID(int id_Liv) ;
    
}
