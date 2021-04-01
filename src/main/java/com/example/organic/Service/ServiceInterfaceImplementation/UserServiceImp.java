package com.example.organic.Service.ServiceInterfaceImplementation;

import com.example.organic.Dao.UsersDao;
import com.example.organic.Model.Role;
import com.example.organic.Model.Users;
import com.example.organic.Service.ServiceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void saveUser(Users users, Role role) {

        role.setRoleName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        role.setUsers(users);

        users.setRoles( roles);

        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));

        usersDao.save(users);
    }

    @Override
    public Users createUser() {
        return new Users();
    }

//    @Override
//    public void saveValue(Users u, Users u1) {
//        u.setFirstName(u1.getFirstName());
//        u.setLastName(u1.getLastName());
//        u.setEmail(u1.getEmail());
//        u.setPassword(bCryptPasswordEncoder.encode(u1.getPassword()));
//        u.setPhoneNumber(u1.getPhoneNumber());
//    }

    @Override
    public Users findUser(String email) {
        return usersDao.findByEmail(email);
    }

    @Override
    public void saveEnable(Users users) {
        usersDao.save(users);
    }

    @Override
    public void savePassword(String pass, Users users) {
        users.setPassword(bCryptPasswordEncoder.encode(pass));
        usersDao.save(users);
    }
}
