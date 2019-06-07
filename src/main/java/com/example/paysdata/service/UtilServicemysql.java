package com.example.paysdata.service;

import com.example.paysdata.dao.UtilDaoCrudRepo;
import com.example.paysdata.entity.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UtilServicemysql {

    @Autowired
    private UtilDaoCrudRepo utilDaoCrudRepo;


    public Collection<Utilisateur> getAllUtilisateur(){
        return (Collection<Utilisateur>) this.utilDaoCrudRepo.findAll();
    }

    public Optional<Utilisateur> getUtlisateurById(int id){
        return this.utilDaoCrudRepo.findById( id);
    }


    public void removeUtilisateurById(int id) {

        this.utilDaoCrudRepo.deleteById( id);
    }

    public void updateUtlilisateur(Utilisateur utilisateur){
        this.utilDaoCrudRepo.save(utilisateur);
    }

    public Utilisateur insertUtilisateur(Utilisateur utilisateur) {
        this.utilDaoCrudRepo.save(utilisateur);
        return utilisateur;
    }
}
