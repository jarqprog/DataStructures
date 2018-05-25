package com.jarq.binarySearchTree;

class Node<T extends Comparable<T>> {

    private T data;
    private Node<T> smallChild;
    private Node<T> bigChild;

    Node(T value, Node<T> smallChild, Node<T> bigChild) {
        this.data = value;
        this.smallChild = smallChild;
        this.bigChild = bigChild;
    }

    void insert(T value) {
        if(data.compareTo(value) > 0) {
            if(smallChild == null) {
                smallChild = new Node<>(value, null, null);
            } else {
                smallChild.insert(value);
            }
        } else {
            if(bigChild == null) {
                bigChild = new Node<>(value, null, null);
            } else {
                bigChild.insert(value);
            }
        }
    }

    Node<T> find(T value) {
        if(data == null) {
            return null;
        }

        if( compareTo(value) == 0 ) {
            return this;
        }

        else if( compareTo(value) > 0 ) {
            return (smallChild == null) ? null : smallChild.find(value);
        }
        return (bigChild == null) ? null : bigChild.find(value);
    }

    Node<T> minimum() {
        return this.smallChild == null ? this : this.smallChild.minimum();
    }

    Node<T> maximum() {
        return this.bigChild == null ? this : this.bigChild.maximum();
    }

    Node<T> delete(T value, Node<T> current) {
        // based on https://www.cs.usfca.edu/

        if(current == null) {
            return null;
        }

        if(value.compareTo(current.data) < 0) {
            current.smallChild = delete(value, current.smallChild);
        }

        else if(value.compareTo(current.data) > 0) {
            current.bigChild = delete(value, current.bigChild);
        }

        else if(current.smallChild != null && current.bigChild != null) {
            current.data = current.smallChild.maximum().data;
            current.smallChild = current.smallChild.delete(current.data, current.smallChild);
        } else {
            current = current.smallChild != null ? current.smallChild : current.bigChild;
        }
        return current;
    }

    int compareTo(Node<T> node) {
        return data.compareTo(node.data);
    }

    int compareTo(T value) {
        return data.compareTo(value);
    }

    @Override
    public String toString() {
        return "Node with value: " + data.toString();
    }

    T getData() {
        return data;
    }

    void setData(T data) {
        this.data = data;
    }

    Node<T> getSmallChild() {
        return smallChild;
    }

    void setSmallChild(Node<T> smallChild) {
        this.smallChild = smallChild;
    }

    Node<T> getBigChild() {
        return bigChild;
    }

    void setBigChild(Node<T> bigChild) {
        this.bigChild = bigChild;
    }
}
   