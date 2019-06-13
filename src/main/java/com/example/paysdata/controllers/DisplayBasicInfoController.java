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
import java.util.List;

@RestController
@RequestMapping("/displayBasicInfo")
public class DisplayBasicInfoController {
    @Autowired
    private PaysServiceMysql paysServiceMysql;

    @CrossOrigin
    @RequestMapping(value = "/{paysCode}", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
    public String getJsonFromCode(@PathVariable("paysCode") String code) {

        List<Pays> countryList = paysServiceMysql.getByAlpha3Code(code);

        Pays paysToDisplay = countryList.get(0);
        System.out.println(paysToDisplay.getLanguagesProps().size());
        return (paysToDisplay.toJson());
    }



}
