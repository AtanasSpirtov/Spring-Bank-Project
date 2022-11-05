package com.example.demo.service;

import com.example.demo.model.Authority;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.api.SecurityService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

public class SecurityServiceImpl extends _BaseService implements SecurityService {

    @Override
    @Transactional
    public void createUser(User user) {
        Set<Authority> authorities = new HashSet<>();
        authorities.add(new Authority("createTransactions"));
        authorities.add(new Authority("downloadTransactions"));
        Set<Role> userRole = new HashSet<>();
        userRole.add(new Role("regularUser" , authorities));
        user.setRoles(userRole);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        em.persist(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}
