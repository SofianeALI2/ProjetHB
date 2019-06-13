package com.example.paysdata.controllers;

import com.example.paysdata.entity.Pays;
import com.example.paysdata.service.PaysServiceMysql;
import com.example.paysdata.utilClasses.CIAObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import org.json.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/createCountries")
public class CreateCountriesController {
    private String CIAPathToFile = "./src/main/resources/static/json/CIA.json";
    ArrayList<CIAObject> ciaListObject;
    private Set<String> manualEdit;

    @Autowired
    private PaysServiceMysql paysServiceMysql;

    @GetMapping(value="" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody

    public String initService(Model model) {

        JSONObject CIAJson = openCIAJsonFile();
        ciaListObject = fillCiaList(CIAJson);
        Set<String> BlacklistPays = initBlackList();
        JSONArray countryJSON = new JSONArray(getJsonListPays());
        manualEdit = manualEditSet();
        //parcourire la liste des pays et ajoutee dans la  DataBase
        for(int i=0;i<countryJSON.length();i++) {
            Pays pays = jsonToPays(countryJSON.get(i).toString());


            //Si le pays est dans la blacklist on ne l'ajoute pas
            if(BlacklistPays.contains(pays.getName())){
                continue;
            }
            else {
                pays = setCIADataName(pays);
                pays = setCIaInfo(pays,CIAJson);
                //Ajouter pays dans la DataBase
                paysServiceMysql.insertPays(pays);
            }
        }
        return null;
    }

    private Pays setCIaInfo(Pays pays, JSONObject ciaJson) {
        String paysName = pays.getCiaDataName();
        String ciaCode = getCiaCode(ciaJson.getJSONObject("countries").getJSONObject(paysName).getJSONObject("metadata").getString("source"));
        String textIntro = ciaJson.getJSONObject("countries").getJSONObject(paysName).getJSONObject("data").getJSONObject("introduction").getString("background");
        pays.setIntroText(textIntro);
        pays.setCiaCode(ciaCode);
        return pays;
    }

    private String getCiaCode(String string) {
        String keyword = ".html";
        String ciaCode = string.substring(string.indexOf(keyword)-2,string.indexOf(keyword));
        return ciaCode;
    }

    private Pays setCIADataName(Pays pays) {
            if(manualEdit.contains(pays.getName())){
                pays.setCiaDataName(manualLinking(pays.getName()));
            }
            else {
                for (CIAObject c : ciaListObject) {
                    if (c.getCountryName().toLowerCase().equals(pays.getName().toLowerCase()) ||
                            c.getCapitalName().toLowerCase().replaceAll(" .*", "").equals(pays.getCapital().toLowerCase().replaceAll(" .*", ""))) {
                        pays.setCiaDataName(c.getDataName());
                        break;
                    }
                }
            }
            return pays;
    }

    private JSONObject openCIAJsonFile() {
        String CIAJson = "";
        try {
            CIAJson +=  new String(Files.readAllBytes(Paths.get(CIAPathToFile)), StandardCharsets.UTF_8); // Lecture du fichier CIA.json
            //treatmentCIAJSON(CIAJson);
        } catch (IOException e) {
            System.out.println("Erreur d'ouverture du fichier");
        }
        JSONObject CIAJsonObject = new JSONObject(CIAJson);
        return CIAJsonObject;

    }

    private Set<String> initBlackList() {
        Set<String> BlacklistPays = new HashSet<>();
        BlacklistPays.add("Antarctica");
        BlacklistPays.add("Martinique");
        BlacklistPays.add("South Georgia and the South Sandwich Islands");
        BlacklistPays.add("Réunion");
        BlacklistPays.add("Palestine, State of");
        BlacklistPays.add("Nauru");
        BlacklistPays.add("Macao");
        BlacklistPays.add("Hong Kong");
        BlacklistPays.add("Guadeloupe");
        BlacklistPays.add("French Southern Territories");
        BlacklistPays.add("French Guiana");
        BlacklistPays.add("United States Minor Outlying Islands");
        BlacklistPays.add("British Indian Ocean Territory");
        BlacklistPays.add("Bouvet Island");
        BlacklistPays.add("Bonaire, Sint Eustatius and Saba");
        BlacklistPays.add("Åland Islands");
        BlacklistPays.add("Mayotte");
        BlacklistPays.add("Anguilla");
        BlacklistPays.add("Jersey");
        BlacklistPays.add("Western Sahara");
        BlacklistPays.add("Tokelau");
        BlacklistPays.add("Heard Island and McDonald Islands");
        BlacklistPays.add("Holy See");
        return BlacklistPays;

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

    private ArrayList<CIAObject> fillCiaList(JSONObject ciaJson) {
        ArrayList<CIAObject> ciaListObject = new ArrayList<>();
        Iterator<String> iteratorOfKeySet = ciaJson.getJSONObject("countries").keySet().iterator();
        JSONObject ciaJsonCountries = ciaJson.getJSONObject("countries");
        while(iteratorOfKeySet.hasNext()){
            String dataName = iteratorOfKeySet.next();
            JSONObject ciaJsonCountriesNext = ciaJsonCountries.getJSONObject(dataName).getJSONObject("data");
            String countryName = ciaJsonCountriesNext.getString("name");
            String capitalName = "";
            try{
                capitalName += ciaJsonCountriesNext.getJSONObject("government").getJSONObject("capital").getString("name");
            }catch (JSONException e){
                continue;
            }
            ciaListObject.add(new CIAObject(countryName,capitalName,dataName));
        }
        return  ciaListObject;
    }

    private Set<String> manualEditSet() {
        Set<String> manualEdit = new HashSet<>();
        manualEdit.add("Tanzania, United Republic of");
        manualEdit.add("Swaziland");
        manualEdit.add("Korea (Republic of)");
        manualEdit.add("Myanmar");
        manualEdit.add("Moldova (Republic of)");
        manualEdit.add("Bolivia (Plurinational State of)");
        manualEdit.add("Antigua and Barbuda");
        manualEdit.add("Belgium");
        manualEdit.add("Papua New Guinea");
        manualEdit.add("Vanuatu");
        manualEdit.add("Trinidad and Tobago");
        return manualEdit;
    }

    private String manualLinking(String paysName) {
        String ciaDataName = "";
        switch(paysName){
            case "Tanzania, United Republic of":
                ciaDataName = "tanzania";
                break;

            case "Swaziland":
                ciaDataName = "eswatini";
                break;

            case "Korea (Republic of)":
                ciaDataName = "korea_south";
                break;

            case "Myanmar":
                ciaDataName = "burma";
                break;

            case "Moldova (Republic of)" :
                ciaDataName = "moldova";
                break;

            case "Bolivia (Plurinational State of)":
                ciaDataName = "bolivia";
                break;

            case "Antigua and Barbuda" :
                ciaDataName = "antigua_and_barbuda";
                break;

            case "Belgium" :
                ciaDataName = "belgium";
                break;

            case "Papua New Guinea" :
                ciaDataName = "papua_new_guinea";
                break;

            case "Vanuatu" :
                ciaDataName = "vanuatu";
                break;

            case "Trinidad and Tobago":
                ciaDataName = "trinidad_and_tobago";
                break;
        }
        return ciaDataName;
    }

}
