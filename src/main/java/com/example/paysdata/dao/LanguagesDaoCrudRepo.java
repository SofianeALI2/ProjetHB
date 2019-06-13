package com.example.paysdata.dao;

import com.example.paysdata.entity.LanguagesProp;
import com.example.paysdata.entity.Pays;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;



public interface LanguagesDaoCrudRepo extends CrudRepository<LanguagesProp, Integer> {

}
