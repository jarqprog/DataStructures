package com.jarq.queue;

public interface ICustomQueue<T> {

    boolean enqueue (T value);
    T peek();
    T dequeue();
    int queueSize();
    boolean isEmpty();
    boolean enqueue (T value, Integer priority);
}
