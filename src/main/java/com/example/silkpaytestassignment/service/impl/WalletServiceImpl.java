package com.example.silkpaytestassignment.service.impl;

import com.example.silkpaytestassignment.dto.WalletBalanceReadDto;
import com.example.silkpaytestassignment.dto.WalletCreateDto;
import com.example.silkpaytestassignment.dto.WalletReadDto;
import com.example.silkpaytestassignment.entity.Account;
import com.example.silkpaytestassignment.exeption.InsufficientFundsException;
import com.example.silkpaytestassignment.mapper.WalletBalanceReadMapper;
import com.example.silkpaytestassignment.mapper.WalletCreateMapper;
import com.example.silkpaytestassignment.mapper.WalletReadMapper;
import com.example.silkpaytestassignment.repository.AccountRepository;
import com.example.silkpaytestassignment.repository.WalletRepository;
import com.example.silkpaytestassignment.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final AccountRepository accountRepository;

    @Override
    public WalletReadDto findById(Long id) {
        return walletRepository.findById(id)
                .map(WalletReadMapper.INSTANCE::toDto)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    @Override
    public WalletBalanceReadDto findBalanceById(Long id) {
        return walletRepository.findById(id)
                .map(WalletBalanceReadMapper.INSTANCE::toDto)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    @Transactional
    @Override
    public WalletReadDto create(WalletCreateDto wallet) {
        return Optional.of(wallet)
                .map(WalletCreateMapper.INSTANCE::toEntity)
                .map(entity -> {
                    entity.setCreationDate(LocalDate.now());
                    entity.setAccount(getAccountById(wallet.getAccountId()));
                    return entity;
                })
                .map(walletRepository::save)
                .map(WalletReadMapper.INSTANCE::toDto)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST));
    }

    @Transactional
    @Override
    public void transfer(Long id, Double sum) {
        walletRepository.findById(id)
                .map(entity -> {
                    Double finalBalance = getFinalBalance(entity.getBalance(), sum);
                    entity.setBalance(finalBalance);
                    return entity;
                })
                .map(walletRepository::saveAndFlush)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    @Transactional
    @Override
    public void deposit(Long id, Double sum) {
        walletRepository.findById(id)
                .map(entity -> {
                    double finalBalance = sum + entity.getBalance();
                    entity.setBalance(finalBalance);
                    return entity;
                })
                .map(walletRepository::saveAndFlush)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    private Double getFinalBalance(Double currentSum, Double transferSum) {
        double finalBalance = currentSum - transferSum;
        if (finalBalance < 0) {
            throw new InsufficientFundsException();
        }
        return finalBalance;
    }

    private Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }
}
