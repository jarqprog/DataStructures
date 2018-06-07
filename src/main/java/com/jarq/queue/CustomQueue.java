package com.jarq.queue;

public class CustomQueue<T> implements ICustomQueue<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public CustomQueue() {
        this.head = new Node<>(null, tail);
        this.tail = new Node<>(null, null);
    }
    
    @Override
    public boolean enqueue(T value) {
        if(size == 0) {
            head.value = value;
        }
        else if(size == 1) {
            tail.value = value;
        } else {
            tail.nextNode = new Node<>(value, null);
            tail = tail.nextNode;
        }
        size++;
        return true;
    }

    @Override
    public T peek() {
        return head.value;
    }

    @Override
    public T dequeue() {
        if(size == 0) {
            return null;
        }
        T value = head.value;
        if(size == 1) {
            head.value = null;
        }
        else if(size == 2) {
            head.value = tail.value;
            tail.value = null;
        } else {
            head = head.nextNode;
        }
        size--;
        return value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node<T> node = head;

        while(node.getNextNode() != null) {

            node = node.getNextNode();
            stringBuilder.append(String.valueOf(node.value));
            stringBuilder.append("; ");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    private class Node<V> {

        private V value;
        private Node<V> nextNode;

        private Node(V value, Node<V> nextNode) {
            this.value = value;
            this.nextNode = nextNode;
        }

        private Node<V> getNextNode() {
            return nextNode;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", nextNode=" + nextNode +
                    '}';
        }
    }
}
