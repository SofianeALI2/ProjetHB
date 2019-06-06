package com.example.paysdata.controllers;

import com.example.paysdata.entity.Pays;
import com.example.paysdata.service.PaysServiceMysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
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
    private PaysServiceMysql paysServiceMusql;

    @GetMapping(value="" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String initService(Model model) {
    ArrayList<String> countryCodes = new ArrayList<>();
    countryCodes.add("FRA");
    String json = getJsonOfCountry(countryCodes.get(0));
    Pays pays = jsonToPays(json);
    return pays.toString();
    }

    private String getJsonOfCountry(String countryCode) {
        String str ="";
        String jsonResult = "";
        try {
            URL url = new URL("https://restcountries.eu/rest/v2/alpha/" + countryCode);
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
        pays.setNumeroCode(countryJSON.getInt("numericCode"));
        pays.setName(countryJSON.getString("name"));
        pays.setAlpha2Code(countryJSON.getString("alpha2Code"));
        pays.setAlpha3Code(countryJSON.getString("alpha3Code"));
        pays.setCapital(countryJSON.getString("capital"));
        pays.setAltspelling(countryJSON.getJSONArray("altSpellings").getString(1));
        pays.setRegion(countryJSON.getString("region"));
        pays.setSubregion(countryJSON.getString("subregion"));
        pays.setPopulation(countryJSON.getLong("population"));
        pays.setDemorym(countryJSON.getString("demonym"));
        pays.setCurrencies(countryJSON.getJSONArray("currencies").getJSONObject(0).getString("name"));
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
