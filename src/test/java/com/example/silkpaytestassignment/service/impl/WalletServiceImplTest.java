package com.example.silkpaytestassignment.service.impl;

import com.example.silkpaytestassignment.dto.WalletBalanceReadDto;
import com.example.silkpaytestassignment.dto.WalletCreateDto;
import com.example.silkpaytestassignment.dto.WalletReadDto;
import com.example.silkpaytestassignment.entity.Account;
import com.example.silkpaytestassignment.entity.Wallet;
import com.example.silkpaytestassignment.repository.AccountRepository;
import com.example.silkpaytestassignment.repository.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class WalletServiceImplTest {


    private static final Long ACCOUNT_ID = 1L;
    private static final Long WALLET_ID = 1L;
    private static final Double TRANSACTION_SUM = 50D;
    private static final Double DEPOSIT_SUM = 50D;
    private static final Double INITIAL_SUM = 100D;


    @Mock
    private WalletRepository walletRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private WalletServiceImpl walletService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void checkFindById() {
        Wallet wallet = new Wallet();
        wallet.setId(WALLET_ID);
        when(walletRepository.findById(WALLET_ID)).thenReturn(Optional.of(wallet));

        WalletReadDto result = walletService.findById(WALLET_ID);

        assertNotNull(result);
        assertEquals(WALLET_ID, result.getId());
    }

    @Test
    void checkFindBalanceById() {
        Wallet wallet = new Wallet();
        wallet.setId(WALLET_ID);
        when(walletRepository.findById(WALLET_ID)).thenReturn(Optional.of(wallet));

        WalletBalanceReadDto result = walletService.findBalanceById(WALLET_ID);

        assertNotNull(result);
        assertEquals(WALLET_ID, result.getId());
    }

    @Test
    void checkCreate() {
        WalletCreateDto walletCreateDto = new WalletCreateDto();
        walletCreateDto.setBalance(INITIAL_SUM);
        walletCreateDto.setAccountId(ACCOUNT_ID);

        Account account = new Account();
        account.setId(walletCreateDto.getAccountId());

        Wallet wallet = new Wallet();
        wallet.setId(WALLET_ID);
        wallet.setBalance(walletCreateDto.getBalance());
        wallet.setCreationDate(LocalDate.now());
        wallet.setAccount(account);

        when(walletRepository.save(any(Wallet.class))).thenReturn(wallet);
        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));

        WalletReadDto result = walletService.create(walletCreateDto);

        assertNotNull(result);
        assertEquals(wallet.getId(), result.getId());
        assertEquals(wallet.getBalance(), result.getBalance());
        assertEquals(wallet.getCreationDate(), result.getCreationDate());
        assertNotNull(result.getAccount());
        assertEquals(wallet.getAccount().getId(), result.getAccount().getId());
    }


    @Test
    void checkTransfer() {
        Wallet wallet = new Wallet();
        wallet.setId(WALLET_ID);
        wallet.setBalance(INITIAL_SUM);

        when(walletRepository.findById(WALLET_ID)).thenReturn(Optional.of(wallet));
        when(walletRepository.saveAndFlush(any(Wallet.class))).thenReturn(wallet);

        walletService.transfer(WALLET_ID, TRANSACTION_SUM);

        assertEquals(INITIAL_SUM - TRANSACTION_SUM, wallet.getBalance());
    }


    @Test
    void checkDeposit() {
        Wallet wallet = new Wallet();
        wallet.setId(WALLET_ID);
        wallet.setBalance(INITIAL_SUM);

        when(walletRepository.findById(WALLET_ID)).thenReturn(Optional.of(wallet));
        when(walletRepository.saveAndFlush(any(Wallet.class))).thenReturn(wallet);

        walletService.deposit(WALLET_ID, DEPOSIT_SUM);

        assertEquals(INITIAL_SUM + DEPOSIT_SUM, wallet.getBalance());
    }

}