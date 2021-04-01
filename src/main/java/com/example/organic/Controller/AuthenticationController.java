package com.example.organic.Controller;

import com.example.organic.Model.NewUserToken;
import com.example.organic.Model.Role;
import com.example.organic.Model.Users;
import com.example.organic.Service.ServiceInterfaceImplementation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class AuthenticationController {

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    private RoleServiceImp roleServiceImp;

    @Autowired
    private NewUserTokenService newUserTokenService;

    @Autowired
    private MailConstructor mailConstructor;

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/registration")
    public String signup(Users users){
        return "registration";
    }

    @PostMapping("/registration")
    public String signup2(@Valid Users users, Errors errors, Model model, HttpServletRequest request) throws MessagingException {

        if (errors.hasErrors())
            return "registration";

        Users users2 = userServiceImp.findUser(users.getEmail());
        if (users2 != null){
            model.addAttribute("EmailExistingMsg",true);
            return "registration";
        }

        Role role = roleServiceImp.createRole();

//        Users users1 = userServiceImp.createUser();
//        userServiceImp.saveValue(users1,users);
        userServiceImp.saveUser(users,role);

        String token = newUserTokenService.saveUserWithToken(users);

        String appUrl = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();

        mailConstructor.sendVerificationEmail(appUrl,token,users);

        model.addAttribute("successMessage",true);

        return "registration";
    }

    @RequestMapping("/newUser")
    public String newUser(@RequestParam("token")String token,Model model){

        NewUserToken newUserToken = newUserTokenService.findUserByToken(token);

        if (newUserToken == null){
            model.addAttribute("tokenError",true);
            return "registration";
        }

        if (newUserToken.isExpired()){
            model.addAttribute("tokenExpire",true);
            return "registration";
        }

        Users users = newUserToken.getUsers();

        users.setEnable(true);

        userServiceImp.saveEnable(users);
        UserDetails userDetails = userDetailsServiceImp.loadUserByUsername(users.getEmail());

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,userDetails.getPassword(),userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/home";

    }

    @GetMapping("/forgetPassword")
    public String forgetPass(){
        return "forgetPassword";
    }

    @PostMapping("/forgetPassword")
    public String forgetPass2(@RequestParam("email")String email,HttpServletRequest request,Model model) throws MessagingException {

        Users users = userServiceImp.findUser(email);

        if (users == null){
            model.addAttribute("ForgetEmailError",true);
            return "forgetPassword";
        }

        String token = newUserTokenService.saveUserWithToken(users);

        String appUrl = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();

        mailConstructor.sendForgetPasswordEmail(appUrl,token,users);

        model.addAttribute("sendEmail",true);

        return "forgetPassword";
    }

    @GetMapping("/resetPassword")
    public String changePassword(@RequestParam("token")String token, Model model){
        NewUserToken newUserToken = newUserTokenService.findUserByToken(token);

        if (newUserToken == null){
            model.addAttribute("invalidToken",true);
            return "resetPassword";
        }

        if (newUserToken.isExpired()){
            model.addAttribute("tokenExpired",true);
            return "resetPassword";
        }
        model.addAttribute("passwordToken",newUserToken);
        return "resetPassword";
    }

    @PostMapping("/resetPassword")
    public String changePass(@RequestParam("newPassword")String newPassword,@RequestParam("reset_password_token")String token){

        NewUserToken newUserToken = newUserTokenService.findUserByToken(token);
        Users users = newUserToken.getUsers();
        userServiceImp.savePassword(newPassword,users);

        UserDetails userDetails = userDetailsServiceImp.loadUserByUsername(users.getEmail());

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,userDetails.getPassword(),userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/home";

    }

}
