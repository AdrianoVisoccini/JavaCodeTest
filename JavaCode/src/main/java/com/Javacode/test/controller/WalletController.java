package com.Javacode.test.controller;

import com.Javacode.test.dto.WalletDto;
import com.Javacode.test.dto.WalletRequest;
import com.Javacode.test.service.WalletService;
import com.Javacode.test.service.WalletServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {

    private final WalletServiceImpl walletService;

    @Autowired
    private WalletController(WalletServiceImpl walletService){
        this.walletService = walletService;

    }
     @PostMapping
    public ResponseEntity<String> handleWalletOperation(@Validated @RequestBody WalletRequest walletRequest) {
        walletService.processWalletOperation(walletRequest);
        return ResponseEntity.ok("Операция выполнена");
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<WalletDto> getWalletBalance(@PathVariable UUID walletId) {
        WalletDto walletDto = walletService.getWalletBalance(walletId);
        return ResponseEntity.ok(walletDto);
    }
}
