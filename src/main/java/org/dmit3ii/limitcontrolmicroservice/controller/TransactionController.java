package org.dmit3ii.limitcontrolmicroservice.controller;

import lombok.AllArgsConstructor;
import org.dmit3ii.limitcontrolmicroservice.model.Transaction;
import org.dmit3ii.limitcontrolmicroservice.model.TransactionDTO;
import org.dmit3ii.limitcontrolmicroservice.model.mapper.TransactionMapper;
import org.dmit3ii.limitcontrolmicroservice.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/transactions")
@AllArgsConstructor
public class TransactionController {

    private TransactionService transactionService;
    private final TransactionMapper transactionMapper;


    @PostMapping
    public ResponseEntity<Transaction> addTransaction(@RequestBody TransactionDTO transactionDTO) {
        Transaction transaction = transactionMapper.toTransaction(transactionDTO);
        return new ResponseEntity<>(transactionService.saveTransaction(transaction), HttpStatus.CREATED);
    }


}
