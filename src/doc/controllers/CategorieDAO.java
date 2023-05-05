/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doc.controllers;

import java.sql.Connection;
import java.util.List;
import doc.entities.Categorie;
import doc.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Moez
 */
public class CategorieDAO implements ICategorieDAO<Categorie> {

    Connection cnx;

    public CategorieDAO() {
        cnx = MyConnection.getInstance().getConnexion();
    }

    @Override
    public void ajoutCategorie(Categorie c) {
        try {
            String req = "INSERT INTO categorie(nom,information) VALUES (?,?);";

            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, c.getNom());
            ps.setString(2, c.getInformation());

            int value = ps.executeUpdate();
            if (value > 0) {
                System.out.println("L'insertion de la catégorie a été effectuée avec succès !!!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategorieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierCategorie(Categorie c, int id) {
        try {
            String req = "UPDATE categorie SET nom=?,information=? WHERE id="+id;

            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, c.getNom());
            ps.setString(2, c.getInformation());

            int value = ps.executeUpdate();
            if (value > 0) {
                System.out.println("La mise a jour de la catégorie a été effectuée avec succès !!!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategorieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerCategorie(int id) {
        try {
            String req = "DELETE FROM categorie WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            int value_supp = ps.executeUpdate();
            if (value_supp > 0) {
                System.out.println("Suppression a été effectuée avec succès");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Categorie> afficherCategorie() {
        System.out.println("Liste des catégories: \n");
        List<Categorie> list = new ArrayList<>();

        try {
            String req = "select * from categorie";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Categorie c = new Categorie();
                c.setId(rs.getInt(1));
                c.setNom(rs.getString(2));
                c.setInformation(rs.getString(3));
                list.add(c);
                System.out.println(c);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;        
    }

}
