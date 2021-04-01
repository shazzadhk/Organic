package com.example.organic.Controller;

import com.example.organic.Model.Category;
import com.example.organic.Model.Product;
import com.example.organic.Service.ServiceInterfaceImplementation.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AdminController {

    @Autowired
    private ProductServiceImp productServiceImp;

    @GetMapping("/dashboard")
    public String dashboard(){
        return "admin-pages/dashboard";
    }

    @GetMapping("/add_product")
    public String addProduct(Model model){
        Product product = new Product();
        model.addAttribute("product",product);
        model.addAttribute("category_list",productServiceImp.getAllCategory());
        return "admin-pages/addProduct";
    }

    @PostMapping("/add_product")
    public String addProduct2(@Valid Product product,Errors errors){
        if (errors.hasErrors())
            return "admin-pages/addProduct";
        return "admin-pages/product";
    }

    @GetMapping("/add_category")
    public String addCategory(Category category){
        return "admin-pages/addCategory";
    }

    @PostMapping("/add_category")
    public String addCategory2(@Valid Category category, Errors errors){
        if (errors.hasErrors()){
            return "admin-pages/addCategory";
        }
        productServiceImp.save(category);
        return "admin-pages/categoryList";
    }
}
