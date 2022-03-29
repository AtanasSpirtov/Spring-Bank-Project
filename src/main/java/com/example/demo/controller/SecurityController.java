package com.example.demo.controller;

import com.example.demo.controller.dto.MessageDTO;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SecurityController extends _BaseController{

    @GetMapping("/login")
    @ResponseBody public ResponseEntity<MessageDTO> login() {

        logger.info("Entering login ...");
        return new ResponseEntity<>(new MessageDTO("Authenticated Successfully"), HttpStatus.OK);
    }

    @GetMapping("/loggingOut")
    @ResponseBody public ResponseEntity<MessageDTO> logout() {

        logger.info("Entering logout ...");
        SecurityContextHolder.getContext().setAuthentication(null);
        return new ResponseEntity<>(new MessageDTO("Logged out Successfully"), HttpStatus.OK);
    }

    @PostMapping("/signUp")
    @ResponseBody public ResponseEntity<MessageDTO> signUp(@RequestBody UserDTO userDTO) {
        logger.info("Entering signingUp ...");
        usrService.signedUpUser(new User(userDTO.getUsername() , userDTO.getEmail() , userDTO.getPassword()));
        return new ResponseEntity<>(new MessageDTO("SignedUp Successfully"), HttpStatus.OK);
    }
}
