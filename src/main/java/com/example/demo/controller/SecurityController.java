package com.example.demo.controller;

import com.example.demo.controller.dto.MessageDTO;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class SecurityController extends _BaseController{

    @PostMapping("/create")
    public void saveUserWithDefaultPermissions(@RequestBody UserDTO userDTO){
        logger.info("Adding new user {}", userDTO);
        User newUser =  new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail());
        userService.createUser(newUser);
    }
    @GetMapping("/login")
    public ResponseEntity<MessageDTO> successfulAuthentication(){
        return ResponseEntity.ok(new MessageDTO("Succesfull Authentication"));
    }
}
