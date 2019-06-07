package com.example.paysdata.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SHelloController {

    @GetMapping("/pays")
    public String index() {

        return "Site/pays";
    }
    @GetMapping("/index")
    public String indexx() {

        return "Site/index";
    }

}