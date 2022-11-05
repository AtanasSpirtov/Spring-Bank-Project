package com.example.demo.service.api;

import com.example.demo.model.User;

public interface SecurityService {
    void createUser(User user);

    User getUserByUsername(String username);
}
