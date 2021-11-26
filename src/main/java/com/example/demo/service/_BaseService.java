package com.example.demo.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class _BaseService {

    @PersistenceContext
    protected EntityManager em;

    public EntityManager getEntityManager() {
        return this.em;
    }

}
