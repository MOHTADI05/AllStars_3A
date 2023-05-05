/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.entities;

import java.util.Objects;

/**
 *
 * @author mohta
 */
public class Type {
    int id;
    String name ,discription;

    public Type(int id, String name, String discription) {
        this.id = id;
        this.name = name;
        this.discription = discription;
    }

    public Type() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.discription);
        return hash;
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
        final Type other = (Type) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.discription, other.discription)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Type{" + "id=" + id + ", name=" + name + ", discription=" + discription + '}';
    }

    public Type(String name, String discription) {
        this.name = name;
        this.discription = discription;
    }
    
}
