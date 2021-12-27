package me.akvelon.collector.repositories.intefraces;

import me.akvelon.collector.models.User;
import me.akvelon.collector.repositories.CrudRepository;

import java.math.BigDecimal;

public interface UsersRepository extends CrudRepository<User, Long> {
    void changeAmountOfMoney(Long id, BigDecimal amount);
}
