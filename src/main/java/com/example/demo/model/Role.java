package com.example.demo.model;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role extends _BaseEntity {

    String roleName;

    @ManyToMany(targetEntity = Authority.class , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private Set<Authority> authority;

    public Role() {
    }
    public Role(String roleName , Set<Authority> authorities) {
        this.roleName = roleName;
        this.authority = new HashSet<>(authorities);
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
