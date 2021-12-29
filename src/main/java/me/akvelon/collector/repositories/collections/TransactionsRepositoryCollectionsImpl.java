package me.akvelon.collector.repositories.collections;

import me.akvelon.collector.exceptions.TransactionException;
import me.akvelon.collector.exceptions.UserNotFoundException;
import me.akvelon.collector.models.Transaction;
import me.akvelon.collector.repositories.intefraces.TransactionsRepository;
import me.akvelon.collector.repositories.intefraces.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Repository
@Profile("dev-collections")
public class TransactionsRepositoryCollectionsImpl implements TransactionsRepository {

    @Autowired
    private UsersRepository usersRepository;

    private final AtomicLong currentTransactionId;
    private final Map<Long, Transaction> transactions;
    private final AtomicReference<BigDecimal> lostMoney;

    public TransactionsRepositoryCollectionsImpl() {
        this.currentTransactionId = new AtomicLong(0L);
        this.transactions = new ConcurrentSkipListMap<>();
        this.lostMoney = new AtomicReference<>(new BigDecimal(0L));
    }

    @Override
    public List<Transaction> findAll() {
        return new ArrayList<>(transactions.values());
    }

    @Override
    public List<Transaction> findAll(int limit, int offset) {
        return transactions.values().stream().skip(offset).limit(limit).collect(Collectors.toList());
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return Optional.ofNullable(transactions.get(id));
    }

    @Override
    public Transaction save(Transaction entity) {
        if (entity.getAmount().compareTo(new BigDecimal("0")) > 0) {
            var from = entity.getFrom();
            var to = entity.getTo();
            var amount = entity.getAmount();

            usersRepository.changeAmountOfMoney(from, amount.negate());
            try {
                usersRepository.changeAmountOfMoney(to, amount);
            } catch (UserNotFoundException recipientNotFound) {
                try {
                    usersRepository.changeAmountOfMoney(from, amount);
                    throw new TransactionException("Target account not found. Transaction reverted");
                } catch (UserNotFoundException senderNotFound) {
                    this.lostMoney.set(lostMoney.get().add(amount));
                    throw new TransactionException("Target account not found. Source account not found. "
                            + "Please, contact support team");
                }
            }

            var newTransactionId = currentTransactionId.incrementAndGet();
            entity.setId(newTransactionId);
            transactions.put(newTransactionId, entity);
            return entity;
        } else {
            throw new TransactionException("The amount of money sent must be greater than 0");
        }
    }

    @Override
    public void update(Transaction entity) {
        // ignore
    }

    @Override
    public void delete(Transaction entity) {
        // ignore
    }

    @Override
    public void deleteById(Long id) {
        // ignore
    }

    @Override
    public List<Transaction> findAllLatest(LocalDateTime givenTime) {
        return transactions.values().stream().filter(transaction -> transaction.getTimestamp().isAfter(givenTime)).collect(Collectors.toList());
    }
}
