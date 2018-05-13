package com.jarq.queue;

public class CustomQueue<T> implements ICustomQueue<T> {

    private Node<T> head;

    public CustomQueue() {
        this.head = new Node<>(null, null);
    }
    
    @Override
    public boolean enqueue(T value) {
        return enqueue(value, 0);
    }

    @Override
    public boolean enqueue(T value, Integer priority) {
        Node<T> first = head.getNextNode();
        if(first == null) {
            head.setNextNode(new Node<>(value, null, priority));
        }

        Node<T> current = head;
        Node<T> next = first;

        while(next != null && next.getPriority() >= priority) {
            current = next;
            next = next.getNextNode();
        }

        current.setNextNode(new Node<>(value, next, priority));
        return true;
    }

    @Override
    public T peek() {
        Node<T> first = head.getNextNode();
        if(first == null) {
            return null;
        }
        return first.getValue();
    }

    @Override
    public T dequeue() {
        Node<T> first = head.getNextNode();
        if(first == null) {
            return null;
        }

        Node<T> second = first.getNextNode();
        head.setNextNode(second);
        return first.getValue();
    }

    @Override
    public int queueSize() {

        Node<T> node = head;
        int counter = 0;
        while(node.getNextNode() != null) {
            node = node.getNextNode();
            counter++;
        }
        return counter;
    }

    @Override
    public boolean isEmpty() {
        return head.getNextNode() == null;
    }

    private class Node<V> {

        private V value;
        private Node<V> nextNode;
        private int priority;

        private Node(V value, Node<V> nextNode) {
            this.value = value;
            this.nextNode = nextNode;
            this.priority = 0;
        }

        private Node(V value, Node<V> nextNode, int priority) {
            this.value = value;
            this.nextNode = nextNode;
            this.priority = priority;
        }

        private V getValue() {
            return value;
        }

        private Node<V> getNextNode() {
            return nextNode;
        }

        private void setNextNode(Node<V> node) {
            nextNode = node;
        }

        private int getPriority() {
            return priority;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", nextNode=" + nextNode +
                    ", priority=" + priority +
                    '}';
        }
    }
}
