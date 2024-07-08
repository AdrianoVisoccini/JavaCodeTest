package com.Javacode.test.WalletExceptions;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException() {
        super("Недостаточно средств на кошельке для выполнения операции");
    }
}
