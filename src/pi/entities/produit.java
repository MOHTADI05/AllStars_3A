/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pi.entities;

/**
 *
 * @author Moez
 */
public class produit {
    
    private int id;
    private int categorie_id;
    private String nom; 
    private int prix ;
    private String detail;

    public produit() {
    }
    

    public produit(int id, int categorie_id, String nom, int prix, String detail) {
        this.id = id;
        this.categorie_id = categorie_id;
        this.nom = nom;
        this.prix = prix;
        this.detail = detail;
    }

    public produit(int categorie_id, String nom, int prix, String detail) {
        this.categorie_id = categorie_id;
        this.nom = nom;
        this.prix = prix;
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "produit{" + "id=" + id + ", categorie_id=" + categorie_id + ", nom=" + nom + ", prix=" + prix + ", detail=" + detail + '}';
    }
    
    
    
    
    
    
}
