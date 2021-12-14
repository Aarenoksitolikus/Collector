package me.akvelon.collector.repositories.intefraces;

import me.akvelon.collector.models.User;
import me.akvelon.collector.repositories.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
    Optional<User> findFirstByName(String name);
    Optional<User> findByEmail(String email);
}
