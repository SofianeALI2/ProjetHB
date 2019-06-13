package com.example.paysdata.controllers;

import com.example.paysdata.entity.User;
import com.example.paysdata.service.UserServicemysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserServicemysql userServicemysql;

    @GetMapping("/utilisateur")
    public void addUser(HttpServletRequest request , HttpServletResponse response, HttpSession session) throws IOException {
        String name = request.getParameter("name");
        String forename=request.getParameter("forename");
        String login=request.getParameter("login");
        String password=request.getParameter("password");
        String email=request.getParameter("email");


        if (userServicemysql.getUserByEmail(email)!=null)
            //affichage cette email a utiliser deja
        {int a =10;}
        else{
            //creation de session
            session.invalidate();
          HttpSession newsession= request.getSession(true);

            newsession.setAttribute("login",login);
            newsession.setAttribute("email",email);
            newsession.setAttribute("nomPrenom",name+" " +
                    ""+forename);
            userServicemysql.updateUser(new User( name, forename, login, password, email));

        }
        response.sendRedirect("/index");
    }



    @PostMapping("/connexion")
    public void connexionUser(HttpServletRequest request ,HttpServletResponse response, ModelMap map,HttpSession session) throws IOException {

        String login=request.getParameter("login");
        String password=request.getParameter("password");
        System.out.println();
        List<User> listuser=userServicemysql.getUserByLogin(login);
            if (listuser.size()>=1){
                if (listuser.get(0).getPassword().equals(password)){
                    //creation de session

                    session.invalidate();
                    HttpSession newsession= request.getSession(true);
                    newsession.setAttribute("login",login);
                    newsession.setAttribute("email",listuser.get(0).getEmail());
                    newsession.setAttribute("nomPrenom",listuser.get(0).getName()+" " +
                            ""+listuser.get(0).getForename());

                }else{
                    //affichage pour verifiere password
                  response.sendRedirect("/index");
                 }
            }
            else{
                //affichage pour verifiere login
                response.sendRedirect("/index");
            }
        response.sendRedirect("/index");
        }

    @GetMapping(value = "/deconnexion")
    public void deconnexion(HttpServletRequest request,HttpServletResponse response, HttpSession session) throws IOException {
        //suprimer session
        if (!session.isNew()) {
            session.invalidate();
        }

        response.sendRedirect("/index");
    }

        @CrossOrigin
        @RequestMapping( method = RequestMethod.GET , value = "/verifierSession", produces = MediaType.APPLICATION_JSON_VALUE)
        public String verifierSession(HttpServletRequest request, HttpSession session) {
            String json="";
            if (!session.isNew()) {
            String email=(String) session.getAttribute("email");
            String login=(String)session.getAttribute("login");
            String nomprenom=(String)session.getAttribute("nomPrenom");
                json="{\"nomPrenom\":\""+ nomprenom +
                    "\",\"login\":\""+ login +
                    "\",\"email\":\""+email+"\"}";
            }
            return json;
        }

    }
