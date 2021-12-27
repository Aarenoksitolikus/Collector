package me.akvelon.collector.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class User {
    private Long id;
    private String fullName;
    private String email;
    private BigDecimal amountOfMoney;

    public void changeAmountOfMoney(BigDecimal amountOfMoney) {
        if (amountOfMoney.compareTo(new BigDecimal("0")) > 0) {
            this.amountOfMoney.add(amountOfMoney);
        } else {
            this.amountOfMoney.subtract(amountOfMoney);
        }
    }
}
