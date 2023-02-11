package com.example.demo.controller;

import com.example.demo.controller.dto.TransactionDTO;
import com.example.demo.controller.dto.MessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/transaction")
public class BankController extends _BaseController {

    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('createTransactions')")

    public @ResponseBody ResponseEntity<MessageDTO> create(@RequestBody TransactionDTO transaction) {
        logger.info("Entering create ...");
        transactionService.transferMoney(transaction.getSourceAccount().getId(), transaction.getRecipientAccount().getId(), transaction.getTransactionAmount());
        return new ResponseEntity<>(new MessageDTO("OK"), HttpStatus.OK);
    }

    @GetMapping("/allTransactionsByAccount/{accountId}")
    @PreAuthorize("hasAuthority('makeAll')")
    public @ResponseBody ResponseEntity<List<TransactionDTO>> allTransactionsByAccount(@PathVariable("accountId") Long accountId) {

        logger.info("Entering list all transactions ...");

        return new ResponseEntity<>(
                transactionService.listAllTransactions(accountId).parallelStream()
                        .map(transaction -> modelMapper.map(transaction, TransactionDTO.class))
                        .toList(), HttpStatus.OK);
    }
}
