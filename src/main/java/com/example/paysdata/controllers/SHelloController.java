package com.example.paysdata.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public String index(HttpServletRequest request , ModelMap map) {

        return "Site/index";
    }
}