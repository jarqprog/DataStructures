package com.jarq.customStack;

public class StackOverflow extends RuntimeException {

    private final String message;

    public StackOverflow() {
        this.message = "Stack is full!";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
