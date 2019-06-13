package com.example.paysdata.controllers;

import com.example.paysdata.entity.Admin;
import com.example.paysdata.entity.Pays;
import com.example.paysdata.service.AdminServiceMysql;
import com.example.paysdata.service.PaysServiceMysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/")
public class AdminController {
    @Autowired
    private AdminServiceMysql adminServiceMysql;
    @GetMapping("/verifierconxadmin")
    public String greetingForm(HttpServletRequest request, Model model) {
        String login =request.getParameter("login");
        String password=request.getParameter("password");
        Admin admin=new Admin();
        try {
            admin = adminServiceMysql.getAdminByLogin(login);
        }catch(Exception e ){
            admin=null;
        }
        if (admin!=null){
            if (admin.getPassword().equals(password)){
                return "Site/admin";
            }else{
                return "votre password est incorrecte";
            }
        }else{
            return "votre login est incorrecte";
        }
           }

    @GetMapping("/conadmin")
    public String indexx(HttpSession session) {

        return "Site/conadmin";
    }

}


