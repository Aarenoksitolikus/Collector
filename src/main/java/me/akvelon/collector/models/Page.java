package me.akvelon.collector.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Page<T> {
    private Page<T> previous;
    private Page<T> next;
    private T[] content;
}
