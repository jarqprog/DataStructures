package com.jarq.queue;

public class CustomQueue<T> implements ICustomQueue<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public CustomQueue() {
        this.head = new Node<>(null, null, tail);
        this.tail = new Node<>(null, head,null);
    }
    
    @Override
    public boolean enqueue(T value) {
        Node<T> node = new Node<>(value, tail.previous, tail);
        tail.previous.next = node;
        tail.previous = node;
        size++;
        return true;
    }

    @Override
    public T peek() {
        return head.next.value;
    }

    @Override
    public T dequeue() {
        Node<T> node = head.next;
        head.next = node.next;
        if(node.next != null) {
            node.next.previous = head;
        }
        size--;
        return node.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node<T> node = head;

        while(node.getNext() != null) {

            node = node.getNext();
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
        private Node<V> previous;
        private Node<V> next;

        private Node(V value, Node<V> previous, Node<V> nextNode) {
            this.value = value;
            this.previous = previous;
            this.next = nextNode;
        }

        private Node<V> getNext() {
            return next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", previous=" + previous +
                    ", next=" + next +
                    '}';
        }

    }
}
