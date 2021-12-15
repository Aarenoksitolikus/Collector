package me.akvelon.collector.services.interfaces;

import me.akvelon.collector.dto.UserForm;
import org.springframework.stereotype.Service;

@Service
public interface SignUpService {
    UserForm signUp(UserForm userForm);
}
