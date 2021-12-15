package me.akvelon.collector.services.interfaces;

import me.akvelon.collector.dto.UserDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

public interface UsersService {
    List<UserDto> getAll();
    UserDto getById(Long id);
    UserDto getByFullName(String fullName);
    void changeAmountOfMoney(Long userId, BigDecimal amount);
    void deleteUserById(Long id);
}
