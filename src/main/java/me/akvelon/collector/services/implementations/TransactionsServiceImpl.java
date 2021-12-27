package me.akvelon.collector.services.implementations;

import me.akvelon.collector.exceptions.BalanceCheckException;
import me.akvelon.collector.exceptions.UserNotFoundException;
import me.akvelon.collector.models.Page;
import me.akvelon.collector.models.Transaction;
import me.akvelon.collector.repositories.intefraces.TransactionsRepository;
import me.akvelon.collector.repositories.intefraces.UsersRepository;
import me.akvelon.collector.services.interfaces.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<Transaction> getAll() {
        return transactionsRepository.findAll();
    }

    @Override
    public Page<Transaction> getPage(int limit, int offset) {
        return null;
    }

    @Override
    public List<Transaction> getAllTheLatest(LocalDateTime time) {
        return transactionsRepository.findAllInLast30Sec(time);
    }

    @Override
    public Optional<Transaction> getById(Long id) {
        return transactionsRepository.findById(id);
    }

    @Override
    public Transaction makeTransaction(Long from, Long to, String amountOfMoney) {
        return transactionsRepository.save(Transaction.builder()
                .from(from)
                .to(to)
                .amount(new BigDecimal(amountOfMoney))
                .timestamp(LocalDateTime.now())
                .build());
    }
}
