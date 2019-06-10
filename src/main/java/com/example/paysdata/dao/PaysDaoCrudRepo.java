package com.example.paysdata.dao;

import com.example.paysdata.entity.Pays;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface PaysDaoCrudRepo extends CrudRepository<Pays, Integer> {
    List<Pays> findByAlpha3Code(String alpha3Code);

    @Query("Select p.name ,p.alpha2Code ,p.alpha3Code from Pays as p ")
    List<Object[]> findAllNameAlphacode();

    /* Fonction pour effectuer la jointure des données RESTCountries et CIA */
    @Query("Select p.name ,p.capital ,p.alpha2Code, p.alpha3Code from Pays as p ")
    List<Object[]> findAllNameCapitalAlpha2Alpha3();
}
