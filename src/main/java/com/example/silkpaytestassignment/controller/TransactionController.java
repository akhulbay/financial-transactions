package com.example.silkpaytestassignment.controller;

import com.example.silkpaytestassignment.dto.TransactionCreateDto;
import com.example.silkpaytestassignment.dto.TransactionReadDto;
import com.example.silkpaytestassignment.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionReadDto> create(@RequestBody TransactionCreateDto transaction) {
        TransactionReadDto newTransaction = transactionService.create(transaction);
        return new ResponseEntity<>(newTransaction, CREATED);
    }
}
