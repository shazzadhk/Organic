package com.example.organic.Service.ServiceInterfaceImplementation;

import com.example.organic.Dao.CategoryDao;
import com.example.organic.Model.Category;
import com.example.organic.Service.ServiceInterface.ProductInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductInterface {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public void save(Category category) {
        categoryDao.save(category);
    }

    @Override
    public List<Category> getAllCategory() {
        return (List<Category>) categoryDao.findAll();
    }
}
