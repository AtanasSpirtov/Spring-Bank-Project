package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "authorities")
public class Authority extends _BaseEntity {

    private String authorityName;

    public Authority() {}

    public Authority(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }
}
