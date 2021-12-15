package me.akvelon.collector.utils;

import me.akvelon.collector.dto.UserDto;
import me.akvelon.collector.dto.UserForm;
import me.akvelon.collector.models.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("Mapper")
public class UserMapper {
    public static User formToUser(UserForm form) {
        return User.builder()
                .fullName(form.getFullName())
                .amountOfMoney(new BigDecimal("0"))
                .build();
    }

    public static UserDto userToDto(User user) {
        return UserDto.builder()
                .fullName(user.getFullName())
                .amountOfMoney(user.getAmountOfMoney())
                .build();
    }
}
