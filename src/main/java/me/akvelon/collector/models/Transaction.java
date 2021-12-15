package me.akvelon.collector.models;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Transaction {
    private Long id;
    private Long from;
    private Long to;
    private BigDecimal amount;
    private LocalDateTime timestamp;
}
