package com.jarq.queue;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ArrayQueue<T> implements ICustomQueue<T> {

    private T[] array;
    private int numberOfElements = 0;

    public ArrayQueue() {
        int initialSize = 2;
        this.array = createArray(initialSize);
    }

    @Override
    public boolean enqueue(T element) {

        boolean isAdded;

        if(size() == array.length) {
            isAdded = extendArrayAndAddElement(element);
        } else {
            this.array[numberOfElements] = element;
            isAdded = true;
        }

        numberOfElements++;
        return isAdded;
    }

    @Override
    public T peek() {
        if(size() == 0) {
            throw new QueueUnderflow();
        }
        return array[0];
    }

    @Override
    public T dequeue() {
        if(isEmpty()) {
            throw new QueueUnderflow();
        }

        T element = this.array[0];

        for(int i=1; i<this.array.length; i++) {
            this.array[i-1] = this.array[i];
        }

        numberOfElements--;
        return element;
    }

    @Override
    public int size() {
        return numberOfElements;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private boolean extendArrayAndAddElement(T elementToAdd) {

        int arraySizeMultiplier = 2;

        int newSize = this.array.length*arraySizeMultiplier;

        T[] extendedArray = createArray(newSize);

        for(int i=0; i<this.array.length; i++) {
            extendedArray[i] = this.array[i];
        }
        int firstNotOccupiedIndex = size();
        extendedArray[firstNotOccupiedIndex] = elementToAdd;

        this.array = extendedArray;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Queue: ");
        for(T element : array) {
            if(element != null) {
                sb.append(element.toString());
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    private T[] createArray(int size) {

        return (T[]) new Object[size];
    }
}
