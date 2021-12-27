package me.akvelon.collector.exceptions;

public class BalanceCheckException extends RuntimeException {
    private String message;

    public BalanceCheckException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
