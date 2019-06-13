package com.example.paysdata.service;

import com.example.paysdata.dao.AdminDaoCrudRepo;

import com.example.paysdata.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceMysql {
    @Autowired
    private AdminDaoCrudRepo adminDaoCrudRepo;


    public Collection<Admin> getAllAdmin(){
        return (Collection<Admin>) this.adminDaoCrudRepo.findAll();
    }

    public Optional<Admin> getAdminById(int id){
        return this.adminDaoCrudRepo.findById( id);
    }


    public void removeAdminById(int id) {

        this.adminDaoCrudRepo.deleteById( id);
    }



    public Admin getAdminByLogin (String login){
        return this.adminDaoCrudRepo.findByLogin(login);
    }

    public void updateAdmin(Admin admin){
        this.adminDaoCrudRepo.save(admin);
    }

    public Admin insertAdmin(Admin admin) {
        this.adminDaoCrudRepo.save(admin);
        return admin;
    }


}
