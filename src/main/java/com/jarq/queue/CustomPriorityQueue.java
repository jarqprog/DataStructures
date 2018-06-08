package com.jarq.queue;

import java.util.Comparator;
import java.util.Vector;

/**
 * implementation with Vector
 */
public class CustomPriorityQueue<T extends Comparable<T>> implements ICustomQueue<T> {

    private Comparator<T> comparator;
    private int DEFAULT_SIZE = 10;
    private Vector<T> heap = new Vector<>(DEFAULT_SIZE);

    public CustomPriorityQueue() {
        this.comparator = new DefaultComparator();
    }

    public CustomPriorityQueue(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public boolean enqueue(T value) {
        heap.addElement(value);
        int index = size() - 1;
        heapifyUp(index);
        return true;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Is empty!");
        }
        return heap.firstElement();
    }

    @Override
    public T dequeue() {
        T toReturn = peek();

        heap.setElementAt(heap.lastElement(), 0);
        heap.remove(size()-1);
        heapifyDown(0);
        return toReturn;
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public String toString() {
        return heap.toString();
    }

    private void heapifyUp(int index) {
        if (index > 0 && comparator.compare(heap.get(getParentIndex(index)), heap.get(index)) < 0) {
            swap(index, getParentIndex(index));
            heapifyUp(getParentIndex(index));
        }
    }

    private void heapifyDown(int index) {

        int left = getLeftChildIndex(index);
        int right = getRightChildIndex(index);
        int largest = index;
        int size = size();

        if (left < size && comparator.compare(heap.get(left), heap.get(index)) > 0) {
            largest = left;
        }
        if (right < size && comparator.compare(heap.get(right), heap.get(largest)) > 0) {
            largest = right;
        }
        if (largest != index) {
            swap(index, largest);
            heapifyDown(largest);
        }
    }

    private int getParentIndex(int childIndex) {
        if(childIndex == 0) {
            return 0;
        }
        return (childIndex - 1) / 2;
    }

    private int getLeftChildIndex(int parentIndex) {
        return parentIndex * 2 + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return parentIndex * 2 + 2;
    }

    private void swap(int firstIndex, int secondIndex) {
        T temp = heap.get(firstIndex);
        heap.setElementAt(heap.get(secondIndex), firstIndex);
        heap.setElementAt(temp, secondIndex);
    }

    // helper class
    private class DefaultComparator implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return o2.compareTo(o1);
        }
    }
}
