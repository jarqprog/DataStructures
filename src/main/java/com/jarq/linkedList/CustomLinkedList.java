package com.jarq.linkedList;

public class CustomLinkedList<T> implements ICustomLinkedList<T> {

    private Node<T> head = new Node<>(null, null);
    private int size = 0;

    @Override
    public boolean add(T element) {
        return add(element, size);
    }

    @Override
    public boolean add(T element, int index) {
        if(index > size || index < 0) {
            throw new IndexOutOfBoundsException("Invalid index: " + index + ", size: " + size);
        }

        Node<T> current = head;
        Node<T> next = head.nextNode;

        int counter = 0;
        while(next != null && counter <= index) {
            current = next;
            next = next.nextNode;
            counter++;
        }

        current.nextNode = new Node<>(element, next);
        size++;
        return true;
    }

    @Override
    public T get(int index) {
        if(index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Invalid index: " + index + ", size: " + size);
        }

        Node<T> current = head.nextNode;

        int counter = 0;
        while(current.nextNode != null && counter <= index) {
            current = current.nextNode;
            counter++;
        }
        return current.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T getLast() {
        return get(size - 1);
    }

    @Override
    public T getFirst() {
        return get(0);
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
