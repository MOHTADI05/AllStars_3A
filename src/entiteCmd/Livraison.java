/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteCmd;

import java.time.LocalDate;
import java.util.Date;



/**
 *
 * @author user
 */
public class Livraison {
    
    private int id_liv;
    private LocalDate date_liv; 
    private String status;
    private String adress_liv;
    private Commande cmd;
   

    public Livraison() {
    }

  
    

    public Livraison(int id_liv, LocalDate date_liv, String transporteur, String adress_liv , Commande cmd) {
        this.id_liv = id_liv;
        this.date_liv = date_liv;
        this.status = transporteur;
        this.adress_liv = adress_liv;
        this.cmd=cmd;
    }

    public Livraison(LocalDate date_liv, String transporteur, String adress_liv, Commande cmd) {
        this.date_liv = date_liv; 
        this.status = transporteur;
        this.adress_liv = adress_liv;
        this.cmd = cmd;
    }

    public Livraison(int id_liv, LocalDate date_liv, String transporteur, String adress_liv) {
        this.id_liv = id_liv;
        this.date_liv = date_liv;
        this.status = transporteur;
        this.adress_liv = adress_liv;
    }

    public Livraison(int id_liv) {
        this.id_liv = id_liv;
    }
    
     

      
   


    public Commande getCmd() {
        return cmd;
    }

    public void setCmd(Commande cmd) {
        this.cmd = cmd;
    }

    public void setId_liv(int id_liv) {
        this.id_liv = id_liv;
    }

    public void setDate_liv(LocalDate date_liv) {
        this.date_liv = date_liv;
    }

   

    public void setTransporteur(String transporteur) {
        this.status = transporteur;
    }

    public void setAddress_liv(String adress_liv) {
        this.adress_liv = adress_liv;
    }

    public int getId_liv() {
        return id_liv;
    }

    public LocalDate getDate_liv() {
        return date_liv;
    }

    

    public String getTransporteur() {
        return status;
    }

    public String getAddress_liv() {
        return adress_liv;
    }

    @Override
    public String toString() {
        return "Livraison{" + "id_liv=" + id_liv + ", date_liv=" + date_liv  + ", transporteur=" + status + ", adress_liv=" + adress_liv + '}';
    }
    
    
    
}
