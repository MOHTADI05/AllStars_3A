/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pi.entities;

/**
 *
 * @author swide
 */
public class Utilisateur {
      private int id;
    private String nom,prenom,email,password;
    private boolean isBlocked=false;
    private String role="Client";

    public Utilisateur(int Ucin, String Urole, String Unom) {
        this.id=Ucin;
        this.role=Urole;
        this.nom=Unom;
    }

    public Utilisateur() {
    }

    public Utilisateur(int Ucin, String Urole, String Unom, String Uprenom, String Uemail, String Umdp) {
       this.id=Ucin;
        this.role=Urole;
        this.nom=Unom;
        this.email=Uemail;
        this.password=Umdp;
        this.prenom=Uprenom;
    }
   

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
   
    public void setisBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }
public boolean getisBlocked() {
        return isBlocked;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
    
    
    
}
