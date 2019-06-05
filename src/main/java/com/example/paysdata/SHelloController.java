package com.example.paysdata;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SHelloController {

    @GetMapping("")
    public String index() {
        return "Site/pays";
    }

}