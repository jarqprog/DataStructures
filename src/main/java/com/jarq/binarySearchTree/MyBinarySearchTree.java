package com.jarq.binarySearchTree;

public class MyBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {

    private Node<T> root;
    private int size = 0;

    @Override
    public boolean add(T value) {
        if(root == null) {
            root = new Node<>(value, null, null);
        } else {
            root.insert(value);
        }
        size++;
        return true;
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

    private class Node<N extends Comparable<N>> {
        private N data;
        private Node<N> left;
        private Node<N> right;

        private Node(N value, Node<N> left, Node<N> right) {
            this.data = value;
            this.left = left;
            this.right = right;
        }

        private void insert(N value) {
            if(data.compareTo(value) >= 0) {
                if(left == null) {
                    left = new Node<>(value, null, null);
                } else {
                    left.insert(value);
                }
            } else {
                if(right == null) {
                    right = new Node<>(value, null, null);
                } else {
                    right.insert(value);
                }
            }
        }
    }
}
