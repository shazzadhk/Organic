package com.example.organic.Service.ServiceInterfaceImplementation;

import com.example.organic.Dao.CategoryDao;
import com.example.organic.Dao.ProductDao;
import com.example.organic.Dao.UsersDao;
import com.example.organic.Model.Category;
import com.example.organic.Model.Product;
import com.example.organic.Model.Users;
import com.example.organic.Service.ServiceInterface.ProductInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductInterface {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private ProductDao productDao;

    @Override
    public void save(Category category) {
        categoryDao.save(category);
    }

    @Override
    public List<Category> getAllCategory() {
        return (List<Category>) categoryDao.findAll();
    }

    @Override
    public Category getCategory(int categoryId) {
        return categoryDao.findById(categoryId).get();
    }

    @Override
    public void deleteCategory(int categoryId) {

        categoryDao.deleteById(categoryId);
    }

    @Override
    public List<Users> getAllUsers() {
        return (List<Users>) usersDao.findAll();
    }

    @Override
    public void saveProduct(Product product) {
        productDao.save(product);
    }

    @Override
    public Product getProduct(int productId) {
        return productDao.findById(productId).get();
    }

    @Override
    public List<Product> getAllProduct() {
        return (List<Product>) productDao.findAll();
    }
}
