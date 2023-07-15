package com.example.silkpaytestassignment.service.impl;

import com.example.silkpaytestassignment.dto.AccountCreateDto;
import com.example.silkpaytestassignment.dto.AccountReadDto;
import com.example.silkpaytestassignment.mapper.AccountCreateMapper;
import com.example.silkpaytestassignment.mapper.AccountReadMapper;
import com.example.silkpaytestassignment.repository.AccountRepository;
import com.example.silkpaytestassignment.service.AccountService;
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
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public AccountReadDto findById(Long id) {
        return accountRepository.findById(id)
                .map(AccountReadMapper.INSTANCE::toDto)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    @Transactional
    @Override
    public AccountReadDto create(AccountCreateDto account) {
        return Optional.of(account)
                .map(AccountCreateMapper.INSTANCE::toEntity)
                .map(entity -> {
                    entity.setCreationDate(LocalDate.now());
                    return entity;
                })
                .map(accountRepository::save)
                .map(AccountReadMapper.INSTANCE::toDto)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST));
    }
}
