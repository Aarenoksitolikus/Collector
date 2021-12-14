package me.akvelon.collector.services.interfaces;

import me.akvelon.collector.repositories.intefraces.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface UsersService {
    void signUp();
    void signIn();
}
