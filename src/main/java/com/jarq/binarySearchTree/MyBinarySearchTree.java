package com.jarq.binarySearchTree;

public class MyBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {



    @Override
    public boolean add(T value) {
        return false;
    }

    @Override
    public boolean remove(T value) {
        return false;
    }

    @Override
    public boolean contains(T value) {
        return false;
    }

    @Override
    public T findMinimum() {
        return null;
    }

    @Override
    public T findMaximum() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
