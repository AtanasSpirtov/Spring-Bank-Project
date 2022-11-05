package com.example.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
public class User extends _BaseEntity implements Serializable {

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

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<? extends GrantedAuthority> getRolesAsSimpleGrantedAuthorities() {
        return this.roles.stream().flatMap(role ->
                role.getAuthority().stream().map(authority ->
                        new SimpleGrantedAuthority(authority.getAuthorityName()))).toList();
    }
}
