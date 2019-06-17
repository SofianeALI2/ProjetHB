package com.example.paysdata.controllers;

import com.example.paysdata.entity.Pays;
import com.example.paysdata.service.PaysServiceMysql;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class PaysControlleur {
@Autowired
private PaysServiceMysql paysServiceMusql;


    @CrossOrigin
    @RequestMapping(value =("/chargePays"),method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
        public String greetingForm(Model model) {
          //  model.addAttribute("Pays", new Pays());

            List<Object[]> mylist = paysServiceMusql.getAllNamePays();

            String json="[";
            for(Object[] o : mylist){
              json+="{\"name\":\""+ String.valueOf(o[0]) +
                        "\",\"alpha2Code\":\""+ String.valueOf(o[1]) +
                        "\",\"alpha3Code\":\""+String.valueOf(o[2])+"\"},";
            }

            return(json.substring(0,json.length()-1)+"]");
        }

    @CrossOrigin
    @RequestMapping(value =("/chargeRegion"),method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
    public String getListRegion(Model model) {
        //  model.addAttribute("Pays", new Pays());

        List<Object[]> mylist = paysServiceMusql.getAllRegion();

        String json="[";
        for(Object[] o : mylist){
            json+="{\"region\":\""+ String.valueOf(o[0]) +
                  "\"},";
        }

        return(json.substring(0,json.length()-1)+"]");
    }



    @CrossOrigin
    @RequestMapping(value =("/chargePaysFromRegion/{region}"),method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
    public String getjsonPaysFormRegion(@PathVariable("region") String region) {
        System.out.println(region);
        List<Object[]> mylist = paysServiceMusql.getAllPaysFromRegion(region);

        String json="[";
        for(Object[] o : mylist){
            json+="{\"name\":\""+ String.valueOf(o[0]) +
                    "\",\"alpha2Code\":\""+ String.valueOf(o[1]) +
                    "\",\"alpha3Code\":\""+String.valueOf(o[2])+"\"},";
        }

        return(json.substring(0,json.length()-1)+"]");
    }

    @CrossOrigin
    @RequestMapping(value = "/chargeSubRegion/{region}", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
    public String getJsonSubregionFromRegion(@PathVariable("region") String region) {

        List<Object[]> mylist = paysServiceMusql.getAllSubRegion(region);

        String json="[";
        for(Object[] o : mylist){
            json+="{\"subregion\":\""+ String.valueOf(o[0]) +
                    "\"},";
        }

        return(json.substring(0,json.length()-1)+"]");
    }



    @CrossOrigin
    @RequestMapping(value =("/chargePaysFromSubRegion/{subregion}"),method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
    public String getjsonPaysFormSubregion(@PathVariable("subregion") String subregion) {
        System.out.println(subregion);
        List<Object[]> mylist = paysServiceMusql.getAllPaysFromSubregion(subregion);

        String json="[";
        for(Object[] o : mylist){
            json+="{\"name\":\""+ String.valueOf(o[0]) +
                    "\",\"alpha2Code\":\""+ String.valueOf(o[1]) +
                    "\",\"alpha3Code\":\""+String.valueOf(o[2])+"\"},";
        }

        return(json.substring(0,json.length()-1)+"]");
    }

}