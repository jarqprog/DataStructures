package com.jarq.customStack;

public interface ICustomStack<T> {

    boolean push(T item);
    T pop();
    T peek();
    int size();
    int placesLeft();
}
