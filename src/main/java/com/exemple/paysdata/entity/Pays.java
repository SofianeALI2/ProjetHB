package com.exemple.paysdata.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private int numeroCode;
    private String name,alpha2Code,alpah3Code,capital,region,subregion,population,demorym,langage,currencies,flag;

    public Pays() {

    }


}
