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
    @Query("Select distinct p.region from Pays as p ")
    List<Object[]> findAllRegion();
    @Query("Select distinct p.subregion from Pays as p where p.region = ?1")
    List<Object[]> getAllSubRegion(String region);
    @Query("Select p.name ,p.alpha3Code,p.alpha2Code from Pays as p where p.region = ?1")
    List<Object[]> getAllPaysFromRegion(String region);
    @Query("Select p.name ,p.alpha3Code,p.alpha2Code from Pays as p where p.subregion = ?1")
    List<Object[]> getAllPaysFromSubegion(String subregion);
}
