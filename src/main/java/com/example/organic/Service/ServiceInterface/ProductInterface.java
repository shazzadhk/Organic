package com.example.organic.Service.ServiceInterface;


import com.example.organic.Model.Category;

import java.util.List;

public interface ProductInterface {

    void save(Category category);
    List<Category> getAllCategory();
}
