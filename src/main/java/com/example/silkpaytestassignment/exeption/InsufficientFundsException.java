package com.example.silkpaytestassignment.exeption;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException() {
        super("You don't have enough funds on your balance!");
    }
}
