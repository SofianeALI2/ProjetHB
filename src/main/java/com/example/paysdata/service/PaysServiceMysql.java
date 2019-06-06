package com.example.paysdata.service;

import com.example.paysdata.dao.PaysDaoCrudRepo;
import com.example.paysdata.entity.Pays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class PaysServiceMysql {

    @Autowired
    private PaysDaoCrudRepo paysDAO;

    public Collection<Pays> getAllPays(){
        return (Collection<Pays>) this.paysDAO.findAll();
    }

    public Optional<Pays> getPaysById(int id){
        return this.paysDAO.findById((int) id);
    }


    public void removePaysById(int id) {
        this.paysDAO.deleteById( id);
    }

    public void updatePays(Pays pays){
        //  this.paysDAO.findByUpdate(todo);
        this.paysDAO.save(pays);
    }

    public Pays insertPays(Pays pays) {
        this.paysDAO.save(pays);
        return pays;
    }

    public List<Pays> getByAlpha3Code(String code){
        return (this.paysDAO.findByAlpha3Code(code));
    }

}

