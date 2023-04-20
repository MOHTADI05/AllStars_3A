/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.promotion.entity;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author mohta
 */
public class Aliment {
    
    int id ;
    String Name;
    String Desc; 
    int  Regime;
    int Calorie;

    public Aliment(int id, String Name, String Desc, int Regime, int Calorie) {
        this.id = id;
        this.Name = Name;
        this.Desc = Desc;
        this.Regime = Regime;
        this.Calorie = Calorie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setDesc(String Desc) {
        this.Desc = Desc;
    }

    public void setRegime(int Regime) {
        this.Regime = Regime;
    }

    public void setCalorie(int Calorie) {
        this.Calorie = Calorie;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getDesc() {
        return Desc;
    }

    public int getRegime() {
        return Regime;
    }

    public int getCalorie() {
        return Calorie;
    }

    public Aliment(String Name, String Desc, int Regime, int Calorie) {
        this.Name = Name;
        this.Desc = Desc;
        this.Regime = Regime;
        this.Calorie = Calorie;
    }

    public Aliment() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.id;
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
        final Aliment other = (Aliment) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.Regime != other.Regime) {
            return false;
        }
        if (this.Calorie != other.Calorie) {
            return false;
        }
        if (!Objects.equals(this.Name, other.Name)) {
            return false;
        }
        return Objects.equals(this.Desc, other.Desc);
    }

    @Override
    public String toString() {
        return "Aliment{" + "id=" + id + ", Name=" + Name + ", Desc=" + Desc + ", Regime=" + Regime + ", Calorie=" + Calorie + '}';
    }
   
}
