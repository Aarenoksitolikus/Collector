package me.akvelon.collector.utils;

import me.akvelon.collector.dto.UserForm;
import me.akvelon.collector.models.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("Mapper")
public class UserMapper {
    public static User formToUser(UserForm form) {
        return User.builder()
                .fullName(form.getFullName())
                .email(form.getEmail())
                .amountOfMoney(new BigDecimal("0"))
                .build();
    }
}
