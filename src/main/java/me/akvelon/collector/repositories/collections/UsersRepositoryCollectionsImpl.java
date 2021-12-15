package me.akvelon.collector.repositories.collections;

import me.akvelon.collector.models.User;
import me.akvelon.collector.repositories.intefraces.UsersRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("dev-collections")
public class UsersRepositoryCollectionsImpl implements UsersRepository {

    private List<User> users;

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public User save(User entity) {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void deleteAll(List<User> entities) {

    }

    @Override
    public Optional<User> findFirstByName(String name) {
        return Optional.empty();
    }
}
