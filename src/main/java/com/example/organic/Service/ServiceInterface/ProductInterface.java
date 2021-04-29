package com.example.organic.Service.ServiceInterface;


import com.example.organic.Model.Category;
import com.example.organic.Model.Product;
import com.example.organic.Model.Users;

import java.util.List;
import java.util.Optional;

public interface ProductInterface {

    void save(Category category);
    List<Category> getAllCategory();
    Category getCategory(int categoryId);
    void deleteCategory(int categoryId);
    List<Users> getAllUsers();
    void saveProduct(Product product);
    Product getProduct(int productId);
    List<Product> getAllProduct();
}
