package com.example.silkpaytestassignment.repository;

import com.example.silkpaytestassignment.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
