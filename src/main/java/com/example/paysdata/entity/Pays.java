package com.example.paysdata.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private int numeroCode;
    private String name,alpha2Code,alpah3Code,capital,altspelling,region,
            subregion,population,demorym,currencies,langage,flag;
    @ElementCollection
    @CollectionTable(
            name="borders",
            joinColumns=@JoinColumn(name="OWNER_ID")
    )
     private List<String> borders;
    public Pays() {

    }

    public Pays(int numeroCode, String name, String alpha2Code, String alpah3Code, String capital, String altspelling, String region, String subregion, String population, String demorym,
                String currencies, String langage, String flag, List<String> borders) {
        this.numeroCode = numeroCode;
        this.name = name;
        this.alpha2Code = alpha2Code;
        this.alpah3Code = alpah3Code;
        this.capital = capital;
        this.altspelling = altspelling;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
        this.demorym = demorym;
        this.currencies = currencies;
        this.langage = langage;
        this.flag = flag;
        this.borders = borders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroCode() {
        return numeroCode;
    }

    public void setNumeroCode(int numeroCode) {
        this.numeroCode = numeroCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpah3Code() {
        return alpah3Code;
    }

    public void setAlpah3Code(String alpah3Code) {
        this.alpah3Code = alpah3Code;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getAltspelling() {
        return altspelling;
    }

    public void setAltspelling(String altspelling) {
        this.altspelling = altspelling;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getDemorym() {
        return demorym;
    }

    public void setDemorym(String demorym) {
        this.demorym = demorym;
    }

    public String getCurrencies() {
        return currencies;
    }

    public void setCurrencies(String currencies) {
        this.currencies = currencies;
    }

    public String getLangage() {
        return langage;
    }

    public void setLangage(String langage) {
        this.langage = langage;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<String> getBorders() {
        return borders;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

    @Override
    public String toString() {
        return "Pays{" +
                "id=" + id +
                ", numeroCode=" + numeroCode +
                ", name='" + name + '\'' +
                ", alpha2Code='" + alpha2Code + '\'' +
                ", alpah3Code='" + alpah3Code + '\'' +
                ", capital='" + capital + '\'' +
                ", altspelling='" + altspelling + '\'' +
                ", region='" + region + '\'' +
                ", subregion='" + subregion + '\'' +
                ", population='" + population + '\'' +
                ", demorym='" + demorym + '\'' +
                ", currencies='" + currencies + '\'' +
                ", langage='" + langage + '\'' +
                ", flag='" + flag + '\'' +
                ", borders=" + borders +
                '}';
    }
}
