package com.jarq.queue;

import java.util.Arrays;

public class MinHeapPriorityQueue<T extends Comparable<T>> implements ICustomQueue<T> {

    private T[] heap;
    private int size = 0;

    public MinHeapPriorityQueue(int initialSize) {
        this.heap = createHeap(initialSize);
    }

    public MinHeapPriorityQueue() {
        int DEFAULT_LENGTH = 1;
        this.heap = createHeap(DEFAULT_LENGTH);
    }

    @Override
    public boolean enqueue(T value) {
        if (size >= heap.length) {
            extendsHeap();
        }

        heap[size] = value;
        bubbleUp();
        size++;
        return true;
    }

    @Override
    public T peek() {

        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty!");
        }

        return heap[0];
    }

    @Override
    public T dequeue() {
        T value = peek();
        heap[0] = heap[size - 1];
        size--;
        sinkDown();
        heap[size] = null;
        return value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(heap);
    }

    @SuppressWarnings("unchecked")
    private T[] createHeap(int size) {
        return (T[]) new Comparable[size];
    }

    private int left(int index) {
        return index * 2 + 1;
    }

    private int right(int index) {
        return index * 2 + 2;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private void swap(int first, int second) {
        T temp = heap[first];
        heap[first] = heap[second];
        heap[second] = temp;
    }

    private void extendsHeap() {
        heap = Arrays.copyOf(heap, heap.length * 2);
    }

    private void bubbleUp() {
        int index = size;

        while (index > 0 && (heap[parent(index)].compareTo(heap[index]) > 0)) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    private void sinkDown() {
        int currentIndex = 0;
        T leftChild;
        T currentChild;
        T smallerChild;

        while (left(currentIndex) <= size) {

            int smaller = left(currentIndex);

            leftChild = heap[left(currentIndex)];
            currentChild = heap[currentIndex];


            if (right(currentIndex) <=size && leftChild.compareTo(heap[right(currentIndex)]) > 0) {
                smaller = right(currentIndex);
            }

            smallerChild = heap[smaller];

            if (currentChild.compareTo(smallerChild) > 0) {
                swap(currentIndex, smaller);
            } else {
                break;
            }
            currentIndex = smaller;
        }
    }
}
