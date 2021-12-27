package me.akvelon.collector.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class DataAccessThreadPool {
    @Value("")
    private Integer

    private ExecutorService executorService;

    public DataAccessThreadPool() {
        this.executorService = Executors.newFixedThreadPool(5);
    }

    public void submit(Runnable runnable) {
        this.executorService.submit(runnable);
    }
}
