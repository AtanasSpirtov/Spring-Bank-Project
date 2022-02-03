package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SearchEngineController extends _BaseController{
    @GetMapping("/searchForKeyword")
    @ResponseBody
    public ResponseEntity<List<String>> searching(String word) {
        logger.info("Entering searching ...");
        return new ResponseEntity<>(searchEngineService.searchByKeyWord(word), HttpStatus.OK);
    }
}
