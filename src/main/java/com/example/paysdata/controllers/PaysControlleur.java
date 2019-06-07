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
@RequestMapping("/chargePays")
public class PaysControlleur {
@Autowired
private PaysServiceMysql paysServiceMusql;


    @CrossOrigin
    @RequestMapping( method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
        public String greetingForm(Model model) {
          //  model.addAttribute("Pays", new Pays());

            List<Object[]> mylist = paysServiceMusql.getAllNamePays();
            Gson gson = new Gson();

            String json="[";
            for(Object[] o : mylist){
              json+="{\"name\":\""+ String.valueOf(o[0]) +
                        "\",\"alpha2Code\":\""+ String.valueOf(o[1]) +
                        "\",\"alpha3Code\":\""+String.valueOf(o[2])+"\"},";
            }

            return(json.substring(0,json.length()-1)+"]");
        }


    }


