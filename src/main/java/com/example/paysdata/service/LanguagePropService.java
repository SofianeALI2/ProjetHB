package com.example.paysdata.service;

import com.example.paysdata.dao.LanguagesDaoCrudRepo;
import com.example.paysdata.entity.LanguagesProp;
import com.example.paysdata.entity.Pays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class LanguagePropService {
    @Autowired
    LanguagesDaoCrudRepo languagesDaoCrudRepo;

    public Collection<LanguagesProp> getAllLanguages(){
        return (Collection<LanguagesProp>) this.languagesDaoCrudRepo.findAll();
    }

    public void removeLanguagesById(int id) {

        this.languagesDaoCrudRepo.deleteById( id);
    }

    public void updateLanguages(LanguagesProp languagesProp){
        this.languagesDaoCrudRepo.save(languagesProp);
    }

    public LanguagesProp insertLanguages(LanguagesProp languagesProp) {
        this.languagesDaoCrudRepo.save(languagesProp);
        return languagesProp;
    }


}
