package me.akvelon.collector.services.interfaces;

import me.akvelon.collector.models.Transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionsService {
    List<Transaction> getAll();
    List<Transaction> getAllInLast30Sec(LocalDateTime time);
    Optional<Transaction> getById(Long id);
    Transaction makeTransaction(Long from, Long to, String amountOfMoney);
}
