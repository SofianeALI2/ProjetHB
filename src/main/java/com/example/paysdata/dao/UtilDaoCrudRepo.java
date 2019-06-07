package com.example.paysdata.dao;

import com.example.paysdata.entity.Pays;
import com.example.paysdata.entity.Utilisateur;
import org.springframework.data.repository.CrudRepository;

public interface UtilDaoCrudRepo extends CrudRepository<Utilisateur, Integer> {
}
