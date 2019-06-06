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
    return json;

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
        return null;
    }



}
