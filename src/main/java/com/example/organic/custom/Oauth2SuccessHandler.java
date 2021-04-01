package com.example.organic.custom;

import com.example.organic.Dao.UsersDao;
import com.example.organic.Model.Role;
import com.example.organic.Model.Users;
import com.example.organic.Service.ServiceInterfaceImplementation.RoleServiceImp;
import com.example.organic.Service.ServiceInterfaceImplementation.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@Component("abc")
public class Oauth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    private RoleServiceImp roleServiceImp;

    @Autowired
    private UsersDao usersDao;

    private RedirectStrategy redirectStrategy= new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();
        Map attributes = oidcUser.getAttributes();

//        System.out.println(attributes);
//        System.out.println(given_name);
//        System.out.println(family_name);


        Users users = userServiceImp.createUser();
        Role role = roleServiceImp.createRole();

        String email = (String) attributes.get("email");

        String homeUrl = "http://localhost:8080/home2";
        String redirectionUrl = UriComponentsBuilder.fromUriString(homeUrl)
                .queryParam("email",email)
                .build().toUriString();


        if(usersDao.findByEmail(email) != null){
            this.redirectStrategy.sendRedirect(request,response,redirectionUrl);
        }

        else{

            users.setEmail((String) attributes.get("email"));
            users.setFirstName((String) attributes.get("given_name"));
            users.setLastName((String) attributes.get("family_name"));
            users.setEnable(true);

            userServiceImp.saveUser(users,role);

            this.redirectStrategy.sendRedirect(request,response,redirectionUrl);
        }

    }
}
