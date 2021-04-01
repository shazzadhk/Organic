package com.example.organic.Model;


import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
public class NewUserToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String token;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",nullable = false)
    private Users users;

    private Date expirydate;

    public NewUserToken() {
    }

    public NewUserToken(String token, Users users) {
        this.token = token;
        this.users = users;
        this.expirydate = calculateExpiryDate(60*24);
    }

    private Date calculateExpiryDate(final int expiryInMinutes) {

        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE,expiryInMinutes);
        return new Date(calendar.getTime().getTime());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Date getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(Date expirydate) {
        this.expirydate = expirydate;
    }

    public boolean isExpired() {
        return new Date().after(this.expirydate);
    }
}
