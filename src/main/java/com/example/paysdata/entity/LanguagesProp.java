package com.example.paysdata.entity;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
public class LanguagesProp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String languageName;
    private double languageProp;



    public LanguagesProp(String languageName, double languageProp) {
        this.languageName = languageName;
        this.languageProp = languageProp;
    }
    public LanguagesProp() {

    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public double getLanguageProp() {
        return languageProp;
    }

    public void setLanguageProp(double languageProp) {
        this.languageProp = languageProp;
    }
}
