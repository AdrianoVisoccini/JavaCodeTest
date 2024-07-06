package com.Javacode.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class WalletRequest {

    public enum OperationType {
        DEPOSIT, WITHDRAW
    }

    private UUID valletId;
    private OperationType operationType;
    private BigDecimal amount;
}
