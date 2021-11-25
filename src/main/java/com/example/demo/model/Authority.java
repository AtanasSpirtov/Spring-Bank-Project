package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "authorities")
public class Authority extends _BaseEntity {

    private String authorityName;
}
