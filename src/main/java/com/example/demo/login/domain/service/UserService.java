package com.example.demo.login.domain.service;

import com.example.demo.login.domain.Repository.UserDao;
import com.example.demo.login.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao dao;

    // for insert
    public boolean insert(User user) {

        // execute insert
        int rowNumber = dao.insertOne(user);

        // check
        boolean result = false;

        if (rowNumber > 0) {
            result = true;
        }
        return result;
    }

}
