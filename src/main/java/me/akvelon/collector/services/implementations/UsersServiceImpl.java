package me.akvelon.collector.services.implementations;

import me.akvelon.collector.dto.UserDto;
import me.akvelon.collector.services.interfaces.UsersService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Override
    public List<UserDto> getAll() {
        return null;
    }

    @Override
    public UserDto getById(Long id) {
        return null;
    }

    @Override
    public UserDto getByFullName(String fullName) {
        return null;
    }

    @Override
    public void changeAmountOfMoney(Long userId, BigDecimal amount) {

    }

    @Override
    public void deleteUserById(Long id) {

    }
}
