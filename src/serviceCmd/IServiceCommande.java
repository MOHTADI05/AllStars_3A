/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceCmd;

import entiteCmd.Commande;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author oumayma
 * @param <C>
 */
public interface IServiceCommande<C> {
    
        
    public void ajouterCommande(Commande c);
    public List<Commande> afficherCommande();
    public void modifierCommande (Commande c);
    public void supprimerCommande(int id_cmd) throws SQLException;
    public  List <Commande> displayByID(int id_cmd) ;
    
}
