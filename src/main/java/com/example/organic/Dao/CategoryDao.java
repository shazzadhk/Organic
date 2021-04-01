package com.example.organic.Dao;

import com.example.organic.Model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends CrudRepository<Category,Integer>{
}
