package com.example.paysdata.service;

import com.example.paysdata.dao.UserDaoCrudRepo;
import com.example.paysdata.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserServicemysql {

    @Autowired
    private UserDaoCrudRepo userDaoCrudRepo;


    public Collection<User> getAllUser(){
        return (Collection<User>) this.userDaoCrudRepo.findAll();
    }

    public Optional<User> getUserById(int id){
        return this.userDaoCrudRepo.findById( id);
    }


    public void removeUserById(int id) {

        this.userDaoCrudRepo.deleteById( id);
    }

    public User getUserByEmail(String email){
        return this.userDaoCrudRepo.findByEmail(email);
    }

    public List<User> getUserByLogin (String login){
        return this.userDaoCrudRepo.findByLogin(login);
    }

    public void updateUser(User user){
        this.userDaoCrudRepo.save(user);
    }

    public User insertUser(User user) {
        this.userDaoCrudRepo.save(user);
        return user;
    }
}
