package com.example.paysdata.service;

import com.example.paysdata.dao.PaysDaoCrudRepo;
import com.example.paysdata.entity.Pays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PaysServiceMysql {

    @Autowired
    private PaysDaoCrudRepo todoDao;

    public Collection<Pays> getAllPays(){
        return (Collection<Pays>) this.todoDao.findAll();
    }

    public Optional<Pays> getPaysById(int id){
        return this.todoDao.findById((int) id);
    }


    public void removePaysById(int id) {
        this.todoDao.deleteById( id);
    }

    public void updatePays(Pays pays){
        //  this.todoDao.findByUpdate(todo);
    }

    public Pays insertTodo(Pays pays) {
        this.todoDao.save(pays);
        return pays;
    }

}

