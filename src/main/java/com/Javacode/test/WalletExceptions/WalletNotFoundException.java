package com.Javacode.test.WalletExceptions;

import java.util.UUID;

public class WalletNotFoundException extends RuntimeException {
    public WalletNotFoundException(UUID walletId) {
        super("Кошелек с id " + walletId + " не найден");
    }
}
