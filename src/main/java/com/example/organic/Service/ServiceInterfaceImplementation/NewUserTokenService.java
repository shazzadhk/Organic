package com.example.organic.Service.ServiceInterfaceImplementation;

import com.example.organic.Dao.NewUserTokenDao;
import com.example.organic.Model.NewUserToken;
import com.example.organic.Model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NewUserTokenService {

    @Autowired
    private NewUserTokenDao newUserTokenDao;

    public String saveUserWithToken(Users users){

        String token = UUID.randomUUID().toString();
        NewUserToken newUserToken = new NewUserToken(token,users);
        newUserTokenDao.save(newUserToken);

        return token;
    }

    public NewUserToken findUserByToken(String token){
        return newUserTokenDao.findByToken(token);
    }
}
