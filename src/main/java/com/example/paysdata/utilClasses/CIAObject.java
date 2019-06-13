package com.example.paysdata.utilClasses;

public class CIAObject {
    private String countryName;
    private String capitalName;
    private String dataName;

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public CIAObject(String countryName, String capitalName , String dataName) {
        this.countryName = countryName;
        this.capitalName = capitalName;
        this.dataName = dataName;
}

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCapitalName() {
        return capitalName;
    }

    public void setCapitalName(String capitalName) {
        this.capitalName = capitalName;
    }

    @Override
    public String toString() {
        return "CIAObject{" +
                "countryName='" + countryName + '\'' +
                ", capitalName='" + capitalName + '\'' +
                ", dataName='" + dataName + '\'' +
                '}';
    }
}
