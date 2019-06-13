package com.example.paysdata.dao;

import com.example.paysdata.entity.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdminDaoCrudRepo extends CrudRepository<Admin, Integer> {
    Admin findByLogin(String login);
}
