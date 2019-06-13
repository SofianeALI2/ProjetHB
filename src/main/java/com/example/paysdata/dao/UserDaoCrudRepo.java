package com.example.paysdata.dao;

import com.example.paysdata.entity.Pays;
import com.example.paysdata.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDaoCrudRepo extends CrudRepository<User, Integer> {
    User findByEmail(String email);
    List<User> findByLogin( String login);

}