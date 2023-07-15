package com.example.silkpaytestassignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletReadDto {

    private Long id;
    private Double balance;
    private LocalDate creationDate;
    private AccountReadDto account;
}
