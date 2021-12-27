package me.akvelon.collector.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.akvelon.collector.exceptions.BalanceCheckException;

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
            if (this.amountOfMoney.compareTo(amountOfMoney) > 0) {
                this.amountOfMoney.subtract(amountOfMoney);
            } else throw new BalanceCheckException("User's amount of money is not enough to complete transaction");
        }
    }
}
