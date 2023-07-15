package com.example.silkpaytestassignment.controller;

import com.example.silkpaytestassignment.dto.AccountCreateDto;
import com.example.silkpaytestassignment.dto.AccountReadDto;
import com.example.silkpaytestassignment.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<AccountReadDto> findById(@PathVariable("id") Long id) {
        AccountReadDto account = accountService.findById(id);
        return ResponseEntity.ok(account);
    }

    @PostMapping
    public ResponseEntity<AccountReadDto> create(@RequestBody AccountCreateDto account) {
        AccountReadDto newAccount = accountService.create(account);
        return new ResponseEntity<>(newAccount, CREATED);
    }
}
