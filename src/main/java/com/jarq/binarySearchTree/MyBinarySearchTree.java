package com.jarq.binarySearchTree;

public class MyBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {

    private Node<T> root;
    private int size = 0;

    @Override
    public boolean add(T value) {
        if(root == null) {
            root = new Node<>(value, null, null);
            size++;
            return true;
        }
        root.insert(value);
        size++;
        return true;
    }

    @Override
    public boolean remove(T value) {
        if(! contains(value)) {
            return false;
        }

        if(root.compareTo(value) == 0 && size == 1) {
            root = null;
            size--;
            return true;
        }

        root = root.delete(value, root);
        size--;
        return true;
    }

    @Override
    public boolean contains(T value) {
        return root != null && root.find(value) != null;
    }

    @Override
    public T findMinimum() {
        if(root == null) {
            return null;
        }

        Node<T> node = root.minimum();
        return node != null ? node.getData() : null;
    }

    @Override
    public T findMaximum() {
        if(root == null) {
            return null;
        }

        Node<T> node = root.maximum();
        return node != null ? node.getData() : null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Node<T> getRoot() {
        return root;
    }

}
