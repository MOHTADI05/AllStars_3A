/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promotion.entity;

import java.util.Date;
import javafx.beans.property.SimpleIntegerProperty;





/**
 *
 * @author User
 */
public class Promotion {
    private SimpleIntegerProperty id;
     private Date  start_date,end_date;
// prvate SimpleFloatProperty pourcentage;
       private float pourcentage;
     private Categorie categorie;
   private   Produit prodtuit;

    public Promotion() {
    }

    public Promotion(int id, Date start_date, Date end_date, float pourcentage, Categorie categorie, Produit prodtuit) {
            this.id = new SimpleIntegerProperty(id);
//this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.pourcentage = pourcentage;
        this.categorie = categorie;
        this.prodtuit = prodtuit;
    }

    public Promotion(Date start_date, Date end_date, float pourcentage, Categorie categorie, Produit prodtuit) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.pourcentage = pourcentage;
        this.categorie = categorie;
        this.prodtuit = prodtuit;
    }

    
 public SimpleIntegerProperty getIdProperty(){
        return id;
    }
    

    public int getId() {
       // return id;
        return id.get();
    }

    public void setId(int id) {
       // this.id = id;
        this.id = new SimpleIntegerProperty(id);
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public float getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(float pourcentage) {
        this.pourcentage = pourcentage;
    }

    public int getCategorie() {
        return categorie.getId();
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public  Produit getProduit() {
        return prodtuit;
    }

    public  void setProduit(Produit prodtuit) {
        this.prodtuit = prodtuit;
    }

    @Override
    public String toString() {
        return "Promotion{" + "id=" + id + ", start_date=" + start_date + ", end_date=" + end_date + ", pourcentage=" + pourcentage + ", categorie=" + categorie + '}';
    }
  
  
  
}
