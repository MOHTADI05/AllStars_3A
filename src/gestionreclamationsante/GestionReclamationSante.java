/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionreclamationsante;

import entity.Reclamation;
import java.sql.Date;
import service.ServiceReclamation;
import utils.MyConnection;

/**
 *
 * @author Skymil
 */
public class GestionReclamationSante {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServiceReclamation sr=new ServiceReclamation();
        Reclamation r=new Reclamation("bbb@gmail.com", "test2", "test2", new Date(2023-1900,2,2), 0);
        //sr.ajouter(r);
        //sr.supprimer(21);
        //sr.modifier(23, r);
        //System.out.println(sr.afficher());
        //System.out.println(sr.getById(55));
        System.out.println(sr.afficherReclamationUser("ccc@gmail.com"));
    }
    
}
