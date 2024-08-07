package com.majumundur.maju_mundur_shop.controller;

import com.majumundur.maju_mundur_shop.entity.Transaction;
import com.majumundur.maju_mundur_shop.entity.UserAccount;
import com.majumundur.maju_mundur_shop.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody Transaction transaction, @AuthenticationPrincipal UserAccount user) {
        // Ensure all required fields are set
        if (transaction.getAmount() == null || transaction.getAmount() <= 0) {
            return ResponseEntity.badRequest().body("Invalid amount.");
        }

        // Set the customer and transaction time
        transaction.setCustomer(user);
        transaction.setTransactionTime(LocalDateTime.now());

        // Save the transaction
        Transaction savedTransaction = transactionService.saveTransaction(transaction);
        return ResponseEntity.ok(savedTransaction);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions(@AuthenticationPrincipal UserAccount user) {
        List<Transaction> transactions = transactionService.getTransactionsByCustomer(user.getId());
        return ResponseEntity.ok(transactions);
    }
}
