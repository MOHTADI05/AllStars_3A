/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promotion.entity;

/**
 *
 * @author User
 */
public class Categorie {

    public Categorie(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    int id;
    String name;
    String description;

    public Categorie(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
