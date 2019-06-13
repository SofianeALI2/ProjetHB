package com.example.paysdata.controllers;



import org.apache.commons.io.*;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/CIA")
public class CIAInfoController {

    private String CIAPathToFile = "./src/main/resources/static/json/CIA.json";

    @GetMapping(value="" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String initService(Model model) {



        return("");

    }


    @GetMapping(value="/update" , produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateCiaFile(){
        try {
            URL jsonUrl = new URL("https://github.com/iancoleman/cia_world_factbook_api/raw/master/data/factbook.json");
            FileUtils.copyURLToFile(jsonUrl,new File(CIAPathToFile),100000,100000);
        } catch (Exception e) {
            System.out.println("Failed to download file");
        }

    }

    @GetMapping(value="/displayJson" , produces = MediaType.APPLICATION_JSON_VALUE)
    public String displayJsonContent(){
        String CIAJson = "";
        try {
            CIAJson +=  new String(Files.readAllBytes(Paths.get(CIAPathToFile)), StandardCharsets.UTF_8); // Lecture du fichier CIA.json
            //treatmentCIAJSON(CIAJson);
        } catch (IOException e) {
            System.out.println("Erreur d'ouverture du fichier");
        }
        return CIAJson;
    }



}
