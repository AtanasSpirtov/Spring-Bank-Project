package com.example.demo.service;

import com.example.demo.model.Authority;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.api.UserSignService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

public class UserSignServiceImpl extends _BaseService implements UserSignService {

    @Override
    @Transactional
    public void signedUpUser(User user) {
        Set<Authority> authorities = new HashSet<>();
        authorities.add(new Authority("createTransactions"));
        Set<Role> userRole = new HashSet<>();
        userRole.add(new Role("regularUser" , authorities));
        user.setRoles(userRole);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        em.persist(user);
    }
}
