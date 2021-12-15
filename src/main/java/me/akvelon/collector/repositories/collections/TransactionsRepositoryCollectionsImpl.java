package me.akvelon.collector.repositories.collections;

import me.akvelon.collector.models.Transaction;
import me.akvelon.collector.repositories.intefraces.TransactionsRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("dev-collections")
public class TransactionsRepositoryCollectionsImpl implements TransactionsRepository {
    @Override
    public List<Transaction> findAll() {
        return null;
    }

    @Override
    public Optional<Transaction> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Transaction save(Transaction entity) {
        return null;
    }

    @Override
    public void update(Transaction entity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Transaction entity) {

    }

    @Override
    public void deleteAll(List<Transaction> entities) {

    }

    @Override
    public List<Transaction> findAllInLast30Sec() {
        return null;
    }
}
