package com.Javacode.test.repository;

import com.Javacode.test.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {
}
