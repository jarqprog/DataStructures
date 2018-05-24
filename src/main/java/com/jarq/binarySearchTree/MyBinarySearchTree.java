package com.jarq.binarySearchTree;

public class MyBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {

    private Node<T> root = new Node<>(null, null, null);
    private int size = 0;

    @Override
    public boolean add(T value) {
        root.insert(value);
        size++;
        return true;
    }

    @Override
    public boolean remove(T value) {
        return false;
    }

    @Override
    public boolean contains(T value) {
        return root.find(value) != null;
    }

    @Override
    public T findMinimum() {
        Node<T> node = root.minimum();
        if(node != null) {
            return node.data;
        }
        return null;
    }

    @Override
    public T findMaximum() {
        return null;
    }

    @Override
    public int size() {
        return size;
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
            if(data == null) {
                data = value;
                return;
            }
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

        private Node<N> find(N value) {
            if(data == null) {
                return null;
            }
            int factor = data.compareTo(value);
            if(factor == 0) {
                return this;
            }

            else if(factor > 0) {
                return (left == null) ? null : left.find(value);
            }
            return (right == null) ? null : right.find(value);
        }

        private Node<N> minimum() {
            if(this.left == null) {
                return this;
            }
            return this.left.minimum();
        }
    }
}
