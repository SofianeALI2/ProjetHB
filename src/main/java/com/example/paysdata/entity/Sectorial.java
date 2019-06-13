package com.example.paysdata.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Sectorial {

    private double agriculture;
    private double industry;
    private double services;

    public Sectorial() {
    }

    public Sectorial(double agriculture, double industry, double services) {
        this.agriculture = agriculture;
        this.industry = industry;
        this.services = services;
    }

    public double getAgriculture() {
        return agriculture;
    }

    public void setAgriculture(double agriculture) {
        this.agriculture = agriculture;
    }

    public double getIndustry() {
        return industry;
    }

    public void setIndustry(double industry) {
        this.industry = industry;
    }

    public double getServices() {
        return services;
    }

    public void setServices(double services) {
        this.services = services;
    }
}
