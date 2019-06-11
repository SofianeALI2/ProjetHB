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
    private PaysDaoCrudRepo paysDao;

    public Collection<Pays> getAllPays(){
        return (Collection<Pays>) this.paysDao.findAll();
    }
    public List<Object[]> getAllNamePays(){
        return this.paysDao.findAllNameAlphacode();
    }

    public Optional<Pays> getPaysById(int id){
        return this.paysDao.findById( id);
    }


    public void removePaysById(int id) {

        this.paysDao.deleteById( id);
    }

    public void updatePays(Pays pays){
        this.paysDao.save(pays);
    }

    public Pays insertPays(Pays pays) {
        this.paysDao.save(pays);
        return pays;
    }

    public List<Pays> getByAlpha3Code(String code){
        return (this.paysDao.findByAlpha3Code(code));
    }

    public List<Object[]> getAllNameCapitalAlpha2Alpha3(){
        return this.paysDao.findAllNameCapitalAlpha2Alpha3();
    }


}

