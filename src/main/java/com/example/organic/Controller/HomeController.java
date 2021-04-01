package com.example.organic.Controller;

import com.example.organic.Dao.UsersDao;
import com.example.organic.Model.Users;
import com.example.organic.Service.ServiceInterfaceImplementation.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@SessionAttributes("name")
public class HomeController {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private UserServiceImp userServiceImp;

    @GetMapping("/home")
    public String index(Principal principal, Model model){
        Users users = usersDao.findByEmail(principal.getName());
        model.addAttribute("name",users.getFirstName());
        return "index";
    }

    @GetMapping("/home2")
    public String index2(@RequestParam("email")String email, Model model){

        Users users = usersDao.findByEmail(email);
        model.addAttribute("name",users.getFirstName());

        return "index";
    }

//    @GetMapping("/hell")
//    public String hello(){
//        return "hello";
//    }

    @GetMapping("/myProfile")
    public String myprofile(Principal principal,Model model){
        Users users = usersDao.findByEmail(principal.getName());
        model.addAttribute("userInfo",users);
        String fullName = users.getFirstName()+" "+users.getLastName();
        model.addAttribute("fullName",fullName);
        return "myProfile";
    }

    @GetMapping("/edit_profile")
    public String editProfile(Principal principal,Model model){
        Users users = userServiceImp.findUser(principal.getName());
        model.addAttribute("users",users);
        return "editProfile";
    }

    @PostMapping("/edit_profile")
    public String editProfile2(@Valid Users users, Errors errors){

        if (errors.hasErrors())
            return "/edit_profile";

        System.out.println(users.getPhoneNumber());

        users.setEnable(true);

        usersDao.save(users);

        return "redirect:/myProfile";
    }
}
