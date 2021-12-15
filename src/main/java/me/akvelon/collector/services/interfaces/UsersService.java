package me.akvelon.collector.services.interfaces;

import me.akvelon.collector.dto.UserDto;
import me.akvelon.collector.repositories.intefraces.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsersService {
    void signUp();
    List<UserDto> getAll();
    UserDto getById(Long id);
    UserDto getByFullName(String fullName);
}
