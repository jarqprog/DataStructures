package com.jarq.binaryTree;

public class IntBinarySearchTree {

    private Node root;

    private class Node {

        final int value;
        Node left, right;

        private Node(int value) {
            this.value = value;
        }
    }
}
