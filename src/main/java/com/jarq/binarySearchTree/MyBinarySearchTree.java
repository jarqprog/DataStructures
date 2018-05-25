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
        if( ! contains(value) ) {
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
        return root != null && root.find(value) != null;
    }

    @Override
    public T findMinimum() {
        if(root == null) {
            return null;
        }

        Node<T> node = root.minimum();
        return node != null ? node.data : null;
    }

    @Override
    public T findMaximum() {
        if(root == null) {
            return null;
        }

        Node<T> node = root.maximum();
        return node != null ? node.data : null;
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

            if( compareTo(value) == 0 ) {
                return this;
            }

            else if( compareTo(value) > 0 ) {
                return (leftChild == null) ? null : leftChild.find(value);
            }
            return (rightChild == null) ? null : rightChild.find(value);
        }

        private Node<N> minimum() {
            return this.leftChild == null ? this : this.leftChild.minimum();
        }

        private Node<N> maximum() {
            return this.rightChild == null ? this : this.rightChild.maximum();
        }

        private boolean delete(N value) {
            Node<N> node = find(value);
            if(node == null) {
                return false;
            }

            if(! node.hasParent() ) {  // it's tree's root
                if( node.isLeaf() ) {
                    node.data = null;
                } else {
                    Node<N> leftChild = node.leftChild;
                    if (leftChild != null) {
                        Node<N> successor = leftChild.maximum();
                        // successor is node with highest value on the left branch (it's always childless leaf)
                        successor.breakRelationWithParent(successor.isLeftChild());  // dereference parent relation
                        node.data = successor.data;
                    } else {
                        node.data = rightChild.data;
                        rightChild.parent = null;
                        node.adoptSomeonesChildren(rightChild);
                    }
                }
                return true;
            }


            if( node.isLeaf() ) {  // it's regular node
                node.breakRelationWithParent();
            } else {
                Node<N> leftChild = node.leftChild;
                if(leftChild != null) {
                    Node<N> successor = leftChild.maximum();
                    // successor is node with highest value on the left branch (it's always childless leaf)
                    successor.breakRelationWithParent(successor.isLeftChild());  // dereference parent relation
                    successor.adoptSomeonesChildren(node);
                    successor.grabSomeonesParent(node);

                } else {
                    if( node.isLeftChild() ) {
                        node.rightChild.grabSomeonesParent(node, true);
                    } else {
                        node.rightChild.grabSomeonesParent(node, false);
                    }

                }
            }
            return true;
        }

        private int compareTo(Node<N> node) {
            return data.compareTo(node.data);
        }

        private int compareTo(N value) {
            return data.compareTo(value);
        }

        private boolean isLeaf() {
            return leftChild == null && rightChild == null;
        }

        private boolean hasParent() {
            return parent != null;
        }

        private void breakRelationWithParent() {
            if(hasParent()) {
                breakRelationWithParent( isLeftChild() );
            }
        }

        private void breakRelationWithParent(boolean isLeftChild) {
            if(hasParent()) {
                if(isLeftChild) {
                    parent.leftChild = null;
                } else {
                    parent.rightChild = null;
                }
                parent = null;
            }
        }

        private void adoptSomeonesChildren(Node<N> formerParent) {

            leftChild = formerParent.leftChild;
            formerParent.leftChild = null;
            if(leftChild != null) {
                leftChild.parent = this;
            }

            rightChild = formerParent.rightChild;
            formerParent.rightChild = null;
            if(rightChild != null) {
                rightChild.parent = this;
            }
        }

        private void grabSomeonesParent(Node<N> formerChild) {
            grabSomeonesParent(formerChild, formerChild.isLeftChild());
        }

        private void grabSomeonesParent(Node<N> formerChild, boolean beLeftChild) {
            if(formerChild != null && formerChild.parent != null) {
                parent = formerChild.parent;
                if( beLeftChild ) {
                    formerChild.parent.leftChild = this;
                } else {
                    formerChild.parent.rightChild = this;
                }
            }
        }

        private boolean isLeftChild() {
            return parent != null && this.compareTo(parent) < 0;
        }
    }
}
