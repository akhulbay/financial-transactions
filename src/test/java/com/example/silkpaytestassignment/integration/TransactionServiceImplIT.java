package com.example.silkpaytestassignment.integration;

import com.example.silkpaytestassignment.dto.TransactionCreateDto;
import com.example.silkpaytestassignment.dto.TransactionReadDto;
import com.example.silkpaytestassignment.dto.WalletCreateDto;
import com.example.silkpaytestassignment.dto.WalletReadDto;
import com.example.silkpaytestassignment.entity.Account;
import com.example.silkpaytestassignment.repository.AccountRepository;
import com.example.silkpaytestassignment.repository.TransactionRepository;
import com.example.silkpaytestassignment.repository.WalletRepository;
import com.example.silkpaytestassignment.service.TransactionService;
import com.example.silkpaytestassignment.service.WalletService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TransactionServiceImplIT {

    private static final String ACCOUNT_FIRST_NAME = "Shyngys";
    private static final String ACCOUNT_LAST_NAME = "Akhulbay";
    private static final String ACCOUNT_EMAIL = "akhulbai8@gmail.com";
    private static final String ACCOUNT_PHONE_NUMBER = "87472877882";
    private static final Double WALLET_BALANCE = 2500D;
    private static final Double TRANSFER_SUM = 500D;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void checkCreate() {
        Account account = Account.builder()
                .firstName(ACCOUNT_FIRST_NAME)
                .lastName(ACCOUNT_LAST_NAME)
                .email(ACCOUNT_EMAIL)
                .phoneNumber(ACCOUNT_PHONE_NUMBER)
                .creationDate(LocalDate.now())
                .build();
        Account expectedAccount = accountRepository.save(account);

        WalletCreateDto walletCreateDto = new WalletCreateDto();
        walletCreateDto.setBalance(WALLET_BALANCE);
        walletCreateDto.setAccountId(expectedAccount.getId());

        WalletReadDto senderWallet = walletService.create(walletCreateDto);
        WalletReadDto recipientWallet = walletService.create(walletCreateDto);

        TransactionCreateDto transaction = new TransactionCreateDto();
        transaction.setSum(TRANSFER_SUM);
        transaction.setSenderWalletId(senderWallet.getId());
        transaction.setRecipientWalletId(recipientWallet.getId());

        TransactionReadDto actualTransaction = transactionService.create(transaction);

        assertNotNull(actualTransaction);

        assertEquals(senderWallet.getBalance() - TRANSFER_SUM,
                actualTransaction.getSenderWallet().getBalance());
        assertEquals(recipientWallet.getBalance() + TRANSFER_SUM,
                actualTransaction.getRecipientWallet().getBalance());

        accountRepository.deleteAll();
        walletRepository.deleteAll();
        transactionRepository.deleteAll();

    }


}
