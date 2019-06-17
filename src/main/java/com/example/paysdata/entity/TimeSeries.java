package com.example.paysdata.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

public abstract class TimeSeries<T> {



    private String name;

    private ArrayList<Date> date;
    private String units;


    public TimeSeries() {
    }

    public TimeSeries(String name, ArrayList<T> value, ArrayList<Date> date, String units) {
        this.name = name;
        this.date = date;
        this.units = units;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public ArrayList<Date> getDate() {
        return date;
    }

    public void setDate(ArrayList<Date> date) {
        this.date = date;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}
