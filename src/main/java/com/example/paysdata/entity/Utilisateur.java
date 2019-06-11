package com.example.paysdata.entity;

import com.google.gson.Gson;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String identifiant,passWord;
    private String email;

    public Utilisateur() {
    }

    public Utilisateur(String identifiant, String passWord, String email) {
        this.identifiant = identifiant;
        this.passWord = passWord;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String toJson(){
        Gson gson = new Gson();
        return(gson.toJson(this));
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", identifiant='" + identifiant + '\'' +
                ", passWord='" + passWord + '\'' +
                ", email=" + email +
                '}';
    }
}

