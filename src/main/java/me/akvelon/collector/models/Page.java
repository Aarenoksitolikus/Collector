package me.akvelon.collector.models;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Page<T> {
    private Page<T> previous;
    private Page<T> next;
    @NonNull
    private T[] content;
}
