package com.example.silkpaytestassignment.service.impl;

import com.example.silkpaytestassignment.dto.TransactionCreateDto;
import com.example.silkpaytestassignment.dto.TransactionReadDto;
import com.example.silkpaytestassignment.entity.Wallet;
import com.example.silkpaytestassignment.mapper.TransactionCreateMapper;
import com.example.silkpaytestassignment.mapper.TransactionReadMapper;
import com.example.silkpaytestassignment.repository.TransactionRepository;
import com.example.silkpaytestassignment.repository.WalletRepository;
import com.example.silkpaytestassignment.service.TransactionService;
import com.example.silkpaytestassignment.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    private final WalletService walletService;

    @Transactional
    @Override
    public TransactionReadDto create(TransactionCreateDto transaction) {
        return Optional.of(transaction)
                .map(TransactionCreateMapper.INSTANCE::toEntity)
                .map(entity -> {
                    walletService.transfer(transaction.getSenderWalletId(), transaction.getSum());
                    walletService.deposit(transaction.getRecipientWalletId(), transaction.getSum());
                    return entity;
                })
                .map(entity -> {
                    entity.setDateTime(LocalDateTime.now());
                    entity.setSenderWallet(getWalletById(transaction.getSenderWalletId()));
                    entity.setRecipientWallet(getWalletById(transaction.getRecipientWalletId()));
                    return entity;

                })
                .map(transactionRepository::save)
                .map(TransactionReadMapper.INSTANCE::toDto)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST));
    }

    private Wallet getWalletById(Long accountId) {
        return walletRepository.findById(accountId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }
}
