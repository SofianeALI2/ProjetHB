package com.example.paysdata.entity;

import com.google.gson.Gson;

import javax.persistence.*;
import java.util.List;

@Entity
public class Pays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private int numeroCode;
    private long population;
    private String name,alpha2Code, alpha3Code,capital,altspelling,region,
            subregion, demonym,currencies,langage,flag;
    @ElementCollection
    @CollectionTable(
            name="borders",
            joinColumns=@JoinColumn(name="OWNER_ID")
    )
     private List<String> borders;
    public Pays() {

    }

    public Pays(int numeroCode, String name, String alpha2Code, String alpha3Code, String capital, String altspelling, String region, String subregion, long population, String demonym,
                String currencies, String langage, String flag, List<String> borders) {
        this.numeroCode = numeroCode;
        this.name = name;
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
        this.capital = capital;
        this.altspelling = altspelling;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
        this.demonym = demonym;
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

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
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

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public String getDemonym() {
        return demonym;
    }

    public void setDemonym(String demonym) {
        this.demonym = demonym;
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
                ", alpha3Code='" + alpha3Code + '\'' +
                ", capital='" + capital + '\'' +
                ", altspelling='" + altspelling + '\'' +
                ", region='" + region + '\'' +
                ", subregion='" + subregion + '\'' +
                ", population='" + population + '\'' +
                ", demonym='" + demonym + '\'' +
                ", currencies='" + currencies + '\'' +
                ", langage='" + langage + '\'' +
                ", flag='" + flag + '\'' +
                ", borders=" + borders +
                '}';
    }

    public String toJson(){
        Gson gson = new Gson();
        return(gson.toJson(this));
    }
}
