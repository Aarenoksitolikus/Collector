package me.akvelon.collector.services.interfaces;

import me.akvelon.collector.models.Page;
import me.akvelon.collector.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    List<User> getAll();
    Page<User> getPage(int limit, int offset);
    Optional<User> getById(Long id);
    void changeBalance(Long userId, String amount);
    void deleteUserById(Long id);
}
