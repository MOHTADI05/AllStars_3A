/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package doc.controllers;

import doc.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import doc.entities.produit;

/**
 *
 * @author moez
 */
public class ProduitDAO implements IProduitDAO<produit> {

    private Connection cnx;

    public ProduitDAO() {
        cnx = MyConnection.getInstance().getConnexion();

    }

    @Override
    public void ajoutProduit(produit c) {
        try {
            String req = "INSERT INTO produit (categorie_id , nom , prix ,detail) VALUES (?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, c.getCategorie_id());
            ps.setString(2, c.getNom());
            ps.setInt(3, c.getPrix());
            ps.setString(4, c.getDetail());

            int value = ps.executeUpdate();
            if (value > 0) {
                System.out.println(" l insertion du produit a ete effectuer avec succes  :");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierProduit(produit c, int id) {
        try {
            String req = "UPDATE produit set categorie_id = ? , nom = ?, prix = ? , detail = ?  WHERE id =" + id;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, c.getCategorie_id());
            ps.setString(2, c.getNom());
            ps.setInt(3, c.getPrix());
            ps.setString(4, c.getDetail());

            int value_update = ps.executeUpdate();
            if (value_update > 0) {
                System.out.println(" la modification de produit a ete effectuer avec sucess  :");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void supprimerProduit(int id) {
        try {
            String req = "delete from produit where id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            int value_supp = ps.executeUpdate();
            if (value_supp > 0) {
                System.out.println(" Suppression a ete effectuer avec sucess");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<produit> afficherProduits() {
        List<produit> list_produit = new ArrayList<>();

        try {

            String req = "select * from produit";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                produit p = new produit();

                p.setId(rs.getInt(1));
                p.setCategorie_id(rs.getInt("categorie_id"));
                p.setNom(rs.getString("nom"));
                p.setPrix(rs.getInt("prix"));
                p.setDetail(rs.getString("detail"));

                list_produit.add(p);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list_produit;

    }

}
