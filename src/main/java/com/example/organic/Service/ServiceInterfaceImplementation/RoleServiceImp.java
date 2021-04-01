package com.example.organic.Service.ServiceInterfaceImplementation;

import com.example.organic.Model.Role;
import com.example.organic.Service.ServiceInterface.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements RoleService {

    @Override
    public Role createRole() {
        return new Role();
    }
}
