package me.akvelon.collector.repositories.collections;

import me.akvelon.collector.exceptions.UserNotFoundException;
import me.akvelon.collector.models.User;
import me.akvelon.collector.repositories.intefraces.UsersRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
@Profile("dev-collections")
public class UsersRepositoryCollectionsImpl implements UsersRepository {

    private final AtomicLong currentUserId;
    private final Map<Long, User> users;

    public UsersRepositoryCollectionsImpl() {
        this.currentUserId = new AtomicLong(0L);
        this.users = new ConcurrentSkipListMap<>();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public List<User> findAll(int limit, int offset) {
        return users.values().stream().skip(offset).limit(limit).collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public User save(User entity) {
        var newUserId = currentUserId.incrementAndGet();
        entity.setId(newUserId);
        users.put(newUserId, entity);
        return entity;
    }

    @Override
    public void update(User entity) {
        users.computeIfPresent(entity.getId(), (id, user) -> {
            user.setAmountOfMoney(entity.getAmountOfMoney());
            user.setEmail(entity.getEmail());
            user.setFullName(entity.getFullName());
            return user;
        });
    }

    @Override
    public void delete(User entity) {
        users.remove(entity.getId());
    }

    @Override
    public void deleteById(Long id) {
        users.remove(id);
    }

    @Override
    public void changeAmountOfMoney(Long id, BigDecimal amount) {
        if (id != null) {
            users.computeIfPresent(id, (userId, user) -> {
                user.changeAmountOfMoney(amount);
                return user;
            });
        } else {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
    }
}
