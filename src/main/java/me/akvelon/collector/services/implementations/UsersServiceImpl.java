package me.akvelon.collector.services.implementations;

import me.akvelon.collector.models.Page;
import me.akvelon.collector.models.User;
import me.akvelon.collector.repositories.intefraces.UsersRepository;
import me.akvelon.collector.services.interfaces.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public Page<User> getPage(int limit, int offset) {
        return new Page<>((User[]) usersRepository.findAll(limit, offset).toArray());
    }

    @Override
    public Optional<User> getById(Long id){
        return usersRepository.findById(id);
    }

    @Override
    public void changeBalance(Long userId, String amount) {
        usersRepository.changeAmountOfMoney(userId, new BigDecimal(amount));
    }

    @Override
    public void deleteUserById(Long id) {
        usersRepository.deleteById(id);
    }
}
