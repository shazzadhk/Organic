package com.example.organic.Dao;

import com.example.organic.Model.NewUserToken;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewUserTokenDao extends CrudRepository<NewUserToken,Integer> {

    @Query("select p from NewUserToken p where p.token = ?1")
    NewUserToken findByToken(String token);
}
