package com.example.paysdata.controllers;

import com.example.paysdata.entity.Pays;
import com.example.paysdata.service.PaysServiceMysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/hello")
public class PaysControlleur {
@Autowired
private PaysServiceMysql paysServiceMusql;

        @GetMapping("")
        public String greetingForm(Model model) {
          //  model.addAttribute("Pays", new Pays());
            System.out.println(paysServiceMusql.getAllNamePays().toString());
            List<Object[]> mylist = paysServiceMusql.getAllNamePays();
            for(Object[] o : mylist){
                System.out.println("Couple (" + String.valueOf(o[0]) + "," + String.valueOf(o[1]) +"}");
            }

          return "Site/index";
        }


    }


