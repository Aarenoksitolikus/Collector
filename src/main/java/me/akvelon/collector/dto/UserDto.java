package me.akvelon.collector.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class UserDto {
    private String fullName;
    private BigDecimal amountOfMoney;
}
