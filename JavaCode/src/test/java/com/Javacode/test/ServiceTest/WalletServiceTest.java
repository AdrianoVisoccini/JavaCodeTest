package com.Javacode.test.ServiceTest;

import com.Javacode.test.WalletExceptions.InsufficientFundsException;
import com.Javacode.test.WalletExceptions.WalletNotFoundException;
import com.Javacode.test.dto.WalletDto;
import com.Javacode.test.dto.WalletRequest;
import com.Javacode.test.model.Wallet;
import com.Javacode.test.repository.WalletRepository;
import com.Javacode.test.service.WalletServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class WalletServiceTest {

    @Mock
    private WalletRepository walletRepository;

    @InjectMocks
    private WalletServiceImpl walletService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcessWalletOperation_Deposit() {
        UUID walletId = UUID.randomUUID();
        BigDecimal initialBalance = BigDecimal.valueOf(100);
        BigDecimal depositAmount = BigDecimal.valueOf(50);
        WalletRequest request = new WalletRequest(walletId, WalletRequest.OperationType.DEPOSIT, depositAmount);

        Wallet wallet = new Wallet(walletId, initialBalance);
        when(walletRepository.findById(walletId)).thenReturn(Optional.of(wallet));

        walletService.processWalletOperation(request);

        BigDecimal expectedBalance = initialBalance.add(depositAmount);
        assert wallet.getBalance().equals(expectedBalance);
        verify(walletRepository, times(1)).save(wallet);
    }

    @Test
    public void testProcessWalletOperation_Withdraw() {
        UUID walletId = UUID.randomUUID();
        BigDecimal initialBalance = BigDecimal.valueOf(100);
        BigDecimal withdrawAmount = BigDecimal.valueOf(50);
        WalletRequest request = new WalletRequest(walletId, WalletRequest.OperationType.WITHDRAW, withdrawAmount);

        Wallet wallet = new Wallet(walletId, initialBalance);
        when(walletRepository.findById(walletId)).thenReturn(Optional.of(wallet));

        walletService.processWalletOperation(request);

        BigDecimal expectedBalance = initialBalance.subtract(withdrawAmount);
        assert wallet.getBalance().equals(expectedBalance);
        verify(walletRepository, times(1)).save(wallet);
    }

    @Test
    public void testProcessWalletOperation_InsufficientFunds() {
        UUID walletId = UUID.randomUUID();
        BigDecimal initialBalance = BigDecimal.valueOf(50);
        BigDecimal withdrawAmount = BigDecimal.valueOf(100);
        WalletRequest request = new WalletRequest(walletId, WalletRequest.OperationType.WITHDRAW, withdrawAmount);

        Wallet wallet = new Wallet(walletId, initialBalance);
        when(walletRepository.findById(walletId)).thenReturn(Optional.of(wallet));

        assertThrows(InsufficientFundsException.class, () -> walletService.processWalletOperation(request));
        verify(walletRepository, never()).save(wallet);
    }

    @Test
    public void testProcessWalletOperation_WalletNotFound() {
        UUID walletId = UUID.randomUUID();
        BigDecimal amount = BigDecimal.valueOf(50);
        WalletRequest request = new WalletRequest(walletId, WalletRequest.OperationType.DEPOSIT, amount);

        when(walletRepository.findById(walletId)).thenReturn(Optional.empty());

        assertThrows(WalletNotFoundException.class, () -> walletService.processWalletOperation(request));
        verify(walletRepository, never()).save(any());
    }

    @Test
    public void testGetWalletBalance() {
        UUID walletId = UUID.randomUUID();
        BigDecimal initialBalance = BigDecimal.valueOf(100);
        Wallet wallet = new Wallet(walletId, initialBalance);
        when(walletRepository.findById(walletId)).thenReturn(Optional.of(wallet));

        WalletDto walletDto = walletService.getWalletBalance(walletId);

        assertEquals(walletId, walletDto.getId());
        assertEquals(initialBalance, walletDto.getBalance());
    }

}
