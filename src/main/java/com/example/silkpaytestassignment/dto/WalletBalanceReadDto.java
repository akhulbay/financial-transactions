package com.example.silkpaytestassignment.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletBalanceReadDto {

    private Long id;
    private Double balance;
}
