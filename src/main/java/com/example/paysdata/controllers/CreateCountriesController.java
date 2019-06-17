package com.example.paysdata.controllers;

import com.example.paysdata.entity.LanguagesProp;
import com.example.paysdata.entity.Pays;
import com.example.paysdata.entity.Sectorial;
import com.example.paysdata.service.LanguagePropService;
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
import java.util.*;
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

    @Autowired
    private LanguagePropService languagePropService;

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
               // String ImfJsonPays = ImfToJson(pays);
                //pays = setImfTimeSeries(pays,ImfJsonPays);
                //Ajouter pays dans la DataBase
                paysServiceMysql.insertPays(pays);
            }
        }
        return null;
    }


    private String ImfToJson(Pays pays){
        String Alpha3Code = pays.getAlpha3Code();
        String timeSeriesCode = "PPPGDP";
        String url = "https://www.quandl.com/api/v3/datasets/ODA/"+Alpha3Code+"_"+timeSeriesCode+"?api_key=PxcLdVjhDJLuagmbkCAQ";
        String str ="";
        String jsonResult = "";
        try {
            URL url_json = new URL("");
            BufferedReader br = new BufferedReader(new InputStreamReader(url_json.openStream()));
            while (null != (str = br.readLine())) {
                jsonResult += str;
            }
        } catch (Exception ex) {
        }
        return jsonResult;
    }
    private Pays setImfTimeSeries(Pays pays , String json) {
        JSONObject imfJsonObject = new JSONObject(json);
        return null;


    }

    private Pays setCIaInfo(Pays pays, JSONObject ciaJson) {
        String paysName = pays.getCiaDataName();
        System.out.println(paysName);
        JSONObject countryInfo = ciaJson.getJSONObject("countries").getJSONObject(paysName);
        JSONObject countryInfoPeople = countryInfo.getJSONObject("data").getJSONObject("people");
        String ciaCode = getCiaCode(countryInfo.getJSONObject("metadata").getString("source"));
        JSONObject countryInfoGovt = countryInfo.getJSONObject("data").getJSONObject("government");
        JSONObject countryInfoEco = countryInfo.getJSONObject("data").getJSONObject("economy");
        String textIntro = countryInfo.getJSONObject("data").getJSONObject("introduction").getString("background");
        try {
            double medianAge = countryInfoPeople.getJSONObject("median_age").getJSONObject("total").getDouble("value");
            pays.setMedianAge(medianAge);
        }catch(JSONException e){
           // System.out.println("No median age for country :" + paysName);
            pays.setMedianAge(0);
        }

        try{
            double birthRate = countryInfoPeople.getJSONObject("birth_rate").getDouble("births_per_1000_population");
            pays.setBirthRate(birthRate);

        }catch(JSONException e){
           // System.out.println("No birth rate for country :" + paysName);
            pays.setMedianAge(0);
        }
        try{
            double deathRate = countryInfoPeople.getJSONObject("death_rate").getDouble("deaths_per_1000_population");
            pays.setDeathRate(deathRate);

        }catch(JSONException e){
           // System.out.println("No death rate for country :" + paysName);
            pays.setMedianAge(0);
        }

        try{
            double urbanPop =countryInfoPeople.getJSONObject("urbanization").getJSONObject("urban_population").getDouble("value");
            pays.setUrbanPop(urbanPop);
        }catch (JSONException e){
           // System.out.println("No urbanPop for country :" + paysName);
            pays.setUrbanPop(0);
        }

        try{
            double sexRatio = countryInfoPeople.getJSONObject("sex_ratio").getJSONObject("total_population").getDouble("value");
            pays.setSexRatio(sexRatio);
        }catch(JSONException e){
            //System.out.println("No sexRatio for country :" + paysName);
            pays.setUrbanPop(0);
        }

        try{
            double mobileAccess = countryInfo.getJSONObject("data").getJSONObject("communications").getJSONObject("telephones").getJSONObject("mobile_cellular").getDouble("subscriptions_per_one_hundred_inhabitants");
            pays.setMobileAccess(mobileAccess);
        }catch(JSONException e){
           //System.out.println("No mobile for country :" + paysName);
            pays.setMobileAccess(0);
        }

        try{
            double internetAccess = countryInfo.getJSONObject("data").getJSONObject("communications").getJSONObject("internet").getJSONObject("users").getDouble("percent_of_population");
            pays.setInternetAccess(internetAccess);
        }catch(JSONException e){
           // System.out.println("No internet for country :" + paysName);
            pays.setInternetAccess(0);
        }

        try{
            JSONObject ageStructure = countryInfoPeople.getJSONObject("age_structure");
            double zero14 = ageStructure.getJSONObject("0_to_14").getDouble("percent");
            double fifteen24= ageStructure.getJSONObject("15_to_24").getDouble("percent");;
            double twentyFive54= ageStructure.getJSONObject("25_to_54").getDouble("percent");;
            double fiftyFive64= ageStructure.getJSONObject("55_to_64").getDouble("percent");;
            double over65 = ageStructure.getJSONObject("65_and_over").getDouble("percent");;
            pays.setZero14(zero14);
            pays.setFifteen24(fifteen24);
            pays.setTwentyFive54(twentyFive54);
            pays.setFiftyFive64(fiftyFive64);
            pays.setOver65(over65);
        }catch (JSONException e){
            //System.out.println("No age structure for country :" + paysName);
            pays.setZero14(-1);
            pays.setFifteen24(-1);
            pays.setTwentyFive54(-1);
            pays.setFiftyFive64(-1);
            pays.setOver65(-1);
        }

        try{
            List<LanguagesProp> listofLanguages = new ArrayList<LanguagesProp>();
            JSONArray languagesArray = countryInfoPeople.getJSONObject("languages").getJSONArray("language");
            Iterator<Object> languagesArrayIterator = languagesArray.iterator();
            boolean officialAlreadySet = false;
            while(languagesArrayIterator.hasNext()){

                JSONObject languagesIterated= (JSONObject) languagesArrayIterator.next();
                if (languagesIterated.keySet().contains("percent")){
                    LanguagesProp lp = new LanguagesProp(languagesIterated.getString("name"),languagesIterated.getDouble("percent"));
                    listofLanguages.add(lp);
                    if(languagesIterated.keySet().contains("note")){
                        String notes = languagesIterated.getString("note");
                        if(notes.substring(0,Math.min(8,notes.length())).equals("official") && (!officialAlreadySet)){
                            pays.setLangage(lp.getLanguageName());
                            officialAlreadySet = true;
                        }
                    }
                   // languagePropService.insertLanguages(lp);
                }
            }

            pays.setLanguagesProps(listofLanguages);
        }catch (JSONException e){
            System.out.println("Exception dans le language pour +" +paysName);
            pays.setLanguagesProps(null);
        }

        //----------------Government:
        try{
            String govtType = countryInfoGovt.getString("government_type");
            String chiefOfState = countryInfoGovt.getJSONObject("executive_branch").getString("chief_of_state");
            String govtHead = countryInfoGovt.getJSONObject("executive_branch").getString("head_of_government");
            String govtElect = countryInfoGovt.getJSONObject("executive_branch").getString("elections_appointments");
            pays.setGovtType(govtType);
            pays.setChiefOfState(chiefOfState);
            pays.setGovtHead(govtHead);
            pays.setGovtElect(govtElect);

        }catch(JSONException e){
            System.out.println("country For gouv + " + paysName);
        }

        //Socio-economic indicators & data

        try {
            pays.setInfantMortalityRate(countryInfoPeople.getJSONObject("infant_mortality_rate").
                    getJSONObject("total").getDouble("value"));

        }catch(JSONException e) {
            pays.setInfantMortalityRate(0);
        }
        try {
            pays.setLifeExpectancyAtBirth(countryInfoPeople.getJSONObject("life_expectancy_at_birth").
                    getJSONObject("total_population").getDouble("value"));
        }catch(JSONException e) {
            pays.setLifeExpectancyAtBirth(0);
        }
        try {
            pays.setHeatlthExpenditure(countryInfoPeople.getJSONObject("health_expenditures").
                    getDouble("percent_of_gdp"));
        }catch(JSONException e) {
            pays.setHeatlthExpenditure(0);
        }
        try {
            pays.setAdultObesity(countryInfoPeople.getJSONObject("adult_obesity").getDouble("percent_of_adults"));
        }catch(JSONException e) {
            pays.setAdultObesity(0);
        }
        try {
            pays.setEducationExpenditure(countryInfoPeople.getJSONObject("education_expenditures").
                    getDouble("percent_of_gdp"));
        }catch(JSONException e) {
            pays.setAdultObesity(0);
        }
        try{
            pays.setLiteracy(countryInfoPeople.getJSONObject("literacy").getJSONObject("total_population").getDouble("value"));
        }catch(JSONException e){
            pays.setLiteracy(0);
        }

        try{
            pays.setGdpPPP(countryInfoEco.getJSONObject("gdp").getJSONObject("purchasing_power_parity").getJSONArray("annual_values").getJSONObject(0).getDouble("value"));
        }catch (JSONException e) {
            System.out.println("Country + " +paysName + "--- ERROR GDP");
            e.printStackTrace();
            pays.setGdpPPP(0);
        }
        try{
            Sectorial gdpBySector = new Sectorial();
            JSONObject gdpSectorJson = countryInfoEco.getJSONObject("gdp").getJSONObject("composition").getJSONObject("by_sector_of_origin").
                    getJSONObject("sectors");
            gdpBySector.setAgriculture(gdpSectorJson.getJSONObject("agriculture").getDouble("value"));
            gdpBySector.setIndustry(gdpSectorJson.getJSONObject("industry").getDouble("value"));
            gdpBySector.setServices(gdpSectorJson.getJSONObject("services").getDouble("value"));
            pays.setGdpBySector(gdpBySector);
        }catch(JSONException e){
            pays.setGdpBySector(new Sectorial(0,0,0));
        }

        try{
            pays.setTotalLaborForce(countryInfoEco.getJSONObject("labor_force").getJSONObject("total_size").getDouble("total_people"));
        }catch(JSONException e){
            pays.setTotalLaborForce(0);
        }
        try{
            Sectorial laborForceBySector = new Sectorial();
            JSONObject laborForceSector = countryInfoEco.getJSONObject("labor_force").getJSONObject("by_occupation").getJSONObject("occupation");
            laborForceBySector.setAgriculture(laborForceSector.getJSONObject("agriculture").getDouble("value"));
            laborForceBySector.setIndustry(laborForceSector.getJSONObject("industry").getDouble("value"));
            laborForceBySector.setServices(laborForceSector.getJSONObject("services").getDouble("value"));
            pays.setLaborForceBySector(laborForceBySector);
        }catch(JSONException e){
            pays.setLaborForceBySector(new Sectorial(0,0,0));
        }

        try{
            pays.setPublicDebt(countryInfoEco.getJSONObject("public_debt").getJSONArray("annual_values").getJSONObject(0).getDouble("value"));
        }catch(JSONException e){
            pays.setPublicDebt(0);
        }
        try{
            pays.setUnemployment(countryInfoEco.getJSONObject("unemployment_rate").getJSONArray("annual_values").getJSONObject(0).getDouble("value"));
        }catch(JSONException e){
            pays.setUnemployment(0);
        }
        try{
            pays.setInflation(countryInfoEco.getJSONObject("inflation_rate").getJSONArray("annual_values").getJSONObject(0).getDouble("value"));
        }catch(JSONException e){
            pays.setInflation(0);
        }


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
