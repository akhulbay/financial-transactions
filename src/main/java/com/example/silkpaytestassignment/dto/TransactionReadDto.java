package com.example.silkpaytestassignment.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionReadDto {

    private Long id;
    private Double sum;
    private LocalDateTime dateTime;
    private WalletReadDto senderWallet;
    private WalletReadDto recipientWallet;
}
