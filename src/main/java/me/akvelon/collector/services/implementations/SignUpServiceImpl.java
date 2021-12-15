package me.akvelon.collector.services.implementations;

import me.akvelon.collector.dto.UserForm;
import me.akvelon.collector.models.User;
import me.akvelon.collector.repositories.intefraces.UsersRepository;
import me.akvelon.collector.services.interfaces.SignUpService;
import me.akvelon.collector.utils.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public User signUp(UserForm userForm) {
        return usersRepository.save(UserMapper.formToUser(userForm));
    }
}
