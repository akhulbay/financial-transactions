package com.example.silkpaytestassignment.integration;

import com.example.silkpaytestassignment.dto.WalletBalanceReadDto;
import com.example.silkpaytestassignment.dto.WalletCreateDto;
import com.example.silkpaytestassignment.dto.WalletReadDto;
import com.example.silkpaytestassignment.entity.Account;
import com.example.silkpaytestassignment.entity.Wallet;
import com.example.silkpaytestassignment.repository.AccountRepository;
import com.example.silkpaytestassignment.repository.WalletRepository;
import com.example.silkpaytestassignment.service.WalletService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class WalletServiceImplIT {

    private static final String ACCOUNT_FIRST_NAME = "Shyngys";
    private static final String ACCOUNT_LAST_NAME = "Akhulbay";
    private static final String ACCOUNT_EMAIL = "akhulbai8@gmail.com";
    private static final String ACCOUNT_PHONE_NUMBER = "87472877882";
    private static final Double WALLET_BALANCE = 2500D;
    private static final Double TRANSFER_SUM = 500D;


    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private WalletService walletService;

    @Test
    void checkFindById() {
        Account account = Account.builder()
                .firstName(ACCOUNT_FIRST_NAME)
                .lastName(ACCOUNT_LAST_NAME)
                .email(ACCOUNT_EMAIL)
                .phoneNumber(ACCOUNT_PHONE_NUMBER)
                .creationDate(LocalDate.now())
                .build();
        Account expectedAccount = accountRepository.save(account);

        Wallet wallet = Wallet.builder()
                .balance(WALLET_BALANCE)
                .account(expectedAccount)
                .creationDate(LocalDate.now())
                .build();

        Wallet expectedWallet = walletRepository.save(wallet);

        WalletReadDto actualWallet = walletService.findById(expectedWallet.getId());

        assertNotNull(actualWallet);
        assertEquals(expectedWallet.getBalance(), actualWallet.getBalance());
        assertEquals(expectedWallet.getCreationDate(), actualWallet.getCreationDate());
        assertEquals(expectedAccount.getFirstName(), actualWallet.getAccount().getFirstName());
        assertEquals(expectedAccount.getLastName(), actualWallet.getAccount().getLastName());
        assertEquals(expectedAccount.getEmail(), actualWallet.getAccount().getEmail());
        assertEquals(expectedAccount.getPhoneNumber(), actualWallet.getAccount().getPhoneNumber());

        accountRepository.deleteAll();
        walletRepository.deleteAll();
    }

    @Test
    void checkFindBalanceById() {
        Account account = Account.builder()
                .firstName(ACCOUNT_FIRST_NAME)
                .lastName(ACCOUNT_LAST_NAME)
                .email(ACCOUNT_EMAIL)
                .phoneNumber(ACCOUNT_PHONE_NUMBER)
                .creationDate(LocalDate.now())
                .build();
        Account expectedAccount = accountRepository.save(account);

        Wallet wallet = Wallet.builder()
                .balance(WALLET_BALANCE)
                .account(expectedAccount)
                .creationDate(LocalDate.now())
                .build();

        Wallet expectedWallet = walletRepository.save(wallet);

        WalletBalanceReadDto actualWalletBalance = walletService.findBalanceById(expectedWallet.getId());

        assertNotNull(actualWalletBalance);
        assertEquals(expectedWallet.getBalance(), actualWalletBalance.getBalance());

        accountRepository.deleteAll();
        walletRepository.deleteAll();
    }

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

        WalletReadDto actualWallet = walletService.create(walletCreateDto);

        assertNotNull(actualWallet);
        assertEquals(walletCreateDto.getBalance(), actualWallet.getBalance());
        assertEquals(expectedAccount.getFirstName(), actualWallet.getAccount().getFirstName());
        assertEquals(expectedAccount.getLastName(), actualWallet.getAccount().getLastName());
        assertEquals(expectedAccount.getEmail(), actualWallet.getAccount().getEmail());
        assertEquals(expectedAccount.getPhoneNumber(), actualWallet.getAccount().getPhoneNumber());
        assertEquals(expectedAccount.getId(), actualWallet.getAccount().getId());

        walletRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @Test
    void checkTransfer() {
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

        walletService.transfer(senderWallet.getId(), TRANSFER_SUM);

        WalletReadDto actualSenderWallet = walletService.findById(senderWallet.getId());

        assertEquals((senderWallet.getBalance() - TRANSFER_SUM), actualSenderWallet.getBalance());

        accountRepository.deleteAll();
        walletRepository.deleteAll();
    }

    @Test
    void checkDeposit() {
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

        WalletReadDto recipientWallet = walletService.create(walletCreateDto);

        walletService.deposit(recipientWallet.getId(), TRANSFER_SUM);

        WalletReadDto actualRecipientWallet = walletService.findById(recipientWallet.getId());

        assertEquals((recipientWallet.getBalance() + TRANSFER_SUM), actualRecipientWallet.getBalance());

        accountRepository.deleteAll();
        walletRepository.deleteAll();
    }

}
