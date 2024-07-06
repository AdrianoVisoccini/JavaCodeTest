package com.Javacode.test.service;

import com.Javacode.test.dto.WalletDto;
import com.Javacode.test.dto.WalletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public interface WalletService {

    @Transactional
    void processWalletOperation(WalletRequest walletRequest);

    WalletDto getWalletBalance(UUID walletId);
}
