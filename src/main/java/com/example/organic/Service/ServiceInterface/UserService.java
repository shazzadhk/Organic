package com.example.organic.Service.ServiceInterface;

import com.example.organic.Model.Role;
import com.example.organic.Model.Users;


public interface UserService {

    void saveUser(Users users, Role role);
    Users createUser();
//    void saveValue(Users u,Users u1);
    Users findUser(String email);
    void saveEnable(Users users);
    void savePassword(String pass,Users users);
}
