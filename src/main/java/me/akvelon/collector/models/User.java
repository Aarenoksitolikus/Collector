package me.akvelon.collector.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class User {
    private final Long id;
    private String name;
    private String email;
    private Integer balance;
}
