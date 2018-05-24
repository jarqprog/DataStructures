package com.jarq.binarySearchTree;

public class MyBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {

    private Node<T> root;
    private int size = 0;

    @Override
    public boolean add(T value) {
        if(root == null) {
            root = new Node<>(value, null, null, null);
            size++;
            return true;
        }
        root.insert(value);
        size++;
        return true;
    }

    @Override
    public boolean remove(T value) {
        if(root == null) {
            return false;
        }
        if(root.delete(value)) {
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(T value) {
        if(root == null) {
            return false;
        }
        return root.find(value) != null;
    }

    @Override
    public T findMinimum() {
        if(root == null) {
            return null;
        }

        Node<T> node = root.minimum();
        if(node != null) {
            return node.data;
        }
        return null;
    }

    @Override
    public T findMaximum() {
        if(root == null) {
            return null;
        }

        Node<T> node = root.maximum();
        if(node != null) {
            return node.data;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    private class Node<N extends Comparable<N>> {
        private N data;
        private Node<N> leftChild;
        private Node<N> rightChild;
        private Node<N> parent;

        private Node(N value, Node<N> leftChild, Node<N> rightChild, Node<N> parent) {
            this.data = value;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.parent = parent;
        }

        private void insert(N value) {
            if(data.compareTo(value) > 0) {
                if(leftChild == null) {
                    leftChild = new Node<>(value, null, null, this);
                } else {
                    leftChild.insert(value);
                }
            } else {
                if(rightChild == null) {
                    rightChild = new Node<>(value, null, null, this);
                } else {
                    rightChild.insert(value);
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
                return (leftChild == null) ? null : leftChild.find(value);
            }
            return (rightChild == null) ? null : rightChild.find(value);
        }

        private Node<N> minimum() {
            if(this.leftChild == null) {
                return this;
            }
            return this.leftChild.minimum();
        }

        private Node<N> maximum() {
            if(this.rightChild == null) {
                return this;
            }
            return this.rightChild.maximum();
        }

        private boolean delete(N value) {
            Node<N> node = find(value);
            if(node == null) {
                return false;
            }

            boolean isLeftChild = node.compareTo(node.parent) < 0;

            if(node.isLeaf()) {  // has no children
                if(isLeftChild) {
                    node.parent.leftChild = null;
                } else {
                    node.parent.rightChild = null;
                }
            } else {
                Node<N> leftChild = node.leftChild;
                if(leftChild != null) {
                    Node<N> successor = leftChild.maximum();
                    successor.parent.rightChild = null;

                    successor.leftChild = node.leftChild;
                    successor.rightChild = node.rightChild;
                    if(isLeftChild) {
                        node.parent.leftChild = successor;
                    } else {
                        node.parent.rightChild = successor;
                    }
                } else {
                    if(isLeftChild) {
                        node.parent.leftChild = node.rightChild;
                    } else {
                        node.parent.rightChild = node.rightChild;
                    }
                }
            }
            return true;
        }

        private int compareTo(Node<N> node) {
            if(node == null) {
                return 1;
            }
            return this.data.compareTo(node.data);
        }

        private boolean isLeaf() {
            return leftChild == null && rightChild == null;
        }
    }
}
