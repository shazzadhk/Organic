package com.example.organic.Model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "*First Name should not be empty")
    private String firstName;

    @NotEmpty(message = "*Last Name should not be empty")
    private String lastName;

    @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",message = "*Invalid Email Address")
    @NotEmpty(message = "*Email should not be empty")
    private String email;

//    @Size(min = 8,message = "*Password must be at least 8 characters")
    private String password;

    @Pattern(regexp = "01[0-9]{9}",message = "*Invalid Mobile Number")
    private String phoneNumber;

    private boolean isEnable = false;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "users",fetch = FetchType.EAGER)
    private List<Role> roles;

    public Collection<? extends GrantedAuthority> getAuthorities(){
        Set<GrantedAuthority> authorities = new HashSet<>();
        roles.forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getRoleName())));
        return authorities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }
}
