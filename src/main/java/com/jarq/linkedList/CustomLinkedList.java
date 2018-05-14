package com.jarq.linkedList;

public class CustomLinkedList<T> implements ICustomLinkedList<T> {

    private Node<T> head = new Node<>(null, null);
    private int size = 0;

    @Override
    public boolean add(T element) {
        size++;
        return false;
    }

    @Override
    public boolean add(T element, int index) {
        size++;
        return false;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    private class Node<V> {

        private V value;
        private Node<V> nextNode;

        private Node(V value, Node<V> nextNode) {
            this.value = value;
            this.nextNode = nextNode;
        }
    }
}
