package com.example.demo.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User extends _BaseEntity {

    private String username;

    private String password;

    private String email;

    @ManyToMany(targetEntity = Role.class ,fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private Set<Role> roles;

    @OneToOne
    private Account account;

    public User(String username , String password , String email) {
        this.password = password;
        this.username = username;
        this.email = email;
    }

    public User() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {return password;}

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
