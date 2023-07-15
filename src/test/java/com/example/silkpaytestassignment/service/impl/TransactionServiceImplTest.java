package com.example.silkpaytestassignment.service.impl;

import com.example.silkpaytestassignment.dto.TransactionCreateDto;
import com.example.silkpaytestassignment.dto.TransactionReadDto;
import com.example.silkpaytestassignment.entity.Transaction;
import com.example.silkpaytestassignment.entity.Wallet;
import com.example.silkpaytestassignment.repository.TransactionRepository;
import com.example.silkpaytestassignment.repository.WalletRepository;
import com.example.silkpaytestassignment.service.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class TransactionServiceImplTest {

    private static final Double TRANSFER_SUM = 100D;
    private static final Long SENDER_WALLET_ID = 1L;
    private static final Long RECIPIENT_WALLET_ID = 2L;
    private static final Long TRANSACTION_ID = 1L;


    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private WalletRepository walletRepository;

    @Mock
    private WalletService walletService;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_ValidTransactionCreateDto_ReturnsTransactionReadDto() {
        TransactionCreateDto transactionCreateDto = new TransactionCreateDto();
        transactionCreateDto.setSum(TRANSFER_SUM);
        transactionCreateDto.setSenderWalletId(SENDER_WALLET_ID);
        transactionCreateDto.setRecipientWalletId(RECIPIENT_WALLET_ID);

        Wallet senderWallet = new Wallet();
        senderWallet.setId(transactionCreateDto.getSenderWalletId());

        Wallet recipientWallet = new Wallet();
        recipientWallet.setId(transactionCreateDto.getRecipientWalletId());

        Transaction transaction = new Transaction();
        transaction.setId(TRANSACTION_ID);
        transaction.setSum(transactionCreateDto.getSum());
        transaction.setDateTime(LocalDateTime.now());
        transaction.setSenderWallet(senderWallet);
        transaction.setRecipientWallet(recipientWallet);

        doNothing().when(walletService).transfer(transactionCreateDto.getSenderWalletId(),
                transactionCreateDto.getSum());
        doNothing().when(walletService).deposit(transactionCreateDto.getRecipientWalletId(),
                transactionCreateDto.getSum());
        when(walletRepository.findById(transactionCreateDto.getSenderWalletId()))
                .thenReturn(Optional.of(senderWallet));
        when(walletRepository.findById(transactionCreateDto.getRecipientWalletId()))
                .thenReturn(Optional.of(recipientWallet));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        TransactionReadDto result = transactionService.create(transactionCreateDto);

        assertNotNull(result);
        assertEquals(transaction.getId(), result.getId());
        assertEquals(transaction.getSum(), result.getSum());
        assertEquals(transaction.getDateTime(), result.getDateTime());
        assertNotNull(result.getSenderWallet());
        assertEquals(transaction.getSenderWallet().getId(), result.getSenderWallet().getId());
        assertNotNull(result.getRecipientWallet());
        assertEquals(transaction.getRecipientWallet().getId(), result.getRecipientWallet().getId());
    }
}