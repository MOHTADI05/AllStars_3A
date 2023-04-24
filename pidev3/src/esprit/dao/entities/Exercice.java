/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.dao.entities;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author MSI
 */
public class Exercice {
      private int id,likes,dislikes;
    private String nom_Exercice,temps,Repet,image_Exer;
private Activite id_Activite;
  

    public void setId(int id) {
        this.id = id;
    }

    public void setId_Activite(Activite id_Activite) {
        this.id_Activite = id_Activite;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public void setNom_Exercice(String nom_Exercice) {
        this.nom_Exercice = nom_Exercice;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }

    public void setRepet(String Repet) {
        this.Repet = Repet;
    }

    public void setImage_Exer(String image_Exer) {
        this.image_Exer = image_Exer;
    }

    public int getId() {
        return id;
    }

    public Activite getId_Activite() {
        return id_Activite;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public String getNom_Exercice() {
        return nom_Exercice;
    }

    public String getTemps() {
        return temps;
    }

    public String getRepet() {
        return Repet;
    }
   

   



    public String getImage_Exer() {
        return image_Exer;
    }

    public Exercice(int id, Activite id_Activite, String nom_Exercice, String temps, String Repet, String image_Exer) {
        this.id = id;
        this.id_Activite = id_Activite;
        this.nom_Exercice = nom_Exercice;
        this.temps = temps;
        this.Repet = Repet;
        this.image_Exer = image_Exer;
    }

    public Exercice(Activite id_Activite, String nom_Exercice, String temps, String Repet, String image_Exer) {
        this.id_Activite = id_Activite;
        this.nom_Exercice = nom_Exercice;
        this.temps = temps;
        this.Repet = Repet;
        this.image_Exer = image_Exer;
    }

    public Exercice(String nom_Exercice, String temps, String Repet, String image_Exer) {
        this.nom_Exercice = nom_Exercice;
        this.temps = temps;
        this.Repet = Repet;
        this.image_Exer = image_Exer;
    }

    public Exercice() {
    }
}
