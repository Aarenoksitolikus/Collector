package me.akvelon.collector.models;

import lombok.*;

@Data
@AllArgsConstructor
public class Page<T> {
    private Page<T> previous;
    private Page<T> next;
    private T[] content;

    public Page(T[] content) {
        this.content = content;
    }
}
