package com.jarq.queue;

public class CustomQueue<T> implements ICustomQueue<T> {


    @Override
    public boolean enqueue(T value) {
        return false;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public T dequeue() {
        return null;
    }

    @Override
    public int queueSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
