package com.example.demo.controller;

import com.example.demo.controller.dto.TransactionDTO;
import com.example.demo.controller.dto.MessageDTO;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/transaction")
public class BankController extends _BaseController {


    @GetMapping("/create")
    public ResponseEntity<MessageDTO> create(@RequestParam Long accountFromId, @RequestParam Long accountToId
            , @RequestParam BigDecimal amount) {
        //Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Entering create ...");
        transactionService.transferMoney(accountFromId, accountToId, amount);
        return new ResponseEntity<>(new MessageDTO("OK"), HttpStatus.OK);
    }

    @PostMapping("/allTransactionsByAccount")
    public @ResponseBody
    ResponseEntity<List<TransactionDTO>> allTransactionsByAccount(@RequestParam Long accountId) {
        HttpHeaders headers = new HttpHeaders();

        logger.info("Entering list all transactions ...");

        return new ResponseEntity<>(
                transactionService.listAllTransactions(accountId).parallelStream()
                        .map(transaction -> modelMapper.map(transaction, TransactionDTO.class))
                        .collect(Collectors.toList()), HttpStatus.OK);
    }
}
