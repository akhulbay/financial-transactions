package com.example.silkpaytestassignment.controller;

import com.example.silkpaytestassignment.dto.WalletBalanceReadDto;
import com.example.silkpaytestassignment.dto.WalletCreateDto;
import com.example.silkpaytestassignment.dto.WalletReadDto;
import com.example.silkpaytestassignment.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @GetMapping("/{id}")
    public ResponseEntity<WalletReadDto> findById(@PathVariable("id") Long id) {
        WalletReadDto wallet = walletService.findById(id);
        return ResponseEntity.ok(wallet);
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<WalletBalanceReadDto> findBalanceById(@PathVariable("id") Long id) {
        WalletBalanceReadDto balance = walletService.findBalanceById(id);
        return ResponseEntity.ok(balance);
    }

    @PostMapping
    public ResponseEntity<WalletReadDto> create(WalletCreateDto wallet) {
        WalletReadDto newWallet = walletService.create(wallet);
        return new ResponseEntity<>(newWallet, CREATED);
    }
}
