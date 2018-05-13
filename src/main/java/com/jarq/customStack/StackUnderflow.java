package com.jarq.customStack;

public class StackUnderflow extends RuntimeException {

    private final String message;

    public StackUnderflow() {
        this.message = "Stack is empty!";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
