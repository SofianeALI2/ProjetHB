package com.example.paysdata.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class SHelloController {

    @GetMapping("/Pays")
    public String pays(HttpServletRequest request , ModelMap map) {
        String code3Alpha = request.getParameter("country");
        map.put("code3Alpha" , code3Alpha);
        return "Site/pays";
    }
    @GetMapping("/index")
    public String indexx() {

        return "Site/index";
    }

}