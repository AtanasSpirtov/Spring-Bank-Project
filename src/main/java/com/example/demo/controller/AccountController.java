package com.example.demo.controller;

import com.example.demo.controller.dto.AccountDTO;
import com.example.demo.controller.dto.MessageDTO;
import com.example.demo.controller.dto.TransactionDTO;
import com.example.demo.model.Account;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/accountManaging")
public class AccountController extends _BaseController {
    @GetMapping("/create")
    public ResponseEntity<MessageDTO> createAccount(@RequestParam String name, @RequestParam BigDecimal balance) {
        logger.info("Entering create account ...");
        accountService.createAccount(name , balance);
        return new ResponseEntity<>(new MessageDTO("OK"), HttpStatus.OK);
    }

    @GetMapping("/delete")
    public ResponseEntity<MessageDTO> deleteAccount(@RequestParam Long id) {
        logger.info("Entering delete account ...");
        accountService.deleteAccount(id);
        return new ResponseEntity<>(new MessageDTO("OK"), HttpStatus.OK);
    }
    @PostMapping("/AccountById")
    public @ResponseBody
    ResponseEntity<AccountDTO> accountById(@RequestParam Long accountId) {

        logger.info("Entering searchingAccount");
        Account searchedAccount =  accountService.findById(accountId);
        return new ResponseEntity<>(
                modelMapper.map(searchedAccount, AccountDTO.class), HttpStatus.OK);
    }

}
