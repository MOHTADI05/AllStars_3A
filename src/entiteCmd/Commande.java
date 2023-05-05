/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteCmd;
import java.time.LocalDate;
import java.util.List;
import serviceCmd.IServiceCommande;

/**
 *
 * @author oumayma
 */
public class Commande  {
    private int id;
    private String nom_p;
    private int quantite;

    private float prix ;
    private LocalDate date;

    public Commande() {
    }

    public Commande(String nom_p, int num_cl, float prix, LocalDate mail_cl) {
        this.nom_p = nom_p;
        this.quantite = num_cl;
       
        this.prix = prix;
        this.date = mail_cl;
    }

    public Commande(int id_cmd, String nom_p, int num_cl,int prix, LocalDate mail_cl) {
        this.id = id_cmd;
        this.nom_p = nom_p;
        this.quantite = num_cl;
       
        this.prix = prix;
        this.date = mail_cl;
    }

    public int getId_cmd() {
        return id;
    }

    public String getNomP() {
        return nom_p;
    }

    public int getQuantite() {
        return quantite;
    }

   

    public float getPrix() {
        return prix;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setId_cmd(int id_cmd) {
        this.id = id_cmd;
    }

    public void setNomP(String nom_p) {
        this.nom_p = nom_p;
    }

    public void setQuantite(int num_cl) {
        this.quantite = num_cl;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setDate(LocalDate mail_cl) {
        this.date = mail_cl;
    }

    public Commande(int id_cmd) {
        this.id = id_cmd;
    }
    

    @Override
    public String toString() {
        return "Commande{" + "id_cmd=" + id + ", nom_p=" + nom_p + ", num_cl=" + quantite + ", prix=" + prix + ", mail_cl=" + date + '}';
    }

    

    
    
}
