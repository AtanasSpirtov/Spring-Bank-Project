package com.example.demo.model;

import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role extends _BaseEntity {

    String roleName;

    @ManyToMany(targetEntity = Authority.class , fetch = FetchType.EAGER)
    private Set<Authority> authority;

    public Role() {
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Authority> getAuthority() {
        return authority;
    }

    public void setAuthority(Set<Authority> authority) {
        this.authority = authority;
    }
}
