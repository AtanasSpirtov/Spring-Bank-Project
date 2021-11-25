package com.example.demo.controller;

import com.example.demo.controller.dto.MessageDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class SecurityController extends _BaseController{
    @GetMapping("/signUp")
    public ResponseEntity<MessageDTO> signIn(@RequestParam String username
            ,@RequestParam String password ,@RequestParam String email) {
        logger.info("Creating user.....");
        //authenticationService.register(username , password , email);
        return new ResponseEntity<>(new MessageDTO("User Successfully Created") , HttpStatus.OK);
    }

}
