package com.jarq.customStack;


public class CustomStack<T> implements ICustomStack<T> {

    private int size;
    private int currentIndex;
    private final T[] stack;

    @SuppressWarnings("unchecked")
    public CustomStack(int size) {
        this.size = size;
        this.currentIndex = -1;
        this.stack = (T[]) new Object[size];
    }

    @Override
    public boolean push(T item) {
        if(currentIndex >= size-1) {
            throw new StackOverflow();
        }
        currentIndex++;
        stack[currentIndex] = item;
        return true;
    }

    @Override
    public T pop() {
        if(currentIndex == -1) {
            throw new StackUnderflow();
        }

        T element = stack[currentIndex];
        stack[currentIndex--] = null;
        return element;
    }

    @Override
    public T peek() {
        if(currentIndex == -1) {
            throw new StackUnderflow();
        }
        return stack[currentIndex--];
    }

    @Override
    public int size() {
        return stack.length;
    }

    @Override
    public int placesLeft() {
        return stack.length - currentIndex-1;
    }
}
