package com.example.demo.controller;

import com.example.demo.model.Account;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SearchEngineController extends _BaseController{
    @GetMapping("/searchForKeyword/{word}")
    public @ResponseBody ResponseEntity<List<Account>> searching(@PathVariable String word) {
        logger.info("Entering searching ...");

        return new ResponseEntity<>(searchEngineService.searchAccountByName(word), HttpStatus.OK);
    }
}
