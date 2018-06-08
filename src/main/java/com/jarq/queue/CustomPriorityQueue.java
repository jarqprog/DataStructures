package com.jarq.queue;

import java.util.Comparator;

public class CustomPriorityQueue<T> implements ICustomQueue<T> {

    private Comparator<T> comparator;

    public CustomPriorityQueue() {
        this.comparator = comparator;
    }

    public CustomPriorityQueue(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public boolean enqueue(T value) {
        return false;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public T dequeue() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    private class Node<N> {

        private N value;

    }

    private class NodeComparator<N> implements Comparator<N> {

        @Override
        public int compare(N o1, N o2) {
            return 1;
        }
    }
}
