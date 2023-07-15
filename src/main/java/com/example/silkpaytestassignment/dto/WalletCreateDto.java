package com.example.silkpaytestassignment.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletCreateDto {

    private Double balance;
    private Long accountId;
}
