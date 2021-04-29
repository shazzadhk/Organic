package com.example.organic.Controller;

import com.example.organic.Model.Category;
import com.example.organic.Model.Product;
import com.example.organic.Model.Users;
import com.example.organic.Service.ServiceInterfaceImplementation.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

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

        productServiceImp.saveProduct(product);
        return "admin-pages/addProduct";
    }

    @GetMapping("/view_products")
    public String showProducts(Model model){

        List<Product> productList = productServiceImp.getAllProduct();
        model.addAttribute("products",productList);
        return "admin-pages/productList";
    }

    @RequestMapping("/updateProduct")
    public String editProduct(@RequestParam("product_id") int productID,Model model){

        Product product = productServiceImp.getProduct(productID);
        model.addAttribute("product",product);
        model.addAttribute("category_list",productServiceImp.getAllCategory());
        return "admin-pages/addProduct";
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
        return "redirect:/view_category";
    }

    @GetMapping("/view_category")
    public String viewCategories(Model model){

        List<Category> categoryList = productServiceImp.getAllCategory();

        model.addAttribute("categoryList",categoryList);

        return "admin-pages/categoryList";
    }

    @GetMapping("/updateCategory")
    public String updateCategory(@RequestParam("category_id") int categoryID,Model model){

        Category category = productServiceImp.getCategory(categoryID);
        model.addAttribute("category",category);

        return "admin-pages/addCategory";
    }

    @RequestMapping("/deleteCategory")
    public String deleteCategory(@RequestParam("category_id") int categoryID){

        productServiceImp.deleteCategory(categoryID);

        return "redirect:/view_category";
    }

    @RequestMapping("/customers")
    public String allCustomers(Model model){

        List<Users> users = productServiceImp.getAllUsers();

        model.addAttribute("userList",users);
        return "admin-pages/userList";
    }
}
