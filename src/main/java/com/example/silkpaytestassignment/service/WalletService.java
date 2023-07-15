package com.example.silkpaytestassignment.service;

import com.example.silkpaytestassignment.dto.WalletBalanceReadDto;
import com.example.silkpaytestassignment.dto.WalletCreateDto;
import com.example.silkpaytestassignment.dto.WalletReadDto;

public interface WalletService {

    WalletReadDto findById(Long id);

    WalletBalanceReadDto findBalanceById(Long id);

    WalletReadDto create(WalletCreateDto wallet);

    void transfer(Long id, Double sum);

    void deposit(Long id, Double sum);
}
