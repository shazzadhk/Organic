package com.example.organic.Dao;

import com.example.organic.Model.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDao extends CrudRepository<Users,Integer> {

    @Query("select p from Users p where p.email = ?1")
    Users findByEmail(String email);

//    @Query("select p from Users p where p.userName = ?1")
//    Users findByUserName(String user_name);
}
