package com.example.demo.controller;

import com.example.demo.controller.dto.TransactionDTO;
import com.example.demo.controller.dto.MessageDTO;
import com.example.demo.model.Transaction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/transaction")
public class BankController extends _BaseController {


    @PostMapping(value = "/create",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<MessageDTO> create(@RequestBody Transaction transaction) {
        //Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Entering create ...");
        transactionService.transferMoney(transaction.getSourceAccount().getId(), transaction.getRecipientAccount().getId(), transaction.getTransactionAmount());
        return new ResponseEntity<>(new MessageDTO("OK"), HttpStatus.OK);
    }

    @GetMapping("/allTransactionsByAccount/{accountId}")
    public @ResponseBody
    ResponseEntity<List<TransactionDTO>> allTransactionsByAccount(@PathVariable("accountId") Long accountId) {
        HttpHeaders headers = new HttpHeaders();

        logger.info("Entering list all transactions ...");

        return new ResponseEntity<>(
                transactionService.listAllTransactions(accountId).parallelStream()
                        .map(transaction -> modelMapper.map(transaction, TransactionDTO.class))
                        .collect(Collectors.toList()), HttpStatus.OK);
    }
}
