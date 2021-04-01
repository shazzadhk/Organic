package com.example.organic.Service.ServiceInterfaceImplementation;

import com.example.organic.Dao.UsersDao;
import com.example.organic.Model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UsersDao usersDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Users users = usersDao.findByEmail(s);

        boolean isEnable = users.isEnable();

        if (users == null)
            throw new UsernameNotFoundException("Email not found");
        else if (isEnable == false)
            throw new UsernameNotFoundException("Email not found");

        return new User(users.getEmail(),users.getPassword(),users.getAuthorities());
    }
}
