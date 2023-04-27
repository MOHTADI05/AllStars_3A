/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

public class Reclamation {
    private int id;
    private String email;
    private String objet;
    private String description;
    private Date date_reclamation;
    private int etat;

    public Reclamation() {
    }

    public Reclamation(String email, String objet, String description, Date date_reclamation, int etat) {
        this.email = email;
        this.objet = objet;
        this.description = description;
        this.date_reclamation = date_reclamation;
        this.etat = 0;
    }

    public Reclamation(int id, String email, String objet, String description, Date date_reclamation,  boolean etat) {
        this.id = id;
        this.email = email;
        this.objet = objet;
        this.description = description;
        this.date_reclamation = date_reclamation;
        this.etat = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_reclamation() {
        return date_reclamation;
    }

    public void setDate_reclamation(Date date_reclamation) {
        this.date_reclamation = date_reclamation;
    }

  

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", email=" + email + ", objet=" + objet + ", description=" + description + ", date_reclamation=" + date_reclamation +  ", etat=" + etat + '}'+"\n";
    }
    
    
}
