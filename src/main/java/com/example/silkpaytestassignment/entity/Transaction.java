package com.example.silkpaytestassignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double sum;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @ManyToOne
    private Wallet senderWallet;

    @ManyToOne
    private Wallet recipientWallet;
}
