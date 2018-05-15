package com.jarq.intStack;

import com.jarq.customStack.StackOverflow;
import com.jarq.customStack.StackUnderflow;

public class IntStackImpl implements IntStack {

    private final int size;
    private final int[] container;
    private int pointer;

    public IntStackImpl(int size) {
        this.size = size;
        this.container = new int[size];
        this.pointer = -1;
    }

    @Override
    public int pop() {
        if(pointer == -1) {
            throw new StackUnderflow();
        }

        return container[pointer--];
    }

    @Override
    public int peek() {
        if(pointer == -1) {
            throw new StackUnderflow();
        }
        return container[pointer];
    }

    @Override
    public boolean push(int element) {
        if(pointer == size-1) {
            throw new StackOverflow();
        }
        this.container[++pointer] = element;
        return true;
    }

}
