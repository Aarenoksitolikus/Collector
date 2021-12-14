package me.akvelon.collector.repositories.collections;

import me.akvelon.collector.models.User;
import me.akvelon.collector.repositories.intefraces.UsersRepository;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Profile("dev-collections")
public class UsersRepositoryCollectionsImpl implements UsersRepository {

    private List<User> users;

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findById(java.lang.Long aLong) {
        return Optional.empty();
    }

    @Override
    public void save(me.akvelon.collector.models.User entity) {

    }

    @Override
    public void update(me.akvelon.collector.models.User entity) {

    }

    @Override
    public void deleteById(java.lang.Long id) {

    }

    @Override
    public void delete(me.akvelon.collector.models.User entity) {

    }

    @Override
    public void deleteAll(List<me.akvelon.collector.models.User> entities) {

    }

    @Override
    public Optional<me.akvelon.collector.models.User> findFirstByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<me.akvelon.collector.models.User> findByEmail(String email) {
        return Optional.empty();
    }
}
