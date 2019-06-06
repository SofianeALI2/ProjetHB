package com.example.paysdata.dao;

import com.example.paysdata.entity.Pays;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface PaysDaoCrudRepo extends CrudRepository<Pays, Integer> {

    @Query("Select p.name ,p.alpha2Code from Pays as p ")
    List<Object[]> findAllNameAlphacode();
}
