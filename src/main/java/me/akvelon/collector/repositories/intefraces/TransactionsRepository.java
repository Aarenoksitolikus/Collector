package me.akvelon.collector.repositories.intefraces;

import me.akvelon.collector.models.Transaction;
import me.akvelon.collector.repositories.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionsRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findAllLatest(LocalDateTime time);
}
