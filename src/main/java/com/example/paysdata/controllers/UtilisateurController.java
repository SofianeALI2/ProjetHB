package com.example.paysdata.controllers;

import com.example.paysdata.service.UtilServicemysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/utilisateur")
public class UtilisateurController {
    @Autowired
    private UtilServicemysql utilServicemysql;

    @CrossOrigin
    @RequestMapping( method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
    public string
}
