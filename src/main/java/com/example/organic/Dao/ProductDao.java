package com.example.organic.Dao;

import com.example.organic.Model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends CrudRepository<Product,Integer>{
}
