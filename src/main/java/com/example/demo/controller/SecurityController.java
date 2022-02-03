package com.example.demo.controller;

import com.example.demo.controller.dto.MessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SecurityController extends _BaseController{
    @GetMapping("/login")
    @ResponseBody public ResponseEntity<MessageDTO> login() {
        logger.info("Entering login ...");
        return new ResponseEntity<>(new MessageDTO("Authenticated Successfully"), HttpStatus.OK);
    }
}
