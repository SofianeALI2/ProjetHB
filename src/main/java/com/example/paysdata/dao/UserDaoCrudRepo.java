package com.example.paysdata.dao;

import com.example.paysdata.entity.Pays;
import com.example.paysdata.entity.User;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDaoCrudRepo extends CrudRepository<User, Integer> {
    List<User> findByEmail(String email);
    List<User> findByLogin( String login);
@Query ("Select u from User as u where u.login = ?1 and u.password = ?2")
    List<User> findByLoginAndPassword(String login, String password);
}
