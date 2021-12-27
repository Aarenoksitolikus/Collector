package me.akvelon.collector.services.implementations;

import me.akvelon.collector.models.User;
import me.akvelon.collector.repositories.intefraces.UsersRepository;
import me.akvelon.collector.services.interfaces.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<User> getAll() {
        return usersRepository.findAll();
    }

    @Override
    public Optional<User> getById(Long id){
        return usersRepository.findById(id);
    }

    @Override
    public void changeAmountOfMoney(Long userId, String amount) {
        var user = usersRepository.findById(userId).orElse(null);
        if (user != null) {
            user.changeAmountOfMoney(amount);
        }
        usersRepository.update(user);
    }

    @Override
    public void deleteUserById(Long id) {
        usersRepository.deleteById(id);
    }
}
