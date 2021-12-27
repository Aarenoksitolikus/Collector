package me.akvelon.collector.controllers;

import me.akvelon.collector.exceptions.TransactionNotFoundException;
import me.akvelon.collector.models.Transaction;
import me.akvelon.collector.services.interfaces.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {
    @Autowired
    private TransactionsService transactionsService;

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionsService.getAll());
    }

    @GetMapping("/last")
    public ResponseEntity<List<Transaction>> getLastTransactions() {
        return ResponseEntity.ok(transactionsService.getAllInLast30Sec(LocalDateTime.now()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionsService.getById(id)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction with ID " + id + " not found")));
    }

    @PostMapping("{from}/{to}/{amount}")
    public ResponseEntity<Transaction> makeTransaction(@PathVariable Long from,
                                                       @PathVariable Long to,
                                                       @PathVariable String amount) {
        return ResponseEntity.ok(transactionsService.makeTransaction(from, to, amount));
    }
}
