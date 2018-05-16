package com.jarq.binaryTree;

public class IntBinarySearchTreeImpl implements IntBinarySearchTree {

    private Node root;
    private int size = 0;

    public boolean add(int value) {
        root = addRecursive(root, value);
        return true;
    }

    public int size() {
        return size;
    }

    public boolean containsValue(int value) {
        return containsRecursive(root, value);
    }

    private boolean containsRecursive(Node current, int value) {
        if(current == null) {
            return false;
        }
        if(current.value == value){
            return true;
        }
        if (current.value > value) {
            return containsRecursive(current.leftChild, value);
        } else {
            return containsRecursive(current.rightChild, value);
        }
    }

    private Node addRecursive(Node current, int value) {
        if (current == null) {
            size++;
            return new Node(value);
        }
        if (current.value > value) {
            current.leftChild = addRecursive(current.leftChild, value);
        } else if (current.value < value) {
            current.rightChild = addRecursive(current.rightChild, value);
        } else {
            return current;
        }
        return current;
    }

    private class Node {

        private final int value;
        private Node leftChild, rightChild;

        private Node(int value) {
            this.value = value;
        }

        public String toString() {
            return "Node with value: " + value;
        }
    }
}
