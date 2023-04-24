/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.dao.entities;

/**
 *
 * @author MSI
 */
public class Activite {
      private int id,calorie;
    private String materiel,niveau,duree,objectif,image,imageFile;

    public int getId() {
        return id;
    }

    public int getCalorie() {
        return calorie;
    }

    public String getMateriel() {
        return materiel;
    }

    public String getNiveau() {
        return niveau;
    }

    public String getDuree() {
        return duree;
    }

    public String getObjectif() {
        return objectif;
    }

    public String getImage() {
        return image;
    }

    public String getImageFile() {
        return imageFile;
    }

 
    public void setId(int id) {
        this.id = id;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public void setMateriel(String materiel) {
        this.materiel = materiel;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public Activite(int calorie, String materiel, String niveau, String duree, String objectif, String imageFile) {
        this.calorie = calorie;
        this.materiel = materiel;
        this.niveau = niveau;
        this.duree = duree;
        this.objectif = objectif;
        this.imageFile = imageFile;
    }

 

    public Activite(int id, int calorie, String materiel, String niveau, String duree, String objectif, String image, String imageFile) {
        this.id = id;
        this.calorie = calorie;
        this.materiel = materiel;
        this.niveau = niveau;
        this.duree = duree;
        this.objectif = objectif;
        this.image = image;
        this.imageFile = imageFile;
    }

    public Activite(int id, int calorie, String materiel, String niveau, String duree, String objectif, String imageFile) {
        this.id = id;
        this.calorie = calorie;
        this.materiel = materiel;
        this.niveau = niveau;
        this.duree = duree;
        this.objectif = objectif;
        this.imageFile = imageFile;
    }

    public Activite(int calorie, String materiel, String niveau, String duree, String objectif, String image, String imageFile) {
        this.calorie = calorie;
        this.materiel = materiel;
        this.niveau = niveau;
        this.duree = duree;
        this.objectif = objectif;
        this.image = image;
        this.imageFile = imageFile;
    }

    @Override
    public String toString() {
        return "Activite{" + "id=" + id + ", calorie=" + calorie + ", materiel=" + materiel + ", niveau=" + niveau + ", duree=" + duree + ", objectif=" + objectif + ", image=" + image + ", imageFile=" + imageFile + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        return hash;
    }

    public Activite(int id, String objectif) {
        this.id = id;
        this.objectif = objectif;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Activite other = (Activite) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public Activite(String objectif, String niveau,int calorie, String duree,String materiel,  String imageFile   ) {
        this.calorie = calorie;
        this.materiel = materiel;
        this.niveau = niveau;
        this.duree = duree;
        this.objectif = objectif;
        this.imageFile = imageFile;

    }
  public Activite(int id, String objectif, String niveau,int calorie, String duree,String materiel,  String imageFile   ) {
                this.id = id;
      this.calorie = calorie;
        this.materiel = materiel;
        this.niveau = niveau;
        this.duree = duree;
        this.objectif = objectif;
        this.imageFile = imageFile;

    }

    public Activite(String objectif,int calorie,  String niveau, String duree,String materiel ) {
        this.calorie = calorie;
        this.materiel = materiel;
        this.niveau = niveau;
        this.duree = duree;
        this.objectif = objectif;
    }
    public Activite() {
    }
}
