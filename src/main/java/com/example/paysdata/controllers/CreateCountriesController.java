package com.example.paysdata.controllers;

import com.example.paysdata.entity.Pays;
import com.example.paysdata.service.PaysServiceMysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import org.json.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/createCountries")
public class CreateCountriesController {
    @Autowired
    private PaysServiceMysql paysServiceMysql;

    @GetMapping(value="" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody

    public String initService(Model model) {


        JSONArray countryJSON = new JSONArray(getJsonListPays());

        //parcourire la liste des pays et ajoutee dans la  DataBase

        for(int i=0;i<countryJSON.length();i++) {
            Pays pays = jsonToPays(countryJSON.get(i).toString());
            //Ajouter pays dans la DataBase
            paysServiceMysql.insertPays(pays);
        }
        return null;
    }

    private String getJsonListPays() {
        String str ="";
        String jsonResult = "";
        try {
            URL url = new URL("https://restcountries.eu/rest/v2/all");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            while (null != (str = br.readLine())) {
                jsonResult += str;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jsonResult;
    }



    private Pays jsonToPays(String json){
        Pays pays=new Pays();
        JSONObject countryJSON = new JSONObject(json);
        //test sur numeroCode
        int numerocode=0;
        try {
            numerocode=countryJSON.getInt("numericCode");
        }catch (Exception e){
            e.printStackTrace();
            numerocode=0;
        }
        pays.setNumeroCode(numerocode);
        pays.setName(countryJSON.getString("name"));
        pays.setAlpha2Code(countryJSON.getString("alpha2Code"));
        pays.setAlpha3Code(countryJSON.getString("alpha3Code"));
        pays.setCapital(countryJSON.getString("capital"));
        //test sur altspling
        String altsplings="";
        try {
            altsplings=  countryJSON.getJSONArray("altSpellings").getString(1);
        }catch (Exception e){
            e.printStackTrace();
            altsplings=null;
        }
            //////////
        pays.setAltspelling(altsplings);


        pays.setRegion(countryJSON.getString("region"));
        pays.setSubregion(countryJSON.getString("subregion"));
        pays.setPopulation(countryJSON.getLong("population"));
        pays.setDemonym(countryJSON.getString("demonym"));
        //test sur curencies
        String curencies="";
        try {
            curencies=  countryJSON.getJSONArray("currencies").getJSONObject(0).getString("name");
        }catch (Exception e){
            e.printStackTrace();
            curencies=null;
        }
        pays.setCurrencies(curencies);

        /////////

        pays.setLangage(countryJSON.getJSONArray("languages").getJSONObject(0).getString("name"));
        pays.setFlag(countryJSON.getString("flag"));
        JSONArray bordersjson = countryJSON.getJSONArray("borders");
        ArrayList<String> borders=new ArrayList<>();

        for (int i=0;i<bordersjson.length();i++) {
            borders.add(bordersjson.getString(i));
        }
        pays.setBorders(borders);

        return pays;
    }


}
