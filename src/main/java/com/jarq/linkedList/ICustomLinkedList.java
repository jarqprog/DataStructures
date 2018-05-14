package com.jarq.linkedList;

public interface ICustomLinkedList<T> {

    boolean add(T element);
    boolean add(T element, int index);
    T get(int index);
    int size();
}
