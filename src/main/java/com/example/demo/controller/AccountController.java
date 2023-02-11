package com.example.demo.controller;

import com.example.demo.controller.dto.AccountDTO;
import com.example.demo.controller.dto.MessageDTO;
import com.example.demo.model.Account;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/accountManaging")
public class AccountController extends _BaseController {

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('makeAll')")
    public ResponseEntity<MessageDTO> createAccount(Account account) {
        logger.info("Entering create account ...");
        accountService.createAccount(account.getName() , account.getBalance());
        return new ResponseEntity<>(new MessageDTO("OK"), HttpStatus.OK);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('makeAll')")
    public ResponseEntity<MessageDTO> deleteAccount(@RequestParam String name) {
        logger.info("Entering delete account ...");
        accountService.deleteAccount(name);
        return new ResponseEntity<>(new MessageDTO("OK"), HttpStatus.OK);
    }
    @GetMapping("/accountById/{accountId}")
    @PreAuthorize("hasAuthority('makeAll')")
    public @ResponseBody
    ResponseEntity<AccountDTO> accountById(@PathVariable("accountId") Long accountId) {

        logger.info("Entering searchingAccount");
        Account searchedAccount =  accountService.findById(accountId);
        return new ResponseEntity<>(
                modelMapper.map(searchedAccount, AccountDTO.class), HttpStatus.OK);
    }
    @GetMapping("/accountByName/{accountName}")
    @PreAuthorize("hasAuthority('makeAll') or hasAuthority('createTransactions')")
    public @ResponseBody
    ResponseEntity<AccountDTO> accountByName(@PathVariable("accountName") String accountName) {

        logger.info("Entering searchingAccount");
        Account searchedAccount =  accountService.findByName(accountName);
        return new ResponseEntity<>(
                modelMapper.map(searchedAccount, AccountDTO.class), HttpStatus.OK);
    }

}
