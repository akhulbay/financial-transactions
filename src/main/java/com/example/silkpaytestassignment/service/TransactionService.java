package com.example.silkpaytestassignment.service;

import com.example.silkpaytestassignment.dto.TransactionCreateDto;
import com.example.silkpaytestassignment.dto.TransactionReadDto;

public interface TransactionService {


    TransactionReadDto create(TransactionCreateDto transaction);
}
