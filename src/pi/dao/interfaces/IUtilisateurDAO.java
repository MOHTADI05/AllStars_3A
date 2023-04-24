/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pi.dao.interfaces;

import java.util.List;
import javafx.collections.ObservableList;
import pi.entities.Utilisateur;

/**
 *
 * @author swide
 */
public interface IUtilisateurDAO {

    void inscription(Utilisateur Utilisateur);

    void updateUtilisateur(Utilisateur u);

    boolean deleteUtilisateur(int id);

    Utilisateur findUtilisateurById(int id);

    Utilisateur findDepotByAdresse(String adr);

    List<Utilisateur> DisplayAllDepots();

    public boolean check(String text, String text0,String text1);
    
     public boolean checkA(String text, String text0,String text1);

    public Utilisateur findByEmail(String email);

    public void login(String email, String mdp);
   

    public ObservableList<Utilisateur> displayAll();

    public ObservableList<Utilisateur> getAllUtilisateurs();
    
     public void blockuser(Utilisateur U);
     
      public void deblockuser(Utilisateur U) ;
      
        public void updatePassword(String email, String newPassword);
    
}
