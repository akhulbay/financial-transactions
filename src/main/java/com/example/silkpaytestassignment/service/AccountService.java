package com.example.silkpaytestassignment.service;

import com.example.silkpaytestassignment.dto.AccountCreateDto;
import com.example.silkpaytestassignment.dto.AccountReadDto;

public interface AccountService {

    AccountReadDto findById(Long id);

    AccountReadDto create(AccountCreateDto account);
}
