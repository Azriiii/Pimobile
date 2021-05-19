/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.entites;

/**
 *
 * @author Amine
 */
public class User {
   private int id ;
   private String nom ;
   private String prenom ;
   private String Email ;
   private String password ;

    public User() {
    }

    public User(String Email, String password) {
        this.Email = Email;
        this.password = password;
    }

    public User(int id, String nom, String prenom, String Email, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.Email = Email;
        this.password = password;
    }

    public User(String nom, String prenom, String Email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.Email = Email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
   
}
