package com.example.wallet.repository;


import com.example.wallet.repository.entities.DepositTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositTransactionRepository extends JpaRepository<DepositTransactionEntity, String> {
}
