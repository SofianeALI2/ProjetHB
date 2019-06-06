package com.example.paysdata.dao;

import com.example.paysdata.entity.Pays;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaysDaoCrudRepo extends CrudRepository<Pays, Integer> {
    List<Pays> findByAlpha3Code(String alpha3Code);

}
