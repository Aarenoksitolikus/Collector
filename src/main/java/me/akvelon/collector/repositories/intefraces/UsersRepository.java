package me.akvelon.collector.repositories.intefraces;

import me.akvelon.collector.models.User;
import me.akvelon.collector.repositories.CrudRepository;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, Long> {
    Optional<User> findFirstByName(String name);
}
