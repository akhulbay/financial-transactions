package com.example.silkpaytestassignment.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCreateDto {

    private Double sum;
    private Long senderWalletId;
    private Long recipientWalletId;
}
