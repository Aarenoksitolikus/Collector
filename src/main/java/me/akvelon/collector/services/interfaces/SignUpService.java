package me.akvelon.collector.services.interfaces;

import me.akvelon.collector.dto.UserForm;
import me.akvelon.collector.models.User;

public interface SignUpService {
    User signUp(UserForm userForm);
}
