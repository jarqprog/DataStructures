package com.jarq.binarySearchTree;

public interface BinarySearchTree<T extends Comparable<T>> {

    boolean add(T value);
    boolean remove(T value);
    boolean contains(T value);
    T findMinimum();
    T findMaximum();
    int size();
    void drawTree();
}
