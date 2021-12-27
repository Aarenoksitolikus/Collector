package me.akvelon.collector.services.interfaces;

import me.akvelon.collector.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    List<User> getAll();
    Optional<User> getById(Long id);
    void changeAmountOfMoney(Long userId, String amount);
    void deleteUserById(Long id);
}
