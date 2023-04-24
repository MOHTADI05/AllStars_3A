/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Skymil
 */
import java.util.Date;

public class Reponse {
    private int id;
    private int reclamation_id;
    private String objet;
    private String description;
    private Date date_reponse;

    public Reponse() {
    }

    public Reponse(int reclamation_id, String objet, String description, Date date_reponse) {
        this.reclamation_id = reclamation_id;
        this.objet = objet;
        this.description = description;
        this.date_reponse = date_reponse;
    }

    public Reponse(int id, int reclamation_id, String objet, String description, Date date_reponse) {
        this.id = id;
        this.reclamation_id = reclamation_id;
        this.objet = objet;
        this.description = description;
        this.date_reponse = date_reponse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReclamation_id() {
        return reclamation_id;
    }

    public void setReclamation_id(int reclamation_id) {
        this.reclamation_id = reclamation_id;
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

    public Date getDate_reponse() {
        return date_reponse;
    }

    public void setDate_reponse(Date date_reponse) {
        this.date_reponse = date_reponse;
    }

    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", reclamation_id=" + reclamation_id + ", objet=" + objet + ", description=" + description + ", date_reponse=" + date_reponse + '}'+"\n";
    }
    
}
