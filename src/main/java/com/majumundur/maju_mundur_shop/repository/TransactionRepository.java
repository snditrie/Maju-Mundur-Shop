package com.majumundur.maju_mundur_shop.repository;

import com.majumundur.maju_mundur_shop.entity.Transaction;
import com.majumundur.maju_mundur_shop.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCustomerId(Long customerId);
}
